import java.io.IOException;
import java.util.Scanner;


public class HangmanApplication {


    public static void main(String[] args)throws IOException {
        //Garbage collection practice

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hangman. You will spell a word that I choose. If you take six attempts incorrectly, I win. If you guess correctly 4 times, you win");
        System.out.println();
        System.out.println("I have picked my word. Below is a picture. Also, the amount of guesses is represented below. Each incorrect guess I add a body part");

        System.out.println();


        boolean doYouWantToPlay = true;

        while(doYouWantToPlay){
            System.out.println();
            System.out.println("Let's play");

            Hangman game = new Hangman();

            do{
                    System.out.println(game.drawPicture());
                System.out.println();
                    System.out.println(game.getFormalCurrentGuess());
                System.out.println();
                    System.out.println(game.mysteryWord);
                    //get the guess

                System.out.println("Enter a character that you think is in the word?");

                System.out.println();
                char guess = (sc.next().toLowerCase()).charAt(0);
                System.out.println();

                //check if character has been guessed already
                while(game.guessedAlready(guess)){
                    System.out.println("Try again, you guessed that already!");

                    guess = (sc.next().toLowerCase()).charAt(0);

                }

                if(game.goodGuess(guess)){
                    System.out.println("Great guess!");

                }else{
                    System.out.println("Your guess is not in the word");

                }

            }while(!game.gameOver()); //keep playing until game is done



            System.out.println();
            System.out.println("Do you want to play? (Y/N)?");

            char response = (sc.next().toUpperCase()).charAt(0);

            doYouWantToPlay = (response == 'Y');
        }
    }
}
