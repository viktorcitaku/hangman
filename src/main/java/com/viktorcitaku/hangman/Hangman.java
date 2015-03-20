package com.viktorcitaku.hangman;
//////////////// VIKTOR CITAKU //////////////////////////////////////////////////////////////
import java.util.*;
/////////////////////////////////////////////////////////////////////////////////////////////
//klasa "Hangman" eshte klasa kryesore
public class Hangman {
	
	private final Hangmans h = new Hangmans();//instanca e krijuar "Hangmans"
	private final Scanner sc = new Scanner(System.in);//importimi i Skanerit
	//fjalet e fshehura te ruajtura ne arrejin "hiddenWords"
	private final String[] hiddenWords = {"APPLE", "BANANA", "PEAR",
									"ANANAS", "CHERRY", "PLUMS",
									"BLUBERRIES", "STRAWBERRIES"};
	private final char[] word;//arrej i karaktereve
	private final int[] memo;//arrej memorues
	int randomNum = (int)(Math.random()*hiddenWords.length);//gjenerues i rendomt i numrave
        @SuppressWarnings("FieldMayBeFinal")
	private String typedLetter, letterStar, quit;//disa variabla te tipit "String"
	private char character;//variabla e tipit "character"
	int temp, win;//variabla e tipit "int"
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//konstruktimi i Konstruktorit "Hangman()" 
	public Hangman() { 
		
		letterStar = hiddenWords[randomNum];
		word = new char[letterStar.length()];
		
		for(int i=0; i<letterStar.length(); i++) {
			
			word[i] = letterStar.charAt(i);
		}
		
		memo = new int[word.length];
		

		for(int t=0; t<memo.length; t++) {
			
			memo[t] = -1;
		}
		
		quit = "START";
		temp = letterStar.length();
		win = 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "logicForWords()" eshte nje algoritem per korigjimin
	//e fjaleve, me "input" nga shfryetezuesi, etj.
        @SuppressWarnings("UnnecessaryContinue")
	public void logicForWords() { 
		
		System.out.print(" Word: ");
		for(int i=0; i<letterStar.length(); i++) {
			
			if(memo[i] > -1)
				System.out.print(word[i]);
			else
				System.out.print("*");
		}System.out.println();
		
		if(endGame() == true) {
			quit = "QUIT";
			win = 1;
			return;
		}
		
		System.out.print(" Type a letter: ");
		typedLetter = sc.nextLine();
		if(typedLetter.equalsIgnoreCase("END")) {
			
			quit = "QUIT";
			return;
		}
		
		character = typedLetter.toUpperCase().charAt(0);
		
		for(int k=0; k<word.length; k++) {
			
			if(character == word[k]) {
				
				memo[k] = k;
				continue;
			}
		}
		
		gameOver();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "gameOver()" eshte metod e cila merret me 
	//korigjimin e gabimeve dhe shfaqjen e hangman-ve
	int mistake = 0;
	int counter = 1;
        @SuppressWarnings("UnnecessaryContinue")
	public void gameOver() {
		
		for(int c=0; c<memo.length; c++) {
			
			if(memo[c] == -1) {
				mistake += 1;
				continue;
			}
		}
		
		if(temp == mistake) {
			
			counter += 1;
			mistake = 0;
		}else{
			
			temp = mistake;
			mistake = 0;
		}
		
		switch(counter) {
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
	////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "endGame()" merret me shikimin, se kur fjala 
	//eshte plotesuar e gjitha
	int end = 0;
        @SuppressWarnings("UnnecessaryContinue")
	public boolean endGame() {
		
		end = 0;
		for(int c=0; c<memo.length; c++) {
			
			if(memo[c] != -1) {
				end += 1;
				continue;
			}
		}
		
		return end == letterStar.length();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "designBorderForRoundGame()" merret me disajnin e
	//lojes psh.=> "------ HANGMAN, ROUND:1 ----------"
	int numCount = 0;
	public void designBorderForRoundGame() {
		
		int i=0;
		while(i<6) {
			
			System.out.print("-");
			i++;
		}
		System.out.print(" HANGMAN, ROUND:"+(++numCount)+" ");
		i=0;
		while(i<10) {
			
			System.out.print("-");
			i++;
		}System.out.println();System.out.println();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "printGameOver()" merret me njoftimin e 
	//shryetezuesit qe loja ka perfundu
	//psh.=> "------ GAME OVER ---------"
	public void printGameOver(int win) {
		
		System.out.println();
		int i=0;
		while(i<9) {
			
			System.out.print("-");
			i++;
		}
		if(win == 1) {
			
			System.out.print(" YOU  WON ");
			i=0;
			while(i<15) {
			
				System.out.print("-");
				i++;
			}System.out.println();System.out.println();
		}else {
		
			System.out.print(" GAME OVER ");
			i=0;
			while(i<15) {
			
				System.out.print("-");
				i++;
			}System.out.println();System.out.println();
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//metoda "print()" ka per detyr te shfaq lojen ne CLI
	public void print() {
		
		do {
		
			designBorderForRoundGame();
			logicForWords();
		}while(!(quit.equals("QUIT")));
		printGameOver(win);
	}
}//////////////////////////////////////////////////////////////////////////////////////////////////////
