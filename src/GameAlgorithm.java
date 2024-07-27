/**GameAlgorithm.java
 * Description: This class manages all the backend of the Guess Who program. It manages the AI Algorithm,
 * 				the game, score, and the players. It houses the most important functions for the program to
 * 				run - all the algorithms and loops.
 * Date: January 12, 2023
 * * 	Assignment: Guess Who Project
 *
 * Contributors:
 * - Aaron Tom (GUI + DESIGN)
 * - Frank Ding (COMPUTER ALGORITHM)
 * - Nick Zhu (CORE / MOST IMPORTANT BASE FUNCTIONS)
 */



//import necessities
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * GameAlgorithm class
 */
public class GameAlgorithm {

	//initialize basic variables
	static ArrayList<Person> personList = new ArrayList<>();
	static ArrayList<Person> computerSelectionCharacterList;
	static 	ArrayList<Person> computerPersonList;
	static boolean playerQuestionValue = false;
	static boolean compWin = false;
	static boolean playerWin = false;
	static int turn = 0;
	static int questionNum;
	static final int numQuestions = 26;
	static final int numCharacters = 24;
	static String[] questionBank;
	static Person player1;
	static Person player2;

	static ArrayList<Boolean> playerAnswers;
	static ArrayList<Integer> computerQuestions;

	static ArrayList<Integer> playerQuestions;
	static ArrayList<Boolean> computerAnswers;
	static String date;
	static ArrayList<String> highScoreNames = new ArrayList<>();
	static ArrayList<Integer> highScoreScores = new ArrayList<>();
	static ArrayList<Integer> highScoreAI = new ArrayList<>();

	/**
	 * Houses all file reading needed to initialize the game. VERY IMPORTANT. Only run at the start of the program
	 * @throws FileNotFoundException
	 */
	public static void initializeGameFileReading() throws FileNotFoundException {
		//create arraylist of persons
		Scanner CLScanner = new Scanner(new File("Character List.txt"));
		String name;
		String eyeColour;
		String hairColour;
		String hairLength;
		boolean gender;
		boolean skinTone;
		boolean facialHair;
		boolean glasses;
		boolean teethVisible;
		boolean wearingHat;
		boolean piercings;

		//resets person list
		personList = new ArrayList<Person>();
		computerSelectionCharacterList = new ArrayList<>();
		computerPersonList = new ArrayList<Person>();

		//begin scanning Character List.txt from the second line (first line contains labels)
		CLScanner.nextLine();

		for(int i=0;i<numCharacters;i++) {
			name = CLScanner.next();
			eyeColour = CLScanner.next();
			gender = setBool("male", CLScanner.next());
			skinTone = setBool("light", CLScanner.next());
			hairColour = CLScanner.next();
			facialHair = setBool("true", CLScanner.next());
			glasses = setBool("true", CLScanner.next());
			teethVisible = setBool("true", CLScanner.next());
			wearingHat = setBool("true", CLScanner.next());
			hairLength = CLScanner.next();
			piercings = setBool("true", CLScanner.next());
			personList.add(new Person(name, eyeColour, gender, skinTone, hairColour, facialHair, glasses, teethVisible, wearingHat, hairLength, piercings));
//			computerPersonList.add(new Person(name, eyeColour, gender, skinTone, hairColour, facialHair, glasses, teethVisible, wearingHat, hairLength, piercings));

			//ADDS THE LIST OF DIFFICULT CHARACTER
			if (i == 0 || i == 1 || i == 3 || i == 6 || i == 7 || i == 10 || i == 11 || i == 12 || i == 14 || i == 15 || i == 16 || i == 19 || i == 23 || i == 24){
				computerSelectionCharacterList.add(new Person(name, eyeColour, gender, skinTone, hairColour, facialHair, glasses, teethVisible, wearingHat, hairLength, piercings));
			}
		}
		//LIST OF AI CHARACTERS TO CHOOSE: jordan leo joe al nick ben eric mike amy emma rachel katie liz lily carmen sofia

		//create array of questions through a for loop
		Scanner QBScanner = new Scanner(new File("Question Bank.txt"));
		questionBank = new String[numQuestions];
		for(int i=0;i<numQuestions;i++) {
			questionBank[i] = QBScanner.nextLine();
		}
	}


