package dev.Aziz.tilegame.sounds;


import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements Runnable {

    private Clip clip;

    public Sound(Clip clip){
        this.clip = clip;
    }

    @Override
    public void run() {

        SoundLoader.playSound(clip);
    }

    public void setVolume(float volume){

        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(volume);

    }


}
