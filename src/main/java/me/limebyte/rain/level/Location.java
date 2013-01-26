package me.limebyte.rain.level;

import java.util.Vector;

import me.limebyte.rain.level.tile.Tile;

/**
 * Represents a 2-dimensional position in a level
 */
public class Location {

    private Level level;
    private int x, y;
    private Direction direction;

    /**
     * Constructs a new Location with the given coordinates
     * 
     * @param level The level in which this location resides
     * @param x The x-coordinate of this new location
     * @param y The y-coordinate of this new location
     */
    public Location(Level level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the level that this location resides in
     * 
     * @param level New level that this location resides in
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets the level that this location resides in
     * 
     * @return Level that contains this location
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Gets the tile at the represented location
     * 
     * @return Tile at the represented location
     */
    public Tile getTile() {
        return Level.getTileAt(this);
    }

    /**
     * Sets the x-coordinate of this location
     * 
     * @param x X-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the x-coordinate of this location
     * 
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the floored value of the X component, indicating the tile that this
     * location is contained with.
     * 
     * @return tile X
     */
    public int getTileX() {
        return locToTile(x);
    }

    /**
     * Sets the y-coordinate of this location
     * 
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the y-coordinate of this location
     * 
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the floored value of the Y component, indicating the tile that this
     * location is contained with.
     * 
     * @return tile y
     */
    public int getTileY() {
        return locToTile(y);
    }

    // TODO
    /**
     * Gets a Vector pointing in the direction that this Location is facing
     * 
     * @return Vector
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Adds the location by another.
     * 
     * @see Vector
     * @param loc The other location
     * @return the same location
     * @throws IllegalArgumentException for differing levels
     */
    public Location add(Location loc) {
        if (loc == null || loc.getLevel() != getLevel()) {
            throw new IllegalArgumentException("Cannot add Locations of differing levels");
        }

        x += loc.x;
        y += loc.y;
        return this;
    }

    /**
     * Adds the location by another. Not level-aware.
     * 
     * @see Vector
     * @param x X coordinate
     * @param y Y coordinate
     * @return the same location
     */
    public Location add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Subtracts the location by another.
     * 
     * @see Vector
     * @param loc The other location
     * @return the same location
     * @throws IllegalArgumentException for differing levels
     */
    public Location subtract(Location loc) {
        if (loc == null || loc.getLevel() != getLevel()) {
            throw new IllegalArgumentException("Cannot add Locations of differing levels");
        }

        x -= loc.x;
        y -= loc.y;
        return this;
    }

    /**
     * Subtracts the location by another. Not level-aware and orientation
     * independent.
     * 
     * @see Vector
     * @param x X coordinate
     * @param y Y coordinate
     * @return the same location
     */
    public Location subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Gets the magnitude of the location, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the location's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long. Not
     * level-aware and orientation independent.
     * 
     * @see Vector
     * @return the magnitude
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Gets the magnitude of the location squared. Not level-aware and
     * orientation independent.
     * 
     * @see Vector
     * @return the magnitude
     */
    public double lengthSquared() {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    /**
     * Get the distance between this location and another. The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the location's magnitude. NaN will be
     * returned if the inner result of the sqrt() function overflows, which will
     * be caused if the distance is too long.
     * 
     * @see Vector
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing levels
     */
    public double distance(Location o) {
        return Math.sqrt(distanceSquared(o));
    }

    /**
     * Get the squared distance between this location and another.
     * 
     * @see Vector
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing levels
     */
    public double distanceSquared(Location o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else if (o.getLevel() == null || getLevel() == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null level");
        } else if (o.getLevel() != getLevel()) {
            throw new IllegalArgumentException("Cannot measure distance between " + getLevel().getName() + " and " + o.getLevel().getName());
        }

        return Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2);
    }

    /**
     * Performs scalar multiplication, multiplying all components with a scalar.
     * Not level-aware.
     * 
     * @param m The factor
     * @see Vector
     * @return the same location
     */
    public Location multiply(int m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Zero this location's components. Not level-aware.
     * 
     * @see Vector
     * @return the same location
     */
    public Location zero() {
        x = 0;
        y = 0;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Location other = (Location) obj;

        if (this.level != other.level && (this.level == null || !this.level.equals(other.level))) return false;
        if (this.x != other.x) return false;
        if (this.y != other.y) return false;
        if (!this.direction.equals(other.direction)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "level=" + level + ",x=" + x + ",y=" + y + ",direction=" + direction + '}';
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    /**
     * Safely converts a double (location coordinate) to an int (tile
     * coordinate)
     * 
     * @param loc Precise coordinate
     * @return Tile coordinate
     */
    public static int locToTile(int loc) {
        return loc >> 4;
    }
}
