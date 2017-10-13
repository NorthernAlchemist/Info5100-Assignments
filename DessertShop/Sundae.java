package DessertShop;

public class Sundae extends IceCream {
    private int toppingPrice;
    private String topping;

    public Sundae(String cream, int creamPrice, String topping, int toppingPrice){
        super(cream, creamPrice);
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    @Override
    public int getCost() {
        return super.getCost() + toppingPrice;
    }

    @Override
    public String toString(){
        DessertShoppe Sundae = new DessertShoppe();
        StringBuilder temp = new StringBuilder();
        StringBuilder fullName = new StringBuilder();
        fullName.append(getTopping() + " Sundae with " + getName());

        if (fullName.length() <= 20){
            temp.append(fullName);
            for (int j = fullName.length(); j < Sundae.getPrintWidth() - String.valueOf(getCost()).length(); j++){
                temp.append(" ");
            }
            temp.append(Sundae.cents2dollarAndCentsmethod(getCost()) + "\n");

        } else {
            for (int i = 0; i < fullName.length(); i++){
                if (i == Sundae.getMaxSize()){
                    temp.append("\n");
                }
                temp.append(fullName.charAt(i));
            }

            for (int j = fullName.length() - Sundae.getMaxSize(); j < Sundae.getPrintWidth() - String.valueOf(getCost()).length(); j++){
                temp.append(" ");
            }
            temp.append(Sundae.cents2dollarAndCentsmethod(getCost()) + "\n");
        }

        return temp.toString();
    }

    public String getTopping(){
        return topping;
    }
}
