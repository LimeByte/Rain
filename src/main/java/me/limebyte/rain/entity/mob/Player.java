package me.limebyte.rain.entity.mob;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;
import me.limebyte.rain.input.KeyboardListener;

public class Player extends Mob {

    private KeyboardListener input;
    private String name;
    private Sprite sprite = Sprite.playerLeft;

    public Player(String name, KeyboardListener input) {
        this.name = name;
        this.input = input;
    }

    public Player(String name, KeyboardListener input, int x, int y) {
        this(name, input);
        this.x = x;
        this.y = y;
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

        screen.renderPlayer(x - sprite.SIZE / 2, y - sprite.SIZE / 2, sprite);
    }

    @Override
    public void update() {
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
    }

}
