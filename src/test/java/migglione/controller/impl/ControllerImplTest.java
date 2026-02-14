package migglione.controller.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import migglione.model.api.Game;
import migglione.persistence.api.ScoreRepository;
import migglione.persistence.api.TutorialRepository;
import migglione.view.api.SwingView;

public class ControllerImplTest {
    
    @Mock
    private SwingView view;
    @Mock
    private Game model;
    @Mock
    private ScoreRepository sRep;
    @Mock
    private TutorialRepository tRep;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCheckFirstSession() {
        assertFalse(tRep.haveTutorialBeenSeen());

        ControllerImpl controller = new ControllerImpl(view, sRep, tRep, model);

        verify(tRep).writeOnTutorial();
        verify(view).showTutorialPrompt();
    }
}