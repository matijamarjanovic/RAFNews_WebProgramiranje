package raf.rs.rafnews_webprogramiranje.entities;

public class User {

    private int id;
    private String email;
    private String name;
    private String surname;
    private boolean admin; //tip korisnika -> ako je admin, boolean = true
    private boolean status;
    private String hashedPassword;

    public User(int id, String email, String name, String surname, boolean admin, boolean status, String hashedPassword) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.admin = admin;
        this.status = status;
        this.hashedPassword = hashedPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }
}
