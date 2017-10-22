package Assignment6;

import java.util.ArrayList;
import java.util.List;

public class User {

        private String name;
        private int age;
        private String address;
        private String phoneNumber;
        private String bankAccountNumber;
        private double availableBalance;
        private List<String> recentTransaction = new ArrayList<>();

        public User(String name, int age, String address, String phoneNumber, String bankAccountNumber, double availableBalance) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.bankAccountNumber = bankAccountNumber;
            this.availableBalance = availableBalance;
        }

        public int getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getBankAccountNumber() {
            return bankAccountNumber;
        }


        public String getName() {
            return name;
        }


        public double getAvailableBalance() {
            return availableBalance;
        }


        public List<String> getRecentTransaction() {
            return recentTransaction;
        }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
}
