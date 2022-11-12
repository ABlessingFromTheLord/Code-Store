import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //creating rooms
        Room room_one = new Room("Hallway");
        Room room_two = new Room("Kitchen");
        Room room_three = new Room("Living_room");
        Room room_four = new Room("Bathroom");

        //adding rooms into apartment
        Apartment kleinApartment = new Apartment(room_one);
        kleinApartment.addNewRoom(room_two);
        kleinApartment.addNewRoom(room_three);
        kleinApartment.addNewRoom(room_four);

        //creating neighbors
        ArrayList<Room> edge_hallway_livingroom = new ArrayList<>();
        edge_hallway_livingroom.add(room_one);
        edge_hallway_livingroom.add(room_three);

        ArrayList<Room> edge_livingroom_kitchen = new ArrayList<>();
        edge_livingroom_kitchen.add(room_three);
        edge_livingroom_kitchen.add(room_two);

        ArrayList<Room> edge_livingroom_bathroom = new ArrayList<>();
        edge_livingroom_bathroom.add(room_three);
        edge_livingroom_bathroom.add(room_four);

        //specifying neighbors in the apartment
        kleinApartment.addNeighbors(edge_hallway_livingroom);
        kleinApartment.addNeighbors(edge_livingroom_kitchen);
        kleinApartment.addNeighbors(edge_livingroom_bathroom);

        //testing
        kleinApartment.enter();

    }

}
