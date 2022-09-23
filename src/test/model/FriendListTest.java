package model;

import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriendListTest {

    FriendList listOfFriends;

    @BeforeEach
    public void setup() {
        listOfFriends = new FriendList();

    }

    @Test
    public void testAddFriend() {
        Friends clara = new Taiga("Clara", 21);
        listOfFriends.addFriend(clara);
        assertEquals(1, listOfFriends.returnNumberOfFriends());
        
    }

    @Test
    public void testTalkedToFriend() {
        Friends ben = new Taiga("Ben", 261);
        listOfFriends.addFriend(ben);
        ben.addGreetings();
        ben.addGreetings();
        ben.addGreetings();
        ben.addGreetings();
        ben.addGreetings();
        // WE GREETED HIM 5 TIMES!

        assertEquals("You have greeted Ben!", listOfFriends.talkedToFriend("Ben"));
        assertEquals("You have greeted Ben 6 times!", listOfFriends.talkedToFriendAmount("Ben"));

    }

    @Test
    public void testRemoveFriend() {
        Friends ben = new Taiga("Ben", 213);
        Friends yone = new Taiga("Yone", 123);
        Friends loj = new Taiga("Loj", 1616);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(yone);
        listOfFriends.addFriend(loj);

        listOfFriends.removeFriend("yone");
        assertEquals(2, listOfFriends.returnNumberOfFriends());


    }

    @Test
    public void testReturnFriendList() {
        Friends ben = new Taiga("Ben", 213);
        Friends yone = new Taiga("Yone", 123);
        Friends loj = new Taiga("Loj", 1616);
        Friends hja = new Taiga("Hja", 213);
        Friends aba = new Taiga("Aba", 123);
        Friends asga = new Taiga("Asga", 1616);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(yone);
        listOfFriends.addFriend(loj);
        listOfFriends.addFriend(hja);
        listOfFriends.addFriend(aba);
        listOfFriends.addFriend(asga);

        assertEquals(5, listOfFriends.returnNumberOfFriends());
        assertEquals("Your friends are: [Ben, Yone, Loj, Hja, Aba]!", listOfFriends.returnFriendList());

        // WORK ON THIS TEST, LEARN HOW TO EXTRACT THE STRING FROM EACH INDEX OF THE ARRAYLIST!
    }

    @Test
    public void testAddFriendMax() {
        Friends ben = new Taiga("Ben", 213);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(ben);
        listOfFriends.addFriend(ben);

        assertEquals(5, listOfFriends.returnNumberOfFriends());

        listOfFriends.addFriend(ben);
        assertEquals(5, listOfFriends.returnNumberOfFriends());

    }

    @Test
    public void testTalkedToFriendNoGreetings() {
        Friends ben = new Taiga("Ben", 12312);
        listOfFriends.addFriend(ben);
        assertEquals("You have greeted Ben 0 times!", listOfFriends.talkedToFriendAmount("Ben"));


    }

    @Test
    public void testReturnFriendListEmpty() {
        assertEquals(0, listOfFriends.returnNumberOfFriends());
        assertEquals("You currently do not have any friends!", listOfFriends.returnFriendList());

    }

    @Test
    public void testRemoveFriendTwoFriends() {
        Friends blazer = new Blazer("Blazer", 15151);
        Friends taiga = new Taiga("Taiga", 131241);
        listOfFriends.addFriend(blazer);
        listOfFriends.addFriend(taiga);

        listOfFriends.removeFriend("blazer");
        assertEquals(1, listOfFriends.returnNumberOfFriends());
        assertEquals("", listOfFriends.returnFriendList());

    }

    @Test
    public void testGreetTwoFriends() {
        Friends blazer = new Blazer("Blazer", 5116);
        Friends taiga = new Taiga("Taiga", 612621);
        listOfFriends.addFriend(blazer);
        listOfFriends.addFriend(taiga);

        assertEquals("You have greeted Blazer!", listOfFriends.talkedToFriend("Blazer"));
        assertEquals("You have greeted Taiga!", listOfFriends.talkedToFriend("Taiga"));
        assertEquals("You have greeted Blazer!", listOfFriends.talkedToFriend("Blazer"));
        assertEquals("You have greeted Blazer 2 times!", listOfFriends.talkedToFriendAmount("Blazer"));
        assertEquals("You have greeted Taiga 1 times!", listOfFriends.talkedToFriendAmount("Taiga"));
        assertEquals("", listOfFriends.talkedToFriend("Tom"));

    }

    @Test
    public void testFriendDescription() {
        Friends blazer = new Blazer("Blazer", 5116);
        Friends taiga = new Taiga("Taiga", 612621);
        listOfFriends.addFriend(blazer);
        listOfFriends.addFriend(taiga);

        assertEquals("Taiga: I am a turbulent friend who comes from ashes.",
                listOfFriends.friendDescription("Taiga"));

        assertEquals("Blazer : My body is made of fire!", listOfFriends.friendDescription("Blazer"));

        assertEquals("", listOfFriends.friendDescription("Yimit"));


    }

    @Test
    public void testTalkedToFriendAmount() {
        Friends blazer = new Blazer("Blazer", 12521);
        Friends taiga = new Taiga("Taiga", 1262161);
        listOfFriends.addFriend(blazer);
        listOfFriends.addFriend(taiga);
        blazer.addGreetings();
        blazer.addGreetings();
        taiga.addGreetings();

        assertEquals("You have greeted Blazer 2 times!", listOfFriends.talkedToFriendAmount("Blazer"));
        assertEquals("You have greeted Taiga 1 times!", listOfFriends.talkedToFriendAmount("Taiga"));
        assertEquals("", listOfFriends.talkedToFriendAmount("Tom"));

    }


}
