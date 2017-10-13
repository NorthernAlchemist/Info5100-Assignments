package DessertShop;

public class DessertShoppe {
    private String storeName = "M & M Dessert Shoppe";
    private int maxSize = 21;
    private int printWidth = 30;

    public double cents2dollarAndCentsmethod(int cost){
        return cost / (double)100;
    }

    public double getRate(){
        double rate = 0.065;
        return rate;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public int getPrintWidth(){
        return printWidth;
    }
}
