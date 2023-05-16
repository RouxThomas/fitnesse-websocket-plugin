package fitnesse.websocket.plugin.fixtures;

public class Wait {

	public boolean Milliseconds(int i) {
		boolean result;
		try {
			Thread.sleep(i);
			result = true;
		} catch (InterruptedException e) {
			result = false;
		}
		return result;
	}
}
