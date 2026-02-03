package migglione.view.api;

/**
 * Functional interface to apply the Strategy method.
 * 
 * <p>
 * This interface makes so every scene can return it's
 * implementation of the MusicPlayer, with the music
 * chosen for that scene.
 * 
 * <p>
 * Its true use however is seen in the implementation
 * of SwingView, since it is responsible for playing
 * the music of the new scene and eventually stopping the
 * previous one (check SetScene)
 */
@FunctionalInterface
public interface MusicStrategy {
    /**
     * Method used to return the scene's MusicPlayer.
     * 
     * @return the MusicPlayer chosen among its 
     *         implementations for the scene
     */
    MusicPlayer getMusic();
}
