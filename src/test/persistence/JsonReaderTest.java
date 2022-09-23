package persistence;

import model.FriendList;
import model.mainfriends.Friends;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        try {
            JsonReader reader = new JsonReader("./data/notExistent.json");
            FriendList friendList = reader.read();
            fail("Since this file does not exist, it should be caught by the catch!!");
        } catch (IOException e) {
            //pass
            System.out.println("Test passed, we caught it!");
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyFriendList() {
        try {
            JsonReader reader = new JsonReader("./data/testEmptyFriendList.json");
            FriendList friendList = reader.read();
            assertEquals("You currently do not have any friends!", friendList.returnFriendList());
            assertEquals(0, friendList.returnNumberOfFriends());
           /* assertTrue(friendList.returnNumberOfFriends() == 0);
            assertFalse(friendList.returnNumberOfFriends() != 0);*/

        } catch (IOException e) {
            fail("File does not exist!");
        }
    }

    @Test
    public void testReaderFriendListTwoFriends() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderFriendListTwoFriends.json");
            FriendList friendList = reader.read();
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(2, listFriend.size());
            assertEquals("Taiga", listFriend.get(0).getName());
            assertEquals("Blazer", listFriend.get(1).getName());
            assertEquals("Taiga: I am a turbulent friend who comes from ashes.",
                    listFriend.get(0).descriptionFriend());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(1).descriptionFriend());
            assertEquals("This friend does not exist!", friendList.friendDescription("Tom"));
            assertEquals(192, listFriend.get(1).getAge());
            assertEquals(19,listFriend.get(0).getAge());

        } catch (IOException e) {
            fail("File does not exist!");
        }
    }

    @Test
    public void testReaderFriendListOneFriend() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderFriendListOneFriend.json");
            FriendList friendList = reader.read();
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(1, listFriend.size());
            assertEquals("Blazer", listFriend.get(0).getName());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(0).descriptionFriend());
            assertEquals("This friend does not exist!", friendList.friendDescription("Tom"));
            assertEquals(19142, listFriend.get(0).getAge());


        } catch (IOException e) {
            fail("File does not exist!");
        }
    }

    @Test
    public void testReaderFriendListTwoFriendsOppositeOrder() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderFriendListTwoFriendsOppositeOrder.json");
            FriendList friendList = reader.read();
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(2, listFriend.size());
            assertEquals("Blazer", listFriend.get(0).getName());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(0).descriptionFriend());
            assertEquals("Taiga", listFriend.get(1).getName());
            assertEquals("Taiga: I am a turbulent friend who comes from ashes.",
                    listFriend.get(1).descriptionFriend());
            assertEquals("This friend does not exist!", friendList.friendDescription("Tom"));
            assertEquals(192, listFriend.get(0).getAge());
            assertEquals(19,listFriend.get(1).getAge());


        } catch (IOException e) {
            fail("File does not exist!");
        }



    }




}
