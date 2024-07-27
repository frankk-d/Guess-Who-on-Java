/**GameMenu.java
 * Description: This class operates the main game menu - all the menus related to the GAMEPLAY. This does not include
 *              the main game. This class inherits MainMenu as both are menu classes. This class is also in charge of
 *              linking the front end GUI with the back end GameAlgorithm.
 * Date: January 12, 2023
 * * 	Assignment: Guess Who Project
 *
 * Contributors:
 * - Aaron Tom
 * - Frank Ding
 * - Nick Zhu
 */

//import important classes
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;

/**
 * GameMenu class start
 */
public class GameMenu extends MainMenu{
    //All sub Panels that are used within gameBoard method
    static JLabel questionTitle;
    static JPanel questionMenu;
    static JPanel questionMenuGrid;

    static JPanel board;
    static JPanel qBox;

    //AI and Player First JButtons
    static JButton playerFirst;
    static JButton aiFirst;

    //All menu items that are related to questions
    static JButton home;
    static JPanel eyeMenu;
    static JPanel genderMenu;
    static JPanel skinMenu;
    static JPanel hairMenu;
    static JPanel faceMenu;
    static JPanel glassesMenu;
    static JPanel teethMenu;
    static JPanel hatMenu;
    static JPanel earMenu;
    static JPanel characterGuessMenu;
    static JLabel updateText;

    // declaration of variables used in next few methods
    static JFrame askWindow;
    static JPanel askPanel;
    //Jbuttons for question menu items
    static JButton yes;
    static JButton eye;
    static JButton no;

    static JButton gender;
    static JLabel answer;

    static JButton skin;
    static JLabel answer2;

    static JButton hair;
    static JButton face;
    static JButton glasses;
    static JButton teeth;
    static JButton hat;
    static JButton ear;
    static JButton characterGuess;
    static JButton brownEye;
    static JButton greenEye;
    static JButton blueEye;
    static JButton male;
    static JButton female;
    static JButton dark;
    static JButton light;
    static JButton blackHair;
    static JButton brownHair;
    static JButton gingerHair;
    static JButton blondeHair;
    static JButton whiteHair;
    static JButton shortHair;
    static JButton tiedHair;
    static JButton longHair;
    static JButton bald;
    static JButton faceHair;
    static JButton noFaceHair;
    static JButton yesGlasses;
    static JButton noGlasses;
    static JButton yesTeeth;
    static JButton noTeeth;
    static JButton yesHat;
    static JButton noHat;
    static JButton yesPierce;
    static JButton noPierce;
    static JButton qMenu;

    //character click JButtons
    static JButton[][] character;
    //Array to determine which is flipped and what is not flipped
    static boolean[][] characterFlipped;
    //Jbutton to store the names
    static JButton[][] names;

    //Turn label - outputs turn switch
    static JLabel turnLabel;
    //creates the end turn panel
    static JPanel endTurnPanel;
    static JButton endTurnButton;

    //creates the question box question panels and labels
    static JPanel qBoxQuestionPanel1;
    static JPanel qBoxQuestionPanel2;
    static JTextArea qBoxQuestionPanelLabel1;
    static JTextArea qBoxQuestionPanelLabel2;

    //score board
    static JPanel scoreBoard;
    static JLabel playerScoreLabel;
    static JLabel AIScoreLabel;
    static int playerScore = 0;
    static int AIScore = 0;

    //timer labels
    static JPanel timerPanel;
    static JLabel timerLabel;
    static Timer timer;
    static long startTime;

    static long currentTime;
    static long elapsedTime;
    static int seconds = 0;
    static int tenSeconds = 0;
    static int minutes = 0;

    //determines if there was an error or not
    static boolean error = false;

    //all static variables for the ERROR PAGE
    static JPanel errorPanel = new JPanel();
    static JLabel errorLineLabel1;
    static JButton[][] errorNames = new JButton[12][2];
    static JPanel errorGuessPanel = new JPanel();
    static JLabel challengeLabel = new JLabel ("DEFEAT!");
    static JPanel challengeLabelPanel = new JPanel();


    //All the JLabels for the error panel, but after the player clicked their desired character
    static JPanel qDisplayPanel = new JPanel();
    static JPanel valueDisplayPanel = new JPanel();
    static JTextArea[] qDisplayLabel;
    static JTextArea[] valueDisplayLabel;
    static JPanel totalValueDisplayPanel =  new TranslucentPanel(Color.BLACK, .5f);
    static JLabel errorHelpText = new JLabel("Coloured answers are the ones you answered incorrectly!");
    static JLabel errorHelpText2;
    static JButton proceedButton = new JButton("Proceed");

    //gets scale for the windows
    static final int SCALEX = 120;
    static final int SCALEY = 162;

    //grid of names
    static final String[][] gridOfNames =  {{"Al","Amy"},
            {"Ben","Carmen"},
            {"Daniel","David"},
            {"Emma","Eric"},
            {"Farah","Gabe"},
            {"Joe","Jordan"},
            {"Katie","Laura"},
            {"Leo","Lily"},
            {"Liz","Mia"},
            {"Mike","Nick"},
            {"Olivia","Rachel"},
            {"Sam","Sofia"}};

    //array of character images
    static BufferedImage[][] bufferedImageArray = new BufferedImage[4][6];
    static Image[][] imageArray = new Image[4][6];

    //player name
    static String playerName = "";


    /**
     * This method creates the turn choice for the player - player first or AI
     */
    public static void turnChoice(){
        //turn panel - sets bounds, adds to the frame
        turnPanel.setBounds(0, 0, 1800, 1000);
        turnPanel.setBackground(Color.decode("#f2524b"));
        turnPanel.setOpaque(false);
        turnPanel.setLayout(null);
        frame.add(turnPanel);

        //player button - sets bounds, configures settings
        playerFirst.setBounds(360, 500, 360, 200);
        playerFirst.setBackground(Color.decode("#ff6f69"));
        playerFirst.setForeground(Color.WHITE);
        playerFirst.setBorder(new LineBorder(Color.WHITE, 8));
        playerFirst.setFocusPainted(false);
        //sets font to POPPINS
        playerFirst.setFont(POPPINS.deriveFont(50f));

        //configures settings for the AI First button
        aiFirst.setBounds(1080, 500, 360, 200);
        aiFirst.setBackground(Color.decode("#ff6f69"));
        aiFirst.setForeground(Color.WHITE);
        aiFirst.setBorder(new LineBorder(Color.WHITE, 8));
        aiFirst.setFocusPainted(false);
        aiFirst.setFont(POPPINS.deriveFont(50f));

        //configures settings for the turn label to tell player who goes first
        turnLabel = new JLabel("Who Goes First?");
        turnLabel.setFont(POPPINS.deriveFont(100f));
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setBounds(500,100,1000,200);

        //adds components to the turn panel
        turnPanel.add(turnLabel);
        turnPanel.add(playerFirst);
        turnPanel.add(aiFirst);

        //adds action listener to buttons
        playerFirst.addActionListener(new clicked());
        aiFirst.addActionListener(new clicked());

        //sets turn panel to invisible
        turnPanel.setVisible(false);
    }


    /**
     * Uploads the picture to an arraylist to save space
     */
    public static void uploadPicture(){
        //scans through each row column and uploads the picture directly
        for (int r = 0; r < 4; r++){
            for (int c = 0; c < 6; c++){
                character[r][c].setIcon(new ImageIcon(imageArray[r][c]));
            }
        }
    }

