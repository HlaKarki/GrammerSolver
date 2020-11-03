import java.util.*;

/**
 *This program will read an input file, and using grammar in Backus-Naur form
 * will randomly generate elements of the grammar.
 *
 * @author Lucas D. Dahl
 * @version 10/19/20
 */
public class GrammarSolver {

    // **************************** Fields ****************************
    private Map<String, List<String>> grammarMap = new TreeMap<>();

    // ************************** Constructors ************************

    /**
     *  This is the default constructor that takes in a list of from
     *  a file that has non-terminals and terminals.
     *
     * @param rules is the list of rules to use to construct a sentence.
     */
    public GrammarSolver(List<String> rules) {

        // Properties
        String nonTerminal = null, list = "";

        // Make sure the list is not null or empty
        if(rules.isEmpty()) {
            throw new IllegalArgumentException("The rule list must contain at least one rule.");
        }

        // Create the string to scan.
        for(String word: rules) {
            list += word + "::=";
        }

        // Setup the scanner.
        Scanner lineScanner = new Scanner(list);

        // Set the delimiter
        lineScanner.useDelimiter("::=");

        // Create the map of non-terminals and terminals.
        while(lineScanner.hasNext()) {

            ArrayList<String> terminal = new ArrayList<>();

            // Get the terminal and the non-terminal
            nonTerminal = lineScanner.next().trim();

            // Setup the terminals to scan through an breakup.
            String terminalLine = lineScanner.next();
            Scanner termScan = new Scanner(terminalLine);

            // Set the delimiter
            termScan.useDelimiter("\\|");

            // Divide by each terminal.
            while(termScan.hasNext()) {
                String word = termScan.next().trim();
                terminal.add(word.trim());
            }

            // Add them to the map.
            if(!grammarMap.containsKey(nonTerminal)) {
                grammarMap.put(nonTerminal, terminal);
            } else {
                throw new IllegalArgumentException("Contains more than one rule set for a non-terminal.");
            }
        }

    }

    // **************************** Methods ***************************


    // **************** Public ****************
    /**
     *This method will return true if the map contains the current
     * symbol as a non-terminal, and false if it does not.
     *
     * @param symbol is the string symbol to check.
     * @return will return true or false if the map contains the key.
     */
    public Boolean contains(String symbol) {

        // Make sure the symbol is not empty or null.
        if(symbol.isEmpty()) {
            throw new IllegalArgumentException("The symbol does not exist.");
        }

        return grammarMap.containsKey(symbol);
    }

    /**
     * This method will return a set containing all the string
     * non-terminal strings from teh file.
     *
     * @return a set of Strings that are non-terminal symbols.
     */
    public Set<String> getSymbols() {

        // Properties
        Set<String> grammarSet = new TreeSet<>();

        for(String rule: grammarMap.keySet()) {
            grammarSet.add(rule);
        }

        // Return the non-terminal symbols from the file.
        return grammarSet;
    }

    /**
     *This method will take in a non-terminal symbol to start a recursive
     * generated string.
     *
     * @param symbol is the symbol to start the generation from,
     * @return the fine string to display to the user once the base case is met.
     */
    public String generate(String symbol) {

        // Properties
        String grammar = "";
        Random rand = new Random();
        ArrayList<String> terminalSet = new ArrayList<>();

        if(!contains(symbol)) {
            return symbol;
        } else {

            int randNum = rand.nextInt(grammarMap.get(symbol).size());
            String line = grammarMap.get(symbol).get(randNum).trim();

            Scanner termScan = new Scanner(line.trim());

            while(termScan.hasNext()) {

                String word = termScan.next().trim();
                terminalSet.add(word);
            }

            // Create the sentence recursively.
            for(String terminal: terminalSet) {
                grammar += generate(terminal) + " ";
            }
        }

        return grammar.trim();
    }

}