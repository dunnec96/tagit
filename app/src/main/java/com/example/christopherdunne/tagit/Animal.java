package com.example.christopherdunne.tagit;

/**
 * Created by christopherdunne on 28/02/2018.
 */

public class Animal {
    private  String TagNo;
    private  String Dam;
    private  String Sire;
    private  String DateOfBirth;
    private  String Sex;


    public Animal() {
    }

    public  Animal(String tagNo,String dam,String sire,String dateOfBirth,String sex) {
        TagNo = tagNo;
        Dam = dam;
        Sire = sire;
        DateOfBirth = dateOfBirth;
        Sex = sex;

    }

    public String getTagNo() {
        return TagNo;
    }

    public void setTagNo(String tagNo) {
        TagNo = tagNo;
    }

    public String getDam() {
        return Dam;
    }
    public void setDam(String dam) {
        Dam = dam;
    }

    public String getSire() {
        return Sire;
    }
    public void setSire(String sire) {
        Sire = sire;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
    public void setDate(String date) {
       DateOfBirth = date;
    }

    public String getSex() {
        return Sex;
    }
    public void setSex(String sex) {
        Sex = sex;
    }

}
