package migglione.persistence.api;

public interface TutorialRepository {
    boolean haveTutorialBennSeen();

    void writeOnTutorial();
}
