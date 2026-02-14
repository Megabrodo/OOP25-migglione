package migglione.controller.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import migglione.model.api.Game;
import migglione.persistence.api.ScoreRepository;
import migglione.persistence.api.TutorialRepository;
import migglione.view.api.SwingView;

/**
 * Test for the ControllerImpl.
 * 
 * <p>
 * The usage of mocks is necessary, so it has been
 * considered the usage of Mockito as an external library.
 * This way, copies of the entities that the Controller
 * uses can be made and be used to simulate the outcome.
 * 
 * <p>
 * To implement and use the Mockito library, new package
 * restricted methods have been added to ControllerImpl, and
 * to verify the methods of the library this link can be followed:
 * https://site.mockito.org/javadoc/current/org/mockito/Mockito.html#method_summary
 */
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

        new ControllerImpl(view, sRep, tRep, model);

        verify(tRep).writeOnTutorial();
        verify(view).showTutorialPrompt();
    }

    @Test 
    void testEndSession() {
        when(model.getWinner()).thenReturn(Optional.of("player"));
        when(model.getPlayerScore()).thenReturn(Optional.of(20));
        when(model.getCPUScore()).thenReturn(Optional.of(4));

        ControllerImpl controller = new ControllerImpl(view, sRep, tRep, model);
        controller.setPlayerMockName("player");
        controller.endSession();

        verify(sRep).writeWinner("player", 20);
        verify(view).endMessage("player", "player", 20, 4);
    }

    @Test
    void testEndSessionNotWorking() {
        when(model.getWinner()).thenReturn(Optional.empty());
        when(model.getPlayerScore()).thenReturn(Optional.empty());
        when(model.getCPUScore()).thenReturn(Optional.empty());

        ControllerImpl controller = new ControllerImpl(view, sRep, tRep, model);
        controller.endSession();

        verifyNoInteractions(sRep);
        verify(view, never()).endMessage(anyString(), anyString(), anyInt(), anyInt());
    }
}