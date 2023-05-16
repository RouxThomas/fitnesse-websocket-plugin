package fitnesse.websocket.plugin.tests.utils;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/sameAnswer")
public class sameAnswerEndPoint {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());

	@OnOpen
	public void onOpen(Session session) {
		_logger.log(Level.INFO, "[SERVER] /sameAnswer : onOpen");
	}

	@OnMessage
	public void onMessage(String message, Session session)
			throws java.io.IOException {

		_logger.log(Level.INFO,
				"[SERVER] /sameAnswer : onMessage, received message : "
						+ message + ", and response is : " + message);	
		session.getBasicRemote().sendText(message);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		_logger.log(Level.INFO, "[SERVER] /sameAnswer : onClose, session "
				+ session.getId() + " close, because " + closeReason);
	}
}