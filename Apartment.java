
import java.util.*;

public class Apartment{

    private Room current_room; //to keep track of where we are
    ArrayList<Room> apt = new ArrayList<>();
    ArrayList<ArrayList<Room>> neighbors = new ArrayList<>();
    ArrayList<Room> previous_rooms = new ArrayList<>();


    public Apartment(Room entrance){
        this.apt.add(entrance); //we put the entrance in the apartment
        this.current_room = entrance;
        this.previous_rooms.add(entrance);
    }

    //functions
    public void addNewRoom(Room room){
        this.apt.add(room);
    }

    public void addNeighbors(ArrayList<Room> edge){
        this.neighbors.add(edge); // we add a connection between two rooms that we call edge
    }

    public void enter(){
        while(true){
            System.out.println("You are in the "+ this.current_room.getRoomName());
            System.out.println("What do you want to do?");


            //possibilities
            //common possibilities
            int counter = 1;

            System.out.println(counter+ ") Switch Light");
            counter++;
            System.out.println(counter + ") Leave Room");
            counter++;
            System.out.println(counter+ ") Enter neighboring Room");
            counter++;

            //special possibilities
            if(Objects.equals(current_room.getRoomName(), "Kitchen")){
                System.out.println(counter+ ") Switch stove");

            } else if (Objects.equals(current_room.getRoomName(), "Bathroom")) {
                System.out.println(counter+ ") Switch on shower");
            }
            counter++;


            //user gives their input
            Scanner sc = new Scanner(System.in);
            String user_input = sc.nextLine().toLowerCase(Locale.ROOT); //to remove case sensitivities

            //comparing user input
            if(Objects.equals(user_input, "switch light")){
                current_room.switchLight();
            }

            else if (Objects.equals(user_input, "leave room")) {
                //edge case for when we are in the entrance
                if (current_room.getRoomName().equals("hallway")){
                    //**check states function comes here **

                    break;

                }

                //we are in any room i
                else{
                    current_room = previous_rooms.get(previous_rooms.size()-1);// returning to the one before the last one

                    //removing the last room
                    previous_rooms.remove(previous_rooms.size()-1);
                }
                break;
            }

            else if (user_input.equals("switch stove") && current_room.getRoomName().equals("Kitchen")) {
                //still have to implement the functionality

                System.out.println("switched on Stove");
                break;
            }

            else if (user_input.equals("switch on shower") && current_room.getRoomName().equals("Bathroom")) {
                //still have to implement the functionality

                System.out.println("switched shower on");
                break;
            }

            else if (user_input.equals("enter neighboring room")) {
                System.out.println("Which Room?");
                ArrayList<Room> choose_from = new ArrayList<>();

                for (ArrayList<Room> edge: this.neighbors
                ) {
                    if(edge.contains(current_room)){
                        if(edge.get(0) == current_room){
                            choose_from.add(edge.get(1));
                        }
                        choose_from.add(edge.get(0));
                    }

                }

                counter = 1;
                for (Room i: choose_from
                ) {
                    System.out.println(counter+ ") " + i.getRoomName());
                    counter++;
                }

                Scanner sc2 = new Scanner(System.in);
                String input2 = sc2.next().toLowerCase(Locale.ROOT);
                Room chosen_room = null;

                for (Room i: choose_from
                ) {

                    if(i.getRoomName().toLowerCase(Locale.ROOT).equals(input2)){
                        chosen_room = i;
                    }
                }
                previous_rooms.add(previous_rooms.size(), current_room);//placing the current room in the lsit of rooms visited since we are moving foward
                current_room = chosen_room; //overwrite the current room to be the next room

            }


        }

    }


    /*
    public void showrooms(){
        for (Room i: apt
        ) {
            System.out.println(i.getRoomName());
        }
    }*/


}
