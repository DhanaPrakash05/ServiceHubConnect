package Model.Classes;

public class Electrician extends ServiceProvider {
    private static Electrician electrician=null;
    private boolean wiringInstallation;
    private boolean communication;
    private boolean generatorInstallation;
    private boolean electronicalRepairs;
    private boolean lightingInstallation;
    Electrician(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour, boolean wiringInstallation, boolean communication, boolean generatorInstallation,
                boolean electronicalRepairs, boolean lightingInstallation){
        super(name,email, password, mobileNo, address, role,isAvailable,rating,startWorkingHour,endWorkingHour);
        setWiringInstallation(wiringInstallation);
        setCommunication(communication);
        setGeneratorInstallation(generatorInstallation);
        setElectronicalRepairs(electronicalRepairs);
        setLightingInstallation(lightingInstallation);
    }
    public void setWiringInstallation(boolean wiringInstallation) {
        this.wiringInstallation = wiringInstallation;
    }
    public void setCommunication(boolean communication) {
        this.communication = communication;
    }
    public void setGeneratorInstallation(boolean generatorInstallation) {
        this.generatorInstallation = generatorInstallation;
    }
    public void setElectronicalRepairs(boolean electronicalRepairs) {
        this.electronicalRepairs = electronicalRepairs;
    }
    public void setLightingInstallation(boolean lightingInstallation) {
        this.lightingInstallation = lightingInstallation;
    }
    public boolean getWiringInstallation() {
        return wiringInstallation;
    }
    public boolean getCommunication() {
        return communication;
    }
    public boolean getGeneratorInstallation() {
        return generatorInstallation;
    }
    public boolean getElectronicalRepairs() {
        return electronicalRepairs;
    }
    public boolean getLightingInstallation() {
        return lightingInstallation;
    }
    public static Electrician createElectrician(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour, boolean wiringInstallation, boolean communication, boolean generatorInstallation, boolean electronicalRepairs, boolean lightingInstallation) {
        electrician = new Electrician(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour, wiringInstallation, communication, generatorInstallation, electronicalRepairs, lightingInstallation);
        return electrician;
    }
    public static Electrician getElectrician(){
        if(electrician!=null){
            return electrician;
        }
        else {
            throw new IllegalArgumentException("Electrician not yet created");
        }
    }
}