	/**
	 * Resets the game, initializes the game
	 * @throws IOException
	 */
	public static void initializeGame() throws IOException{
		//resets game variables
		playerQuestionValue = false;
		compWin = false;
		playerWin = false;

		computerPersonList = new ArrayList<>(personList);

		//create person
		int person1 = 0;
		int person2;
		do{
			person2 = (int)(1+Math.random()*computerSelectionCharacterList.size());
		} while (person1 == person2);

		//computer character, if the character is random
		player2 = computerSelectionCharacterList.get(person2-1); //comptuer character

		//resets playerAnswers and computer Questions
		playerAnswers = new ArrayList<Boolean>();
		computerQuestions = new ArrayList<Integer>();

		playerQuestions = new ArrayList<Integer>();
		computerAnswers = new ArrayList<Boolean>();

		//GETS DATE
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = (dateFormat.format(cal.getTime()));
	}

	/**
	 * This method makes the AI Ask a question
	 */
	public static void askQuestion(){
		//computers turn, calculates number of people
		int numPeople = computerPersonList.size();

		//if its not the last character left
		if (numPeople > 1){
			//gets the question to ask, stores it in a number format
			questionNum = getQuestion(computerPersonList);

			String computerText = "";

			//gets the text version of the question
			for (int i = 0; i < numQuestions || i+1 == questionNum; i ++){
				if (i+1 == questionNum){
					computerText = questionBank[i];
				}
			}

			//Asks the user for yes or no input
			GameMenu.yesNoInput(computerText);

		//If it is the last person left
		} else{
			//create text for the question
			String computerText = "";

			//gets the question, but based on the person list to get the last person standing
			for(int i = 0; i< computerPersonList.size(); i++) {
				computerText = computerPersonList.get(i).getName()+" ";
			}

			//Asks the user for yes or no input by telling the user what the guess is
			GameMenu.yesNoInput("I GUESS... " + computerText + "!");
		}
	}


	/**
	 * This method checks the players guess, once the player guessed
	 * @param name The name of the person the player guessed
	 * @return true or false, depending on if the player is right or not
	 */
	public static boolean playerGuessChecker(String name){
		//if the player guessed the character:
		if (player2.getName().equals(name)){
			return true;
		//if the player didn't guess the character
		} else{
			return false;
		}
	}

	/**
	 * For the error page, this method checks what questions the player answered WRONG and stores them in an array
	 * @param name The name of the correct player character
	 * @return An arraylist of all the values the player got wrong
	 */
	public static ArrayList<Integer> errorValidate(String name){
		//make a new arraylist of the wrong values
		ArrayList<Integer> wrongValues = new ArrayList<>();
		//Scans through the person list to find the players actual character as an object
		for (int i = 0; i < personList.size(); i++){
			//if it is, then you found the actual player character object
			if (personList.get(i).getName().equals(name)){
				player1 = personList.get(i);
			}
		}

		//Scans through the player answers to see if the player answers align with the actual answer
		for (int i = 0; i < playerAnswers.size(); i++){
			//if it doesn't align, then it adds to the wrong values arraylist
			if (player1.questionChecker(computerQuestions.get(i)) != playerAnswers.get(i)){
				wrongValues.add(i);
			}
		}
		//returns an arraylist of all the wrong questions the player answered incorrectly
		return wrongValues;
	}

	/**
	 * This method gets the question text that represents the given question number
	 * @param questionNum the number of the question
	 * @return the text for the number of the question
	 */
	public static String getQuestionText(int questionNum){
		String questionText = "";
		//loops through, exits once the question number has been found
		for (int i = 0; i < numQuestions || i+1 == questionNum; i ++){
			//if the question number has been found
			if (i+1 == questionNum){
				//then get the question text from the question bank
				questionText = GameAlgorithm.questionBank[i];
			}
		}
		//returns the question text
		return questionText;
	}

