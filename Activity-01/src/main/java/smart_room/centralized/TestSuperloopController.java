package smart_room.centralized;

import smart_room.centralized.superloop.SuperLoopController;

public class TestSuperloopController {
    public static void main(final String... args) {
        final SinglelBoardSimulator board = new SinglelBoardSimulator();
        board.init();
        final SuperLoopController controller = new SuperLoopController(board);
        controller.start();
    }
}
