package de.iotacb.cloud.core.window;

import java.util.ArrayList;

import de.iotacb.cloud.core.entity.Entity;

public abstract class World {
	
	public Window window;
	
	public int windowWidth, windowHeight;
	
	public ArrayList<Entity> entities;
	
	public World(Window window) {
		this.window = window;
		this.windowWidth = window.windowWidth;
		this.windowHeight = window.windowHeight;
		
		this.entities = new ArrayList<Entity>();
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	public void updateEntities() {
		this.entities.forEach(Entity::update);
	}
	
	public void drawEntities() {
		this.entities.forEach(Entity::draw);
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void delEntity(Class <? extends Entity> clazz) {
		this.entities.removeIf(entity -> entity.getClass().equals(clazz));
	}
	
	public void delEntity(long id) {
		this.entities.removeIf(entity -> entity.entityId == id);
	}
	
	public Entity getEntityById(long entityId) {
		Entity entity = null;
		for (Entity e : this.entities) {
			if (e.entityId == entityId) {
				entity = e;
			}
		}
		return entity;
	}

}
