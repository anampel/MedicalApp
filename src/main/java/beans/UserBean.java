package beans;
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


}