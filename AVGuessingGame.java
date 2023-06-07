//Aaron Vadnais
//CS 145
//Lab 1: Guessing Game
//This is an edited version of the guessing game program from CS&141
//The user is asked to guess a number between 1 and 100 and when finished, displays their results
//See end of code(118) for revisions and more comments

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
public class AVGuessingGame {
    public static void main(String[] args) throws FileNotFoundException { //main method
        Scanner input = new Scanner(System.in);
        int ggArray[] = {9999,0,0,1,0};//array with game data stored(this is for your end game results)
        String playAgain = "";

        String s = gameMenu(playAgain); //call to menu method and returns user input string

        while (s.startsWith("y")) {
            ggArray[4] = guessingSequence();
            System.out.println("\nHow about another game? (y/n)");
            s = input.next();
            ggArray[2] += ggArray[4];
            ggArray[1]++;
            if (ggArray[4] < ggArray[0]) {
                ggArray[0] = ggArray[4];
            }
        }
        switch(s){
            case "n":
                System.out.println("Here are your results");
                gameResults(ggArray);//call to final method with results
                break;
            default:
                System.out.println("Please enter a valid command");
        }
    }// end of main method

    public static String gameMenu(String playAgain){//this method contains the main menu and user input response
        Scanner input = new Scanner(System.in);

        System.out.println("Hello");
        System.out.println("Let's play a game:");
        System.out.println("\nYou have to try to guess what number I'm thinking of.");
        System.out.println("Guess any number between 1 and 100 and I will tell you if you are correct.");
        System.out.println("Do you accept?: y/n?");
        playAgain = input.next();
        playAgain = playAgain.toLowerCase();

        return playAgain;
    }
    public static int guessingSequence(){// method that contains the "guessing game" code. Loops as many times as user wants
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int numTries = 1;
        int randomNumber = rand.nextInt(100);

        System.out.println("Enter a value between 0 and 100: ");
        int userGuess = input.nextInt();

        while(userGuess != randomNumber){//actual code for the guessing game. user cannot break out until # is guessed
            if (userGuess > randomNumber && userGuess < 101 && userGuess > -1){
                System.out.println("guess lower");
                userGuess = input.nextInt();
                numTries++;
            }else if (userGuess < randomNumber && userGuess < 101 && userGuess > -1){
                System.out.println("guess higher");
                userGuess = input.nextInt();
                numTries++;
            }else{
                System.out.println("please follow the rules...Guess between 0 and 100");
                userGuess = input.nextInt();
                numTries += 0;
            }

        }
        if (numTries == 1){
            System.out.println("Nice job, you guessed it on your first try!");
            return numTries;
        }else{
            System.out.println("You guessed correct in: " + numTries + " guesses");
            return numTries;
        }

    }//end of guessingSequence method


    public static void gameResults(int ggArray[]) throws FileNotFoundException { //final results method displays game statistics
       // new File(outputFile)

        Scanner acsiiArt = new Scanner (new File("asciiartsmiley.txt")); //imports cool artwork! Keep with game file

        if(ggArray[1] == 0){
            System.out.println("...you didn't play :(");
        }else if(ggArray[1]>=1) {

            System.out.println("Total games played: " + ggArray[1]);
            System.out.println("Total guesses: " + ggArray[4]);

            double z = ggArray[4] * 1.0 / ggArray[1];

            System.out.println("Average guesses per game: " + z);
            System.out.println("Best game score: " + ggArray[0]);
            System.out.println();

            while (acsiiArt.hasNextLine()) {
                String text = acsiiArt.nextLine();
                System.out.println(text);
            }
        }
    }//end of gameResults method

}// end of class


//added string array for game result variables, added ascii art, added switch case in menu
//added a method for the initial game menu.
//if user guesses above 100 or below 0, they are prompted not to, and their score is not affected.
//ascii art source http://loveascii.com/smilies.html
//IDE used: IntelliJ