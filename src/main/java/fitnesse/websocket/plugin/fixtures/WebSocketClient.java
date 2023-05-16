package fitnesse.websocket.plugin.fixtures;

import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fitnesse.websocket.plugin.tests.utils.WSServer;
import fitnesse.websocket.plugin.utils.RunnableWaitingTime;
import fitnesse.websocket.plugin.utils.TransmittedMessage;
import fitnesse.websocket.plugin.utils.WSClientSession;
import fitnesse.websocket.plugin.utils.WebSocketProvider;

public class WebSocketClient {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());
	private String _sessionId;

	public WebSocketClient(String sessionId) {
		_sessionId = sessionId;
	}

	public void connectTo(String uri) throws Exception, DeploymentException, IOException,
			URISyntaxException {
		_logger.log(Level.INFO, "[FIXTURE WebSocketClient] : connect to " + uri);
		WebSocketProvider.getInstance().newSession(_sessionId, uri);
	}

	public void sendMsg(String message) throws Exception, DeploymentException,
			IOException, URISyntaxException {
		WSClientSession ws = WebSocketProvider.getInstance().getSession(_sessionId);
		ws.sendMessage(message);
	}

	public boolean receivedMsg(String message) throws Exception,
			DeploymentException, IOException, URISyntaxException {
		WSClientSession ws = WebSocketProvider.getInstance().getSession(_sessionId);
		List<TransmittedMessage> messages = ws.receivedMessages();

		/* get time of identified msg */
		boolean isFound = false;
		long transmittedMsgTime = 0;
		Iterator<TransmittedMessage> itBegin = messages.iterator();
		Iterator<TransmittedMessage> it = itBegin;

		while (!isFound && it.hasNext()) {
			TransmittedMessage transmittedMessage = it.next();
			isFound = message.equalsIgnoreCase(transmittedMessage.message());
			transmittedMsgTime = transmittedMessage.timeInMs();
		}
		_logger.log(Level.INFO,
				"[receivedMsgInEllapsedTimeInMsFromIdentifier], isFound:"
						+ isFound);
		return isFound;
	}

	public void close() throws java.lang.Exception, java.io.IOException {
		WebSocketProvider.getInstance().getSession(_sessionId).close();
	}

}
