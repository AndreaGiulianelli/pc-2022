package smart_room.centralized.superloop;

public interface FsmSpState {
    void sense();
    void decide();
    FsmSpState act();
}
