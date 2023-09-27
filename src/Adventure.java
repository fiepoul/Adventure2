public class Adventure {
    private Room currentRoom;

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

        room1.addExit("east", room2);
        room1.addExit("south", room4);
        room2.addExit("west", room1);
        room2.addExit("east", room3);
        room3.addExit("west", room2);
        room3.addExit("south", room6);
        room4.addExit("north", room1);
        room4.addExit("south", room7);
        room5.addExit("west", room8);
        room6.addExit("north", room3);
        room6.addExit("south", room9);
        room7.addExit("north", room4);
        room7.addExit("east", room8);
        room8.addExit("north", room5);
        room8.addExit("west", room7);
        room8.addExit("east", room9);
        room9.addExit("west", room8);
        room9.addExit("north", room6);

        currentRoom = room1;
    }

    public void move(String direction) {
        Room newRoom = currentRoom.getExit(direction);
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
}
