package awgenscp_func;

import ucar.multiarray.ArrayMultiArray;
import ucar.multiarray.IndexIterator;
import ucar.multiarray.MultiArray;
import ucar.multiarray.MultiArrayImpl;
import ucar.netcdf.Attribute;
import ucar.netcdf.Netcdf;
import ucar.netcdf.NetcdfFile;
import ucar.netcdf.Variable;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.text.*;


public class readcfs {
    matfunct matlab = new matfunct();
    
    public void run(String dir,String filename) {

    // notify filename
    System.out.println("reading:"+filename);
    
    // open as read only
    try {
        Netcdf nc=new NetcdfFile(filename,true);  
    
        // get time dimension
        Variable nctime=nc.get("TIME");
        int ntime=nctime.getLengths()[0];
        System.out.println("ntime:"+ntime);
        
        // store time data
        double[] time=new double[ntime];
        int[] index = new int[1];
        for (int i=0;i<ntime;i++){
            index[0] = i;
            time[i]=nctime.getDouble(index);
            System.out.println("time:"+time[i]);
        }
        
        // get ensemble dimension
        Variable ncens=nc.get("ENS");
        int nens=ncens.getLengths()[0];
        System.out.println("n_ensemble:"+nens);
        
        // store ensemble data
        double[] ens=new double[nens];
        for (int i=0;i<nens;i++){
            index[0] = i;
            ens[i]=ncens.getDouble(index);
            System.out.println("ens:"+ens[i]);
        }
        
        // get dataset dimension
        Variable ncdata=nc.get("anom");
        int[] dims=ncdata.getLengths();
        System.out.println("dims:"+dims.length);
        for (int i=0;i<dims.length;i++){
            System.out.println("dim "+i+" len: "+dims[i]);
        }
        
        // store main dataset
        int[] ix=new int[5];
        double[][] data=new double[dims[0]][dims[1]];
        for (int i=0;i<dims[0];i++){
        for (int j=0;j<dims[1];j++){
            ix[0]=i;
            ix[1]=j;
            ix[2]=0;
            ix[3]=0;
            ix[4]=0;
            data[i][j]=ncdata.getDouble(ix);
            System.out.println("data["+i+","+j+"]: "+data[i][j]);
        }
        }
        
        // convert time dimension
        long init_date=matlab.datenum(1970,1,1);
        int[][] newtime=new int[3][ntime];
        for (int i=0;i<ntime;i++) {
            long gettime=init_date+(24*60*60*1000* (long) time[i]);
            int getnewtime[]=matlab.datevec(gettime);
            for (int j=0;j<3;j++){
                newtime[j][i]=getnewtime[j];
            }
            System.out.println("newtime:"+newtime[0][i]+" "+newtime[1][i]+" "+newtime[2][i]);
        }
        
        // write obs time to text
        String obstime=dir+"CFS_obs_time.txt";
        createfile(obstime);
        for (int i=0;i<ntime;i++) {
             if (data[40][i]>-99.0) {
             String text=String.valueOf(newtime[0][i])+" "+String.valueOf(newtime[1][i])+" "+String.valueOf(newtime[2][i]);
             appendtext(obstime,text);
             }
        }
        
        // write obs data to text
        String obsdata=dir+"CFS_obs_data.txt";
        createfile(obsdata);
        for (int i=0;i<ntime;i++) {
             if (data[40][i]>-99.0) {
             String text=String.valueOf(data[40][i]);
             appendtext(obsdata,text);
             }
        }
        
        // write ensembel time to text
        String enstime=dir+"CFS_ens_time.txt";
        createfile(enstime);
        for (int i=0;i<ntime;i++) {
             if (data[0][i]>-99.0) {
             String text=String.valueOf(newtime[0][i])+" "+String.valueOf(newtime[1][i])+" "+String.valueOf(newtime[2][i]);
             appendtext(enstime,text);
             }
        }
        
        // write obs data to text
        String ensdata=dir+"CFS_ens_data.txt";
        createfile(ensdata);
        for (int i=0;i<ntime;i++) {
        if (data[0][i]>-99.0) {
             String text=String.valueOf(data[0][i]);
             for (int j=1;j<40;j++) {
             text=text+","+String.valueOf(data[j][i]);
             }
        appendtext(ensdata,text);
        }
        }
    
    // catch error
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
    
    // terminate void: run
    }
    
    
    public void appendtext(String filename,String textline) {
        
        // begin read file    
        try {
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
        out.println(textline);
        out.close();
        } catch (IOException e) {
        }
        
    // terminate void: appendtext    
    }

    
    public void createfile(String filename) {
        
        try{
        PrintWriter writer = new PrintWriter(filename);
        } catch (IOException e) {
        }
        
    // terminate void: createfile    
    }

}
