package dev.Aziz.tilegame.sounds;

import dev.Aziz.tilegame.gfx.Assets;

public class Sound implements Runnable {
    @Override
    public void run() {

        SoundLoader.playSound(Assets.backGroundMusic);
    }
}
