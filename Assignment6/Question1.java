package Assignment6;

public class Question1 {
    public static void main (String args[]){
        try{
            int test = process(10);
        }catch (MyIndexOutOfBoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static int process(int num) throws MyIndexOutOfBoundException{
        if (num < 0 || num > 9){
            MyIndexOutOfBoundException exception = new MyIndexOutOfBoundException();
            exception.setIndex(num);
            throw new MyIndexOutOfBoundException("Error Message",
                                                 "Index: " + num + ", but Lower bound: " + exception.getLowerBound()
                                                 + ", Upper bound: " + exception.getUpperBound() );
        }

        return 9 - num;
    }


}

