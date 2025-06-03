import java.util.ArrayList;

/**
 * Class Item - an item in an adventure game.
 *
 * This class is part of the "Word Hunter" application. 
 * "Word Hunter" is a very simple, text based adventure game. 
 * 
 * A "item" represents an item in the game. The item is located in a room. It has some weight value. It has a letter contained in it. 
 * An item cannot move around by itself. It needs to be moved by a player.
 *
 * K number: 22019372
 * Student name: Sanika Gadgil
 */
public class Item
{
    private int weight; //weight of the item (in grams)
    Room location; //set the location of the item
    String letter; //assign a letter to the item
    String description; //description of the item
    
    /**
     * Create an item described "description" and assign it some weight.
     * @param description, the description of the item
     * @param weight, the weight of the item
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight; //in grams
    }
    
    /**
     * @return The description of the room
     * (the one that was defined in the constructor).
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * set location of the item
     * @param location, the room the item is to be kept in.
     */
    public void setLocation(Room location)
    {
        // put your code here
        this.location = location;
        
    }

    /**
     * get the location of the item
     * @return location, the room the item is kept in.
     */
    public Room getLocation()
    {
        return location;
    }
    
    /**
     * assign a letter to the item
     * @param letter, the letter the item contains
     */
    public void setLetter(String letter)
    {
        this.letter = letter;
    }
    
    /**
     * get the letter of the item
     * @return letter, the letter the item contains.
     */
    public String getLetter(Item i)
    {
        return letter;
    }
    
    /**
     * get the weight of the item
     * @return weight, the weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
}
