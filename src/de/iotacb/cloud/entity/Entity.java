package de.iotacb.cloud.entity;

import de.iotacb.cloud.core.display.Display;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Random;
import de.iotacb.cloud.utilities.math.Vector;
import de.iotacb.cloud.utilities.peripherals.Keys;

public abstract class Entity {

    public double x, y, width, height, joyStickThreshold;
    public int displayWidth, displayHeight;
    public long id;

    public Display display;

    public Entity(final Display display) {
        this.display = display;
        this.display.currentScreen.entities.add(this);
        this.displayWidth = display.displayWidth;
        this.displayHeight = display.displayHeight;
        joyStickThreshold = 0.1;
        createID();
        initialize();
    }

    private void createID() {
        long time = System.currentTimeMillis();
        long halfTime = time / 2;
        long tempID = Random.randomLong(999999999);
        id = halfTime + (tempID / 2);
    }

    /**
     * This method will be called if a instance of this class is created
     */
    public abstract void initialize();

    /**
     * This method will handle all stuff for updating the entity you wrote
     */
    public abstract void update();

    /**
     * This method will handle all stuff for rendering the entity you wrote
     */
    public abstract void draw();

    /**
     * Set the location of the entity
     * @param x The location on the x axe
     * @param y The location on the y axe
     */
    public void setLocation(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the location of the entity
     * @param location Two dimensional vector (x and y axes)
     */
    public void setLocation(final Vector location) {
        this.x = location.x;
        this.y = location.y;
    }

    /**
     * Move the entity into a direction (Use degrees)
     * @param direction The direction in which the entity should move
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveIntoDirection(final double direction, final double speed, final int key) {
        if (display.inputHandler.isKeyDown(key) || key == -1) {
            x += Maths.lengthDirX(speed, direction);
            y += Maths.lengthDirY(speed, direction);
        }
    }

    /**
     * Move the entity out of a direction (Use degrees)
     * @param direction The direction out of the entity should move
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveOutOfDirection(final double direction, final double speed, final int key) {
        if (display.inputHandler.isKeyDown(key) || key == -1) {
            x -= Maths.lengthDirX(speed, direction);
            y -= Maths.lengthDirY(speed, direction);
        }
    }

    /**
     * Move the entity to a point on the x and y axes
     * @param x1 Current location of the entity on the x axe
     * @param y1 Current location of the entity on the y axe
     * @param x2 The location on the x axe where the entity should move to
     * @param y2 The location on the y axe where the entity should move to
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveToPoint(final double x1, final double y1, final double x2, final double y2, final double speed, final int key) {
        double direction = Maths.pointDirection(x1, y1, x2, y2);
        if (Maths.dist(x1, y1, x2, y2) > 5) moveIntoDirection(direction, speed, key);
    }

    /**
     * Move the entity to a point on the x and y axes
     * @param location1 The current location of the entity as a two dimensional vector
     * @param location2 The location where the entity should move to as a two dimensional vector
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveToPoint(final Vector location1, final Vector location2, final double speed, final int key) {
        moveToPoint(location1.x, location1.y, location2.x, location2.y, speed, key);
    }

    /**
     * Move the entity away from a point on the x and y axes
     * @param x1 Current location of the entity on the x axe
     * @param y1 Current location of the entity on the y axe
     * @param x2 The location on the x axe where the entity should get away
     * @param y2 The location on the y axe where the entity should get away
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveAwayFromPoint(final double x1, final double y1, final double x2, final double y2, final double speed, final int key) {
        double direction = Maths.pointDirection(x1, y1, x2, y2);
        if (Maths.dist(x1, y1, x2, y2) > 5) moveOutOfDirection(direction, speed, key);
    }

    /**
     * Move the entity away from a point on the x and y axes
     * @param location1 Current location of the entity as a two dimensional vector
     * @param location2 The location where the entity should get away as a two dimensional vector
     * @param speed The moving speed of the entity
     * @param key The key that should be pressed for moving (Set to -1 for constant movement)
     */
    public void moveAwayFromPoint(final Vector location1, final Vector location2, final double speed, final int key) {
        moveAwayFromPoint(location1.x, location1.y, location2.x, location2.y, speed, key);
    }

    /**
     * Move the entity with custom keys
     * @param keyUp The key that should be pressed for moving up
     * @param keyLeft The key that should be pressed for moving left
     * @param keyDown The key that should be pressed for moving down
     * @param keyRight The key that should be pressed for moving right
     * @param speed The moving speed of the entity
     */
    public void moveWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight, double speed) {
        int xMove = display.inputHandler.isKeyDown(keyLeft) ? -1 : display.inputHandler.isKeyDown(keyRight) ? 1 : 0;
        int yMove = display.inputHandler.isKeyDown(keyUp) ? -1 : display.inputHandler.isKeyDown(keyDown) ? 1 : 0;
        Vector input = Vector.create(xMove, yMove);
        Vector direction = input.normalize();
        Vector velocity = direction.multAll(speed);
        Vector moveAmount = velocity.multAll(display.deltaTime);

        x += moveAmount.x;
        y += moveAmount.y;
    }

    /**
     * Move the entity with the WASD keys
     * @param speed The moving speed of the entity
     */
    public void moveWithWASD(final double speed) {
        moveWithKeys(Keys.W, Keys.A, Keys.S, Keys.D, speed);
    }

    /**
     * Move the entity with arrow keys
     * @param speed The moving speed of the entity
     */
    public void moveWithArrows(final double speed) {
        moveWithKeys(Keys.UP, Keys.LEFT, Keys.DOWN, Keys.RIGHT, speed);
    }

    /**
     * Move the entity with a game pad
     * @param speed The moving speed of the entity
     * @param moveStick Left or right joystick (0 = Left, 1 = Right)
     */
    public void moveWithGamepad(final double speed, final int moveStick) {
        double stickX = (moveStick == 0 ? display.inputHandler.getLeftJoystickX() : display.inputHandler.getRightJoystickX());
        double stickY = (moveStick == 0 ? display.inputHandler.getLeftJoystickY() : display.inputHandler.getRightJoystickY());

        if (Math.abs(stickX) > joyStickThreshold) {
            if (stickX > 0) {
                x += (speed * Math.abs(stickX)) * display.deltaTime;
            }
            if (stickX < 0) {
                x -= (speed * Math.abs(stickX)) * display.deltaTime;
            }
        }
        if (Math.abs(stickY) > joyStickThreshold) {
            if (stickY > 0) {
                y += (speed * Math.abs(stickY)) * display.deltaTime;
            }
            if (stickY < 0) {
                y -= (speed * Math.abs(stickY)) * display.deltaTime;
            }
        }
    }

    /**
     * Let the jump a specific distance into the wanted direction
     * @param distance The distance to jump
     * @param direction The direction
     */
    public void jumpDirection(final double distance, final double direction) {
        x += Maths.lengthDirX(distance, direction);
        y += Maths.lengthDirY(distance, direction);
    }
}
