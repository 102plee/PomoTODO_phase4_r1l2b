package parsers;

import model.DueDate;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Represents Task parser
public class TaskParser {
    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray

    List<Task> tasks = new ArrayList<>();
    JSONArray taskArray;

    public List<Task> parse(String input) {
        taskArray = new JSONArray(input);
        for (Object object : taskArray) {
            JSONObject taskJson = (JSONObject) object;
            Task thisTask = mainHelper(taskJson);
            if (thisTask != null) {
                thisTask = helpertaskcalendar(thisTask, taskJson);
                tasks.add(thisTask);
            }
        }
        return tasks;
    }

    private Task mainHelper(JSONObject taskJson) {
        String description = "";
        description += helperDescription(taskJson);
        description += helperTags(taskJson);
        description += helperPriority(taskJson);
        description += helperStatus(taskJson);
        return new Task(description);

    }

    private String helperDescription(JSONObject taskJson) {
        JSONArray tagArray;
        String description = taskJson.getString("description");
        description += "##";
        return description;
    }

    private String helperTags(JSONObject taskJson) {

        JSONArray tagArray = taskJson.getJSONArray("tags");
        String description = "";
        for (Object object1 : tagArray) {
            JSONObject tagJson = (JSONObject) object1;
            description += tagJson.getString("name");
            description += "; ";
        }
        return description;
    }

    private String helperPriority(JSONObject taskJson) {
        String description = "";
        if (taskJson.getJSONObject("priority").getBoolean("important")) {
            description += "important; ";
        }
        if (taskJson.getJSONObject("priority").getBoolean("urgent")) {
            description += "urgent; ";
        }
        return description;
    }

    private String helperStatus(JSONObject taskJson) {
        String description = "";
        description += taskJson.get("status").toString();
        return description;
    }


    private Task helpertaskcalendar(Task thisTask1, JSONObject taskJson) {
        if (taskJson.get("due-date") != JSONObject.NULL) {
            Task thisTask = thisTask1;
            Calendar cal = Calendar.getInstance();
            JSONObject duedateJson = taskJson.getJSONObject("due-date");
            cal.set(duedateJson.getInt("year"), duedateJson.getInt("month"),
                    duedateJson.getInt("day"), duedateJson.getInt("hour"), duedateJson.getInt("minute"));
            Date d = cal.getTime();
            DueDate dd = new DueDate(d);
            thisTask.setDueDate(dd);
            return thisTask;
        } else {
            Task thisTask = thisTask1;
            DueDate dd = new DueDate();
            thisTask.setDueDate(dd);
            return thisTask;
        }
    }

}