	/**
	 * This method prints the questions asked by the AI in the game to a file for storage.
	 * This allows us to test and see what questions the AI Asked, and what the player answered
	 */
	public static void writeQuestionsToFile() throws IOException {
		//creates new print writer object
		PrintWriter output = new PrintWriter(new FileOutputStream(new File("Computer Responses.txt"), true ));

		//the reason why we need to constantly open a file is to make sure that the responses document has the most up to date information
		output.println(date);

		//goes through loop to get the question, outputs question to file, and outputs answer to the file for the question
		for (int i = 0; i < computerQuestions.size(); i++){
			output.print(questionBank[computerQuestions.get(i)-1] + " - ");
			output.println(playerAnswers.get(i) + " ");
		}
		output.close();

	}


	/**
	 * This method prints the questions asked by the AI in the game to a file for storage.
	 * This allows us to test and see what questions the AI Asked, and what the player answered
	 */
	public static void writeAnswersToFile() throws IOException{
		//creates new print writer object
		PrintWriter output = new PrintWriter(new FileOutputStream(new File("Player Responses.txt"), true ));

		//the reason why we need to constantly open a file is to make sure that the responses document has the most up to date information
		output.println(date);

		//goes through loop to get the question, outputs question to file, and outputs answer to the file for the question
		for (int i = 0; i < playerQuestions.size(); i++){
			output.print(questionBank[playerQuestions.get(i)-1] + " - ");
			output.println(computerAnswers.get(i) + " ");
		}
		output.close();
	}

