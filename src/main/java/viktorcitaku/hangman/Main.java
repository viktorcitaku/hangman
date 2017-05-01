package viktorcitaku.hangman;

import java.util.Scanner;

/**
 * Main class with main method which will execute main logic!
 *
 * @author Viktor Citaku
 */
public class Main {
	
	private static final Scanner sc = new Scanner(System.in);

	private static final String i = "\n\n\t\tHANGMAN GAME version 0.0.1 beta\n-It's a game you can test yourself.";

	private static final String n = " It could have some problems, but who cares. :P\n";

	private static final String f = "If you find errors during the game, report.";

	private static final String o = " Hope you enjoy the game.\n\n";

	/**
	 * Method which will show to use how the game is played!
	 *
	 * @param i Piece of string used to build information string
	 * @param n Piece of string used to build information string
	 * @param f Piece of string used to build information string
	 * @param o Piece of string used to build information string
	 */
	private static void displayGameInfo(String i, String n, String f, String o) {
		System.out.println(i+n+f+o);
	}

	public static void main(String[] args) {
		try {
			displayGameInfo(i, n, f, o);
			char c;
			do {
				try {
					Hangman h = new Hangman();
					h.print();
				} catch (StringIndexOutOfBoundsException sioobe) {
					System.out.println();
					System.out.println("You typed \"ENTER\" accidentally,"+
										" and caused internal error.");
					System.out.println();
				}
				System.out.print("Play again [y/n]?: ");
				String answer = sc.nextLine();
				c = answer.toLowerCase().charAt(0);
				System.out.println();
			} while(c != 'n');
		} catch (Exception ex) {
			System.out.println();
			System.out.println("Internal ERROR: "+ex.getMessage());
			System.out.println();
		}
	}
}
