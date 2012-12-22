package me.limebyte.rain.entity.mob;

import me.limebyte.rain.entity.Entity;
import me.limebyte.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected byte dir = 0;
    protected boolean moving = false;

    public abstract void move();

    private boolean collision() {
        return false;
    }

}
