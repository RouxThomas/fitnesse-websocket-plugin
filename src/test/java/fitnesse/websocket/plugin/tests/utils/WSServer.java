package fitnesse.websocket.plugin.tests.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.tyrus.server.Server;

public class WSServer {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());
	private static Server _server;

	public void start(String address, int port)
			throws jakarta.websocket.DeploymentException {
		_server = new Server(address, port, "/", null, sameAnswerEndPoint.class);

		_logger.log(Level.INFO, "[SERVER]: launch server");
		_server.start();

	}

	public void stop() {
		_logger.log(Level.INFO, "[SERVER] : stop server");
		_server.stop();
	}
}