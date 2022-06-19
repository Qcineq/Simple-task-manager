package pl.coderslab;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";                                        // informacje o zadaniach
    static final String[] OPTIONS = {"1. Add", "2. Remove", "3. List", "4. Exit"};      // lista dostępnych opcji w programie
    static String[][] tasks;                                                            // tablica przechowująca wczytane z pliku informacje


    public static void main(String[] args) {
        printOptions(OPTIONS);
    }

    // wyświetlanie opcji
    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }



}
