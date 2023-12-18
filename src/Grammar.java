import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grammar {
    private String filename;
    private List<String> nonTerminals;
    private List<String> terminals;
    private Map<String, List<String>> productions;
    private String start;

    public Grammar(String filename) {
        this.filename = filename;
        this.nonTerminals = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.productions = new HashMap<>();
        this.start = "";
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            nonTerminals = List.of(reader.readLine().trim().split(":")[1].trim().split(" "));
            terminals = List.of(reader.readLine().trim().split(":")[1].trim().split(" "));
            start = reader.readLine().trim().split(":")[1].trim();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] productionStr = line.trim().split("->");
                String key = productionStr[0].trim();
                String value = productionStr[1].trim();

                if (!productions.containsKey(key)) {
                    productions.put(key, new ArrayList<>());
                }
                productions.get(key).add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "non-terminals: " + nonTerminals + "\n" +
                "terminals: " + terminals + "\n" +
                "productions: " + productions + "\n" +
                "start: " + start + "\n";
    }

    public void printProductions(String nonTerminal) {
        System.out.println("Productions for " + nonTerminal + ":");
        for (String production : productions.get(nonTerminal)) {
            System.out.println("\t" + nonTerminal + " -> " + production);
        }
    }

    public boolean isCFG() {
        for (String key : productions.keySet()) {
            if (!nonTerminals.contains(key)) {
                System.out.println(key + " is not a non-terminal");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Grammar gr = new Grammar("g2.txt");
        System.out.println(gr);
        System.out.println(gr.isCFG());
    }
}
