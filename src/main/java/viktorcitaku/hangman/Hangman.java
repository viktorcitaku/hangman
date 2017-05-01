package viktorcitaku.hangman;

import java.util.Scanner;

/**
 * The main class that holds the business logic of the game.
 *
 * @author Viktor Citaku
 */
public class Hangman {

	// An instance of the class that generates the figures
	private final Hangmans h = new Hangmans();

	private final Scanner sc = new Scanner(System.in);

    private final char[] words;

	private final int[] memo;

    private String letterStar;
    private String quit;

    private int temp, win;

    /**
     * Prepares the logic for the game
     */
	public Hangman() {

        String[] hiddenWords = {"APPLE", "BANANA", "PEAR",
                "ANANAS", "CHERRY", "PLUMS",
                "BLUBERRIES", "STRAWBERRIES"};

        int randomNum = (int) (Math.random() * hiddenWords.length);

        letterStar = hiddenWords[randomNum];

		words = new char[letterStar.length()];
		
		for (int i=0; i < letterStar.length(); i++) {
			words[i] = letterStar.charAt(i);
		}
		
		memo = new int[words.length];

		for (int t = 0; t < memo.length; t++) {
			memo[t] = -1;
		}
		
		quit = "START";
		temp = letterStar.length();
		win = 0;
	}

    /**
     * Contains the algorithm to correct the words from user
     */
    private void logicForWords() {
		System.out.print(" Word: ");

		for (int i=0; i<letterStar.length(); i++) {
			
			if (memo[i] > -1)
				System.out.print(words[i]);
			else
				System.out.print("*");
		} System.out.println();
		
		if (endGame()) {
			quit = "QUIT";
			win = 1;
			return;
		}
		
		System.out.print(" Type a letter: ");

        String typedLetter = sc.nextLine();

		if (typedLetter.equalsIgnoreCase("END")) {
			quit = "QUIT";
			return;
		}

        char character = typedLetter.toUpperCase().charAt(0);
		
		for (int k = 0; k < words.length; k++) {
			if(character == words[k]) {
				memo[k] = k;
			}
		}
		
		gameOver();
	}

	private int mistake = 0;
	private int counter = 1;

    /**
     * This method will take care of user mistakes by displaying "hangmans"
     */
    private void gameOver() {

        for (int aMemo : memo) {
            if (aMemo == -1) {
                mistake += 1;
            }
        }
		
		if (temp == mistake) {
			counter += 1;
			mistake = 0;
		} else {
			temp = mistake;
			mistake = 0;
		}
		
		switch (counter) {
			case 1:
				h.printHanOne(); break;
			case 2:
				h.printHanTwo(); break;
			case 3:
				h.printHanThree(); break;
			case 4:
				h.printHanFour(); break;
			case 5:
				h.printHanFive(); break;
			case 6:
				h.printHanSix(); break;
			case 7:
				h.printHanSeven(); quit = "QUIT"; break;
		}
	}

    /**
     * Checks if the game has ended
     *
     * @return true or false if the game has ended or not
     */
    private boolean endGame() {
        int end = 0;

        for (int aMemo : memo) {
            if (aMemo != -1) {
                end += 1;
            }
        }
		
		return end == letterStar.length();
	}

	private int numCount = 0;

    /**
     * This method will output game rounds example:
     * "------ HANGMAN, ROUND:1 ----------"
     */
    private void designBorderForRoundGame() {
		int i=0;
		while (i < 6) {
			System.out.print("-");
			i++;
		}

		System.out.print(" HANGMAN, ROUND:" + (++numCount) + " ");

		i=0;

		while (i < 10) {
			System.out.print("-");
			i++;
		}System.out.println();System.out.println();
	}

    /**
     * Will notify the user when the game is over, example:
     * "------ GAME OVER ---------"
     *
     * @param win ...
     */
    private void printGameOver(int win) {
		System.out.println();

		int i=0;

		while (i < 9) {
			System.out.print("-");
			i++;
		}

		if (win == 1) {
			System.out.print(" YOU  WON ");

			i=0;

			while (i < 15) {
				System.out.print("-");
				i++;
			}System.out.println();System.out.println();
		}else {
			System.out.print(" GAME OVER ");

			i=0;

			while (i < 15) {
				System.out.print("-");
				i++;
			}System.out.println();System.out.println();
		}
	}

    /**
     * This is the entry point of the game!
     */
    void print() {
		
		do {
		
			designBorderForRoundGame();
			logicForWords();
		}while(!(quit.equals("QUIT")));
		printGameOver(win);
	}
}
