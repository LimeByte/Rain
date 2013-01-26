package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Sprite;

public class RockTile extends Tile {

    public RockTile(int id, Sprite sprite, int colour) {
        super(id, sprite, colour);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean isLiquid() {
        return false;
    }

}
