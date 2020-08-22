package  music;

import javafx.scene.media.AudioClip;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase Encargada de la generacion del Sonido durante todo el juego.
 * @author Grupo 10
 *
 */
public class Sound extends Thread {
    public void run(){


    }

    public Sound(String path) {

        playmusic(path);
    }
    public Sound(){

        error();
    }

    public static void playmusic(String path)
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null ;

        try
        {
            InputStream test = new FileInputStream(path);
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch( IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }

    public static void error(){

        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null ;

        try
        {
            InputStream test = new FileInputStream("src/resources/error.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
    } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void correct(){

        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null ;

        try
        {
            InputStream test = new FileInputStream("src/resources/correct.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    }






