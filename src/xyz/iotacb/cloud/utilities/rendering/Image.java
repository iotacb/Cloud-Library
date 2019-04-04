package xyz.iotacb.cloud.utilities.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import xyz.iotacb.cloud.core.exceptions.CloudInitializeException;
import xyz.iotacb.cloud.utilities.lists.ImageList;
import xyz.iotacb.cloud.utilities.math.Vector;
import xyz.iotacb.cloud.utilities.time.Timer;

import static org.lwjgl.opengl.GL11.*;

public class Image
{
    String path;
    int id;
    int width;
    int height;
    int index;
    ImageList imageList;
    
    ByteBuffer pixels;
    
    boolean animate;
    
    Timer animationDelay;
    
    /**
     * Load a image from the file path
     * @param path
     */
    public Image(final String path) {
        this.imageList = null;
        this.path = path;
        init();
    }
    
    /**
     * Load multiple images from the image list and decide if the images should be shown as a gif
     * @param imageList
     * @param animate
     */
    public Image(final ImageList imageList, final boolean animate) {
        this.imageList = null;
        this.path = "";
        this.imageList = imageList;
        this.animate = animate;
        this.animationDelay = new Timer();
        init();
    }
    
    /**
     * Load the bytes of the image into the byte buffer
     */
    void init() {
        try {
        	this.id = GL11.glGenTextures();
            final BufferedImage bi = ImageIO.read(new File(this.path));
            this.width = bi.getWidth();
            this.height = bi.getHeight();
            final int[] pixels_raw = bi.getRGB(0, 0, this.width, this.height, null, 0, this.width);
            pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
            for (int i = 0; i < pixels_raw.length; ++i) {
                final int pixel = pixels_raw[i];
                pixels.put((byte)(pixel >> 16 & 0xFF));
                pixels.put((byte)(pixel >> 8 & 0xFF));
                pixels.put((byte)(pixel & 0xFF));
                pixels.put((byte)(pixel >> 24 & 0xFF));
            }
            pixels.flip();
        }
        catch (IOException e) {
        }
    }
    
    /**
     * Draw the image to a location with dimensions
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void draw(double x, double y, double w, double h) {
    	if (!Render.canBeRendered(Render.display, x, y, w, h)) return;
    	if (pixels == null)
			try {
				throw new CloudInitializeException("ByteBuffer not initialized!");
			} catch (CloudInitializeException e) {
				e.printStackTrace();
			}
    	w /= 2;
    	h /= 2;
        Render.color(Color.white);
        GL11.glEnable(GL_TEXTURE_2D);
        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL_LIGHTING);
        GL11.glBindTexture(GL_TEXTURE_2D, this.id);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        final double xDiff = Math.abs(this.width - w);
        final double yDiff = Math.abs(this.height - h);
        x += this.width - xDiff;
        y += this.height - yDiff;
        GL11.glBegin(GL_QUADS);
        {
            GL11.glTexCoord2d(0.0, 1.0);
            GL11.glVertex2d(x + -w, y + h);
            GL11.glTexCoord2d(1.0, 1.0);
            GL11.glVertex2d(x + w, y + h);
            GL11.glTexCoord2d(1.0, 0.0);
            GL11.glVertex2d(x + w, y + -h);
            GL11.glTexCoord2d(0.0, 0.0);
            GL11.glVertex2d(x + -w, y + -h);
        }
        GL11.glEnd();
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glEnable(GL_LIGHTING);
        GL11.glDisable(GL_BLEND);
    }
    
    /**
     * Draw a wanted image of the image list to a location with dimensions
     * @param x
     * @param y
     * @param w
     * @param h
     * @param index
     */
    public void draw(final double x, final double y, final double w, final double h, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(x, y, w, h);
        }
    }
    
    /**
     * Draw the image to a location with dimensions
     * @param axes
     * @param dimensions
     */
    public void draw(final Vector axes, final Vector dimensions) {
        draw(axes.x, axes.y, dimensions.x, dimensions.y);
    }
    
    /**
     * Draw a wanted image of the image list to a location with dimensions
     * @param axes
     * @param dimensions
     * @param index
     */
    public void draw(final Vector axes, final Vector dimensions, final int index) {
    	draw(axes.x, axes.y, dimensions.x, dimensions.y, index);
    }
    
    /**
     * Draw a animated image to a location with dimensions and set the delay between the image change
     * @param x
     * @param y
     * @param w
     * @param h
     * @param delay
     */
    public void drawAnimated(final double x, final double y, final double w, final double h, final int delay) {
    	if (!animate) return;
        if (this.imageList.getCount() > 0) {
        	if (animationDelay.isOver(delay)) {
        		if (index < getImageList().getCount() - 1) {
        			index++;
        		} else {
        			index = 0;
        		}
        	}
            this.imageList.getImageList()[index].draw(x, y, w, h);
        }
    }
    
    /**
     * Draw a animated image to a location with dimensions and set the delay between the image change
     * @param axes
     * @param dimensions
     * @param delay
     */
    public void drawAnimated(final Vector axes, final Vector dimensions, final int delay) {
    	drawAnimated(axes.x, axes.y, dimensions.x, dimensions.y, delay);
    }
    
    /**
     * Get the image list
     * @return
     */
    public ImageList getImageList() {
        return this.imageList;
    }
}