	/**
	 * This method removes the character
	 * @param questionNum the question num assigned
	 * @param removalList the list of characters you would like to remove
	 * @param questionCheckerValue true or false, depending on if the person has that attribute
	 */
	public static void characterRemover(int questionNum, ArrayList<Person> removalList, boolean questionCheckerValue){
		//if the person has the attribute
		if(questionCheckerValue){
			//cycles through each question and see if the requested variable from person matches the condition
			//If it matches, then remove all the attributes in accordance with the question and the answer
			//Question 1: Is the eye colour brown? (removes all green and blue because answer is yes)
			if(questionNum==1) {
				elimEyeColour("green", removalList);
				elimEyeColour("blue", removalList);
			//Question 2: Is the eye colour green? (removes all brown and blue because answer is yes)
			} else if(questionNum==2) {
				elimEyeColour("brown", removalList);
				elimEyeColour("blue", removalList);
			//Question 3: Is the eye colour blue? (removes all green and brown because answer is yes)
			} else if(questionNum==3) {
				elimEyeColour("brown", removalList);
				elimEyeColour("green", removalList);
			//Question 4: Is the person a male? (removes females)
			} else if (questionNum == 4){
				elimGender(false, removalList);
			//Question 5: Is the person a female? (removes males)
			} else if (questionNum == 5){
				elimGender(true, removalList);
			//Question 6: Does the person have a light skin tone?
			} else if(questionNum == 6) {
				elimSkinTone(false, removalList);
			//Question 7: Does this person have a dark skin tone?
			} else if (questionNum == 7) {
				elimSkinTone(true, removalList);
			//Question 8: Is the hair colour black?
			} else if (questionNum == 8){
				elimHairColour("brown", removalList);
				elimHairColour("ginger", removalList);
				elimHairColour("blonde", removalList);
				elimHairColour("white", removalList);
			//Question 9: Is the hair colour brown?
			} else if (questionNum == 9){
				elimHairColour("black", removalList);
				elimHairColour("ginger", removalList);
				elimHairColour("blonde", removalList);
				elimHairColour("white", removalList);
			//Question 10: Is the hair colour ginger?
			} else if (questionNum == 10){
				elimHairColour("black", removalList);
				elimHairColour("brown", removalList);
				elimHairColour("blonde", removalList);
				elimHairColour("white", removalList);
			//Question 11: Is the hair colour blonde?
			} else if (questionNum == 11){
				elimHairColour("black", removalList);
				elimHairColour("brown", removalList);
				elimHairColour("ginger", removalList);
				elimHairColour("white", removalList);
			//Question 12: Is the hair colour white?
			} else if (questionNum == 12){
				elimHairColour("black", removalList);
				elimHairColour("brown", removalList);
				elimHairColour("ginger", removalList);
				elimHairColour("blonde", removalList);
			//Question 13: Does the person have facial hair?
			} else if (questionNum == 13){
				elimFacialHair(false, removalList);
			//Question 14: Does the person have no facial hair?
			} else if (questionNum == 14){
				elimFacialHair(true, removalList);
			//Question 15: Is the person wearing glasses?
			} else if (questionNum == 15){
				elimGlasses(false, removalList);
			//Question 16: Is the person not wearing glasses?
			} else if (questionNum == 16){
				elimGlasses(true, removalList);
			//Question 17: Does the person have visible teeth?
			} else if (questionNum == 17){
				elimTeethVisible(false, removalList);
			//Question 18: Is the person not showing teeth?
			} else if (questionNum == 18){
				elimTeethVisible(true, removalList);
			//Question 19: Is the person wearing a hat?
			} else if (questionNum == 19){
				elimWearingHat(false, removalList);
			//Question 20: Is the person not wearing a hat?
			} else if (questionNum == 20){
				elimWearingHat(true, removalList);
			//Question 21: Does the person have short hair?
			} else if (questionNum == 21){
				elimHairLength("tied", removalList);
				elimHairLength("long", removalList);
				elimHairLength("bald", removalList);
			//Question 22: Does the person have their hair tied up?
			} else if (questionNum == 22){
				elimHairLength("short", removalList);
				elimHairLength("long", removalList);
				elimHairLength("bald", removalList);
			//Question 23: Does the person have long hair?
			} else if (questionNum == 23){
				elimHairLength("short", removalList);
				elimHairLength("tied", removalList);
				elimHairLength("bald", removalList);
			//Question 24: Is the person bald?
			} else if (questionNum == 24){
				elimHairLength("short", removalList);
				elimHairLength("tied", removalList);
				elimHairLength("long", removalList);
			//Question 25: Does the person have an ear piercing?
			} else if (questionNum == 25){
				elimPiercings(false, removalList);
			//Question 26: Does the person not have an ear piercing?
			} else if (questionNum == 26){
				elimPiercings(true, removalList);
			}
		//If the player answered that the person doesnt have the attribute
		} else {
			//Question 1: Is the eye colour brown?
			if (questionNum == 1) {
				elimEyeColour("brown", removalList);
			//Question 2: Is the eye colour green?
			} else if (questionNum == 2) {
				elimEyeColour("green", removalList);
			//Question 3: Is the eye colour blue?
			} else if (questionNum == 3) {
				elimEyeColour("blue", removalList);
			//Question 4: Is the person a male?
			} else if (questionNum == 4){
				elimGender(true, removalList);
			//Question 5: Is the person a female?
			} else if (questionNum == 5){
				elimGender(false, removalList);
			//Question 6: Does the person have a light skin tone?
			} else if (questionNum == 6) {
				elimSkinTone(true, removalList);
			//Question 7: Does this person have a dark skin tone?
			} else if (questionNum == 7){
				elimSkinTone(false, removalList);
			//Question 8: Is the hair colour black?
			} else if (questionNum == 8){
				elimHairColour("black", removalList);
			//Question 9: Is the hair colour brown?
			} else if (questionNum == 9){
				elimHairColour("brown", removalList);
			//Question 10: Is the hair colour ginger?
			} else if (questionNum == 10){
				elimHairColour("ginger", removalList);
			//Question 11: Is the hair colour blonde?
			} else if (questionNum == 11){
				elimHairColour("blonde", removalList);
			//Question 12: Is the hair colour white?
			} else if (questionNum == 12){
				elimHairColour("white", removalList);
			//Question 13: Does the person have facial hair?
			} else if (questionNum == 13){
				elimFacialHair(true, removalList);
			//Question 14: Does the person have no facial hair?
			} else if (questionNum == 14){
				elimFacialHair(false, removalList);
			//Question 15: Is the person wearing glasses?
			} else if (questionNum == 15){
				elimGlasses(true, removalList);
			//Question 16: Is the person not wearing glasses?
			} else if (questionNum == 16){
				elimGlasses(false, removalList);
			//Question 17: Does the person have visible teeth?
			} else if (questionNum == 17){
				elimTeethVisible(true, removalList);
			//Question 18: Is the person not showing teeth?
			} else if (questionNum == 18){
				elimTeethVisible(false, removalList);
			//Question 19: Is the person wearing a hat?
			} else if (questionNum == 19){
				elimWearingHat(true, removalList);
			//Question 20: Is the person not wearing a hat?
			} else if (questionNum == 20){
				elimWearingHat(false, removalList);
			//Question 21: Does the person have short hair?
			} else if (questionNum == 21){
				elimHairLength("short", removalList);
			//Question 22: Does the person have their hair tied up?
			} else if (questionNum == 22){
				elimHairLength("tied", removalList);
			//Question 23: Does the person have long hair?
			} else if (questionNum == 23){
				elimHairLength("long", removalList);
			//Question 24: Is the person bald?
			} else if (questionNum == 24){
				elimHairLength("bald", removalList);
			//Question 25: Does the person have an ear piercing?
			} else if (questionNum == 25){
				elimPiercings(true, removalList);
			//Question 26: Does the person not have an ear piercing?
			} else if (questionNum == 26){
				elimPiercings(false, removalList);
			}
		}
	}

