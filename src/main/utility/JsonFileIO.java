package utility;

import model.Task;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");
    private static List<Task> tasks;
    private static TaskParser taskparser;
    private static Jsonifier jsonifier;
    
    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() {
        taskparser = new TaskParser();
        FileInputStream fis = null;
        String str = "";
        try {
            fis = new FileInputStream(jsonDataFile);
            int content;
            while ((content = fis.read()) != -1) {
                str += (char) content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskparser.parse(str);
    }
    
    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) {
        jsonifier = new Jsonifier();
        try {
            FileWriter file = new FileWriter(jsonDataFile);
            file.write(jsonifier.taskListToJson(tasks).toString());
            file.flush();
        } catch (IOException e) {
            System.out.println("Oopsie");
        }

    }
}
