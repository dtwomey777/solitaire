import java.io.File;
import java.io.FileNotFoundException;
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

	/*
	* integer constant for the deck size
	*/

	public static final int SIZE = 28;

	/*
	* initializes an array of size SIZE that will
	* be used to store the values of the input file.
	*/

	private static int[] deck = new int[SIZE];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String fileName = getFileName(s);
		String message = getMessage(s);
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
			try{
				System.out.println("Enter a file to seed deck");
      				String fileName = s.nextLine();
     				File f = new File(fileName);
				Scanner fileScan = new Scanner(f);
     				found = true;
				file = fileName;
				int count = 0;
				while(fileScan.hasNextInt()){
					for(int i = 0; i < SIZE; i++){
						deck[i] = fileScan.nextInt();
					}
				}
			}catch(FileNotFoundException e){
				System.out.println("Bad filename.");
			}
		}
		return file;
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

	//IM NOT SURE IF WE'RE USING THIS
	/*
	* takes user inputed message, gets rid of all spaces,
	* turns all letters into capitals, and stores each letter
	* individually in a string array.
	*
	* @param message - user inputed message
	* @param letters - string array containing individual letters
	*/
	public static String[] format(String message){
		String uCase = message.toUpperCase();
		String formatted = uCase.replaceAll("[^a-zA-Z]", "");
		int messageLen = formatted.length();
		if (messageLen % 5 > 0){
			int mod = messageLen % 5;
			for (int x = 0; x < mod; x++){
				formatted = formatted + 'X';
			}
			messageLen = formatted.length();
		}
		String[] letters = new String[messageLen];
		for(int i = 0; i < messageLen ; i++){
			String lttr = formatted.substring(i, i+1);
			letters[i] = lttr;
		}
		return letters;
	}

	public static boolean playAgain(Scanner s){
		boolean playAgain = false;
		System.out.println("Do you want to play again??");
    		String verdict = s.nextLine();
		String verdictCaps = verdict.toUpperCase();
		String letter1 = verdict.substring(0);
		if(letter1.equals("Y")){
			playAgain = true;
		} else{
			System.out.println("Thanks for playing!");
		}
		return playAgain;
	}

	/**
	* Encrypts the message.
	*
	* @param e the formatted array from format()
	*/

	public static void encrypt(String[] e) {
		//convert letters to numbers
		//get keystream values
		//add the numbers together
		//if the added numbers are greater than 26, subtract by 26
		//convert back to letters
	}
		public static void step1() {
		int i = 0;
		while(deck[i] != 27) i ++;
		int temp = deck[i];
		deck[i] = deck[(i + 1) % deck.length];
		deck[(i + 1) % deck.length] = temp;
	}
	public static void step2() {
		int i = 0;
		while(deck[i] != 28) i ++;
		int temp = deck[i];
		deck[i] = deck[(i + 1) % deck.length];
		deck[(i + 1) % deck.length] = temp;
		deck[(i + 1) % deck.length] = deck[(i + 2) % deck.length];
		deck[(i + 2) % deck.length] = temp;
	}
	public static void step3() {
		int pos1 = 0;
		int pos2 = 0;
		for(int i = 0; i < deck.length; i ++){
			if(deck[i] == 27) pos1 = i;
			if(deck[i] == 28) pos2 = i;
		}
		for(int i = 0; i < (pos1 + pos2) / 2 - pos1; i ++){
			int temp = deck[i + pos1];
			deck[i + pos1] = deck[pos2 - i];
			deck[pos2 - i] = temp;
		}
		for(int i = 0; i < deck.length / 2; i ++){
			int temp = deck[i];
			deck[i] = deck[deck.length - 1 - i];
			deck[deck.length - 1 - i] = temp;
		}
	}
	public static void step4() {
		int[] deckCopy = new int[deck.length];
		for(int i = 0; i < deck.length; i ++) {
			deckCopy[i] = deck[i];
		}
		int lastCard = deckCopy[deck.length - 1];
		if(lastCard == 28) lastCard --;
		for(int i = 0; i < deck.length - 1 - lastCard; i ++) {
			deck[i] = deckCopy[i + lastCard];
		}
		for(int i = 0; i < lastCard; i ++) {
			deck[i + deck.length - lastCard - 1] = deckCopy[i];
		}
	}
	public static int step5() {
		int topCard = deck[0];
		if(deck[topCard] == 28 || deck[topCard] == 27) return -1;
		return deck[topCard];
	}
	public static int algorithm() {
		step1();
		step2();
		step3();
		step4();
		if(step5() != -1) return step5();
		while(step5() != -1) {
			step1();
			step2();
			step3();
			step4();
		}
		return step5();
	}
	
}