	/**
	 * This method gets the question from an array list for the computer
	 * @param computerPersonList
	 * @return
	 */
	public static int getQuestion(ArrayList<Person> computerPersonList){
		//Create and initialize arrays
		int[] numberAttributes = new int[26]; //number of characters with that number of attributes

		int[] newNumberAttributes = new int[26]; //new number of attributes after the algorithm has been run (dividing calculation)

		//finds number of attributes for each attribute
		//for each person in the available list of people for the AI to choose, count the number of attributes that they have
		//the number of the numberAttributes array correlates with the question number - 1
		for (int i = 0; i < computerPersonList.size(); i++){
			//If the hair color is brown, add 1
			if (computerPersonList.get(i).getEyeColour().equals("brown")){
				numberAttributes[0] ++;
			//If the hair color is green, add 1 to the number of people with green hair
			} else if (computerPersonList.get(i).getEyeColour().equals("green")){
				numberAttributes[1] ++;
			//If the hair color is blue, add 1 to the number of people with blue hair
			} else if (computerPersonList.get(i).getEyeColour().equals("blue")){
				numberAttributes[2] ++;
			}
			//If male
			if (computerPersonList.get(i).getGender() == true){
				numberAttributes[3] ++;
			//If female
			} else if (computerPersonList.get(i).getGender() == false){
				numberAttributes[4] ++;
			}

			//if skin tone is light
			if (computerPersonList.get(i).getSkinTone() == true){
				numberAttributes[5] ++;
			//if skin tone is dark
			} else if (computerPersonList.get(i).getSkinTone() == false) {
				numberAttributes[6]++;
			}

			//if hair is black
			if (computerPersonList.get(i).getHairColour().equals("black")){
				numberAttributes[7] ++;
			//if hair is brown
			} else if (computerPersonList.get(i).getHairColour().equals("brown")){
				numberAttributes[8] ++;
			//if hair is ginger
			} else if (computerPersonList.get(i).getHairColour().equals("ginger")){
				numberAttributes[9] ++;
			//if hair is blonde
			} else if (computerPersonList.get(i).getHairColour().equals("blonde")){
				numberAttributes[10] ++;
			//if hair is white
			} else if (computerPersonList.get(i).getHairColour().equals("white")){
				numberAttributes[11] ++;
			}

			//if facial hair
			if (computerPersonList.get(i).getFacialHair() == true){
				numberAttributes[12] ++;
			//if no facial hair
			} else if (computerPersonList.get(i).getFacialHair() == false){
				numberAttributes[13] ++;
			}

			//if character has glasses
			if (computerPersonList.get(i).getGlasses() == true){
				numberAttributes[14] ++;
			//if character doesnt have glasses
			} else if (computerPersonList.get(i).getGlasses() == false){
				numberAttributes[15] ++;
			}

			//if teeth are visible
			if (computerPersonList.get(i).getTeethVisible() == true){
				numberAttributes[16] ++;
			//if teeth arent visible
			} else if (computerPersonList.get(i).getTeethVisible() == false){
				numberAttributes[17] ++;
			}

			//if they got hat
			if (computerPersonList.get(i).getWearingHat() == true){
				numberAttributes[18] ++;
			//if they got no hat
			} else if (computerPersonList.get(i).getWearingHat() == false){
				numberAttributes[19] ++;
			}

			//if hair length is short
			if (computerPersonList.get(i).getHairLength().equals("short")){
				numberAttributes[20] ++;
			//if hair length tied
			} else if (computerPersonList.get(i).getHairLength().equals("tied")){
				numberAttributes[21] ++;
			//if hair length long
			} else if (computerPersonList.get(i).getHairLength().equals("long")){
				numberAttributes[22] ++;
			//hair length bald
			} else if (computerPersonList.get(i).getHairLength().equals("bald")){
				numberAttributes[23] ++;
			}

			//if piercings
			if (computerPersonList.get(i).getPiercings() == true){
				numberAttributes[24] ++;
			//if no piercings
			} else if (computerPersonList.get(i).getPiercings() == false){
				numberAttributes[25] ++;
			}
		}

		//gets absolute value to find which attribute is the closest to the middle value. that is the optimal number
		for (int i = 0; i < 26; i++){
			int optimalNumber = (int)(Math.abs((computerPersonList.size()/2)-numberAttributes[i]));
			//gets the optimal value
			newNumberAttributes[i] = optimalNumber;
		}

		//gets the index of the smallest attribute (the attribute thats closest to the middle value)
		int smallestValue = newNumberAttributes[0];
		int smallestValueIndex = 0;
		//with the new number of attributes, find the index of the smallest attribute (means that that values closest to the middle value)
		for (int i = 0; i < 26; i++){
			if (newNumberAttributes[i] < smallestValue){
				smallestValue = newNumberAttributes[i];
				smallestValueIndex = i;
			}
		}
		//Returns the question thats most likely to eliminate the most values
		return smallestValueIndex+1;
	}


