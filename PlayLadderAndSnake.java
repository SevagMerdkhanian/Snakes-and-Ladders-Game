/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 1
 * Friday, February 3, 2023
 */
package Assignment_1;
import java.util.Scanner;
public class PlayLadderAndSnake {

	public static void main(String[] args) {
		/**
		 * Displaying welcome message
		 */
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Welcome to Sevag and Alec's Ladder and Snake Game");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++\n");
		Scanner keyboard = new Scanner(System.in);
		/**
		 * Prompting and reading user input for number of players
		 */
        System.out.println("Enter the number of players who wish to play: ");
        int number_players = keyboard.nextInt();
        System.out.println("");
        /**
         * Creating a LadderAndSnake game with number_players input from the user
         */
        LadderAndSnake game = new LadderAndSnake(number_players);
        /**
         * Playing game
         */
        game.play();
        /**
         * Displaying closing message
         */
        System.out.println("");
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Thank you for playing Sevag and Alec's Ladder and Snake Game");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        keyboard.close();

	}

}
