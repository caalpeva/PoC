package team.boolbee.poc.concurrency.basic.cooperation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Sender implements Runnable {
	
	private PipedWriter out = new PipedWriter();
	
	public PipedWriter getPipedWriter() {
		return out;
	}
	
	public void run() {
		try {
			while(true) {
				for(char c = 'A'; c <= 'z'; c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(500);
				} // for
			} // while
		} catch(IOException e) {
			PipedIO.logger.info(e + " Sender write exception");
		} catch (InterruptedException e) {
			PipedIO.logger.info(e + " Sender sleep interrupted");
		}
	}
}

class Receiver implements Runnable {
	
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}
	
	public void run() {
		try {
			while(true) {
				// Se bloquea hasta que haya caracteres
				char c = (char) in.read(); // PipedReader.read() interrumpible
				PipedIO.logger.info("Read: " + c);
			} // while
		} catch(IOException e) {
			PipedIO.logger.info(e + " Receiver read exception");
		}
	}
}

public class PipedIO {
	
	static Log logger = LogFactory.getLog(PipedIO.class);
	
	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}