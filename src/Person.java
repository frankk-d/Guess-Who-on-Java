/*	Authors: Frank Ding, Aaron Tom, Nick Zhu
 * 	Date: January 13, 2024
 * 	Assignment: Guess Who Project
 * 	Description: This class defines the methods and variables for the object Person.
 */

public class Person {

	//declare all variable names

	private String name;
	private String eyeColour;
	private boolean gender;
	private boolean skinTone;
	private String hairColour;
	private boolean facialHair;
	private boolean glasses;
	private boolean teethVisible;
	private boolean wearingHat;
	private String hairLength;
	private boolean piercings;

	/**
	 * Constructor method to instantiate an object Person with all variables initialized.
	 *
	 * @param defaultName 			Person's name
	 * @param defaultEyeColour 		Person's eye colour
	 * @param defaultGender 		Person's gender
	 * @param defaultSkinTone 		Person's skin tone
	 * @param defaultHairColour		Person's hair colour
	 * @param defaultFacialHair		if Person has facial hair
	 * @param defaultGlasses		if Person has glasses
	 * @param defaultTeethVisible	if Person has teeth visible
	 * @param defaultWearingHat		if Person is wearing a hat
	 * @param defaultHairLength		Person's hair length
	 * @param defaultPiercings		if Person has piercings
	 */
	public Person(String defaultName, String defaultEyeColour, boolean defaultGender, boolean defaultSkinTone, String defaultHairColour, boolean defaultFacialHair, boolean defaultGlasses, boolean defaultTeethVisible, boolean defaultWearingHat, String defaultHairLength, boolean defaultPiercings) {
		name = defaultName;
		eyeColour = defaultEyeColour;
		gender = defaultGender;
		skinTone = defaultSkinTone;
		hairColour = defaultHairColour;
		facialHair = defaultFacialHair;
		glasses = defaultGlasses;
		teethVisible = defaultTeethVisible;
		wearingHat = defaultWearingHat;
		hairLength = defaultHairLength;
		piercings = defaultPiercings;
	}

	/**
	 * This method checks if a question asked about a Person is true or false (yes or no).
	 *
	 * @param questionNumber	the question being asked (refer to Question Bank.txt)
	 * @return					true if the question asked is true, false otherwise
	 */
	public boolean questionChecker(int questionNumber){

		//cycle through each question and see if the requested variable from person matches the condition

		//Question 1: Is the eye colour brown?
		if (questionNumber == 1 && eyeColour.equals("brown")){
			return true;
		}

		//Question 2: Is the eye colour green?
		else if (questionNumber == 2 && eyeColour.equals("green")){
			return true;
		}

		//Question 3: Is the eye colour blue?
		else if (questionNumber == 3 && eyeColour.equals("blue")){
			return true;
		}

		//Question 4: Is the person a male?
		else if (questionNumber == 4 && gender){
			return true;
		}

		//Question 5: Is the person a female?
		else if (questionNumber == 5 && !gender){
			return true;
		}

		//Question 6: Does the person have a light skin tone?
		else if (questionNumber == 6 && skinTone){
			return true;
		}

		//Question 7: Does this person have a dark skin tone?
		else if (questionNumber == 7 && !skinTone){
			return true;
		}

		//Question 8: Is the hair colour black?
		else if (questionNumber == 8 && hairColour.equals("black")){
			return true;
		}

		//Question 9: Is the hair colour brown?
		else if (questionNumber == 9 && hairColour.equals("brown")){
			return true;
		}

		//Question 10: Is the hair colour ginger?
		else if (questionNumber == 10 && hairColour.equals("ginger")){
			return true;
		}

		//Question 11: Is the hair colour blonde?
		else if (questionNumber == 11 && hairColour.equals("blonde")){
			return true;
		}

		//Question 12: Is the hair colour white?
		else if (questionNumber == 12 && hairColour.equals("white")){
			return true;
		}

		//Question 13: Does the person have facial hair?
		else if (questionNumber == 13 && facialHair){
			return true;
		}

		//Question 14: Does the person have no facial hair?
		else if (questionNumber == 14 && !facialHair){
			return true;
		}

		//Question 15: Is the person wearing glasses?
		else if (questionNumber == 15 && glasses){
			return true;
		}

		//Question 16: Is the person not wearing glasses?
		else if (questionNumber == 16 && !glasses){
			return true;
		}

		//Question 17: Does the person have visible teeth?
		else if (questionNumber == 17 && teethVisible){
			return true;
		}

		//Question 18: Is the person not showing teeth?
		else if (questionNumber == 18 && !teethVisible){
			return true;
		}

		//Question 19: Is the person wearing a hat?
		else if (questionNumber == 19 && wearingHat){
			return true;
		}

		//Question 20: Is the person not wearing a hat?
		else if (questionNumber == 20 && !wearingHat){
			return true;
		}

		//Question 21: Does the person have short hair?
		else if (questionNumber == 21 && hairLength.equals("short")){
			return true;
		}

		//Question 22: Does the person have their hair tied up?
		else if (questionNumber == 22 && hairLength.equals("medium")){
			return true;
		}

		//Question 23: Does the person have long hair?
		else if (questionNumber == 23 && hairLength.equals("long")){
			return true;
		}

		//Question 24: Is the person bald?
		else if (questionNumber == 24 && hairLength.equals("bald")){
			return true;
		}

		//Question 25: Does the person have an ear piercing?
		else if (questionNumber == 25 && piercings){
			return true;
		}

		//Question 26: Does the person not have an ear piercing?
		else if (questionNumber == 26 && !piercings){
			return true;
		}

		//returns false if question asked is false
		else {
			return false;
		}
	}

	/**
	 * Getter method for Person's name.
	 *
	 * @return	Person's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for Person's eye colour.
	 *
	 * @return	Person's eye colour
	 */
	public String getEyeColour() {
		return eyeColour;
	}

	/**
	 * Getter method for Person's gender.
	 *
	 * @return	Person's gender (male = true, female = false)
	 */
	public boolean getGender() {
		return gender;
	}

	/**
	 * Getter method for Person's skin tone.
	 *
	 * @return	Person's skin tone (light = true, dark = false)
	 */
	public boolean getSkinTone() {
		return skinTone;
	}

	/**
	 * Getter method for Person's hair colour.
	 *
	 * @return	Person's hair colour
	 */
	public String getHairColour() {
		return hairColour;
	}

	/**
	 * Getter method for if Person has facial hair.
	 *
	 * @return	if Person has facial hair
	 */
	public boolean getFacialHair() {
		return facialHair;
	}

	/**
	 * Getter method for if Person wears glasses.
	 *
	 * @return	if Person wears glasses
	 */
	public boolean getGlasses() {
		return glasses;
	}

	/**
	 * Getter method for if Person has visible teeth.
	 *
	 * @return	if Person has visible teeth
	 */
	public boolean getTeethVisible() {
		return teethVisible;
	}

	/**
	 * Getter method for if Person is wearing a hat.
	 *
	 * @return	if Person is wearing a hat
	 */
	public boolean getWearingHat() {
		return wearingHat;
	}

	/**
	 * Getter method for Person's hair length.
	 *
	 * @return	Person's hair length
	 */
	public String getHairLength() {
		return hairLength;
	}

	/**
	 * Getter method for if Person has piercings.
	 *
	 * @return	if Person has piercings
	 */
	public boolean getPiercings() {
		return piercings;
	}
}