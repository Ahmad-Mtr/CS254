```java
package Registration;

import java.util.ArrayList;

public class User {

    private String fullName;
    private String userName;
    private String password;
    private String major;
    private ArrayList<String> skills;
    private String gender;
    private String email;

    public User() {
        userName = "";
        password = "";
        fullName = "";
        gender = "";
        email = "";
        major = "";
        skills = new ArrayList<String>();
    }

    public boolean equals(Object o) {
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
        /* Check if o is an instance of User or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }

        // typecast o to User so that we can compare data members 
        User usr = (User) o;

        // Compare the data members and return accordingly 
        return userName.equals(usr.getUserName());
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

```