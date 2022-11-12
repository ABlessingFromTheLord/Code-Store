public class Bathroom extends Room{
    private boolean shower = false;

    public Bathroom(String the_roomName, boolean the_light, boolean the_Shower) {
        super(the_roomName, the_light);
        shower = the_Shower;
    }

    public void switchShower(){
        shower = true;
        System.out.println("Switched on shower");
    }

}
