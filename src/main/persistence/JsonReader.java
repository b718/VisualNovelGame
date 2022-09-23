package persistence;

import model.FriendList;
import model.mainfriends.Blazer;
import model.mainfriends.Friends;
import model.mainfriends.Taiga;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FriendList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FriendList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFriendList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FriendList from JSON object and returns it
    private FriendList parseFriendList(JSONObject jsonObject) {
        FriendList fl = new FriendList();
        addFriends(fl, jsonObject);
        return fl;
    }

    // MODIFIES: FriendList!
    // EFFECTS: parses Friends from JSON object and adds them to FriendList
    private void addFriends(FriendList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("friendlist");
        for (Object json : jsonArray) {
            JSONObject nextFriend = (JSONObject) json;
            addFriend(fl, nextFriend);
        }
    }

    // MODIFIES: FriendList!
    // EFFECTS: parses Friend from JSON object and adds it to FriendList
    private void addFriend(FriendList fl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        //Friends f = new Taiga(name, age);

        if (jsonObject.getString("name").equals("Taiga")) {
            Friends t = new Taiga(name, age);
            fl.addFriend(t);
        } else {
            Friends b = new Blazer(name, age);
            fl.addFriend(b);
        }

    }
}
