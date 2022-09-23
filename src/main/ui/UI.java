package ui;

import model.FriendList;
import model.events.EventLog;
import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;

public class UI implements ActionListener {
    // This creates the UI for our game!

    public static final int WIDTH_WINDOW = 810;
    public static final int HEIGHT_WINDOW = 600;
    public static final Font SERIF_FONT = new Font("Serif Sans", Font.PLAIN, 20);
    public static final Font SERIF_FONT_SMALL = new Font("Serif Sans", Font.PLAIN, 12);
    public static final Font SERIF_FONT_SMALLER = new Font("Serif Sans", Font.PLAIN, 9);

    public static final Font TITLE_FONT = new Font("Kokonor", Font.BOLD, 40);
    public static final Color BABY_BLUE = new Color(137, 207, 240);
    public static final Color LIGHT_CYAN = new Color(177, 205, 208);
    private static final String JSON_STORE = "./data/UI.json";
    private static final URL TAIGA_URL = UI.class.getClassLoader().getResource("cpsc-210-taiga.png");
    private static final URL BLAZER_URL = UI.class.getClassLoader().getResource("cpsc-210-blazer.png");
    private static final URL BLAZER_URL_2 = UI.class.getClassLoader().getResource("cpsc-210-blazer-v2.png");
    private static final ImageIcon TAIGA_IMAGE = new ImageIcon(TAIGA_URL);
    private static final ImageIcon BLAZER_IMAGE = new ImageIcon(BLAZER_URL);
    private static final ImageIcon BLAZER_IMAGE_2 = new ImageIcon(BLAZER_URL_2);
    private static final JLabel TAIGA_LABEL = new JLabel(TAIGA_IMAGE);
    private static final JLabel BLAZER_LABEL = new JLabel(BLAZER_IMAGE);
    private static final JLabel BLAZER_LABEL_2 = new JLabel(BLAZER_IMAGE_2);

    private static final URL LANDSCAPE_URL = UI.class.getClassLoader().getResource("cpsc-210-landscape.jpg");
    private static final ImageIcon LANDSCAPE_IMAGE = new ImageIcon(LANDSCAPE_URL);
    private static final JLabel LANDSCAPE_LABEL = new JLabel(LANDSCAPE_IMAGE);

    //======
    private Friends taiga;
    private Friends blazer;
    private FriendList listOfFriend;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MAIN WINDOW
    private JFrame window;

    // FIRST SCENE
    private JPanel titlePanel;
    private JButton startButton;

    // START SCENE
    private JPanel startPanel;
    private JPanel startSceneButtonPanel;
    private JPanel startSceneNoOption;
    private JButton choice1StartScene;
    private JButton choice2StartScene;

    // MEETING TAIGA SCENE
    private JPanel meetingTaigaScenePanel;
    private JPanel meetingTaigaSceneButtonPanel;
    private JButton choice1MeetingTaiga;
    private JButton choice2MeetingTaiga;

    // TRAVEL SCENE
    private JPanel travelScene;
    private JPanel travelSceneButtonPanel;
    private JButton choice1TravelScene;
    private JButton choice2TravelScene;
    private JButton choice3TravelScene;
    private JButton choice4TravelScene;

    // TRAVEL NORTH SCENE
    private JPanel northScene;

    // TRAVEL EAST SCENE
    private JPanel eastScene;

    // TRAVEL SOUTH SCENE - Meeting Blazer
    private JPanel meetingBlazerScenePanel;
    private JPanel meetingBlazerSceneButtonPanel;
    private JButton choice1MeetingBlazer;
    private JButton choice2MeetingBlazer;

    // TRAVEL WEST SCENE
    private JPanel westScene;

    // END SCENE
    private JPanel endScene;

    // SHOW FRIENDLIST
    private JPanel friendListPanelStart;
    private JPanel friendListPanelMeetingTaiga;
    private JPanel friendListPanelTravel;
    private JPanel friendListPanelMeetingBlazer;
    private JPanel friendListPanelEnd;

    // FRIENDLIST TAB
    // private JPanel friendListPanelTab;

    private JPanel friendListTabButtonPanelStart;
    private JButton choiceRemoveFriendListTabStart;
    private JButton choiceViewFriendListTabStart;
    private JButton choiceGreetFriendListTabStart;
    private JButton choiceNoneFriendListTabStart;

    private JPanel choiceRemoveFriendListPanelSetStart;
    private JPanel choiceRemoveFriendListButtonPanelSetStart;
    private JButton choiceRemoveFriendListSetStart;
    private JTextField choiceRemoveFriendListFieldStart;

    // =====

    private JPanel friendListTabButtonPanelTaiga;
    private JButton choiceRemoveFriendListTabTaiga;
    private JButton choiceViewFriendListTabTaiga;
    private JButton choiceGreetFriendListTabTaiga;
    private JButton choiceNoneFriendListTabTaiga;

    private JPanel choiceRemoveFriendListPanelSetTaiga;
    private JPanel choiceRemoveFriendListButtonPanelSetTaiga;
    private JButton choiceRemoveFriendListSetTaiga;
    private JTextField choiceRemoveFriendListFieldTaiga;

    // =====

    private JPanel friendListTabButtonPanelTravel;
    private JButton choiceRemoveFriendListTabTravel;
    private JButton choiceViewFriendListTabTravel;
    private JButton choiceGreetFriendListTabTravel;
    private JButton choiceNoneFriendListTabTravel;

    private JPanel choiceRemoveFriendListPanelSetTravel;
    private JPanel choiceRemoveFriendListButtonPanelSetTravel;
    private JButton choiceRemoveFriendListSetTravel;
    private JTextField choiceRemoveFriendListFieldTravel;
    // =====

    private JPanel friendListTabButtonPanelBlazer;
    private JButton choiceRemoveFriendListTabBlazer;
    private JButton choiceViewFriendListTabBlazer;
    private JButton choiceGreetFriendListTabBlazer;
    private JButton choiceNoneFriendListTabBlazer;

    private JPanel friendListTabButtonPanelEnd;
    private JButton choiceRemoveFriendListTabEnd;
    private JButton choiceViewFriendListTabEnd;
    private JButton choiceGreetFriendListTabEnd;
    private JButton choiceNoneFriendListTabEnd;

    private JButton accessFriendListTabButtonStart;
    private JButton accessFriendListTabButtonTaiga;
    private JButton accessFriendListTabButtonTravel;
    private JButton accessFriendListTabButtonBlazer;
    private JButton accessFriendListTabButtonEnd;

    private JPanel friendListPanelTabStart;
    private JPanel friendListPanelTabMeetingTaiga;
    private JPanel friendListPanelTabTravel;
    private JPanel friendListPanelTabMeetingBlazer;
    private JPanel friendListPanelTabEnd;

    // SAVE & LOAD
    private JButton saveButton;
    private JButton loadButton;
    private JButton printButton;
    private JPanel saveAndLoadPanel;


