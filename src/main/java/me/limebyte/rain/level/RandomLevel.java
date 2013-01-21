package me.limebyte.rain.level;

import java.util.Random;

import me.limebyte.rain.level.tile.Tile;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = calculateTile();
            }
        }
    }

    private Tile calculateTile() {
        double rand = random.nextDouble();
        if (rand <= 0.9) return Tile.grass; // 90%
        if (rand > 0.9) return Tile.rock; // 10%
        return Tile.voidTile;
    }
}
