package View;
import java.util.*;
public class Menu {
    Scanner sc=new Scanner(System.in);
    UserView uv=new UserView();
    ServiceProviderView sv=new ServiceProviderView();

    public void start(){
        char opt='1';
        while(opt>='1'&&opt<'3'){
            System.out.println("Are you a service provider or a general user?");
            System.out.println("PRESS-1 for user");
            System.out.println("PRESS-2 for service_provider");
            System.out.print("PRESS-3 to exit the application");
            opt=sc.next().charAt(0);
            switch(opt){
                case '1':{
                    uv.entry();
                    break;
                }
                case '2':{
                    sv.entry();
                    break;
                }
                case '3':{
                    System.exit(0);
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
