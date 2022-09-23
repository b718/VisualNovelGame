package model.mainfriends;

import model.mainfriends.Friends;
import org.json.JSONObject;

public class Taiga extends Friends {

    // One of the main characters in the game that is the Sub for Friends!

    public Taiga(String name, int age) {
        super(name, age);
    }

    @Override
    public String descriptionFriend() {
        return "Taiga: I am a turbulent friend who comes from ashes.";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        return json;
    }

    // HOW CAN WE MAKE THE NAME OF THIS PERSON SPECIFIC?

    // CUZ HERE WE HAVE THE CONSTRUCTOR CONSTRUCTING AND STUFF BUT WE WANT IT TO BE VERY SPECIFIC
    // WITH THE NAME AND SUCH!

}
