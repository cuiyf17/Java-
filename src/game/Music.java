package game;

import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JApplet;


public class Music {
    String file_path;
    Music(String _file_path){
        file_path=_file_path;
    }
    public void play()
    {
        while(Basic.endGame==false){
            AudioInputStream cin = null;
            try {
                cin = AudioSystem.getAudioInputStream(new File(file_path));
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AudioFormat format = cin.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = null;
            try {
                line = (SourceDataLine) AudioSystem.getLine(info);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            try {
                line.open(format);//或者line.open();format参数可有可无
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

            line.start();
            int nBytesRead = 0;
            byte[] buffer = new byte[512];
            while (Basic.endGame==false) {
                try {
                    nBytesRead = cin.read(buffer, 0, buffer.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nBytesRead <= 0)
                    break;
                line.write(buffer, 0, nBytesRead);
            }
            line.drain();
            line.close();
        }

    }
}
