public class Kitchen extends Room{
    private boolean stove = false;
    private boolean isKitchen = true;

    public Kitchen(String the_roomName) {
        super(the_roomName);
        stove = false;
    }

    public void switchStove(){
        stove = !stove;

        if(stove == true){
            System.out.println("Switched on the stove");
        }
        else{
            System.out.println("Switched off the stove");
        }
    }

    public boolean isKitchen() {
        return isKitchen;
    }
}

