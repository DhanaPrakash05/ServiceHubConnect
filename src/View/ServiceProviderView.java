package View;
import java.util.*;
import Controller.*;
import java.sql.*;
public class ServiceProviderView {
    Scanner sc=new Scanner(System.in);
    ServiceProviderController spc=new ServiceProviderController();
    public void entry(){
        char opt='1';
        while(opt>='1'&&opt<'3'){
            System.out.println("PRESS-1 to Login");
            System.out.println("PRESS-2 to register as a new service provider");
            System.out.print("PRESS-3 to exit the application");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                  login();
                  opt='3';
                  break;
                }
                case '2':{
                    signup();
                    opt='3';
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
     void signup(){
        char opt='1';
        while(opt>='1'&&opt<'4'){
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
            System.out.println("Enter your shift time in format (hour:minutes AM/PM)");
            System.out.println("Enter when your shift begins");
            String startWorkingTime=sc.nextLine();
            System.out.println("Enter when your shift ends");
            String endWorkingTime=sc.nextLine();
            System.out.println("Are you available for work right after you register");
            System.out.println("PRESS 1-YES or 2-NO");
            boolean isAvailable=sc.nextInt()==1?true:false;
            System.out.println("Select your stream of service");
            System.out.println("PRESS-1 for Plumber");
            System.out.println("PRESS-2 for Electrician");
            System.out.println("PRESS-3 for Pet Sitter");
            System.out.println("PRESS-4 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    sc.nextLine();
                    System.out.println("Select the services you can offer");
                    System.out.println("Can you provide all pipe leakage fixing services");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean leakRepair=sc.nextInt()==1?true:false;
                    System.out.println("Can you provide all sort of installation");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean installation=sc.nextInt()==1?true:false;
                    System.out.println("Do you offer drainage cleaning and sewage services");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean drainCleaning=sc.nextInt()==1?true:false;
                    System.out.println("Do you offer to provide overall general inspection and services");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean overallService=sc.nextInt()==1?true:false;
                    String result=spc.signup(username,email,password,mobileNo,address,"Plumber",isAvailable,3,startWorkingTime,endWorkingTime,leakRepair,installation,drainCleaning,overallService);
                    if(result.equals("success")){
                        serviceProviderMenu();
                        opt='4';
                    }
                    else{
                        System.out.println(result);
                    }

                    break;
                }
                case '2':{
                    sc.nextLine();
                    System.out.println("Select the services you can offer");
                    System.out.println("Can you provide wiring installation services");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean wiringInstallation=sc.nextInt()==1?true:false;
                    System.out.println("Can you provide communication wiring services like cellular, wifi connections");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean communication=sc.nextInt()==1?true:false;
                    System.out.println("Do you offer general installation services like generator, ups");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean generalInstallation=sc.nextInt()==1?true:false;
                    System.out.println("Do you provide lighting decorations for special occasions");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean lightingInstallation=sc.nextInt()==1?true:false;
                    System.out.println("Do you offer to repair electronic devices and gadgets");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean electronicalRepairs=sc.nextInt()==1?true:false;
                    String result=spc.signup(username, email, password, mobileNo, address, "Electrician", isAvailable, 3, startWorkingTime, endWorkingTime, wiringInstallation, communication, generalInstallation, electronicalRepairs, lightingInstallation);
                    if(result.equals("success")){
                        serviceProviderMenu();
                        opt='4';
                    }
                    else{
                        System.out.println(result);
                    }
                    break;

                }
                case '3':{
                    sc.nextLine();
                    System.out.println("Select the services you can offer");
                    System.out.println("Can take care of dogs");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean dogService=sc.nextInt()==1?true:false;
                    System.out.println("Can take care of cats");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean catService=sc.nextInt()==1?true:false;
                    System.out.println("Can take care of birds");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean birdService=sc.nextInt()==1?true:false;
                    System.out.println("Can you look out for cattles");
                    System.out.println("PRESS 1-YES or 2-NO");
                    boolean cattleService=sc.nextInt()==1?true:false;
                    String result=spc.signup(username, email, password, mobileNo, address, "PetSitter", isAvailable, 3, startWorkingTime, endWorkingTime, dogService, catService, birdService, cattleService,"");
                    if(result.equals("success")){
                        serviceProviderMenu();
                        opt='4';
                    }
                    else{
                        System.out.println(result);
                    }
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

    void login(){
        char opt='1';
        while(opt>='1'&&opt<'4'){
            sc.nextLine();
            System.out.println("Enter your email");
            String email=sc.nextLine();
            System.out.println("Enter your password");
            String password=sc.nextLine();
            System.out.println("PRESS-1 if you are a Plumber");
            System.out.println("PRESS-2 if you are an Electrician");
            System.out.println("PRESS-3 if you are a petSitter");
            System.out.println("PRESS-4 to exit");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    String result=spc.login(email,password,"Plumber");
                   if(result.equals("successfully logged in")){
                       serviceProviderMenu();
                   }
                   else{
                       System.out.println(result);
                   }
                   break;
                }
                case '2':{
                    String result=spc.login(email,password,"Electrician");
                    if(result.equals("successfully logged in")){
                        serviceProviderMenu();
                        opt='4';
                    }
                    else {
                        System.out.println(result);
                    }
                    break;
                }
                case '3':{
                    String result=spc.login(email,password,"PetSitter");
                    if(result.equals("successfully logged in")){
                        serviceProviderMenu();
                        opt='4';
                    }
                    else {
                        System.out.println(result);
                    }
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

    void serviceProviderMenu(){
        ResultSet rslt=spc.findIfWorkExists();
        System.out.println("Current available works for you");
        if(printRelavantRequests(rslt)>0) {
            int reqId;
            System.out.println("Enter the id of the request to be serviced");
            reqId = sc.nextInt();
            System.out.println("Enter the amount that costs to complete this service");
            int amount = sc.nextInt();
            if(spc.createService(reqId, amount).equals("service booked")){
                System.out.println("service booked");
                contactUser(reqId);
            }
            else{
                System.out.println("Could'nt able to book that service");
            }
        }

    }

    int printRelavantRequests(ResultSet rslt){
        try {
            int c=0;
            System.out.println("+-----------+-----------+-------------------+");
            System.out.println("| Request ID| User ID   | Service           |");
            System.out.println("+-----------+-----------+-------------------+");
            while (rslt.next()) {
                c++;
                long requestId = rslt.getLong("request_id");
                String userId = rslt.getString("user_id");
                String service = rslt.getString("service");
                System.out.printf("| %-9d | %-9s | %-17s |\n", requestId, userId, service);
            }

            System.out.println("+-----------+-----------+--------------");
            return c;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    void contactUser(int reqId){
        char opt='1';
        while(opt>='1'&&opt<'3'){
            System.out.println("PRESS-1 to get customers mobile number");
            System.out.println("PRESS-2 to get customers address");
            System.out.print("PRESS-3 to exit the application");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    System.out.println(spc.getMobileOrAddress(reqId,"mobileNo"));
                    opt='3';
                    break;
                }
                case '2':{
                    System.out.println(spc.getMobileOrAddress(reqId,"Address"));
                    opt='3';
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

}
