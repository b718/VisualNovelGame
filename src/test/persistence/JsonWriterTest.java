package persistence;

import model.FriendList;
import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;


import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            FriendList friendList = new FriendList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
            e.printStackTrace();
            System.out.println("We caught it! Test passed!");
        }
    }


    @Test
    public void testWriterEmptyFriendList() {
        try {
            FriendList friendList = new FriendList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFriendList.json");
            writer.open();
            writer.write(friendList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFriendList.json");
            friendList = reader.read();
            assertEquals("You currently do not have any friends!", friendList.returnFriendList());
            assertEquals(0, friendList.returnNumberOfFriends());
            /*assertTrue(friendList.returnNumberOfFriends() == 0);
            assertFalse(friendList.returnNumberOfFriends() != 0);*/
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    public void testWriterFriendListTwoFriends() {
        try {
            FriendList friendList = new FriendList();
            friendList.addFriend(new Taiga("Taiga", 19));
            friendList.addFriend(new Blazer("Blazer", 192));
            JsonWriter writer = new JsonWriter("./data/testWriterFriendListTwoFriends.json");
            writer.open();
            writer.write(friendList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFriendListTwoFriends.json");
            friendList = reader.read();
            //assertEquals("My work room", wr.getName());
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(2, listFriend.size());
            assertEquals("Taiga", listFriend.get(0).getName());
            assertEquals("Blazer", listFriend.get(1).getName());
            assertEquals("Taiga: I am a turbulent friend who comes from ashes.",
                    listFriend.get(0).descriptionFriend());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(1).descriptionFriend());

            assertEquals(19, listFriend.get(0).getAge());
            assertEquals(192, listFriend.get(1).getAge());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterFriendListTwoFriendsOppositeOrder() {
        try {
            FriendList friendList = new FriendList();
            friendList.addFriend(new Blazer("Blazer", 192));
            friendList.addFriend(new Taiga("Taiga", 19));
            JsonWriter writer = new JsonWriter("./data/testWriterFriendListTwoFriendsOppositeOrder.json");
            writer.open();
            writer.write(friendList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFriendListTwoFriendsOppositeOrder.json");
            friendList = reader.read();
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(2, listFriend.size());
            assertEquals("Blazer", listFriend.get(0).getName());
            assertEquals("Taiga", listFriend.get(1).getName());
            assertEquals("Taiga: I am a turbulent friend who comes from ashes.",
                    listFriend.get(1).descriptionFriend());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(0).descriptionFriend());

            assertEquals(19, listFriend.get(1).getAge());
            assertEquals(192, listFriend.get(0).getAge());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterFriendListOneFriend() {
        try {
            FriendList friendList = new FriendList();
            friendList.addFriend(new Blazer("Blazer", 19142));
            JsonWriter writer = new JsonWriter("./data/testWriterFriendListOneFriend.json");
            writer.open();
            writer.write(friendList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFriendListOneFriend.json");
            friendList = reader.read();
            List<Friends> listFriend = friendList.getFriendList();
            assertEquals(1, listFriend.size());
            assertEquals("Blazer", listFriend.get(0).getName());
            assertEquals("Blazer : My body is made of fire!",
                    listFriend.get(0).descriptionFriend());
            assertEquals(19142, listFriend.get(0).getAge());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
