package xyz.iotacb.cloud.utilities.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import xyz.iotacb.cloud.utilities.lists.ImageList;
import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorF;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Image
{
    String path;
    int id;
    int width;
    int height;
    ImageList imageList;
    
    public Image(final String path) {
        this.imageList = null;
        this.path = path;
    }
    
    public Image(final ImageList imageList) {
        this.imageList = null;
        this.path = "";
        this.imageList = imageList;
    }
    
    public void draw(int x, int y, final int w, final int h) {
        Render.color(Color.white);
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        try {
            final BufferedImage bi = ImageIO.read(new File(this.path));
            this.width = bi.getWidth();
            this.height = bi.getHeight();
            final int[] pixels_raw = bi.getRGB(0, 0, this.width, this.height, null, 0, this.width);
            final ByteBuffer pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
            for (int i = 0; i < pixels_raw.length; ++i) {
                final int pixel = pixels_raw[i];
                pixels.put((byte)(pixel >> 16 & 0xFF));
                pixels.put((byte)(pixel >> 8 & 0xFF));
                pixels.put((byte)(pixel & 0xFF));
                pixels.put((byte)(pixel >> 24 & 0xFF));
            }
            pixels.flip();
            GL11.glBindTexture(3553, this.id = GL11.glGenTextures());
            GL11.glTexParameteri(3553, 10241, 9728);
            GL11.glTexParameteri(3553, 10240, 9728);
            GL11.glTexImage2D(3553, 0, 6408, this.width, this.height, 0, 6408, 5121, pixels);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        final int xDiff = Math.abs(this.width - w);
        final int yDiff = Math.abs(this.height - h);
        x += this.width - xDiff;
        y += this.height - yDiff;
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 1.0);
        GL11.glVertex2d(x + -w, y + h);
        GL11.glTexCoord2d(1.0, 1.0);
        GL11.glVertex2d(x + w, y + h);
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d(x + w, y + -h);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(x + -w, y + -h);
        GL11.glEnd();
        GL11.glDisable(3553);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
    }
    
    public void draw(final VectorI axes, final VectorI dimensions) {
        this.draw(axes.x, axes.y, dimensions.x, dimensions.y);
    }
    
    public void draw(final int x, final int y, final int w, final int h, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(x, y, w, h);
        }
    }
    
    public void draw(final VectorI axes, final VectorI dimensions, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(axes, dimensions);
        }
    }
    
    public void draw(final double x, final double y, final double w, final double h) {
        this.draw((int)x, (int)y, (int)w, (int)h);
    }
    
    public void draw(final VectorD axes, final VectorD dimensions) {
        this.draw(axes.x, axes.y, dimensions.x, dimensions.y);
    }
    
    public void draw(final double x, final double y, final double w, final double h, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(x, y, w, h);
        }
    }
    
    public void draw(final VectorD axes, final VectorD dimensions, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(axes, dimensions);
        }
    }
    
    public void draw(final float x, final float y, final float w, final float h) {
        this.draw((int)x, (int)y, (int)w, (int)h);
    }
    
    public void draw(final VectorF axes, final VectorF dimensions) {
        this.draw(axes.x, axes.y, dimensions.x, dimensions.y);
    }
    
    public void draw(final float x, final float y, final float w, final float h, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(x, y, w, h);
        }
    }
    
    public void draw(final VectorF axes, final VectorF dimensions, final int index) {
        if (this.imageList.getCount() > 0) {
            this.imageList.getImageList()[index].draw(axes, dimensions);
        }
    }
    
    public ImageList getImageList() {
        return this.imageList;
    }
}
