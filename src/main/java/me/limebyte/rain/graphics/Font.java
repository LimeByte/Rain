package me.limebyte.rain.graphics;

public class Font {

    private SpriteSheet sheet;
    public int letterSpacing;
    public int size;
    private static final String chars = "ABCDEFGH" + "IJKLMNOP" + "QRSTUVWX" + "YZ012345" + "6789.,:!" + "?/\\-_'+=" + "        " + "        ";

    public static Font font = new Font(SpriteSheet.font, -2);

    public Font(SpriteSheet sheet, int letterSpacing) {
        this.sheet = sheet;
        this.letterSpacing = letterSpacing;
        this.size = sheet.getSize() / 8;
    }

    public Sprite getSprite(char character) {
        int index = chars.indexOf(character);
        if (index < 0) return getSprite('X');

        return new Sprite(8, index % 8, index / 8, sheet);
    }
}
