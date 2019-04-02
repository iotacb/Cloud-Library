package xyz.iotacb.cloud.core.display;

import xyz.iotacb.cloud.core.gui.Button;
import xyz.iotacb.cloud.entity.Entity;
import xyz.iotacb.cloud.utilities.lists.CloudList;
import xyz.iotacb.cloud.utilities.math.Vector;

public abstract class Screen {
	
	/**
	 * Dimensions of the screen
	 */
	public Vector screenDimensions;
	
	/**
	 * Display variable which you created in your main method of your application
	 */
	public Display display;
	
	/**
	 * Add your buttons to this list
	 */
	public CloudList<Button> buttonList;
	
	/**
	 * Add your entities to this list
	 */
	public CloudList<Entity> entities;
	
	/**
	 * Create the screen
	 * @param display
	 */
	public Screen(final Display display) {
		this.display = display;
		this.screenDimensions = display.displayDimensions;
		this.buttonList = new CloudList<Button>();
		this.entities = new CloudList<Entity>();
	}
	
	/**
	 * This method will be called when the screen is set
	 */
	public abstract void init();
	
	/**
	 * This method will be called in the update loop of the display
	 */
	public abstract void update();
	
	/**
	 * This method will be called in the rendering loop of the display
	 */
	public abstract void draw();
	
	/**
	 * This methods adds a entity to the entity list
	 * @param entity
	 */
	public void addEntity(final Entity entity) {
		this.entities.add(entity);
	}
	
	/**
	 * This method add a list of entities to the entity list
	 * @param entities
	 */
	public void addEntities(final CloudList<Entity> entities) {
		this.entities.addAll(entities);
	}
	
	/**
	 * Draw all button within the button list
	 */
	public void drawButtons() {
		this.buttonList.forEach(b -> {
			b.draw();
			if (b.isHovered()) {
				if (display.inputHandler.isMouseButtonDown(0)) {
					if (!b.isClicked()) {
						buttonActions(b);
						b.setClicked(true);
					}
				} else {
					b.setClicked(false);
				}
			}
		});
	}
	
	/**
	 * Add the actions of your buttons inside this method
	 * @param button
	 */
	public void buttonActions(final Button button) {}
	
}
