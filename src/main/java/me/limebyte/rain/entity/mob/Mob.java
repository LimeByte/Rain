package me.limebyte.rain.entity.mob;

import me.limebyte.rain.entity.Entity;
import me.limebyte.rain.graphics.Sprite;
import me.limebyte.rain.level.Direction;
import me.limebyte.rain.level.Level;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected Direction dir = Direction.NONE;
    protected boolean moving = false;

    public void move(int xa, int ya) {
        dir = Direction.getByMovement(xa, ya);

        if (!collision(xa, ya)) {
            location.add(xa, ya);
        }
    }

    private boolean collision(int xa, int ya) {
        Level level = location.getLevel();
        int x = location.getTileX();
        int y = location.getTileY();

        return level.getTile(x + xa, y + ya).isSolid();
    }

}
