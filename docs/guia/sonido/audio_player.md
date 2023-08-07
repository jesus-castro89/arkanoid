---
title: AudioPlayer
description: Definición de la clase de reproducción de sonidos.
editLink: false
---

# AudioPlayer <Badge type="tip" text="Nuevo" vertical="middle" />

Para poder reproducir sonidos en formato "WAV", es importante crear la clase encargada de la gestión y reproducción de
los archivos de sonido.

Esta clase cuenta con 3 atributos:

- audioClip
    - Elemento de tipo Clip que representa el audio a reproducir.
- running
    - Un booleano que permite determinar si el audio se seguirá reproduciendo o no.
- clipTime
    - Elemento que guarda el momento de la pista en la cual se detiene la ejecución de la reproducción.

Así mismo, la clase deberá de implementar la interfaz Runnable y se verá así:

```java
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
}
```
