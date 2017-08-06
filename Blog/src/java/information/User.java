package information;

import java.util.*;


public class User {

    private int id;
    private String idName;
    private String name;
    private String password;
    private String photo;
    private String introduction;
    private ArrayList<EssayClass> essayClass = new ArrayList<EssayClass>();
    private ArrayList<Essay> essay = new ArrayList<Essay>();
    private int state = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ArrayList<Essay> getEssay() {
        return essay;
    }

    public void setEssay(ArrayList<Essay> essay) {
        this.essay = essay;
    }

    public ArrayList<EssayClass> getEssayClass() {
        return essayClass;
    }

    public void setEssayClass(ArrayList<EssayClass> essayClass) {
        this.essayClass = essayClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //System.out.print(id);
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
