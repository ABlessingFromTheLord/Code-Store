
public class Bathroom extends Room{
    private boolean shower = false;

    public Bathroom(String the_roomName) {
        super(the_roomName);
        shower = false;
    }

    public void switchShower(){
        shower = !shower;
        System.out.println("Switched on shower");
    }

}
