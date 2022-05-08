package other;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
/**
 * Class used to play sound
 */
public class Sound {

    //possibles sounds
    public static final int COIN = 3;
    public static final int DAMAGE = 4;
    public static final int WIN = 5;
    public static final int SWORD = 6;
    public static final int BOUNCE = 7;
    public static final int GAME_OVER = 8;

    Clip clip;
    Clip activeClip;
    URL[] urlSound = new URL[30];
    long cliptime = 0;

    /**
     * Constructor method, load all the sounds
     */
    public Sound() {
        urlSound[0]=getClass().getResource("/level1.wav");
        urlSound[1]=getClass().getResource("/level2.wav");
        urlSound[2]=getClass().getResource("/level3.wav");
        urlSound[3]=getClass().getResource("/coin.wav");
        urlSound[4]=getClass().getResource("/hitmonster.wav");
        urlSound[5]=getClass().getResource("/win.wav");
        urlSound[6]=getClass().getResource("/levelup.wav");
        urlSound[7]=getClass().getResource("/bounce.wav");
        urlSound[8]=getClass().getResource("/gameover.wav");
    }

    /**
     * Set the clip to the wanted file
     * @param i, the number of the file in the list preloaded
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(urlSound[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e) {

        }
    }

    /**
     * play the clip
     */
    public void play() {
        clip.start();
    }

    /**
     * loop the clip
     */
    public void loop() {
        try {
            //we stop the former loop clip
            activeClip.stop();
        }
        catch (Exception e) {

        }

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //we put the activeClip to the current clip, so that when we want to stop the clip loop, we can do it
        //because each time a new sound is played, the clip change
        activeClip=clip;
    }

    /**
     * Stop the current clip
     */
    public void stop() {
        clip.stop();
    }

    /**
     * We switch the loop
     * @param num
     */
    public void switchloop(int num) {
        setFile(num);
        play();
        loop();
    }

    public Clip getCurrentLoop() {
        return activeClip;
    }

    public void pauseCurrentLoop() {
        cliptime = activeClip.getMicrosecondPosition();
        activeClip.stop();
    }

    public void playCurrentLoop() {
        activeClip.setMicrosecondPosition(cliptime);
        activeClip.start();
    }
}
