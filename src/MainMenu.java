/**MainMenu.java
 * Description: This class is 1 of 2 classes that manage GUI. This class specifically
 * manages the main menu that appears when the user first launches the program. it allows you to access many things and is connected to the GameMenu class.
 *
 * Date: January 12, 2023
 * * 	Assignment: Guess Who Project
 *
 * Contributors:
 * - Aaron Tom (GUI + DESIGN)
 * - Frank Ding (COMPUTER ALGORITHM)
 * - Nick Zhu (CORE / MOST IMPORTANT BASE FUNCTIONS)
 */


//import necessities
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;

/**
 * Main Menu Class
 */
public class MainMenu{
	//Global Variables

	// all custom font variables

	//made a static final font because its easier to create this way, while also not using a lot of memory (lesson learned)
	static final Font POPPINS = FontGenerator.fontCreator("Poppins-Bold.ttf", 30f); //runs the font creator with the specific file name

	// all JPanels
	static JPanel mainMenu;
	static JPanel mainGame;
	static JPanel[] rulePage;
	static JPanel gameEnd;
	static JPanel playerEnterName;
	static JPanel turnPanel;
	static JPanel highScorePanel;

	// JTextField used in playerEnterName JPanel
	static JTextField enterName;

	// rulePage counter to be able to swap between different pages in the rule page
	static int counter=0;

	// all JButtons
	static JButton play;
	static JButton quit;
	static JButton rules;
	static JButton menu;
	static JButton back;
	static JButton next;
	static JButton resetGame;
	static JButton highScore;
	static JButton enter;
	static JButton cycle;
	static JButton yes = new JButton("Yes");
	static JButton no = new JButton("No");

	// all JLabels
	static JLabel title; //Logo of Guess Who
	static JLabel title2;//Logo of Guess Who
	static JLabel title3;//Logo of Guess Who - repeated 3 times because we were lazy to continously readd it each time we swapped panel visibility
	static JLabel scoreLabel;
	static JLabel AILabel;
	static JFrame frame; //Frame that holds all buttons and labels

	// all Variables used for the timer
	static Timer timer;
	static long elapsedTime;
	static int seconds = 0;
	static int tenSeconds = 0;
	static int minutes = 0;


	static JLabel[] highScoreNamesLabel;
	static JLabel[] highScoreScoresLabel;
	static JPanel highScoreDisplayPanel;

	static JPanel namesDisplayPanel;

	static JPanel scoresDisplayPanel;

