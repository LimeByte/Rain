package me.limebyte.rain.graphics;


public class Screen {

    public int width, height;
    public int[] pixels;

    private int xOffset, yOffset;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void render(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < sprite.size; y++) {
            int ya = yp + y;
            for (int x = 0; x < sprite.size; x++) {
                int xa = xp + x;
                if (xa < -sprite.size || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int colour = sprite.pixels[x + y * sprite.size];
                if (colour != 0x00) {
                    pixels[xa + ya * width] = colour;
                }
            }
        }
    }

    public void renderText(int xp, int yp, String text, TextAlign align) {
        Font font = Font.font;
        text = text.toUpperCase();

        int width = text.length() * (font.size + font.letterSpacing);

        for (int i = 0; i < text.length(); i++) {
            Sprite sprite = font.getSprite(text.charAt(i));
            render(xp + i * font.size + i * font.letterSpacing + align.offset(width), yp, sprite);
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public enum TextAlign {
        LEFT, RIGHT, CENTER;

        public int offset(int width) {
            if (this == LEFT) return 0;
            if (this == CENTER) return -(width / 2);
            if (this == RIGHT) return -width;
            return 0;
        }
    }

}