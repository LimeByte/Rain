package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.AnimatedSprite;
import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public abstract class Tile {

    public Sprite sprite;
    private int colour;

    public static final Tile[] TILES = new Tile[4096];
    public static Tile voidTile = new VoidTile(0, Sprite.voidSprite, 0x000000);
    public static Tile grass = new GrassTile(1, Sprite.grass, 0xff004000);
    public static Tile rock = new RockTile(2, Sprite.rock, 0xff404040);
    public static Tile water = new WaterTile(3, Sprite.water, 0xff000040);
    public static Tile lava = new LavaTile(4, Sprite.lava, 0xffff6a00);

    public Tile(int id, Sprite sprite, int colour) {
        this.sprite = sprite;
        this.colour = colour;

        if (TILES[id] != null) throw new IllegalArgumentException("Tile ID " + id + " is already occupied by " + TILES[id] + " when adding " + this);
        TILES[id] = this;
    }

    public void render(int x, int y, Screen screen) {
        screen.render(x << 4, y << 4, sprite);
    }

    public void tick() {
        if (sprite instanceof AnimatedSprite) {
            ((AnimatedSprite) sprite).tick();
        }
    }

    public abstract boolean isSolid();

    public abstract boolean isLiquid();

    public int getColour() {
        return colour;
    }

    public static Tile getByColour(int colour) {
        for (Tile tile : TILES) {
            if (tile.getColour() == colour) return tile;
        }
        return voidTile;
    }

}
