public class Room {
    private String roomName;
    private boolean lighting = false;

    public Room(String the_roomName, boolean the_light){
        roomName = the_roomName;
        lighting = the_light;
    }

    //functions
    public void enter(){
        System.out.println("You are now in the "+ roomName);
    }

    public void switchLight(){
        lighting = true;
        System.out.println("Switched on the light");
    }

}
