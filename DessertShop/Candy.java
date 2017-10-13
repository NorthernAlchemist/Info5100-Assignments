package DessertShop;

public class Candy extends DessertItem{
    private double pounds;
    private int price;

    public Candy(String name, double pounds, int price){
        super(name);
        this.pounds = pounds;
        this.price = price;
    }

    @Override
    public int getCost() {
        double cost = pounds * price;
        return (int)Math.round(cost);
    }

    @Override
    public String toString(){
        DessertShoppe candy = new DessertShoppe();
        StringBuilder temp = new StringBuilder();
        temp.append(getPounds() + " lbs. @ " + candy.cents2dollarAndCentsmethod(getPrice()) + " /lb." + "\n");
        temp.append(getName());

        for (int j = getName().length(); j < candy.getPrintWidth() - String.valueOf(getCost()).length(); j++){
            temp.append(" ");
        }
        temp.append(candy.cents2dollarAndCentsmethod(getCost()) + "\n");
        return temp.toString();
    }

    public double getPounds(){
        return pounds;
    }

    public int getPrice(){
        return price;
    }

}