	// declare BufferedImage variables
	static BufferedImage turnSelectionPicImage;
	static BufferedImage questionMenuPicImage;
	static BufferedImage questionMenuAskPicImage;
	static BufferedImage computerAskPicImage;
	static BufferedImage playerAnswerPicImage;
	static BufferedImage computerAskOutputPicImage;
	static BufferedImage playerAskOutputPicImage;
	static BufferedImage boardExamplePicImage;
	static BufferedImage wholeBoardPicImage;
	static BufferedImage endTurnPicImage;
	/**
	 * Main Method is where all JPanels are created by calling their respective methods
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//adds the high scores - has to be first to run the file checking loading
		GameAlgorithm.addHighScores();

		//chooses a random number that corresponds to a respective song
		int selectMusic = (int)(1+Math.random()*5);

		//generates all the music
		if (selectMusic == 1){
			playSound("the-final-boss-battle-2.wav", true);
		} else if (selectMusic == 2){
			playSound("moving-mountains.wav", true);
		}else if (selectMusic == 3){
			playSound("Mozart - Lacrimosa.wav", true);
		} else if (selectMusic == 4) {
			playSound("Tobu - Candyland.wav", true);
		} else if (selectMusic == 5){
			playSound("sky high.wav", true);
		}

		// instantiating all JPanels
		mainMenu = new JPanel();
		mainGame = new JPanel();
		rulePage = new JPanel[7];
		gameEnd = new JPanel();
		playerEnterName = new JPanel();
		turnPanel=new JPanel();
		enterName = new JTextField(15);
		menu=new JButton("Menu");
		frame = new JFrame("Guess Who?");

		// Reads Guess who file and puts it into a JLabel
		BufferedImage logo = ImageIO.read(new File("Guess Who Logo.png"));
		Image image = new ImageIcon(logo).getImage();
		frame.setIconImage(image);

		// reads files first to prevent unnecessary usage
		GameMenu.readPicture();
		loadImages();

		//creates main menu
		frame();
		ruleMenu();
		mainMenu();

		//creates the game board and other important GAME MENUS
		GameAlgorithm.initializeGameFileReading();
		GameMenu.gameBoard();
		GameMenu.turnChoice();
		enterName();
		createHighScore();

	}
	/**
	 * Creates the inital frame that will hold all JPanels
	 *
	 * @return void
	 */
	public static void frame(){
		//sets settings for the initial frame
		frame.setSize(1800,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Creates the HighScore panel and add all relevant buttons to the panel
	 *
	 * @throws IOException
	 * @return void
	 */
	public static void createHighScore()throws IOException{
		//ranks all the high scores from least to greatest
		GameAlgorithm.rankHighScores();

		//instantiates title3
		title3 = new JLabel();
		//reads image file and copys it into a JLabel
		BufferedImage logo = ImageIO.read(new File("Guess Who Logo.png"));
		title3 = new JLabel(new ImageIcon(logo));
		title3.setBounds(600, 30, 512, 236);

		// instantiates and changes appearance of HighScorePanel
		highScorePanel = new JPanel();
		highScorePanel.setLayout(null);
		highScorePanel.setBorder(new LineBorder(Color.BLACK,1 ));
		highScorePanel.setOpaque(false);
		highScorePanel.setBounds(0, 0, 1800, 1000);
		highScorePanel.setBackground(Color.decode("#f2524b"));

		//Instantiates and changes appearance of HighScoreDisplayPanel
		highScoreDisplayPanel = new TranslucentPanel(Color.BLACK, .4f);
		highScoreDisplayPanel.setLayout(null);
		highScoreDisplayPanel.setBorder(new LineBorder(Color.BLACK,1 ));
		highScoreDisplayPanel.setBounds(380, 280, 1000, 600);
		highScoreDisplayPanel.setBackground(Color.decode("#f2524b"));
		highScorePanel.add(highScoreDisplayPanel);

		// Changes appearance and functionality of menu JButton
		menu.setBounds(50, 825, 300, 100);
		menu.setBackground(Color.decode("#fa8c87"));
		menu.setBorder(new LineBorder(Color.WHITE, 8));
		menu.setFocusPainted(false);
		menu.setForeground(Color.white);
		menu.setFont(POPPINS.deriveFont(50f));
		menu.addActionListener(new clicked());
		menu.addMouseListener(new MyMouseListener());

		//add highscorepanel to frame
		frame.add(highScorePanel);

		// Changes settings of playerEnterName panel
		playerEnterName.setLayout(null);
		playerEnterName.setBackground(Color.decode("#f2524b"));
		// declare a JLabel
		JLabel highScoreTitle = new JLabel("highscores             scores");
		highScoreTitle.setForeground(Color.white);
		highScoreTitle.setFont(POPPINS.deriveFont(50f));
		highScoreTitle.setBounds(20, 20, 600, 100);
		//adds a JLabel to JPanel
		highScoreDisplayPanel.add(highScoreTitle);

		// Declares arrayLists
		ArrayList<String> highScoreNames = GameAlgorithm.highScoreNames;
		ArrayList<Integer> highScoreScores = GameAlgorithm.highScoreScores;

		//Creates new JPanel and changes visuals and where it is in the panel
		namesDisplayPanel = new JPanel(new GridLayout(5,1));
		namesDisplayPanel.setBounds(20, 120, 400, 400);
		namesDisplayPanel.setOpaque(false);
		highScoreDisplayPanel.add(namesDisplayPanel);

		//Creates new JPanel and changes visuals and where it is in the panel
		scoresDisplayPanel = new JPanel(new GridLayout(5,1));
		scoresDisplayPanel.setBounds(450, 120, 400, 400);
		scoresDisplayPanel.setOpaque(false);

		highScoreDisplayPanel.add(scoresDisplayPanel);

		//PLAYER LABEL
		highScoreNamesLabel = new JLabel[highScoreNames.size()];
		//PLAYER LABEL
		highScoreScoresLabel = new JLabel[highScoreScores.size()];

		//if the number of high scores is less than or equal to five
		if (highScoreNames.size() <= 5){
			//gets the total size of the number of high score names
			for (int i = 0; i < highScoreNames.size(); i++){
				//create a new high score names label
				highScoreNamesLabel[i] = new JLabel();
				highScoreNamesLabel[i].setText(" #" + (i+1) + ": " + highScoreNames.get(i)); //sets text to the high score name
				highScoreNamesLabel[i].setFont(POPPINS.deriveFont(40f)); //sets font
				highScoreNamesLabel[i].setForeground(Color.WHITE);
				highScoreNamesLabel[i].setOpaque(false);
				highScoreNamesLabel[i].setFocusable(false);
				highScoreNamesLabel[i].setBackground(UIManager.getColor("Label.background"));
				highScoreNamesLabel[i].setBorder(UIManager.getBorder("Label.border"));

				//high score scores label create - new JLAbel
				highScoreScoresLabel[i] = new JLabel();
				highScoreScoresLabel[i].setText(String.valueOf(highScoreScores.get(i))); //sets text to the high score
				highScoreScoresLabel[i].setFont(POPPINS.deriveFont(40f));
				highScoreScoresLabel[i].setForeground(Color.WHITE);
				highScoreScoresLabel[i].setOpaque(false);
				highScoreScoresLabel[i].setFocusable(false);
				highScoreScoresLabel[i].setBackground(UIManager.getColor("Label.background"));
				highScoreScoresLabel[i].setBorder(UIManager.getBorder("Label.border"));

				//adds each to the display panel
				namesDisplayPanel.add(highScoreNamesLabel[i]);
				scoresDisplayPanel.add(highScoreScoresLabel[i]);

				//if the current label its on is the first one (HIGHEST SCORE)
				if (i == 0){
					//changes the color
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#ff6fe8"));
				//second place
				} else if (i == 1){
					//changes the color
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#b863fe"));
				//third place
				} else if (i == 2){
					//changes the color
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#5a1ed5"));
				}
			}

			//if the number of high scores is more than five
		} else{
			//limit the loop to only 5
			for (int i = 0; i < 5; i++){
				//create a new high score names label
				highScoreNamesLabel[i] = new JLabel();
				highScoreNamesLabel[i].setText("#" + (i+1) + ": " + highScoreNames.get(i)); //sets text
				highScoreNamesLabel[i].setFont(POPPINS.deriveFont(40f));
				highScoreNamesLabel[i].setForeground(Color.WHITE);
				highScoreNamesLabel[i].setOpaque(false);
				highScoreNamesLabel[i].setFocusable(false);
				highScoreNamesLabel[i].setBackground(UIManager.getColor("Label.background"));
				highScoreNamesLabel[i].setBorder(UIManager.getBorder("Label.border"));

				//creates a new high score scores label
				highScoreScoresLabel[i] = new JLabel();
				highScoreScoresLabel[i].setText(String.valueOf(highScoreScores.get(i))); //sets text to the score
				highScoreScoresLabel[i].setFont(POPPINS.deriveFont(40f));
				highScoreScoresLabel[i].setForeground(Color.WHITE);
				highScoreScoresLabel[i].setOpaque(false);
				highScoreScoresLabel[i].setFocusable(false);
				highScoreScoresLabel[i].setBackground(UIManager.getColor("Label.background"));
				highScoreScoresLabel[i].setBorder(UIManager.getBorder("Label.border"));

				//adds each to the array
				namesDisplayPanel.add(highScoreNamesLabel[i]);
				scoresDisplayPanel.add(highScoreScoresLabel[i]);

				//if the current label its on is the first one (HIGHEST SCORE)
				//first place
				if (i == 0){
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#ff6fe8")); //changes color
				} else if (i == 1){ //second place
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#b863fe")); //changes color
				} else if (i == 2){ //third place
					highScoreNamesLabel[i].setOpaque(true);
					highScoreNamesLabel[i].setBackground(Color.decode("#5a1ed5")); //changes color
				}
			}
		}

		//adds the title to the high score panel, sets to not visible
		highScorePanel.add(title3);
		highScorePanel.setVisible(false);
	}



	/**
	 * This method creates the panel where user can enter their name
	 *
	 * @throws IOException IO EXCEPTION
	 * @return void
	 */
	public static void enterName()throws IOException{
		// Instantiates JPanel and changes visual settings and dimensions
		playerEnterName = new JPanel();
		playerEnterName.setBounds(0, 0, 1800, 1000);
		playerEnterName.setLayout(null);
		playerEnterName.setOpaque(false);
		playerEnterName.setBackground(Color.decode("#f2524b"));

		//Changes visuals and dimensions of JTextField enterName
		enterName.setBounds(450, 500, 850, 100);
		enterName.setText("");
		enterName.setFont(POPPINS.deriveFont(30f));

		//decalre and instantiate askEnterName JLabel
		JLabel askEnterName = new JLabel("Please Enter Your Name");
		// Changes visual settings of the JLabel
		askEnterName.setForeground(Color.white);
		askEnterName.setFont(POPPINS.deriveFont(50f));
		askEnterName.setBounds(600,400 ,1000 ,100 );

		// declare and instantiate extraInfo JLabel
		JLabel extraInfo = new JLabel("(Already played? Type an existing name to load your progress!)");
		// changes visual settings of the Jlabel
		extraInfo.setForeground(Color.white);
		extraInfo.setFont(POPPINS.deriveFont(20f));
		extraInfo.setBounds(550,600 ,1000 ,100 );

		//instantiates enter JButton
		enter=new JButton("Enter");
		// places where JButton is placed in panel
		// adds functionality to JButton as well as change dimensions and visuals of the JButton
		enter.setBounds(825, 700, 150, 100);
		enter.setBackground(Color.decode("#fa8c87"));
		enter.setBorder(new LineBorder(Color.WHITE, 8));
		enter.setFocusPainted(false);
		enter.setForeground(Color.white);
		enter.setFont(POPPINS.deriveFont(20f));
		enter.addActionListener(new clicked());
		enter.addMouseListener(new MyMouseListener());

		// Reads Guess who file and puts it into a JLabel
		BufferedImage logo = ImageIO.read(new File("Guess Who Logo.png"));
		title2 = new JLabel(new ImageIcon(logo));
		title2.setBounds(600, 30, 512, 236);

		// adding everything to the frame and JPanel
		frame.add(playerEnterName);
		playerEnterName.add(title2);
		playerEnterName.add(askEnterName);
		playerEnterName.add(enter);
		playerEnterName.add(enterName);
		playerEnterName.add(extraInfo);
		playerEnterName.setVisible(false);
	}

	/**
	 * This method creates the panel once the user has finished a game
	 * @param win
	 * @throws IOException
	 */
	public static void gameEndMenu(boolean win) throws IOException {
		//true = player win
		//false = AI win
		frame.getContentPane().removeAll();
		frame.repaint();

		//instantiates endMessage JLabel
		JLabel endMessage = new JLabel();
		endMessage.setFont(POPPINS.deriveFont(100f));
		endMessage.setForeground(Color.white);


		//making calls to gameAlgorithm class
		GameAlgorithm.writeAnswersToFile();
		GameAlgorithm.writeQuestionsToFile();
		GameAlgorithm.addPlayerScore(GameMenu.playerName, GameMenu.playerScore, GameMenu.AIScore);

		//instantiates gameEnd JPanel
		gameEnd = new JPanel();
		gameEnd.setSize(1800,1000);
		if (win == true){ // if else statement that changes background color and sound that is played depending on condition as well as the final display message
			playSound("Windows Logon.wav",false);
			gameEnd.setBackground(Color.decode("#14cc6d"));
			endMessage.setText("VICTORY");
			endMessage.setBounds(675, 300, 700, 100);
		} else{
			playSound("lose.wav",false);
			gameEnd.setBackground(Color.decode("#f2524b"));
			endMessage.setText("DEFEAT");
			endMessage.setBounds(700, 300, 700, 100);
		}
		gameEnd.setLayout(null);


		// instantiates resetGame button, changes position of button on JPanel as well as change dimensions and visual settings
		// also adds functionality to the Button
		resetGame = new JButton("new game");
		resetGame.setBounds(785, 500, 200, 100);
		resetGame.setBackground(Color.decode("#fa8c87"));
		resetGame.setBorder(new LineBorder(Color.WHITE, 8));
		resetGame.setFocusPainted(false);
		resetGame.setForeground(Color.white);
		resetGame.setFont(POPPINS.deriveFont(30f));
		resetGame.addActionListener(new clicked());
		resetGame.addMouseListener(new MyMouseListener());

		// instantiates scoreJLabel, changes position of button on JPanel as well as change dimensions and visual settings
		scoreLabel = new JLabel(GameMenu.playerName + " score: " + GameMenu.playerScore);
		scoreLabel.setFont(POPPINS.deriveFont(30f));
		scoreLabel.setBounds(100, 800, 700, 100);
		scoreLabel.setForeground(Color.WHITE);
		// instantiates scoreJLabel, changes position of button on JPanel as well as change dimensions and visual settings
		AILabel = new JLabel("AI score: " + GameMenu.AIScore);
		AILabel.setFont(POPPINS.deriveFont(30f));
		AILabel.setBounds(100, 860, 700, 100);
		AILabel.setForeground(Color.WHITE);

		// adds all components to gameEnd
		gameEnd.add(AILabel);
		gameEnd.add(scoreLabel);
		gameEnd.add(endMessage);
		gameEnd.add(resetGame);
		gameEnd.setVisible(true);
		// adds game end to frame
		frame.add(gameEnd);

	}
	/**
	 * This method removes everything on the panel and reinstantiates each panel and calls all methods that created the JPanels
	 * @return void
	 * @throws IOException
	 */
	public static void newGame() throws IOException {
		// removes everything from frame JFrame
		frame.getContentPane().removeAll();
		frame.repaint();

		//reinstantiates all JPanels
		gameEnd = new JPanel();
		playerEnterName = new JPanel();
		turnPanel = new JPanel();
		mainMenu = new JPanel();
		mainGame = new JPanel();
		GameMenu.gameRemoveAll();

		//method calls
		ruleMenu();
		mainMenu();
		GameMenu.gameBoard();
		GameMenu.turnChoice();
		enterName();
		createHighScore();

		frame.repaint();
	}

	/**
	 * creates the mainMenu panel
	 * @return void
	 * @throws IOException
	 */
	public static void mainMenu()throws IOException{ // CHANGED
		// instantiates MainMenu
		mainMenu =new JPanel();
		frame.add(mainMenu);

		//changes settings and visuals of MainMenu Panelk
		mainMenu.setLayout(null);
		mainMenu.setBorder(new LineBorder(Color.BLACK,1 ));
		mainMenu.setOpaque(false);
		mainMenu.setBounds(0, 0, 1800, 1000);
		mainMenu.setBackground(Color.decode("#f2524b"));

		frame.getContentPane().setBackground(Color.decode("#f2524b"));

		//This creates the title for the main menu
		BufferedImage logo = ImageIO.read(new File("Guess Who Logo.png"));
		title = new JLabel(new ImageIcon(logo));
		title.setBounds(600, 30, 512, 236);

		//This is for all of the buttons
		// all buttons are instantiated and have changed dimensions, placement in panel, color, and added functionality
		play = new JButton("Play");
		play.setMaximumSize(new Dimension(400,100));
		play.setBounds(600, 300, 500, 150);
		play.setBackground(Color.decode("#fa8c87"));
		play.setBorder(new LineBorder(Color.WHITE, 8));
		play.setFocusPainted(false);
		play.setForeground(Color.white);
		play.setFont(POPPINS.deriveFont(50f));
		play.addActionListener(new MainMenu.clicked());
		play.addMouseListener(new MyMouseListener());

		//creates rules button for how to play, sets settings
		rules = new JButton("How To Play");
		rules.setMaximumSize(new Dimension(400,100));
		rules.setBounds(600, 500, 500, 150);
		rules.setFocusPainted(false);
		rules.setBackground(Color.decode("#fa8c87"));
		rules.setBorder(new LineBorder(Color.WHITE, 8));
		rules.setForeground(Color.white);
		rules.setFont(POPPINS.deriveFont(50f));
		rules.addActionListener(new MainMenu.clicked());
		rules.addMouseListener(new MyMouseListener());

		//creates high score button, creates high score
		highScore= new JButton("\uD83C\uDFC6");
		highScore.setBounds(1600, 680, 100, 100);
		highScore.setBackground(Color.decode("#fa8c87"));
		highScore.setBorder(new LineBorder(Color.WHITE, 8));
		highScore.setFocusPainted(false);
		highScore.setForeground(Color.white);
		highScore.setFont(new Font("Semgoe UI", 1, 50));
		highScore.addActionListener(new MainMenu.clicked());
		highScore.addMouseListener(new MyMouseListener());
		mainMenu.add(highScore);

		//quits the game
		quit = new JButton("Quit");
		quit.setMaximumSize(new Dimension(400,100));
		quit.setBounds(600, 700, 500, 150);
		quit.setFocusPainted(false);
		quit.setBackground(Color.decode("#fa8c87"));
		quit.setForeground(Color.white);
		quit.setBorder(new LineBorder(Color.WHITE, 8));
		quit.setFont(POPPINS.deriveFont(50f));
		quit.addActionListener(new MainMenu.clicked());
		quit.addMouseListener(new MyMouseListener());

		//cycle button - cycles through the background - acts as a mystery button
		cycle = new JButton("?");
		cycle.setBounds(1600, 800, 100, 100);
		cycle.setFocusPainted(false);
		cycle.setBackground(Color.decode("#fa8c87"));
		cycle.setForeground(Color.white);
		cycle.setBorder(new LineBorder(Color.WHITE, 8));
		cycle.setFont(POPPINS.deriveFont(50f));
		cycle.addActionListener(new MainMenu.clicked());
		cycle.addMouseListener(new MyMouseListener());

		// adds all components to mainMenu panel
		mainMenu.add(title);
		mainMenu.add(cycle);
		mainMenu.add(play);
		mainMenu.add(rules);
		mainMenu.add(quit);
		mainMenu.setVisible(true);
		mainMenu.revalidate();
		mainMenu.repaint();
	}


	/**
	 * This method loads all the images - runs only once to save memory
	 * @throws IOException
	 */
	public static void loadImages() throws IOException{
		// Load images only once
		turnSelectionPicImage = ImageIO.read(new File("first.png"));
		questionMenuPicImage = ImageIO.read(new File("questionmenu.png"));
		questionMenuAskPicImage = ImageIO.read(new File("questionmenuexample.png"));
		computerAskPicImage = ImageIO.read(new File("computerask.png"));
		playerAnswerPicImage = ImageIO.read(new File("playeranswer.png"));
		computerAskOutputPicImage = ImageIO.read(new File("computeraskoutput.png"));
		playerAskOutputPicImage = ImageIO.read(new File("playeraskoutput.png"));
		boardExamplePicImage = ImageIO.read(new File("boardexample.png"));
		wholeBoardPicImage = ImageIO.read(new File("wholeboard.png"));
		endTurnPicImage = ImageIO.read(new File("endturn.png"));
	}
	/**
	 * This method creates the rule / how to play Jpanels
	 * @return void
	 * @throws IOException
	 */
	public static void ruleMenu()throws IOException{
		// Create JLabels with reused images
		JLabel turnSelectionPic = new JLabel(new ImageIcon(turnSelectionPicImage));
		JLabel questionMenuPic = new JLabel(new ImageIcon(questionMenuPicImage.getScaledInstance(443, 600, Image.SCALE_DEFAULT)));
		JLabel questionMenuAskPic = new JLabel(new ImageIcon(questionMenuAskPicImage));
		JLabel computerAskPic = new JLabel(new ImageIcon(computerAskPicImage));
		JLabel playerAnswerPic = new JLabel(new ImageIcon(playerAnswerPicImage));
		JLabel playerAskOutputPic = new JLabel(new ImageIcon(playerAskOutputPicImage));
		JLabel boardExamplePic = new JLabel(new ImageIcon(boardExamplePicImage.getScaledInstance(534, 350, Image.SCALE_DEFAULT)));
		JLabel wholeBoard = new JLabel(new ImageIcon(wholeBoardPicImage));
		JLabel endturn = new JLabel(new ImageIcon(endTurnPicImage));

		//Next and Back are instantiated and have changed dimensions, placement in panel, color, and added functionality
		back = new JButton("back");
		back.setBounds(360, 825, 300, 100);
		back.setBackground(Color.decode("#fa8c87"));
		back.setBorder(new LineBorder(Color.WHITE, 8));
		back.setFocusPainted(false);
		back.setForeground(Color.white);
		back.setFont(POPPINS.deriveFont(50f));
		back.addActionListener(new clicked());
		back.addMouseListener(new MyMouseListener());
		//creates the next button with all its settings
		next = new JButton("next");
		next.setBounds(1400, 825, 300, 100);
		next.setBackground(Color.decode("#fa8c87"));
		next.setBorder(new LineBorder(Color.WHITE, 8));
		next.setFocusPainted(false);
		next.setForeground(Color.white);
		next.setFont(POPPINS.deriveFont(50f));
		next.addActionListener(new clicked());
		next.addMouseListener(new MyMouseListener());


		//cycles through each page of the rules page and sets settings for each of them
		for(int i=0;i<7;i++){
			rulePage[i] = new JPanel();
			rulePage[i].setLayout(null);
			rulePage[i].setSize(1800,1000);
			rulePage[i].setBackground(Color.decode("#f2524b"));
			rulePage[i].setVisible(false);
			frame.add(rulePage[i]);
		}
		// Jlabel that explains the game <html> and <br/> are used to break the line and format the Jlabel
		//for page 1 of the rulePage array Jpanels
		JLabel ruleText = new JLabel("<html>Welcome to Guess Who!<br/> "
				+ " In this game, you will be playing against a computer by trying to guess each other's chosen character.<br/>"
				+ " Pick a character and keep them in your head<br/>"
				+ " You will take turns asking yes or no questions<br/>"
				+ " to gain clues about who their character is until <br/>"
				+ " one of you is ready to guess<br/>"
				+ " If you guess incorrectly you lose!<br/>"
				+ " Whoever guesses correctly first wins!</html>");
		ruleText.setForeground(Color.white);
		ruleText.setFont(POPPINS.deriveFont(50f));
		ruleText.setBounds(300, 0, 1500, 700);

		// changes visuals and dimensions and adds functionality to menu button
		menu.setBounds(50, 825, 300, 100);
		menu.setBackground(Color.decode("#fa8c87"));
		menu.setBorder(new LineBorder(Color.WHITE, 8));
		menu.setFocusPainted(false);
		menu.setForeground(Color.white);
		menu.setFont(POPPINS.deriveFont(50f));
		menu.addActionListener(new clicked());
		menu.addMouseListener(new MyMouseListener());

		//adds components to panel
		rulePage[0].add(ruleText);
		rulePage[0].add(menu);
		rulePage[0].add(next);


		//for page 2 of the rulePage array Jpanels
		JLabel turnSelection = new JLabel("When You Press Play, Select Who Goes First!");
		turnSelection.setForeground(Color.white);
		turnSelection.setFont(POPPINS.deriveFont(50f));
		turnSelection.setBounds(300, 650, 1400, 100);

		turnSelectionPic.setBounds(250, 30, 1300, 600);
		turnSelectionPic.setBorder(new LineBorder(Color.WHITE, 8));

		//adds components to panel
		rulePage[1].add(turnSelectionPic);
		rulePage[1].add(turnSelection);

		//for page 3 of the rulePage array Jpanels
		wholeBoard.setBounds(250, 30, 1300, 600);
		wholeBoard.setBorder(new LineBorder(Color.WHITE, 8));
		JLabel wholeBoardLabel = new JLabel("This is what the board will look like");
		wholeBoardLabel.setForeground(Color.white);
		wholeBoardLabel.setFont(POPPINS.deriveFont(40f));
		wholeBoardLabel.setBounds(450, 525, 1000, 300);
		JLabel wholeBoardLabel2 = new JLabel("We Will Be Going Over How To Interact With The Different Buttons");
		wholeBoardLabel2.setForeground(Color.white);
		wholeBoardLabel2.setFont(POPPINS.deriveFont(40f));
		wholeBoardLabel2.setBounds(250, 650, 1400, 200);

		//adds components to panel
		rulePage[2].add(wholeBoard);
		rulePage[2].add(wholeBoardLabel);
		rulePage[2].add(wholeBoardLabel2);


		//for page 4 of the rulePage array Jpanels

		// sets position and size of images
		questionMenuPic.setBounds(350, 30, 443, 525);
		questionMenuPic.setBorder(new LineBorder(Color.WHITE, 8));
		questionMenuAskPic.setBounds(850, 30, 452, 496);
		questionMenuAskPic.setBorder(new LineBorder(Color.WHITE, 8));

		// changes font and text color for JLabels as well as position in Jpanel
		JLabel questionMenu = new JLabel("This is the Question Menu.");
		JLabel questionMenu2 = new JLabel("You select a type of question");
		JLabel questionMenu3 = new JLabel("Once selected, you can select a question");
		questionMenu.setForeground(Color.white);
		questionMenu.setFont(POPPINS.deriveFont(40f));
		questionMenu.setBounds(250, 525, 1400, 200);
		questionMenu2.setForeground(Color.white);
		questionMenu2.setFont(POPPINS.deriveFont(40f));
		questionMenu2.setBounds(250, 600, 1400, 200);
		questionMenu3.setForeground(Color.white);
		questionMenu3.setFont(POPPINS.deriveFont(40f));
		questionMenu3.setBounds(850, 475, 1400, 200);


		//adds components to panel
		rulePage[3].add(questionMenuPic);
		rulePage[3].add(questionMenuAskPic);
		rulePage[3].add(questionMenu);
		rulePage[3].add(questionMenu2);
		rulePage[3].add(questionMenu3);

		//for page 5 of the rulePage array Jpanels
		boardExamplePic.setBounds(600, 30, 500, 500); // sets position and size of image
		JLabel boardExample = new JLabel("This is what a character on the board looks like");
		JLabel boardExample2 = new JLabel("If you click on the character it will flip and show the back");
		JLabel boardExample3 = new JLabel("The board is for your own use to keep track of who the opponents character is");

		// changes font and text color for JLabels as well as position in Jpanel
		boardExample.setForeground(Color.white);
		boardExample.setFont(POPPINS.deriveFont(40f));
		boardExample.setBounds(400, 450, 1400, 200);
		boardExample2.setForeground(Color.white);
		boardExample2.setFont(POPPINS.deriveFont(40f));
		boardExample2.setBounds(350, 500, 1400, 200);
		boardExample3.setForeground(Color.white);
		boardExample3.setFont(POPPINS.deriveFont(40f));
		boardExample3.setBounds(100, 550, 1700, 200);

		//adds components to panel
		rulePage[4].add(boardExamplePic);
		rulePage[4].add(boardExample);
		rulePage[4].add(boardExample2);
		rulePage[4].add(boardExample3);

		//for page 6 of the rulePage array Jpanels
		playerAskOutputPic.setBounds(350, 30, 984, 217);
		playerAskOutputPic.setBorder(new LineBorder(Color.WHITE, 8));

		// changes font and text color for JLabels as well as position in Jpanel
		JLabel askOutputLabel = new JLabel("This is what the output will look like once you ask a question");
		askOutputLabel.setFont(POPPINS.deriveFont(40f));
		askOutputLabel.setBounds(250, 250, 1600, 200);
		askOutputLabel.setForeground(Color.white);
		endturn.setBounds(800, 450, 185, 136);
		JLabel endTurnLabel = new JLabel("Press this button after any changes you have made to the board");
		endTurnLabel.setFont(POPPINS.deriveFont(40f));
		endTurnLabel.setBounds(225, 525, 1600, 200);
		endTurnLabel.setForeground(Color.white);

		//adds components to panel
		rulePage[5].add(playerAskOutputPic);
		rulePage[5].add(endturn);
		rulePage[5].add(askOutputLabel);
		rulePage[5].add(endTurnLabel);

		//for page 7 of the rulePage array Jpanels
		computerAskPic.setBounds(350, 30, 1002, 256);
		computerAskPic.setBorder(new LineBorder(Color.WHITE, 8));
		// changes font and text color for JLabels as well as position in Jpanel
		JLabel computerAskLabel = new JLabel("The Computer will ask a question once it is the computers turn");
		computerAskLabel.setFont(POPPINS.deriveFont(40f));
		computerAskLabel.setBounds(250, 225, 1600, 200);
		computerAskLabel.setForeground(Color.white);

		//sets settings for the player answer picture
		playerAnswerPic.setBounds(100, 375, 411, 379);
		playerAnswerPic.setBorder(new LineBorder(Color.WHITE, 8));

		// changes font and text color for JLabels as well as position in Jpanel
		JLabel playerAnswerLabel = new JLabel("Please Answer Yes or No");
		playerAnswerLabel.setFont(POPPINS.deriveFont(40f));
		playerAnswerLabel.setBounds(550, 400, 1200, 200);
		playerAnswerLabel.setForeground(Color.white);
		JLabel reminder = new JLabel("Don't forget to click end turn after you answer!");
		reminder.setFont(POPPINS.deriveFont(40f));
		reminder.setBounds(550, 500, 1200, 200);
		reminder.setForeground(Color.white);

		//adds components to panel
		rulePage[6].add(computerAskPic);
		rulePage[6].add(playerAnswerPic);
		rulePage[6].add(computerAskLabel);
		rulePage[6].add(playerAnswerLabel);
		rulePage[6].add(reminder);




	}
	/**
	 * changes visibility of panels
	 * @return void
	 */
	public static void viewMainMenu(){
		// sets the panel that is the parent to menu button to invisible
		//sets mainMenu to visible
		mainMenu.setVisible(true);
		menu.getParent().setVisible(false);
	}

	/**
	 * changes visibility of panels
	 * @return void
	 */
	public static void viewAIRules(){
		// sets all panels to invisible
		mainMenu.setVisible(false);
		rulePage[0].setVisible(false);
		mainGame.setVisible(false);
		turnPanel.setVisible(false);
		playerEnterName.setVisible(true); // sets playerEnter name panel to visible
	}

	/**
	 * changes visibility of panels
	 * starts timer
	 * @return void
	 */
	public static void viewGameBoard(){
		// sets all other panels to invisible
		mainMenu.setVisible(false);
		rulePage[0].setVisible(false);

		GameMenu.startTime = System.currentTimeMillis();
		GameMenu.timer.restart(); // sets timer to 0
		GameMenu.timer.start(); // starts timer
		mainGame.setVisible(true); // sets mainGame to visible
		turnPanel.setVisible(false);
	}

	/**
	 * changes visibility of panels
	 * adds menu and next button
	 * @return void
	 */
	public static void viewRuleMenu(){
		counter=0; // sets counter to 0
		//sets all panels to invsible and sets rulePage[0] to visible
		mainMenu.setVisible(false);
		mainGame.setVisible(false);
		rulePage[0].setVisible(true);
		rulePage[0].add(menu);
		rulePage[0].add(next);
		turnPanel.setVisible(false);

	}

	/**
	 * This method plays a sound from a .wav file and can be set to loop the sound continuously.
	 *
	 * @param soundName			the path of the .wav file we want to play
	 * @param loopContinuously	boolean for if we want the sound to be looped continuously
	 */
	public static void playSound(String soundName, boolean loopContinuously)
	{
		try {

			//retrieve the .wav file
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));

			//convert the file into a clip
			Clip clip = AudioSystem.getClip( );

			//set the clip to loop continuously if desired
			if(loopContinuously) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

			//play the sound clip
			clip.open(audioInputStream);
			clip.start( );
		}
		catch(Exception e)
		{
			e.printStackTrace( );
		}
	}

