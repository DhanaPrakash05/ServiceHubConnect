
package Model.Classes;


public class User {

    private static User user=null;
    private String userId=null;
    private String username;
    private String email;
    private String password;
    private String mobileNo;
    private String address;
   private User(String username,String email,String password,String mobileNo,String address){
       setUsername(username);
       setEmail(email);
       setPassword(password);
       setMobileNo(mobileNo);
       setAddress(address);
   }


//   Setters
   public void setUsername(String username){
       if(username!=null&&!username.equals("")) {
           this.username = username;
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
   public void setAddress(String address){
       if(address!=null&&!address.equals("")) {
           this.address = address;
       }
       else{
           throw new IllegalArgumentException("Invalid address");
       }
   }

   public void setUserId(String userId){
       this.userId=userId;
   }
   public void setMobileNo(String mobileNo){
       if(mobileNo.matches("^[0-9]{10}$")) {
           this.mobileNo = mobileNo;
       }
       else{
           throw new IllegalArgumentException("Invalid mobile number format");
       }
   }
//   Getters
    public String getUsername(){
       return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public String getUserId(){
       return this.userId;
    }

    public static User createUser(String username, String email, String password, String mobileNo, String address) {
        user= new User(username, email, password, mobileNo, address);
        return user;
    }

    public static User getUser(){
      if(user!=null){
          return user;
      }
      else{
          throw new IllegalArgumentException("User not yet created");
      }
    }

}

