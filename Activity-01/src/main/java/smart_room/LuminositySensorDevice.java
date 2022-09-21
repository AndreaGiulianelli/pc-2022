package smart_room;

public interface LuminositySensorDevice extends EventSource {
	/**
	 * @return value between 0 and 1.
	 */
	double getLuminosity();
		
}
