/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import java.util.*;

/**
 *
 * @author xingxiaoyu
 */
public class EssayClass {
    private int user_id;
    private int class_id;
    private String className;
    private int essayNum;
    private ArrayList<Essay> essays = new ArrayList<Essay>();
    public EssayClass(){
        
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public EssayClass(String className){
        this.className = className;
    }
    public ArrayList<Essay> getEssays() {
        return essays;
    }

    public void setEssays(ArrayList<Essay> essays) {
        this.essays = essays;
    }

    public int getEssayNum() {
        essayNum = essays.size();
        return essayNum;
    }

    public void setEssayNum(int essayNum) {
        this.essayNum = essayNum;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
