import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String questionText;
    String[] options;
    int correctOption; // index starting from 1

    Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    boolean isCorrect(int userAnswer) {
        return userAnswer == correctOption;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"1) Berlin", "2) Madrid", "3) Paris", "4) Rome"},
                3
        ));
        questions.add(new Question(
                "Which language runs in a web browser?",
                new String[]{"1) Java", "2) C", "3) Python", "4) JavaScript"},
                4
        ));
        questions.add(new Question(
                "Who developed Java?",
                new String[]{"1) Microsoft", "2) Sun Microsystems", "3) Google", "4) Apple"},
                2
        ));

        int score = 0;

        System.out.println("=== Welcome to the Quiz App ===");

        for (Question q : questions) {
            System.out.println("\n" + q.questionText);
            for (String option : q.options) {
                System.out.println(option);
            }

            System.out.print("Your answer (enter option number): ");
            int userAnswer = scanner.nextInt();

            if (q.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer was option " + q.correctOption);
            }
        }

        System.out.println("\n=== Quiz Finished! ===");
        System.out.println("Your Score: " + score + "/" + questions.size());

        scanner.close();
    }
}
