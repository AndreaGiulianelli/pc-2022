package smart_room.centralized;

import smart_room.Event;
import smart_room.centralized.eventdriven.ControllerImpl;

public class TestEventDrivenController {

	public static void main(String[] args) {
		final SinglelBoardSimulator board = new SinglelBoardSimulator();
		board.init();
		final ControllerImpl controller = new ControllerImpl(board);
	
		board.register((Event ev) -> {
			System.out.println("New event: " + ev);
			controller.notifyEvent(ev);
		});

		controller.start();
	}

}
