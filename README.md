# taskat

A simple, customizable task management application that lets you organize tasks by date and description.
It features both a command-line interface and a JavaFX-powered graphical user interface.

| Terminal UI                                          | Graphical UI                                     |
|------------------------------------------------------|--------------------------------------------------|
| <img src="images/console_snapshot.png" height="500"> | <img src="images/gui_snapshot.png" height="500"> |

## Key features

- Add tasks with a date and description
- View tasks
- Persists tasks in your system's app data folder (see [code for determining file path](https://github.com/joeyshi12/taskat/blob/b2f05530e6119b9de925c01a8a1238dd0cb24072/src/main/java/utility/JsonFileIO.java#L50))

> ⚙️ Note: The GUI layout was originally provided as part of a CPSC 210 course assignment, with key logic components implemented independently.

## Getting started

Install the [latest release of tasket](https://github.com/joeyshi12/taskat/releases).
Start the GUI application by double-clicking the jar file or executing `java -jar taskat.jar` in the terminal.
To use the command-line interface, execute `java -cp taskat.jar ConsoleToDoApp`.
