package de.iotacb.cloud.utilities.render.font;

import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FontManager {
	
	public static FontManager instance;

	private TTFFontRenderer smallFont;
	private TTFFontRenderer normalFont;
	private TTFFontRenderer normalBig;
	private TTFFontRenderer bigFont;
	private TTFFontRenderer giantFont;

	public FontManager() {
		final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
		final ConcurrentLinkedQueue<TextureData> textureDataQueue = new ConcurrentLinkedQueue<>();

		try {
			loadFonts(executor, textureDataQueue);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
			while (!textureDataQueue.isEmpty()) {
				final TextureData textureData = textureDataQueue.poll();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureData.getTextureId());

				// Sets the texture parameter stuff.
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

				// Uploads the texture to opengl.
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, textureData.getWidth(), textureData.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureData.getBuffer());
			}
		}
	}

	private void loadFonts(final ThreadPoolExecutor executor, final ConcurrentLinkedQueue<TextureData> textureDatas) throws FontFormatException, IOException {
		final Font small = new Font("Arial", Font.PLAIN, 12);
		final Font normal = new Font("Arial", Font.PLAIN, 18);
		final Font normalBig = new Font("Arial", Font.PLAIN, 24);
		final Font big = new Font("Arial", Font.PLAIN, 48);
		final Font giant = new Font("Arial", Font.PLAIN, 120);

		this.smallFont = new TTFFontRenderer(executor, textureDatas, small);
		this.normalFont = new TTFFontRenderer(executor, textureDatas, normal);
		this.bigFont = new TTFFontRenderer(executor, textureDatas, big);
		this.normalBig = new TTFFontRenderer(executor, textureDatas, normalBig);
		this.giantFont = new TTFFontRenderer(executor, textureDatas, giant);
	}

	public final TTFFontRenderer getSmallFont() {
		return smallFont;
	}

	public final TTFFontRenderer getNormalFont() {
		return normalFont;
	}

	public final TTFFontRenderer getNormalBig() {
		return normalBig;
	}

	public final TTFFontRenderer getBigFont() {
		return bigFont;
	}

	public TTFFontRenderer getGiantFont() {
		return giantFont;
	}
}