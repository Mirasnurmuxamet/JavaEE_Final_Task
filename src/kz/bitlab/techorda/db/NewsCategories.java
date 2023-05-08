package kz.bitlab.techorda.db;

public class NewsCategories {
    private int id;
    private String nameCategory;
    public NewsCategories(){
    }

    public NewsCategories(int id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
