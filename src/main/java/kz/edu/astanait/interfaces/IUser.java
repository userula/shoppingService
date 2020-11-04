package kz.edu.astanait.interfaces;

public interface IUser {
    public void setId(int id);
    public int getId();

    public void setFname(String fname);
    public String getFname();

    public void setLname(String lname);
    public String getLname();

    public void setEmail(String email);
    public String getEmail();

    public void setPassword(String password);
    public String getPassword();
}
