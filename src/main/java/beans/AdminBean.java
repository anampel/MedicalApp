package beans;

/**
 *Child of UserBean, concerns Admins
 * */
public class AdminBean extends UserBean {
    public String id;
    /**
     *Constructor
     * */
    public AdminBean() {
    }

    /**
     *Getters & Setters
     * */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
