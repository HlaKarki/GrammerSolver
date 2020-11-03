import java.util.*; //for Scanner, List, Set, Map and Random

/**
 * This program reads an input file with a grammar in Backus-Naur Form and allows the user to randomly generate elements of the grammar.
 * This program makes heavy use of recursion to implement the core of your algorithm.
 *
 * @author Hla Htun
 * @version October 26 2020
 */
public class GrammarSolver {

    private Map<String, List<String>> map = new TreeMap<>();

    /**
     * This method will intake the set of rules from a selected file and clean out the rules inside of it and store it properly into a map
     * @param rules This is the set of rules that are present in the file selected by the user
     */
    public GrammarSolver(List<String> rules) {

        if (rules.isEmpty()) {
            throw new IllegalArgumentException(); //throw an exception if the passes list of rules is empty/null
        }

        String nonTerminal;

        for(String x: rules){ //for each word in the list named rules

            //System.out.println(x); //debug

            List<String> terminal = new ArrayList<>();

            Scanner scan1 = new Scanner(x); //set up first scanner
            scan1.useDelimiter("::="); //separate the non-terminal name and terminal name using ::=

            nonTerminal = scan1.next().trim(); //store the non-terminal name after trimming it
            String temporaryHolder = scan1.next().trim(); //store the string after ::= to a temporary String holder after trimming it

            Scanner scan2 = new Scanner(temporaryHolder); //set up second scanner
            scan2.useDelimiter("\\|"); //use the second scanner to separate the terminal names using |

            while(scan2.hasNext()){

                String scanWord = scan2.next().trim(); //put the scanned word from the second scanner to String scanWord
                terminal.add(scanWord.trim()); //add scanWord to the terminal list

            }

            if(!map.containsKey(nonTerminal)){ //this is to make sure that there are only unique terminal names

                map.put(nonTerminal,new ArrayList<String>()); //before putting everything into the map, first set it up by putting every non terminal word with an empty list as its value
                map.get(nonTerminal).addAll(terminal); //then ask for the values of EACH non-terminal name and add the list of rules related to it to the List<String>()

            }
            else{
                throw new IllegalArgumentException(); //throw an exception if a terminal name is seen more than once
            }

        }
        //System.out.println(map); //debugging
    }

    /**
     * This method will check if the input by the user matches any of the key values in the map
     * @param symbol This is the symbol from a particular text file which will be passed by the user
     * @return This will return a boolean value - true if the symbol is found in the map and false otherwise
     */
    public boolean contains(String symbol){
        symbol = symbol.trim(); //trim the symbol first without doing anything else to avoid potential errors

        if(symbol.length()==0){
            throw new IllegalArgumentException(); //if the symbol is empty/null, throw an exception
        }
        else{
            if(map.containsKey(symbol)){
                return true; //return true if the symbol is found in the keys
            }
            else
                return false; //otherwise return false
        }

    }

    /**
     * This method will return a Set of String which contains all the rules that corresponds to the non-terminal name a.k.a symbol
     * @return This will return the set of rules related to the non-terminal name
     */
    public Set<String> getSymbols(){
        Set<String> sortedSet = new TreeSet<>();

        for(String x: map.keySet()){
            sortedSet.add(x); //since the map's value is using a list and a set is required to return, all the elements are being added to the new set
        }

        return sortedSet; //the set gets returned here
    }

    /**
     * This method will generate words that relate to the symbol, at random, and returns it as a String
     * @param symbol This is initially the non-terminal value passed by the user
     * @return This will return a String after having gone through all the occurrence in the rules
     */
    public String generate(String symbol){

        String word = "";
        Random rand = new Random();

        if(symbol.length()==0){
            throw new IllegalArgumentException(); //if the parameter is empty/null throw an exception
        }
        else{

            List<String> valuesHolder = new ArrayList<>();

            if(!map.containsKey(symbol)){ //if the symbol is not found in the map just return it
                //System.out.println("In not contains "+symbol); //debug
                return symbol;
            }

            else{
                List<String> tempList = new ArrayList<>();
                tempList.addAll(map.get(symbol)); //add all of the rules of that symbol to the new list

                //System.out.println(tempList); //debug

                int randInt = rand.nextInt(tempList.size()); //store a random number within the range of 0-(size of that new list)
                String values = tempList.get(randInt); //use the random number as an index and get the rule in that index, then store it in String values

                Scanner scan1 = new Scanner(values.trim()); //set up the first scanner
                scan1.useDelimiter(" "); //separate the string using spaces " "

                while(scan1.hasNext()){

                    String tempWord = scan1.next().trim(); //add the scanned word to String tempWord after trimming
                    //int check = 0;
                    boolean check = true;
                    //System.out.print(tempWord+","); //debug
                    Scanner scan2 = new Scanner(tempWord); //set up the second scanner with the default delimiter so it separates the spaces out
                    while(scan2.hasNext()){
                        //check=1;
                        check = false; //this check will make sure that the same word is not being added to the list again later

                        String tempWord2 = scan2.next().trim(); //add the scanned word to tempWord2 after trimming

                        if(!tempWord2.isEmpty()){
                            valuesHolder.add(tempWord2); //as long as the word is not empty(just an extra precaution), add it to the list valuesHolder
                        }

                    }

                    if(!tempWord.isEmpty() && check!=false){

                        valuesHolder.add(tempWord); //as long as the scanned word is not empty and the boolean value is true, add the word to the list valuesHolder
                    }

                }

                for(String x: valuesHolder){

                    word += generate(x) + " "; //for each word in the list, concatenate it to the String word and return it
                }
            }
        }

        return word.trim(); //return it after trimming the String word
        //return ""; //debug
    }
}


