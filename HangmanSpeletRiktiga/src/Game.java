import java.util.Scanner;
import java.util.*;

/* Håller ordning på spelet. showGame() ska skriva ut spelstatusen (se ovan). update(String
alpha) tar en in en bokstav och updaterar på lämpligt sätt spelet.
 */
public class Game
{
	private String[] wordsToPlayWith = {"SCHEISSE", "ARSCH", "PIMMEL", "RÖVHATT", "ASSHAT", "FUCK", "KUK", "LISA", "KILLIAN", "PATRIK", "JAMES", "DEVRAN", "BILL"}; //example
	private String wordForGame;
	private char[] fillInWord;
	private char[] wordToGuess;
	private char[] wrongCharacters = {0,0,0,0,0,0,0,0,0,0};
	int wrongCounter;
	private boolean isFound = false;
	private String playerName;
	
	public Game(){
		this.wordForGame = "";
	}
	
	//Obligatorisk metod
	public void showGame()
	{
		System.out.println();
		System.out.println("Hej " + this.playerName+ "! Jag tänker på ett ord som har: " +  this.wordForGame.length() + " bokstäver");
		System.out.println("Så här långt har du gissat: " + this.getFillInWordAsString());
		System.out.println("Du har gissat på följande bokstäver: "+ this.getWrongCharacters());
		System.out.println("Hängmätare: " + this.getWrongCount());
		System.out.println("Vilken bokstav vill du gissa på?");
	}
	
	/**
	 * this method starts the game and handles input
	 */
	public void update( String alpha)
	{
		alpha = alpha.toUpperCase();
		char input;
		
		if(alpha.length()>1)
		{
			System.out.println("Endast en bokstav i taget!");
			return;
		}
		input = Character.toUpperCase(alpha.charAt(0));
		
		if(!Character.isLetter(input))
		{
			System.out.println("Ange en bokstav: ");
			return;
		}
		if(this.wordForGame.contains(alpha))
		{
			for(int i = 0; i< this.wordForGame.length(); i++)
			{
				if (input == this.wordToGuess[i])
				{
					this.fillInWord[i] = input;
				}
			}
		}else
		{
			manageWrongCharacters(input);
			this.wrongCounter++;
		}
		if(this.wrongCounter == 10)
		{
			System.out.println("Du förlorade... Din dumma jävel!");
			System.out.println("Ditt ord var: " + wordForGame);
			System.out.println();
			this.wrongCharacters = new char[10];
			this.wrongCounter = 0;
			this.isFound = true;
		}
		if(Arrays.equals(this.fillInWord, this.wordToGuess))
		{
			System.out.println("Du vann!");
			System.out.println("Ditt ord var: " + wordForGame);
			System.out.println();
			this.wrongCharacters = new char[10];
			this.wrongCounter = 0;
			this.isFound = true;
			//update Players statistics
		}
	}
	
	/**
	 * method to generate a random word from the array
	 */
	public void selectGameWord()
	{
		Random rand = new Random();
		int n = rand.nextInt(wordsToPlayWith.length);
		this.wordForGame = wordsToPlayWith[n];
		
		this.wordToGuess = new char[this.wordForGame.length()];
		
		for (int i = 0; i < this.wordForGame.length(); i++)
		{
			this.wordToGuess[i] = this.wordForGame.charAt(i);
		}
	}
	
	public void setFillInWord()
	{
		this.fillInWord = new char[this.wordForGame.length()];
		Arrays.fill(this.fillInWord, '_');
	}
	public int getThisWordLength(){
		return this.wordForGame.length();
	}
	
	public char[] getFillInWord(){
		return this.fillInWord;
	}
	
	public void manageWrongCharacters(char inputLetter)
	{
		for(int i = 0; i<this.wrongCharacters.length; i++)
		{
			if(inputLetter == this.wrongCharacters[i])
			{
				break;
			}else
			{
				this.wrongCharacters[this.wrongCounter] = inputLetter;
			}
		}
	}
	public String getWordForGame(){
		return this.wordForGame;
	}
	
	public String getFillInWordAsString()
	{
		String word = "";
		for(int i = 0; i<this.wordForGame.length(); i++)
		{
			word = word + this.fillInWord[i] + " ";
		}
		return word;
	}
	
	public String getWrongCharacters()
	{
		String word = "";
		for(int i = 0; i<this.wordForGame.length(); i++)
		{
			if(this.wrongCharacters[i] != 0)
			{
				word = word + this.wrongCharacters[i] + " ";
			}
		}
		return word;
	}
	
	public String getWrongCount()
	{
		String word = "";
		if(this.wrongCounter > 0)
		{
			for(int i = 0; i<this.wrongCounter; i++)
			{
				word = word + "*" + " ";
			}
		}
		return word;
	}
	
	public boolean getIsFound(){
		return this.isFound;
	}
	
	public void setIsFound(boolean bool){
		this.isFound = bool;
	}
}