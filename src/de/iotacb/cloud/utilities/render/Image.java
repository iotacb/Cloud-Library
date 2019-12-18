package de.iotacb.cloud.utilities.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.time.Timer;

public class Image {

	public File imageFile;

	public Image[] images;

	int imageWidth, imageHeight, imageId, imageIndex;

	ByteBuffer pixelBuffer;

	Window window;

	Timer animationTimer;

	public boolean finishedLoading = false;

	public Image(Window window, String imagePath) {
		this.window = window;
		this.imageFile = new File(imagePath);
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image(Window window, File file) {
		this.window = window;
		this.imageFile = file;
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image(Window window, Image[] images) {
		this.window = window;
		this.images = images;
		this.imageIndex = 0;
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws IOException {
		this.animationTimer = new Timer();

		this.imageId = glGenTextures();

		if (this.imageFile == null && this.images.length > 0) {
			this.imageFile = this.images[0].imageFile;
		}

		BufferedImage image = ImageIO.read(this.imageFile);

		this.imageWidth = image.getWidth();
		this.imageHeight = image.getHeight();

		int[] imagePixels = image.getRGB(0, 0, imageWidth, imageHeight, null, 0, imageWidth);
		this.pixelBuffer = BufferUtils.createByteBuffer(imageWidth * imageHeight * 4); // each pixel (rgba) = width * height * 4 (r,g,b,a)

		for (int i = 0; i < imagePixels.length; i++) {
			int pixel = imagePixels[i];
			this.pixelBuffer.put((byte) (pixel >> 16 & 0xFF)); // red
			this.pixelBuffer.put((byte) (pixel >> 8 & 0xFF)); // green
			this.pixelBuffer.put((byte) (pixel & 0xFF)); // blue
			this.pixelBuffer.put((byte) (pixel >> 24 & 0xFF)); // alpha
		}

		this.pixelBuffer.flip();

		this.finishedLoading = true;
	}

	public void drawImage(double x, double y, double width, double height) {
		if (this.pixelBuffer == null) {
			try {
				throw new Exception("Pixel buffer of image is empty. Error: #004");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		width /= 2;
		height /= 2;

		Render.push();

		Render.color(Color.white);

		Render.enable(GL_TEXTURE_2D);
		Render.enable(GL_BLEND);
		Render.disable(GL_LIGHTING);
		glBindTexture(GL_TEXTURE_2D, this.imageId);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, this.imageWidth, this.imageHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE,
				this.pixelBuffer);

		double diffW = Math.abs(this.imageWidth - width);
		double diffH = Math.abs(this.imageHeight - height);
		x += (this.imageWidth - diffW);
		y += (this.imageHeight - diffH);

		Render.begin(GL_QUADS);
		{
			glTexCoord2d(0, 1);
			Render.vertex(x - width, y + height);
			glTexCoord2d(1, 1);
			Render.vertex(x + width, y + height);
			glTexCoord2d(1, 0);
			Render.vertex(x + width, y - height);
			glTexCoord2d(0, 0);
			Render.vertex(x - width, y - height);
		}
		Render.end();
		
		GL30.glGenerateMipmap(imageId);
		
		Render.enable(GL_LIGHTING);
		Render.disable(GL_BLEND);
		Render.disable(GL_TEXTURE_2D);

		Render.pop();
	}
	
	public void drawImage(Vec location, Vec size) {
		drawImage(location.x, location.y, size.x, size.y);
	}

	public void drawImage(Vec location, double width, double height) {
		drawImage(location.x, location.y, width, height);
	}

	public void drawImage(double x, double y, Vec size) {
		drawImage(x, y, size.x, size.y);
	}

	public void drawImage(double x, double y) {
		drawImage(x, y, this.imageWidth, this.imageHeight);
	}

	public void drawImage(Vec location) {
		drawImage(location.x, location.y, this.imageWidth, this.imageHeight);
	}

	public void drawImageIndex(double x, double y, double width, double height, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImage(x, y, width, height);
	}

	public void drawImageIndex(Vec location, Vec size, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImage(location, size);
	}

	public void drawImageIndex(Vec location, double width, double height, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImage(location, width, height);
	}

	public void drawImageIndex(double x, double y, Vec size, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImage(x, y, size);
	}

	public void drawImageIndex(double x, double y, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		drawImageIndex(x, y, this.images[index].imageWidth, this.images[index].imageHeight, index);
	}

	public void drawImageIndex(Vec location, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		drawImageIndex(location.x, location.y, index);
	}

	public void drawImageAnimated(double x, double y, double width, double height, int frameDelay) {
		if (this.images.length <= 0 || this.images == null) {
			return;
		}

		if (this.animationTimer.havePassed(frameDelay)) {
			if (this.imageIndex < this.images.length - 1) {
				this.imageIndex++;
			} else {
				this.imageIndex = 0;
			}
		}
		drawImageIndex(x, y, width, height, this.imageIndex);
	}

	public void drawImageAnimated(Vec location, Vec size, int frameDelay) {
		drawImageAnimated(location.x, location.y, size.x, size.y, frameDelay);
	}

	public void drawImageAnimated(Vec location, double width, double height, int frameDelay) {
		drawImageAnimated(location.x, location.y, width, height, frameDelay);
	}

	public void drawImageAnimated(double x, double y, Vec size, int frameDelay) {
		drawImageAnimated(x, y, size.x, size.y, frameDelay);
	}

	public void drawImageAnimated(double x, double y, int frameDelay) {
		drawImageAnimated(x, y, this.images[this.imageIndex].imageWidth, this.images[this.imageIndex].imageHeight, frameDelay);
	}
	
	public void drawImageCentered(double x, double y, double width, double height) {
		drawImage(x - width / 2, y - height / 2, width, height);
	}
	
	public void drawImageCentered(Vec location, Vec size) {
		drawImage(location.x - size.x / 2, location.y - size.y / 2, size.x, size.y);
	}

	public void drawImageCentered(Vec location, double width, double height) {
		drawImage(location.x - width / 2, location.y - height / 2, width, height);
	}

	public void drawImageCentered(double x, double y, Vec size) {
		drawImage(x - size.x / 2, y - size.y / 2, size.x, size.y);
	}

	public void drawImageCentered(double x, double y) {
		drawImage(x - imageWidth / 2, y - imageHeight / 2, this.imageWidth, this.imageHeight);
	}

	public void drawImageCentered(Vec location) {
		drawImage(location.x - imageWidth / 2, location.y - imageHeight / 2, this.imageWidth, this.imageHeight);
	}

	public void drawImageCenteredIndex(double x, double y, double width, double height, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImageCentered(x, y, width, height);
	}

	public void drawImageCenteredIndex(Vec location, Vec size, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImageCentered(location, size);
	}

	public void drawImageCenteredIndex(Vec location, double width, double height, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImageCentered(location, width, height);
	}

	public void drawImageCenteredIndex(double x, double y, Vec size, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		this.images[index].drawImageCentered(x, y, size);
	}

	public void drawImageCenteredIndex(double x, double y, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		drawImageIndex(x, y, this.images[index].imageWidth, this.images[index].imageHeight, index);
	}

	public void drawImageCenreredIndex(Vec location, int index) {
		if (index < 0 || index > this.images.length - 1 || this.images == null) {
			return;
		}
		drawImageCenteredIndex(location.x, location.y, index);
	}

	public void drawImageCenteredAnimated(double x, double y, double width, double height, int frameDelay) {
		if (this.images.length <= 0 || this.images == null) {
			return;
		}

		if (this.animationTimer.havePassed(frameDelay)) {
			if (this.imageIndex < this.images.length - 1) {
				this.imageIndex++;
			} else {
				this.imageIndex = 0;
			}
		}
		drawImageCenteredIndex(x, y, width, height, this.imageIndex);
	}

	public void drawImageCenteredAnimated(Vec location, Vec size, int frameDelay) {
		drawImageCenteredAnimated(location.x, location.y, size.x, size.y, frameDelay);
	}

	public void drawImageCenteredAnimated(Vec location, double width, double height, int frameDelay) {
		drawImageCenteredAnimated(location.x, location.y, width, height, frameDelay);
	}

	public void drawImageCenteredAnimated(double x, double y, Vec size, int frameDelay) {
		drawImageCenteredAnimated(x, y, size.x, size.y, frameDelay);
	}

	public void drawImageCenteredAnimated(double x, double y, int frameDelay) {
		drawImageCenteredAnimated(x, y, this.images[this.imageIndex].imageWidth, this.images[this.imageIndex].imageHeight, frameDelay);
	}
	
	public void reloadImage() {
		try {
			this.finishedLoading = false;
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}
}
