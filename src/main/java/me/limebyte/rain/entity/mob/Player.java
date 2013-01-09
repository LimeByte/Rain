package me.limebyte.rain.entity.mob;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;
import me.limebyte.rain.input.KeyboardListener;

public class Player extends Mob {

    private KeyboardListener input;
    private String name;
    private static final Sprite sprite = Sprite.player;

    public Player(String name, KeyboardListener input) {
        this.name = name;
        this.input = input;
    }

    public Player(String name, KeyboardListener input, int x, int y) {
        this(name, input);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) move(xa, ya);
    }

    @Override
    public void render(Screen screen) {
        screen.renderPlayer(x - sprite.SIZE / 2, y - sprite.SIZE / 2, sprite);
    }

    public String getName() {
        return name;
    }

}
