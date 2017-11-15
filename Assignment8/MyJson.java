package Assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.*;

public class MyJson {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/lifanqian/IdeaProjects/5100/Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent());
        writeBackToFormerStyle(s, file.getParent());
    }

    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws FileNotFoundException {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        Scanner in = new Scanner(file);
        //delete the line id~webId~category~year~make~model~trim~type~price~photo
        in.nextLine();
        while (in.hasNextLine()){
            String line = in.nextLine();
            String[] vehicleBuilder = new String[10];
            Scanner lineRead = new Scanner(line);
            lineRead.useDelimiter("~");
            for (int i = 0; i < 10; i++){
                if (lineRead.hasNext()){
                    vehicleBuilder[i] = lineRead.next();
                }
            }
            lineRead.close();
            Vehicle vehicle = new Vehicle(vehicleBuilder);
            vehicleList.add(vehicle);
        }
        in.close();
        return vehicleList;
    }

    public static String getJsonString(ArrayList<Vehicle> vehicles){
        HashMap<String, StringBuilder> dealers= new HashMap<>();
        for (Vehicle vehicle: vehicles){
            if (!dealers.containsKey(vehicle.webId)){
                StringBuilder group = new StringBuilder("\"" + vehicle.webId + "\" : [" + "\n");
                dealers.put(vehicle.webId, group);
            }
            StringBuilder attribute = new StringBuilder("{ \n");
            attribute.append("\"id\" : " + "\"" + vehicle.id + "\"" + "\n");
            attribute.append("\"category\" : " + "\"" + vehicle.category + "\"" + "\n");
            attribute.append("\"year\" : " + "\"" + vehicle.year + "\"" + "\n");
            attribute.append("\"make\" : " + "\"" + vehicle.make + "\"" + "\n");
            attribute.append("\"model\" : " + "\"" + vehicle.model + "\"" + "\n");
            attribute.append("\"trim\" : " + "\"" + vehicle.trim + "\"" + "\n");
            attribute.append("\"type\" : " + "\"" + vehicle.model + "\"" + "\n");
            attribute.append("\"price\" : " + "\"" + vehicle.price + "\"" + "\n");
            attribute.append("\"photo\" : " + "\"" + vehicle.photo + "\"" + "\n");
            attribute.append("},\n");
            dealers.get(vehicle.webId).append(attribute);
        }

        StringBuilder res = new StringBuilder("{ \n");
        Set<String> set = dealers.keySet();
        for (String dealer: set){
            StringBuilder vehicleForEachDeal = dealers.get(dealer);
            int length = vehicleForEachDeal.length();
            //delete last comma and add ]
            vehicleForEachDeal.deleteCharAt(length - 2).append("]");
            res.append(vehicleForEachDeal);
        }
        res.append("\n}");

        return res.toString();
    }

    public static void writeToFile(String inputToWrite, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath +"/JsonTest.txt");
        writer.write(inputToWrite);
        writer.flush();
        writer.close();
    }

    //Extra
    public static void writeBackToFormerStyle(String inputToWrite, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath + "/FormerStyleTest.txt");
        StringBuilder original = new StringBuilder();
        HashMap<String, String> dealerCar = new HashMap<>();
        /*I can't find a way to completely recover to the original one from the Json string for now.
        Such like all the vehicles from different dealers are mixed in original file, but I can only get the sorted
        vehicles from same dealer*/
        Scanner in = new Scanner(inputToWrite);
        in.useDelimiter("]|\\[\\n");
        int position = 1;
        String dealer = "";
        while (in.hasNext()) {
            if (position % 2 == 1) {
                String temp = in.next();
                //System.out.println(temp);
                dealer = temp.replaceAll("[ \":{\\n]", "");
                //System.out.println(dealer);
                // to avoid add space or any sign as dealer
                if (dealer.length() > 1) {
                    dealerCar.put(dealer, "");
                }
                position++;
            } else {
                String temp = in.next();
                //System.out.println(temp);
                // similar as above
                    if (temp.length() > 10) {
                        dealerCar.put(dealer, temp);
                    }
            }
        }
        //System.out.println(dealerCar);
        Set<String> sellers = dealerCar.keySet();
        for (String seller : sellers) {
            String Cars = dealerCar.get(seller).replaceAll("[ {\"},]","");
            //System.out.println(test);
            Scanner carReader = new Scanner(Cars);
            int index = 0;
            while (carReader.hasNextLine()){
                if (index %10 == 1) {
                    original.append(seller + "~");
                    index++;
                }
                if (index % 10 == 0 && index != 0){
                    int length = original.length();
                    //delete the last ~
                    original.deleteCharAt(length - 1);
                    original.append("\n");
                }
                String eachLine = carReader.nextLine();
                if (eachLine.length() > 1) {
                    //delete "id","category" ......
                    int colon = eachLine.indexOf(":");
                    String attribution = eachLine.substring(colon + 1);
                    original.append(attribution + "~");
                    index++;
                }
            }
            carReader.close();
        }

        in.close();
        writer.write(original.toString());
        writer.flush();
        writer.close();
    }

}

class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
        switch (cat){
            case "used": return USED;
            case "new": return NEW;
            case "certified": return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}
