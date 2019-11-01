public interface NonPlayable {

    // each NPC should have a speak variable which it uses to communicate with the player

    // return the dialogue of NPC in String format
    String getSpeak();

    // modify the dialogue
    void setSpeak(String s);

    // return the name of the image file in String format
    // Don't worry about formatting it as an image, the driver class will do that
    String getImageName();
}