package Model.Classes;

public class Service {
    private int serviceId;
    private int requestId;
    private int amount;
    private boolean isCompleted;

    public Service(int serviceId,int requestId,int amount, boolean isCompleted){
        this.serviceId=serviceId;
        this.requestId=requestId;
        this.amount=amount;
        this.isCompleted=isCompleted;
    }

    public void setServiceId(int serviceId){
        this.serviceId=serviceId;
    }
    public int getServiceId(){
        return serviceId;
    }
    public int getRequestId(){
        return this.requestId;
    }
    public int getAmount(){
        return this.amount;
    }
    public boolean isCompleted(){
        return this.isCompleted;
    }
}
