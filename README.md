# Ball Project

A Java AWT desktop application demonstrating animated balls using platform threads, scheduled tasks, and virtual threads.

## Requirements

- JDK 21 or newer, with both `java` and `javac` from that JDK on your PATH.
- A desktop environment to display the application window.

## Compile and run

Run these commands from the project folder:

```sh
javac -d out src/*.java
java -cp out MyFrame
```

Click **Add Ball** to add four balls using different concurrency mechanisms. Click **Shake Balls** to resume paused balls.

## Source files

Java source code is stored in `src/`. Compiled `.class` files, build folders, and local IDE settings are excluded through `.gitignore`.

The Java source files are preserved as supplied in `Ball_project.zip`.
