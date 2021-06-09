package de.iotacb.cloud.utilities.render.font;

import java.nio.ByteBuffer;

public class TextureData {

    private final int textureId;
    private final int width, height;
    private final ByteBuffer buffer;

    public TextureData(final int textureId, final int width, final int height, final ByteBuffer buffer) {
        this.textureId = textureId;
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public final int getTextureId() {
        return textureId;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final ByteBuffer getBuffer() {
        return buffer;
    }
}