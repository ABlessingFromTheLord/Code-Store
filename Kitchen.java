public class Kitchen extends Room{
    private boolean stove = false;

    public Kitchen(String the_roomName, boolean the_light, boolean the_stove) {
        super(the_roomName, the_light);
        stove = the_stove;
    }

    public void switchStove(){
        stove = true;
        System.out.println("Swtiched on the stove");
    }

}
