package raf.rs.rafnews_webprogramiranje.entities;

public class Category {
    private int id;
    private String name;
    private String description;

    public Category(String name, String description, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category(){}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