	/**
	 * clicked class handles any action performed events
	 *
	 *
	 * @author aaron
	 *
	 */
	static class clicked implements ActionListener{
		public void actionPerformed(ActionEvent e){

			if (e.getSource() == menu){// when menu button is clicked
				playSound("menuclick.wav", false);
				viewMainMenu();
			} else if (e.getSource() == resetGame){ // if clicked button is reset game
				playSound("menuclick.wav", false);
				try {
					newGame();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			} else if(e.getSource() == rules){ // if clicked button is rules
				playSound("menuclick.wav", false);
				viewRuleMenu();
				rulePage[0].add(menu);
			}
			else if(e.getSource() == quit){// if button clicked is quit
				playSound("to-three.wav", false);
				frame.dispose();// closes the frame
			}
			else if(e.getSource() == play){// if button clicked is play
				playSound("start-game.wav", false);
				viewAIRules();

			}
			else if(e.getSource() == back){//if button clicked is back
				playSound("menuclick.wav", false);
				back.getParent().setVisible(false);
				counter-=1; // counter is - 1 so that it can go back to a previous page
				rulePage[counter].setVisible(true); // rulePage uses counter as it is an array of JPanels
				rulePage[counter].add(next);
				if (counter!= 0){ // if counter is 0 it stops adding back button to prevent it from going out of bounds
					rulePage[counter].add(back);
				}
				rulePage[counter].add(menu);
			}
			else if(e.getSource() == next){
				playSound("menuclick.wav", false);
				next.getParent().setVisible(false);
				counter+=1;// counter adds 1 so it can go to the next page
				rulePage[counter].setVisible(true);
				if (counter!= 6){//if counter is 6 it stops adding the next button to Rulepage as anything greater than 6 will be out of bounds
					rulePage[counter].add(next);
				}
				rulePage[counter].add(back);
				rulePage[counter].add(menu);
			}
			//if the enter button is clicked
			else if(e.getSource() == enter){
				playSound("menuclick.wav", false);

				//sets visible
				playerEnterName.setVisible(false);
				turnPanel.setVisible(true);

				//gets text in the button
				GameMenu.playerName = enterName.getText();

				//scans through all the high score names to find if the name entered is equal to a previous name
				for (int i = 0; i < GameAlgorithm.highScoreNames.size() ; i++){
					//if it is equal, then sets the scores to the score found in the high score list
					if (GameMenu.playerName.equals(GameAlgorithm.highScoreNames.get(i))){
						GameMenu.playerScore = (GameAlgorithm.highScoreScores.get(i));
						GameMenu.AIScore = (GameAlgorithm.highScoreAI.get(i));
						GameMenu.AIScoreLabel.setText("AI: " + GameMenu.AIScore);
						GameMenu.playerScoreLabel.setText(GameMenu.playerName + ": " + GameMenu.playerScore);
						//ends loop
						i = 100;
					//if not found, then reset
					} else{
						GameMenu.playerScore = 0;
						GameMenu.AIScore = 0;
					}
				}
				//changes display icons
				GameMenu.qBoxQuestionPanelLabel1.setText(GameMenu.playerName + ": ");
				GameMenu.playerScoreLabel.setText(GameMenu.playerName + ": " + GameMenu.playerScore);
				GameMenu.AIScoreLabel.setText("AI: " + GameMenu.AIScore);

			//if the high score button is pressed
			} else if (e.getSource() == highScore){
				playSound("menuclick.wav", false);
				//sets panels to visible
				highScorePanel.setVisible(true);
				mainMenu.setVisible(false);
				highScorePanel.add(menu);

			//if the cycle button is pressed
			} else if (e.getSource() == cycle){
				playSound("Windows Unlock.wav", false);

				//creates new color array list with all the colours
				ArrayList<Color> color = new ArrayList<>();

				//adds different colors to the array list
				color.add(Color.decode("#f2524b")); //default red
				color.add(Color.decode("#8257fe")); //dark purple
				color.add(Color.decode("#62cd7a")); //green
				color.add(Color.decode("#5a16c3")); //another shade of dark purple
				color.add(Color.decode("#e573fe")); //pink

				//creates arraylist of different colours and adds colours to the arraylist
				ArrayList<Color> buttonColor = new ArrayList<>();
				buttonColor.add(Color.decode("#fa8c87"));
				buttonColor.add(Color.decode("#9676f5"));
				buttonColor.add(Color.decode("#065170"));
				buttonColor.add(Color.decode("#482066"));
				buttonColor.add(Color.decode("#b964ff"));


				//initialize variables for the color
				Color colorValue = color.get((int)(0+Math.random()*color.size()));
				Color colorButtonValue;
				Color currentColor = mainMenu.getBackground();


				//generates a random number
				int genNumber;
				do{
					genNumber = (int)(0+Math.random()*color.size());

					//assigns the color a Color value with the Color Object
					colorValue = color.get(genNumber);

					//gets the current color
					currentColor = mainMenu.getBackground();
				}
				while (colorValue.equals(currentColor));

				//sets the color to the button color array list of the specific color for the background
				colorButtonValue = buttonColor.get(genNumber);

				// Changes colors of buttons
				play.setBackground(colorButtonValue);
				quit.setBackground(colorButtonValue);
				rules.setBackground(colorButtonValue);
				menu.setBackground(colorButtonValue);
				back.setBackground(colorButtonValue);
				next.setBackground(colorButtonValue);
				enter.setBackground(colorButtonValue);
				cycle.setBackground(colorButtonValue);
				yes.setBackground(colorButtonValue);
				no.setBackground(colorButtonValue);
				highScore.setBackground(colorButtonValue);
				GameMenu.playerFirst.setBackground(colorButtonValue);
				GameMenu.aiFirst.setBackground(colorButtonValue);

				//changes color of the background
				mainMenu.setBackground(colorValue);
				//changes color of the JFrame
				frame.getContentPane().setBackground(colorValue);

			}
		}
	}
	/**
	 * MyMouseListener class handles all mouse events in java
	 * provides methods to respond to different mouse actions
	 *
	 * @author aaron
	 *
	 */
	static class MyMouseListener implements MouseListener {
		// method called when mouse button is clicked
		public void mouseClicked(MouseEvent e){   // moves the box at the mouse location
		}
		//method called when mouse button is pressed
		public void mousePressed(MouseEvent e){   // MUST be implemented even if not used!
		}
		// method called when mouse button is released
		public void mouseReleased(MouseEvent e){  // MUST be implemented even if not used!
		}
		// method called when mouse enters a component
		public void mouseEntered(MouseEvent e){   // once mouse is hovering over a button it increases border thickness to simulate a highlight to show that button is being hovered
			playSound("click-close.wav", false);

			if (e.getSource() == play){ // if componenet hovered is play
				play.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == quit){// if componenet hovered is quit
				quit.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == rules){// if componenet hovered is rules
				rules.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == menu){// if componenet hovered is menu
				menu.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == back){ // if componenet hovered is back
				back.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == next){ // if componenet hovered is next
				next.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == resetGame){ // if componenet hovered is resetgame
				resetGame.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == enter){ // if componenet hovered is enter
				enter.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == cycle){ // if componenet hovered is cycle
				cycle.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == yes){// if componenet hovered is yes
				yes.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == no){// if componenet hovered is no
				no.setBorder(new LineBorder(Color.WHITE, 15));
			}
			else if(e.getSource() == highScore){ // if componenet hovered is highScore
				highScore.setBorder(new LineBorder(Color.WHITE, 15));
			}

		}
		// method called when mouse exits component
		public void mouseExited(MouseEvent e){// once mouse exits it removes the increased border size
			if (e.getSource() == play){ // if componenet hovered is play
				play.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == quit){ // if componenet hovered isquit
				quit.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == rules){ // if componenet hovered is rules
				rules.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == menu){// if componenet hovered is menu
				menu.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == back){ // if componenet hovered is back
				back.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == next){ // if componenet hovered is next
				next.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == resetGame){ // if componenet hovered is reset game
				resetGame.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == enter){ // if componenet hovered is enter
				enter.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == cycle){ // if component hovered is cycle
				cycle.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == yes){// if componenet hovered is yes
				yes.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == no){ // if componenet hovered is no
				no.setBorder(new LineBorder(Color.WHITE, 8));
			}
			else if(e.getSource() == highScore){ // if componenet hovered is highscore
				highScore.setBorder(new LineBorder(Color.WHITE, 8));
			}
		}
	}

}