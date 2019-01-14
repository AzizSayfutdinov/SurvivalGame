package dev.Aziz.tilegame.sounds;


import javax.sound.sampled.Clip;

public class Sound implements Runnable {

    private Clip clip;

    public Sound(Clip clip){
        this.clip = clip;
    }

    @Override
    public void run() {

        SoundLoader.playSound(clip);
    }
}
