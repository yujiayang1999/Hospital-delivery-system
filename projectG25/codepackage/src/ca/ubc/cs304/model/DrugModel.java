package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class DrugModel {
    private int medical_id;
    private String ingredient;
    private String storage_detail;

    public DrugModel(int id,String ing,String storage){
        this.medical_id= id;
        this.ingredient =ing;
        this.storage_detail= storage;
    }
    public int getMedical_id() {
        return medical_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getStorage_detail() {
        return storage_detail;
    }


}
