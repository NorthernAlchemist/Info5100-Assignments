package Assignment6;

import java.util.*;

class Atm{
    private double availableAmountInMachine;
    private double  transactionFee;
    //User and password
    private HashMap<User, String> userData;
    private User currUser;

    public static void main(String args[]){
        User calvin = new User("calvin", 25, "0001 North", "2061231234", "50481234", 10000);
        User abel = new User("abel", 18, "0002 North", "2061234321", "5048367", 5000);
        User mike = new User("mike", 38, "0003 East", "2061235678", "504812198", 7500);

        HashMap<User, String> testList = new HashMap<>();
        testList.put(calvin, "calvin2525");
        testList.put(abel, "abel1818");
        testList.put(mike, "mike3838");

        Atm chase = new Atm(8000, 2.5, testList);
        //try new user first
        chase.process();
    }


    public Atm(double availableAmountInMachine, double transactionFee, HashMap<User, String> userData){
        this.availableAmountInMachine = availableAmountInMachine;
        this.transactionFee =transactionFee;
        this.userData =userData;
    }


//  Tried to store and read all data use the txt file,
//  haven't find ways to match the read content with the console input.
//   public void store(User user, String password){
//         BufferedWriter writer = null;
//        try {
//            FileWriter userData = new FileWriter("userData.txt");
//            writer = new BufferedWriter(userData);
//            writer.write(user.getName() + " ");
//            writer.write(user.getAge() + " ");
//            writer.write(user.getAddress() + " ");
//            writer.write(user.getPhoneNumber() + " ");
//            writer.write(user.getBankAccountNumber() + " ");
//            writer.write(Double.toString(user.getAvailableBalance()) + " ");
//            writer.write(password);
//        } catch (IOException e){
//
//        }finally {
//            try {
//                if (writer != null)
//                    writer.close();
//            } catch (IOException e) {
//
//            }
//        }
//    }
//
//    public List<String> read(){
//        List<String> readIn = new ArrayList<>();
//        FileReader fileReader = null;
//        try{
//            fileReader = new FileReader("userData.txt");
//            char[] a = new char[50];
//            fileReader.read(a);
//
//            for (char c: a){
//                readIn.add(String.valueOf(c));
//            }
//        } catch (IOException e){
//
//        } finally {
//            try{
//                fileReader.close();
//                return readIn;
//            } catch (IOException e){
//
//            }
//        }
//    }

    public void process(){
        boolean newUser = this.isNew();
        boolean isLoggedIn = false;

        if (newUser){
            this.createNewUser();
        }

        isLoggedIn = this.login();

        if (isLoggedIn == false){
            System.out.println("You can reset the password if you forget it. Press 1 to reset:");
            Scanner scanner = new Scanner(System.in);
            int isReset = scanner.nextInt();
            if (isReset == 1){
                this.resetPassword();
            }
        }

        while (isLoggedIn){
            System.out.println("press 1 to see your available balance:");
            System.out.println("press 2 to withdrawal:");
            System.out.println("press 3 to deposit:");
            System.out.println("press 4 for recentTransactions:");
            System.out.println("press 5 to change password:");
            System.out.println("press 0 to exit:");

            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();

            switch (action){
                case 1:
                    System.out.print("Your current balance is : ");
                    System.out.println(this.currUser.getAvailableBalance());
                    break;
                case 2:
                    this.withdrawal();
                    break;
                case 3:
                    this.deposit();
                    break;
                case 4:
                    this.printRecentTransaction();
                    break;
                case 5:
                    this.changePassword();
                    break;
                case 0:
                    isLoggedIn = false;
                    break;
            }

        }

        System.out.print("Thanks for using!");

    }

    public boolean isNew(){
        System.out.println("Are you a 1.Current user or 2.New user?");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        switch (index){
            case 1:
                return false;
            case 2:
                return true;
            default:
                System.out.println("Invalid input!");
                return this.isNew();
        }
    }

