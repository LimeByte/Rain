package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(int id, Sprite sprite, int colour) {
        super(id, sprite, colour);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isLiquid() {
        return false;
    }

}
