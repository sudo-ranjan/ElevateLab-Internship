cat > README.md <<EOF
# Online Quiz App (Java Console)

A simple **console-based quiz application** developed in Java. This mini project demonstrates your understanding of control flow, OOP, and user input handling.

---

## Features

- Multiple-choice quiz questions
- Uses a **List** to store questions dynamically
- Accepts user answers through the console
- Scores the user and shows the final result
- Demonstrates clean OOP design with a `Question` class

---

## Technologies Used

- Java (JDK)
- VS Code, IntelliJ, or any Java IDE
- Command Line / Terminal

---

## Project Structure

```
Task8-QuizApp/
├── QuizApp.java       # Main application code
├── README.md          # Project documentation
```

---

## How to Run

1. **Open terminal in project folder**

2. **Compile the program**
   ```bash
   javac QuizApp.java
   ```

3. **Run the program**
   ```bash
   java QuizApp
   ```

---

## Sample Output

```bash
=== Welcome to the Quiz App ===

What is the capital of France?
1) Berlin
2) Madrid
3) Paris
4) Rome
Your answer (enter option number): 1
Wrong! Correct answer was option 3

Which language runs in a web browser?
1) Java
2) C
3) Python
4) JavaScript
Your answer (enter option number): 4
Correct!

Who developed Java?
1) Microsoft
2) Sun Microsystems
3) Google
4) Apple
Your answer (enter option number): 2
Correct!

=== Quiz Finished! ===
Your Score: 2/3
```

---

## How it Works

- **`Question` class**: Represents a single quiz question, its options, and the correct answer.
- **`List<Question>`**: Stores all the quiz questions dynamically.
- **Loop & Control Flow**: Iterates through questions, takes user input, checks answers.
- **Scoring**: Calculates the total score and displays it at the end.

---

## Outcome

By completing this project, you have:
- Practiced using **OOP** (`Question` class)
- Worked with **Collections** (`List`)
- Applied **loops** and **conditionals**
- Improved your **console I/O skills**

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss your ideas.

---