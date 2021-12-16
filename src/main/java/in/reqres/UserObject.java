package in.reqres;
/*
Object for users data
 */
public class UserObject {
    /*
    Object properties
     */
    private String userName;
    private String userJob;
    private String userId;
    /*
    The constance of properties in JSON body
     */
    public static String USER_JSON_NAME = "name";
    public static String USER_JSON_JOB = "job";
    public static String USER_JSON_ID = "id";

    /*
    Getters
     */
    public String getUserName() {
        return userName;
    }

    public String getUserJob() {
        return userJob;
    }

    public String getUserId() {
        return userId;
    }
    /*
    Setters
     */
    public void setUserName(String userName) {
        if(userName != null) {
            this.userName = userName;
        }else this.userName = "";

    }

    public void setUserJob(String userJob) {
        if(userJob != null)
            this.userJob = userJob;
        else
            this.userJob = "";
    }

    public void setUserId(String userId) {

        this.userId = userId;

    }
/*
Constructor
 */
    public UserObject(){

    }
/*
Method return boolean , if user exist on server (have initialised Id ) method return true
 */
    public boolean isUserCreatedOnServer(){
        if(userId != null && userId.length() > 0) return true;
        return  false;
    }

}
