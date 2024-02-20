package Model.Classes;

public class Plumber extends ServiceProvider {

    private static Plumber plumber=null;
    private boolean leakRepair;
    private boolean installation;
    private boolean drainCleaning;
    private boolean overAllService;

    private Plumber(String name, String email, String password, String mobileNo, String address, String role,boolean isAvailable,int rating,String startWorkingHour, String endWorkingHour,boolean leakRepair,boolean installation,boolean drainCleaning,boolean overAllService){
        super(name,email, password, mobileNo, address, role,isAvailable,rating,startWorkingHour,endWorkingHour);
       setLeakRepair(leakRepair);
       setInstallation(installation);
       setDrainCleaning(drainCleaning);
       setOverAllService(overAllService);
    }
    public void setLeakRepair(boolean leakRepair){
        this.leakRepair=leakRepair;
    }
    public void setInstallation(boolean installation){
        this.installation=installation;
    }
    public void setDrainCleaning(boolean drainCleaning){
        this.drainCleaning=drainCleaning;
    }
    public void setOverAllService(boolean overAllService){
        this.overAllService=overAllService;
    }
    public boolean getLeakRepair(){
        return this.leakRepair;
    }
    public boolean getInstallation() {
        return this.installation;
    }

    public boolean getDrainCleaning() {
        return this.drainCleaning;
    }

    public boolean getOverAllService() {
        return this.overAllService;
    }

    public static Plumber createPlumber(String name, String email, String password, String mobileNo, String address, String role,boolean isAvailable,int rating,String startWorkingHour, String endWorkingHour,boolean leakRepair,boolean installation,boolean drainCleaning,boolean overAllService){
       plumber=new Plumber(name, email, password, mobileNo, address, role,isAvailable,rating,startWorkingHour, endWorkingHour,leakRepair,installation,drainCleaning,overAllService);
        return plumber;
    }
    public static Plumber getPlumber(){
        if(plumber!=null){
            return plumber;
        }
        else {
            throw new IllegalArgumentException("Plumber not yet created");
        }
    }
}
