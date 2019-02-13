package team.boolbee.poc.concurrency.basic.blocking;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterruptionChecker implements Runnable {

	private static Log logger = LogFactory.getLog(InterruptionChecker.class);
	
	private volatile double d = 0.0;
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				try {
					logger.info("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					
					// Una operaci�n no bloqueante de larga duraci�n
					logger.info("Calculating");
					for(int i = 1; i < 25000; i ++) {
						d = d + (Math.PI + Math.E) / d;
					} // for
					logger.info("Finished time-consuming operation");
				} finally {
					// Para garantizar la limpieza de recursos
				}
			} // while
			logger.info("Exiting via while() test");
		} catch (InterruptedException e) {
			logger.info("Exiting via InterruptedException");
		}
	}

	// Cuando se invoca a interrupt() para una hebra, el �nico instante
	// en que se produce la interrupci�n es cuando la tarea entra, o se encuentra ya dentro,
	// de una operaci�n bloqueante (excepto en m�todos sincronizados bloqueados o E/S no interrumpibles)
	
	// Dependiendo de la l�gica de la tarea es posible que no se realice esa llamada bloqueante.
	// Para detener dicha tarea, la misma necesita una segunda forma de finalizar.
	// Comprobando el estado interrumpido (fijado por la llamada a interrupt()) mediante Thread.interrupted()
	// es posible abandonar el bucle run() sin generar una excepci�n. Este indicador de estado interrumpido
	// ser� reiniciado cuando se genere la excepci�n o si la tarea llama a Thread.interrupted().
	
	// Por lo tanto, la notificaci�n de interrupt() se recibir� a trav�s de una �nica excepci�n
	// InterruptedException o una �nica comprobaci�n con �xito del m�todo Thread.interrupted().
}