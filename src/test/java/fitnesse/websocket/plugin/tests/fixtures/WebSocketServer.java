package fitnesse.websocket.plugin.tests.fixtures;

import fitnesse.websocket.plugin.tests.utils.WSServerProvider;
import jakarta.websocket.DeploymentException;

public class WebSocketServer {

	public WebSocketServer() {
	}

	public void startOnAddressAndPort(String address, String port)
			throws jakarta.websocket.DeploymentException {
		WSServerProvider.getInstance().getServer()
				.start(address, Integer.valueOf(port));
	}

	public void stop() throws java.lang.InterruptedException {
		WSServerProvider.getInstance().getServer().stop();
	}
}
