package xyz.iotacb.cloud.core.audio;

import jaco.mp3.player.MP3Player;

public class AudioPlayer {
	
	/**
	 * Plays a audio file with Jaco player
	 * @param audio
	 * @param repeat
	 */
	public static void playAudio(final Audio audio, final boolean repeat) {
		MP3Player player = new MP3Player(audio.file);
		player.setRepeat(repeat);
		player.play();
	}

}
