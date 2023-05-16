package fitnesse.websocket.plugin.tests.utils;

public class WSServerProvider {

	private static volatile WSServerProvider _instance;
	private static Object mutex = new Object();
	private static volatile WSServer _server = new WSServer();

	private WSServerProvider() {
	}

	public static WSServerProvider getInstance() {
		WSServerProvider result = _instance;
		if (result == null) {
			synchronized (mutex) {
				result = _instance;
				if (result == null)
					_instance = result = new WSServerProvider();
			}
		}
		return result;
	}

	public WSServer getServer() {
		return _server;
	}
}