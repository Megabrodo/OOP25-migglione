package migglione.persistence.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import migglione.persistence.api.TutorialRepository;

public class TutorialRepositoryImpl implements TutorialRepository {

    private static final Logger LOGGER = Logger.getLogger(TutorialRepositoryImpl.class.getName());
    private final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "tutorial.txt");

    @Override
    public boolean haveTutorialBennSeen() {
        if (!Files.exists(path)) {
            return false;
        }

        try (BufferedReader read = Files.newBufferedReader(path)) {
            String s = read.readLine();
            return !s.isBlank();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error while reading file", e);
            return false;
        }
    }

    @Override
    public void writeOnTutorial() {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            Files.createDirectories(path.getParent());
            writer.write("Ok");
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error in writing in file", e);
        }
    }
    
}
