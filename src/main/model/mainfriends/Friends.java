package model.mainfriends;

import persistence.Writable;

public abstract class Friends implements Writable {
    // THIS NEEDS TO BE ABSTRACT BECAUSE WE ARE MAKING A GAME WITH SET CHARACTERS!

    // This represents the Abstract Class that the main characters in the game branch off of!

    protected String name;
    protected int age;
    protected int greetingsForTheDay;

    // EFFECTS : CREATES A FRIEND THAT HAS THEIR AGE AND NAME
    public Friends(String name, int age) {
        this.name = name;
        this.age = age;
        greetingsForTheDay = 0;

    }

    // EFFECTS : RETURNS THE AGE OF THE FRIEND
    public int getAge() {
        return age;
    }

    // EFFECTS : RETURNS THE NAME OF THE FRIEND
    public String getName() {
        return name;
    }

    // MODIFIES : THIS
    // EFFECTS : ADDS 1 FOR EVERYTIME A PERSON IS GREETED!
    public void addGreetings() {
        greetingsForTheDay++;

    }

    // EFFECTS : RETURN THE NUMBER OF GREETINGS FOR THE DAY
    public int getGreetings() {
        return greetingsForTheDay;
    }
    // WE NEED TO DECIDE IF WE WANT TO MAKE THIS AN ABSTRACT CLASS AND THEN HAVE
    // SPECIFIC CHARACTERS THAT DO COOL STUFF!


    // EFFECTS : EVERY CHARACTER WE MEET HAS A SPECIAL DESCRIPTION ABILITY!
    public abstract String descriptionFriend();





    // EFFECTS : RETURNS THE CHARACTER SPECIAL ABILITY
    // public String returnDescriptionFriend() {}

    // WE MIGHT NEED THIS METHOD INCASE WE WANT TO RETURN THE DESCRIPTION OF OUR FRIEND!

}
