package Controller;
import Model.RequestDao;
import Model.ServiceDao;
import Model.ServiceProviderDao;
import Model.UserDAO;
import Model.Classes.*;
import java.sql.*;
public class ServiceProviderController {
    ServiceProviderDao dao=new ServiceProviderDao();
    RequestDao requestDao=new RequestDao();
    ServiceDao serviceDao=new ServiceDao();
    UserDAO userDao=new UserDAO();
    public String signup(String name, String email, String password, String mobileNo, String address,
                       String role, boolean isAvailable, int rating,
                       String startWorkingHour, String endWorkingHour,
                       boolean leakRepair, boolean installation, boolean drainCleaning,
                       boolean overallService){
        String result=dao.checkExistingEmailAndMobile(email,mobileNo);
        if(result.equals("Success")) {
            ServiceProvider plumber = Plumber.createPlumber(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour, leakRepair, installation, drainCleaning, overallService);
            dao.signup(plumber);
            return "success";
        }
        else{
            return result;
        }
    }
    public String signup(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour, boolean wiringInstallation, boolean communication, boolean generatorInstallation, boolean electronicalRepairs, boolean lightingInstallation) {
        String result=dao.checkExistingEmailAndMobile(email,mobileNo);
        if(result.equals("Success")) {
        ServiceProvider electrician = Electrician.createElectrician(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour, wiringInstallation, communication, generatorInstallation, electronicalRepairs, lightingInstallation);
        dao.signup(electrician);
        return "success";
        }
        else{
            return result;
        }
    }

    public String signup(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour,
                                boolean dogService, boolean catService, boolean birdsService, boolean cattleService,String dummy){
        String result=dao.checkExistingEmailAndMobile(email,mobileNo);
        if(result.equals("Success")) {
        ServiceProvider petSitter= PetSitter.createPetSitter(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour, dogService, catService, birdsService, cattleService);
        dao.signup(petSitter);
            return "success";
        }
        else{
            return result;
        }

    }

    public String login(String email,String password,String role){
        if (role.equals("Plumber")){
            return dao.loginPlumber(email,password);
        }
        else if(role.equals("Electrician")){
            return dao.loginElectrician(email,password);
        }
        else{
            return dao.loginPetSitter(email,password);
        }
    }

    public ResultSet findIfWorkExists(){
        String serviceProviderId=ServiceProvider.getServiceProviderId();
        return dao.findIfWorkexists(serviceProviderId);

    }

    public String createService(int request_id,int amount){
        Service service=new Service(0,request_id,amount,false);
        if(serviceDao.insertService(service)){
            requestDao.stopDeletionThread(request_id);
            requestDao.updateRequestStatus(request_id);
            requestDao.updateRequestServices(request_id,ServiceProvider.getServiceProviderId());
            return "service booked";
        }
        else{
            return "error accepting service";
        }
    }

    public String getMobileOrAddress(int reqId,String mobileOrAddress){
        if(mobileOrAddress.equals("mobileNo")){
           return userDao.getMobileNo(reqId);
        }
        else{
           return userDao.getAddress(reqId);
        }
    }

}
