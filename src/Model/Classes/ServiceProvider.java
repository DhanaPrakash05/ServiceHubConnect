package Model.Classes;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public abstract class ServiceProvider {
    private static String serviceProviderId = null;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private String address;
    private boolean isAvailable;
    private LocalTime startWorkingHour;
    private LocalTime endWorkingHour;
    private String role;
    private int rating;

    protected ServiceProvider(String name, String email, String password, String mobileNo, String address, String role,boolean isAvailable,int rating,String startWorkingHour, String endWorkingHour) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setMobileNo(mobileNo);
        setAddress(address);
        setStartWorkingHour(startWorkingHour);
        setEndWorkingHour(endWorkingHour);
        setIsAvailable(isAvailable);
        setRating(rating);
        setRole(role);
    }

    // Setters...
    public void setName(String name){
        if(name!=null&&!name.equals("")) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public void setEmail(String email){
        if(email.matches( "^[A-Za-z0-9_+&*-]+(?:\\.[A-Za-z0-9_+&*-]+)*@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,7}$")) {
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void setPassword(String password){
        if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            this.password = password;
        }
        else{
            throw new IllegalArgumentException("Invalid password format");
        }
    }

    public void setMobileNo(String mobileNo){
        if(mobileNo.matches("^[0-9]{10}$")) {
            this.mobileNo = mobileNo;
        }
        else{
            throw new IllegalArgumentException("Invalid mobile number format");
        }
    }

    public void setAddress(String address){
        if(address!=null&&!address.equals("")) {
            this.address = address;
        }
        else{
            throw new IllegalArgumentException("Invalid address");
        }
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable=isAvailable;
    }

    public void setRating(int rating){
        this.rating=rating;
    }

    public void setRole(String role){
        this.role=role;
    }
    public void setStartWorkingHour(String startWorkingHour) {
        if (!startWorkingHour.contains("AM")&&!startWorkingHour.contains("PM")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.startWorkingHour = LocalTime.parse(startWorkingHour, formatter);
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
            this.startWorkingHour = LocalTime.parse(startWorkingHour, formatter);
        }
    }

    public void setEndWorkingHour(String endWorkingHour) {
        if (!endWorkingHour.contains("AM")&&!endWorkingHour.contains("PM")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.endWorkingHour = LocalTime.parse(endWorkingHour, formatter);
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
            this.endWorkingHour = LocalTime.parse(endWorkingHour, formatter);
        }
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    // Getters...
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public LocalTime getStartWorkingHour() {
        return startWorkingHour;
    }

    public LocalTime getEndWorkingHour() {
        return endWorkingHour;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public int getRating() {
        return rating;
    }

    public String getRole() {
        return role;
    }
    public static String getServiceProviderId() {
        return serviceProviderId;
    }

    //methods to be overriden
    public void setLeakRepair(){

    }
    public void setInstallation(){

    }
    public void setDrainCleaning(){

    }
    public void setOverAllService(){

    }
    public boolean getLeakRepair(){
        return true;
    }
    public boolean getInstallation(){
        return true;
    }

    public boolean getDrainCleaning() {
        return true;
    }

    public boolean getOverAllService() {
        return true;
    }


    public void setDogService() {

    }

    public void setCatService() {

    }

    public void setBirdsService() {

    }

    public void setCattleService() {

    }

    public boolean getDogService() {
        return true;
    }

    public boolean getCatService() {
        return true;
    }

    public boolean getBirdsService() {
        return true;
    }
    public boolean getCattleService() {
        return true;
    }
    public void setWiringInstallation() {

    }
    public void setCommunication() {

    }
    public void setGeneratorInstallation() {

    }
    public void setElectronicalRepairs() {

    }
    public void setLightingInstallation() {

    }
    public boolean getWiringInstallation() {
        return true;
    }
    public boolean getCommunication() {
        return  true;
    }
    public boolean getGeneratorInstallation() {
        return  true;
    }
    public boolean getElectronicalRepairs() {
        return  true;
    }
    public boolean getLightingInstallation() {
        return  true;
    }

}
