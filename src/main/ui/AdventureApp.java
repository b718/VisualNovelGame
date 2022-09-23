package ui;

import model.FriendList;
import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventureApp {

    // This is the console UI for our game and how we will interact with our characters!

    // WE NEED TO THINK ABOUT HOW WE WILL SAVE THE GAME!
    // WE NEED TO ADD A LOAD OPTION NOW!
    // AND A SAVE OPTION!

    private static final String JSON_STORE = "./data/adventureApp.json";
    private Friends taiga;
    private Friends blazer;
    private FriendList listOfFriend;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean playGame = true;
    private ArrayList<String> gameState;


    // EFFECTS : RUNS THE ADVENTURE APP!
    public AdventureApp() {
        runApp();

    }

    // MODIFIES : THIS
    // EFFECTS : WE PROCESS THE INPUT FROM THE CONSOLE!
    private void runApp() {
        //boolean playGame = true;
        String commandInput = "";
        instantiate();

        while (playGame) {
            starterMenu();
            commandInput = input.next();
            commandInput = commandInput.toLowerCase();

            if (commandInput.equals("no")) {
                /*System.out.println("*THE GAME WILL NOW END!*");
                playGame = false;*/
                fastLoaderMenu();


            } else {
                takeCommandInput(commandInput);
            }
        }

        //System.out.println("*YOU LEFT THE PREMISE HOVERING IN FEAR!*");

        // =======
        /*
        if (commandInput.equals("no")) {
            System.out.println("*YOU LEFT THE PREMISE HOVERING IN FEAR!*");
        } else {
            takeCommandInput(commandInput);
        }
         */
        // =======

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
        gameState = new ArrayList<>();
        input.useDelimiter("\n");
    }


    // EFFECTS : DISPLAY THE START MENU!
    private void starterMenu() {
        System.out.println("");
        System.out.println("=====");
        System.out.println("Would you like to load the file?");
        System.out.println("=====");
        System.out.println("\nYes");
        System.out.println("\nNo");
        System.out.println("=====");


    }

    // EFFECTS : TAKES YOU TO WHERE YOU WERE LAST IN THE STORY!
    private void fastLoaderMenu() {
        String choice = "";
        fastLoadMenuDisplayer();
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("startmenu")) {
            starterMenu();
        } else if (choice.equals("meetingtaiga")) {
            meetingTaigaScene();
        } else if (choice.equals("travelscene")) {
            travelScene();
        } else if (choice.equals("endscene")) {
            endScene();
        } else {
            System.out.println("We will now go to the StartMenu!");
            startMenu();
        }

    }

    // EFFECTS : DISPLAYS THE OPTIONS FOR WHERE WE TRAVEL!
    private void fastLoadMenuDisplayer() {
        System.out.println("");
        System.out.println("=====");
        System.out.println("Where Were You Last In The Story?");
        System.out.println("=====");
        System.out.println("\nStartMenu");
        System.out.println("\nMeetingTaiga");
        System.out.println("\nTravelScene");
        System.out.println("\nEndScene");
        System.out.println("\nNone");
        System.out.println("=====");

    }

    // MODIFIES : THIS
    // EFFECTS : TAKES IN THE USER COMMAND AND PROCESS IT!
    private void takeCommandInput(String s) {
        if (s.equals("yes")) {
            loadFriendListAndState();
            fastLoaderMenu();
            //meetingTaigaScene();
        }
    }

    // EFFECTS : DISPLAYS A GAME START MENU!
    private void startMenu() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println("Welcome to the Desert, where life is anything but fun.");
        System.out.println("Would you care to engage in this world?");
        System.out.println("=====");
        System.out.println("\nYes");
        System.out.println("\nNo");
        System.out.println("=====");

        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("yes")) {
            meetingTaigaScene();

        } else {
            System.out.println("=====");
            System.out.println("*YOU LEFT THE PREMISE HOVERING IN FEAR!*");
            System.out.println("=====");
            playGame = false;


        }

    }


    // MODIFIES : THIS
    // EFFECTS  : WE ARE MEETING TAIGA THE FIRST CHARACTER AND DECIDE IF WE ADD HER OR NOT!
    private void meetingTaigaScene() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println("As you walk around, you see a short yet interesting girl.");
        System.out.println("As you approach her, she asks if you want to be friends with her.");
        System.out.println("=====");
        System.out.println("\nYes");
        System.out.println("\nNo");
        System.out.println("=====");

        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("yes")) {
            listOfFriend.addFriend(taiga);
            //System.out.println(listOfFriend.returnFriendList());
            //System.out.println(taiga.descriptionFriend());
            accessListOfFriends();
            saveAndLoadMenu();
            travelScene();
            // WHY DOES CALLING THE METHOD NOT RETURN THE STRING BUT PUTTING SOUT WILL RETURN THE STRING!

        } else {
            System.out.println("=====");
            System.out.println("You avoided her and moved somewhere else!");
            System.out.println("=====");
            //System.out.println(listOfFriend.returnFriendList());
            //endScene();
            travelScene();

        }

    }

    // EFFECTS : THIS IS WHERE WE DECIDE WHERE TO TRAVEL!
    private void travelScene() {
        String choice = "";
        System.out.println("");
        // WE CAN TRAVEL N E S W!
        travelSceneHelper();
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("north")) {
            System.out.println("Traveling North led to the cold tundra where the bitter cold ended us!");
            System.out.println("YOUR FRIENDS ARE: " + listOfFriend.returnFriendList());
            playGame = false;
        } else if (choice.equals("east")) {
            System.out.println("Traveling East led to a happy farm life where everyday remains the same!");
            System.out.println("YOUR FRIENDS ARE: " + listOfFriend.returnFriendList());
            playGame = false;

        } else if (choice.equals("south")) {
            meetingBlazerScene();

        } else if (choice.equals("west")) {
            System.out.println("Traveling West led you to a body of water where you drowned.");
            System.out.println("YOUR FRIENDS ARE: " + listOfFriend.returnFriendList());
            playGame = false;
        }

        // NOW WE NEED TO CODE WHAT HAPPENS AT THESE PLACES!

    }

    // EFFECTS : A HELPER THAT PASTES THE OPTIONS FOR WHERE TO TRAVEL IN [travelScene]
    private void travelSceneHelper() {
        System.out.println("=====");
        System.out.println("You have decided to travel in the search of society!");
        System.out.println("However, we can only choose 1 direction!");
        System.out.println("*CHOOSE WISELY*");
        System.out.println("=====");
        System.out.println("\nNorth");
        System.out.println("\nEast");
        System.out.println("\nSouth");
        System.out.println("\nWest");
        System.out.println("=====");
    }

    // MODIFIES : THIS, BLAZER
    // EFFECTS : WE MEET BLAZER AND DECIDE IF WE ADD HIM OR NOT
    private void meetingBlazerScene() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println("As you walk South, you see a man engulfed by flame.");
        System.out.println("As you approach him to help him, he painlessly greets you.");
        System.out.println("Blazer: I'm Blazer, can I join you?");
        System.out.println("=====");
        System.out.println("\nYes");
        System.out.println("\nNo");
        System.out.println("=====");

        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("yes")) {
            listOfFriend.addFriend(blazer);
            //System.out.println(listOfFriend.returnFriendList());
            //System.out.println(taiga.descriptionFriend());
            accessListOfFriends();
            saveAndLoadMenu();
            endScene();
            // WHY DOES CALLING THE METHOD NOT RETURN THE STRING BUT PUTTING SOUT WILL RETURN THE STRING!

        } else {
            System.out.println("=====");
            System.out.println("You avoided him and moved somewhere else!");
            System.out.println("=====");
            //System.out.println(listOfFriend.returnFriendList());
            //endScene();
            endScene();

        }


    }

    // EFFECTS : SHOW YOU WHAT YOU CAN DO ABOUT YOUR CURRENT LIST OF FRIENDS!
    private void accessListOfFriends() {
        String choice = "";
        accessListOfFriendsHelper();
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("remove")) {
            listRemoveFriend();
            accessListOfFriends();

        } else if (choice.equals("view")) {

            listViewFriend();
            accessListOfFriends();
        } else if (choice.equals("greet")) {

            listGreetFriend();

        } else if (choice.equals("none")) {

            System.out.println("Lets get back to the game!");
        }

        // CAN WE SUPPRESS CHECK STYLE HERE?

    }

    // EFFECTS : TO PASTE THE OPTIONS FOR [accessListOfFriends]!
    private void accessListOfFriendsHelper() {
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("Would you like to remove a friend, or view their description?");
        System.out.println("=====");
        System.out.println("\nRemove");
        System.out.println("\nView");
        System.out.println("\nGreet");
        System.out.println("\nNone");
        System.out.println("=====");

    }


    // MODIFIES : THIS
    // REMOVES : A FRIEND FROM THE LIST!
    private void listRemoveFriend() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("=====");
        System.out.println("\nEnters your friends name!");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        listOfFriend.removeFriend(choice);
        System.out.println(listOfFriend.returnFriendList());
        System.out.println("=====");
    }


    // EFFECTS : VIEWS A FRIENDS DESCRIPTION!
    private void listViewFriend() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("=====");
        System.out.println("\nEnters your friends name!");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        System.out.println(listOfFriend.friendDescription(choice));
        System.out.println("=====");

    }

    // MODIFIES : FRIEND
    // EFFECTS : GREETS A FRIEND ONCE!
    private void listGreetFriend() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("=====");
        System.out.println("Would you like to greet a friend or see how many greetings exist for a friend?");
        System.out.println("\nSee");
        System.out.println("\nGreet");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("see")) {
            listGreetedFriendAmount();
            accessListOfFriends();
        } else if (choice.equals("greet")) {
            listGreetFriendAdd();
            accessListOfFriends();
        }

    }

    // EFFECTS : PRINTS THE OUT OF TIMES WE HAVE GREETED A FRIEND
    private void listGreetedFriendAmount() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("=====");
        System.out.println("\nEnters your friends name!");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        System.out.println(listOfFriend.talkedToFriendAmount(choice));
        System.out.println("=====");

    }

    private void listGreetFriendAdd() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println(listOfFriend.returnFriendList());
        determineFriend();
        System.out.println("=====");
        System.out.println("\nEnters your friends name!");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        System.out.println(listOfFriend.talkedToFriend(choice));
        System.out.println("=====");
    }

    // EFFECTS : PRINTS OUT THE ENDING SCENE FOR ACT 1!
    private void endScene() {
        System.out.println("=====");
        System.out.println("As you leave the premise with Taiga and Blazer, you discover a corrupt object.");
        System.out.println("As you approach the object with your group, reality begins to shatter!");
        System.out.println("COME BACK FOR ACT 2!!");
        System.out.println("YOUR FRIENDS ARE: " + listOfFriend.returnFriendList());
        System.out.println("=====");
        playGame = false;
    }


    // EFFECTS : DETERMINES WHAT TO PRINT BASED ON THE NUMBER OF FRIENDS!
    private void determineFriend() {
        if (listOfFriend.returnNumberOfFriends() == 0) {
            System.out.println("You currently have " + listOfFriend.returnNumberOfFriends() + " Friends!");
        } else if (listOfFriend.returnNumberOfFriends() == 1) {
            System.out.println("You currently have " + listOfFriend.returnNumberOfFriends() + " Friend!");
        } else {
            System.out.println("You currently have " + listOfFriend.returnNumberOfFriends() + " Friends!");
        }
    }



    // EFFECTS : DISPLAYS OPTIONS FOR US TO SAVE AND LOAD THE GAME!
    private void saveAndLoadMenu() {
        String choice = "";
        System.out.println("");
        System.out.println("=====");
        System.out.println("Would you like to SAVE the file?");
        System.out.println("=====");
        System.out.println("\nYes");
        System.out.println("\nNo");
        System.out.println("=====");
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("yes")) {
            saveFriendListAndState();
        }

    }

    // EFFECTS: saves the current FriendList to file
    private void saveFriendListAndState() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfFriend);
            jsonWriter.close();
            System.out.println("Saved " + listOfFriend.returnFriendList() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the FriendList from file and game state!
    private void loadFriendListAndState() {
        try {
            listOfFriend = jsonReader.read();
            System.out.println("Loaded " + listOfFriend.returnFriendList() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



    //======

    // THINK ABOUT HOW WE MIGHT NEED HAVE LEVELS WHERE WE MEET THIS PEOPLE!
    // SO WE MIGHT ACTUALLY NEED AN INTERFACE WHICH GIVES THE LEVELS THEIR BASIC FUNCTIONS
    // BUT CERTAIN THINGS WE HAVE TO CHANGE LIKE WHO THEY MEET

    // MAYBE HAVE AN INTERFACE FOR WEAPONS?
    // THE IMPLEMENTATION SHOULD BE SIMILAR TO THE FRIENDS SYSTEM WE HAVE!

    //======


    //======

    // SCENES FOR THIS ACT 1
    // START
    // MEETING TAIGA
    // TELLS YOU ABOUT A VILLAGE
    // TRAVELLING SECTION
    // (POSSIBLY ADDING CERTAIN DIALOGUE THAT MAKES YOU 2 CLOSER?)!
    // MEETING BLAZER
    // TRAVELLING
    // END SCENE

    //======
}
