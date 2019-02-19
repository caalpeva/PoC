package team.boolbee.poc.concurrency.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LiftOff implements Runnable {

	private static Log logger = LogFactory.getLog(LiftOff.class);
	
	private static int taskCount = 0;
	private final int id = taskCount++;
	protected int countDown = 10;
	
	public LiftOff() { }
	
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String getStatus() {
		return String.format("# %d (%s)", id, (countDown > 0)
				? countDown: "Liftoff");
	}
	
	public void run() {
		while(countDown-- > 0) {
			logger.info(getStatus());
			Thread.yield(); // Sugerencia de cambio de contexto para el planificador de hebras
		} // while
	}
	
	public static void main(String[] args) {
		// La tarea no est� dirigida por una hebra separada,
		// sino que simplemente est� utilizando la hebra asignada a main() 
//		LiftOff liftOff = new LiftOff();
//		liftOff.run();
		
		// La forma tradicional de transformar un objeto Runnable en una
		// tarea funcional consiste en entreg�rselo a un constructor Thread
//		for(int i = 0; i < 5; i++) {
//			Thread t = new Thread(new LiftOff());
//			t.start();
//		} // for
//		logger.info("Waiting for LiftOff");
		
		// Los ejecutores simplifican la programaci�n concurrente
		// encarg�ndose de gestionar los objetos Threads
		
		// CachedThreadPool generar� tantas hebras como necesite durante la
		// ejecuci�n de un programa y luego dejar� de crear nuevas hebras
		// a medida que vaya pudiendo reciclar las antiguas.
		ExecutorService exec = Executors.newCachedThreadPool();
		
		// Con FixedThreadPool se realiza la costosa asignaci�n de hebras una �nica vez,
		// por adelantado, con lo que limitamos el n�mero de hebras. Con esta soluci�n
		// se ahorra tiempo y se evita agotar los recursos disponibles. 
		//ExecutorService exec = Executors.newFixedThreadPool(3);
		
		// SingleThreadEcecutor es como FixedThreadPool pero con un tama�o de �nica hebra.
		// Si se env�a m�s de una tarea a SingleThreadExecutor, las tareas se pondr�n
		// en cola y cada una de ellas se ejecutar� hasta completarse antes de que se
		// inicie la siguiente tarea, utilizando todas la misma hebra.
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		} // for
		
		exec.shutdown(); // Impide que se env�en nuevas tareas al objecto Executor
	}
}