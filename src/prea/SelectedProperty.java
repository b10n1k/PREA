/*
 * Class SelectedProperty used to hold some properties
 * to be proposed to a perspective client.
 * Amongst others, the class SelectedProperties provide
 * file manipulation functionality specialised for storing
 * the selected objects to a text file and
 * calculation of the cheapest property
 * 
 */

package prea;

import java.util.ArrayList;

/**
 *
 * @author j0ni
 * @version 1.0
 */
public class SelectedProperty extends PropertyList{

    ArrayList<Property> wishList;

    /**
     * Constructor of class without arguments
     */
    public SelectedProperty(){
        wishList=new ArrayList<Property>();
    }

    /**
     * This method add a Property into wishList. Parameter index
     * point the ArrayList of PropertyList that to be added.
     *
     * @param <T> Return the appropiate type
     * @param index the position in the ArrayList of PropertyList
     */
    public <T> void addSelected(int index){
         wishList.add(list.get(index));
        }

    /**
 *
     * Gives back a string with all
     * the Properties in wishList ArrayList
     *
 * @param <T>   Return the appropiate type
 * @return  The full list of ArrayList
 */
    @Override
    public <T> String displayList(){
        String thelist="";
        for(Property p:wishList){
            thelist+=p.toString();
            thelist+="\n";
        }
        return thelist;
    }

    /**
     * Calculate and return the property with the cheapest price
     *
     * @return A String of the selected property with the cheapest price
     */
    public String cheaperis(){
        float[] prices=new float[wishList.size()];
        int index=0;

        for(int i=0;i<wishList.size();i++){
             String t=wishList.get(i).propertyPrice;
             String replace = t.replace(",", ".");
             String[] tt=replace.trim().split(" ");
             Float intValue=Float.parseFloat(tt[0]);
             prices[i]=intValue;
        }

        float cheap=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<cheap){
                 cheap=prices[i];
                    index=i;
            }
        }
            return wishList.get(index).toString();
    }

}
