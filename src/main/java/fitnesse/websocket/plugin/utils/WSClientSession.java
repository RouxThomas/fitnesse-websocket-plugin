package fitnesse.websocket.plugin.utils;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import fitnesse.websocket.plugin.tests.utils.WSServer;

@ClientEndpoint
public class WSClientSession {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());
	private List<TransmittedMessage> _receivedMessages;
	private WSClient _websocketClient;
	private Session _session;

	public WSClientSession(URI uri) throws java.io.IOException,
			jakarta.websocket.DeploymentException {
		_receivedMessages = Collections
				.synchronizedList(new ArrayList<TransmittedMessage>());
		_websocketClient = new WSClient(_receivedMessages);

		WebSocketContainer container = ContainerProvider
				.getWebSocketContainer();
		_session = container.connectToServer(_websocketClient, uri);
	}

	public List<TransmittedMessage> receivedMessages() {
		return _receivedMessages;
	}

	public void sendMessage(String message) throws java.io.IOException {
		TransmittedMessage msg = new TransmittedMessage(true, message,
				System.currentTimeMillis());
		_logger.log(Level.INFO, "[WSClientRunnable], sendMessage : " + message);
		_session.getBasicRemote().sendText(message);
	}

	public void close() throws java.io.IOException {
		_session.close();
	}
}
