package de.iotacb.cloud.utilities.collision;

import java.awt.Color;

import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.render.Render;

public class CBox {
	
	public int x, y, width, height;
	
	public Color intersectingColor;
	
	public CBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.intersectingColor = Colors.setAlpha(Color.red, 100);
	}
	
	public void draw() {
		Render.rect(x, y, width, height, intersectingColor);
	}
	
	public boolean intersecting(CBox box) {
		this.intersectingColor = Collision.rect(x, y, width, height, box.x, box.y, box.width, box.height) ? Colors.setAlpha(Color.green, 100) : Colors.setAlpha(Color.red, 100);
		box.intersectingColor = Collision.rect(x, y, width, height, box.x, box.y, box.width, box.height) ? Colors.setAlpha(Color.green, 100) : Colors.setAlpha(Color.red, 100);
		return Collision.rect(x, y, width, height, box.x, box.y, box.width, box.height);
	}
	
}
