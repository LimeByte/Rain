package me.limebyte.rain.entity.mob;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;
import me.limebyte.rain.input.KeyboardListener;

public class Player extends Mob {

    private KeyboardListener input;

    public Player(KeyboardListener input) {
        this.input = input;
    }

    public Player(KeyboardListener input, int x, int y) {
        this(input);
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
        screen.renderPlayer(x, y, Sprite.player0);
    }

}
