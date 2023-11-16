import java.io.*;
import java.util.*;
public class FiniteAutomaton {
    private Set<String> states;
    private Set<Character> alphabet;
    private Map<Map.Entry<String, Character>, String> transitions;
    private String initialState;
    private Set<String> finalStates;

    public FiniteAutomaton() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        transitions = new HashMap<>();
        finalStates = new HashSet<>();
    }

    public void addState(String state) {
        states.add(state);
    }

    public void setAlphabet(Set<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public void addTransition(String sourceState, char symbol, String targetState) {
        if (!alphabet.contains(symbol)) {
            throw new IllegalArgumentException("Symbol not in alphabet");
        }
        if (!states.contains(sourceState) || !states.contains(targetState)) {
            throw new IllegalArgumentException("State not defined");
        }

        Map.Entry<String, Character> key = new AbstractMap.SimpleEntry<>(sourceState, symbol);
        transitions.put(key, targetState);
    }

    public void setInitialState(String state) {
        if (!states.contains(state)) {
            throw new IllegalArgumentException("State not defined");
        }
        initialState = state;
    }

    public void addFinalState(String state) {
        if (!states.contains(state)) {
            throw new IllegalArgumentException("State not defined");
        }
        finalStates.add(state);
    }

    public boolean accepts(String input) {
        String currentState = initialState;
        for (char symbol : input.toCharArray()) {
            Map.Entry<String, Character> key = new AbstractMap.SimpleEntry<>(currentState, symbol);
            if (!transitions.containsKey(key)) {
                return false;
            }
            currentState = transitions.get(key);
        }
        return finalStates.contains(currentState);
    }

    public void readFAFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("I.S")) {
                    initialState = line.split("->")[1].trim();
                } else if (line.startsWith("F.S")) {
                    finalStates.addAll(Arrays.asList(line.split("->")[1].trim().split("\\s+")));
                } else if (line.startsWith("S.S")) {
                    states.addAll(Arrays.asList(line.split("->")[1].trim().split("\\s+")));
                } else if (line.startsWith("Alph")) {
                    String[] alphSymbols = line.split("->")[1].trim().split("\\s+");
                    for (String sym : alphSymbols) {
                        alphabet.add(sym.charAt(0));
                    }
                } else if (line.startsWith("T")) {
                    String[] parts = line.split("->")[1].trim().split("\\s+");
                    addTransition(parts[0], parts[1].charAt(0), parts[2]);
                }
            }
        }
    }

    public void displayMenu(){
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("\nFinite Automaton Menu:");
            System.out.println("1. Display States");
            System.out.println("2. Display Alphabet");
            System.out.println("3. Display Transitions");
            System.out.println("4. Display Initial State");
            System.out.println("5. Display Final States");
            System.out.println("6. Display all");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayStates();
                    break;
                case 2:
                    displayAlphabet();
                    break;
                case 3:
                    displayTransitions();
                    break;
                case 4:
                    displayInitialState();
                    break;
                case 5:
                    displayFinalStates();
                    break;
                case 6:
                    displayStates();
                    displayAlphabet();
                    displayTransitions();
                    displayInitialState();
                    displayFinalStates();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayStates() {
        System.out.println("States: " + states);
    }

    private void displayAlphabet() {
        System.out.println("Alphabet: " + alphabet);
    }

    private void displayTransitions() {
        System.out.println("Transitions:");
        for (Map.Entry<Map.Entry<String, Character>, String> entry : transitions.entrySet()) {
            Map.Entry<String, Character> key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key.getKey() + " -- " + key.getValue() + " --> " + value);
        }
    }

    private void displayInitialState() {
        System.out.println("Initial State: " + initialState);
    }

    private void displayFinalStates() {
        System.out.println("Final States: " + finalStates);
    }

    public static void main(String[] args) {
        FiniteAutomaton fa = new FiniteAutomaton();
        try {
            fa.readFAFromFile("FAconstant.in");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String inputString = scanner.nextLine();

        boolean isAccepted = fa.accepts(inputString);
        System.out.println("The string \"" + inputString + "\" is " + (isAccepted ? "accepted" : "not accepted") + " by the FA.");
        scanner.close();


    }
}
