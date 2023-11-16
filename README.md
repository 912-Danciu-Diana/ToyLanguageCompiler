# FLCD
Documentation for FiniteAutomaton Class
Overview
The FiniteAutomaton class in Java is designed to model and work with finite automata (FAs). It supports defining states, alphabet, transitions, initial state, and final states. This class also provides functionality to read FA configurations from a file, display the FA's elements, and test whether specific strings are accepted by the automaton.

Class Structure
Fields:

states: A set of strings representing the states of the FA.
alphabet: A set of characters representing the FA's alphabet.
transitions: A map where each key is a pair of a state and a character (symbol), and each value is the resulting state after the transition.
initialState: A string representing the initial state of the FA.
finalStates: A set of strings representing the final (accepting) states of the FA.
Constructor:

FiniteAutomaton(): Initializes a new instance of the FiniteAutomaton class with empty sets for states, alphabet, final states, and an empty map for transitions.
Methods:

addState(String state): Adds a state to the FA.
setAlphabet(Set<Character> alphabet): Sets the alphabet for the FA.
addTransition(String sourceState, char symbol, String targetState): Adds a transition to the FA.
setInitialState(String state): Sets the initial state of the FA.
addFinalState(String state): Adds a final state to the FA.
accepts(String input): Checks if the FA accepts the given string.
readFAFromFile(String fileName): Reads FA configuration from a file.
displayMenu(): Displays a menu to interact with the FA.
displayStates(): Displays the states of the FA.
displayAlphabet(): Displays the alphabet of the FA.
displayTransitions(): Displays the transitions of the FA.
displayInitialState(): Displays the initial state of the FA.
displayFinalStates(): Displays the final states of the FA.

Usage found in main class:
Creating an FA
Reading FA from a File
Testing String Acceptance

File Format (FA.in)
The FA configuration file should follow a specific format:

Initial state: Prefixed with "I.S ->".
Final states: Prefixed with "F.S ->".
States: Prefixed with "S.S ->".
Alphabet: Prefixed with "Alph ->".
Transitions: Prefixed with "T ->".

