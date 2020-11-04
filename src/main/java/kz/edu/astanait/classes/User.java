package kz.edu.astanait.classes;

import kz.edu.astanait.interfaces.IUser;

public class User implements IUser {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;

    public User(int id, String fname, String lname, String email, String password){
        setId(id);
        setFname(fname);
        setLname(lname);
        setEmail(email);
        setPassword(password);
    }
    public User(String fname, String lname, String email, String password){
        setFname(fname);
        setLname(lname);
        setEmail(email);
        setPassword(password);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
