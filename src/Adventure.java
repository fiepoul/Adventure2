public class Adventure {
    private Room currentRoom;
    private Player player;

    public Adventure() {

        Room room1 = new Room("Room 1", "You are in America. What you have to do now is make the right choices.");
        Room room2 = new Room("Room 2", "You are searching for a job. Excellent choice.");
        Room room3 = new Room("Room 3", "You didn't get your dream job, but a job at target that almost pays your rent.");
        Room room4 = new Room("Room 4", "You are getting closer to the dream by exploring the possibilities of becoming a famous actor.");
        Room room5 = new Room("Room 5", "You marry rich. Congratulation you are now in possession of the american dream! Remember not to sign a prenup!");
        Room room6 = new Room("Room 6", "You realize that you will have to call your parents to fully pay for your rent.");
        Room room7 = new Room("Room 7", "You moved to hollywood. Good job! Now the problem is that you are stuck working for another actor getting his assistant coffee.");
        Room room8 = new Room("Room 8", "You realize that you are never able to reach the american dream if you where not born into wealth. What do you do now? You are only one choice away from reaching your dream.");
        Room room9 = new Room("Room 9", "You're parents accepted your plea for money and offered a weekly allowance of 100 dollars if you call home more often. You accept.");

        room1.addExit(Room.Direction.NORTH, room2);
        room1.addExit(Room.Direction.SOUTH, room4);
        room2.addExit(Room.Direction.WEST, room1);
        room2.addExit(Room.Direction.EAST, room3);
        room3.addExit(Room.Direction.WEST, room2);
        room3.addExit(Room.Direction.SOUTH, room6);
        room4.addExit(Room.Direction.NORTH, room1);
        room4.addExit(Room.Direction.SOUTH, room7);
        room5.addExit(Room.Direction.WEST, room8);
        room6.addExit(Room.Direction.NORTH, room3);
        room6.addExit(Room.Direction.SOUTH, room9);
        room7.addExit(Room.Direction.NORTH, room4);
        room7.addExit(Room.Direction.EAST, room8);
        room8.addExit(Room.Direction.NORTH, room5);
        room8.addExit(Room.Direction.WEST, room7);
        room8.addExit(Room.Direction.EAST, room9);
        room9.addExit(Room.Direction.WEST, room8);
        room9.addExit(Room.Direction.NORTH, room6);

        Item jobOpportunity = new Item("A job opportunity that maybe can help reach the dream", "Job opportunity");
        room2.addItem(jobOpportunity);
        Item phone = new Item("Pick up the phone and call your parents", "Phone");
        room6.addItem(phone);
        Item paycheck = new Item("The paycheck that almost pays your rent","Paycheck");
        room3.addItem(paycheck);
        Item ticketToHollywood = new Item("Pick up your ticket to reach the stars", "Ticket to Hollywood");
        room4.addItem(ticketToHollywood);
        Item coffeeMug = new Item("Pick up the cup to do your job", "Coffee mug");
        room6.addItem(coffeeMug);
        Item parentsMoney = new Item("Pick up 100 dollars so you can pay your rent", "Money from parents");
        room9.addItem(parentsMoney);
        Item richSpouse = new Item("Pick up a rich possible spouse", "Rich possible spouse");
        room8.addItem(richSpouse);

        player = new Player(room1);
        currentRoom = room1;
    }

    public void move(String direction) {
        Room newRoom = currentRoom.getExit(Room.Direction.valueOf(direction));
        if (newRoom != null) {
            currentRoom = newRoom;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Player getPlayer() {
        return player;
    }
}
