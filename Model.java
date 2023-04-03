package TestingPac;

public class Model {
    int id;
    String name;
    String gender;
    boolean status;
    Model(){
        id=0;
        name=null;
        gender=null;
        status=true;
    }

    public Model(int id, String name, String gender, boolean status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void getData(){
        System.out.println(getId()+"\t"+getName()+"\t"+getGender()+"\t"+isStatus());
    }
}
