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
	/*
	 * We have to initialize an array that keeps the original
	 * array so that we can reset it every time.
	 */
	private static int[] originalDeck = new int[SIZE];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String fileName = getFileName(s);
		String option = enDeCode(fileName, s);
		String message = getMessage(s);
		if(option.equals("E")) encrypt(format(message));
		if(option.equals("D")) decrypt(format(message));
		while(playAgain(s)) {
			resetDeck();
			option = enDeCode(fileName, s);
			message = getMessage(s);
			if(option.equals("E")) encrypt(format(message));
			if(option.equals("D")) decrypt(format(message));
		}
	}
	/**
	 * Resets the deck.
	 */
	public static void resetDeck() {
		for(int i = 0; i < originalDeck.length; i ++) {
			deck[i] = originalDeck[i];
		}
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
			System.out.println("(E)ncode or (D)ecode ");
			String verdict = s.nextLine();
      			String verdictCaps = verdict.toUpperCase();
      			String letter1 = verdictCaps.substring(0, 1);
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
	public static char[] format(String message){
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
		char[] letters = new char[messageLen];
		for(int i = 0; i < messageLen ; i++){
			char lttr = formatted.charAt(i);
			letters[i] = lttr;
		}
		return letters;
	}

	public static boolean playAgain(Scanner s){
		boolean playAgain = false;
		System.out.println("Do you want to play again??");
    	String verdict = s.nextLine();
		String verdictCaps = verdict.toUpperCase();
		String letter1 = verdictCaps.substring(0, 1);
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

	public static void encrypt(char[] e) {
		for(int i = 0; i < e.length; i ++) {
			e[i] -= 65;
			int debug = algorithm();
			e[i] += debug;
			e[i] %= 26;
			e[i] += 65;
		}
		for(int i = 0; i < e.length; i ++) {
			System.out.print(e[i]);
		}
		System.out.println();
	}
	/**
	* Decrypts the message.
	*
	* @param e the formatted array from format()
	*/

	public static void decrypt(char[] e) {
		for(int i = 0; i < e.length; i ++) {
			e[i] -= 65;
			e[i] -= algorithm();
			e[i] += 26;
			e[i] %= 26;
			e[i] += 65;
		}
		for(int i = 0; i < e.length; i ++) {
			System.out.print(e[i]);
		}
		System.out.println();
	}

	/**
	 * Shuffles the deck according to specifications of step 1 of the algorithm.
	 */
	public static void step1() {
		//We look for the A joker.
		int i = 0;
		while(deck[i] != 27) i ++;
		//Do the swaps. We use modulus since we might wrap around the array.
		int temp = deck[i];
		deck[i] = deck[(i + 1) % deck.length];
		deck[(i + 1) % deck.length] = temp;
	}
	/**
	 * Shuffles the deck according to specifications of step 2 of the algorithm.
	 */
	public static void step2() {
		//We look for the B joker.
		int i = 0;
		while(deck[i] != 28) i ++;
		//Do the swaps. We use modulus since we might wrap around the array.
		int temp = deck[i];
		deck[i] = deck[(i + 1) % deck.length];
		deck[(i + 1) % deck.length] = temp;
		deck[(i + 1) % deck.length] = deck[(i + 2) % deck.length];
		deck[(i + 2) % deck.length] = temp;
	}
	/**
	 * Shuffles the deck according to specifications of step 3 of the algorithm.
	 */
	public static void step3() {
		//We find the A and B jokers and store them.
		int pos1 = 0;
		int pos2 = 0;
		for(int i = 0; i < deck.length; i ++){
			if(deck[i] == 27) pos1 = i;
			if(deck[i] == 28) pos2 = i;
		}
		//We want pos1 < pos2.
		if(pos2 < pos1) {
			int temp = pos2;
			pos2 = pos1;
			pos1 = temp;
		}
		//We create a copy of the array so we can keep track of the original stuff.
		int[] deckCopy = new int[deck.length];
		//Do step 3 of algorithm.
		for(int i = 0; i < deck.length; i ++) {
			deckCopy[i] = deck[i];
		}
		for(int i = pos2 + 1; i < deck.length; i ++) {
			deck[i - pos2 - 1] = deckCopy[i];
		}
		for(int i = 0; i < pos1; i ++) {
			deck[deck.length - pos1 + i] = deckCopy[i];
		}
		for(int i = deck.length - pos2 - 1; i < deck.length - pos1; i ++) {
			deck[i] = deckCopy[pos1 + i - (deck.length - pos2 - 1)];
		}
	}
	/*
	 * Shuffles the deck according to specifications of step 3 of the algorithm.
	 */
	public static void step4() {
		//We make a copy of the deck to keep track of the original while we are changing it.
		int[] deckCopy = new int[deck.length];
		for(int i = 0; i < deck.length; i ++) {
			deckCopy[i] = deck[i];
		}
		//We look at the last card. If it is a joker we make the value 27.
		int lastCard = deckCopy[deck.length - 1];
		int realLastCard = lastCard;
		if(lastCard == 28) lastCard --;
		//We swap according to the algorithm.
		for(int i = lastCard; i < deck.length; i ++) {
			deck[i - lastCard] = deckCopy[i];
		}
		for(int i = deck.length - lastCard - 1; i < deck.length - 1; i ++) {
			deck[i] = deckCopy[i - (deck.length - lastCard - 1)];
		}
		deck[deck.length - 1] = realLastCard;
	}
	/**
	 * Looks at the top card and the card counting down from the top card.
	 * @return the value of the card, if joker return -1;
	 */
	public static int step5() {
		int topCard = deck[0] - 1;
		if(deck[topCard] == 28 || deck[topCard] == 27) return -1;
		return deck[topCard];
	}
	/**
	 * Generates the keystream using the algorithm
	 * @return
	 */
	public static int algorithm() {
		//Run the algorithm
		step1();
		step2();
		step3();
		step4();
		//If it's not a joker then return the keystream.
		if(step5() != -1) return step5();
		//While it's not a joker run the algorithm.
		while(step5() != -1) {
			step1();
			step2();
			step3();
			step4();
		}
		return step5();
	}
}
