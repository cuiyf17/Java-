package game;

public class MusicThread implements Runnable{
    Music music_player;
    public MusicThread(String file_path){
        music_player=new Music(file_path);
    }
    public void run(){
        music_player.play();
    }
}
