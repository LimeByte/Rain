package me.limebyte.rain.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Song {

    private Clip clip;
    private boolean loop;
    private static final float volume = 0f;
    private FloatControl gain;
    private Thread fader;
    private boolean fading = false;

    public Song(String path, boolean loop) {
        this.loop = loop;
        try {
            AudioInputStream sample;
            sample = AudioSystem.getAudioInputStream(Song.class.getResource(path));
            clip = AudioSystem.getClip();
            clip.open(sample);
            gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.err.println("Failed to load song.");
        }
    }

    private void fade(final FloatControl gain, final double seconds, final float to, final float from) {
        final float dif = to - from;
        gain.setValue(from);
        fader = new Thread(new Runnable() {
            public void run() {
                fading = true;
                for (float i = 0; i < seconds * 1000; i++) {
                    if (!fading) break;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {

                    }
                    gain.setValue((float) (from + dif * Math.sqrt(i / seconds / 1000)));
                }
                fading = false;
            }
        });
        fader.start();
    }

    private void fadeIn(float from) {
        fade(gain, 2, volume, from);
    }

    private void fadeOut(float to) {
        fade(gain, 2, to, volume);
    }

    public void play() {
        resume();
        fadeIn(-50f);
    }

    public void pause() {
        fading = false;
        try {
            fader.join();
        } catch (InterruptedException e) {
        }
        clip.stop();
    }

    public void resume() {
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    public void stop() {
        fadeOut(-80f);
        try {
            fader.join();
        } catch (InterruptedException e) {
        }
        clip.stop();
        clip.setFramePosition(0);
    }

    public boolean isPlaying() {
        return clip.isActive();
    }
}