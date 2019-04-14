package de.iotacb.cloud.core.display;

import de.iotacb.cloud.entity.Entity;
import de.iotacb.cloud.utilities.render.Image;

import java.util.ArrayList;

public abstract class Screen {

    public Display display;

    public double displayWidth, displayHeight;

    public ArrayList<Entity> entities;
    public ArrayList<Image> images;

    public Screen(final Display display) {
        this.display = display;
        displayWidth = display.displayWidth;
        displayHeight = display.displayHeight;
        this.entities = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    /**
     * This method will be called when a new instance of the class is created
     */
    public abstract void initialize();

    /**
     * This method will handle all stuff for updating the entity you wrote
     */
    public abstract void update();

    /**
     * This method will handle all stuff for updating the entity you wrote
     */
    public abstract void draw();

    /**
     * This method will call the update method of each entity
     */
    public void updateAllEntities() {
        this.entities.forEach(Entity::update);
    }

    /**
     * This method will call the render method of each entity
     */
    public void drawAllEntities() {
        this.entities.forEach(Entity::draw);
    }

    public void reloadAllImages() {
        this.images.forEach(Image::reload);
    }

    /**
     * Get a entity from the entity list by it's ID
     * @param entityID The ID of the entity
     * @return The entity that matches the ID
     */
    public Entity getEntityByID(final long entityID) {
        Entity ent = null;
        for (Entity entity : this.entities) {
            if (entity.id == entityID) {
                ent = entity;
            }
        }
        return ent;
    }

}