    /**
     * Uploads the picture to an arraylist to save space - METHOD OVERLOADING
     * @param row row pressed
     * @param col col pressed
     * @throws IOException
     */
    public static void uploadPicture(int row, int col)throws IOException{
        //scans through each row column, if the value is found, sets it to the image icon
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 6; c++) {
                if (r == row && c == col){
                    character[r][c].setIcon(new ImageIcon(imageArray[r][c]));
                }
            }
        }
    }
    /**
     *
     * this method is used to read all picture files that are character images
     * @return void
     * @throws IOException
     */
    public static void readPicture() throws IOException {
        String[] characterNames = {"AL", "Amy", "Ben", "Carmen", "Daniel", "David",
                "Emma", "Eric", "Farah", "Gabe", "Joe", "Jordan",
                "Katie", "Laura", "Leo", "Lily", "Liz", "Mia",
                "Mike", "Nick", "Olivia", "Rachel", "Sam", "Sofia"};

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 6; c++) {
                String fileName = characterNames[r * 6 + c] + ".png"; // scans through the array of names and creates a string with each name to create a file name
                bufferedImageArray[r][c] = ImageIO.read(new File(fileName)); // sets a cell in the 2d array as a file
                imageArray[r][c] = bufferedImageArray[r][c].getScaledInstance(SCALEX, SCALEY, Image.SCALE_DEFAULT);// resizes imagine and places it into another 2d array
            }
        }
    }
    /**
     *
     * this method is used to read a picture file that is the back of the guess who card and uploads it to a 2d array
     * @return void
     * @throws IOException
     */
    public static void backUploadPicture(int row, int col) throws IOException {
        BufferedImage bufferedImage;
        Image image;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 6; c++) {
                if (r == row && c == col){
                    bufferedImage = ImageIO.read(new File("cardBack.png")); // reads file
                    image = bufferedImage.getScaledInstance(SCALEX, SCALEY, Image.SCALE_DEFAULT); // changes size of image
                    character[r][c].setIcon(new ImageIcon(image));// adds image to 2d array
                }
            }
        }
    }
    /**
     *
     * this method is used to create the gameBoard
     * @return void
     * @throws IOException
     */
    public static void gameBoard() throws IOException {
        duplicatorFixer(); // method call to prevent duplications
        board.setBounds(500, 30, 780, 650); // sets size and position of board Jpanel

        // changes visual settings of board JPanel
        GridLayout testLayout = new GridLayout(4,6);
        board.setLayout(testLayout);
        board.setBackground(new Color(0f,0f,0f,0f));
        testLayout.setHgap(10);
        testLayout.setVgap(10);


        // nested for loop used to change size of buttons in the 2d array and adds functionality and adds it to Jpanel
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 6; c++) {
                character[r][c] = new JButton("  ");
                character[r][c].setMaximumSize(new Dimension(200, 150));
                character[r][c].addActionListener(new gridClicked());
                character[r][c].setBackground(Color.BLACK);
                board.add(character[r][c]);


                character[r][c].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

            }
        }

        uploadPicture(); // calls upload Picture method
        board.setVisible(true); // sets board to be visible


        // sets size and background color of maingame Jpanel
        mainGame.setSize(1800, 1000);
        mainGame.setBackground(Color.decode("#f23d65"));
        mainGame.setLayout(null);


        characterGuessMenu.removeAll();// removes all components in characterGuessMenu
        characterGuessMenu.setVisible(false);// sets visibility to false
        characterGuessMenu.setBounds(40, 100, 400, 900); // sets position and size of panel
        characterGuessMenu.setBackground(new Color(0f,0f,0f,0f));// sets background of panel

        GridLayout characterGuessMenuLayout = new GridLayout(12,2); // creates a gridlayout for a layout manager
        characterGuessMenu.setLayout(characterGuessMenuLayout); // changes layout of characterGuessMenu
        characterGuessMenuLayout.setHgap(5);
        characterGuessMenuLayout.setVgap(5);


        // nested loop used to create 2d grid of buttons with character names from another 2d array

        for (int i=0;i<12;i++){
            for(int j=0;j<2;j++){
            	// adds functionality to all buttons and changes color and size of buttons
                names[i][j] = new JButton(gridOfNames[i][j]);
                characterGuessMenu.add(names[i][j]);
                names[i][j].addActionListener(new clicked());
                names[i][j].setBackground(Color.decode("#ff6688"));
                names[i][j].setBorder(new LineBorder(Color.WHITE, 8));
                names[i][j].setFocusPainted(false);
                names[i][j].setFont(POPPINS.deriveFont(20f));
                names[i][j].setForeground(Color.white);
                names[i][j].addMouseListener(new MyMouseListener());

            }
        }
        //question menu
        questionTitle.setFont(POPPINS.deriveFont(40f));
        questionTitle.setForeground(Color.WHITE);
        questionTitle.setMaximumSize(new Dimension(400, 78));

        //
        questionMenu.setBounds(40, 0, 400, 900);
        questionMenu.setLayout(new BoxLayout(questionMenu, BoxLayout.Y_AXIS));
        questionMenu.setBackground(mainGame.getBackground());


        //eyes
        eyeMenu.setBounds(40, 100, 400, 900);
        eyeMenu.setBackground(new Color(0f,0f,0f,0f));
        eyeMenu.setLayout(new BoxLayout(eyeMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        brownEye.setMaximumSize(new Dimension(400, 90));
        brownEye.setBackground(Color.decode("#ff6688"));
        brownEye.setBorder(new LineBorder(Color.WHITE, 8));
        brownEye.setFocusPainted(false);
        brownEye.setForeground(Color.white);

        // changes size of button and changes color and background of button
        greenEye.setMaximumSize(new Dimension(400, 90));
        greenEye.setBackground(Color.decode("#ff6688"));
        greenEye.setBorder(new LineBorder(Color.WHITE, 8));
        greenEye.setFocusPainted(false);
        greenEye.setForeground(Color.white);

        // changes size of button and changes color and background of button
        blueEye.setMaximumSize(new Dimension(400, 90));
        blueEye.setBackground(Color.decode("#ff6688"));
        blueEye.setBorder(new LineBorder(Color.WHITE, 8));
        blueEye.setFocusPainted(false);
        blueEye.setForeground(Color.white);

        //changes font of buttons
        brownEye.setFont(POPPINS.deriveFont(20f));
        greenEye.setFont(POPPINS.deriveFont(20f));
        blueEye.setFont(POPPINS.deriveFont(20f));


        //adds buttons to eyeMenu panel
        eyeMenu.add(blueEye);
        eyeMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates a gap between buttons
        eyeMenu.add(greenEye);
        eyeMenu.add(Box.createRigidArea(new Dimension(0,10)));
        eyeMenu.add(brownEye);
        eyeMenu.add(Box.createRigidArea(new Dimension(0,10)));



        //gender
        genderMenu.setBounds(40, 100, 400, 900);
        genderMenu.setBackground(new Color(0f,0f,0f,0f));
        genderMenu.setLayout(new BoxLayout(genderMenu, BoxLayout.Y_AXIS));
        // changes size of button and changes color and background of button
        male.setMaximumSize(new Dimension(400, 90));
        male.setBackground(Color.decode("#ff6688"));
        male.setBorder(new LineBorder(Color.WHITE, 8));
        male.setFocusPainted(false);
        male.setForeground(Color.white);

        // changes size of button and changes color and background of button
        female.setMaximumSize(new Dimension(400, 90));
        female.setBackground(Color.decode("#ff6688"));
        female.setBorder(new LineBorder(Color.WHITE, 8));
        female.setFocusPainted(false);
        female.setForeground(Color.white);

        // changes font of buttons
        male.setFont(POPPINS.deriveFont(20f));
        female.setFont(POPPINS.deriveFont(20f));

        // adds buttons to panel
        genderMenu.add(male);
        genderMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates gap between buttons
        genderMenu.add(female);
        genderMenu.add(Box.createRigidArea(new Dimension(0,10)));



        skinMenu.setBounds(40, 100, 400, 900);
        skinMenu.setBackground(new Color(0f,0f,0f,0f));
        skinMenu.setLayout(new BoxLayout(skinMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        dark.setMaximumSize(new Dimension(400, 90));
        dark.setBackground(Color.decode("#ff6688"));
        dark.setBorder(new LineBorder(Color.WHITE, 8));
        dark.setFocusPainted(false);
        dark.setForeground(Color.white);

        // changes size of button and changes color and background of button
        light.setMaximumSize(new Dimension(400, 90));
        light.setBackground(Color.decode("#ff6688"));
        light.setBorder(new LineBorder(Color.WHITE, 8));
        light.setFocusPainted(false);
        light.setForeground(Color.white);

        // sets font of buttons
        dark.setFont(POPPINS.deriveFont(15f));
        light.setFont(POPPINS.deriveFont(15f));

        // adds buttons to panel
        skinMenu.add(dark);
        skinMenu.add(Box.createRigidArea(new Dimension(0,10)));// creates gap between buttons
        skinMenu.add(light);
        skinMenu.add(Box.createRigidArea(new Dimension(0,10)));



        hairMenu.setBounds(40, 30, 400, 900);
        hairMenu.setOpaque(false);
//        hairMenu.setBackground(new Color(0f,0f,0f,0f));
        hairMenu.setLayout(new BoxLayout(hairMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        blackHair.setMaximumSize(new Dimension(400, 90));
        blackHair.setBackground(Color.decode("#ff6688"));
        blackHair.setBorder(new LineBorder(Color.WHITE, 8));
        blackHair.setFocusPainted(false);
        blackHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        brownHair.setMaximumSize(new Dimension(400, 90));
        brownHair.setBackground(Color.decode("#ff6688"));
        brownHair.setBorder(new LineBorder(Color.WHITE, 8));
        brownHair.setFocusPainted(false);
        brownHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        gingerHair.setMaximumSize(new Dimension(400, 90));
        gingerHair.setBackground(Color.decode("#ff6688"));
        gingerHair.setBorder(new LineBorder(Color.WHITE, 8));
        gingerHair.setFocusPainted(false);
        gingerHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        blondeHair.setMaximumSize(new Dimension(400, 90));
        blondeHair.setBackground(Color.decode("#ff6688"));
        blondeHair.setBorder(new LineBorder(Color.WHITE, 8));
        blondeHair.setFocusPainted(false);
        blondeHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        whiteHair.setMaximumSize(new Dimension(400, 90));
        whiteHair.setBackground(Color.decode("#ff6688"));
        whiteHair.setBorder(new LineBorder(Color.WHITE, 8));
        whiteHair.setFocusPainted(false);
        whiteHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        shortHair.setMaximumSize(new Dimension(400, 90));
        shortHair.setBackground(Color.decode("#ff6688"));
        shortHair.setBorder(new LineBorder(Color.WHITE, 8));
        shortHair.setFocusPainted(false);
        shortHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        tiedHair.setMaximumSize(new Dimension(400, 90));
        tiedHair.setBackground(Color.decode("#ff6688"));
        tiedHair.setBorder(new LineBorder(Color.WHITE, 8));
        tiedHair.setFocusPainted(false);
        tiedHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        longHair.setMaximumSize(new Dimension(400, 90));
        longHair.setBackground(Color.decode("#ff6688"));
        longHair.setBorder(new LineBorder(Color.WHITE, 8));
        longHair.setFocusPainted(false);
        longHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        bald.setMaximumSize(new Dimension(400, 90));
        bald.setBackground(Color.decode("#ff6688"));
        bald.setBorder(new LineBorder(Color.WHITE, 8));
        bald.setFocusPainted(false);
        bald.setForeground(Color.white);

        // changes font of all button text
        blackHair.setFont(POPPINS.deriveFont(20f));
        brownHair.setFont(POPPINS.deriveFont(20f));
        gingerHair.setFont(POPPINS.deriveFont(20f));
        blondeHair.setFont(POPPINS.deriveFont(20f));
        whiteHair.setFont(POPPINS.deriveFont(20f));
        shortHair.setFont(POPPINS.deriveFont(20f));
        tiedHair.setFont(POPPINS.deriveFont(15f));
        longHair.setFont(POPPINS.deriveFont(20f));
        bald.setFont(POPPINS.deriveFont(20f));

        // adds buttons to panel
        hairMenu.add(bald);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates gap between buttons
        hairMenu.add(longHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(tiedHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(shortHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(whiteHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(blondeHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(gingerHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(brownHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));
        hairMenu.add(blackHair);
        hairMenu.add(Box.createRigidArea(new Dimension(0,10)));



        faceMenu.setBounds(40, 100, 400, 900);
        faceMenu.setBackground(new Color(0f,0f,0f,0f));
        faceMenu.setLayout(new BoxLayout(faceMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        faceHair.setMaximumSize(new Dimension(400, 90));
        faceHair.setBackground(Color.decode("#ff6688"));
        faceHair.setBorder(new LineBorder(Color.WHITE, 8));
        faceHair.setFocusPainted(false);
        faceHair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        noFaceHair.setMaximumSize(new Dimension(400, 90));
        noFaceHair.setBackground(Color.decode("#ff6688"));
        noFaceHair.setBorder(new LineBorder(Color.WHITE, 8));
        noFaceHair.setFocusPainted(false);
        noFaceHair.setForeground(Color.white);
        // sets font of button text
        faceHair.setFont(POPPINS.deriveFont(15f));
        noFaceHair.setFont(POPPINS.deriveFont(15f));

        // adds buttons to panel
        faceMenu.add(faceHair);
        faceMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates gap between buttons
        faceMenu.add(noFaceHair);
        faceMenu.add(Box.createRigidArea(new Dimension(0,10)));

        glassesMenu.setBounds(40, 100, 400, 900);
        glassesMenu.setBackground(new Color(0f,0f,0f,0f));
        glassesMenu.setLayout(new BoxLayout(glassesMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        yesGlasses.setMaximumSize(new Dimension(400, 90));
        yesGlasses.setBackground(Color.decode("#ff6688"));
        yesGlasses.setBorder(new LineBorder(Color.WHITE, 8));
        yesGlasses.setFocusPainted(false);
        yesGlasses.setForeground(Color.white);

        // changes size of button and changes color and background of button
        noGlasses.setMaximumSize(new Dimension(400, 90));
        noGlasses.setBackground(Color.decode("#ff6688"));
        noGlasses.setBorder(new LineBorder(Color.WHITE, 8));
        noGlasses.setFocusPainted(false);
        noGlasses.setForeground(Color.white);

        // changes font of button text
        yesGlasses.setFont(POPPINS.deriveFont(20f));
        noGlasses.setFont(POPPINS.deriveFont(20f));

        // adds buttons to panel
        glassesMenu.add(yesGlasses);
        glassesMenu.add(Box.createRigidArea(new Dimension(0,10)));// creates gap between buttons
        glassesMenu.add(noGlasses);
        glassesMenu.add(Box.createRigidArea(new Dimension(0,10)));


        teethMenu.setBounds(40, 100, 400, 900);
        teethMenu.setBackground(new Color(0f,0f,0f,0f));
        teethMenu.setLayout(new BoxLayout(teethMenu, BoxLayout.Y_AXIS));

        // changes size of button and changes color and background of button
        yesTeeth.setMaximumSize(new Dimension(400, 90));
        yesTeeth.setBackground(Color.decode("#ff6688"));
        yesTeeth.setBorder(new LineBorder(Color.WHITE, 8));
        yesTeeth.setFocusPainted(false);
        yesTeeth.setForeground(Color.white);

        // changes size of button and changes color and background of button
        noTeeth.setMaximumSize(new Dimension(400, 90));
        noTeeth.setBackground(Color.decode("#ff6688"));
        noTeeth.setBorder(new LineBorder(Color.WHITE, 8));
        noTeeth.setFocusPainted(false);
        noTeeth.setForeground(Color.white);

        // sets font of button text
        yesTeeth.setFont(POPPINS.deriveFont(20f));
        noTeeth.setFont(POPPINS.deriveFont(20f));

        //adds buttons to panel
        teethMenu.add(yesTeeth);
        teethMenu.add(Box.createRigidArea(new Dimension(0,10)));// creates gap between buttons
        teethMenu.add(noTeeth);
        teethMenu.add(Box.createRigidArea(new Dimension(0,10)));

        hatMenu.setBounds(40, 100, 400, 900);
        hatMenu.setLayout(new BoxLayout(hatMenu, BoxLayout.Y_AXIS));
        hatMenu.setBackground(new Color(0f,0f,0f,0f));

        // changes size of button and changes color and background of button
        yesHat.setMaximumSize(new Dimension(400, 90));
        yesHat.setBackground(Color.decode("#ff6688"));
        yesHat.setBorder(new LineBorder(Color.WHITE, 8));
        yesHat.setFocusPainted(false);
        yesHat.setForeground(Color.white);

        // changes size of button and changes color and background of button
        noHat.setMaximumSize(new Dimension(400, 90));
        noHat.setBackground(Color.decode("#ff6688"));
        noHat.setBorder(new LineBorder(Color.WHITE, 8));
        noHat.setFocusPainted(false);
        noHat.setForeground(Color.white);

        // sets font of button text
        yesHat.setFont(POPPINS.deriveFont(20f));
        noHat.setFont(POPPINS.deriveFont(20f));
        // adds buttons to panel
        hatMenu.add(yesHat);
        hatMenu.add(Box.createRigidArea(new Dimension(0,10)));// creates gap between buttons
        hatMenu.add(noHat);
        hatMenu.add(Box.createRigidArea(new Dimension(0,10)));




        earMenu.setBounds(40, 100, 400, 900);
        earMenu.setLayout(new BoxLayout(earMenu, BoxLayout.Y_AXIS));
        earMenu.setBackground(new Color(0f,0f,0f,0f));

        // changes size of button and changes color and background of button
        yesPierce.setMaximumSize(new Dimension(400, 90));
        yesPierce.setBackground(Color.decode("#ff6688"));
        yesPierce.setBorder(new LineBorder(Color.WHITE, 8));
        yesPierce.setFocusPainted(false);
        yesPierce.setForeground(Color.white);

        // changes size of button and changes color and background of button
        noPierce.setMaximumSize(new Dimension(400, 90));
        noPierce.setBackground(Color.decode("#ff6688"));
        noPierce.setBorder(new LineBorder(Color.WHITE, 8));
        noPierce.setFocusPainted(false);
        noPierce.setForeground(Color.white);

        //changes font of button text
        yesPierce.setFont(POPPINS.deriveFont(15f));
        noPierce.setFont(POPPINS.deriveFont(15f));

        //adds buttons to panel
        earMenu.add(yesPierce);
        earMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates gap between buttons
        earMenu.add(noPierce);
        earMenu.add(Box.createRigidArea(new Dimension(0,10)));



        // changes size of button and changes color and background of button
        eye.setMaximumSize(new Dimension(400, 78));
        eye.setBackground(Color.decode("#ff6688"));
        eye.setBorder(new LineBorder(Color.WHITE, 8));
        eye.setFocusPainted(false);
        eye.setForeground(Color.white);

        // changes size of button and changes color and background of button
        gender.setMaximumSize(new Dimension(400, 78));
        gender.setBackground(Color.decode("#ff6688"));
        gender.setBorder(new LineBorder(Color.WHITE, 8));
        gender.setFocusPainted(false);
        gender.setForeground(Color.white);

        // changes size of button and changes color and background of button
        skin.setMaximumSize(new Dimension(400, 78));
        skin.setBackground(Color.decode("#ff6688"));
        skin.setBorder(new LineBorder(Color.WHITE, 8));
        skin.setFocusPainted(false);
        skin.setForeground(Color.white);

        // changes size of button and changes color and background of button
        hair.setMaximumSize(new Dimension(400, 78));
        hair.setBackground(Color.decode("#ff6688"));
        hair.setBorder(new LineBorder(Color.WHITE, 8));
        hair.setFocusPainted(false);
        hair.setForeground(Color.white);

        // changes size of button and changes color and background of button
        face.setMaximumSize(new Dimension(400, 78));
        face.setBackground(Color.decode("#ff6688"));
        face.setBorder(new LineBorder(Color.WHITE, 8));
        face.setFocusPainted(false);
        face.setForeground(Color.white);

        // changes size of button and changes color and background of button
        glasses.setMaximumSize(new Dimension(400, 78));
        glasses.setBackground(Color.decode("#ff6688"));
        glasses.setBorder(new LineBorder(Color.WHITE, 8));
        glasses.setFocusPainted(false);
        glasses.setForeground(Color.white);

        // changes size of button and changes color and background of button
        teeth.setMaximumSize(new Dimension(400, 78));
        teeth.setBackground(Color.decode("#ff6688"));
        teeth.setBorder(new LineBorder(Color.WHITE, 8));
        teeth.setFocusPainted(false);
        teeth.setForeground(Color.white);

        // changes size of button and changes color and background of button
        hat.setMaximumSize(new Dimension(400, 79));
        hat.setBackground(Color.decode("#ff6688"));
        hat.setBorder(new LineBorder(Color.WHITE, 8));
        hat.setFocusPainted(false);
        hat.setForeground(Color.white);

        // changes size of button and changes color and background of button
        ear.setMaximumSize(new Dimension(400, 79));
        ear.setBackground(Color.decode("#ff6688"));
        ear.setBorder(new LineBorder(Color.WHITE, 8));
        ear.setFocusPainted(false);
        ear.setForeground(Color.white);

        // changes size of button and changes color and background of button
        characterGuess.setMaximumSize(new Dimension(400, 79));
        characterGuess.setBackground(Color.decode("#ff6688"));
        characterGuess.setBorder(new LineBorder(Color.WHITE, 8));
        characterGuess.setFocusPainted(false);
        characterGuess.setForeground(Color.white);

        // changes font of button text
        eye.setFont(POPPINS.deriveFont(30f));
        gender.setFont(POPPINS.deriveFont(30f));
        skin.setFont(POPPINS.deriveFont(30f));
        hair.setFont(POPPINS.deriveFont(30f));
        face.setFont(POPPINS.deriveFont(30f));
        glasses.setFont(POPPINS.deriveFont(30f));
        teeth.setFont(POPPINS.deriveFont(30f));
        hat.setFont(POPPINS.deriveFont(30f));
        ear.setFont(POPPINS.deriveFont(30f));
        characterGuess.setFont(POPPINS.deriveFont(30f));

        // changes size of button and changes color and background of button
        qMenu.setFont(POPPINS.deriveFont(30f));
        qMenu.setBackground(Color.decode("#28e0cb"));
        qMenu.setBorder(new LineBorder(Color.WHITE, 8));
        qMenu.setFocusPainted(false);
        qMenu.setForeground(Color.white);

        // adds functionality to buttons with call to clicked class
        eye.addActionListener(new clicked());
        gender.addActionListener(new clicked());
        skin.addActionListener(new clicked());
        hair.addActionListener(new clicked());
        face.addActionListener(new clicked());
        glasses.addActionListener(new clicked());
        teeth.addActionListener(new clicked());
        hat.addActionListener(new clicked());
        ear.addActionListener(new clicked());
        characterGuess.addActionListener(new clicked());

     // adds functionality to buttons with call to clicked class
        brownEye.addActionListener(new clicked());
        greenEye.addActionListener(new clicked());
        blueEye.addActionListener(new clicked());
        male.addActionListener(new clicked());
        female.addActionListener(new clicked());
        dark.addActionListener(new clicked());
        light.addActionListener(new clicked());
        blackHair.addActionListener(new clicked());
        brownHair.addActionListener(new clicked());
        gingerHair.addActionListener(new clicked());
        blondeHair.addActionListener(new clicked());
        whiteHair.addActionListener(new clicked());
        shortHair.addActionListener(new clicked());
        tiedHair.addActionListener(new clicked());
        longHair.addActionListener(new clicked());
        bald.addActionListener(new clicked());
        faceHair.addActionListener(new clicked());
        noFaceHair.addActionListener(new clicked());
        yesGlasses.addActionListener(new clicked());
        noGlasses.addActionListener(new clicked());
        yesTeeth.addActionListener(new clicked());
        noTeeth.addActionListener(new clicked());
        yesHat.addActionListener(new clicked());
        noHat.addActionListener(new clicked());
        yesPierce.addActionListener(new clicked());
        noPierce.addActionListener(new clicked());
        qMenu.addActionListener(new clicked());

        // sets all panels to invisible
        eyeMenu.setVisible(false);
        genderMenu.setVisible(false);
        skinMenu.setVisible(false);
        hairMenu.setVisible(false);
        faceMenu.setVisible(false);
        glassesMenu.setVisible(false);
        teethMenu.setVisible(false);
        hatMenu.setVisible(false);
        earMenu.setVisible(false);


        // adds buttons to panel
        questionMenu.add(questionTitle);
        questionMenu.add(eye);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10))); // creates gap between buttons
        questionMenu.add(gender);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(skin);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(hair);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(face);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(glasses);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(teeth);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(hat);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(ear);
        questionMenu.add(Box.createRigidArea(new Dimension(0,10)));
        questionMenu.add(characterGuess);
        questionMenu.setVisible(true); // makes panel visible


        //qBox is an abbreviation of Question Box, This variable name is used to say that it is the box of previous questions and answers
        qBox.setBounds(500, 700, 1000, 250);
        qBox.setLayout(new GridLayout(1, 2));
        qBox.setBackground(new Color(0f, 0f, 0f, 0f));
        qBox.setVisible(true);

        //Player Panel
        qBoxQuestionPanel1.setLayout(null);
        qBoxQuestionPanel1.setBackground(Color.decode("#5e1726"));
        qBoxQuestionPanel1.setVisible(true);
        qBox.add(qBoxQuestionPanel1);

        //Computer Panel
        qBoxQuestionPanel2.setLayout(null);
        qBoxQuestionPanel2.setBackground(Color.decode("#5e1726"));
        qBoxQuestionPanel2.setVisible(true);
        qBox.add(qBoxQuestionPanel2);

        //PLAYER LABEL
        qBoxQuestionPanelLabel1 = new JTextArea();
        qBoxQuestionPanelLabel1.setBounds(40, 20, 400, 250);
        qBoxQuestionPanelLabel1.setFont(POPPINS.deriveFont(40f));
        qBoxQuestionPanelLabel1.setForeground(Color.WHITE);
        qBoxQuestionPanelLabel1.setText(playerName);
        qBoxQuestionPanelLabel1.setWrapStyleWord(true);
        qBoxQuestionPanelLabel1.setLineWrap(true);
        qBoxQuestionPanelLabel1.setOpaque(false);
        qBoxQuestionPanelLabel1.setEditable(false);
        qBoxQuestionPanelLabel1.setFocusable(false);
        qBoxQuestionPanelLabel1.setBackground(UIManager.getColor("Label.background"));
        qBoxQuestionPanelLabel1.setBorder(UIManager.getBorder("Label.border"));

        //AI LABEL
        qBoxQuestionPanelLabel2 = new JTextArea();
        qBoxQuestionPanelLabel2.setBounds(40, 20, 400, 250);
        qBoxQuestionPanelLabel2.setFont(POPPINS.deriveFont(40f));
        qBoxQuestionPanelLabel2.setForeground(Color.WHITE);
        qBoxQuestionPanelLabel2.setText("Computer: ");
        qBoxQuestionPanelLabel2.setWrapStyleWord(true);
        qBoxQuestionPanelLabel2.setLineWrap(true);
        qBoxQuestionPanelLabel2.setOpaque(false);
        qBoxQuestionPanelLabel2.setEditable(false);
        qBoxQuestionPanelLabel2.setFocusable(false);
        qBoxQuestionPanelLabel2.setBackground(UIManager.getColor("Label.background"));
        qBoxQuestionPanelLabel2.setBorder(UIManager.getBorder("Label.border"));

        // adds labels to panel
        qBoxQuestionPanel1.add(qBoxQuestionPanelLabel1);
        qBoxQuestionPanel2.add(qBoxQuestionPanelLabel2);

        // score board jpanel that sets size and position in mainGame panel
        scoreBoard = new JPanel();
        scoreBoard.setLayout(null);
        scoreBoard.setBounds(1350,30,350,170);
        scoreBoard.setBackground(Color.decode("#ff7a98"));
        mainGame.add(scoreBoard);

        //instantiate label
        JLabel scoreLabel = new JLabel ("scores:");
        //sets font and size and position of label
        scoreLabel.setFont(POPPINS.deriveFont(15f));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(30, 0, 350, 50);
        scoreBoard.add(scoreLabel); // add label to panel

        //instantiate label
        playerScoreLabel = new JLabel(playerName + ": " + playerScore);
        //sets font and size and position of label
        playerScoreLabel.setFont(POPPINS.deriveFont(30f));
        playerScoreLabel.setBounds(30, 5, 350, 100);
        playerScoreLabel.setForeground(Color.WHITE);

        scoreBoard.add(playerScoreLabel);
//        scoreBoard.setBorder(new LineBorder(Color.WHITE, 8));
        AIScoreLabel = new JLabel("AI: "+ AIScore);
        AIScoreLabel.setFont(POPPINS.deriveFont(30f));
        AIScoreLabel.setBounds(30, 80, 350, 100);
        AIScoreLabel.setForeground(Color.WHITE);
        scoreBoard.add(AIScoreLabel);

        //instantiate panel
        // sets position of panel and size of panel
        timerPanel = new JPanel(null);
        timerPanel.setBounds(1350, 240, 150, 100);
        timerPanel.setBackground(Color.decode("#f76083"));
//        timerPanel.setBorder(new LineBorder(Color.BLACK, 8));
        mainGame.add(timerPanel);

        JLabel timerName = new JLabel ("timer:");
        timerName.setFont(POPPINS.deriveFont(15f));
        timerName.setForeground(Color.WHITE);
        timerName.setBounds(30, 0, 350, 50);
        timerPanel.add(timerName);

      //instantiate label
     // sets position of button and size of button and font of button text
        timerLabel = new JLabel("0:00");
        timerLabel.setFont(POPPINS.deriveFont(30f));
        timerLabel.setBounds(30, 30, 350, 50);
        timerLabel.setForeground(Color.WHITE);
        timerPanel.add(timerLabel);

        //instantiate label
        // sets position of button and size of button and font of button text
        home = new JButton("\uD83C\uDFE0");
        home.setBounds(1710, 30, 70, 70);
        home.setBackground(Color.decode("#6268fc"));
        home.setBorder(new LineBorder(Color.WHITE, 8));
        home.setFont(new Font("Semgoe UI", 1, 20));
        home.setFocusPainted(false);
        home.setForeground(Color.white);
        home.addActionListener(new clicked());
        mainGame.add(home);

        //instantiate label
        // sets position of button and size of button and font of button text
        updateText.setBounds(1350, 500, 350, 100);
        updateText.setText("Select a question!");
        updateText.setFont(POPPINS.deriveFont(30f));
        updateText.setForeground(Color.WHITE);
        mainGame.add(updateText);

        // sets layout of panel to null
        endTurnPanel.setLayout(null);
        endTurnPanel.setBounds(1525, 700, 200, 250); // sets position and size of panel
        endTurnPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        mainGame.add(endTurnPanel);


        //instantiate label
        // sets position of button and size of button and font of button text
        endTurnButton = new JButton("END TURN");
        endTurnButton.setBounds(23, 50, 155, 100);
        endTurnButton.setBackground(Color.decode("#6268fc"));
        endTurnButton.setBorder(new LineBorder(Color.WHITE, 8));
        endTurnButton.setFont(POPPINS.deriveFont(23f));
        endTurnButton.setFocusPainted(false);
        endTurnButton.setForeground(Color.white);
        endTurnButton.addActionListener(new clicked());
        endTurnButton.addMouseListener(new MyMouseListener());
        endTurnButton.setVisible(false);
        endTurnPanel.add(endTurnButton);

        qMenu.setMaximumSize(new Dimension(400, 90));

        // adds all sub panels to main panel
        mainGame.add(qBox);
        mainGame.add(board);
        mainGame.add(questionMenu);
        mainGame.add(eyeMenu);
        mainGame.add(hairMenu);
        mainGame.add(genderMenu);
        mainGame.add(skinMenu);
        mainGame.add(faceMenu);
        mainGame.add(glassesMenu);
        mainGame.add(teethMenu);
        mainGame.add(hatMenu);
        mainGame.add(earMenu);
        mainGame.add(characterGuessMenu);
        mainGame.setVisible(false);
        frame.add(mainGame);

        timer = new Timer(-1, new gameTimer());
//        timer.restart();
//        timer.start();

        frame.repaint();

        mouseListenerAddition();

    }


    /**
     * This runs the character checker, outputs a value
     * @param questionNum
     */
    public static void compCharacterChecker(int questionNum){
        endTurnButton.setVisible(true);
        questionMenu.setVisible(false);
        boolean questionValue = GameAlgorithm.player2.questionChecker(questionNum);
        String questionText = GameAlgorithm.getQuestionText(questionNum);

        GameAlgorithm.playerQuestions.add(questionNum);
        GameAlgorithm.computerAnswers.add(questionValue);

        String outputYesNo = "";
        if (questionValue){
            outputYesNo = "YES!";
        } else{
            outputYesNo = "NO!";
        }

        qBoxQuestionPanelLabel1.setText(playerName + ": "  + questionText);
        qBoxQuestionPanelLabel2.setText("Computer: " + outputYesNo);

    }








    /**
     * Yes or no input, creates new JFrame for the yes or no input
     */
    public static void yesNoInput(String computerText){
        askPanel.setVisible(true);
        askPanel.removeAll();

        answer = new JLabel("    Please Answer The Question");
        answer.setForeground(Color.WHITE);
        answer.setBounds(0,0,400,100);
        answer.setFont(POPPINS.deriveFont(24f));

        //sets position and size of askPanel and sets layout to null
        askPanel.setLayout(null);
        askPanel.setBounds(40,100,400,600);
        askPanel.setBackground(new Color(0f,0f,0f,.5f));
        GameMenu.mainGame.add(askPanel);

        //IMPORTANT TEXT, UPDATES THE TEXT FOR AI
        qBoxQuestionPanelLabel2.setText("Computer: " + computerText);

     // instantiates button and sets position size font and color of button
        yes = new JButton("Yes");
        yes.setBounds(40,100,300,100);
        yes.setBackground(Color.decode("#fa4399"));
        yes.setBorder(new LineBorder(Color.WHITE, 8));
        yes.setFont(POPPINS.deriveFont(23f));
        yes.setFocusPainted(false);
        yes.setForeground(Color.white);

        // instantiates button and sets position size font and color of button
        no  = new JButton("No");
        no.setBackground(Color.decode("#fa4399"));
        no.setBounds(40,250,300,100);
        no.setBorder(new LineBorder(Color.WHITE, 8));
        no.setFont(POPPINS.deriveFont(23f));
        no.setFocusPainted(false);
        no.setForeground(Color.white);

        // adds functionality of mouse hovering over component
        yes.addMouseListener(new MyMouseListener());
        no.addMouseListener(new MyMouseListener());
        yes.setMaximumSize(new Dimension(400, 90));
        no.setMaximumSize(new Dimension(400, 90));
        askPanel.add(answer);
        askPanel.add(Box.createRigidArea(new java.awt.Dimension(0,200)));
        askPanel.add(yes);
        askPanel.add(Box.createRigidArea(new java.awt.Dimension(0,150)));
        askPanel.add(no);
        yes.addActionListener(new clicked());
        no.addActionListener(new clicked());
    }

    /**
     * Action listener if the character buttons are pressed
     */
    static class gridClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 6; c++) {
                    if (e.getSource() == character[r][c] && characterFlipped[r][c] == false) {
                        playSound("card-turn.wav",false);
                        try {
                            backUploadPicture(r, c);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        characterFlipped[r][c] = true;
                    } else if (e.getSource() == character[r][c] && characterFlipped[r][c] == true){
                        playSound("card-turn.wav",false);
                        try {
                            uploadPicture(r, c);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        characterFlipped[r][c] = false;
                    }
                }
            }
        }
    }

    /**
     * This method is called if the player wants to challenge the AI's decision on what the player's character is.
     */
    public static void errorChallenge(){
        //remove all panels from main game
        mainGame.removeAll();
        mainGame.repaint();

        //initialize the error panel pane
        errorPanel = new JPanel();
        errorPanel.setLayout(null); //sets layout
        errorPanel.setBounds(0,0,1900,1000); //sets bounds
        errorPanel.setBackground(Color.decode("#8e54fe")); //sets background
        GameMenu.mainGame.add(errorPanel); //adds to the main game panel
        errorPanel.setVisible(true); //sets it to visible

        //creates new JLabel to tell user to enter their character
        errorLineLabel1 = new JLabel("Please enter your character:");
        errorLineLabel1.setForeground(Color.WHITE);
        errorLineLabel1.setFont(POPPINS.deriveFont(40f));
        errorLineLabel1.setBounds(20,200,1000,90);

        //adds the JLabel to the error panel
        errorPanel.add(errorLineLabel1);

        //creates a challenge label panel to hold the challenge label
        challengeLabelPanel.setBackground(Color.decode("#e56efc"));
        challengeLabelPanel.setBounds(0,0,1780,100);
        challengeLabelPanel.setBorder(new LineBorder(Color.WHITE, 10));

        //creates a new challenge label, configures settings for the challenge label - the title for the challenge
        //sets foreground, font, bounds, and adds it to the challenge label panel
        challengeLabel.setForeground(Color.WHITE);
        challengeLabel.setFont(POPPINS.deriveFont(50f));
        challengeLabel.setBounds(0,0,1000,100);
        challengeLabelPanel.add(challengeLabel);

        //adds the challenge label to the error panel
        errorPanel.add(challengeLabelPanel);

        //create new grid layout for the error guessing - called errorGuessLayout
        GridLayout errorGuessLayout = new GridLayout(2, 12);
        //sets the layout for the error guess panel
        errorGuessPanel.setLayout(errorGuessLayout);
        errorGuessPanel.setOpaque(false);
        //sets vertical and horizontal gap
        errorGuessLayout.setHgap(10);
        errorGuessLayout.setVgap(10);
        //sets layout and bounds
        errorGuessPanel.setLayout(errorGuessLayout);
        errorGuessPanel.setBounds(20,300,1550,200);
        //sets the panel to visible
        errorGuessPanel.setVisible(true);

        //for loop to add the buttons to the error guess layout, i = col, j = rows
        for (int i=0;i<12;i++){
            for(int j=0;j<2;j++){
                //adds a new JButton with the name for gridOfNames (a grid of names)
                //sets font, background, border, font, and some other important settings
                errorNames[i][j] = new JButton(gridOfNames[i][j]);
                errorNames[i][j].setFont(POPPINS.deriveFont(15f));
                errorNames[i][j].setBackground(Color.decode("#6268fc"));
                errorNames[i][j].setBorder(new LineBorder(Color.WHITE, 8));
                errorNames[i][j].setFont(POPPINS.deriveFont(23f));
                errorNames[i][j].setFocusPainted(false);
                errorNames[i][j].setForeground(Color.white);
                errorNames[i][j].addActionListener(new errorClicked());
                errorNames[i][j].addMouseListener(new MyMouseListener());
                errorGuessPanel.add(errorNames[i][j]); //adds button to the panel grid
            }
        }

        //adds the grid of buttons to the main error panel
        errorPanel.add(errorGuessPanel);
    }


    /**
     * This method displays the values for what the player gets wrong
     * @param wrongValues the arraylist of all the wrong values that the player got wrong
     * @param name name of character selected
     */
    public static void errorNameDisplayer(ArrayList<Integer> wrongValues, String name){
        //removes the error guess panel (grid of characters)
        errorPanel.remove(errorGuessPanel);
        errorGuessPanel.removeAll();
        errorPanel.repaint();

        //sets the text to the selected character
        errorLineLabel1.setText("Selected Character: " + name);

        //gets the display size (number of player answers)
        int displaySize = GameAlgorithm.playerAnswers.size();

        //error help text - text to help the user, tells user what coloured values they entered incorrectly
        //this chunk of code sets basic gui aesthetics
        errorHelpText.setForeground(Color.WHITE);
        errorHelpText.setVisible(true);
        errorHelpText.setFont(POPPINS.deriveFont(40f));
        errorHelpText.setBounds(20, 780, 1720, 100);
        //adds to main errorPanel
        errorPanel.add(errorHelpText);

        //error help text 2 - another text to help the user by giving hints. This text is different as it
        //tells the user if they inputted the wrong character if everything is right
        errorHelpText2 = new JLabel();
        errorHelpText2.setForeground(Color.WHITE);
        errorHelpText2.setFont(POPPINS.deriveFont(18f));
        errorHelpText2.setBounds(20, 700, 1720, 100);
        errorHelpText2.setVisible(true);
        //adds to error panel
        errorPanel.add(errorHelpText2);

        //the background panel to display all the values - its the total of all the display panels and houses them
        //this is a translucent panel because regular JPanels bug out when I try to change them
        totalValueDisplayPanel.setLayout(null); //sets layout to null
        totalValueDisplayPanel.setBounds(20, 330, 1720, 400);
        errorPanel.add(totalValueDisplayPanel);

        //Creates the question display panel, sets it as a translucent panel.
        //This panel displays the questions
        qDisplayPanel = new TranslucentPanel(Color.BLACK, .3f);
        //sets layout to be the display size
        qDisplayPanel.setLayout(new GridLayout(1, displaySize));
        qDisplayPanel.setBounds(20, 20, 1600, 120);
        //makes panel visible
        qDisplayPanel.setVisible(true);
        totalValueDisplayPanel.add(qDisplayPanel);

        //creates the value display panel - value that displays the correct adn incorrect values
        valueDisplayPanel = new TranslucentPanel(Color.BLACK,.3f);
        //sets layout to be the display size
        valueDisplayPanel.setLayout(new GridLayout(1, displaySize));
        valueDisplayPanel.setBounds(20, 170, 1600, 120);
        valueDisplayPanel.setVisible(true);
        //adds it to to total of all value display panels
        totalValueDisplayPanel.add(valueDisplayPanel);

        //FOR QUESTIONS AI ASK PLAYER
        //creates new  JTextArea array to the size of the number of questions asked to the player
        qDisplayLabel = new JTextArea[displaySize];
        //cycles through for each display panel, creates one, adds to the grid
        for (int i = 0; i < displaySize; i++) {
            //creates new JText area, sets font, important settings
            qDisplayLabel[i] = new JTextArea(2, 1);
            qDisplayLabel[i].setFont(POPPINS.deriveFont(40f));
            qDisplayLabel[i].setForeground(Color.WHITE);
            qDisplayLabel[i].setWrapStyleWord(true);
            qDisplayLabel[i].setLineWrap(true);
            qDisplayLabel[i].setOpaque(false);
            qDisplayLabel[i].setEditable(false);
            qDisplayLabel[i].setFocusable(false);
            //sets background to clear, with border and text
            qDisplayLabel[i].setBackground(UIManager.getColor("Label.background"));
            qDisplayLabel[i].setBorder(UIManager.getBorder("Label.border"));
            qDisplayLabel[i].setText("  "  + GameAlgorithm.getQuestionText(GameAlgorithm.computerQuestions.get(i)));

            //adds it to the question display panel
            qDisplayPanel.add(qDisplayLabel[i]);
        }

        //FOR THE VALUES THAT THE PLAYER RESPONDS TO THE AI
        valueDisplayLabel = new JTextArea[displaySize];
        for (int i = 0; i < displaySize; i++) {
            //creates new JText area, sets font, important settings
            valueDisplayLabel[i] = new JTextArea();
            valueDisplayLabel[i].setFont(POPPINS.deriveFont(40f));
            valueDisplayLabel[i].setForeground(Color.WHITE);
            valueDisplayLabel[i].setWrapStyleWord(true);
            valueDisplayLabel[i].setLineWrap(true);
            valueDisplayLabel[i].setOpaque(false);
            valueDisplayLabel[i].setEditable(false);
            valueDisplayLabel[i].setFocusable(false);
            //sets background to clear, with border and text
            valueDisplayLabel[i].setBackground(UIManager.getColor("Label.background"));
            valueDisplayLabel[i].setBorder(UIManager.getBorder("Label.border"));

            //determines if its true or false, converts it to yes or no
            if (GameAlgorithm.playerAnswers.get(i)){
                valueDisplayLabel[i].setText("  "  + "YES");
            } else{
                valueDisplayLabel[i].setText("  "  + "NO");
            }

            //adds it to the value display panel
            valueDisplayPanel.add(valueDisplayLabel[i]);
        }

        //DETERMINES WHICH ONES ARE WRONG
        //loops for each value, tries to see if it is wrong or right
        //two loops for the array, first loops through each value label
        for (int i = 0; i < displaySize; i++) {
            //for each value label, scan through all the wrong values to see if it matches
            for (int m = 0; m < wrongValues.size(); m++) {
                //if the wrong value matches with the current label number, then:
                if (i == wrongValues.get(m)) {
                    //sets text to X to let player know its wrong
                    valueDisplayLabel[i].setText(valueDisplayLabel[i].getText() + " (X)");
                    valueDisplayLabel[i].setOpaque(true);
                    //changes color
                    valueDisplayLabel[i].setBackground(Color.decode("#f823c3"));
                }
            }
        }
        //IF THE PLAYER ENTERED THE WRONG CHARACTER AT THE END: (ERROR CHECKING!!!)
        if (wrongValues.size() == 0){
            errorHelpText2.setText("All of your answers are correct. Either you clicked the wrong character, or you answered the last yes/no question incorrectly. YOU STILL LOSE!");
        }
        //creates a new proceed button for the player to proceed
        //sets font, bounds, background, border, font, and important settings like action listener, mouse listener
        proceedButton.setFont(POPPINS.deriveFont(40f));
        proceedButton.setBounds(1500, 770, 200, 100);
        proceedButton.setBackground(Color.decode("#3bb98a"));
        proceedButton.setBorder(new LineBorder(Color.WHITE, 8));
        proceedButton.setFont(POPPINS.deriveFont(40f));
        proceedButton.setFocusPainted(false);
        proceedButton.setForeground(Color.white);
        proceedButton.addActionListener(new clicked());
        errorPanel.add(proceedButton);

        //refreshes everything
        frame.getContentPane().repaint();

        //sets error to false
        error = false;
    }

    /**
     * This method is called once the player guesses a character. It sets the GUI to whatever happens, if the guess is
     * right or wrong
     * @param characterGuess the character the player guesses
     */
    public static void setCharacterGuess(String characterGuess){
        //sets the text for the question label to the player name guessing a character
        qBoxQuestionPanelLabel1.setText(playerName + ": "   + characterGuess + "!");

        //asks the game algorithm i the guess is correct or not correct
        boolean guessValue = GameAlgorithm.playerGuessChecker(characterGuess);

        //if the guess is correct
        if (guessValue){
            //WIN GAME
            GameAlgorithm.playerWin = true;
            //sets text to win game
            qBoxQuestionPanelLabel2.setText("Computer: YES! YOU WIN! ");
            //adds one to the player score
            playerScore++;
            //sets the player score label, makes the end turn button visible
            playerScoreLabel.setText(playerName + ": " +playerScore);
            endTurnButton.setVisible(true);

        //if guess is wrong
        } else{
            //LOSE GAME
            GameAlgorithm.compWin = true;
            GameAlgorithm.playerWin = false;
            //sets text to lose game
            qBoxQuestionPanelLabel2.setText("Computer: HAHAHAHA NOPE YOU LOSE");
            //adds one score to the AI
            AIScore++;
            //sets the AI Score
            AIScoreLabel.setText("AI: "+AIScore);
            //shows the end turn button
            endTurnButton.setVisible(true);
        }

    }

    /**
     * This class uses an action listener to continuously update the timer label with the correct elapsed time.
     */
    static class gameTimer implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //calculate the elapsed time using the current time and start time
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;

            //convert elapsed time into seconds, ten seconds (so we can include 0s in formatting), and minutes
            seconds = (int) (elapsedTime / 1000) % 10;
            tenSeconds = (int) (elapsedTime / 10000) % 6;
            minutes = (int) (elapsedTime / 60000) % 60;

            //update label continuously using formatted time
            timerLabel.setText(minutes+":"+ tenSeconds+ seconds);
        }
    }

    /**
     * This method removes everything from the JPanels. This is called to reset the game to prevent errors.
     */
    public static void gameRemoveAll(){
        //removes all from all JPanels
        board.removeAll();
        askPanel.removeAll();
        qBoxQuestionPanel2.removeAll();
        qBoxQuestionPanel1.removeAll();
        totalValueDisplayPanel.removeAll();
        endTurnPanel.removeAll();
        errorPanel.removeAll();
        qDisplayPanel.removeAll();
        valueDisplayPanel.removeAll();
    }

    /**
     * This class manages the action listener for the grid of names shown in the error challenge
     */
    static class errorClicked implements ActionListener {
        public void actionPerformed ( ActionEvent e){
            //plays a sound select
            playSound("select.wav",false);

            //loops through each row and col to find the button that was clicked
            for (int i=0;i<12;i++){
                for(int j=0;j<2;j++){
                    //once the button was found
                    if (e.getSource() == errorNames[i][j]){
                        //calls GameAlgorithm.errorValidate to find the values that the player got wrong, values that
                        //the player got wrong are stored in an array
                        ArrayList<Integer> wrongValues = GameAlgorithm.errorValidate(errorNames[i][j].getText());

                        //runs the error name displayer to display the errors that the player made
                        errorNameDisplayer(wrongValues, gridOfNames[i][j]);
                    }
                }
            }
        }
    }

    /**
     * This class manages the action listener for ALL THE MENU ITEMS ON THE MAIN GAMEPLAY SCREEN.
     */
    static class clicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //refreshes the screen to prevent JSwing glitches
            frame.getContentPane().repaint();

            //if the names are clicked in the guess menu
            //cycles through each row and col to find the value clicked
            for(int r=0; r<12;r++){
                for (int c=0; c<2; c++){
                    //once the specific button is found
                    if (e.getSource() == names[r][c]){
                        //play a swap sound
                        playSound("swap-sound.wav",false);

                        //make the menu invisible
                        characterGuessMenu.setVisible(false);

                        //runs the method to set the character guess to the specific name value
                        //grid of names stores all the specific names
                        setCharacterGuess(gridOfNames[r][c]);
                    }
                }
            }

            //if the button pressed is the end turn button
            if (e.getSource() == endTurnButton) {
                //plays the swap sound sound effect
                playSound("swap-sound.wav",false);

                //sets the question panel labels to the player text and computer text by clearing both
                qBoxQuestionPanelLabel1.setText(playerName + ": " );
                qBoxQuestionPanelLabel2.setText("Computer: ");

                //makes button invisible so cant be pressed again
                endTurnButton.setVisible(false);
                //if its currently the players turn and nobody won yet, and theres no error declared
                if (GameAlgorithm.turn == 0 && (!GameAlgorithm.compWin && !GameAlgorithm.playerWin) && !error){
                    //clears the update text
                    updateText.setText("");
                    //sets the turn to the AI turn
                    GameAlgorithm.turn = 1;

                    //hides question menu
                    questionMenu.setVisible(false);

                    //asks the question in game algorithm
                    GameAlgorithm.askQuestion();

                //if its currently the AIs turn and nobody won yet, and no error
                } else if (GameAlgorithm.turn == 1 && (!GameAlgorithm.compWin && !GameAlgorithm.playerWin) && !error){
                    //tells player to select a question
                    updateText.setText("Select a question!");

                    //sets turn to players turn
                    GameAlgorithm.turn = 0;

                    //makes the question menu visible
                    questionMenu.setVisible(true);

                //if the player thinks theres an error with the AI's decision
                } else if (error){
                    //COMMENCE THE ERROR CHALLENGE!
                    errorChallenge();
                //if the computer won
                } else if (GameAlgorithm.compWin){
                    //stop timer
                    timer.stop();
                    //WIN SCREEN
                    try {
                        //play the game end menu with false
                        gameEndMenu(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                //if the player won
                } else if (GameAlgorithm.playerWin){
                    //stop timer
                    timer.stop();
                    //WIN SCREEN
                    try {
                        //play game end menu with the player winning (true)
                        gameEndMenu(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            //if the player clicked the yes button when asked a question from the AI
            }else if (e.getSource() == yes){
                //play a sound
                playSound("select.wav",false);

                //sets the player question value to true (player said yes)
                GameAlgorithm.playerQuestionValue = true;
                //makes panels visible
                askPanel.setVisible(false);

                //makes the end turn button visible because turn ended
                endTurnButton.setVisible(true);
                //sets text to reference what the player pressed
                qBoxQuestionPanelLabel1.setText(playerName + ": YES!" );

                //gets number of people in the computer person list
                int numPeople = GameAlgorithm.computerPersonList.size();

                //if theres more than one person, then that means the player pressed yes to the computer asking a regular question
                if (numPeople > 1){
                    //removes the character
                    GameAlgorithm.characterRemover(GameAlgorithm.questionNum, GameAlgorithm.computerPersonList, true);

                    //keeps track of the answers answered by the player + questions asked by computer
                    GameAlgorithm.playerAnswers.add(true);
                    GameAlgorithm.computerQuestions.add(GameAlgorithm.questionNum);

                //if theres only one person in the computer list, then that means that the player pressed yes to the computer asking a guess question
                } else if (numPeople == 1){
                    timer.stop();

                    //COMPUTER WINS
                    GameAlgorithm.compWin = true;
                }

            //if the player pressed no when the computer asks a question
            } else if (e.getSource() == no){
                //plays a sound
                playSound("select.wav",false);

                //sets the question value to false because the player said no
                GameAlgorithm.playerQuestionValue = false;

                //sets panels to invisible, shows end turn button to end turn
                askPanel.setVisible(false);
                endTurnButton.setVisible(true);

                //updates text
                qBoxQuestionPanelLabel1.setText(playerName + ": NO!" );

                //gets number of people still in the computers list of people
                int numPeople = GameAlgorithm.computerPersonList.size();

                //if theres more than one person, then that means the player said no to a regular question. (normal)
                if (numPeople > 1){
                    //removes the character
                    GameAlgorithm.characterRemover(GameAlgorithm.questionNum, GameAlgorithm.computerPersonList, false);
                    //keeps track of the answers + questions asked
                    GameAlgorithm.playerAnswers.add(false);
                    GameAlgorithm.computerQuestions.add(GameAlgorithm.questionNum);

                //if theres only one person, then that means the player said no to a computer guess. this means that
                //they dont agree with the computers decision, therefore an error occured
                } else if (numPeople == 1){
                    //stop timer
                    timer.stop();

                    //sets error to true
                    error = true;

                    //adds a score to the AI because the player is wrong
                    AIScore++;

                    AIScoreLabel.setText("AI: " + AIScore);
                }
            //if the player pressed character guess
            } else if (e.getSource() == characterGuess){
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                characterGuessMenu.setVisible(true);
                characterGuessMenu.add(qMenu);
            }
            else if (e.getSource() == eye) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                eyeMenu.setVisible(true);
                eyeMenu.add(qMenu);

            } else if (e.getSource() == hair) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                hairMenu.setVisible(true);
                hairMenu.add(qMenu);

            } else if (e.getSource() == gender) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                genderMenu.setVisible(true);
                genderMenu.add(qMenu);

            } else if (e.getSource() == skin) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                skinMenu.setVisible(true);
                skinMenu.add(qMenu);

            } else if (e.getSource() == face) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                faceMenu.setVisible(true);
                faceMenu.add(qMenu);

            } else if (e.getSource() == glasses) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                glassesMenu.setVisible(true);
                glassesMenu.add(qMenu);

            } else if (e.getSource() == teeth) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                teethMenu.setVisible(true);
                teethMenu.add(qMenu);
            } else if (e.getSource() == hat) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                hatMenu.setVisible(true);
                hatMenu.add(qMenu);
            } else if (e.getSource() == ear) {
                playSound("select.wav",false);
                //sets the normal question menu to disappear and the respective menu to appear
                questionMenu.setVisible(false);
                earMenu.setVisible(true);
                earMenu.add(qMenu);
            } else if (e.getSource() == brownEye) { //1
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                eyeMenu.setVisible(false);
                compCharacterChecker(1);
            } else if (e.getSource() == greenEye) { //2
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                eyeMenu.setVisible(false);
                compCharacterChecker(2);
            } else if (e.getSource() == blueEye) { //3
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                eyeMenu.setVisible(false);
                compCharacterChecker(3);
            } else if (e.getSource() == male) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                genderMenu.setVisible(false);
                compCharacterChecker(4);
            } else if (e.getSource() == female) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                genderMenu.setVisible(false);
                compCharacterChecker(5);
            } else if (e.getSource() == dark) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                skinMenu.setVisible(false);
                compCharacterChecker(7);
            } else if (e.getSource() == light) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                skinMenu.setVisible(false);
                compCharacterChecker(6);
            } else if (e.getSource() == blackHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(8);
            } else if (e.getSource() == brownHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(9);
            } else if (e.getSource() == gingerHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(10);
            } else if (e.getSource() == blondeHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(11);
            } else if (e.getSource() == whiteHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(12);
            } else if (e.getSource() == faceHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                faceMenu.setVisible(false);
                compCharacterChecker(13);
            } else if (e.getSource() == noFaceHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                faceMenu.setVisible(false);
                compCharacterChecker(14);
            } else if (e.getSource() == yesGlasses) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                glassesMenu.setVisible(false);
                compCharacterChecker(15);
            } else if (e.getSource() == noGlasses) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                glassesMenu.setVisible(false);
                compCharacterChecker(16);
            } else if (e.getSource() == yesTeeth) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                teethMenu.setVisible(false);
                compCharacterChecker(17);
            } else if (e.getSource() == noTeeth) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                teethMenu.setVisible(false);
                compCharacterChecker(18);
            } else if (e.getSource() == yesHat) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hatMenu.setVisible(false);
                compCharacterChecker(19);
            } else if (e.getSource() == noHat) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hatMenu.setVisible(false);
                compCharacterChecker(20);
            }  else if (e.getSource() == shortHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(21);
            } else if (e.getSource() == tiedHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(22);
            } else if (e.getSource() == longHair) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(23);
            } else if (e.getSource() == bald) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                hairMenu.setVisible(false);
                compCharacterChecker(24);
            }else if (e.getSource() == yesPierce) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                earMenu.setVisible(false);
                compCharacterChecker(25);
            } else if (e.getSource() == noPierce) {
                playSound("guess-question.wav",false);
                //hides all menus, calls the computer question checker
                earMenu.setVisible(false);
                compCharacterChecker(26);


            //if the button pressed is the question menu, hide
            } else if (e.getSource() == qMenu){
                playSound("select.wav",false);
                qMenu.getParent().setVisible(false);
                questionMenu.setVisible(true);
            //if players going first
            }else if (e.getSource() == playerFirst){
                playSound("to-three.wav",false);

                //view game board
                viewGameBoard();

                //sets turn to player
                GameAlgorithm.turn = 0;

                //initialize game
                try {
                    GameAlgorithm.initializeGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //sets visible
                turnPanel.setVisible(false);
                mainGame.setVisible(true);

            //if the AI is going first
            }else if (e.getSource()== aiFirst){
                playSound("to-three.wav",false);

                //view game board
                viewGameBoard();

                //sets turn to 1
                GameAlgorithm.turn = 1;

                //start the game
                try {
                    GameAlgorithm.initializeGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //sets visible
                questionMenu.setVisible(false);
                turnPanel.setVisible(false);
                mainGame.setVisible(true);

                //asks the question to the player
                GameAlgorithm.askQuestion();

            //if the player wants to proceed (IN THE ERROR CHALLENGE MENU)
            } else if (e.getSource() == proceedButton){
                playSound("proceed.wav",false);

                //play the end game menu, not won false
                try {
                    gameEndMenu(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            //if the player clicked the home button
            } else if (e.getSource() == home){
                playSound("home.wav",false);

                //play the game end menu, false not won
                try {
                    gameEndMenu(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }


    /**
     * MOUSE LISTENER - THIS CLASS MANAGES ALL THE MOUSE EXITS AND HOVERS
     */
    static class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mousePressed(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mouseReleased(MouseEvent e){  // MUST be implemented even if not used!
        }
        //if mouse is entered
        public void mouseEntered(MouseEvent e){   // MUST be implemented even if not used!
            playSound("click-close.wav", false);

            //if player hovered over error names button
            //search for each row and column until button is found
            for (int i=0;i<12;i++){
                for(int j=0;j<2;j++){
                    //once button is found, change the border
                    if (e.getSource() == errorNames[i][j]){
                        errorNames[i][j].setBorder(new LineBorder(Color.WHITE, 15));

                    }
                }
            }
            //search for each row and column until button is found
            for(int r=0; r<12;r++){
                for (int c=0; c<2; c++){
                    //Changes the border if the player hovers and exits over the respective buttons
                    if (e.getSource() == names[r][c]){
                        names[r][c].setBorder(new LineBorder(Color.WHITE, 15));

                    }
                }
            }
                //Changes the border if the player hovers and exits over the respective buttons
            if (e.getSource() == endTurnButton) {
                endTurnButton.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yes) {
                yes.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == no) {
                no.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == characterGuess) {
                characterGuess.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == eye) {
                eye.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == hair) {
                hair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == gender) {
                gender.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == skin) {
                skin.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == face) {
                face.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == glasses) {
                glasses.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == teeth) {
                teeth.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == hat) {
                hat.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == ear) {
                ear.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == brownEye) { //1
                brownEye.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == greenEye) { //2
                greenEye.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blueEye) { //3
                blueEye.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == male) {
                male.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == female) {
                female.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == dark) {
                dark.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == light) {
                light.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blackHair) {
                blackHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == brownHair) {
                brownHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == gingerHair) {
                gingerHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blondeHair) {
                blondeHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == whiteHair) {
                whiteHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == faceHair) {
                faceHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noFaceHair) {
                noFaceHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesGlasses) {
                yesGlasses.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noGlasses) {
                noGlasses.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesTeeth) {
                yesTeeth.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noTeeth) {
                noTeeth.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesHat) {
                yesHat.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noHat) {
                noHat.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == shortHair) {
                shortHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == tiedHair) {
                tiedHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == longHair) {
                longHair.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == bald) {
                bald.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesPierce) {
                yesPierce.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noPierce) {
                noPierce.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == qMenu) {
                qMenu.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == playerFirst) {
                playerFirst.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == aiFirst) {
                aiFirst.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == proceedButton) {
                proceedButton.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == home) {
                home.setBorder(new LineBorder(Color.WHITE, 15));
                //Changes the border if the player hovers and exits over the respective buttons
            }

        }

        /**
         * If the mouse exits the selected button
         */
        public void mouseExited(MouseEvent e){
            //search each row and column for the specific button exited
            for (int i=0;i<12;i++){
                for(int j=0;j<2;j++){
                    //once found, change border back to original
                    if (e.getSource() == errorNames[i][j]){
                        errorNames[i][j].setBorder(new LineBorder(Color.WHITE, 8));

                    }
                }
            }
            //search each row and column for the specific button exited
            for(int r=0; r<12;r++){
                for (int c=0; c<2; c++){
                    //Changes the border if the player hovers and exits over the respective buttons
                    if (e.getSource() == names[r][c]){
                        names[r][c].setBorder(new LineBorder(Color.WHITE, 8));

                    }
                }
            }
                //Changes the border if the player hovers and exits over the respective buttons
            if (e.getSource() == endTurnButton) {
                endTurnButton.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yes) {
                yes.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == no) {
                no.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == characterGuess) {
                characterGuess.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == eye) {
                eye.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == hair) {
                hair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == gender) {
                gender.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == skin) {
                skin.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == face) {
                face.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == glasses) {
                glasses.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == teeth) {
                teeth.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == hat) {
                hat.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == ear) {
                ear.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == brownEye) { //1
                brownEye.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == greenEye) { //2
                greenEye.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blueEye) { //3
                blueEye.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == male) {
                male.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == female) {
                female.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == dark) {
                dark.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == light) {
                light.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blackHair) {
                blackHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == brownHair) {
                brownHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == gingerHair) {
                gingerHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == blondeHair) {
                blondeHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == whiteHair) {
                whiteHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == faceHair) {
                faceHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noFaceHair) {
                noFaceHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesGlasses) {
                yesGlasses.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noGlasses) {
                noGlasses.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesTeeth) {
                yesTeeth.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noTeeth) {
                noTeeth.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesHat) {
                yesHat.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noHat) {
                noHat.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == shortHair) {
                shortHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == tiedHair) {
                tiedHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == longHair) {
                longHair.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == bald) {
                bald.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == yesPierce) {
                yesPierce.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == noPierce) {
                noPierce.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == qMenu) {
                qMenu.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == playerFirst) {
                playerFirst.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == aiFirst) {
                aiFirst.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == proceedButton) {
                proceedButton.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            } else if (e.getSource() == home) {
                home.setBorder(new LineBorder(Color.WHITE, 8));
                //Changes the border if the player hovers and exits over the respective buttons
            }


        }
    }

    /**
     * This method adds mouse listener to every single button!!!! (if they arent part of a for loop)
     */
    public static void mouseListenerAddition(){
        endTurnButton.addMouseListener(new MyMouseListener());
        characterGuess.addMouseListener(new MyMouseListener());
        eye.addMouseListener(new MyMouseListener());
        hair.addMouseListener(new MyMouseListener());
        gender.addMouseListener(new MyMouseListener());
        skin.addMouseListener(new MyMouseListener());
        face.addMouseListener(new MyMouseListener());
        glasses.addMouseListener(new MyMouseListener());
        teeth.addMouseListener(new MyMouseListener());
        hat.addMouseListener(new MyMouseListener());
        ear.addMouseListener(new MyMouseListener());
        brownEye.addMouseListener(new MyMouseListener()); //1
        greenEye.addMouseListener(new MyMouseListener()); //2
        blueEye.addMouseListener(new MyMouseListener()); //3
        male.addMouseListener(new MyMouseListener());
        female.addMouseListener(new MyMouseListener());
        dark.addMouseListener(new MyMouseListener());
        light.addMouseListener(new MyMouseListener());
        blackHair.addMouseListener(new MyMouseListener());
        brownHair.addMouseListener(new MyMouseListener());
        gingerHair.addMouseListener(new MyMouseListener());
        blondeHair.addMouseListener(new MyMouseListener());
        whiteHair.addMouseListener(new MyMouseListener());
        faceHair.addMouseListener(new MyMouseListener());
        noFaceHair.addMouseListener(new MyMouseListener());
        yesGlasses.addMouseListener(new MyMouseListener());
        noGlasses.addMouseListener(new MyMouseListener());
        yesTeeth.addMouseListener(new MyMouseListener());
        noTeeth.addMouseListener(new MyMouseListener());
        yesHat.addMouseListener(new MyMouseListener());
        noHat.addMouseListener(new MyMouseListener());
        shortHair.addMouseListener(new MyMouseListener());
        tiedHair.addMouseListener(new MyMouseListener());
        longHair.addMouseListener(new MyMouseListener());
        bald.addMouseListener(new MyMouseListener());
        yesPierce.addMouseListener(new MyMouseListener());
        noPierce.addMouseListener(new MyMouseListener());
        qMenu.addMouseListener(new MyMouseListener());
        playerFirst.addMouseListener(new MyMouseListener());
        aiFirst.addMouseListener(new MyMouseListener());
        proceedButton.addMouseListener(new MyMouseListener());
        home.addMouseListener(new MyMouseListener());
    }

    /**
     * This method fixes the duplicator glitch in our program by instantiating all the JSwing objects
     */
    public static void duplicatorFixer(){
        questionTitle = new JLabel("            Questions");
        questionMenu = new JPanel();
        board = new JPanel();
        qBox = new JPanel();

        playerFirst = new JButton("Player");
        aiFirst = new JButton("Computer");

        home = new JButton("HOME");
        eyeMenu = new JPanel();
        genderMenu = new JPanel();
        skinMenu = new JPanel();
        hairMenu = new JPanel();
        faceMenu = new JPanel();
        glassesMenu = new JPanel();
        teethMenu = new JPanel();
        hatMenu = new JPanel();
        earMenu = new JPanel();
        characterGuessMenu = new JPanel();
        updateText = new JLabel();

        eye = new JButton("1. Eye Colors");
        gender = new JButton("2. Gender");
        skin = new JButton("3. Skin");
        hair = new JButton("4. Hair");
        face = new JButton("5. Facial Hair");
        glasses = new JButton("6. Glasses");
        teeth = new JButton("7. Teeth");
        hat = new JButton("8. Hat");
        ear = new JButton("9. Ear");
        characterGuess = new JButton("10. Guess");

        brownEye = new JButton("Is The Eye Color Brown?");
        greenEye = new JButton("Is The Eye Color Green?");
        blueEye = new JButton("Is The Eye Color Blue?");
        male = new JButton("Is The Person A Male?");
        female = new JButton("Is The Person A Female?");
        dark = new JButton("Does The Person Have A Dark Skin Tone?");
        light = new JButton("Does The Person Have A Light Skin Tone?");
        blackHair = new JButton("Is The Hair Color Black?");
        brownHair = new JButton("Is The Hair Color Brown?");
        gingerHair = new JButton("Is The Hair Color Ginger?");
        blondeHair = new JButton("Is The Hair Color Blonde?");
        whiteHair = new JButton("Is The Hair Color White?");
        shortHair = new JButton("Does The Person Have Short Hair?");
        tiedHair = new JButton("Does The Person Have Their Hair Tied Up?");
        longHair = new JButton("Does The Person Have Long Hair?");
        bald = new JButton("Is The Person Bald?");
        faceHair = new JButton("Does The Person Have Facial Hair?");
        noFaceHair = new JButton("Does The Person Have No Facial Hair?");
        yesGlasses = new JButton("Is The Person Wearing Glasses?");
        noGlasses = new JButton("Is The Person Not Wearing Glasses?");
        yesTeeth = new JButton("Is The Person Showing Teeth?");
        noTeeth = new JButton("Is The Person Not Showing Teeth?");
        yesHat = new JButton("Is The Person Wearing A Hat?");
        noHat = new JButton("Is The Person Not Wearing A Hat?");
        yesPierce = new JButton("Does The Person Have An Ear Piercing?");
        noPierce = new JButton("Does The Person Not Have An Ear Piercing?");
        qMenu = new JButton("Menu");

        character = new JButton[4][6];
        characterFlipped = new boolean[4][6];
        names = new JButton[12][2];
        endTurnPanel = new JPanel();
        qBoxQuestionPanel1 = new JPanel();
        qBoxQuestionPanel2 = new JPanel();
        askPanel = new JPanel();

        errorNames = new JButton[12][2];
        errorGuessPanel = new JPanel();
        challengeLabel = new JLabel("DEFEAT!");
        challengeLabelPanel = new JPanel();
        errorPanel = new JPanel();

        totalValueDisplayPanel = new TranslucentPanel(Color.BLACK, .5f);
        errorHelpText = new JLabel("Coloured answers are the ones you answered incorrectly!");
        proceedButton = new JButton("Proceed");
        qDisplayPanel = new JPanel();
        valueDisplayPanel = new JPanel();

    }

}