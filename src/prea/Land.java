/*
 * Class Land extends class Property
 * 
 */

package prea;

import java.util.ArrayList;

/**
 *
 * @author j0ni
 */
public class Land extends Property{

    protected String propertyLocation;
    protected String propertyExtra;

    /**
     * Constructor
     */
    public Land(){
        
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
propertyLocation=data[i];
++i;
propertyExtra=data[i];
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
                "Location : "+propertyLocation+"\n"+
                "Extra : "+propertyExtra+"\n";
    }

    @Override
    public String getLocation() {
        return propertyLocation;
    }

}
