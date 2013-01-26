package me.limebyte.rain.entity;

import java.util.Random;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.level.Location;

public abstract class Entity {

    public Location location;
    private boolean removed = false;
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

    public Location getLocation() {
        return location;
    }
}
