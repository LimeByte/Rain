package me.limebyte.rain.entity;

import java.util.Random;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.level.Level;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public abstract void tick();

    public abstract void render(Screen screen);

    public void remove() {
        // Remove from Level
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
