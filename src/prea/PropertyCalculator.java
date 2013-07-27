/*
 * with the minimum, maximum and average value of the sold,
 *for sale and all properties per area.
 *Also, it will print the total commission for the representative
 *which ids the 2% of total amount of the sold properties
 * @author j0ni
 * @version 1.2
 */
package prea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j0ni
 * @version 1.0
 */
public class PropertyCalculator {

    ArrayList<Property> aforsale;
    ArrayList<Property> asold;
    File statistics;
    FileWriter writeStats;
    float[] prices;
    float[] priceforsale;
    float[] pricesold;
    float min;
    float max;
    float average;
    float commission;
    String sales;
    String solds;
    private ArrayList<String> locations;


    public PropertyCalculator(PropertyList pl){

        aforsale=new ArrayList<Property>();
        asold=new ArrayList<Property>();

        for(Property pr:pl.list)
           
            if(pr.getAvailability().trim().equals("for sale")){ aforsale.add(pr);}
            else
                if(pr.getAvailability().trim().equals("sold")){ asold.add(pr);}

    }

/**
 * Used to find out the minimum price through an array
 * and return a specific String to present it.
 *
 * @param sarray An instance of ArrayList
 * @return The minimum price in a String
 */
 public String minimum(ArrayList sarray){
    SelectedProperty tmparray=new SelectedProperty();
    tmparray.wishList=sarray;
     Property ptmp=(Property) sarray.get(0);
     String txt=ptmp.getPrice().trim();
     
     String[] num = txt.trim().split(" ");

        String replace = num[0].replace(",", ".");
     float thenum=Float.parseFloat(replace);
          min=thenum;
          for(Property psales:tmparray.wishList){//replace aforsale with
                  txt=psales.getPrice().trim();
                  
                  num = txt.trim().split(" ");
                  replace=num[0].replace(",",".");
                  thenum=Float.parseFloat(replace);
            if(thenum<min){
                 min=thenum;
            }

        }

     return "\n The minimum price of  : "+min;
 }

 /**
 * Used to find out the maximum price through an ArrayList
 * and return a specific String to present it.
 *
 * @param sarray An instance of ArrayList
 * @return The maximum price in a String
 */
 public String maximum(ArrayList sarray){
    SelectedProperty tmparray=new SelectedProperty();
    tmparray.wishList=sarray;
     Property ptmp=(Property) sarray.get(0);
     String txt=ptmp.getPrice().trim();

     String[] num = txt.trim().split(" ");

        String replace = num[0].replace(",", ".");
     float thenum=Float.parseFloat(replace);
          max=thenum;
          for(Property psales:tmparray.wishList){
                  txt=psales.getPrice().trim();

                  num = txt.trim().split(" ");
                  replace=num[0].replace(",",".");
                  thenum=Float.parseFloat(replace);
            if(thenum>max){
                 max=thenum;
            }

        }

     return "\n The maximum price of : "+max;
 }

 /**
 * Used to calculate the average value of Property 's fields Price.
  * Takes an ArrayList with instances  of Property class
 * and return a specific String to present it.
 *
 * @param sarray An instance of ArrayList
 * @return The average value of all Property 's fields Price in a String
 */
 public String average(ArrayList sarray){
         float sum=0;
    SelectedProperty tmparray=new SelectedProperty();
    tmparray.wishList=sarray;
     Property ptmp=(Property) sarray.get(0);
     String txt=ptmp.getPrice().trim();

     String[] num = txt.trim().split(" ");

        String replace = num[0].replace(",", ".");
     float thenum=Float.parseFloat(replace);

          for(Property psales:tmparray.wishList){
                    txt=psales.getPrice().trim();

                  num = txt.trim().split(" ");
                  replace=num[0].replace(",",".");
                  thenum=Float.parseFloat(replace);
                    sum+=thenum;
             
        }
         
          average=sum/tmparray.wishList.size();
     return "\n The average price of : "+average;
 }

 /**
  * Calculate the total commission for the representative
  * which is the 2% of total amount of the sold properties
  *
  * @return The commission value in a String
  */
 public String commission(){
     float sum=0;
    Iterator it=asold.iterator();

          for(Property psales:asold){
              // while(it.hasNext()){
               String    txt=psales.getPrice().trim();
                 String[] num = txt.trim().split(" ");
                 String replace=num[0].replace(",",".");
                  float thenum=Float.parseFloat(replace);
                   System.out.println(thenum+"");
                    sum+=thenum;
             // }
        }

     commission=(float) (sum * 0.2);
     return "\n the total commission for the representative is  : "+commission;
 }

 /**
  * provide a String with the minimum, maximum and average value
  * for properties per area.
  * @param pl a PropertyList that is used for statistics
  * @param area The area which is interested
  * @return A statistic text with the minimum, maximum and average value
  */
 public String statisticsPerArea(PropertyList pl,String area){

     String info="";
     ArrayList<Property> tmparea=null; //create this to manipulate data
     for(Property pr:pl.list){
     if(area.equals(pr.getLocation()))
            tmparea.add(pr);
         }
     
     info += "Location :"+area;
     info+="\n<statistics details>";
     info+="\n";

     info+=maximum(tmparea);//TODO
     info+=minimum(tmparea);//TODO
     info+=average(tmparea);//TODO
     info+=".................";
     info+="\n";
             
     
     return info;
 }

 /**
  * Save General statistics for an ArrayList
  * @param area The interested ArrayList
  */
public void saveStatistics(ArrayList area){
     try {
                   File itemfile=new File("STATISTICS.txt");
                    PrintWriter filewriter = new PrintWriter(itemfile);
                    filewriter.println("STATISTICS\n");
                    filewriter.println("------------------------");
                    filewriter.println(maximum(area));
                    filewriter.println(minimum(area));
                    filewriter.println(average(area));
                    filewriter.println("\n");
                    filewriter.println("about commision of sold property");
                    filewriter.println(commission());
                    filewriter.println(new Date().toString());
                    filewriter.flush();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PREAUserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
}

 private float[] createPriceArray(ArrayList aList){
     Property p[]=(Property[]) aList.toArray();
        float[] tmpprice=new float[p.length];
        //int index=0;

        for(int i=0;i<p.length;i++){
             String t=p[i].propertyPrice;
             String replace = t.replace(",", ".");
             String[] tt=replace.trim().split(" ");
             Float intValue=Float.parseFloat(tt[0]);
             tmpprice[i]=intValue;
            }
      return tmpprice;
     }


     private Float getPriceValue(ArrayList aList){
        Property p[]=(Property[]) aList.toArray();
        //prices=new float[list.size()];
        Float floatValue = null;
          //int index=0;

        for(int i=0;i<p.length;i++){
             String t=p[i].propertyPrice;
             String replace = t.replace(",", ".");
             String[] tt=replace.trim().split(" ");
             floatValue=Float.parseFloat(tt[0]);
            }
        return floatValue;
     }

    private float[] returnArray(ArrayList area) {
       if(area.containsAll(aforsale)) return priceforsale;
       else
           if(area.containsAll(asold)) return pricesold;
       return prices;
    }

    private boolean exist(String s){
        for(String str:locations){
            if(locations.contains(s)) return true;
        }
        return false;
    }

}