    // EFFECTS : CREATES THE WINDOW FOR WHICH OUR GAME WILL BE IN!
    public UI() {
        window = new JFrame("Adventures Of Life");
        window.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setBackground(Color.lightGray);
        window.setContentPane(BLAZER_LABEL);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LogPrinter lp = new LogPrinter();
                lp.printLog(EventLog.getInstance());
                super.windowClosing(e);

            }
        });

        instantiate();
        titleScreen();
        //saveAndLoadPanel();
        //friendListTabRemoveFriendFieldStart();
        window.setVisible(true);

    }


    // MODIFIES : THIS
    // EFFECTS : INSTANTIATES OUR FIELDS!
    private void instantiate() {
        taiga = new Taiga("Taiga", 25);
        blazer = new Blazer("Blazer", 56);
        listOfFriend = new FriendList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS : THE PANEL THAT HOLDS THE BUTTONS
    private void saveAndLoadPanel() {
        saveAndLoadPanel = new JPanel();
        saveAndLoadPanel.setBackground(Color.black);
        saveAndLoadPanel.setBounds(10, 500, 140, 50);
        saveAndLoadPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        saveAndLoadPanel.setLayout(new GridLayout(1, 3));
        saveGame();
        loadGame();
        printGame();
        window.add(saveAndLoadPanel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


    }

    // MODIFIES : THIS
    // EFFECTS  : SAVES THE STATE OF THE GAME!
    private void saveGame() {
        saveButton = new JButton("SAVE");
        saveButton.setFont(SERIF_FONT_SMALLER);
        saveButton.setForeground(Color.white);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        saveAndLoadPanel.add(saveButton);

    }

    // MODIFIES : THIS
    // EFFECTS  : LOADS THE STATE OF THE GAME!
    private void loadGame() {
        loadButton = new JButton("LOAD");
        loadButton.setFont(SERIF_FONT_SMALLER);
        loadButton.setForeground(Color.white);
        loadButton.setFocusable(false);
        loadButton.addActionListener(this);
        saveAndLoadPanel.add(loadButton);

    }

    // MODIFIES : THIS
    // EFFECTS  : PRINTS THE STATE OF THE GAME!
    private void printGame() {
        printButton = new JButton("PRINT");
        printButton.setFont(SERIF_FONT_SMALLER);
        printButton.setForeground(Color.white);
        printButton.setFocusable(false);
        printButton.addActionListener(this);
        saveAndLoadPanel.add(printButton);
    }

    // MODIFIES : THIS
    // EFFECTS : STARTS A TITLE SCREEN!
    private void titleScreen() {
        titlePanel = new JPanel();
        titlePanel.setBounds(200, 100, 401, 80);
        titlePanel.setBackground(Color.black);
        titlePanel.setBorder(BorderFactory.createLineBorder(BABY_BLUE));
        // FIGURE OUT WHY THIS DOESNT WORK!
        JLabel titleNameLabel = new JLabel("Adventures Of Life");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(TITLE_FONT);
        titlePanel.add(titleNameLabel);
        window.add(titlePanel);
        startButton();
        saveAndLoadPanel();
        //accessFriendListTab();
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS : DISPLAYS A BUTTON THAT ALLOWS US TO START THE GAME!
    private void startButton() {
        startButton = new JButton("START");
        startButton.setOpaque(true);
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setBounds(360, 400, 80, 50);
        startButton.setFocusable(false);
        //startButton.setBorder(BorderFactory.createEtchedBorder());
        startButton.setBorder(BorderFactory.createLineBorder(Color.red));
        startButton.setFont(SERIF_FONT);
        startButton.addActionListener(this);
        startButton.setActionCommand("start");
        //startButtonPanel.add(startButton);
        window.add(startButton);
        window.setVisible(true);

    }


    // MODIFIES : THIS
    // EFFECTS : SHOWS THE STARTING SCENE TO THE GAME
    private void startScene() {
        startPanel = new JPanel();
        startPanel.setBounds(200, 100, 400, 125);
        startPanel.setBackground(Color.black);

        JTextArea startText = new JTextArea("Welcome to the Desert, where life is anything but fun. "
                + "\nWould you care to engage in this world? ");
        startPanel.setBorder(BorderFactory.createLineBorder(Color.green));

        startText.setBounds(150, 100, 350, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        startPanel.add(startText);
        startSceneButton();
        accessFriendListTabStart();
        window.add(startPanel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : GIVES THE OPTIONS FOR THE START SCENE!
    private void startSceneButton() {
        startSceneButtonPanel = new JPanel();
        startSceneButtonPanel.setBounds(300, 330, 200, 100);
        startSceneButtonPanel.setBackground(Color.black);
        startSceneButtonPanel.setLayout(new GridLayout(2, 1));

        choice1StartScene = new JButton("YES");
        choice1StartScene.setBackground(Color.black);
        choice1StartScene.setForeground(Color.white);
        choice1StartScene.setFont(SERIF_FONT);
        choice1StartScene.setFocusable(false);
        choice1StartScene.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        startSceneButtonPanel.add(choice1StartScene);

        choice2StartScene = new JButton("NO");
        choice2StartScene.setBackground(Color.black);
        choice2StartScene.setForeground(Color.white);
        choice2StartScene.setFont(SERIF_FONT);
        choice2StartScene.setFocusable(false);
        choice2StartScene.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        startSceneButtonPanel.add(choice2StartScene);

        window.add(startSceneButtonPanel);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS : ENDING THE GAME IF WE CLICK NO!
    private void startSceneNoOption() {
        startSceneNoOption = new JPanel();
        startSceneNoOption.setBounds(165, 100, 480, 125);
        startSceneNoOption.setBackground(Color.black);

        JTextArea startText = new JTextArea(" *YOU LEFT THE PREMISE HOVERING IN FEAR!* ");
        /*startText.append("                               ");
        startText.append("FOOKING PUSSIO");*/
        startSceneNoOption.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        startSceneNoOption.add(startText);
        window.add(startSceneNoOption);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


    }

    // MODIFIES : THIS
    // EFFECTS : MEETING TAIGA SCENE!
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void meetingTaigaScene() {
        meetingTaigaScenePanel = new JPanel();
        meetingTaigaScenePanel.setBounds(175, 100, 450, 500);
        meetingTaigaScenePanel.setBackground(Color.black);

        JTextArea startText = new JTextArea("As you walk around, you see a short yet interesting girl. "
                + "\nAs you approach her, she asks if you want to be friends with her. ");
        meetingTaigaScenePanel.setBorder(BorderFactory.createLineBorder(Color.pink));

        startText.setBounds(400, 200, 400, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        meetingTaigaScenePanel.add(startText);
        meetingTaigaButton();
        accessFriendListTabTaiga();
        meetingTaigaScenePanel.add(TAIGA_LABEL);
        window.add(meetingTaigaScenePanel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


        /*System.out.println("As you walk around, you see a short yet interesting girl.");
        System.out.println("As you approach her, she asks if you want to be friends with her.");*/

    }

    // MODIFIES : THIS
    // EFFECTS  : THE OPTIONS FOR THE MEETING TAIGA SCENE!
    private void meetingTaigaButton() {
        meetingTaigaSceneButtonPanel = new JPanel();
        meetingTaigaSceneButtonPanel.setBounds(300, 330, 200, 100);
        meetingTaigaSceneButtonPanel.setBackground(Color.black);
        meetingTaigaSceneButtonPanel.setLayout(new GridLayout(2, 1));
        //window.setVisible(true);

        choice1MeetingTaiga = new JButton("YES");
        choice1MeetingTaiga.setBackground(Color.black);
        choice1MeetingTaiga.setForeground(Color.white);
        choice1MeetingTaiga.setFont(SERIF_FONT);
        choice1MeetingTaiga.setFocusable(false);
        choice1MeetingTaiga.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        meetingTaigaSceneButtonPanel.add(choice1MeetingTaiga);

        choice2MeetingTaiga = new JButton("NO");
        choice2MeetingTaiga.setBackground(Color.black);
        choice2MeetingTaiga.setForeground(Color.white);
        choice2MeetingTaiga.setFont(SERIF_FONT);
        choice2MeetingTaiga.setFocusable(false);
        choice2MeetingTaiga.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        meetingTaigaSceneButtonPanel.add(choice2MeetingTaiga);

        window.add(meetingTaigaSceneButtonPanel);
        window.setVisible(true);

        //window.setVisible(true);
        // IF THE ANSWER IS YES, WE NEED TO EXPAND OUR FRIENDLIST!
    }

    // MODIFIES : THIS
    // EFFECTS : TAKES US TO THE TRAVEL SCENE!
    private void travelScene() {
        travelScene = new JPanel();
        travelScene.setBounds(165, 100, 480, 125);
        travelScene.setBackground(Color.black);

        JTextArea startText = new JTextArea("You have decided to travel in the search of society! "
                + "\nHowever, we can only choose 1 direction! "
                + "\n*CHOOSE WISELY* ");

        travelScene.setBorder(BorderFactory.createLineBorder(LIGHT_CYAN));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        travelScene.add(startText);
        travelSceneButton();
        accessFriendListTabTravel();
        window.add(travelScene);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFES : THIS
    // EFFECTS : BUTTONS FOR THE TRAVEL SCENE
    private void travelSceneButton() {
        travelSceneButtonPanel = new JPanel();
        travelSceneButtonPanel.setBounds(300, 330, 200, 100);
        travelSceneButtonPanel.setBackground(Color.black);
        travelSceneButtonPanel.setLayout(new GridLayout(4, 1));
        //window.setVisible(true);
        travelSceneButtonNorth();
        travelSceneButtonEast();
        travelSceneButtonSouth();
        travelSceneButtonWest();

        window.add(travelSceneButtonPanel);
        window.setVisible(true);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NORTH!
    private void travelSceneButtonNorth() {
        choice1TravelScene = new JButton("NORTH");
        choice1TravelScene.setBackground(Color.black);
        choice1TravelScene.setForeground(Color.white);
        choice1TravelScene.setFont(SERIF_FONT);
        choice1TravelScene.setFocusable(false);
        choice1TravelScene.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        travelSceneButtonPanel.add(choice1TravelScene);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING EAST!
    private void travelSceneButtonEast() {
        choice2TravelScene = new JButton("EAST");
        choice2TravelScene.setBackground(Color.black);
        choice2TravelScene.setForeground(Color.white);
        choice2TravelScene.setFont(SERIF_FONT);
        choice2TravelScene.setFocusable(false);
        choice2TravelScene.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        travelSceneButtonPanel.add(choice2TravelScene);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING SOUTH!
    private void travelSceneButtonSouth() {
        choice3TravelScene = new JButton("SOUTH");
        choice3TravelScene.setBackground(Color.black);
        choice3TravelScene.setForeground(Color.white);
        choice3TravelScene.setFont(SERIF_FONT);
        choice3TravelScene.setFocusable(false);
        choice3TravelScene.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        travelSceneButtonPanel.add(choice3TravelScene);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING WEST!
    private void travelSceneButtonWest() {
        choice4TravelScene = new JButton("WEST");
        choice4TravelScene.setBackground(Color.black);
        choice4TravelScene.setForeground(Color.white);
        choice4TravelScene.setFont(SERIF_FONT);
        choice4TravelScene.setFocusable(false);
        choice4TravelScene.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        travelSceneButtonPanel.add(choice4TravelScene);

    }

    // MODIFIES : THIS
    // EFFECTS  : REPRESENTS WHAT HAPPENS IF WE GO NORTH!
    private void travelNorthScene() {
        northScene = new JPanel();
        northScene.setBounds(165, 100, 480, 125);
        northScene.setBackground(Color.black);

        JTextArea startText = new JTextArea("Traveling North led to the cold tundra where the bitter cold ended us!");
        /*startText.append("                               ");
        startText.append("FOOKING PUSSIO");*/
        northScene.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        northScene.add(startText);
        window.add(northScene);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : REPRESENTS WHAT HAPPENS IF WE GO EAST!
    private void travelEastScene() {
        eastScene = new JPanel();
        eastScene.setBounds(165, 100, 480, 125);
        eastScene.setBackground(Color.black);

        JTextArea startText = new JTextArea("Traveling East led to a happy farm life where everyday remains the same!");
        eastScene.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        eastScene.add(startText);
        window.add(eastScene);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }


    // MODIFIES : THIS
    // EFFECTS : MEETING BLAZER SCENE!
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void meetingBlazerScene() {
        meetingBlazerScenePanel = new JPanel();
        meetingBlazerScenePanel.setBounds(175, 100, 450, 500);
        meetingBlazerScenePanel.setBackground(Color.black);

        JTextArea startText = new JTextArea("As you walk South, you see a man engulfed by flame."
                + "\nAs you approach him to help him, he painlessly greets you."
                + "\nBlazer: I'm Blazer, can I join you?");
        meetingBlazerScenePanel.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 400, 300);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        meetingBlazerScenePanel.add(startText);
        meetingBlazerButton();
        accessFriendListTabBlazer();
        meetingBlazerScenePanel.add(BLAZER_LABEL_2);
        window.add(meetingBlazerScenePanel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

        /*System.out.println("As you walk around, you see a short yet interesting girl.");
        System.out.println("As you approach her, she asks if you want to be friends with her.");*/

    }

    // MODIFIES : THIS
    // EFFECTS  : THE OPTIONS FOR THE MEETING TAIGA SCENE!
    private void meetingBlazerButton() {
        meetingBlazerSceneButtonPanel = new JPanel();
        meetingBlazerSceneButtonPanel.setBounds(300, 330, 200, 100);
        meetingBlazerSceneButtonPanel.setBackground(Color.black);
        meetingBlazerSceneButtonPanel.setLayout(new GridLayout(2, 1));
        //window.setVisible(true);

        choice1MeetingBlazer = new JButton("YES");
        choice1MeetingBlazer.setBackground(Color.black);
        choice1MeetingBlazer.setForeground(Color.white);
        choice1MeetingBlazer.setFont(SERIF_FONT);
        choice1MeetingBlazer.setFocusable(false);
        choice1MeetingBlazer.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        meetingBlazerSceneButtonPanel.add(choice1MeetingBlazer);

        choice2MeetingBlazer = new JButton("NO");
        choice2MeetingBlazer.setBackground(Color.black);
        choice2MeetingBlazer.setForeground(Color.white);
        choice2MeetingBlazer.setFont(SERIF_FONT);
        choice2MeetingBlazer.setFocusable(false);
        choice2MeetingBlazer.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        meetingBlazerSceneButtonPanel.add(choice2MeetingBlazer);

        window.add(meetingBlazerSceneButtonPanel);
        window.setVisible(true);

        //window.setVisible(true);
        // IF THE ANSWER IS YES, WE NEED TO EXPAND OUR FRIENDLIST!
    }

    // MODIFIES : THIS
    // EFFECTS  : REPRESENTS WHAT HAPPENS IF WE GO WEST!
    private void travelWestScene() {
        westScene = new JPanel();
        westScene.setBounds(165, 100, 480, 125);
        westScene.setBackground(Color.black);

        JTextArea startText = new JTextArea("Traveling West led you to a body of water where you drowned.");
        westScene.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        westScene.add(startText);
        window.add(westScene);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : REPRESENTS THE END OF THE ACT!
    private void endScene() {
        endScene = new JPanel();
        endScene.setBounds(165, 100, 480, 180);
        endScene.setBackground(Color.black);

        JTextArea startText = new JTextArea("As you approach an unidentified object, everything becomes blurry."
                + "\nThe sensation of elevation takes over your body."
                + "\nWithout a doubt, you're being transported."
                + "\nWhat happens next?");

        endScene.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        showFriendListEnd();
        accessFriendListTabEnd();
        endScene.add(startText);
        window.add(endScene);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : DISPLAYS OUR CURRENT FRIENDS!
    private void showFriendListStart() {
        friendListPanelStart = new JPanel();
        friendListPanelStart.setBounds(520, 30, 250, 40);
        friendListPanelStart.setBackground(Color.black);

        JTextArea startText = new JTextArea(listOfFriend.returnFriendList());
        friendListPanelStart.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 230, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT_SMALL);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelStart.add(startText);
        window.add(friendListPanelStart);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : DISPLAYS OUR CURRENT FRIENDS!
    private void showFriendListMeetingTaiga() {
        friendListPanelMeetingTaiga = new JPanel();
        friendListPanelMeetingTaiga.setBounds(520, 30, 250, 40);
        friendListPanelMeetingTaiga.setBackground(Color.black);

        JTextArea startText = new JTextArea(listOfFriend.returnFriendList());
        friendListPanelMeetingTaiga.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 230, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT_SMALL);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelMeetingTaiga.add(startText);
        window.add(friendListPanelMeetingTaiga);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : DISPLAYS OUR CURRENT FRIENDS!
    private void showFriendListTravel() {
        friendListPanelTravel = new JPanel();
        friendListPanelTravel.setBounds(520, 30, 250, 40);
        friendListPanelTravel.setBackground(Color.black);

        JTextArea startText = new JTextArea(listOfFriend.returnFriendList());
        friendListPanelTravel.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 230, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT_SMALL);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTravel.add(startText);
        window.add(friendListPanelTravel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : DISPLAYS OUR CURRENT FRIENDS!
    private void showFriendListMeetingBlazer() {
        friendListPanelMeetingBlazer = new JPanel();
        friendListPanelMeetingBlazer.setBounds(520, 30, 250, 40);
        friendListPanelMeetingBlazer.setBackground(Color.black);

        JTextArea startText = new JTextArea(listOfFriend.returnFriendList());
        friendListPanelMeetingBlazer.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 230, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT_SMALL);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelMeetingBlazer.add(startText);
        window.add(friendListPanelMeetingBlazer);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();
    }

    // MODIFIES : THIS
    // EFFECTS  : DISPLAYS OUR CURRENT FRIENDS!
    private void showFriendListEnd() {
        friendListPanelEnd = new JPanel();
        friendListPanelEnd.setBounds(520, 30, 250, 40);
        friendListPanelEnd.setBackground(Color.black);

        JTextArea startText = new JTextArea(listOfFriend.returnFriendList());
        startText.setText(listOfFriend.returnFriendList());
        friendListPanelEnd.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 230, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT_SMALL);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelEnd.add(startText);
        window.add(friendListPanelEnd);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST TAB!
    private void accessFriendListTabStart() {
        accessFriendListTabButtonStart = new JButton("FRIENDS");
        //System.out.println("access friends");
        accessFriendListTabButtonStart.setOpaque(true);
        accessFriendListTabButtonStart.setBackground(Color.darkGray);
        accessFriendListTabButtonStart.setForeground(Color.white);
        accessFriendListTabButtonStart.setBounds(60, 30, 80, 40);
        accessFriendListTabButtonStart.setFocusable(false);
        accessFriendListTabButtonStart.setBorder(BorderFactory.createLineBorder(Color.red));
        accessFriendListTabButtonStart.setFont(SERIF_FONT_SMALL);
        accessFriendListTabButtonStart.addActionListener(this);
        startPanel.add(accessFriendListTabButtonStart);

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST TAB!
    private void accessFriendListTabTaiga() {
        accessFriendListTabButtonTaiga = new JButton("FRIENDS");
        accessFriendListTabButtonTaiga.setOpaque(true);
        accessFriendListTabButtonTaiga.setBackground(Color.darkGray);
        accessFriendListTabButtonTaiga.setForeground(Color.white);
        accessFriendListTabButtonTaiga.setBounds(60, 30, 80, 40);
        accessFriendListTabButtonTaiga.setFocusable(false);
        accessFriendListTabButtonTaiga.setBorder(BorderFactory.createLineBorder(Color.red));
        accessFriendListTabButtonTaiga.setFont(SERIF_FONT_SMALL);
        accessFriendListTabButtonTaiga.addActionListener(this);
        meetingTaigaScenePanel.add(accessFriendListTabButtonTaiga);

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST TAB!
    private void accessFriendListTabTravel() {
        accessFriendListTabButtonTravel = new JButton("FRIENDS");
        accessFriendListTabButtonTravel.setOpaque(true);
        accessFriendListTabButtonTravel.setBackground(Color.darkGray);
        accessFriendListTabButtonTravel.setForeground(Color.white);
        accessFriendListTabButtonTravel.setBounds(60, 30, 80, 40);
        accessFriendListTabButtonTravel.setFocusable(false);
        accessFriendListTabButtonTravel.setBorder(BorderFactory.createLineBorder(Color.red));
        accessFriendListTabButtonTravel.setFont(SERIF_FONT_SMALL);
        accessFriendListTabButtonTravel.addActionListener(this);
        travelScene.add(accessFriendListTabButtonTravel);

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST TAB!
    private void accessFriendListTabBlazer() {
        accessFriendListTabButtonBlazer = new JButton("FRIENDS");
        accessFriendListTabButtonBlazer.setOpaque(true);
        accessFriendListTabButtonBlazer.setBackground(Color.darkGray);
        accessFriendListTabButtonBlazer.setForeground(Color.white);
        accessFriendListTabButtonBlazer.setBounds(60, 30, 80, 40);
        accessFriendListTabButtonBlazer.setFocusable(false);
        accessFriendListTabButtonBlazer.setBorder(BorderFactory.createLineBorder(Color.red));
        accessFriendListTabButtonBlazer.setFont(SERIF_FONT_SMALL);
        accessFriendListTabButtonBlazer.addActionListener(this);
        meetingBlazerScenePanel.add(accessFriendListTabButtonBlazer);

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST TAB!
    private void accessFriendListTabEnd() {
        accessFriendListTabButtonEnd = new JButton("FRIENDS");
        accessFriendListTabButtonEnd.setOpaque(true);
        accessFriendListTabButtonEnd.setBackground(Color.darkGray);
        accessFriendListTabButtonEnd.setForeground(Color.white);
        accessFriendListTabButtonEnd.setBounds(60, 30, 80, 40);
        accessFriendListTabButtonEnd.setFocusable(false);
        accessFriendListTabButtonEnd.setBorder(BorderFactory.createLineBorder(Color.red));
        accessFriendListTabButtonEnd.setFont(SERIF_FONT_SMALL);
        accessFriendListTabButtonEnd.addActionListener(this);
        endScene.add(accessFriendListTabButtonEnd);

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST PANEL!
    private void friendListPanelStart() {
        friendListPanelTabStart = new JPanel();
        friendListPanelTabStart.setBounds(165, 100, 480, 125);
        friendListPanelTabStart.setBackground(Color.black);

        JTextArea startText = new JTextArea("What would you like to do with your friends?");

        friendListPanelTabStart.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTabStart.add(startText);
        friendListButtonPanelStart();
        window.add(friendListPanelTabStart);
        friendListPanelTabStart.setVisible(true);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST PANEL!
    private void friendListPanelTaiga() {
        friendListPanelTabMeetingTaiga = new JPanel();
        friendListPanelTabMeetingTaiga.setBounds(165, 100, 480, 125);
        friendListPanelTabMeetingTaiga.setBackground(Color.black);

        JTextArea startText = new JTextArea("What would you like to do with your friends?");

        friendListPanelTabMeetingTaiga.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTabMeetingTaiga.add(startText);
        friendListButtonPanelTaiga();
        window.add(friendListPanelTabMeetingTaiga);
        //riendListPanelTabMeetingTaiga.setVisible(true);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST PANEL!
    private void friendListPanelTravel() {
        friendListPanelTabTravel = new JPanel();
        friendListPanelTabTravel.setBounds(165, 100, 480, 125);
        friendListPanelTabTravel.setBackground(Color.black);

        JTextArea startText = new JTextArea("What would you like to do with your friends?");

        friendListPanelTabTravel.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTabTravel.add(startText);
        friendListButtonPanelTravel();
        window.add(friendListPanelTabTravel);
        friendListPanelTabTravel.setVisible(true);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST PANEL!
    private void friendListPanelBlazer() {
        friendListPanelTabMeetingBlazer = new JPanel();
        friendListPanelTabMeetingBlazer.setBounds(165, 100, 480, 125);
        friendListPanelTabMeetingBlazer.setBackground(Color.black);

        JTextArea startText = new JTextArea("What would you like to do with your friends?");

        friendListPanelTabMeetingBlazer.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTabMeetingBlazer.add(startText);
        friendListButtonPanelBlazer();
        window.add(friendListPanelTabMeetingBlazer);
        friendListPanelTabMeetingBlazer.setVisible(true);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : TAKES US TO THE FRIENDLIST PANEL!
    private void friendListPanelEnd() {
        friendListPanelTabEnd = new JPanel();
        friendListPanelTabEnd.setBounds(165, 100, 480, 125);
        friendListPanelTabEnd.setBackground(Color.black);

        JTextArea startText = new JTextArea("What would you like to do with your friends?");

        friendListPanelTabEnd.setBorder(BorderFactory.createLineBorder(Color.red));

        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        friendListPanelTabEnd.add(startText);
        friendListButtonPanelEnd();
        window.add(friendListPanelTabEnd);
        friendListPanelTabEnd.setVisible(true);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();

    }

    // MODIFIES : THIS
    // EFFECTS  : THE BUTTON PANEL FOR OUR FRIENDLIST!
    private void friendListButtonPanelStart() {
        friendListTabButtonPanelStart = new JPanel();
        friendListTabButtonPanelStart.setBounds(300, 330, 200, 100);
        friendListTabButtonPanelStart.setBackground(Color.black);
        friendListTabButtonPanelStart.setLayout(new GridLayout(4, 1));

        friendListTabRemoveFriendStart();
        friendListTabViewFriendStart();
        friendListTabGreetFriendStart();
        friendListTabNoneStart();

        window.add(friendListTabButtonPanelStart);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING REMOVE FRIEND!
    private void friendListTabRemoveFriendStart() {
        choiceRemoveFriendListTabStart = new JButton("REMOVE");
        choiceRemoveFriendListTabStart.setBackground(Color.black);
        choiceRemoveFriendListTabStart.setForeground(Color.white);
        choiceRemoveFriendListTabStart.setFont(SERIF_FONT);
        choiceRemoveFriendListTabStart.setFocusable(false);
        choiceRemoveFriendListTabStart.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        friendListTabButtonPanelStart.add(choiceRemoveFriendListTabStart);
    }

    // MODIFIES : THIS
    // EFFECTS  : LETS US CHOOSE WHICH FRIEND TO REMOVE
    private void friendListTabRemoveFriendFieldStart() {
        choiceRemoveFriendListPanelSetStart = new JPanel();
        choiceRemoveFriendListPanelSetStart.setBounds(165, 100, 480, 125);
        choiceRemoveFriendListPanelSetStart.setBackground(Color.black);
        choiceRemoveFriendListPanelSetStart.setBorder(BorderFactory.createLineBorder(Color.red));

        JTextArea startText = new JTextArea("Which Friend would you like to do this to?");
        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        choiceRemoveFriendListPanelSetStart.add(startText);
        friendListTabRemoveFriendStartButton();
        window.add(choiceRemoveFriendListPanelSetStart);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


    }

    // MODIFIES : THIS
    // EFFECTS : CREATES THE BUTTON AND FIELD THAT APPLIES THE OPTION TO A FRIEND!
    private void friendListTabRemoveFriendStartButton() {
        choiceRemoveFriendListButtonPanelSetStart = new JPanel();
        choiceRemoveFriendListButtonPanelSetStart.setBounds(300, 330, 200, 30);
        choiceRemoveFriendListButtonPanelSetStart.setBackground(Color.black);
        choiceRemoveFriendListButtonPanelSetStart.setLayout(new GridLayout(1, 1));

        choiceRemoveFriendListSetStart = new JButton("SET");
        choiceRemoveFriendListSetStart.setBackground(Color.black);
        choiceRemoveFriendListSetStart.setForeground(Color.white);
        choiceRemoveFriendListSetStart.setFont(SERIF_FONT);
        choiceRemoveFriendListSetStart.setFocusable(false);
        choiceRemoveFriendListSetStart.addActionListener(this);

        choiceRemoveFriendListFieldStart = new JTextField();
        choiceRemoveFriendListFieldStart.setFont(SERIF_FONT);
        choiceRemoveFriendListFieldStart.setBackground(Color.darkGray);
        choiceRemoveFriendListFieldStart.setForeground(Color.white);

        choiceRemoveFriendListButtonPanelSetStart.add(choiceRemoveFriendListSetStart);
        choiceRemoveFriendListButtonPanelSetStart.add(choiceRemoveFriendListFieldStart);
        window.add(choiceRemoveFriendListButtonPanelSetStart);

    }


    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING VIEW FRIEND!
    private void friendListTabViewFriendStart() {
        choiceViewFriendListTabStart = new JButton("VIEW");
        choiceViewFriendListTabStart.setBackground(Color.black);
        choiceViewFriendListTabStart.setForeground(Color.white);
        choiceViewFriendListTabStart.setFont(SERIF_FONT);
        choiceViewFriendListTabStart.setFocusable(false);
        choiceViewFriendListTabStart.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelStart.add(choiceViewFriendListTabStart);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING GREET FRIEND!
    private void friendListTabGreetFriendStart() {
        choiceGreetFriendListTabStart = new JButton("GREET");
        choiceGreetFriendListTabStart.setBackground(Color.black);
        choiceGreetFriendListTabStart.setForeground(Color.white);
        choiceGreetFriendListTabStart.setFont(SERIF_FONT);
        choiceGreetFriendListTabStart.setFocusable(false);
        choiceGreetFriendListTabStart.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelStart.add(choiceGreetFriendListTabStart);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NONE OF THE OPTIONS!
    private void friendListTabNoneStart() {
        choiceNoneFriendListTabStart = new JButton("NONE");
        choiceNoneFriendListTabStart.setBackground(Color.black);
        choiceNoneFriendListTabStart.setForeground(Color.white);
        choiceNoneFriendListTabStart.setFont(SERIF_FONT);
        choiceNoneFriendListTabStart.setFocusable(false);
        choiceNoneFriendListTabStart.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelStart.add(choiceNoneFriendListTabStart);

    }

    // MODIFIES : THIS
    // EFFECTS  : THE BUTTON PANEL FOR OUR FRIENDLIST!
    private void friendListButtonPanelTaiga() {
        friendListTabButtonPanelTaiga = new JPanel();
        friendListTabButtonPanelTaiga.setBounds(300, 330, 200, 100);
        friendListTabButtonPanelTaiga.setBackground(Color.black);
        friendListTabButtonPanelTaiga.setLayout(new GridLayout(4, 1));

        friendListTabRemoveFriendTaiga();
        friendListTabViewFriendTaiga();
        friendListTabGreetFriendTaiga();
        friendListTabNoneTaiga();

        window.add(friendListTabButtonPanelTaiga);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING REMOVE FRIEND!
    private void friendListTabRemoveFriendTaiga() {
        choiceRemoveFriendListTabTaiga = new JButton("REMOVE");
        choiceRemoveFriendListTabTaiga.setBackground(Color.black);
        choiceRemoveFriendListTabTaiga.setForeground(Color.white);
        choiceRemoveFriendListTabTaiga.setFont(SERIF_FONT);
        choiceRemoveFriendListTabTaiga.setFocusable(false);
        choiceRemoveFriendListTabTaiga.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        friendListTabButtonPanelTaiga.add(choiceRemoveFriendListTabTaiga);
    }

    // MODIFIES : THIS
    // EFFECTS  : LETS US CHOOSE WHICH FRIEND TO REMOVE
    private void friendListTabRemoveFriendFieldTaiga() {
        choiceRemoveFriendListPanelSetTaiga = new JPanel();
        choiceRemoveFriendListPanelSetTaiga.setBounds(165, 100, 480, 125);
        choiceRemoveFriendListPanelSetTaiga.setBackground(Color.black);
        choiceRemoveFriendListPanelSetTaiga.setBorder(BorderFactory.createLineBorder(Color.red));

        JTextArea startText = new JTextArea("Which Friend would you like to do this to?");
        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        choiceRemoveFriendListPanelSetTaiga.add(startText);
        friendListTabRemoveFriendTaigaButton();
        window.add(choiceRemoveFriendListPanelSetTaiga);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


    }

    // MODIFIES : THIS
    // EFFECTS : CREATES THE BUTTON AND FIELD THAT APPLIES THE OPTION TO A FRIEND!
    private void friendListTabRemoveFriendTaigaButton() {
        choiceRemoveFriendListButtonPanelSetTaiga = new JPanel();
        choiceRemoveFriendListButtonPanelSetTaiga.setBounds(300, 330, 200, 30);
        choiceRemoveFriendListButtonPanelSetTaiga.setBackground(Color.black);
        choiceRemoveFriendListButtonPanelSetTaiga.setLayout(new GridLayout(1, 1));

        choiceRemoveFriendListSetTaiga = new JButton("SET");
        choiceRemoveFriendListSetTaiga.setBackground(Color.black);
        choiceRemoveFriendListSetTaiga.setForeground(Color.white);
        choiceRemoveFriendListSetTaiga.setFont(SERIF_FONT);
        choiceRemoveFriendListSetTaiga.setFocusable(false);
        choiceRemoveFriendListSetTaiga.addActionListener(this);

        choiceRemoveFriendListFieldTaiga = new JTextField();
        choiceRemoveFriendListFieldTaiga.setFont(SERIF_FONT);
        choiceRemoveFriendListFieldTaiga.setBackground(Color.darkGray);
        choiceRemoveFriendListFieldTaiga.setForeground(Color.white);

        choiceRemoveFriendListButtonPanelSetTaiga.add(choiceRemoveFriendListSetTaiga);
        choiceRemoveFriendListButtonPanelSetTaiga.add(choiceRemoveFriendListFieldTaiga);
        window.add(choiceRemoveFriendListButtonPanelSetTaiga);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING VIEW FRIEND!
    private void friendListTabViewFriendTaiga() {
        choiceViewFriendListTabTaiga = new JButton("VIEW");
        choiceViewFriendListTabTaiga.setBackground(Color.black);
        choiceViewFriendListTabTaiga.setForeground(Color.white);
        choiceViewFriendListTabTaiga.setFont(SERIF_FONT);
        choiceViewFriendListTabTaiga.setFocusable(false);
        choiceViewFriendListTabTaiga.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTaiga.add(choiceViewFriendListTabTaiga);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING GREET FRIEND!
    private void friendListTabGreetFriendTaiga() {
        choiceGreetFriendListTabTaiga = new JButton("GREET");
        choiceGreetFriendListTabTaiga.setBackground(Color.black);
        choiceGreetFriendListTabTaiga.setForeground(Color.white);
        choiceGreetFriendListTabTaiga.setFont(SERIF_FONT);
        choiceGreetFriendListTabTaiga.setFocusable(false);
        choiceGreetFriendListTabTaiga.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTaiga.add(choiceGreetFriendListTabTaiga);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NONE OF THE OPTIONS!
    private void friendListTabNoneTaiga() {
        choiceNoneFriendListTabTaiga = new JButton("NONE");
        choiceNoneFriendListTabTaiga.setBackground(Color.black);
        choiceNoneFriendListTabTaiga.setForeground(Color.white);
        choiceNoneFriendListTabTaiga.setFont(SERIF_FONT);
        choiceNoneFriendListTabTaiga.setFocusable(false);
        choiceNoneFriendListTabTaiga.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTaiga.add(choiceNoneFriendListTabTaiga);

    }

    // MODIFIES : THIS
    // EFFECTS  : THE BUTTON PANEL FOR OUR FRIENDLIST!
    private void friendListButtonPanelTravel() {
        friendListTabButtonPanelTravel = new JPanel();
        friendListTabButtonPanelTravel.setBounds(300, 330, 200, 100);
        friendListTabButtonPanelTravel.setBackground(Color.black);
        friendListTabButtonPanelTravel.setLayout(new GridLayout(4, 1));

        friendListTabRemoveFriendTravel();
        friendListTabViewFriendTravel();
        friendListTabGreetFriendTravel();
        friendListTabNoneTravel();

        window.add(friendListTabButtonPanelTravel);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING REMOVE FRIEND!
    private void friendListTabRemoveFriendTravel() {
        choiceRemoveFriendListTabTravel = new JButton("REMOVE");
        choiceRemoveFriendListTabTravel.setBackground(Color.black);
        choiceRemoveFriendListTabTravel.setForeground(Color.white);
        choiceRemoveFriendListTabTravel.setFont(SERIF_FONT);
        choiceRemoveFriendListTabTravel.setFocusable(false);
        choiceRemoveFriendListTabTravel.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        friendListTabButtonPanelTravel.add(choiceRemoveFriendListTabTravel);
    }

    // MODIFIES : THIS
    // EFFECTS  : LETS US CHOOSE WHICH FRIEND TO REMOVE
    private void friendListTabRemoveFriendFieldTravel() {
        choiceRemoveFriendListPanelSetTravel = new JPanel();
        choiceRemoveFriendListPanelSetTravel.setBounds(165, 100, 480, 125);
        choiceRemoveFriendListPanelSetTravel.setBackground(Color.black);
        choiceRemoveFriendListPanelSetTravel.setBorder(BorderFactory.createLineBorder(Color.red));

        JTextArea startText = new JTextArea("Which Friend would you like to do this to?");
        startText.setBounds(400, 200, 450, 250);
        startText.setOpaque(true);
        startText.setBackground(Color.black);
        startText.setForeground(Color.white);
        startText.setFont(SERIF_FONT);
        startText.setEditable(false);
        startText.setWrapStyleWord(true);
        startText.setLineWrap(true);
        choiceRemoveFriendListPanelSetTravel.add(startText);
        friendListTabRemoveFriendTravelButton();
        window.add(choiceRemoveFriendListPanelSetTravel);
        window.setVisible(true);
        ((JComponent) window.getContentPane()).revalidate();
        window.repaint();


    }

    // MODIFIES : THIS
    // EFFECTS : CREATES THE BUTTON AND FIELD THAT APPLIES THE OPTION TO A FRIEND!
    private void friendListTabRemoveFriendTravelButton() {
        choiceRemoveFriendListButtonPanelSetTravel = new JPanel();
        choiceRemoveFriendListButtonPanelSetTravel.setBounds(300, 330, 200, 30);
        choiceRemoveFriendListButtonPanelSetTravel.setBackground(Color.black);
        choiceRemoveFriendListButtonPanelSetTravel.setLayout(new GridLayout(1, 1));

        choiceRemoveFriendListSetTravel = new JButton("SET");
        choiceRemoveFriendListSetTravel.setBackground(Color.black);
        choiceRemoveFriendListSetTravel.setForeground(Color.white);
        choiceRemoveFriendListSetTravel.setFont(SERIF_FONT);
        choiceRemoveFriendListSetTravel.setFocusable(false);
        choiceRemoveFriendListSetTravel.addActionListener(this);

        choiceRemoveFriendListFieldTravel = new JTextField();
        choiceRemoveFriendListFieldTravel.setFont(SERIF_FONT);
        choiceRemoveFriendListFieldTravel.setBackground(Color.darkGray);
        choiceRemoveFriendListFieldTravel.setForeground(Color.white);

        choiceRemoveFriendListButtonPanelSetTravel.add(choiceRemoveFriendListSetTravel);
        choiceRemoveFriendListButtonPanelSetTravel.add(choiceRemoveFriendListFieldTravel);
        window.add(choiceRemoveFriendListButtonPanelSetTravel);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING VIEW FRIEND!
    private void friendListTabViewFriendTravel() {
        choiceViewFriendListTabTravel = new JButton("VIEW");
        choiceViewFriendListTabTravel.setBackground(Color.black);
        choiceViewFriendListTabTravel.setForeground(Color.white);
        choiceViewFriendListTabTravel.setFont(SERIF_FONT);
        choiceViewFriendListTabTravel.setFocusable(false);
        choiceViewFriendListTabTravel.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTravel.add(choiceViewFriendListTabTravel);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING GREET FRIEND!
    private void friendListTabGreetFriendTravel() {
        choiceGreetFriendListTabTravel = new JButton("GREET");
        choiceGreetFriendListTabTravel.setBackground(Color.black);
        choiceGreetFriendListTabTravel.setForeground(Color.white);
        choiceGreetFriendListTabTravel.setFont(SERIF_FONT);
        choiceGreetFriendListTabTravel.setFocusable(false);
        choiceGreetFriendListTabTravel.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTravel.add(choiceGreetFriendListTabTravel);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NONE OF THE OPTIONS!
    private void friendListTabNoneTravel() {
        choiceNoneFriendListTabTravel = new JButton("NONE");
        choiceNoneFriendListTabTravel.setBackground(Color.black);
        choiceNoneFriendListTabTravel.setForeground(Color.white);
        choiceNoneFriendListTabTravel.setFont(SERIF_FONT);
        choiceNoneFriendListTabTravel.setFocusable(false);
        choiceNoneFriendListTabTravel.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelTravel.add(choiceNoneFriendListTabTravel);

    }

    // MODIFIES : THIS
    // EFFECTS  : THE BUTTON PANEL FOR OUR FRIENDLIST!
    private void friendListButtonPanelBlazer() {
        friendListTabButtonPanelBlazer = new JPanel();
        friendListTabButtonPanelBlazer.setBounds(300, 330, 200, 100);
        friendListTabButtonPanelBlazer.setBackground(Color.black);
        friendListTabButtonPanelBlazer.setLayout(new GridLayout(4, 1));

        friendListTabRemoveFriendBlazer();
        friendListTabViewFriendBlazer();
        friendListTabGreetFriendBlazer();
        friendListTabNoneBlazer();

        window.add(friendListTabButtonPanelBlazer);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING REMOVE FRIEND!
    private void friendListTabRemoveFriendBlazer() {
        choiceRemoveFriendListTabBlazer = new JButton("REMOVE");
        choiceRemoveFriendListTabBlazer.setBackground(Color.black);
        choiceRemoveFriendListTabBlazer.setForeground(Color.white);
        choiceRemoveFriendListTabBlazer.setFont(SERIF_FONT);
        choiceRemoveFriendListTabBlazer.setFocusable(false);
        choiceRemoveFriendListTabBlazer.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        friendListTabButtonPanelBlazer.add(choiceRemoveFriendListTabBlazer);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING VIEW FRIEND!
    private void friendListTabViewFriendBlazer() {
        choiceViewFriendListTabBlazer = new JButton("VIEW");
        choiceViewFriendListTabBlazer.setBackground(Color.black);
        choiceViewFriendListTabBlazer.setForeground(Color.white);
        choiceViewFriendListTabBlazer.setFont(SERIF_FONT);
        choiceViewFriendListTabBlazer.setFocusable(false);
        choiceViewFriendListTabBlazer.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelBlazer.add(choiceViewFriendListTabBlazer);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING GREET FRIEND!
    private void friendListTabGreetFriendBlazer() {
        choiceGreetFriendListTabBlazer = new JButton("GREET");
        choiceGreetFriendListTabBlazer.setBackground(Color.black);
        choiceGreetFriendListTabBlazer.setForeground(Color.white);
        choiceGreetFriendListTabBlazer.setFont(SERIF_FONT);
        choiceGreetFriendListTabBlazer.setFocusable(false);
        choiceGreetFriendListTabBlazer.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelBlazer.add(choiceGreetFriendListTabBlazer);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NONE OF THE OPTIONS!
    private void friendListTabNoneBlazer() {
        choiceNoneFriendListTabBlazer = new JButton("NONE");
        choiceNoneFriendListTabBlazer.setBackground(Color.black);
        choiceNoneFriendListTabBlazer.setForeground(Color.white);
        choiceNoneFriendListTabBlazer.setFont(SERIF_FONT);
        choiceNoneFriendListTabBlazer.setFocusable(false);
        choiceNoneFriendListTabBlazer.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelBlazer.add(choiceNoneFriendListTabBlazer);

    }

    // MODIFIES : THIS
    // EFFECTS  : THE BUTTON PANEL FOR OUR FRIENDLIST!
    private void friendListButtonPanelEnd() {
        friendListTabButtonPanelEnd = new JPanel();
        friendListTabButtonPanelEnd.setBounds(300, 330, 200, 100);
        friendListTabButtonPanelEnd.setBackground(Color.black);
        friendListTabButtonPanelEnd.setLayout(new GridLayout(4, 1));

        friendListTabRemoveFriendEnd();
        friendListTabViewFriendEnd();
        friendListTabGreetFriendEnd();
        friendListTabNoneEnd();

        window.add(friendListTabButtonPanelEnd);
        window.setVisible(true);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING REMOVE FRIEND!
    private void friendListTabRemoveFriendEnd() {
        choiceRemoveFriendListTabEnd = new JButton("REMOVE");
        choiceRemoveFriendListTabEnd.setBackground(Color.black);
        choiceRemoveFriendListTabEnd.setForeground(Color.white);
        choiceRemoveFriendListTabEnd.setFont(SERIF_FONT);
        choiceRemoveFriendListTabEnd.setFocusable(false);
        choiceRemoveFriendListTabEnd.addActionListener(this);
        //choice1StartScene.setActionCommand("yes-start");
        friendListTabButtonPanelEnd.add(choiceRemoveFriendListTabEnd);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING VIEW FRIEND!
    private void friendListTabViewFriendEnd() {
        choiceViewFriendListTabEnd = new JButton("VIEW");
        choiceViewFriendListTabEnd.setBackground(Color.black);
        choiceViewFriendListTabEnd.setForeground(Color.white);
        choiceViewFriendListTabEnd.setFont(SERIF_FONT);
        choiceViewFriendListTabEnd.setFocusable(false);
        choiceViewFriendListTabEnd.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelEnd.add(choiceViewFriendListTabEnd);
    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING GREET FRIEND!
    private void friendListTabGreetFriendEnd() {
        choiceGreetFriendListTabEnd = new JButton("GREET");
        choiceGreetFriendListTabEnd.setBackground(Color.black);
        choiceGreetFriendListTabEnd.setForeground(Color.white);
        choiceGreetFriendListTabEnd.setFont(SERIF_FONT);
        choiceGreetFriendListTabEnd.setFocusable(false);
        choiceGreetFriendListTabEnd.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelEnd.add(choiceGreetFriendListTabEnd);

    }

    // MODIFIES : THIS
    // EFFECTS  : BUTTON REPRESETING NONE OF THE OPTIONS!
    private void friendListTabNoneEnd() {
        choiceNoneFriendListTabEnd = new JButton("NONE");
        choiceNoneFriendListTabEnd.setBackground(Color.black);
        choiceNoneFriendListTabEnd.setForeground(Color.white);
        choiceNoneFriendListTabEnd.setFont(SERIF_FONT);
        choiceNoneFriendListTabEnd.setFocusable(false);
        choiceNoneFriendListTabEnd.addActionListener(this);
        //choice2StartScene.setActionCommand("no-start");
        friendListTabButtonPanelEnd.add(choiceNoneFriendListTabEnd);

    }

    // MODIFES : THIS
    // EFFECTS : DECIDES WHAT TO DO WITH THE GIVEN ACTION!
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            titlePanel.setVisible(false);
            startButton.setVisible(false);
            showFriendListStart();
            startScene();

        } else if (e.getSource() == choice1StartScene) {
            startPanel.setVisible(false);
            startSceneButtonPanel.setVisible(false);
            friendListPanelStart.setVisible(false);
            accessFriendListTabButtonStart.setVisible(false);
            showFriendListMeetingTaiga();
            meetingTaigaScene();

        } else if (e.getSource() == choice2StartScene) {
            startPanel.setVisible(false);
            startSceneButtonPanel.setVisible(false);
            accessFriendListTabButtonStart.setVisible(false);
            startSceneNoOption();

        } else if (e.getSource() == choice1MeetingTaiga) {
            meetingTaigaScenePanel.setVisible(false);
            meetingTaigaSceneButtonPanel.setVisible(false);
            friendListPanelMeetingTaiga.setVisible(false);
            accessFriendListTabButtonTaiga.setVisible(false);
            listOfFriend.addFriend(taiga);
            showFriendListTravel();
            travelScene();

        } else if (e.getSource() == choice2MeetingTaiga) {
            meetingTaigaScenePanel.setVisible(false);
            meetingTaigaSceneButtonPanel.setVisible(false);
            friendListPanelMeetingTaiga.setVisible(false);
            accessFriendListTabButtonTaiga.setVisible(false);
            showFriendListTravel();
            travelScene();

        } else if (e.getSource() == choice1TravelScene) {
            travelScene.setVisible(false);
            travelSceneButtonPanel.setVisible(false);
            friendListPanelTravel.setVisible(false);
            accessFriendListTabButtonTravel.setVisible(false);
            showFriendListMeetingBlazer();
            travelNorthScene();

        } else if (e.getSource() == choice2TravelScene) {
            travelScene.setVisible(false);
            travelSceneButtonPanel.setVisible(false);
            friendListPanelTravel.setVisible(false);
            accessFriendListTabButtonTravel.setVisible(false);
            showFriendListMeetingBlazer();
            travelEastScene();

        } else if (e.getSource() == choice3TravelScene) {
            travelScene.setVisible(false);
            travelSceneButtonPanel.setVisible(false);
            friendListPanelTravel.setVisible(false);
            accessFriendListTabButtonTravel.setVisible(false);
            showFriendListMeetingBlazer();
            meetingBlazerScene();

        } else if (e.getSource() == choice4TravelScene) {
            travelScene.setVisible(false);
            travelSceneButtonPanel.setVisible(false);
            friendListPanelTravel.setVisible(false);
            accessFriendListTabButtonTravel.setVisible(false);
            showFriendListMeetingBlazer();
            travelWestScene();

        } else if (e.getSource() == choice1MeetingBlazer) {
            meetingBlazerScenePanel.setVisible(false);
            meetingBlazerSceneButtonPanel.setVisible(false);
            friendListPanelMeetingBlazer.setVisible(false);
            listOfFriend.addFriend(blazer);
            accessFriendListTabButtonBlazer.setVisible(false);
            showFriendListEnd();
            endScene();

        } else if (e.getSource() == choice2MeetingBlazer) {
            meetingBlazerScenePanel.setVisible(false);
            meetingBlazerSceneButtonPanel.setVisible(false);
            accessFriendListTabButtonBlazer.setVisible(false);
            endScene();

        } else if (e.getSource() == accessFriendListTabButtonStart) {
            startPanel.setVisible(false);
            startSceneButtonPanel.setVisible(false);
            friendListPanelStart();
            //System.out.println("pressed");

            // my guess is that it is a visibility issue, but I honestly cannot figure it out!

        } else if (e.getSource() == choiceNoneFriendListTabStart) {
            friendListPanelTabStart.setVisible(false);
            friendListTabButtonPanelStart.setVisible(false);
            startScene();

        } else if (e.getSource() == accessFriendListTabButtonTaiga) {
            meetingTaigaScenePanel.setVisible(false);
            meetingTaigaSceneButtonPanel.setVisible(false);
            friendListPanelTaiga();

        } else if (e.getSource() == choiceNoneFriendListTabTaiga) {
            friendListPanelTabMeetingTaiga.setVisible(false);
            friendListTabButtonPanelTaiga.setVisible(false);
            meetingTaigaScene();

        } else if (e.getSource() == accessFriendListTabButtonTravel) {
            travelScene.setVisible(false);
            travelSceneButtonPanel.setVisible(false);
            friendListPanelTravel();

        } else if (e.getSource() == choiceNoneFriendListTabTravel) {
            friendListPanelTabTravel.setVisible(false);
            friendListTabButtonPanelTravel.setVisible(false);
            travelScene();

        } else if (e.getSource() == accessFriendListTabButtonBlazer) {
            meetingBlazerScenePanel.setVisible(false);
            meetingBlazerSceneButtonPanel.setVisible(false);
            friendListPanelBlazer();

        } else if (e.getSource() == choiceNoneFriendListTabBlazer) {
            friendListPanelTabMeetingBlazer.setVisible(false);
            friendListTabButtonPanelBlazer.setVisible(false);
            meetingBlazerScene();

        } else if (e.getSource() == accessFriendListTabButtonEnd) {
            endScene.setVisible(false);
            friendListPanelEnd();

        } else if (e.getSource() == choiceNoneFriendListTabEnd) {
            friendListPanelTabEnd.setVisible(false);
            friendListTabButtonPanelEnd.setVisible(false);
            endScene();

        } else if (e.getSource() == choiceRemoveFriendListTabStart) {
            friendListPanelTabStart.setVisible(false);
            friendListTabButtonPanelStart.setVisible(false);
            friendListTabRemoveFriendFieldStart();

        } else if (e.getSource() == choiceRemoveFriendListSetStart) {
            choiceRemoveFriendListButtonPanelSetStart.setVisible(false);
            choiceRemoveFriendListPanelSetStart.setVisible(false);
            listOfFriend.removeFriend(choiceRemoveFriendListFieldStart.getText().toLowerCase());
            friendListPanelTabStart.setVisible(true);
            friendListButtonPanelStart();

        } else if (e.getSource() == choiceRemoveFriendListTabTaiga) {
            friendListPanelTabMeetingTaiga.setVisible(false);
            friendListTabButtonPanelTaiga.setVisible(false);
            friendListTabRemoveFriendFieldTaiga();

        } else if (e.getSource() == choiceRemoveFriendListSetTaiga) {
            choiceRemoveFriendListPanelSetTaiga.setVisible(false);
            choiceRemoveFriendListButtonPanelSetTaiga.setVisible(false);
            listOfFriend.removeFriend(choiceRemoveFriendListFieldTaiga.getText().toLowerCase());
            friendListPanelTabMeetingTaiga.setVisible(true);
            friendListButtonPanelTaiga();

        } else if (e.getSource() == choiceRemoveFriendListTabTravel) {
            friendListPanelTabTravel.setVisible(false);
            friendListTabButtonPanelTravel.setVisible(false);
            friendListTabRemoveFriendFieldTravel();

        } else if (e.getSource() == choiceRemoveFriendListSetTravel) {
            choiceRemoveFriendListPanelSetTravel.setVisible(false);
            choiceRemoveFriendListButtonPanelSetTravel.setVisible(false);
            listOfFriend.removeFriend(choiceRemoveFriendListFieldTravel.getText().toLowerCase());
            friendListPanelTabTravel.setVisible(true);
            friendListButtonPanelTravel();

        } else if (e.getSource() == saveButton) {

            try {
                jsonWriter.open();
                jsonWriter.write(listOfFriend);
                jsonWriter.close();

            } catch (FileNotFoundException f) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }

        } else if (e.getSource() == loadButton) {

            try {
                listOfFriend = jsonReader.read();

            } catch (IOException f) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        } else if (e.getSource() == printButton) {
            LogPrinter lp = new LogPrinter();

            lp.printLog(EventLog.getInstance());
        }


    }
}
