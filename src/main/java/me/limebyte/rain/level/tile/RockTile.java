package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Sprite;

public class RockTile extends Tile {

    public RockTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
