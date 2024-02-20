package Controller;
import java.sql.ResultSet;
import java.util.*;
import Model.Classes.*;
import Model.RequestDao;
import Model.ServiceDao;
import Model.ServiceProviderDao;
import Model.UserDAO;

public class UserController {
    UserDAO udao=new UserDAO();
    RequestDao rqdao=new RequestDao();

    ServiceDao serviceDao=new ServiceDao();
    ServiceProviderDao spdao=new ServiceProviderDao();
    public String signup(String username,String email,String password,String mobNo,String address){
        String isRecordExist=udao.checkExistingEmailAndMobile(email,mobNo);
        if(isRecordExist.equals("Success")) {
            User user = User.createUser(username, email, password, mobNo, address);
            udao.signup(user);
            return user.getUserId();
        }
        else{
            return isRecordExist;
        }
    }
    public String login(String email,String password){
        if(email.matches( "^[A-Za-z0-9_+&*-]+(?:\\.[A-Za-z0-9_+&*-]+)*@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,7}$")){
            if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")){

                return udao.login(email,password);
            }
            else{
                return "invalid password";
            }
        }
        else{
            return "Invalid email";
        }
    }
    public void getAllServiceProviders(String tableName, String column) {
        String serviceProviderId="";

        if (tableName.equals("plumbingservices")) {
            serviceProviderId = "plumbingService_id";
        } else if (tableName.equals("electricianservices")) {
            serviceProviderId = "electricianService_id";
        } else if(tableName.equals("petsittingservices")){
            serviceProviderId = "petSittingService_id";
        }

        ResultSet rslt = null;

        try {
            rslt = spdao.getAvailableServiceProviders(column, tableName, serviceProviderId);
            List<String> resultList = new ArrayList<>();

            while (rslt.next()) {
                String serviceProviderid = rslt.getString("serviceProvider_id");
                resultList.add(serviceProviderid);
            }

            if (!resultList.isEmpty()) {
                Request rqst = new Request(User.getUser().getUserId(), column, resultList.toString().replaceAll("[\\]\\[]",""),false);
                System.out.println(rqdao.createRequest(rqst));
            } else {
                System.out.println("Sorry...currently, we cannot find people who can help with your particular request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rslt != null) {
                    rslt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean doesUserExist(){
        return rqdao.doesUserExist(User.getUser().getUserId());
    }

    public String getMobileNoOfServiceProvider(){
        return spdao.getMobileNo(User.getUser().getUserId());
    }
    public void updateReviewAndRating(String review,int rating){
        serviceDao.addReview(review,User.getUser().getUserId());
        serviceDao.setCompletionStatus(User.getUser().getUserId());
        spdao.updateRating(rating,User.getUser().getUserId());
    }

    public int getAmount(){
        return serviceDao.getAmount(User.getUser().getUserId());
    }
    public boolean updateIsPaid(){
        return serviceDao.updateIsPaid(User.getUser().getUserId());
    }
}
