package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";                                        // informacje o zadaniach
    static final String[] OPTIONS = {"1. Add", "2. Remove", "3. List", "4. Exit"};      // lista dostępnych opcji w programie
    static String[][] tasks;                                                            // tablica przechowująca wczytane z pliku informacje


    public static void main(String[] args) {
        tasks = loadDataToTab(FILE_NAME);
        printOptions(OPTIONS);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                case "4":
                case "Exit":
                    break;
                case "add":
                case "1":
                case "Add":
                    addTask();
                    break;
                case "remove":
                case "2":
                case "Remove":
                    removeTask(tasks, getTheNumber());
                    System.out.println("Value was successfully deleted.");
                    break;
                case "list":
                case "3":
                case "List":
                    printTab(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            printOptions(OPTIONS);
        }
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

    // Wyświetlanie listy zadań
    public static void printTab(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Dodawanie zadań
    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date (YYYY-MM-DD)");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: (true/false)");
        String isImportant = scanner.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = isImportant;
    }

    // Pobranie numeru zadania do usunięcia
    public static int getTheNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove.");

        String n = scanner.nextLine();
        while (!isNumberGreaterEqualZero(n)) {
            System.out.println(ConsoleColors.RED);
            System.out.println("Incorrect argument passed. Please give number greater or equal 0" + ConsoleColors.RESET);
            scanner.nextLine();
        }
        return Integer.parseInt(n);
    }

    // Sprawdzanie, czy podany numer zadania do usunięcia (jako String) jest możliwy do przekształcenia na int
    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    // Usuwanie zadania
    private static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ConsoleColors.RED);
            System.out.println("Element not exist in tab" + ConsoleColors.RESET);
        }
    }

}
