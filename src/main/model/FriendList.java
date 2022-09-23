package model;

import model.events.Event;
import model.events.EventLog;
import model.mainfriends.Friends;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FriendList implements Writable {

    // WE CAN ONLY HAVE 5 FRIENDS
    // WE WANT TO ABLE TO REMOVE A SPECIFIC PERSON
    // WE ALSO WANT TO BE ABLE TO VIEW THE SIZE OF OUR LIST
    // AND ALSO VIEW WHO IS IN THIS LIST
    // WE ALSO WANT TO BE ABLE TO ADD PEOPLE TO THIS LIST

    // This represents how we will hold ours Friends and how we run methods on our Friends like greeting, removing,
    // and seeing their Talents!

    private static final int MAX_NUMBER_OF_FRIENDS = 5;
    private List<Friends> listFriends;

    // EFFECTS : CREATES A LIST OF FRIENDS
    public FriendList() {
        listFriends = new ArrayList<Friends>();
    }

    // MODIFIES : THIS
    // EFFECTS : ADDS A FRIEND TO OUR LIST OF FRIENDS
    public void addFriend(Friends f) {
        if (listFriends.size() < MAX_NUMBER_OF_FRIENDS) {
            listFriends.add(f);
            EventLog.getInstance().logEvent(new Event("Added Friend: " + f.getName()));

        }

    }

    // EFFECTS : RETURNS YOUR CURRENT LIST OF FRIENDS (String name)!
    // TRY TO MAKE THIS THE CURRENT LIST WITH AGE!
    // THE ORIGINAL RETURN TYPE BEFORE THIS FriendList!
    public String returnFriendList() {
        ArrayList<String> currentFriends = new ArrayList<>();

        for (Friends f : listFriends) {
            currentFriends.add(f.getName());
        }

        if (currentFriends.size() == 0) {
            return "You currently do not have any friends!";
        } else {
            return "Your friends are: " + currentFriends + "!";
        }
    }

    // EFFECTS : RETURN THE NUMBER OF FRIENDS YOU HAVE!
    public int returnNumberOfFriends() {
        return listFriends.size();
    }

    // REQUIRES : THIS FRIEND MUST BE IN THE LIST!
    // MODIFIES : THIS
    // EFFECTS  : REMOVES THIS FRIEND FROM YOUR LIST OF FRIENDS; TAKES THEIR NAME!
    public void removeFriend(String s) {
        ArrayList<Friends> badFriends = new ArrayList<>();

        for (Friends f : listFriends) {
            if (f.getName().toLowerCase().equals(s.toLowerCase())) {
                badFriends.add(f);
            }
        }

        for (Friends i : badFriends) {
            listFriends.remove(i);
            EventLog.getInstance().logEvent(new Event("Removed Friend: " + i.getName()));
        }

    }

    // REQUIRES : THE FRIEND MUST BE IN THE LIST!
    // EFFECTS : RETURNS THAT WE HAVE GREETED THIS FRIEND!
    public String talkedToFriend(String s) {
        for (Friends i : listFriends) {
            if (i.getName().toLowerCase().equals(s.toLowerCase())) {
                i.addGreetings();
                return "You have greeted " + s + "!";
            }
        }
        return "This friend does not exist!";
    }

    // REQUIRES : THIS FRIEND MUST BE IN THE LIST!
    // EFFECTS  : RETURNS THE NUMBER OF TIMES WE HAVE GREETED THIS FRIEND!
    public String talkedToFriendAmount(String s) {
        for (Friends i : listFriends) {
            if (i.getName().toLowerCase().equals(s.toLowerCase())) {
                return "You have greeted " + s + " " + i.getGreetings() + " times!";
            }
        }
        return "This friend does not exist!";

    }


    // There might be an issue with the method! THE RETURN TYPE WAS ORIGINALLY INT BUT
    // I CHANGED IT TO STRING BECAUSE THE CASE WHERE THE LOOPS END DOESN'T MAKE SENSE

    // IF THE REQUIREMENTS TAG REQUIRES THAT WE HAVE SOMETHING IN THE LIST, DOES THE RETURN
    // FORM OUTSIDE THE LOOP MATTER?

    // THIS NOW TAKES A STRING INSTEAD!


    // REQUIRES : THAT THIS FRIEND IS IN THE LIST?
    // EFFECTS : RETURNS THE FRIENDS UNIQUE DESCRIPTION
    public String friendDescription(String s) {
        for (Friends i : listFriends) {
            if (i.getName().toLowerCase().equals(s.toLowerCase())) {
                return i.descriptionFriend();
            }
        }
        return "This friend does not exist!";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("friendlist", friendListToJson());
        return json;
    }

    // EFFECTS : RETURN THINGS IN THIS FRIENDLIST AS A JSON ARRAY!
    private JSONArray friendListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Friends f : listFriends) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: RETURNS CURRENT FRIENDLIST!
    public List<Friends> getFriendList() {
        return listFriends;
    }

    // WE NEED TO IMPLEMENT A METHOD THAT SAYS THE FRIENDS DESCRIPTION!

    // WE ALSO NEED A METHOD THAT DOES NOT LET US - EXCEED FRIEND SIZE!



}
