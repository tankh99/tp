package seedu.address.commons.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Contains auto-specific code
 */
public class AudioUtil {

    public static final String ERROR_SOUND = "/assets/boop.wav";

    private static Clip clip;

    // Load audio file at the beginning
    static {
        try {
            // Load audio input stream
            InputStream inputStream = AudioUtil.class.getResourceAsStream(ERROR_SOUND);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * Plays an audio given a file path. Note that there is a slight delay due to having to load up the media player
     *
     * @param audioPath File path to the audio file to play. Note that directory starts from project root
     */
    public static void playAudio(String audioPath) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
