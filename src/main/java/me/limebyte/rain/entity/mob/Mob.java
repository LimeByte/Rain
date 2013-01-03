package me.limebyte.rain.entity.mob;

import me.limebyte.rain.entity.Entity;
import me.limebyte.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected byte dir = 0;
    protected boolean moving = false;

    public void move(int xa, int ya) {
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        if (!collision()) {
            x += xa;
            y += ya;
        }
    }

    private boolean collision() {
        return false;
    }

}
