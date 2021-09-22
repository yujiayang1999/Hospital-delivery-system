package ca.ubc.cs304.model;

public class Drugmanager {
    private int medical_id;

    public int getMedical_id() {
        return medical_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getStorage_detail() {
        return storage_detail;
    }

    public int getManager_id() {
        return manager_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    private String ingredient;
    private String storage_detail;
    private int manager_id;
    private String phone_num;
    public Drugmanager(int medical_id, String ingredient, String storage_detail, int manager_id, String phone_num) {
        this.medical_id = medical_id;
        this.ingredient = ingredient;
        this.storage_detail = storage_detail;
        this.manager_id = manager_id;
        this.phone_num = phone_num;
    }
}
