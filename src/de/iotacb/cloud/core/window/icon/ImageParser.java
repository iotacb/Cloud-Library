package de.iotacb.cloud.core.window.icon;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class ImageParser {
	
    public ByteBuffer image;
    public int width, height;

    public ImageParser(final int width, final int height, final ByteBuffer image) {
        this.image = image;
        this.height = height;
        this.width = width;
    }
    
    public static final ImageParser loadImage(final String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer comp = stack.mallocInt(1);
            final IntBuffer w = stack.mallocInt(1);
            final IntBuffer h = stack.mallocInt(1);

            image = STBImage.stbi_load(path, w, h, comp, 4);
            width = w.get();
            height = h.get();
        }
        return new ImageParser(width, height, image);
    }

}
