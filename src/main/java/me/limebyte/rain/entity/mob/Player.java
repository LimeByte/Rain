package me.limebyte.rain.entity.mob;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Screen.TextAlign;
import me.limebyte.rain.graphics.Sprite;
import me.limebyte.rain.input.KeyboardListener;
import me.limebyte.rain.level.Level;
import me.limebyte.rain.level.Location;
import me.limebyte.rain.level.tile.Tile;

public class Player extends Mob {

    private KeyboardListener input;
    public String name;
    public Sprite sprite = Sprite.playerLeft;
    private boolean damaged = false;

    public Player(String name, Level level, KeyboardListener input) {
        this(name, new Location(level, 0, 0), input);
    }

    public Player(String name, Location location, KeyboardListener input) {
        this.name = name;
        this.input = input;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    @Override
    public void render(Screen screen) {
        switch (dir) {
            case NONE:
                sprite = Sprite.player;
                break;
            case UP:
                sprite = Sprite.playerUp;
                break;
            case RIGHT:
                sprite = Sprite.playerRight;
                break;
            case DOWN:
                sprite = Sprite.playerDown;
                break;
            case LEFT:
                sprite = Sprite.playerLeft;
                break;
        }

        if (damaged) sprite = sprite.tinted(0xffff2222);

        int halfSprite = sprite.size / 2;
        int yp = location.getY() - halfSprite;
        screen.renderText(location.getX(), yp - sprite.size / 3, name, TextAlign.CENTER);
        screen.render(location.getX() - halfSprite, yp, sprite);
    }

    @Override
    public void tick() {
        int xa = 0, ya = 0;
        if (input.up) {
            ya--;
        }
        if (input.down) {
            ya++;
        }
        if (input.left) {
            xa--;
        }
        if (input.right) {
            xa++;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }

        damaged = location.getTile() == Tile.lava;
    }

}
