package model;

import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendsTest {

    @Test
    public void testGetAge() {
        Friends blake = new Taiga("Blakey", 17);
        assertEquals(17, blake.getAge());
    }

    @Test
    public void testGetName() {
        Friends carol = new Taiga("Carol", 16);
        assertEquals("Carol", carol.getName());
    }

    @Test
    public void testGreetings() {
        Friends carlos = new Taiga("Carlos", 20);
        carlos.addGreetings();
        carlos.addGreetings();
        assertEquals(2, carlos.getGreetings());

    }

    @Test
    public void testDescriptionTaiga() {
        Friends taiga = new Taiga("Cr", 12321);
        assertEquals("", taiga.descriptionFriend());
    }

    @Test
    public void testDescriptionBlazer() {
        Friends blazer = new Blazer("Cr", 1251);
        assertEquals("", blazer.descriptionFriend());
    }

}