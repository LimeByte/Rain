package me.limebyte.rain.level;

public enum Direction {
    NONE, UP, RIGHT, DOWN, LEFT;

    public static Direction getByMovement(int xa, int ya) {
        if (xa < 0) return LEFT;
        if (xa > 0) return RIGHT;
        if (ya < 0) return UP;
        if (ya > 0) return DOWN;
        return NONE;
    }
}
