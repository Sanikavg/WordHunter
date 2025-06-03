import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Word Hunter" application. 
 * "Word Hunter" is a very simple, text based adventure game. 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. The room also contains some or no items.
 * 
 * K number: 22019372
 * Student name: Sanika Gadgil
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;                 //stores the description of the room.
    private String location;                    //stores the location (direction) of the room.
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<String> items;            //stores items of the room
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param location The room's location.
     */
    public Room(String description, String location) 
    {
        this.description = description;  
        this.location = location;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription(Room room)
    {
        return "You are in the " + description + " located in the " + location + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = " , Exits available: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit ;
        }
        return returnString;
    }
    
    /**
     * Place an item in the room, 
     * for example: pan in the kitchen.
     * Add it to the list of items in the room.
     * @param item (description) to be added to the room
     */
    public void placeItem(String item)
    {
        items.add(item);
    }
    
    /**
     * Return an ArrayList listing the items (as their descriptions) placed in the room.
     */
    public ArrayList getItems()
    {
        return items;
    }
    
    /**
     * Remove an item from the room.
     * Delete it from list of items in the room.
     * @param item (object) The item to be removed
     */
    public void removeItem(Item item)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).equals(item.getDescription()))
            {
                items.remove(i);
            }
        }
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

