package DessertShop;

import java.text.DecimalFormat;
import java.util.Vector;

public class Checkout {
    private Vector<DessertItem> DessertList = new Vector<>();
    private DessertShoppe shoppe = new DessertShoppe();

    public Checkout(){

    }

    public int numberOfItems(){
        return DessertList.size();
    }

    public void enterItem(DessertItem item){
        DessertList.add(item);
    }

    public void clear(){
        DessertList.removeAllElements();
    }

    public int totalCost(){
        int totalCost = 0;
        for (int i = 0; i < DessertList.size(); i++){
            totalCost += DessertList.get(i).getCost();
        }
        return totalCost;
    }

    public int totalTax(){
        return (int)Math.round(totalCost() * shoppe.getRate());
    }

    @Override
    public String toString(){

        StringBuilder receipt = new StringBuilder();

        //print store name and -
        for (int i = 0; i< (shoppe.getPrintWidth() - shoppe.getStoreName().length()) / 2; i++){
            receipt.append(" ");
        }
        receipt.append(shoppe.getStoreName());
        for (int i = (shoppe.getPrintWidth() + shoppe.getStoreName().length()) / 2; i < shoppe.getPrintWidth(); i++){
            receipt.append(" ");
        }
        receipt.append("\n");
        for (int i = 0; i< (shoppe.getPrintWidth() - shoppe.getStoreName().length()) / 2; i++){
            receipt.append(" ");
        }
        for (int i = 0; i < shoppe.getStoreName().length(); i++){
            receipt.append("-");
        }
        for (int i = (shoppe.getPrintWidth() + shoppe.getStoreName().length()) / 2; i < shoppe.getPrintWidth(); i++){
            receipt.append(" ");
        }
        receipt.append("\n");
        receipt.append("\n");

        //print each item
        for (int i = 0; i < DessertList.size(); i++){
                receipt.append(DessertList.get(i).toString());
        }

        //Tax
        receipt.append("\n");
        receipt.append("Tax");

        if (totalTax() < 100){
            DecimalFormat df = new DecimalFormat("#.00");
            for(int i = 0; i < shoppe.getPrintWidth() - 5; i++){
                receipt.append(" ");
            }
            double tax = (shoppe.cents2dollarAndCentsmethod(totalTax())) % 1;
            receipt.append(df.format(tax));
        } else {
            for(int i = 0; i < shoppe.getPrintWidth() - String.valueOf(totalTax()).length() - 3; i++){
                receipt.append(" ");
            }
            receipt.append(shoppe.cents2dollarAndCentsmethod(totalTax()));
        }

        //Total Cost
        receipt.append("\n");
        receipt.append("Total Cost");
        for(int i = 0; i < shoppe.getPrintWidth() - String.valueOf(totalCost()).length() - 10; i++){
            receipt.append(" ");
        }
        receipt.append(shoppe.cents2dollarAndCentsmethod(totalCost()) + shoppe.cents2dollarAndCentsmethod(totalTax()));
        receipt.append("\n\n");

        //return
        return receipt.toString();
    }
}
