package de.iotacb.cloud.core.scene;

import java.util.ArrayList;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;

public abstract class Scene {
	
	public final Window window;
	
	private final ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Scene(final Window window) {
		this.window = window;
	}
	
	public abstract void initialize();
	public void update() {
		updateEntities();
	}
	public void draw() {
		drawEntities();
	}
	
	public final void updateEntities() {
		getEntities().forEach(Entity::update);
	}
	
	public final void drawEntities() {
		getEntities().forEach(Entity::draw);
	}
	
	public final void addEntity(final Entity entity) {
		getEntities().add(entity);
	}
	
	public final void addEntities(final Entity...entities) {
		for (final Entity ent : entities) {
			addEntity(ent);
		}
	}
	
	public final void delEntity(final Class <? extends Entity> clazz) {
		getEntities().removeIf(entity -> entity.getClass().equals(clazz));
	}
	
	public final void delEntity(final long id) {
		getEntities().removeIf(entity -> entity.entityId == id);
	}
	
	public final Entity getEntityById(final long entityId) {
		Entity entity = null;
		for (final Entity e : entities) {
			if (e.entityId == entityId) {
				entity = e;
			}
		}
		return entity;
	}
	
	public final ArrayList<Entity> getEntities() {
		return entities;
	}

}
