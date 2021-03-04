package pl.bielwa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class NewQuiz {

    public static final double GOOD_ENOUGH_NUMBER = 60.0;
    public static final double SCORE_GOOD_ENOUGH = 70.0;

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Map<String, String> dictionary = new HashMap<>(Map.of("arrow", "strzała", "shop", "sklep", "phone", "telefon", "accept", "akceptować", "after", "później", "add", "dodawać", "air", "powietrze", "machine", "maszyna", "meat", "mięso", "never", "nigdy"));
    public static final int QUESTIONS_NUMBER = 16;


    public NewQuiz() {

        fillMap();

    }


    public void fillMap() {

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

        System.out.println("Cześć, jak masz na imię");
        String nameUser = scanner.nextLine();
        System.out.println("Cześć " + nameUser);

    }


    private void showMenu() {

        while (true) {

            printOptions();
            String option = scanner.nextLine();
            if (option.equals("1")) {

                runQuiz(dictionary);

            } else if (option.equals("2")) {

                break;

            }

        }

    }


    private void printOptions() {

        System.out.println("Jeśli klikniesz 1 rozpoczniesz quiz z tłumczenia słowek");
        System.out.println("Najwyższy wynik quizu to " + (GOOD_ENOUGH_NUMBER) * 100);
        System.out.println("Jeśli klikniesz 2 zamkniesz program");

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
                dictionary.remove(dictionary.get(word));
            } else {
                System.out.println("NIESTETY blad...");
            }
            questionIndex++;

        }

        System.out.println("Twój wynik to " + score + "/" + QUESTIONS_NUMBER);
        double finalScore = (double)score / (double)questionIndex *100;
        if (finalScore >= SCORE_GOOD_ENOUGH) {
            System.out.println("Bardzo dobrze!" + finalScore + "% poprawych odpowiedzi");
        } else {
            System.out.println("Niestety! Tylko " + finalScore + "% poprawnych odpowiedzi");
        }
    }


    private String getRandomWord(List<String> keys) {
        int index = random.nextInt(keys.size());
        String word = keys.get(index);
        return word;
    }
}


