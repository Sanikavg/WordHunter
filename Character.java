
/**
 * Class Character - a character in an adventure game.
 *
 * This class is part of the "Word Hunter" application. 
 * "Word Hunter" is a very simple, text based adventure game. 
 *
 * A "Character" represents one character in the game. The characters are assigned random locations that keep changing. Each Character presents 
 * the player with a surprise message which could either help the player win or bring in a change.  
 *
 * K number: 22019372
 * Student name: Sanika Gadgil
 */
public class Character
{
    Room room; //stores the room the character is in.
    String description; //stores the description of the character.
    boolean messageTaken;  //checks whether a message is given or not.
    
    /**
     * Create a character described "description". Initially, it has
     * no room or message assigned. "description" is something like "chef" or
     * "librarian".
     * @param description The character's description.
     */
    public Character(String description)
    {
        this.description = description;
        messageTaken = false;
    }

    /**
     * Return a message from the character,
     * example:"Message from Character: Hello. I am the chef. I have a special surprise for you. It could be a boon or bane. 
     * Are you willing to take the risk? Kindly type 'get' to receive a surprise!
     * 
     * @return A message
     */
    public String getMessage()
    {
        return "Message from Character: Hello. I am the " + description + ". I have a special surprise for you. " + "\n" + "It could be a boon or bane. Are you willing to take the risk? Kindly type 'get' to receive a surprise!";
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public void move(Room room)
    {
        this.room = room;
    }
    
    /**
     * Return the location of the character.
     * @return the room of the character.
     */
    public Room getLocation()
    {
        return room;
    }
    
    /**
     * if message has been taken from the character, store the value true in messageTaken.
     * @param messageTaken (true or false)
     */
    public void messageGiven(boolean messageTaken)
    {
        this.messageTaken = messageTaken;
    }
    
    /**
     * if message has been taken from the character and given to the player, return true.
     * @return messageTaken (true or false)
     */
    public boolean checkMessageGiven()
    {
        return messageTaken;
    }
}
