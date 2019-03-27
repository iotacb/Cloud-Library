package xyz.iotacb.cloud.core.audio;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Audio {
	
	File file;
	
	MP3Player player;
	
	/**
	 * Load a audio file from a file path
	 * @param path
	 */
	public Audio(final String path) {
		this.file = new File(path);
		player = new MP3Player(this.file);
	}
	
	/**
	 * Play the audio file and decide if it should be repeat
	 * @param repeat
	 */
	public void play(final boolean repeat) {
		player.setRepeat(repeat);
		player.play();
	}
	
	/**
	 * Stop the audio playing
	 */
	public void stop() {
		player.stop();
	}
	
	/**
	 * Check if the audio is currently playing
	 * @return
	 */
	public boolean isPlaying() {
		return !player.isStopped();
	}
	
	/**
	 * Get the file
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
}
