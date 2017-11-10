package Assignment7;

public class ReverseHello extends Thread {
    private static int i = 1;
    private static Thread[] threads = new Thread[50];

    public ReverseHello(){

    }

    public static void main(String[] args){
        threads[0] = new ReverseHello();
        threads[0].start();
    }

    private void createThread(int i) throws InterruptedException{
        threads[i] = new ReverseHello();
        threads[i].start();
        try{
            threads[i].join();
        } catch (InterruptedException e){

        }
    }

    @Override
    public void run(){
        if (i < 50) {
            try{
                createThread(i++);
            } catch (Exception e){

            }
        }
        System.out.println("Hello from Thread " + (i--) + "!");
    }


}
