package de.iotacb.cloud.core.world;

import java.util.ArrayList;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;

public abstract class World {
	
	public Window window;
	
	ArrayList<Entity> entities;
	
	Camera camera;
	
	public World(Window window) {
		this.window = window;
		
		this.entities = new ArrayList<Entity>();
		
		this.camera = new Camera(window, 0, 0);
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
	
	public void addEntities(Entity...entities) {
		for (Entity ent : entities) {
			addEntity(ent);
		}
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
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public Camera getCamera() {
		return camera;
	}

}
