package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public class LavaTile extends Tile {

    public LavaTile(int id, Sprite sprite, int colour) {
        super(id, sprite, colour);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.render(x << 4, y << 4, sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isLiquid() {
        return true;
    }

}
