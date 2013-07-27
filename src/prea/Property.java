/*
 * the super abstract class Property provide
 * the basic functionality to store any property.
 * 
 */

package prea;

/**
 * @author j0ni
 * @version 1.0
 */


public abstract class Property {

    protected String propertyID;
    protected String propertyKind;
    protected String propertyPrice;
    protected String propertyArea;
    protected String propertyAvailability;  //sold or for sale

    /**
     * Set price
     * @param price the price
     */
    public void setPrice(String price){
        float temp=Float.parseFloat(price);
        if(temp>=0) propertyPrice=price;
    }

    /**
     *
     * @param av
     */
    public void setAvailability(String av) {
        propertyAvailability=av;
    }

    /**
     *
     * @param kind
     */
    public void setKind(String kind) {
        propertyKind=kind;
    }

    /**
     *
     * @param id
     */
    public void setID(String id) {
        propertyID=id;
    }

    /**
     *
     * @param area
     */
    public void setArea(String area){
        propertyArea=area;
    }

    /**
     *
     * @return
     */
    public String getPrice() {
        return propertyPrice;
    }

    /**
     *
     * @return
     */
    public String getAvailability() {
        return propertyAvailability;
    }

    /**
     *
     * @return
     */
    public String getKind() {
        return propertyKind;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return propertyID;
    }

    /**
     *
     * @return
     */
    public String getArea(){
        return propertyArea;
    }

    /**
     *
     * @return
     */
   abstract public String getLocation();

     @Override
     /**
     * Print informations about Property
     */
    public String toString(){
        return "PREA property\n< " +
                 "Property ID: "+propertyID +"\n" +
                 "Type : "+propertyKind+"\n" +
                 "Availability: "+propertyAvailability + "\n" +
                 "Price: "+propertyPrice+"\n"+
                 "Area(sq.m.) : "+ propertyArea+"\n";
    }

}
