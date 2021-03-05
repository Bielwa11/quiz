package games;

import java.util.*;

public class NewQuiz {
    private static final double SCORE_GOOD_ENOUGH = 60.0;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Map<String, String>> dictionaries = new HashMap<>();
    private static final int QUESTIONS_NUMBER = 16;
    private double highScore = 0.0;
    private Map<String, String> currentLanguage;

    public NewQuiz() {
        fillAllDictionaries();
    }


    public void fillAllDictionaries() {
        fillEnglishDictionary();
        fillItalianDictionary();
    }

    private void fillItalianDictionary() {
        Map<String, String> italianDictionary = new HashMap<>();
        italianDictionary.putIfAbsent("spaghetti", "spaghetti");
        italianDictionary.putIfAbsent("meat", "mięso");
        italianDictionary.putIfAbsent("machine", "maszyna");
        italianDictionary.putIfAbsent("air", "powietrze");
        italianDictionary.putIfAbsent("add", "dodawać");
        italianDictionary.putIfAbsent("after", "później");
        italianDictionary.putIfAbsent("accept", "akceptować");
        italianDictionary.putIfAbsent("phone", "telefon");
        italianDictionary.putIfAbsent("shop", "sklep");
        italianDictionary.putIfAbsent("arrow", "strzała");
        italianDictionary.putIfAbsent("book", "książka");
        italianDictionary.putIfAbsent("continue", "kontynuować");
        italianDictionary.putIfAbsent("cool", "chłodny");
        italianDictionary.putIfAbsent("definition", "definicja");
        italianDictionary.putIfAbsent("factory", "fabryka");
        italianDictionary.putIfAbsent("bike", "rower");

        dictionaries.putIfAbsent("italian", italianDictionary);

    }

    private void fillEnglishDictionary() {
        Map<String, String> englishDictionary = new HashMap<>();
        englishDictionary.putIfAbsent("never", "nigdy");
        englishDictionary.putIfAbsent("meat", "mięso");
        englishDictionary.putIfAbsent("machine", "maszyna");
        englishDictionary.putIfAbsent("air", "powietrze");
        englishDictionary.putIfAbsent("add", "dodawać");
        englishDictionary.putIfAbsent("after", "później");
        englishDictionary.putIfAbsent("accept", "akceptować");
        englishDictionary.putIfAbsent("phone", "telefon");
        englishDictionary.putIfAbsent("shop", "sklep");
        englishDictionary.putIfAbsent("arrow", "strzała");
        englishDictionary.putIfAbsent("book", "książka");
        englishDictionary.putIfAbsent("continue", "kontynuować");
        englishDictionary.putIfAbsent("cool", "chłodny");
        englishDictionary.putIfAbsent("definition", "definicja");
        englishDictionary.putIfAbsent("factory", "fabryka");
        englishDictionary.putIfAbsent("bike", "rower");

        dictionaries.putIfAbsent("english", englishDictionary);
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
            chooseLanguage();
            printOptions();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                runQuiz(currentLanguage);
            } else if (option.equals("2")) {
                ownWord(currentLanguage);
            } else if (option.equals("3")) {
                break;
            }
        }
    }

    private void chooseLanguage() {
        while (true) {
            System.out.println("Wybierz numer jezyka jaki chcesz");
            List<String> languages = new ArrayList<>(dictionaries.keySet());
            for (int i = 0; i < languages.size(); i++) {
                System.out.println(i + ". " + languages.get(i));
            }
            int option = scanner.nextInt();
            if (option < languages.size() && option >= 0) {
                currentLanguage = dictionaries.get(languages.get(option));
                break;
            } else {
                System.out.println("Wybrales zly numer, sprobuj jeszcze raz.");
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


