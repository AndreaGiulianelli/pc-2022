package smart_room.centralized;

import smart_room.Event;
import smart_room.centralized.control.ControllerImpl;

public class TestController {

	public static void main(String[] args) throws Exception {
		final SinglelBoardSimulator board = new SinglelBoardSimulator();
		board.init();
		final ControllerImpl controller = new ControllerImpl(board);
	
		board.register((Event ev) -> {
			System.out.println("New event: " + ev);
			controller.notifyEvent(ev);
		});

		controller.start();
		
		while (true) {
			System.out.println("Pres Det: " + board.presenceDetected() + " - Light level: " + board.getLuminosity());
			Thread.sleep(1000);
		}
	}

}
