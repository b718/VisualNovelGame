package model.mainfriends;

import org.json.JSONObject;

public class Blazer extends Friends {


    // One of the main characters in the game that is the Sub for Friends!

    public Blazer(String name, int age) {
        super(name, age);
    }

    @Override
    public String descriptionFriend() {
        return "Blazer : My body is made of fire!";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        return json;
    }
}
