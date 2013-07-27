/*
 * Class PropertyList used to manage a list of all available properties and
 * their details. The class must be initialized by reading a text file
 * in which the property details are stored separated by semicolons.
 * 
 */

package prea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author j0ni
 * @version 0.2
 * @see http://www.java2s.com/Code/Java/Language-Basics/GenericArrayList.htm
 * @see http://www.oracle.com/technetwork/articles/javase/generics-136597.html
 */
public class PropertyList{

    Scanner sc;
    protected ArrayList<Property> list;
    File file;

/**
 * Class Constructor initialize the ArrayList list
 * which it will used to manipulate Properties object
 */
    public  PropertyList(){
       list=new ArrayList<Property>();
    }

 /**
  * Class Constructor with parameter file. The file gives the properties data
  * and initialize the ArrayList list
  * which it will used to manipulate Properties object.
  * @param file a text file with appropriate format to retrieve the data
  */
    public PropertyList(String file){
       list=new ArrayList<Property>();
       this.file=new File(file);
       createList();

    }


 /**
  * Creates the List scanning the file.
  * Additional categorize the Properties in Land, House, Flat.
  */
    private void createList(){
        String linedata[]=null;
        try{
        sc=new Scanner(file);
        while(sc.hasNextLine()){
            linedata=sc.nextLine().trim().split(";");
            int linelength=linedata.length;
            if(linedata[1].trim().equalsIgnoreCase("Land")){
                Land l=new Land();
                l.insertData(linedata);
                addProperty(l);
                    }
            else
                if(linedata[1].trim().equalsIgnoreCase("Flat")){
                Flat f=new Flat();
                f.insertData(linedata);
                addProperty(f);
                    }
                else
                if(linedata[1].trim().equalsIgnoreCase("House")){
                House h=new House();
                h.insertData(linedata);
                    addProperty(h);
                    }
                }

        }catch(IOException e){System.out.println(e);}
    }


/**
 * Changes the data file
 * @param file The name of file which read from
 */
    public void chooseFile(String file){
        this.file=new File(file);
    }
/**
 * Adds the proper subclass into the ArrayList
 * @param <T>   Return the appropiate type
 * @param p     Adds the current property
 */
    public void addProperty(Property p){
        list.add(p);
    }
/**
 *
 * @param <T>   Return the appropiate type
 * @return  The full list of ArrayList
 */
    public <T> String displayList(){
        String thelist=null;
        for(Property p:list){
            thelist+=p.toString();
        }
        return thelist;
    }

/**
 * retrieve specific elements of list
 * @return  The Land Object from the list
 */
    public ArrayList getLandList(){
        ArrayList<Land> l=new ArrayList<Land>();
        for(Property p:list){
            if(p instanceof Land) {
                Land land = (Land) p;
                l.add(land);
            }
            }
        
        return l;
    }

/**
 * retrieve specific elements of list
 * @return  The Flat Object from the list
 */
        public ArrayList getFlatList(){
        ArrayList<Flat> l=new ArrayList<Flat>();
        for(Property p:list){
            if(p instanceof Flat) {
                Flat flat = (Flat) p;
                l.add(flat);
            }
            }

        return l;
    }

 /**
 * retrieve specific elements of list
 * @return  The House Object from the list
 */
            public ArrayList getHouseList(){
        ArrayList<House> l=new ArrayList<House>();
        for(Property p:list){
            if(p instanceof House) {
                House house = (House) p;
                l.add(house);
            }
            }

        return l;
    }

       public Property getProperty(String s){
           Property found=null;
           for(Property p:list)
               if(s.equals(p.propertyID))
                found=p;
                return found;
       }

       public int getIndex(Property prp){
           int i=0;
           for(Property p:list){
               if(prp.equals(p)){
                   return i;
               }
           i++;
           }
           return -1;
       }

       protected String[] getAreas(){
           String[] areas=new String[list.size()];
           for(int i=0;i<i+1;){//raise counter whether is a new String
               for(int d=0;d<list.size();d++){//go through list
               String smth=list.get(d).getLocation();//take String
                    for(int k=0;k<areas.length;k++){//check array if String is included until i=areas.length
                        if(areas[k].equals(smth))//if yes raise i & look for new String
                            i++;
                        else
                            areas[i]=smth; //else strore current String
                   }
               }
           }
           return areas;
       }

}
