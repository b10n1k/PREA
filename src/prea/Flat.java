/*
 * Class Flat provide functionality for subcategory of Property
 * respond to flats
 */
package prea;

/**
 *
 * @author j0ni
 * @version 1.0
 */
public class Flat extends Property{

    private String floors;
    private String beds;
    private String location;
    private String shortInfo;

    /**
     * Constructor
     */
    public Flat(){

    }

    /**
     * Set the number of beds as a string
     * @param beds literal number of beds
     */
    public void setBeds(String beds){
        this.beds=beds;
    }

    /**
     * Set the number of floors as a string
     * @param floors literal number of floors
     */
    public void setFloors(String floors){
        this.floors=floors;
    }

    /**
     * Retrieve a String with the literal number of beds
     * @return literal number of beds
     */
    public String getBeds(){
        return beds;
    }

    /**
     * Retrieve a String with the literal number of floors
     * @return literal number of floors
     */
    public String getFloors(){
        return floors;
    }


    /**
     * This method inserts the data in the property 's fields.
     *
     * @param data an array which include the property data
     */
    public void insertData(String data[]) {
        int i=0;
        int l=data.length;
        if(l==9){
	while(i<data.length){
    propertyID=data[i];
++i;
    propertyKind=data[i];
++i;
    propertyPrice=data[i];
++i;
    propertyArea=data[i];
++i;
    floors=data[i];
    ++i;
    beds=data[i];
    ++i;
    location=data[i];
++i;
   shortInfo=data[i];
++i;
    propertyAvailability=data[i];
++i;

    }
        }
        else{
            	while(i<data.length){
    propertyID=data[i];
++i;
    propertyKind=data[i];
++i;
    propertyPrice=data[i];
++i;
    propertyArea=data[i];
++i;
    floors=data[i];
    ++i;
    beds=data[i];
    ++i;
    location=data[i];
++i;
    propertyAvailability=data[i];
    ++i;

    }
        }
    }

    @Override
    /**
     * Print informations about Property
     */
    public String toString(){
        return super.toString()+
                "Beds : "+beds+"\n"+
                "Floors : "+floors+"\n"+
                "Location : "+location+"\n"+
                "Extra Info :"+shortInfo+"\n";
    }

    @Override
    public String getLocation() {
        return location;
    }


}
