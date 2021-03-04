package pl.bielwa;

import java.util.*;

public class Main {
    private Object scores;

    public static void main(String[] args) {
        
        NewQuiz quizz new NewQuiz();
        quizz,initiateQuiz();
        
        Scanner scanner = new Scanner(System.in);
        Map<String, String> dictionary = new HashMap<>(Map.of("arrow", "strzała", "shop", "sklep", "phone", "telefon", "accept", "akceptować", "after", "później", "add", "dodawać", "air", "powietrze", "machine", "maszyna", "meat", "mięso", "never", "nigdy"));
        dictionary.putIfAbsent("book", "książka");
        dictionary.putIfAbsent("continue", "kontynuować");
        dictionary.putIfAbsent("cool", "chłodny");
        dictionary.putIfAbsent("definition", "definicja");
        dictionary.putIfAbsent("factory", "fabryka");
        System.out.println("Cześć, jak masz na imię");
        String nameUser = scanner.nextLine();
        System.out.println("Cześć" + " " + nameUser);


        while (true) {
            System.out.println("Jeśli klikniesz 1 rozpoczniesz quiz z tłumczenia słowek");
            System.out.println("Najwyższy wynik quizu to ");
            System.out.println("Jeśli klikniesz 2 zamkniesz program");
            String option = scanner.nextLine();
            if (option.equals("1")) {
                initiateQuiz(dictionary);
            } else if (option.equals("2")) {
                break;
            }

        }
    }

    private initiateQuiz() {
    }

    private static void initiateQuiz(Map<String, String> dictionary) {
        Scanner quizScanner = new Scanner(System.in);
        List<String> keys = new ArrayList<>(dictionary.keySet());
        Random random = new Random();
        int questionIndex = 0;
        int score = 0;
        while (questionIndex < 15) {
            int index = random.nextInt(keys.size());
            String word = keys.get(index);
            System.out.println("(Pytanie " + (questionIndex + 1) + "/15)");
            System.out.println("Przetłumacz słowo " + word);
            String wordFromUser = quizScanner.nextLine();


            if (wordFromUser.equalsIgnoreCase(dictionary.get(word))) {
                System.out.println("Zgadza się!");
                score++;
            } else {
                System.out.println("NIESTETY blad...");
            }
            questionIndex++;
        }
        System.out.println("Twój wynik to " + score + "/15");
        double finalScore = score / questionIndex;
        if (score >= 10) {
            System.out.println("Bardzo dobrze!" + finalScore + "% poprawych odpowiedzi");
        } else {
            System.out.println("Niestety! Tylko " + finalScore + "% poprawnych odpowiedzi");
        }
    }
}




