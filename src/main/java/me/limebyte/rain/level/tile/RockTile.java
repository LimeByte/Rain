package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public class RockTile extends Tile {

    public RockTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
