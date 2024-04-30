package org.todolist.model;
public class User {
    private int userid ;// Change id to userid
    private String todousername;
    private String passwd;
    private String confirmpasswd;
    private String email;
    private String gender;
     private int  tasknewno;


    public User(int userid, String todousername, String passwd, String email, String gender) {
       this.userid= this.userid;
        this.todousername = this.todousername;
        this.passwd = this.passwd;
        this.confirmpasswd = this.confirmpasswd;
        this.email = this.email;
        this.gender = this.gender;

    }



    // Getters and setters for each field
//    public int getUserid() { return userid; } // Change getId to getUserid
//    public void setUserid(int userid) { this.userid = userid; } // Change setId to setUserid
//
//    public String getTodousername() { return todousername; }
//    public void setTodousername(String todousername) { this.todousername = todousername; }
//
//    public String getPasswd() { return passwd; }
//    public void setPasswd(String passwd) { this.passwd = passwd; }
//
//    public String getEmail() { return email; }
//
//    public void setEmail(String email) { this.email = email; }
//
//    public String getGender() { return gender; }
//    public void setGender(String gender) { this.gender = gender; }
//
//
//    public String getConfirmpasswd() {
//        return confirmpasswd;
//    }
//
//    private void setConfirmpasswd(String confirmpasswd) {
//        this.confirmpasswd = confirmpasswd;
//    }
}
