import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    String mysteryWord;

    StringBuilder currentGuess;

    ArrayList<Character> previousGuesses = new ArrayList<>();

    int maxAttempts =6;
    int currentAttempt = 0;

    // take words from file off the internet and put in a list as potential hangman words

    ArrayList<String> dictionary = new ArrayList<>();

    private static FileReader fileReader; //get file for us
    private static BufferedReader bufferedReader; // iterate through file

    public Hangman() throws IOException{ // what if our file is not there

        //initialize dictionary word list

        initializeStream();
        mysteryWord = pickWord();
        currentGuess = initializeCurrentGuess();

    }
    public String getFormalCurrentGuess() {
        return "Current Guess: " + currentGuess.toString();
    }
    public void initializeStream() throws IOException{

        try{
            File inFile = new File("dictionary");
            fileReader = new FileReader(inFile);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();

            while(currentLine !=null){ //while we are not at the end of the file
                dictionary.add(currentLine); //create dictionary of all words from file
                currentLine = bufferedReader.readLine(); //allows us to iterate through lines
            }
            bufferedReader.close();
        } catch(IOException e){

            System.out.println("Could not stream file");

        }

    }

    public String pickWord(){
        Random rand = new Random();

        int index = Math.abs(rand.nextInt()) % dictionary.size();

        return dictionary.get(index);

    }

    public StringBuilder initializeCurrentGuess(){

        StringBuilder current = new StringBuilder();

        for(int i =0; i<mysteryWord.length()*2; i++){ //we want the drawing to be twice as long as word due to spaces
            if(i%2 ==0){

                current.append("_");
            }
            current.append(" ");
            }
        return current;
    }

    public boolean gameOver(){
        if(didWeWin()){
            System.out.println();
            System.out.println("Congrats! You Won!");

            return true;

        }
        else if(didWeLose()){
            System.out.println();
            System.out.println("Sorry, you lose! The word was "+mysteryWord);
            return true;
        }
        return false;
    }

    public boolean didWeLose() {

        return currentAttempt >= maxAttempts;
    }

    public boolean didWeWin() {
        String guess = getCondensedCurrentGuess();

        return guess.equals(mysteryWord);
    }

    public String getCondensedCurrentGuess() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");

    }

    public boolean guessedAlready(char guess){
        return previousGuesses.contains(guess);
    }

    public boolean goodGuess(char guess){

        boolean isItAGoodGuess = false;

        for(int ch = 0; ch<mysteryWord.length(); ch++){
            if(mysteryWord.charAt(ch)==guess){
                currentGuess.setCharAt(ch*2, guess); //can only do this with stringbuilders
                isItAGoodGuess = true;
                previousGuesses.add(guess);
            }
            if(!isItAGoodGuess){
                currentAttempt++; //if not a good guess, update attempt number
            }
        }
        return isItAGoodGuess;
    }



    public String drawPicture(){
        switch(currentAttempt){

            case 0:
                return noPersonDraw();

            case 1:
                return headDraw();

            case 2:
                return torsoDraw();

            case 3:
                return larmDraw();

            case 4:
                return rarmDraw();
            case 5:
                return llegDraw();
            case 6:
                return rlegDraw();
            default:
                return rlegDraw();
        }

    }

    private String rlegDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }

    private String llegDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       / \n" +
                "|\n" +
                "|\n";
    }

    private String rarmDraw() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String torsoDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|        | \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String larmDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / |  \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String headDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       \n"+
                "|        \n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String noPersonDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        \n" +
                "|       \n"+
                "|        \n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }
}
