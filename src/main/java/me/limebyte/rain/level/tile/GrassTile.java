package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
