package Assignment7;

class Root {
    public static void main(String[] args) {

        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device,heat,pressure);

        controller.start();
        heat.start();
        pressure.start();
    }
}


public class Controller extends Thread {
    private Device device;
    private Sensor heat;
    private Sensor pressure;

    public Controller (Device device, Sensor heat, Sensor pressure){
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }

    @Override
    public void run(){
        device.startup();
        synchronized (device) {
            while (heat.getValue() <= 70.0 && pressure.getValue() <= 100) {
                try {
                    System.out.print("hear ->" + (double)Math.round(heat.getValue() * 100) / 100);
                    System.out.println(" , pressure -> " + (double)Math.round(pressure.getValue() * 100) / 100);
                    device.wait();
                } catch (InterruptedException e){

                }
            }
        }
        System.out.print("hear ->" + (double)Math.round(heat.getValue() * 100) / 100);
        System.out.println(" , pressure -> " + (double)Math.round(pressure.getValue() * 100) / 100);
        device.shutdown();
    }


}

class Sensor extends Thread {
    private final Device device;
    private double value = 0.00;

    public Sensor(Device device) {
        this.device = device;
    }

    public double getValue() {
        return value;
    }

    public void updateValue() {
        this.value += 0.01;
    }

    public void run() {
        while(true) {
            synchronized (device) {
                try{
                    updateValue();
                    device.notify();
                    sleep(1);
                } catch (Exception e){

                }
            }
        }
    }
}

class Device{
    public void startup(){
        System.out.println("Device started");
    }

    public void shutdown(){
        System.out.println("Device shutting down due to maintenance");
    }
}
