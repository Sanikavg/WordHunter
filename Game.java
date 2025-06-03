import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  This class is the main class of the "Word Hunter" application. 
 *  "Word Hunter" is a very simple, text based adventure game.  Users 
 *  can walk around, pick items, drop items, meet characters, get surprises and win the game.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, items, characters, commands, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * K number: 22019372
 * Student name: Sanika Gadgil
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; //the current room of the player
    Room ballroom,bar,billiardRoom,library,bedroom,lounge,dining,kitchen,magicTransporter; //all the rooms in the game
    Room [] room; //an array that lists all the rooms 
    ArrayList<Room> visitedRooms; //lists the rooms visited by the player
    public Player player; //player of the game
    Item glass, cue, ball, book, pillow, bag, candlestick, knife, pan; //all the items in the game
    Item[] item; //an array that stores all items
    ArrayList<String> letters; //lists all letters assigned to the items
    Character chef, bartender, waiter, librarian; //all characters in the game
    private boolean playerMetCharacter; //confirms if a player has met a character
    private String startWord; //the word assigned to the player 
    
    /**
     * Create the game and initialise its internal map, the player, the rooms, the characters, the items, the letters
     */
    public Game() 
    {
        visitedRooms = new ArrayList<Room>();
        player = new Player(ballroom); //the player starts in the ballroom
        createRooms();
        item_letters();
        roomItems();
        createCharacters();
        parser = new Parser();
        Room[] room2 = {ballroom,bar,billiardRoom,library,bedroom,lounge,dining,kitchen,magicTransporter};
        room = room2;
        Item[] item2 = {glass, cue, ball, book, pillow, bag, candlestick, knife, pan};
        item = item2;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
      
        // create the rooms
        ballroom = new Room("ballroom", "north");
        bar = new Room("bar", "northeast");
        billiardRoom = new Room("billiardRoom", "east");
        library = new Room("library", "southeast");
        bedroom = new Room("bedroom", "south");
        lounge = new Room("lounge", "southwest");
        dining = new Room("dining", "west");
        kitchen = new Room("kitchen", "northwest");
        magicTransporter = new Room("magicTransporter", "centre");
        
        // initialise room exits
        ballroom.setExit("west",kitchen);
        ballroom.setExit("east", bar);
        ballroom.setExit("south", magicTransporter);
        
        bar.setExit("south",billiardRoom);
        bar.setExit("west",ballroom);
        
        billiardRoom.setExit("north",bar);
        billiardRoom.setExit("south",library);
        billiardRoom.setExit("west", magicTransporter);
        
        library.setExit("north",billiardRoom);
        library.setExit("west",bedroom);
        
        bedroom.setExit("east",library);
        bedroom.setExit("west",lounge);
        bedroom.setExit("north",magicTransporter);
        
        lounge.setExit("east",bedroom);
        lounge.setExit("north",dining);
        
        dining.setExit("south",lounge);
        dining.setExit("north",kitchen);
        dining.setExit("east",magicTransporter);
        
        kitchen.setExit("south",dining);
        kitchen.setExit("east",ballroom);

        currentRoom = ballroom;  // start game in the ballroom
    }
    
    /**
     * list all the letters to assigned to the items
     */
    private void item_letters()
    {
        letters = new ArrayList<String>();
        letters.add("L");
        letters.add("I");
        letters.add("O");
        letters.add("V");
        letters.add("A");
        letters.add("E");
        letters.add("R");
        letters.add("S");
        letters.add("F");         
    }
    
    /**
     * Create all the items and place them in rooms. Also assign them letters.
     */
    private void roomItems()
    {
        Random rand = new Random();
        
        int int_random = rand.nextInt(letters.size()); 
        glass = new Item("glass", 600);
        bar.placeItem("glass");
        glass.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size()); 
        cue = new Item("cue", 500);
        billiardRoom.placeItem("cue");
        cue.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size()); 
        ball = new Item("ball", 170);
        billiardRoom.placeItem("ball");
        ball.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size()); 
        book = new Item("book", 500);
        library.placeItem("book");
        book.setLetter(letters.get(int_random));
        letters.remove(int_random);

        int_random = rand.nextInt(letters.size()); 
        pillow = new Item("pillow", 300);
        bedroom.placeItem("pillow");
        pillow.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size()); 
        bag = new Item("bag", 800);
        bedroom.placeItem("bag");
        bag.setLetter(letters.get(int_random));
        letters.remove(int_random);

        int_random = rand.nextInt(letters.size()); 
        candlestick = new Item("candlestick", 500);
        lounge.placeItem("candlestick");
        candlestick.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size()); 
        knife = new Item("knife", 450);
        dining.placeItem("knife");
        knife.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
        int_random = rand.nextInt(letters.size());         
        pan = new Item("pan", 500);
        kitchen.placeItem("pan");
        pan.setLetter(letters.get(int_random));
        letters.remove(int_random);
        
    }
    
    /**
     * Assign the player a word to play
     */
    public void getWord(Player player)
    {
        Random rand = new Random();
        int int_random = rand.nextInt(8); 
        String[] words = {"LIFE" , "LOVE" , "LIVE" , "FEAR" , "REAL" , "FAIR", "SAVE", "SAFE"};
        startWord = words[int_random];
        player.current_word(startWord);
        System.out.println("You are assigned the word: " + startWord);
    }
    
    /**
     * Create all the characters
     */
    private void createCharacters()
    {
        
        chef = new Character("chef");
        
        bartender = new Character("bartender");
        
        waiter = new Character("waiter");
        
        librarian = new Character("librarian");
        
    }
    
    /**
     * Move the characters to rooms randomly.
     */
    private void moveCharacters()
    {
        ArrayList <Room> characterRooms = new ArrayList<Room>();
        characterRooms.add(bar);
        characterRooms.add(billiardRoom);
        characterRooms.add(library);
        characterRooms.add(bedroom);
        characterRooms.add(lounge);
        characterRooms.add(dining);
        characterRooms.add(kitchen);
        
        Random rand = new Random();
        
        int int_random = rand.nextInt(characterRooms.size());
        chef.move(characterRooms.get(int_random));
        characterRooms.remove(int_random);
        
        int_random = rand.nextInt(characterRooms.size());
        bartender.move(characterRooms.get(int_random));
        characterRooms.remove(int_random);
        
        int_random = rand.nextInt(characterRooms.size());
        waiter.move(characterRooms.get(int_random));
        characterRooms.remove(int_random);
        
        int_random = rand.nextInt(characterRooms.size());
        librarian.move(characterRooms.get(int_random));
        characterRooms.remove(int_random);
    }
    
    /**
     * Display the message from the character in the room.
     */
    private void printRoomCharacter()
    {
        if (chef.getLocation().getShortDescription().equals(currentRoom.getShortDescription()) && !chef.checkMessageGiven())
        {
            playerMetCharacter = true;
            chef.messageGiven(true);
            System.out.println(chef.getMessage());
            return;
        }
        else if (bartender.getLocation().getShortDescription().equals(currentRoom.getShortDescription()) && !bartender.checkMessageGiven())
        {
            playerMetCharacter = true;
            bartender.messageGiven(true);
            System.out.println(bartender.getMessage());
            return;
        }
        else if (waiter.getLocation().getShortDescription().equals(currentRoom.getShortDescription()) && !waiter.checkMessageGiven())
        {
            playerMetCharacter = true;
            waiter.messageGiven(true);
            System.out.println(waiter.getMessage());
            return;
        }
        else if (librarian.getLocation().getShortDescription().equals(currentRoom.getShortDescription()) && !librarian.checkMessageGiven())
        {
            playerMetCharacter = true;
            librarian.messageGiven(true);
            System.out.println(librarian.getMessage());
            return;
        }
        playerMetCharacter = false; //if there is no character in the room, the player has not met any character.
    }
    
    /**
     * Create the magic transporter room and give it the ability to be able to transport the player to a random room.
     */
    private void magicTransporterRoom()
    {
        Random rand = new Random();
        int int_random = rand.nextInt(8); 
        Room magicRoom = room[int_random];
        if (magicRoom == currentRoom)
        {
            magicTransporterRoom();
        }
        else
        {
            currentRoom = magicRoom;
            visitedRooms.add(currentRoom);
            System.out.println(currentRoom.getLongDescription(currentRoom));
            return;
        }
    }
    
    /**
     * Check if the player has won. (Check if all the items, with letters needed to form the word, have been dropped in the ballroom)
     */
    public void checkWin()
    {
        ArrayList<Item> item_obj = new ArrayList<Item>();
        ArrayList <String> collectedItems = ballroom.getItems();
        for (String x: collectedItems)
                {
                    for (Item i: item)
                    {
                        if (x.equals(i.getDescription()))
                        {
                            item_obj.add(i);
                        }
                    }
                }
        int letterCount = 0;
        String[] ch = player.getCurrentWord().split("");
        for (String w: ch)
            { 
                for (Item i: item_obj)
                {
                    if (w.equals(i.getLetter(i)))
                    {
                        letterCount += 1;
                        break;
                    }
                }
            }
        
        if (letterCount == 4)
        {
            System.out.println("!!!CONGRATULATIONS!!! You have successfully collected all the letter items to complete the word.");
            System.out.println("Thank you for playing! Type 'quit' to quit the game.");
        }
        else
        { 
            return;
        }
    }
    
    /**
     * Give the player the ability to use the surprise they have received.
     */
    private void availSurprise(int int_random, String[] surprise)
    {
        if (int_random == 0)
        {
            String currentWord = player.getCurrentWord();
            getWord(player);
            if (startWord.equals(currentWord))
            {
                System.out.println("Woah that was a lucky escape :)");
                return;
            }
            else
            {
                System.out.println("Oops! Don't give up just yet, it's just a word change, you got this!");
                return;
            }
        }
        else if(int_random == 1)
        {
            System.out.println("WOHOOOO!! You have entered the magic transporter room. You will now be transported to a random room.");
            System.out.println(".........");
            magicTransporterRoom();
            return;
        }
        else if(int_random == 2)
        {
            System.out.println("Type 'move' in order to move an item");
            return;
        }
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        visitedRooms.add(currentRoom);
        getWord(player);
        moveCharacters();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Word Hunter!");
        System.out.println("Get ready for an exhilarating word-hunting adventure. Immerse yourself in a world of hidden letters.");
        System.out.println("There are some items in every room. Enter 'pick' to pick an item. Each item contains a letter. ");
        System.out.println("Your task is to gather all the items, that contain the letters you would need, to complete your word.");
        System.out.println("In order to win, the items needs to be collected in the ballroom."); 
        System.out.println("Warning: The rooms are surrounded with water. If you exit a room with more than 1000 grams weight on you, you are at the risk of drowning.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription(currentRoom));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean. Kindly enter  valid command word.");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("pick"))
        {
            pickItem(command);
        }
        else if (commandWord.equals("drop"))
        {
            dropItem(command);
        }
        else if (commandWord.equals("get"))
        {
            getSurprise(command);
        }
        else if(commandWord.equals("move"))
        {
            moveItem(command);
        }
        else if (commandWord.equals("back")) 
        {
            goBack();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Are you lost? Don't give up just yet.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. If there is amagic transporter room, call the magic transporter method
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.getShortDescription().equals(magicTransporter.getShortDescription()))
            {
                System.out.println("WOHOOOO!! You have entered the magic transporter room. You will now be transported to a different room.");
                System.out.println(".........");
                magicTransporterRoom();
                return;
            }
        
            currentRoom = nextRoom;
            visitedRooms.add(currentRoom);
            System.out.println(currentRoom.getLongDescription(currentRoom));
            
            printRoomCharacter();
        }
    }
    
    /**
     * Give the player a surprise. If right command not entered, display error message.
     */
    private void getSurprise(Command command)
    {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Get what?");
            return;
        }
        if(!playerMetCharacter)
        {
            System.out.println("You haven't met a character in this room. You are not entitled to receiving a surprise.");
            return;
        }
        else
        {
            Random rand = new Random();
            int int_random = rand.nextInt(3); 
            String[]surprise = {"You're at the risk of a word change....", "You are being transported to the magic room", "You are allowed to move one item of your choice to one room of your choice."};
            System.out.println("REVEALING SURPRISE");
            System.out.println("!!! " + surprise[int_random] +" !!!");
            availSurprise(int_random, surprise);
        }
        
    }
    
    /**
     * If a player has met a character and received the "Move item" surprise, then allow the user to use the move command. 
     * Move the reuqested item to the requested room.
     */
    private void moveItem(Command command)
    {
        if (!playerMetCharacter)
        {
            System.out.println("You haven't met a character here to use this command.");
            return;
        }
        //Item[] items = {glass, cue, ball, book, pillow, bag, candlestick, knife, pan};
        //Room[] rooms = {ballroom,bar,billiardRoom,library,bedroom,lounge,dining,kitchen};
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Move what?");
            for (Item i: item)
            {
                System.out.println("Item: "+ i.getDescription() + " ,weight: " + i.getWeight() + " ,letter: " + i.getLetter(i));
            }
            return;
        }
        if(!command.hasThirdWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Move where?");
            for (Room r: room)
            {
                System.out.println("Room: " + r.getShortDescription() + " ,Items in room: " + r.getItems());
            }
            return;
        }
        else
        {
            String itemToMove = command.getSecondWord();
            String roomToMove = command.getThirdWord();
            
            for(Room r: room)
            {
                if (r.getShortDescription().equals(roomToMove))
                {
                    r.placeItem(itemToMove);
                    System.out.println("Your item has been successfully moved.");
                }
            }
            
            for (Item i: item)
            {
                if (i.getDescription().equals(itemToMove))
                {
                    i.getLocation().removeItem(i);
                }
            }
            
            ArrayList <String> heldItems = player.getItem();
            for (String i: heldItems)
            {
                if (i.equals(itemToMove))
                {
                    player.removeHeldItem(i, item);
                }
            }
            
            checkWin(); //if the user has moved the last necessary item to the ballroom, check if the user has won.
        }
    }
    
    /**
     * Allow the user to go the last visited location until the user is back to their starting room, the ballroom.
     */
    private void goBack()
    {
        currentRoom = visitedRooms.get(visitedRooms.size() - 2);
        visitedRooms.remove(visitedRooms.size() - 1);
        if (currentRoom.getShortDescription().equals(ballroom.getShortDescription()))
        {
            System.out.println("You are back to your starting room");
            System.out.println(currentRoom.getLongDescription(currentRoom));
            checkWin();
            moveCharacters();
            return;
        }
        System.out.println(currentRoom.getLongDescription(currentRoom));
        return;
    }
    
    /**
     * Pick the item from the room, if it exists in the room, otherwise display error message.
     */
    private void pickItem(Command command)
    {
        ArrayList <String> roomItems = currentRoom.getItems();
        //Item[] items = {glass, cue, ball, book, pillow, bag, candlestick, knife, pan};
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            if (roomItems.size() == 0)
            {
                System.out.println("There are no items in this room.");
                return;
            }
            
            System.out.println("Items in the room: ");
            for (Item i: item)
            { 
                for (String x: roomItems)
                {
                    if (x.equals(i.getDescription()))
                    {
                        System.out.println("Item: "+ x + " ,weight: " + i.getWeight() + " ,letter: " + i.getLetter(i));
                        
                    }
                }
                
            }
            return;
        }
        String description = command.getSecondWord();
        if (!currentRoom.getItems().contains(description))
        {
            System.out.println("Item does not exist in this room"); 
            return;
        }
        else
        { 
           
            for (Item i: item)
            { 
                
                 if (description.equals(i.getDescription()))
                    {
                        if (!player.itemCarryCheck(i))
                        {
                            System.out.println("You have exceeded the total weight. You cannot pick this item. You are at the risk of drowning.");
                        }
                        else
                        {
                            player.addItem(i);
                            currentRoom.removeItem(i);
                            System.out.println("You have successfully picked up the item.");
                        }
                        return;
                    }
                
                
            }
            
        
        }
    }
    
    /**
     * Drop an item held by the user in the current room.
     */
    private void dropItem(Command command)
        {
            ArrayList <String> heldItems = player.getItem();
            //Item[] items = {glass, cue, ball, book, pillow, bag, candlestick, knife, pan};
            if(!command.hasSecondWord())
            {
                if (heldItems.size() == 0)
                {
                    System.out.println("You are not holding any item.");
                    return;
                }
                
                System.out.println("Items you are holding: ");

                for (String i: heldItems)
                {
                    System.out.println(i);
                }
                return;
            }
            String droppedItem = command.getSecondWord();
            if (!player.getItem().contains(droppedItem))
            {
                System.out.println("You are not carrying this item.");
                return;
            }
            else
            {
                currentRoom.placeItem(droppedItem);
                for (int i = 0; i < heldItems.size(); i++)
                {
                    if (heldItems.get(i).equals(droppedItem))
                    {
                        player.removeHeldItem(heldItems.get(i),item);
                    }
                }
                System.out.println("The item has been dropped.");
                checkWin(); //If the user has dropped the last required item, then check if the user has won.
                return;
            }
            
        }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
