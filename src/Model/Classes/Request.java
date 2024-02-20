package Model.Classes;
public class Request {
    private String userId;
    private String service;
    private String serviceProviders;
    private boolean status;
    public Request(String userId,String service,String serviceProviders,boolean status){
        this.userId=userId;
        this.service=service;
        this.serviceProviders=serviceProviders;
        this.status=status;
    }

    public String getUserId(){
        return this.userId;
    }
    public String getService(){
        return this.service;
    }
    public String getServiceProviders(){
        return this.serviceProviders;
    }
    public boolean getStatus(){return this.status;}

}
