package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
