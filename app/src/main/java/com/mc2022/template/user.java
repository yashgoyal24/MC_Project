package com.mc2022.template;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Entity(tableName = "user")
public class user implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="fname")
    private String fname;
    @ColumnInfo(name="lname")
    private String lname;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="phoneno")
    private String phoneno;
    @ColumnInfo(name="pswd")
    private String pswd;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPhoneno() {
        return phoneno;
    }



}
