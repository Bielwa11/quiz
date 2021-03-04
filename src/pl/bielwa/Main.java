package pl.bielwa;

public class Main {

    public static void main(String[] args) {

        NewQuiz quizz = new NewQuiz();
        quizz.fillMap();
        quizz.initiate();
        quizz.compare();
    }
}