	/**
	 * This method adds high scores to an array list to read. This methods only run once cause its efficient to read a file once
	 * @throws IOException
	 */
	public static void addHighScores() throws IOException {
		//file reader
		BufferedReader reader = new BufferedReader (new FileReader ("highscores.txt"));
		String name = reader.readLine();

		//sets i to 0 (the current line that its reading)
		int i = 0;
		//adds words to array list until no words can be read
		while (name != null) {
			//first line - NAME
			if (i%3 == 0){
				highScoreNames.add(name);
			//Second line - SCORES
			} else if (i%3 == 1){
				highScoreScores.add(Integer.parseInt(name));
			//Third line - AI SCORES
			} else {
				highScoreAI.add(Integer.parseInt(name));
			}
			//reads another line
			name = reader.readLine();
			i++;
		}
		//closes the reader
		reader.close();

	}

	/**
	 * This method adds a new player score to the array list, if theres a high score
	 * @param playerName name of the player
	 * @param score score of the player
	 * @param AIScore AI Score of the player
	 * @throws IOException
	 */
	public static void addPlayerScore(String playerName, int score, int AIScore) throws IOException {
		//if the player isnt added
		boolean added = false;
		//repeats a for loop that scans through the names
		for (int i = 0; i < highScoreNames.size(); i++){
			//IF THE username exists and the  score is greater than the high score
			if (highScoreNames.get(i).equals(playerName) && score>=highScoreScores.get(i)){
				//set added to true
				added = true;
				//changes the score
				highScoreScores.set(i, score);
				highScoreAI.set(i, AIScore);
			}
		}
		//if the player name isnt in the loop of current usernames stored in the game
		if (!added){
			//creates a new name slot by adding the following stats to the respective arraylists
			highScoreNames.add(playerName);
			highScoreScores.add(score);
			highScoreAI.add(AIScore);
		}

		//i is equal to the size of high score names
		int i = highScoreNames.size();

		//adds highscores to the file
		PrintWriter output = new PrintWriter("highscores.txt");
		for (int x = 0; x< i; x++){
			//writes the high scores to the file for data storage
			output.println(highScoreNames.get(x));
			output.println(highScoreScores.get(x));

			if (x != i-1){
				output.println(highScoreAI.get(x));
			} else{
				output.print(highScoreAI.get(x));
			}
		}
		//closes everything
		output.close();
	}

