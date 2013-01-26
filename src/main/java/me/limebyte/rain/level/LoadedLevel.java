package me.limebyte.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadedLevel extends Level {

    private String path;
    private int[] levelPixels;

    public LoadedLevel(String name, String path) {
        super(name);
        this.path = path;
        loadLevel();
        generateLevel();
    }

    private void loadLevel() {
        try {
            BufferedImage image = ImageIO.read(LoadedLevel.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            levelPixels = new int[width * height];
            image.getRGB(0, 0, width, height, levelPixels, 0, width);
            tiles = new int[width * height];
        } catch (IOException e) {
            System.err.println("Failed to load level.");
        }
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = levelPixels[x + y * width];
            }
        }
    }

}
