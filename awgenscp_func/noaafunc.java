/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awgenscp_func;

import java.io.*;
import java.util.*;


/**
 *
 * @author andung
 */
public class noaafunc {
    
    public void readnoaa(String dir,int subsetstart,int subsetend,int basestart,int baseend){

    // initiate variables
    noaafunc run=new noaafunc();
    String txt_file=dir+"get_noaasst.txt";
    String output_file=dir+"nino_obs.txt";
    String output_time=dir+"nino_time.txt";
    
    // start reading file
    double[][] data=run.noaaread(txt_file);
    System.out.println("datalength:"+data.length);
    
    // initiate variables to subset data
    int count=0;
    double[][] copydata=new double[99999][3];
    double[][] subset=new double[1][1];
    
    // loop to count subset
    for (int i=0;i<data.length;i++) {
        if (data[i][0]>=subsetstart){
        if (data[i][0]<=subsetend){
            copydata[count][0]=data[i][0];
            copydata[count][1]=data[i][1];
            copydata[count][2]=data[i][2];
            count=count+1;
        }
        }
    }
    
    // add subset information
    System.out.println("subsetlen:" + count);
    subset=new double[count][3];
    
    // copy data to subset
    for (int i=0;i<count;i++) {
        subset[i][0]=copydata[i][0];
        subset[i][1]=copydata[i][1];
        subset[i][2]=copydata[i][2];
        System.out.println("subset: "+subset[i][0]+" "+subset[i][1]+" "+subset[i][2]);
    }

// initiate variables to calculate anomaly
    double totalcount=0;
    double sum=0;
    
    // loop to calculate sum value
    for (int i=0;i<data.length;i++) {
        if (data[i][0]>=basestart){
        if (data[i][0]<=baseend){
            totalcount=totalcount+1;
            sum=sum+data[i][2];
        }
        }
    }
    
    // calculate average
    double average=sum/totalcount;
    System.out.println("meanval: "+average);

    // calculate subset data anomaly
    for (int i=0;i<subset.length;i++){
        subset[i][2]=subset[i][2]-average;
        System.out.println("diff: "+subset[i][2]);
    }
    
    // save output
    run.savetime(output_time,subset);
    run.savenoaa(output_file,subset);
    
    
}


public double[][] noaaread(String filename) {

        // notify progress
        System.out.println("Reading data... \n");
        
        // initiate variables
        Scanner scanner;
        int counter =0 ;
        int row = 0;
        double[][] nums = new double[1][1];
        double[][] guess = new double[99999][3];
       

        // begin try reading
        try {
        
            // define scanner
            scanner = new Scanner(new File(filename));
            scanner.useDelimiter(" ");
            
            // define column and row
            while (scanner.hasNext()) {
 
                // reading line
                String line = scanner.nextLine();
                //System.out.println(line);
                
                // add counter
                counter=counter+1;
            
                // split lines
                String[] fields = line.split("\\s+");
                //System.out.println("length:"+fields.length);
                
                // check number of array fields
                if (fields.length==14){
                    for (int i=2;i<fields.length;i++) {
                    double getval=Double.parseDouble(fields[i]);
                    if (getval!=-99.99) {
                    guess[row][2]=Double.parseDouble(fields[i]);
                    guess[row][0]=Double.parseDouble(fields[1]);
                    guess[row][1]=i-1;
                    //System.out.println("field:"+row+" "+guess[row]+"\n");
                    row=row+1;
                    }
                    }
                }
                

            // end while loop
            }

            // close scanner
            scanner.close();

            // add row      
            nums = new double[row][3];

            // copy data
            for (int i=0;i<row;i++){
            nums[i][2]=guess[i][2];
            nums[i][0]=guess[i][0];
            nums[i][1]=guess[i][1];
            //System.out.println("time:"+nums[i][0]+" "+nums[i][1]+" data:"+nums[i][2]+"\n");
            }
           
        // end try
        }

        // catch error
        catch (FileNotFoundException ex) {
        //Logger.getLogger(testnoaa.class.getName()).log(Level.SEVERE, null, ex);
        }

        // default return
        return nums;
}


public void savetime(String filename,double data[][]) {

    // identify matrix size
    int row = data.length;
    int col = data[0].length-1;

    // write to file
    try{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    // write data
    for (int i=0; i<row; i++) {
        String testline = new String();
        for (int j=0; j<col; j++) {   
            String val = String.valueOf(data[i][j]);
            if (j!=0) {
            testline= testline+","+val;
            } else {
            testline= val;    
            }
        }    
        writer.write(testline);
        writer.write("\n");
    }
    writer.close();
    } catch (IOException ex) {
        //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }
// end funtion savetime
}


public void savenoaa(String filename,double data[][]) {

    // identify matrix size
    int row = data.length;
    int col = data[0].length;

    // write to file
    try{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    // write data
    for (int i=0; i<row; i++) {
        String testline = new String();   
        String val = String.valueOf(data[i][2]);
        testline= val;    
        writer.write(testline);
        writer.write("\n");
    }
    writer.close();
    } catch (IOException ex) {
        //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }
// end funtion savetime
}
    
}
