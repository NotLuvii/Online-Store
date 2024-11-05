package unb.cb.customerservice.SharedDataTypes;

public class User {
    private final int user_ID;
    private final String username;
    private final String password;
    private final String userType;

    public User(int user_IDIn, String usernameIn, String passwordIn, String userType){
        this.user_ID = user_IDIn;
        this.username = usernameIn;
        this.password = passwordIn;
        this.userType = userType;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
}
