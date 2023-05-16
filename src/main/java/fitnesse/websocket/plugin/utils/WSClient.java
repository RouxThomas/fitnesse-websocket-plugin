package fitnesse.websocket.plugin.utils;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import fitnesse.websocket.plugin.tests.utils.WSServer;

@ClientEndpoint
public class WSClient {

	private static final Logger _logger = Logger.getLogger(WSServer.class
			.getName());
	private List<TransmittedMessage> _messagesList;

	public WSClient(List<TransmittedMessage> msgs) {
		_messagesList = msgs;
	}

	@OnOpen
	public void onOpen(Session session) {
		_logger.log(
				Level.INFO,
				"[CLIENT]: Connection established with session ID : "
						+ session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		_logger.log(Level.INFO, "[CLIENT]: received msg : " + message);
		TransmittedMessage msg = new TransmittedMessage(false, message,
				System.currentTimeMillis());
		_messagesList.add(msg);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		_logger.log(Level.INFO, "[CLIENT]: Session " + session.getId()
				+ " close, because " + closeReason);
	}

	@OnError
	public void onError(Session session, Throwable err) {
		_logger.log(Level.INFO,
				"[CLIENT]: Error!!!!!, Session ID: " + session.getId() + ", "
						+ err.getMessage());
	}

}
