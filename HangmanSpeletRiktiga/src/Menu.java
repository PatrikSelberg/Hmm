/* : Varje meny i spelet ska vara en instans av klassen Menu. Utöver lämplig konstruktor och
instansvariabler ska metoden show() skriva ut menyn. Instansmetoderna getString(), getAlpha() och
getInt() väntar till spelaren skrivit in en sträng, en bokstav respektive ett nummer. Skriver spelaren
fel typ av input ska lämpligt felmeddelande skrivas ut. */
import java.util.Scanner;
import java.io.File;
public class Menu
{
	private boolean isInStartMenue;
	private String wrongCount;
	private Player currentPlayer;
	private File file;
	
	
	//Obligatorisk metod
	public Menu(){
		this.isInStartMenue = true;
		this.wrongCount = "";
		this.currentPlayer = new Player();
		this.file = new File("src/Savedata.txt");
		
	}
	
	public void loadPlayer()
	{
		File file = new File("src/Savedata.txt");
		try
		{
			Scanner lanner = new Scanner(file);
			//currentPlayer.setNumberOfGames();
			//currentPlayer.setMatchesWon();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getInfoFromSavedata(File file)
	{
		try
		{
			Scanner janner = new Scanner(file);
			while (janner.hasNextLine())
			{
				String line = janner.nextLine();
				int i = 0;
				while (!Character.isWhitespace(line.charAt(i)))
				{
					i++;
				}
				currentPlayer.setName(line.substring(0, i));
			}
			
			
			 //Går igenom filen och söker fram den första inten
			 //och kopplar det till numberOfGames
			Scanner panner = new Scanner(file);
			
			//Ny scanner som söker fram int, radix, 8 gör så att den första inten i raden läses in.
			//Om jag ändrar den skriver den ut 0 vunna?!
			while (panner.hasNext())
			{
				if (panner.hasNextInt(8)) //om radix: 0 skrivs vunna
				{
					currentPlayer.setNumberOfGames(panner.nextInt());
				}
				else
				{
					panner.next();
				}
			}
			
			//Ny scanner som söker fram int, konstigt nog tar den här fram den andra inten
			Scanner yanner = new Scanner(file);
			while (yanner.hasNext())
			{
				if (yanner.hasNextInt())
				{
					currentPlayer.setMatchesWon(yanner.nextInt());
				}
				else
				{
					yanner.next();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void makeYourChoice(Game currentGame)
	{
		boolean switchCaseIsActive = true;
		//
		while (switchCaseIsActive) {
			
			this.show();
			Scanner scanner = new Scanner(System.in);
			System.out.print("");
			String input = scanner.nextLine();
			
			switch (input)
			{
				case "1":
					System.out.println("Spela spelet");
					this.setUp(currentGame);
					while(!currentGame.getIsFound())
					{
						currentGame.showGame();
						currentGame.update(this.getString());
					}
					this.clearUp(currentGame);
					break;
				case "2":
					System.out.println("Lägg till spelare: ");
					//creates a new object
					Player newPlayer = new Player();
					newPlayer.playerInfo();
					break;
				case "3":
					System.out.println("Välj befintlig spelare: ");
					//printOutAllPlayerNames();
					this.getInfoFromSavedata(this.file);
					currentPlayer.getInfo();
					//choose a player and load all the data
					//where/how do we store the data?,
					break;
				case "4":
					System.out.println("Avsluta");
					switchCaseIsActive = false;
					break;
				default:
					System.out.println("Felaktig input! Välj ett av alternativen i menyn");
					break;
			}
		}
	}
	
	public void printOutAllPlayerNames()
	{
		File file = new File("src/Savedata.txt");
		try
		{
			Scanner banner = new Scanner(file);
			while (banner.hasNextLine())
			{
				System.out.println(banner.nextLine());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * prints out the menu depending on witch state it is in
	 */
	public void show()
	{
		if(this.isInStartMenue)
		{
			System.out.println("Välj ett alternativ!");
			System.out.println("Tryck 1: Spela spelet");
			System.out.println("Tryck 2: Lägg till ny spelare");
			System.out.println("Tryck 3: Välj spelare");
			System.out.println("Tryck 4: Avsluta spelet");
		}
	}
	
	/**
	 * gets userimput
	 * @return string
	 */
	public String getString()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return input;
	}
	
	/**
	 * gets userinput
	 * @return int
	 */
	public int getInt()
	{
		Scanner scanner = new Scanner(System.in);
		//creates a variable that holds the input
		int scannedVariable = 0;
		
		while (true)
		{
			//tries to convert the input to an integer
			try
			{
				//if the input is an integer, input is saved in scannedVariable
				scannedVariable = Integer.parseInt(scanner.next());
				break;
			} catch (NumberFormatException exception)
			{
				System.out.println("Ange en siffra");
			}
		}
		return scannedVariable;
	}
	
	/**
	 * sets all instancevariables to their original position
	 * @param game Game Class
	 */
	public void clearUp(Game game)
	{
		game.setIsFound(false);
		this.isInStartMenue = true;
		this.setWrongCount();
		this.clearWrongCount();
	}
	
	/**
	 * chooses a word to play with, changes the menu to the in-game-menu, sets all needed instancevariables
	 * @param game Game Class
	 */
	public void setUp(Game game)
	{
		game.selectGameWord();
		game.setFillInWord();
		this.isInStartMenue = false;
	}
	
	public void setWrongCount(){
		this.wrongCount = this.wrongCount + " *";
	}
	
	public void clearWrongCount(){
		this.wrongCount = "";
	}
}