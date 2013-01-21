package me.limebyte.rain.graphics;

import java.awt.Color;

public class Sprite {

    public final int SIZE;
    protected int x, y;
    public int[] pixels;
    protected SpriteSheet sheet;

    /** Terrain **/
    public static Sprite voidSprite = new Sprite(16, new Color(0x1B87E0));
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.terrain);
    public static Sprite rock = new Sprite(16, 1, 0, SpriteSheet.terrain);

    /** Entity **/
    public static Sprite player = new Sprite(32, 0, 0, SpriteSheet.foster);
    public static Sprite playerUp = new Sprite(32, 0, 1, SpriteSheet.foster);
    public static Sprite playerRight = new Sprite(32, 0, 2, SpriteSheet.foster);
    public static Sprite playerDown = new Sprite(32, 0, 3, SpriteSheet.foster);
    public static Sprite playerLeft = new Sprite(32, 0, 4, SpriteSheet.foster);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.x = x * size;
        this.y = y * size;
        this.pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, Color colour) {
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        loadColour(colour);
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    private void loadColour(Color colour) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = colour.getRGB();
            }
        }
    }
}
