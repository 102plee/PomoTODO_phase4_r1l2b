package persistence;


import model.DueDate;
import model.Priority;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

// Converts model elements to JSON objects
public class Jsonifier {

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagToJson(Tag tag) {
        JSONObject tagJson = new JSONObject();
        tagJson.put("name", tag.getName());
        return tagJson;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        JSONObject priorityJson = new JSONObject();
        priorityJson.put("important", priority.isImportant());
        priorityJson.put("urgent", priority.isUrgent());
        return priorityJson;
    }

    // EFFECTS: returns JSON respresentation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        if (dueDate == null) {
            return null;
        } else {
            JSONObject duedateJson = new JSONObject();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dueDate.getDate());
            duedateJson.put("year", cal.get(Calendar.YEAR));
            duedateJson.put("month", cal.get(Calendar.MONTH));
            duedateJson.put("day", cal.get(Calendar.DAY_OF_MONTH));
            duedateJson.put("hour", cal.get(Calendar.HOUR_OF_DAY));
            duedateJson.put("minute", cal.get(Calendar.MINUTE));
            return duedateJson;
        }
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        JSONObject taskJson = new JSONObject();
        taskJson.put("description", task.getDescription());
        JSONArray tags = new JSONArray();
        for (Tag tag : task.getTags()) {
            tags.put(tagToJson(tag));
        }
        taskJson.put("tags", tags);
        if (dueDateToJson(task.getDueDate()) != null) {
            taskJson.put("due-date", dueDateToJson(task.getDueDate()));
        } else {
            taskJson.put("due-date", JSONObject.NULL);
        }
        taskJson.put("priority", priorityToJson(task.getPriority()));
        taskJson.put("status", task.getStatus().toString());
        return taskJson;
    }

    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        JSONArray taskObjects = new JSONArray();
        for (Task task : tasks) {
            if (!task.equals(null)) {
                taskObjects.put(taskToJson(task));
            }
        }
        return taskObjects;
    }
}
