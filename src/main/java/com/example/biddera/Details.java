package com.example.biddera;

public class Details {
    private int userid;
    private int productid;
    private String category;
    private String name;
    private String description;
    private long maxbid;
    private int numberofbids;
    private String bidendingdate;

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
     * @return long return the maxbid
     */
    public long getMaxbid() {
        return maxbid;
    }

    /**
     * @param maxbid the maxbid to set
     */
    public void setMaxbid(long maxbid) {
        this.maxbid = maxbid;
    }

    /**
     * @return int return the numberofbids
     */
    public int getNumberofbids() {
        return numberofbids;
    }

    /**
     * @param numberofbids the numberofbids to set
     */
    public void setNumberofbids(int numberofbids) {
        this.numberofbids = numberofbids;
    }

    /**
     * @return String return the bidendingdate
     */
    public String getBidendingdate() {
        return bidendingdate;
    }

    /**
     * @param bidendingdate the bidendingdate to set
     */
    public void setBidendingdate(String bidendingdate) {
        this.bidendingdate = bidendingdate;
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


    /**
     * @return int return the productid
     */
    public int getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(int productid) {
        this.productid = productid;
    }

}
