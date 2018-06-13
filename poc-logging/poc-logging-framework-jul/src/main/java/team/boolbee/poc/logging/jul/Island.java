package team.boolbee.poc.logging.jul;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Island {
	static {
		Handler fh;
		try {
			String path = Island.class.getClassLoader().getResource("jul-logging.properties").getFile();
			System.setProperty("java.util.logging.config.file", path);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void execute() throws SecurityException, IOException {
		// public static Logger logger = Logger.getLogger("com.wombat.nose");
		Logger logger = Logger.getLogger("team.boolbee.poc.logging.jul");
		FileHandler fh = new FileHandler("%t/mylog.txt");
		logger.addHandler(fh);
		Logger.getLogger("team.boolbee.poc.logging.jul").setLevel(Level.FINEST);
		// Request that every detail gets logged.
		logger.setLevel(Level.ALL);
		// Log a simple INFO message.
		logger.info("doing stuff");
		try {
			String txt = null;
			txt.equals(null);
		} catch (Exception ex) {
			logger.log(Level.WARNING, "trouble sneezing", ex);
		}
		logger.fine("done");
	}
	
	public static void main(String[] args) {
		try {
			Island.execute();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}