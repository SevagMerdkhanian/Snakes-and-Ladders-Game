/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 1
 * Friday, February 3, 2023
 */
package Assignment_1;
import java.util.Scanner;
/**
 * LadderAndSnake class is a simulation of an entire game of Ladders and Snakes.
 * Has attributes String[][] board and int[] player.
 * @author Sevag and Alec
 */
public class LadderAndSnake {
	//Attributes
	private String[][] board;
	private int[] player;
	/**
	 * Default Constructor sets the board with createBoard method and sets player to an array of size 2
	 */
	public LadderAndSnake() {	
		board = createBoard();
		player = new int[2];
	}
	/**
	 * Parameterized constructor sets the board with createBoard 
	 * @param p integer p to set number of players
	 */
	public LadderAndSnake(int p) {
		board = createBoard();
		if (p > 2) {
			System.out.println("Initialization was attempted for " + p + " member of players; however this "
							   + "is only expected for an extended version of the game. Value will be set to 2\n");
			player = new int[2];	
		}
		if (p < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
			System.exit(0);
		}
		else
			player = new int[p];	
	}
	/**
	 * getBoard method 
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}
	/**
	 * getPlayer method
	 * @return the player array
	 */
	public int[] getPlayer() {
		return player;
	}
	/**
	 * setBoard method sets the board to b only if b is a 10x10 array.
	 * It also checks if each element in the array is null, and in that case,
	 * sets a square to "Square squareNumer".
	 * @param b String[][] b for the board which is only used if the array is 10x10
	 */
	public void setBoard(String[][] b) {
		if (b.length==10 && b[0].length ==10)
			board = b;
		int square = 1;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == null) 
					board[i][j] = "Square " + String.format("%2s", square);
					square++;
				
			}
	}
	/**
	 * setPlayer method which. regardless of the value of p, sets player to an array of size 2
	 * @param p int[] p for the player array
	 */
	public void setPlayer(int[] p) {
		player = new int[2];
	}
	/**
	 * Copy constructor which sets the LadderAndSnake object's board to aGame's board.
	 * Player is not copied since it is always the same regardless.
	 * @param aGame LadderAndSnake object to copy
	 */
	public LadderAndSnake(LadderAndSnake aGame) {
		board = aGame.board;
	}
	/**
	 * equals method checks if the LadderAndSnake object's board is the same as aGame's board.
	 * Player is not checked for since it is always the same regardless .
	 * @param aGame LadderAndSnake object to check equality
	 * @return boolean truth value of equality
	 */
	public boolean equals(LadderAndSnake aGame) {
		return (board == aGame.board);
	}
	/**
	 * toString method simply displays the board 
	 */
	public String toString() {
		return displayBoard(board);
	}
	/**
	 * flipDice method flips a dice
	 * @return d a value from 1 to 6
	 */
	public int flipDice() {
		double r = Math.random();
		int d = (int)(r*6+1);
		return d;
	}
	/**
	 * play method is the actual core of the game
	 */
	public void play() {
		/**
		 * Determining order of play based on highest initial dice value.
		 * Displaying appropriate messages.
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("- Now deciding which player will start playing;");
		int d1 = this.flipDice();
		int d2 = this.flipDice();
		int present_player = 0;
		int count = 0;
		while (d1 == d2) {
			count++;
			d1 = this.flipDice();
			d2 = this.flipDice();
			System.out.println("- Player 1 got a dice value of " + d1);
			System.out.println("- Player 2 got a dice value of " + d2);
			if (d1==d2) 
				System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie");
		}
		if (d1 > d2) {
			System.out.println("Reached final decision on order of playing: Player 1 then Player 2. It took "
					+ count + " attempt(s) before a decision could be made\n");
			present_player = 0;
		}
		else {
			System.out.println("Reached final decision on order of playing: Player 2 then Player 1. It took "
						+ count + " attempt(s) before a decision could be made\n");
			present_player = 1;
		}
		/**
		 * Asking the player if they want the board displayed after every turn.
		 * Displaying the board after every turn is useful but creates some visual clutter.
		 */
		System.out.println("Do you want to display the board after every turn, or only at the end of the game?");
		System.out.print("Type yes and press enter if so. If not, type anything else and press enter: ");
		String display_after_turn = keyboard.nextLine().toLowerCase();
		/**
		 * Setting players' positions to 0 (outside the board)
		 */
		player[0] = 0;
		player[1] = 0;
		boolean game_continue = true;
		/**
		 * While the game continues, keep playing.
		 * Also, asks the current player to play their turn.
		 */
		while (game_continue) {
			System.out.println("Player " + (present_player+1) + ", enter any combination of characters and press enter to roll the dice.");
			System.out.print("(You could also just press enter): ");
			keyboard.nextLine();
			int dice = flipDice();
			player[present_player] += dice;
			/**
			 * Switch statement checks for if a player reaches the bottom of a ladder or 
			 * the head of a snake and moves them to the appropriate square. 
			 * Also checks for if a player reaches square 100, in which case they win (game is over).
			 * Default case simply moves a player to the right square normally.
			 * Displays appropriate messages.
			 */
			switch (player[present_player]) {
				case 1: 
					player[present_player] = 38; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 4: 
					player[present_player] = 14; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 9: 
					player[present_player] = 31; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 16: 
					player[present_player] = 6; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 21: 
					player[present_player] = 42; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 28: 
					player[present_player] = 84; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 36: 
					player[present_player] = 44; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 48: 
					player[present_player] = 30; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 51: 
					player[present_player] = 67; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 62: 
					player[present_player] = 19; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 64: 
					player[present_player] = 60; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 71: 
					player[present_player] = 91; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					break;
				case 80: 
					player[present_player] = 100; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They climb a ladder and are now in square " + player[present_player]);
					System.out.println("Player " + (present_player+1) + " wins!");
					game_continue = false;
					break;
				case 93: 
					player[present_player] = 68; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 95: 
					player[present_player] = 24; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 97: 
					player[present_player] = 76; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 98: 
					player[present_player] = 78; 
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + ". They slither down a snake and are now in square " + player[present_player]);
					break;
				case 100:
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + " and is now in square " + player[present_player]);
					System.out.println("Player " + (present_player+1) + " wins!");
					game_continue = false;
					break;
				default:
					System.out.println("Player " + (present_player+1) + " got a dice value of " + dice + " and is now in square " + player[present_player]);
					break;
			}
			/**
			 * Checks for if a player reaches a square beyond 100 and puts them in the appropriate square based on that.
			 * Player is put into the square 100 - the extra squares they went beyond.
			 * Displays appropriate message.
			 */
			if (player[present_player] > 100) {
				player[present_player] = 100 - (player[present_player] - 100);
				System.out.println("Player " + (present_player+1) + " moves to square " + player[present_player] + 
								   " since they moved to a square that exceeds the limit of the board");
			}
			/**
			 * Checks for if a player reaches the square of the other player, then kicks them out.
			 * The previous player is kicked into square 0.
			 * Displays appropriate message.
			 */
			if (player[present_player] == player[(present_player + 1) % 2]) {
				player[(present_player + 1) % 2] = 0;
				System.out.println("Player " + (present_player+1) + " kicks player " + (((present_player + 1) % 2)+1) + " "
						+ "from square " + player[present_player] +". Player " + (((present_player + 1) % 2)+1) + " restarts at square 0!");
			
			}
			/**
			 * The board is updated every time a turn is completed. 
			 * Have to "reset" it each time or else previous positions are kept as well.
			 */
			board = createBoard();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (board[i][j].equals("Square " + String.format("%2s", player[present_player])))
						board[i][j] = "PLAYER " + String.format("%2s", (present_player+1));
					if (board[i][j].equals("Square " + String.format("%2s", player[(present_player + 1) % 2])))
						board[i][j] = "PLAYER " + String.format("%2s", (((present_player + 1) % 2)+1));
				}
			}
			/**
			 * if the game is still going on, continues playing the game
			 */
			if (game_continue ==true) {
				/**
				 * Displaying the board after each turn if the user chose that option
				 * Also checks if either player 1 or player 2 are in square 0
				 */
				if (display_after_turn.equals("yes")) {
					System.out.println("Current Board: ");
					if (player[0] ==0)
						System.out.println("\nPlayer 1 is in square 0 (outside the board)");
					if (player[1] ==0)
						System.out.println("\nPlayer 2 is in square 0 (outside the board)");
					displayBoard(board);
				}
				System.out.println("Game not over: flipping again.\n");
				/**
				 * Setting the new present player to the other player for each turn
				 */
				present_player = (present_player + 1) % 2;
			}

		}
		/**
		 * Displaying the board at the end of the game
		 * Also checks if either player 1 or player 2 are in square 0
		 */
		System.out.println("Board at the end of the game: ");
		if (player[0] ==0)
			System.out.println("\nPlayer 1 is in square 0 (outside the board)");
		if (player[1] ==0)
			System.out.println("\nPlayer 2 is in square 0 (outside the board)");
		displayBoard(board);
		keyboard.close();
		
	}
	/**
	 * Displays the board in a nice format
	 * @param b String[][] b to be displayed 
	 * @return String "" (not important)
	 */
	public String displayBoard(String[][] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.println("\n");
			for (int j = 0; j < b.length; j++) {
				System.out.print("[" + b[i][j] + "]");
			}
		}
		return("");
	}
	/**
	 * Creates the board with each element being "Square NumberSquare" as in the picture of the board shown in the assignment
	 * @return String[][] board
	 */
	public static String[][] createBoard() {
			String[][] b = new String[10][10];
			int square_even_row = 100;
			for (int row = 0; row < 10; row++)
				for (int col = 0; col < 10; col++) {
					if (row % 2 ==0)
						b[row][col] = "Square " + String.format("%2s", square_even_row);
					square_even_row--;
				}
			int square_odd_row = 1;
			for (int row = 9; row >= 0; row--)
				for (int col = 0; col < 10; col++) {
					if (row % 2 !=0)
						b[row][col] = "Square " + String.format("%2s", square_odd_row);
					square_odd_row++;
				}
			return b;
	}
	
		
}


	
	
	


