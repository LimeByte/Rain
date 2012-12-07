package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x, y, this);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
