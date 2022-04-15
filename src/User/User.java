package User;

import DB.Const;

abstract public class User {
    private String name;
    private String passport;
    private String id;
    private String phone_number;
    private String email;
    private String password;
    private String bik;
    private String table;

    public User(String name, String passport, String id, String phone_number, String email, String bik, String password){
        this.name = name;
        this.passport = passport;
        this.id = id;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.bik = bik;
    }
    public User() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getBik() { return bik; }
    public void setBik(String bik) { this.bik = bik; }
    public String getTable() { return table; }
}
