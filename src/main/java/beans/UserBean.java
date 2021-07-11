package beans;

import Utils.TrafficCounter;

import java.util.ArrayList;
import java.util.List;

/**
 *Initialize the user's values and reproduce them to its children
 * */
public class UserBean {
        String username;
        String name;
        String surname;
        String phone;
        String role;
        String password;
        public static final String regexUsername = "^[a-zA-Z][a-zA-Z0-9_]{6,19}$";
        public static final String regexPhone = "\"^\\\\d{10}$\"$";

    /**
     *Constructor
     * */

    public UserBean() {
    }

    /**
     *Getters & Setters
     * */
    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Users' sign in validation
     * @param username The username of the user
     * @param phone The phone of the user */
    public boolean signinValidation(String username, String phone){
        boolean valid;
        try{
            if (username.matches(regexUsername)) {
                System.out.println("Valid username");
                valid = true;
            } else {
                System.out.println("Invalid username");
                valid = false;
            }
            if (phone.matches(regexPhone)) {
                System.out.println("Valid phone");
                valid = true;
            } else {
                System.out.println("Invalid phone");
                valid = false;
            }
        }catch (NullPointerException e)
        {
            System.out.println("Required username and phone");
            valid = false;
        }
        return valid;
    }
    /**
     * Users' log in validation
     * @param username The username of the user
     * @param phone The phone of the user */
    public boolean login(String username, String phone) {
        if (username != null && username != " " &&
               phone != null && phone != " ") {
            setUsername(username);
            setPhone(phone);
            return true;
        } else {
            return false;
        }
    }
    /**
     *Users logout
     * No parameters
     * */
    public void logout(){

    }
    /**
     * Creates an Array list with username elements
     * No parameters
     * */
    public ArrayList getUsernameList(){
        List<String> list= new ArrayList<>();
        list.add(getUsername());
        return (ArrayList) list;
    }

}