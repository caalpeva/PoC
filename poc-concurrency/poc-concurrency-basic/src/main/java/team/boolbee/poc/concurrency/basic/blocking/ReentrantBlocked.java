package team.boolbee.poc.concurrency.basic.blocking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReentrantBlocked implements Runnable {

	private static Log logger = LogFactory.getLog(ReentrantBlocked.class);
	
	private BlockedMutex blockedMutex = new BlockedMutex();
	
	public void run() {
		logger.info("Waiting for f() in BlockedMutex");
		blockedMutex.f();
		logger.info("Broken out of blocked call");
	}
	
	class BlockedMutex {
		
		private Lock lock = new ReentrantLock();
		
		public BlockedMutex() {
			// Adquirirlo directamente, para demostrar la interrupci�n
			// de la tarea bloqueada en un bloqueo ReentrantLock
			lock.lock();
		}
		
		public void f() {
			try {
				// Esto no estar� nunca disponible para una segunda tarea
				lock.lockInterruptibly(); // Llamada especial
				logger.info("lock acquired in f()");
			} catch(InterruptedException e) {
				logger.info("Interrupted from lock acquisition in f()");
			}
		}
	}
	
	// A diferencia de las tareas bloqueadas en m�todos sincronos o secciones cr�ticas,
	// las tareas bloqueadas en bloqueos de tipo ReentranLock pueden ser interrumpibles.
}