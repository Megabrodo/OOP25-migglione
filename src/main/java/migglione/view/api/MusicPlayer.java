package migglione.view.api;

/**
 * Interface used to describes the main methods for playing music.
 * 
 * <p>
 * This way, different types of music can be played and also
 * the way it behaves can be changed in the different scenes,
 * upholding to the Strategy method.
 */
public interface MusicPlayer {
    /**
     * Functional method to start the music.
     */
    void playMusic();

    /**
     * Functional method to stop the music.
     */
    void stopMusic();
}
