package me.limebyte.rain.entity.mob;

import me.limebyte.rain.entity.Entity;
import me.limebyte.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected Direction dir = Direction.NONE;
    protected boolean moving = false;

    public void move(int xa, int ya) {
        dir = Direction.getByMovement(xa, ya);

        if (!collision()) {
            x += xa;
            y += ya;
        }
    }

    private boolean collision() {
        return false;
    }

    protected enum Direction {
        NONE, UP, RIGHT, DOWN, LEFT;

        protected static Direction getByMovement(int xa, int ya) {
            if (ya < 0) return UP;
            if (xa > 0) return RIGHT;
            if (ya > 0) return DOWN;
            if (xa < 0) return LEFT;
            return NONE;
        }
    }

}
