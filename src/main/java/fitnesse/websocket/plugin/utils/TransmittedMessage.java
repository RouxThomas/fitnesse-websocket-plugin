package fitnesse.websocket.plugin.utils;

public class TransmittedMessage {

	private final boolean _isSent;
	private final String _message;
	private final long _timeInMs;

	public TransmittedMessage(boolean isSent, String message, long timeInMs) {
		_isSent = isSent;
		_message = message;
		_timeInMs = timeInMs;
	}

	public boolean isSent() {
		return _isSent;
	}

	public String message() {
		return _message;
	}

	public long timeInMs() {
		return _timeInMs;
	}
}
