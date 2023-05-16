package fitnesse.websocket.plugin.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import fitnesse.websocket.plugin.tests.utils.WSServer;

public class RunnableWaitingTime implements Runnable {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());
	private final long _nbMillisecToWait;

	public RunnableWaitingTime(long nbMillisecToWait) {
		_nbMillisecToWait = nbMillisecToWait;
	}

	public void run() {

		long timeToReachInMs = System.currentTimeMillis() + _nbMillisecToWait;

		while (System.currentTimeMillis() < timeToReachInMs) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				_logger.log(Level.ALL, e.getMessage());
			}
		}
	}
}
