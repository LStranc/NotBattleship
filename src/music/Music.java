package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Music {
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public Music(String filePath)
        {
            this.filePath = filePath;
            // create AudioInputStream object
            try {
                audioInputStream =
                        AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());


                // create clip reference
                clip = AudioSystem.getClip();

                // open audioInputStream to the clip
                clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch(Exception e){
                System.out.println("Error with playing sound.");
                e.printStackTrace();
            }
        }


    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }

}
