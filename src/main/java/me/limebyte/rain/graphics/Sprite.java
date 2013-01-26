package me.limebyte.rain.graphics;

import java.awt.Color;

public class Sprite implements Cloneable {

    public final int size;
    protected int x, y;
    public int[] pixels;
    protected SpriteSheet sheet;

    /** Terrain **/
    public static Sprite voidSprite = new Sprite(16, new Color(0x1B87E0));
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.terrain);
    public static Sprite rock = new Sprite(16, 1, 0, SpriteSheet.terrain);
    public static Sprite water = new AnimatedSprite(16, 11, 39, SpriteSheet.terrain, 0.2);
    public static Sprite lava = new AnimatedSprite(16, 14, 20, SpriteSheet.terrain, 0.2);

    /** Entity **/
    public static Sprite player = new Sprite(32, 0, 0, SpriteSheet.foster);
    public static Sprite playerUp = new Sprite(32, 0, 1, SpriteSheet.foster);
    public static Sprite playerRight = new Sprite(32, 0, 2, SpriteSheet.foster);
    public static Sprite playerDown = new Sprite(32, 0, 3, SpriteSheet.foster);
    public static Sprite playerLeft = new Sprite(32, 0, 4, SpriteSheet.foster);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.size = size;
        this.x = x * size;
        this.y = y * size;
        this.pixels = new int[size * size];
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, Color colour) {
        this.size = size;
        this.pixels = new int[size * size];
        loadColour(colour);
    }

    private Sprite(int size, int[] pixels) {
        this.size = size;
        this.pixels = pixels;
    }

    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    private void loadColour(Color colour) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = colour.getRGB();
            }
        }
    }

    public Sprite tinted(int colour) {
        int[] tinted = new int[size * size];

        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] != 0x00) {
                tinted[i] = pixels[i] & colour;
            } else {
                tinted[i] = pixels[i];
            }
        }

        return new Sprite(size, tinted);
    }
}
