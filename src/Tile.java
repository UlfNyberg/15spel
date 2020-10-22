
public class Tile {

    //Tiles kommentar
    private int number;

    /**
     * Konstruktor till tiles
     * @param number
     */
    public Tile(int number){
        this.number = number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
