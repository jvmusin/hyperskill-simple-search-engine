package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Map<String, SearchStrategy> searchStrategies = Map.of(
            "ALL", new AllWordsSearchStrategy(),
            "ANY", new AnyWordSearchStrategy(),
            "NONE", new NoneWordsSearchStrategy()
    );
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Dictionary dictionary;

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    private String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int nextInt() {
        return Integer.parseInt(nextLine());
    }

    private void run(String[] args) throws Exception {
        dictionary = new Dictionary(Files.readAllLines(Paths.get(args[1])));
        runMenu();
    }

    private void runMenu() {
        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a line");
            System.out.println("2. Print all lines");
            System.out.println("0. Exit");
            int next = nextInt();
            System.out.println();

            switch (next) {
                case 1:
                    handleFind();
                    break;
                case 2:
                    handlePrint();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    private void handleFind() {
        System.out.println("Select a matching strategy: " + String.join(", ", searchStrategies.keySet()));
        String strategyName = nextLine();
        SearchStrategy strategy = searchStrategies.get(strategyName);

        System.out.println("Enter what to find");
        String[] query = nextLine().toLowerCase().split(" ");

        List<String> res = strategy.find(dictionary, query);
        if (res.isEmpty()) {
            System.out.println("No matching lines found.");
        } else {
            System.out.println(res.size() + " lines found:");
            res.forEach(System.out::println);
        }
        System.out.println();
    }

    private void handlePrint() {
        System.out.println("=== List of lines ===");
        dictionary.getLines().forEach(System.out::println);
        System.out.println();
    }
}
