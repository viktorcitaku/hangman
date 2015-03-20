package com.viktorcitaku.hangman;
//////////////// VIKTOR CITAKU /////////////////////////////////////////////////
import java.util.Scanner;
///////////////////////////////////////////////////////////////////////////////
//klasa "Test" eshte klase e cila merret me ekzekutimin e 
//programit
public class Test {
	
	private static final Scanner sc = new Scanner(System.in);//importimi i Skanerit
	private static final String i = "\n\n\t\tHANGMAN GAME version 0.0.1 beta\n-It's a game you can test yourself.";
	private static final String n = " It could have some problems, but who cares. :P\n";
	private static final String f = "If you find errors during the game, report.";
	private static final String o = " Hope you enjoy the game.\n\n";
	///////////////////////////////////////////////////////////////////////////
	//metoda "dsiplayGameInfo()" tregon se si luhet loja
	public static void displayGameInfo(String i, String n, String f, String o) {
		
		System.out.println(i+n+f+o);
	}
	///////////////////////////////////////////////////////////////////////////
	//metoda "main"
	public static void main(String[] args) {
		
		try{
			
			displayGameInfo(i, n, f, o);
			char c;
			do{
				
				try{
					Hangman h = new Hangman();
					h.print();
				}catch(StringIndexOutOfBoundsException sioobe) {
					
					System.out.println();
					System.out.println("You typed \"ENTER\" accidentally,"+
										" and caused internal error.");
					System.out.println();
				}
				System.out.print("Play again [y/n]?: ");
				String answer = sc.nextLine();
				c = answer.toLowerCase().charAt(0);
				System.out.println();
			}while(c != 'n');
		}catch(Exception ex) {
			
			System.out.println();
			System.out.println("Internal ERROR: "+ex.getMessage());
			System.out.println();
		}
	}

}////////////////////////////////////////////////////////////////////////////////
