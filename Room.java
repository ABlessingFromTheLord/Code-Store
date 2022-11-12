public class Room {
    private String roomName;
    private boolean lighting;

    public Room(String the_roomName){
        roomName = the_roomName;
        lighting = false;
    }

    //functions
    public void enter(){
        System.out.println("You are now in the "+ roomName);
    }

    public void switchLight(){
        lighting = !(lighting);
        if(lighting == true){
            System.out.println("Switched on the light");
        }
        else{
            System.out.println("Switched off the light");
        }
    }

    public String getRoomName() {
        return roomName;
    }

}
