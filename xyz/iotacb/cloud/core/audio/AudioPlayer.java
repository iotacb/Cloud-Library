package xyz.iotacb.cloud.core.audio;

import jaco.mp3.player.MP3Player;

public class AudioPlayer {
	
	public static void playAudio(final Audio audio, final boolean repeat) {
		MP3Player player = new MP3Player(audio.file);
		player.setRepeat(repeat);
		player.play();
	}

}
