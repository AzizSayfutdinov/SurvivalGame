package dev.Aziz.tilegame.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {

    public static Clip loadSound(String path){

        Clip in = null;

        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(SoundLoader.class.getResource(path));
            in = AudioSystem.getClip();
            in.open(audioIn);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return in;

    }

    public static synchronized void playSound(Clip clip){
        if(clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

}
