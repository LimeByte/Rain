package me.limebyte.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    private int size;
    private int[] pixels;

    public static SpriteSheet terrain = new SpriteSheet("/textures/terrain.png");
    public static SpriteSheet sprites = new SpriteSheet("/textures/sprites.png");
    public static SpriteSheet foster = new SpriteSheet("/textures/mob/foster.png");

    public SpriteSheet(String path) {
        this.path = path;
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            size = Math.max(w, h);
            pixels = new int[size * size];
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }

    public int[] getPixels() {
        return pixels;
    }

}
