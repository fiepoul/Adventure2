public class Adventure {
    private Room currentRoom;

    private final Player player;

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

        room1.addExit(Room.Direction.EAST, room2);
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

        Item job = new Item("a job opportunity", "job");
        room2.addItem(job);
        Item phone = new Item("phone call with your parents", "phone");
        room6.addItem(phone);
        Item paycheck = new Item("the paycheck", "check");
        room3.addItem(paycheck);
        Item ticket = new Item("ticket to Hollywood", "ticket");
        room4.addItem(ticket);
        Item mug = new Item("coffee mug", "mug");
        room7.addItem(mug);
        Item money = new Item("100 dollars", "money");
        room9.addItem(money);
        Item spouse = new Item("a rich possible spouse", "spouse");
        room8.addItem(spouse);

        Food donut = new Food("Dunkin donut", "Donut", 5);
        Food pizza = new Food("Pizza slice", "Pizza", 20);
        Food burger = new Food("Cheeseburger", "burger", 30);
        Food cake = new Food("Wedding cake", "cake", 30);
        Food trash = new Food("Dumpster food", "trash", 20);
        Food smoothie = new Food("Smoothie bowl", "Smoothie", 40);
        Food coffee = new Food("Icecoffee", "Coffee", 5);

        room2.addFood(donut);
        room3.addFood(pizza);
        room4.addFood(burger);
        room5.addFood(cake);
        room6.addFood(trash);
        room8.addFood(smoothie);
        room9.addFood(coffee);

        RangedWeapon pistol = new RangedWeapon("Love pistol", "pistol", 6);
        MeleeWeapon knife = new MeleeWeapon("Anti-violence knife", "knife");
        MeleeWeapon sword = new MeleeWeapon("Compassionate sword", "sword");

        room3.addWeapon(pistol);
        room4.addWeapon(knife);
        room7.addWeapon(sword);

        Enemy anxiety = new Enemy("Anxiety", "anxiety of not making it", 20, knife);
        Enemy homesickness = new Enemy("homesickness", "you miss your parents", 40, pistol);

        room3.addEnemy(anxiety);
        room8.addEnemy(homesickness);

        currentRoom = room1;
        player = new Player(room1, 40);
    }

    public void move(Room.Direction direction) {
        if (currentRoom.isValidExit(direction)) {
            currentRoom = currentRoom.getExit(direction);
        } else {
            System.out.println("You cannot go in that direction.");
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public void removeFood(Food food) {
        currentRoom.removeFood(food);
    }
}
