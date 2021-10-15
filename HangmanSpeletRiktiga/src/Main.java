import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
//Main metoden
/** Ska dra igång spelet. Huruvida själva spelloopen ska ligga här eller i Game väljer ni själva.
 * In order for this program to run you need to create a File named "Savedata.txt" in the src file
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		Menu start = new Menu();
		Game currentGame = new Game();
		start.makeYourChoice(currentGame);
	}
}