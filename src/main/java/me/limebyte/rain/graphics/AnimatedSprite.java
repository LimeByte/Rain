package me.limebyte.rain.graphics;

public class AnimatedSprite extends Sprite {

    private double delay;
    private Sprite[] sprites;
    private long time = 0;
    private long prevTime = 0;
    private int index = 0;

    public AnimatedSprite(int size, int y, int frames, SpriteSheet sheet, double delay) {
        super(size, 0, y, sheet);

        sprites = new Sprite[frames];
        int width = sheet.getSize() / size;
        for (int i = 0; i < frames; i++) {
            sprites[i] = new Sprite(size, i % width, i / width + y, sheet);
        }

        this.delay = delay;
    }

    public void tick() {
        time = System.currentTimeMillis();

        if (time - prevTime >= 1000 * delay) {
            if (index < sprites.length - 1) {
                index++;
            } else {
                index = 0;
            }

            pixels = sprites[index].pixels;
            prevTime = time;
        }
    }

}
