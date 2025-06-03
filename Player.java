import java.util.ArrayList;

/**
 * Class Player - a player in an adventure game.
 *
 * This class is part of the "Word Hunter" application. 
 * "Word Hunter" is a very simple, text based adventure game. 
 *
 * A "Player" represents the player in the game. The players play the game, move around, pick items, drop items, hold items.
 * The player can carry only upto a 1000 grams of weight at a time, otherwise they are at a risk of drowning.
 * The player is assigned a word at the start of the game which may or may not change during the course of the game.
 *
 * K number: 22019372
 * Student name: Sanika Gadgil
 */
public class Player
{
    private Room location; //set the location of the player.
    private Room startRoom; //set the startRoom of the player.
    private int total_weight; //hold the total weight (in grams) the player is carrying.
    private ArrayList<String> heldItems; //list of items carried by the player.
    private String word; //the word assigned to the player.
    
    
    /**
     * Create a player. Initially, it has a starting location, the ballroom.
     * 
     * @param  startRoom The ballroom.
     */
    public Player(Room startRoom)
    {
        this.startRoom = startRoom;
        total_weight = 0;
        heldItems = new ArrayList<>();
    }
    
    /**
     * When a player picks and item, add the item to the list of items held by the player.
     * add the weight of the item to the current total weight on the player.
     * @param the item to be added
     */
    public void addItem(Item i)
    {
        heldItems.add(i.getDescription());
        total_weight += i.getWeight();
    }
    
    /**
     * return the list of items held by the user.
     */
    public ArrayList getItem()
    {
        return heldItems;
    }
    
    /**
     * When a player drops an item, remove the item from the list of items held by the player.
     * subtract the weight of the item from the current total weight on the player.
     * @param item: the item to be removed
     * @param items: the item list containing the item objects
     */
    public void removeHeldItem(String item, Item[] items)
    {
        //Subtract the weight of the item from the current total weight on the player.
        for (Item i: items)
        {
            
            if (i.getDescription().equals(item))
            {
                    total_weight -= i.getWeight();
            }
            
        }
        
        //Remove an item from the list of items held by the player.
        for (int i = 0; i < heldItems.size(); i++)
        {
            if (heldItems.get(i).equals(item))
            {
                heldItems.remove(i);
                
            }
        }
        
    }
    
    /**
     * Check if a certain item can be picked up by the player.
     * If the weight of the item added to the total weight (currently on the player) exceeds 1000 grams, the player cannot pick up the item.
     * @param item: the item to be picked
     * @return true, if the player can pick the item and false, if the player can't pick the item
     */
    public boolean itemCarryCheck(Item i)
    {
        
        if ((total_weight + i.getWeight()) <= 1000)
        {
            System.out.println((total_weight + i.getWeight()));
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * set the current word of the player.
     * @param the current word.
     */
    public void current_word(String word)
    {
        this.word = word;
    }
    
    /**
     * get the current word of the player.
     * @return the current word.
     */
    public String getCurrentWord()
    {
        return word;
    }
}
