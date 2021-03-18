package com.example.biddera;

public class MyRegisteredProducts {
    private int id;
    private String name;
    private String description;
    private int currentmax;
    private int total;
    private String bidending;
    private String category;
    private int myBid;

    
    public String getCategoryString( int a ){

        if ( a == 1  ){
            return "Electronic devices";
        } else if( a == 2 ){
            return "Electronic accessories";
        } else if ( a == 3 ){
            return "TV and home appliances";
        }else if ( a == 4 ){
            return "Health and beauty";
        }else if ( a == 5 ){
            return "Grocery and pets";
        }else if ( a == 6 ){
            return "Home and Lifestyle";
        }else if ( a == 7 ){
            return "Women fashoio";
        }else if ( a == 8 ){
            return "Men fashion";
        }else if ( a == 9 ){
            return "Watches, Bags and jewelry";
        }else if ( a == 10 ){
            return "Sports and outdoor";
        }else if ( a == 11 ){
            return "Automotive and bikes";
        }else if ( a == 12 ){
            return "other";
        }

        return "unable to resolve type";
    }
    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int return the currentmax
     */
    public int getCurrentmax() {
        return currentmax;
    }

    /**
     * @param currentmax the currentmax to set
     */
    public void setCurrentmax(int currentmax) {
        this.currentmax = currentmax;
    }

    /**
     * @return int return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return String return the bidending
     */
    public String getBidending() {
        return bidending;
    }

    /**
     * @param bidending the bidending to set
     */
    public void setBidending(String bidending) {
        this.bidending = bidending;
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


    /**
     * @return int return the myBid
     */
    public int getMyBid() {
        return myBid;
    }

    /**
     * @param myBid the myBid to set
     */
    public void setMyBid(int myBid) {
        this.myBid = myBid;
    }


    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return String return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
