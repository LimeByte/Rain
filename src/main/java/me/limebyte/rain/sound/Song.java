package me.limebyte.rain.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Song {

    private Clip clip;
    private boolean loop;

    public Song(String path, boolean loop) {
        this.loop = loop;
        try {
            AudioInputStream sample;
            sample = AudioSystem.getAudioInputStream(Song.class.getResource(path));
            clip = AudioSystem.getClip();
            clip.open(sample);
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(-8.0f);
        } catch (Exception e) {
            System.err.println("Failed to load song.");
        }
    }

    public void play() {
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }
}