package View;
import java.util.*;
import Controller.*;

public class UserView {
    Scanner sc=new Scanner(System.in);
    UserController ctrl=new UserController();
    public void entry(){
        char opt='1';
        while(opt>='1'&&opt<'3'){
            System.out.println("PRESS-1 to Login");
            System.out.println("PRESS-2 to Sign up");
            System.out.print("PRESS-3 to exit the application");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    sc.nextLine();
                    System.out.println("Enter your email");
                    String email = sc.nextLine();
                    System.out.println("Enter your password:");
                    String password = sc.nextLine();
                    String result=ctrl.login(email,password);
                    if(result.equals("Login successful")){
                        if(ctrl.doesUserExist()){
                            System.out.println("your request has been accepted");
                            finishService();
                        }
                        options();
                    }
                    else{
                        System.out.println(result);
                    }
                    break;
                }
                case '2':{
                    sc.nextLine();
                    System.out.println("Enter your username:");
                    String username = sc.nextLine();
                    System.out.println("Enter your email:");
                    String email = sc.nextLine();
                    System.out.println("Enter your password:");
                    String password = sc.nextLine();
                    System.out.println("Enter your mobile number:");
                    String mobileNo = sc.nextLine();
                    System.out.println("Enter your address:");
                    String address = sc.nextLine();
                    String result=ctrl.signup(username,email,password,mobileNo,address);
                    if(result!=null){
                        options();
                    }
                    else{
                        System.out.println("OOPS!! signup failed!! try again..");
                    }
                    break;

                }
                case '3':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }
    }

    void options(){
        char opt='1';
        while(opt>='1'&&opt<'4'){
            System.out.println("PRESS-1 for plumbing services");
            System.out.println("PRESS-2 for booking electical services");
            System.out.println("PRESS-3 for pet sitting services");
            System.out.println("PRESS-4 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                  plumbingServices();
                    break;
                }
                case '2':{
                  electricianServices();
                    break;

                }
                case '3':{
                    petSittingServices();
                    break;
                }
                case '4':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }

    }

    void plumbingServices(){
        char opt='1';
        while(opt>='1'&&opt<'5'){
            System.out.println("PRESS-1 to fix leak repair");
            System.out.println("PRESS-2 for installation services");
            System.out.println("PRESS-3 for drain cleaning");
            System.out.println("PRESS-4 for overall renovation");
            System.out.print("PRESS-5 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    ctrl.getAllServiceProviders("plumbingservices","leakRepair");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '2':{
                    ctrl.getAllServiceProviders("plumbingservices","installation");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '3':{
                    ctrl.getAllServiceProviders("plumbingservices","drainCleaning");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '4':{
                    ctrl.getAllServiceProviders("plumbingservices","overallService");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '5':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }
    }

    void electricianServices(){
        char opt='1';
        while(opt>='1'&&opt<'6'){
            System.out.println("PRESS-1 for wiring installation");
            System.out.println("PRESS-2 for communicational services");
            System.out.println("PRESS-3 for ups or generator installation");
            System.out.println("PRESS-4 for lighting installation");
            System.out.println("PRESS-5 for general repairs");
            System.out.print("PRESS-6 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    ctrl.getAllServiceProviders("electricianservices","wiringInstallation");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '2':{
                    ctrl.getAllServiceProviders("electricianservices","communication");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '3':{
                    ctrl.getAllServiceProviders("electricianservices","generatorInstallation");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '4':{
                    ctrl.getAllServiceProviders("electricianservices","lightingInstallation" );
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '5':{
                    ctrl.getAllServiceProviders("electricianservices","electronicalRepairs");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '6':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }
    }



    void petSittingServices(){
        char opt='1';
        while(opt>='1'&&opt<'5'){
            System.out.println("PRESS-1 for taking care of dogs");
            System.out.println("PRESS-2 for taking care of cats");
            System.out.println("PRESS-3 for taking care of birds");
            System.out.println("PRESS-4 for taking care of cattles");
            System.out.println("PRESS-5 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    ctrl.getAllServiceProviders("petsittingservices","dogService");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '2':{
                    ctrl.getAllServiceProviders("petsittingservices","catService");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '3':{
                    ctrl.getAllServiceProviders("petsittingservices","birdService");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '4':{
                    ctrl.getAllServiceProviders("petsittingservices","cattleService");
                    System.out.println("You will be notified as soon as we find someone to help you");
                    break;
                }
                case '5':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }
    }

    void finishService(){
        char opt='1';
        while(opt>='1'&&opt<'3'){
            System.out.println("PRESS-1 get mobile number of the service provider");
            System.out.println("PRESS_2 to Pay the service provider if the job has completed");
            System.out.print("PRESS-3 to exit the application");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    System.out.println(ctrl.getMobileNoOfServiceProvider());
                    break;
                }
                case '2':{
                    pay();
                    break;

                }
                case '3':{
                    break;
                }
                default:{
                    System.out.println("Invalid input! Please select a valid option");
                    opt='1';
                }
            }
        }
    }

    void pay(){
        sc.nextLine();
        System.out.println("Enter your feedback or review for this service");
        String review =sc.nextLine();
        System.out.println("Enter the rating");
        int rating=sc.nextInt();
        ctrl.updateReviewAndRating(review,rating);
        System.out.println("your review and rating has been recorded");
        int opt;
        do {
            System.out.println("press 1 to get the payment amount");
            opt=sc.nextInt();
            if(opt==1){
                int amount= ctrl.getAmount();
                System.out.println("the amount you need to pay is "+amount);
                System.out.println("Type PAY"+amount+" to finish payment");
                String amt=Integer.toString(amount);
                String input=sc.next();
                if(input.equals("PAY"+amt)){
                    if(ctrl.updateIsPaid()) {
                        System.out.println("Payment was successful");
                        opt=2;
                        System.exit(0);
                    }
                }
                else {
                    System.out.println("something went wrong..try again");
                }
            }
            else{
                System.out.println("Enter a valid input from above");
            }
        }while(opt!=2);
    }


}

