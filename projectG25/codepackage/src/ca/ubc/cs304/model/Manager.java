package ca.ubc.cs304.model;

public class Manager {

    private int manager_id;
    private int medical_id;
    private String phone_num;
    public int getManager_id() {
        return manager_id;
    }

    public int getMedical_id() {
        return medical_id;
    }

    public String getPhone_num() {
        return phone_num;
    }


    public Manager(int manager_id, int medical_id, String phone_num) {
        this.manager_id = manager_id;
        this.medical_id = medical_id;
        this.phone_num = phone_num;
    }


}
