package model;

import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo> {
    private String description;
    private List<Todo> tasks;

    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty
    public Project(String description) {
        super(description);
        this.description = description;
        tasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) {
        if (!task.equals(this)) {
            if (!contains(task)) {
                tasks.add(task);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) {
        if (contains(task)) {
            tasks.remove(task);
        }
    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return description;
    }

    @Override
    public int getEstimatedTimeToComplete() {
        int total = 0;
        for (Todo t : tasks) {
            total += t.getEstimatedTimeToComplete();
        }
        return total;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    @Deprecated
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }

//    // EFFECTS: returns an integer between 0 and 100 which represents
//    //     the percentage of completed tasks (rounded down to the closest integer).
//    //     returns 100 if this project has no tasks!
//    public int getProgress() {
//        int numerator = getNumberOfCompletedTasks();
//        int denominator = getNumberOfTasks();
//        if (numerator == denominator) {
//            return 100;
//        } else {
//            return (int) Math.floor(numerator * 100.0 / denominator);
//        }
//    }

    // EFFECTS: returns an integer between 0 and 100 which represents
//     the percentage of completion (rounded down to the nearest integer).
//     the value returned is the average of the percentage of completion of
//     all the tasks and sub-projects in this project.
    public int getProgress() {
        int size = tasks.size();
        int total = 0;
        for (Todo t : tasks) {
            total += t.getProgress();
        }
        if (size == 0) {
            return 0;
        } else {
            return total / size;
        }

    }
//
//    // EFFECTS: returns the number of completed tasks in this project
//    private int getNumberOfCompletedTasks() {
//        int done = 0;
//        for (Task t : tasks) {
//            if (t.getStatus() == Status.DONE) {
//                done++;
//            }
//        }
//        return done;
//    }

    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
//     If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }

    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public Iterator<Todo> iterator() {
        return new ProjectIterator();
    }

    private class ProjectIterator implements Iterator<Todo> {

        private final String importantandurgent = "IMPORTANT & URGENT";
        private final String important = "IMPORTANT";
        private final String urgent = "URGENT";
        private final String none = "DEFAULT";
        private int locallistindex;

        private int listIndex;
        private int totalSize;
        private String currentPriority;

        public ProjectIterator() {
            totalSize = tasks.size();
            listIndex = 0;
            currentPriority = importantandurgent;
            locallistindex = 0;
        }

        @Override
        public boolean hasNext() {
            if (listIndex >= totalSize) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Todo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int i = 0;
            for (Todo t : tasks) {
                if (t.getPriority().toString() == currentPriority) {
                    if (locallistindex == i) {
                        locallistindex++;
                        listIndex++;
                        return t;
                    }
                    i++;
                }
            }

            nexthelper();
            locallistindex = 0;
            return next();
        }

        private void nexthelper() {
            if (currentPriority == importantandurgent) {
                currentPriority = important;
            } else if (currentPriority == important) {
                currentPriority = urgent;
            } else if (currentPriority == urgent) {
                currentPriority = none;
            }
        }

    }
}