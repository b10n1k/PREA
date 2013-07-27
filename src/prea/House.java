/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prea;

/**
 *
 * @author j0ni
 */
public class House extends Property{
    
    private String beds;
    private String location;
  
    /**
     * Constructor
     */
    public House(){
       
    }

    /**
     * Set the number of beds as a string
     * @param floors literal number of beds
     */
    public void setBeds(String beds){
        this.beds=beds;
    }

    /**
     * Set the location of property
     * @param location the location of property
     */
    public void setLocation(String location){
        this.location=location;
    }

    /**
     * Retrieve a String with the literal number of beds
     * @return literal number of beds
     */
    public String getBeds(){
        return beds;
    }

    /**
     * Retrieve a String with the location of property
     * @return the location of property
     */
    public String getLocation(){
        return location;
    }


    /**
     * This method inserts the data in the property 's fields
     *
     * @param data an array which include the property data
     */
    public void insertData(String[] data) {
            int i=0;
	while(i<data.length){

    propertyID=data[i];
++i;
    propertyKind=data[i];
++i;
    propertyPrice=data[i];
++i;
    propertyArea=data[i];
    ++i;
    beds=data[i];
    ++i;
    location=data[i];
++i;
    propertyAvailability=data[i];
    ++i;

    }
    }

     @Override
     /**
     * Print informations about Property
     */
    public String toString(){
        return super.toString()+
                "Beds : "+beds+"\n"+
                "Location : "+location+"\n";
    }


}