	/**
	 * This method sorts through the high scores and ranks them
	 * @throws IOException
	 */
	public static void rankHighScores() throws IOException{
		//initialize variables
		int m = 0;
		boolean swap = true;

		//conducts an IMPROVED BINARY SEARCH to find the highest score and organize the three arraylists :o
		while(swap){
			swap = false;
			for (int i = 0; i < highScoreScores.size()-1-m; i++){
				//if The high score is less than the high score next value
				if (highScoreScores.get(i) < highScoreScores.get(i+1)){
					//swaps the values in all three array lists
					swap = true;
					int temp = highScoreScores.get(i+1);
					highScoreScores.set(i+1, highScoreScores.get(i));
					highScoreScores.set(i, temp);

					String tempString = highScoreNames.get(i+1);
					highScoreNames.set(i+1, highScoreNames.get(i));
					highScoreNames.set(i, tempString);

					int tempInt = highScoreAI.get(i+1);
					highScoreAI.set(i+1, highScoreAI.get(i));
					highScoreAI.set(i, tempInt);
				}
			}
			//adds one to the iterations
			m++;
		}
	}


	/**
	 * Converts a string from Character List.txt representing an attribute into a boolean value, if there are two possible attributes in that category.
	 *
	 * @param personAttribute	the String value of the attribute to be compared to the String from the text file
	 * @param CLAttribute		the String value of the attribute as it appears on Character List.txt
	 * @return					the converted boolean value of the attribute
	 */
	public static boolean setBool(String personAttribute, String CLAttribute) {
		if(personAttribute.equals(CLAttribute)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested eye colour.
	 *
	 * @param eyeColour	the requested eye colour
	 * @param list		the arraylist from which this method removes objects
	 */
	public static void elimEyeColour(String eyeColour, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getEyeColour().equals(eyeColour)) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested gender.
	 *
	 * @param gender	the requested gender
	 * @param list		the arraylist from which this method removes objects
	 */
	public static void elimGender(boolean gender, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getGender()==gender) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested skin tone.
	 *
	 * @param skinTone	the requested skin tone
	 * @param list		the arraylist from which this method removes objects
	 */
	public static void elimSkinTone(boolean skinTone, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getSkinTone()==skinTone) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested hair colour.
	 *
	 * @param hairColour	the requested hair colour
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimHairColour(String hairColour, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getHairColour().equals(hairColour)) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested value for facial hair.
	 * @param facialHair	the requested value for facial hair
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimFacialHair(boolean facialHair, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getFacialHair()==facialHair) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested value for glasses.
	 *
	 * @param glasses	the requested value for glasses
	 * @param list		the arraylist from which this method removes objects
	 */
	public static void elimGlasses(boolean glasses, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getGlasses()==glasses) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested value for teeth visibility.
	 *
	 * @param teethVisible	the requested value for teeth visibility
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimTeethVisible(boolean teethVisible, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getTeethVisible()==teethVisible) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested value for if they are wearing a hat.
	 *
	 * @param wearingHat	the requested value for if they are wearing a hat
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimWearingHat(boolean wearingHat, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getWearingHat()==wearingHat) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested hair length.
	 *
	 * @param hairLength	the requested hair length
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimHairLength(String hairLength, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getHairLength().equals(hairLength)) {
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes all objects from an ArrayList<Person> that have the requested value for piercings.
	 *
	 * @param piercings	the requested value for piercings
	 * @param list			the arraylist from which this method removes objects
	 */
	public static void elimPiercings(boolean piercings, ArrayList<Person> list) {

		//loop that runs through every object in the arraylist checking if the attributes match
		for(int i=0;i<list.size();i++) {

			//if there is a match, remove the object from the arraylist and reduce i by 1 to match the new index of the next object (since the size of the arraylist will have shrunk by 1)
			if(list.get(i).getPiercings()==piercings) {
				list.remove(i);
				i--;
			}
		}
	}
}