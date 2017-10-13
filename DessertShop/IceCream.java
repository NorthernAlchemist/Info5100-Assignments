package DessertShop;

public class IceCream extends DessertItem {
    private int price;

    public IceCream(String name, int price){
        super(name);
        this.price = price;
    }

    @Override
    public int getCost() {
        return price;
    }

    @Override
    public String toString(){
        DessertShoppe iceCream = new DessertShoppe();
        StringBuilder temp = new StringBuilder();
        temp.append(getName());

        for (int j = getName().length(); j < iceCream.getPrintWidth() - String.valueOf(getCost()).length(); j++){
            temp.append(" ");
        }
        temp.append(iceCream.cents2dollarAndCentsmethod(getCost()) + "\n");

        return temp.toString();
    }

}
