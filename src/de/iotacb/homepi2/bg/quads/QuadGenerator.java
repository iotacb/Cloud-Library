package de.iotacb.homepi2.bg.quads;

import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.homepi2.bg.Background;

public class QuadGenerator extends Background {
	
	List<Quad> quads;
	
	public QuadGenerator(Window window) {
		this.quads = new ArrayList<Quad>();
		
		int column = 0;
		int row = 0;
		
		for (int r = 0; r < (int)(window.getWindowHeight() / 120) + 1; r++) {
			quads.add(new Quad(window, new Vec(120, 120), new Vec(column, row), row + column + quads.size()));
			for (int c = 0; c < (int)(window.getWindowWidth() / 120) + 1; c++) {
					quads.add(new Quad(window, new Vec(120, 120), new Vec(column + 120, row), column + row + quads.size()));
				column += 120;
			}
			column = 0;
			row += 120;
		}
	}

	@Override
	public void draw() {
		quads.forEach(Quad::draw);
	}

	@Override
	public void update() {
	}

}
