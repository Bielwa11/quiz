package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class NewQuiz {
    private static final double SCORE_GOOD_ENOUGH = 60.0;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> dictionary = new HashMap<>();
    private static final int QUESTIONS_NUMBER = 16;
    private double highScore = 0.0;

    public NewQuiz() {
        fillMap();
    }


    public void fillMap() {
        dictionary.putIfAbsent("never", "nigdy");
        dictionary.putIfAbsent("meat", "mięso");
        dictionary.putIfAbsent("machine", "maszyna");
        dictionary.putIfAbsent("air", "powietrze");
        dictionary.putIfAbsent("add", "dodawać");
        dictionary.putIfAbsent("after", "później");
        dictionary.putIfAbsent("accept", "akceptować");
        dictionary.putIfAbsent("phone", "telefon");
        dictionary.putIfAbsent("shop", "sklep");
        dictionary.putIfAbsent("arrow", "strzała");
        dictionary.putIfAbsent("book", "książka");
        dictionary.putIfAbsent("continue", "kontynuować");
        dictionary.putIfAbsent("cool", "chłodny");
        dictionary.putIfAbsent("definition", "definicja");
        dictionary.putIfAbsent("factory", "fabryka");
        dictionary.putIfAbsent("bike", "rower");
    }


    public void initiate() {
        greetings();
        showMenu();
    }


    private void greetings() {
            languageSelection();
            String language = scanner.nextLine();
            if(language.equals("3")){
                System.out.println("Cześć, jak masz na imię");
                String nameUser = scanner.nextLine();
                System.out.println("Cześć " + nameUser);
            }else if(language.equals("4")){
                printOptionsEnglish();
                System.out.println("Hello what's your name");
                String nameUser = scanner.nextLine();
                System.out.println("Hello " + nameUser);
            }
    }

    private void printOptionsEnglish() {
        System.out.println("If you click 1 you will start the word translation quiz");
        System.out.println("The highest score of the quiz is " + highScore);
        System.out.println("If you click 2 you can add your word");
        System.out.println("If you click 3 you will close the program");
    }

    private void languageSelection() {
        System.out.println("Jeśli klikniesz 3 Quzi będzie w języku polskim");
        System.out.println("Jeśli klikniesz 4 Quzi będzie w języku angielskim");
    }


    private void showMenu() {
        while (true) {
            printOptions();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                runQuiz(dictionary);
            } else if (option.equals("2")) {
                ownWord(dictionary);
            } else if (option.equals("3")) {
                break;
            }
        }
    }

    private void ownWord(Map<String, String> dictionary) {
        System.out.println("Wpisz słowo po polsku");
        String polishWord = scanner.nextLine();
        System.out.println("Wpisz słowo po angielsku");
        String englishWord = scanner.nextLine();
        dictionary.putIfAbsent(englishWord, polishWord);
    }


    private void printOptions() {
        System.out.println("Jeśli klikniesz 1 rozpoczniesz quiz z tłumczenia słowek");
        System.out.println("Najwyższy wynik quizu to " + highScore);
        System.out.println("Jeśli klikniesz 2 możesz dodać swoje słowo");
        System.out.println("Jeśli klikniesz 3 zamkniesz program");
    }


    private void runQuiz(Map<String, String> dictionary) {
        List<String> keys = new ArrayList<>(dictionary.keySet());
        int questionIndex = 0;
        int score = 0;

        while (questionIndex < QUESTIONS_NUMBER) {
            String word = getRandomWord(keys);
            System.out.println("(Pytanie " + (questionIndex + 1) + "/" + QUESTIONS_NUMBER + ")");
            System.out.println("Przetłumacz słowo " + word);
            String wordFromUser = scanner.nextLine();

            if (wordFromUser.equalsIgnoreCase(dictionary.get(word))) {
                System.out.println("Zgadza się!");
                score++;
                keys.remove(word);
            } else {
                System.out.println("NIESTETY blad...");
            }
            questionIndex++;
        }
        System.out.println("Twój wynik to " + score + "/" + QUESTIONS_NUMBER);
        double finalScore = (double) score / (double) questionIndex * 100;
        if (finalScore >= SCORE_GOOD_ENOUGH) {
            System.out.println("Bardzo dobrze!" + finalScore + "% poprawych odpowiedzi");
        } else {
            System.out.println("Niestety! Tylko " + finalScore + "% poprawnych odpowiedzi");
        }
        saveHighScoreIfNeeded(finalScore);
    }

    private void saveHighScoreIfNeeded(double finalScore) {
        if (Double.compare(finalScore, highScore) > 0) {
            highScore = finalScore;
        } else {
            //nic sie nie dzieje, highscore zostaje taki sam.
            //przyklad komentarza

            /*
            przyklad komentarza
             */
        }
    }


    private String getRandomWord(List<String> keys) {
        int index = random.nextInt(keys.size());
        String word = keys.get(index);
        return word;
    }
}


