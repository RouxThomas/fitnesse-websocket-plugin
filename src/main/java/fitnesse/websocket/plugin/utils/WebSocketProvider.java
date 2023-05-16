package fitnesse.websocket.plugin.utils;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fitnesse.websocket.plugin.utils.WSClientSession;

public class WebSocketProvider {

	private static volatile WebSocketProvider _instance;
	private static Object mutex = new Object();
	private static Map<String, WSClientSession> _wsClients = new HashMap<String, WSClientSession>();

	private WebSocketProvider() {
	}

	public static WebSocketProvider getInstance() {
		WebSocketProvider result = _instance;
		if (result == null) {
			synchronized (mutex) {
				result = _instance;
				if (result == null)
					_instance = result = new WebSocketProvider();
			}
		}
		return result;
	}

	public void newSession(String sessionId, String uri) throws Exception,
			IOException, URISyntaxException {
		if (_wsClients.containsKey(sessionId)) {
			throw new Exception("Session ID is already defined : " + sessionId);
		}
		_wsClients.put(sessionId, new WSClientSession(new URI(uri)));
	}

	public WSClientSession getSession(String sessionId) throws Exception,
			IOException, URISyntaxException {

		if (!_wsClients.containsKey(sessionId)) {
			throw new Exception("Session ID is not openned : " + sessionId);
		}
		return _wsClients.get(sessionId);
	}

	public List<TransmittedMessage> getMessages(String sessionId)
			throws Exception {

		if (!_wsClients.containsKey(sessionId)) {
			throw new Exception("Session ID is not openned : " + sessionId);
		}
		return _wsClients.get(sessionId).receivedMessages();
	}
}