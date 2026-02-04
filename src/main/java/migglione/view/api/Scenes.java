package migglione.view.api;

public enum Scenes {
    MENU("MENU"),
    START_GAME("START GAME"),
    TUTORIAL("TUTORIAL"),
    SCORES("SCORES"),
    GALLERY("GALLERY"),
    CREDITS("CREDITS");

    private final String sceneName;

    private Scenes(final String sceneName) {
        this.sceneName = sceneName;
    }

    public String getScene() {
        return this.sceneName;
    }
}
