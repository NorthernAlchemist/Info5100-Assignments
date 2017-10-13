package DessertShop;

public class Cookie extends DessertItem {
    private int number;
    private int price;

    public Cookie(String name, int number, int price){
        super(name);
        this.number = number;
        this.price = price;
    }

    @Override
    public int getCost() {
        double cost = number * price / 12;
        return (int)Math.round(cost);
    }

    @Override
    public String toString(){
        DessertShoppe rookie = new DessertShoppe();
        StringBuilder temp = new StringBuilder();
        temp.append(getNumber() + " @ " + rookie.cents2dollarAndCentsmethod(getPrice()) + " /dz." + "\n");
        temp.append(getName());

        for (int j = getName().length(); j < rookie.getPrintWidth() - String.valueOf(getCost()).length(); j++){
            temp.append(" ");
        }
        temp.append(rookie.cents2dollarAndCentsmethod(getCost()) + "\n");
        return temp.toString();
    }

    public int getNumber(){
        return number;
    }

    public int getPrice(){
        return price;
    }
}
