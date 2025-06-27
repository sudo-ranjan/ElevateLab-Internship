# Notes Manager (Java File I/O)

A simple console-based Notes Manager application developed in Java. This project demonstrates how to use **Java File I/O** to create, store, and read notes from a text file.

---

## Features

- Add a new note (appends to `notes.txt`)
- View all saved notes with line numbering
- File-based data persistence using `FileWriter` and `BufferedReader`

---


## How to Run

1. Open terminal in the project folder.
2. Compile the Java program:

```bash
javac NotesApp.java

```
3. Run the program

```bash
java NotesApp

```

## Sample Output

```bash
PS D:\ElevateLab-Java-Ranjan\Task4-NotesManager> javac NotesApp.java
PS D:\ElevateLab-Java-Ranjan\Task4-NotesManager> java NotesApp
Welcome to the Notes Manager

--- Menu ---
1. Add Note
2. View Notes
3. Exit
Enter your choice: 1
Enter your note: Hii, I'm Ranjan Gowda.
Note added successfully.

--- Menu ---
1. Add Note
2. View Notes
3. Exit
Enter your choice: 2

Your Notes:
1. Hii, I'm Ranjan Gowda.

--- Menu ---
1. Add Note
2. View Notes
3. Exit
Enter your choice: 3
Exiting Notes App. Goodbye!

```

## Contributing

Pull requests are welcome. For significant changes, open an issue first to discuss your idea.