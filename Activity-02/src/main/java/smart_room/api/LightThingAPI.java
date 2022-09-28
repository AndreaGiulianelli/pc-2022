package smart_room.api;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Toy LightThing API 
 *   
 * @author aricci
 *
 */
public interface LightThingAPI {
	
	String getId();
	
	/* properties */

	/**
	 * 
	 * Get the state of the light
	 * 
	 */
	Future<String> getState();
	
	/* actions */

	/**
	 * 
	 * Turn on the light.
	 * 
	 * @return
	 */
	Future<Void> on();

	/**
	 * 
	 * Turn off the light.
	 * 
	 * @return
	 */
	Future<Void> off();


	/**
	 * Subscribe to events generated by the light device
	 * 
	 * @param handler
	 * @return
	 */
	Future<Void> subscribe(Handler<JsonObject> handler);
}
