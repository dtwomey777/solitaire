import java.io.File;
import java.util.Scanner;

/**
 * AP Computer Science A Solitaire Encryption Project.
 *
 *
 * @author Declan Twomey, Amy Wang, Bill Fei
 *
 */

	//Bill: Sorting Algorithm
	//Declan: Encryption
	//Amy: User Interface

public class Solitaire {

	private static int[] deck;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String fileName = getFileName(s);
	}

	/**
	* test method recommended by pujara
	* prints the array passed in
	*
	* @param array - an array
	*/

	public static void printArray(int[] array){
		for(int i = 0; i < array.length - 1; i++){
			System.out.println(array[i]);
		}
	}

	/**
	* asks for file name, loops until a valid file is inputed
	*
	* @param s - the Scanner object
	* @return file - the filename
	*/

	public static String getFileName(Scanner s){
		boolean found = false;
    String file = "";
		while(found == false){
			System.out.println("Enter a file to seed deck");
      String fileName = s.nextLine();
      File f = new File(fileName);
      found = f.canRead();
      if(found == false){
				System.out.println("Bad file name.");
			} else{
				found = true;
				file = fileName;
			}
		return file;
		}
	}

	/**
	* asks for E or D, loops until a valid input is received
	*
	* @param s - the Scanner object
	* @param fileName - the file name
	* @return ans - to encode or to decode, in the form of "E" or "D"
	*/

  public static String enDeCode(String fileName, Scanner s){
		boolean valid = false;
		String ans = "";
    while(valid == false){
			System.out.println("(E)ncode or (D)ecode");
			String verdict = s.nextLine();
      String verdictCaps = verdict.toUpperCase();
      String letter1 = verdictCaps.substring(0);
      System.out.println(letter1);
      if(letter1.equals("E") || letter1.equals("D")){
				valid = true;
        ans = letter1;
			} else{
				System.out.println("Invalid choice. Try again.");
			}
    }
    return ans;
	}

	/**
	* asks for message
	*
	* @param s - the Scanner object
	* @return message - the message
	*/

	public static String getMessage(Scanner s){
		System.out.println("Enter message");
    String message = s.nextLine();
    return message;
  }

	public static boolean playAgain(Scanner s){
		boolean playAgain = false;
		System.out.println("Do you want to play again??");
    String verdict = s.nextLine();
		String verdictCaps = verdict.toUpperCase();
		String letter1 = verdict.substring(0);
		if(letter1.equals("Y")){
			playAgain = true;
		}
		return playAgain;
	}

	public static void encrypt(String e){
		//remove non-letters
		String original = e.toUpperCase();
		while(original.contains(" ")){
			original = original.replaceAll("\\s", "");
		}
		if(original % 5 > 0){
			int mod = original % 5;
			for(int i = 1; i<= mod; i++){
				original = original + "X";
			}
		}
		//convert letters to numbers
		//get keystream values
		//add the numbers together
		//if the added numbers are greater than 26, subtract by 26
		//convert back to letters
	}
}
