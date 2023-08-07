package util;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer implements Runnable {
    private Clip audioClip;
    private boolean running = true;
    private long clipTime;

    public AudioPlayer(String audioFilePath) {

        this.load(audioFilePath);
        Thread audioThread = new Thread(this, "audioThread");
        audioThread.start();
    }

    private void load(String audioFilePath) {

        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
            audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
            running = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start(String audioFilePath) {

        load(audioFilePath);
    }

    public void resume() {
        running = true;
        audioClip.setMicrosecondPosition(clipTime);
        audioClip.start();
    }

    public void stop() {

        running = false;
        clipTime = audioClip.getMicrosecondPosition();
        audioClip.stop();
    }

    public void run() {

        while (running) {
            audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
        }
    }

    public Clip getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(Clip audioClip) {
        this.audioClip = audioClip;
    }
}
