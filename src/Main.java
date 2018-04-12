import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Vector;


/*
Movie guessing game!
    Rules:
    There are 25 movies in this current catalog.
    Try and guess which movie it is, each guess will either
    reveal one letter (or multiple) if you guess them right
    if you don't, you will lose a turn. You have 10 tries to guess
    the movie title. Good Luck!!

    Side Note!
        You can either enter one letter or the entire movie title
        All the other crud will be considered gibberish!
 */
public class Main {

    public static void main(String args[]) throws FileNotFoundException{
        // Read in file
        // Break file down by line
        File moviefile = new File("movies.txt");
        Scanner movies = new Scanner(moviefile);

       // keep false until the user had one
        boolean haswon = false;

        // Get the total number of movies and add them to an array
        int numberofmovies = -1;
        Vector movielist = new Vector();
        while(movies.hasNextLine()){
            numberofmovies++;
            String line = movies.nextLine();
            movielist.add(line);
        }

        // Randomly choose a number between 1 and number of lines
        int randomnumber = (int) (Math.random() * numberofmovies + 1);
        //System.out.println(movielist.get(randomnumber));

        // The name of the movie they should be guessing
        String movieanswer = (String) movielist.get(randomnumber);

        // break movie name into char array
        char [] movieanswerarray = movieanswer.toCharArray();

        // character array to hold the already guessed answers
        Vector guessedchars = new Vector();
        guessedchars.add(' ');

        // Take userinput
        Scanner scanner = new Scanner(System.in);

        // create a string of "_" so the user knows the length
        String hangman = "";
        for(int i = 0; i <= movieanswer.length()-1; i++){
            if(movieanswerarray[i] == ' '){
                hangman+=" ";
            }
            else{
                hangman+="_";
            }
        }

        // break hangman variable into an array of chars
        char[] finalanswer = hangman.toCharArray();

        // Give the user 10 guesses
        for(int i = 10; i > 0; i--){
            System.out.println("You are guessing " + new String(finalanswer));
            System.out.println("You have " + i + " guess(es) left" );
            System.out.print("Guess a letter: ");
            String userinput = scanner.nextLine();

            // Compare that input to the characters in the string chosen
            // If in the string reveal the letters
            if(movieanswer.indexOf(userinput) == - 1){
                System.out.println("Your input is incorrect, keep going..");
                System.out.println(" ");

                if(guessedchars.contains(userinput.charAt(0))){
                    System.out.println("You've already guessed this, or entered a space try again..");
                    i++;
                }
            }

            else{
                System.out.println("Your input is correct!");
                System.out.println(" ");

                for(int j = 0; j < movieanswerarray.length; j++){
                    for(int k = 0; k < userinput.length(); k++){
                        if(userinput.charAt(k) == movieanswerarray[j]){
                            finalanswer[j] = userinput.charAt(k);
                        }
                    }
                }

                if(guessedchars.contains(userinput.charAt(0))){
                    System.out.println("You've already guessed this, try again..");
                    i++;
                }

            }

            // add the already guessed character to the array
            guessedchars.add(userinput.charAt(0));

            if(new String(finalanswer).contains("_") == false){
                haswon = true;
                break;
            }
        }

        // If they guess correctly they win
        // If they guess wrong they lose
        if(haswon){
            System.out.println("Congrats! You win!");
        }
        else{
            System.out.println("You Lose, Try again..");
            System.out.println("The answer was "+ movieanswer);
        }
    }
}