    public void createNewUser(){
        //too much code if add and check input one by one, just give a example here
        StringBuilder accountNumber = new StringBuilder(5408);
        Random random = new Random();
        int addOnNumber = random.nextInt(500);
        accountNumber.append(addOnNumber);
        //unique accountNumber
        for (User user: this.getUserList()){
            while(user.getBankAccountNumber().equals(accountNumber.toString())){
                accountNumber.append(addOnNumber);
            }
        }
        User evan = new User("evan", 27, "1011 NE 56th", "2061111111", accountNumber.toString(), 10000);
        this.userData.put(evan, "evan2727");
        System.out.println("An example account has been created with random accountNumber and password.");
        System.out.println("Please try current user.");
    }

    public boolean login(){
        System.out.println("accountNumber: ");
        Scanner scanner = new Scanner(System.in);
        String accountNumber = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();

        for (User user: this.getUserList()){
            while(user.getBankAccountNumber().equals(accountNumber)){
                if (userData.get(user).equals(password)){
                    currUser = user;
                    return true;
                } else {
                    System.out.println("Your account number or password is not correct!");
                    break;
                }
            }
        }
        System.out.println("Your account number or password is not correct!");
        return false;
    }

    public void withdrawal(){
        System.out.println("Withdrawal amount: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        if (amount <= 0){
            System.out.println("Please chose a positive number!");
            return;
        }

        if (amount + this.transactionFee <= this.currUser.getAvailableBalance()
            && amount <= this.availableAmountInMachine){
            this.currUser.setAvailableBalance(this.currUser.getAvailableBalance() - amount - this.transactionFee);
            this.setAvailableAmountInMachine(this.availableAmountInMachine - amount);
            this.currUser.getRecentTransaction().add("withdrawal: " + amount + "    transactionFee: " + this.transactionFee);
        } else if (amount + this.transactionFee > this.currUser.getAvailableBalance()){
            System.out.println("You don't have enough balance in your account!");
        } else {
            System.out.println("This machine doesn't have enough cash!");
        }

    }

    public void deposit(){
        System.out.print("Deposit amount: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        if (amount <= 0){
            System.out.println("Please chose a positive number!");
            return;
        }
        this.currUser.setAvailableBalance(this.currUser.getAvailableBalance() + amount);
        this.setAvailableAmountInMachine(this.availableAmountInMachine + amount);
        this.currUser.getRecentTransaction().add("deposit: " + amount);
    }

    public void printRecentTransaction(){
        List<String> history = this.currUser.getRecentTransaction();
        if (history.size() <= 10){
            for (int i = history.size() - 1; i >= 0; i--){
                System.out.println(history.get(i));
            }
        }

        if (history.size() > 10){
            for (int i = history.size() - 1; i >= history.size() - 10; i--){
                System.out.println(history.get(i));
            }
        }
    }

    public void resetPassword(){
        System.out.print("Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("age: ");
        int age = scanner.nextInt();
        System.out.print("phone number: ");
        String phoneNumber = scanner.nextLine();
        Boolean isUser = false;

        for (User user: this.getUserList()) {
            if (user.getName().equals(name)) {
                if (user.getAge() == age) {
                    if (user.getPhoneNumber().equals(phoneNumber)) {
                        isUser = true;
                        this.currUser = user;
                    }
                }
            }
        }

        if (isUser){
            System.out.print("new password: ");
            String newPassword = scanner.nextLine();
            this.userData.put(this.currUser, newPassword);
            return;
        }

        System.out.println("Can't find corresponding users!");
    }

    public void changePassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("new password: ");
        String newPassword = scanner.nextLine();
        this.userData.put(this.currUser, newPassword);
    }


    public void setAvailableAmountInMachine(double num){
        this.availableAmountInMachine = num;
    }


    public Set<User> getUserList() {
        return userData.keySet();
    }
}
