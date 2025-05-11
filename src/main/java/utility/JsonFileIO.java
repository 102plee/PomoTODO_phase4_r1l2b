package utility;

import model.Task;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() {
        TaskParser taskparser = new TaskParser();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Path filePath = getFilePath();
            Files.createDirectories(filePath.getParent());
            BufferedReader fis = Files.newBufferedReader(filePath);
            int content;
            while ((content = fis.read()) != -1) {
                stringBuilder.append((char) content);
            }
        } catch (NoSuchFileException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskparser.parse(stringBuilder.toString());
    }

    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) {
        try {
            Path filePath = getFilePath();
            Files.createDirectories(filePath.getParent());
            BufferedWriter file = Files.newBufferedWriter(filePath);
            file.write(Jsonifier.taskListToJson(tasks).toString());
            file.flush();
        } catch (IOException e) {
            System.out.println("Oopsie");
        }
    }

    private static Path getFilePath() throws InvalidPathException {
        String osName = System.getProperty("os.name").toLowerCase();
        String homeDir = System.getProperty("user.home");
        if (osName.contains("win")) {
            return Paths.get(homeDir, "AppData", "Local", "taskat", "tasks.json");
        } else if (osName.contains("mac")) {
            return Paths.get(homeDir, "Library", "Application Support", "taskat", "tasks.json");
        } else {
            return Paths.get(homeDir, ".local", "share", "taskat", "tasks.json");
        }
    }
}
