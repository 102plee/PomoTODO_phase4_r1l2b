import model.Task;
import utility.JsonFileIO;

import java.util.List;
import java.util.Scanner;

// A simple "To Do" app
public class ConsoleToDoApp {
    private static List<Task> todo;
    private static Scanner input;
    private static String userInput;
    private static boolean exit = false;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        todo = JsonFileIO.read();

        printLogo();
        while (!exit) {
            displayToDos();
            userInput = input.nextLine();
            takeActionBasedOnInput();
        }
        JsonFileIO.write(todo);
    }

    private static void takeActionBasedOnInput() {
        switch (userInput.toUpperCase().charAt(0)) {
            case 'A':
                addNewTask();
                break;
            case 'D':
                displayTaskDetails();
                break;
            case 'Q':
                exit = true;
                break;
            default:
                System.out.println("Invalid input!");
        }
    }

    private static void displayTaskDetails() {
        for (Task task : todo) {
            System.out.println(task);
        }
        pressEnterToContinue();
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress enter to continue ... ");
        input.nextLine();
    }

    private static void addNewTask() {
        if (getDescriptionForTask()) {
            todo.add(new Task(userInput));
        }
    }

    private static boolean getDescriptionForTask() {
        System.out.println("Enter task description:");
        System.out.print("> ");
        userInput = input.nextLine();
        if (userInput.length() == 0) {
            System.out.println("Task description cannot be empty!");
            return false;
        }
        return true;
    }

    private static void printLogo() {
        String cat = ""
            + "     \\\n"
            + "       \\\n"
            + "         \\\n"
            + "             |\\___/|\n"
            + "           ==) ^Y^ (==\n"
            + "             \\  ^  /\n"
            + "              )=*=(\n"
            + "             /     \\\n"
            + "             |     |\n"
            + "            /| | | |\\\n"
            + "            \\| | |_|/\\\n"
            + "            //_// ___/\n"
            + "                \\_)"
            + "";

        System.out.println(cat);
    }

    private static void displayToDos() {
        System.out.println("\n");
        System.out.println("==============================");
        System.out.println("\tTaskat");
        System.out.println("------------------------------");
        displayPendingTasks();
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("You can ...");
        System.out.println("\tEnter A to add a new task;");
        System.out.println("\tEnter D to show tasks' details;");
        System.out.println("\tEnter Q to quit.");
        System.out.print("> ");
    }

    private static void displayPendingTasks() {
        if (todo.isEmpty()) {
            System.out.println("Wow! you have no pending tasks.");
        } else {
            displayTasks(todo);
        }
        System.out.println("------------------------------");
    }

    private static void displayTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%3d. %s\n", i + 1, tasks.get(i).getDescription());
        }
    }
}