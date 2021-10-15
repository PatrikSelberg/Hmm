/* Klassen player ska innehålla privata instansvariabler av lämpliga typer om spelarens namn
och information om antalet spelade och vunna matcher
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Player
{
	private String name;
	private int numberOfGames;
	private int matchesWon;
	private String input;
	
	
	//Obligatorisk metod
	public Player()
	{
	
	}
	
	public void playerInfo()
	{
		{
			try
			{
				ArrayList<String> loadNames = new ArrayList<String>();
				File saveData = new File("src/Savedata.txt");
				Scanner fileReader = new Scanner(saveData);
				
				while (fileReader.hasNextLine())
				{
					loadNames.add(fileReader.nextLine());
				}
				fileReader.close();
				
				Scanner userInputScanner = new Scanner(System.in);
				String input = userInputScanner.nextLine();
				
				this.name = input;
				this.matchesWon = 0;
				this.numberOfGames = 0;
				this.input = input;
				
				if (loadNames.size() == 0)
				{
					loadNames.add(input + " har spelat " + numberOfGames + " rundor, och vunnit " + matchesWon);
					System.out.println("Spelaren har lagts till!");
				}
				else if (loadNames.contains(input))
				{
					System.out.println("Spelaren finns redan tillagd!");
				}
				else
				{
					System.out.print("Spelaren har lagts till!");
					loadNames.add(input + " har spelat " + numberOfGames + " rundor, och vunnit " + matchesWon);
				}
				
				PrintWriter writer = new PrintWriter("src/Savedata.txt");
				for (int i = 0; i < loadNames.size(); i++)
				{
					writer.println(loadNames.get(i));
				}
				writer.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setNumberOfGames(int numberOfGames)
	{
		this.numberOfGames = numberOfGames;
	}
	
	public void setMatchesWon(int matchesWon)
	{
		this.matchesWon = matchesWon;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getNumberOfGames()
	{
		return this.numberOfGames;
	}
	
	public int getMatchesWon()
	{
		return this.matchesWon;
	}
	
	public String getInput()
	{
		return this.input;
	}
	
	public void getInfo()
	{
		System.out.print("Spelare: " + this.name);
		System.out.print(" har spelat " + this.numberOfGames + " rundor, ");
		System.out.print("och vunnit " + this.matchesWon);
		System.out.println();
	}
}