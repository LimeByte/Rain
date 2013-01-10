package me.limebyte.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    private final int SIZE;
    private int[] pixels;

    public static SpriteSheet terrain = new SpriteSheet("/textures/terrain.png", 256);
    public static SpriteSheet sprites = new SpriteSheet("/textures/sprites.png", 256);
    public static SpriteSheet foster = new SpriteSheet("/textures/mob/foster.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int[] getPixels() {
        return pixels;
    }

}
