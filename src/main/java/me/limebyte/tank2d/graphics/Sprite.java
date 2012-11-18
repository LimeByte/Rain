package me.limebyte.tank2d.graphics;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.terrain);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.x = x * size;
        this.y = y * size;
        this.pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return SIZE;
    }
}
