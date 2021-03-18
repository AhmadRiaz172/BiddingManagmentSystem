package com.example.biddera;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class RegisterProduct {
    private int userid;
    String name;
    String description;
    String image;
    int minbid;
    int category;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCategory(){
        return category;
    }
    public void setCategory( int c ){
        category = c;
    }

    public int getMinbid(){
        return minbid;
    }

    public void setMinbid(int m){
        minbid = m;
    }

    public String getImage(){
        return image;
    }    
    public void setImage( String img ){
        image = img;
    }

    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }

    public void setDescription( String des ){
        description = des;
    }
    public void setName( String n ){
        name = n;
    }



    /**
     * @return int return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

}
