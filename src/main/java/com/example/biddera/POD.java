package com.example.biddera;

public class POD {
    
    private int id;
    private String name;
    private String desc;
    private int minimum;
    private int current;
    private String endingDate ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }


    /**
     * @return String return the endingDate
     */
    public String getEndingDate() {
        return endingDate;
    }

    /**
     * @param endingDate the endingDate to set
     */
    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
