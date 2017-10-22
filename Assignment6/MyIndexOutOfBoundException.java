package Assignment6;

public class MyIndexOutOfBoundException extends Exception {
    private int lowerBound = 0;
    private int upperBound = 9;
    private int index;

    public MyIndexOutOfBoundException(){

    }

    public MyIndexOutOfBoundException(String statement, String reason){
        super(statement + ": " + reason);
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setIndex(int index){
        this.index = index;
    }
}