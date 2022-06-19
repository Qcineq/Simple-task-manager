package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";                                        // informacje o zadaniach
    static final String[] OPTIONS = {"1. Add", "2. Remove", "3. List", "4. Exit"};      // lista dostępnych opcji w programie
    static String[][] tasks;                                                            // tablica przechowująca wczytane z pliku informacje


    public static void main(String[] args) {
        tasks = loadDataToTab(FILE_NAME);
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

    // pobieranie danych z pliku
    public static String[][] loadDataToTab(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {                                                          // sprawdzanie, czy plik o podanej nazwie istnieje
            System.out.println(ConsoleColors.RED);
            System.out.println("File not exist." + ConsoleColors.RESET);
            System.exit(0);
        }

        String[][] tab = null;                                                              // tablica, którą metoda będzie zwracać
        try {
            List<String> strings = Files.readAllLines(dir);                                 // wczytywanie zawartości pliku
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {                                      // umieszczanie wczytanych danych do tablicy
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }


}
