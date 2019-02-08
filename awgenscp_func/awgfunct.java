package awgenscp_func;

//import packages
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.text.*;

public class awgfunct {

// call matlab function    
matfunct matlab = new matfunct();	


// function to create time attribute each day
public void addtime_attrib_day(String filename, String dir,String id, String filesave, int year, int month, int day) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	System.out.println("timeseries "+row);
	
	
	// generate output table
	double[][] dataout= new double[row][4];
	
 	// define initial date range
    long dateinit=matlab.datenum(year,month,day);

	// loop date input
	for (int i=0;i<row;i++) {
		
		// generate output table
		int[] timevec=matlab.datevec(dateinit);
		
		// copy result to output
		for (int j=0;j<3;j++) {
		dataout[i][j]=(double) timevec[j];
		}
		dataout[i][3]=datain[i][0];
		
		// add time increment
		dateinit=dateinit+(24*3600*1000);
		
		// check debug
		for (int j=0;j<3;j++) {
		System.out.println(dataout[i][j]);
		}
			
	}
  
	// save awg
	String[] label=new String[4];
	label[0]="year";
	label[1]="month";
	label[2]="day";
	label[3]="data";
	filesave=dir+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}




// function to create time attribute each day
public void addtime_attrib_rain(String filename,String dir,String ident,String filesave,int year,int month,int day) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	System.out.println("timeseries "+row);
	
	
	// generate output table
	double[][] dataout= new double[row][4];
	
 	// define initial date range
    long dateinit=matlab.datenum(year,month,day);

	// loop date input
	for (int i=0;i<row;i++) {
		
		// generate output table
		int[] timevec=matlab.datevec(dateinit);
		
		// copy result to output
		for (int j=0;j<3;j++) {
		dataout[i][j]=(double) timevec[j];
		}
		dataout[i][3]=datain[i][0];
		
		// add time increment
		dateinit=dateinit+(24*3600*1000);
		
		// check debug
		for (int j=0;j<3;j++) {
		System.out.println(dataout[i][j]);
		}
			
	}
  
	// save awg
	String[] label=new String[4];
	label[0]="year";
	label[1]="month";
	label[2]="day";
	label[3]="rain";
	filesave=dir+ident+"_"+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}





// function to create time attribute each day
public void addtime_attrib_temp(String filename,String dir,String ident,String filesave,int year,int month,int day) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	System.out.println("timeseries "+row);
	
	
	// generate output table
	double[][] dataout= new double[row][4];
	
 	// define initial date range
    long dateinit=matlab.datenum(year,month,day);

	// loop date input
	for (int i=0;i<row;i++) {
		
		// generate output table
		int[] timevec=matlab.datevec(dateinit);
		
		// copy result to output
		for (int j=0;j<3;j++) {
		dataout[i][j]=(double) timevec[j];
		}
		dataout[i][3]=datain[i][0];
		
		// add time increment
		dateinit=dateinit+(24*3600*1000);
		
		// check debug
		for (int j=0;j<3;j++) {
		System.out.println(dataout[i][j]);
		}
			
	}
  
	// save awg
	String[] label=new String[4];
	label[0]="year";
	label[1]="month";
	label[2]="day";
	label[3]="temp";
	filesave=dir+ident+"_"+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}



// function to create time attribute each month
public void addtime_attrib_month(String filename, String dir, String filesave, int year, int month) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	System.out.println("timeseries "+row);
	
	// generate output table
	double[][] dataout= new double[row][3];

	// loop date input
	for (int i=0;i<row;i++) {
		
		// add value to array
		dataout[i][0]=year;
		dataout[i][1]=month;
		dataout[i][2]=datain[i][0];
		
        // recycle time value if month=12
        if (month==12) {
            year=year+1;
		    month=1;
        } else {
            month=month+1;
        }
		
		// check debug
		for (int j=0;j<3;j++) {
		System.out.println(dataout[i][j]);
		}	
	}
  
	// save awg
	String[] label=new String[3];
	label[0]="year";
	label[1]="month";
	label[2]="data";
	filesave=dir+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}




// function to create time attribute each month
public void addtime_attrib_predictnow(String filename,String dir,String ident,String filesave,int year,int month) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	System.out.println("timeseries "+row);
	
	// generate output table
	double[][] dataout= new double[row][3];

	// loop date input
	for (int i=0;i<row;i++) {
		
		// add value to array
		dataout[i][0]=year;
		dataout[i][1]=month;
		dataout[i][2]=datain[i][0];
		
        // recycle time value if month=12
        if (month==12) {
            year=year+1;
		    month=1;
        } else {
            month=month+1;
        }
		
		// check debug
		for (int j=0;j<3;j++) {
		System.out.println(dataout[i][j]);
		}	
	}
  
	// save awg
	String[] label=new String[3];
	label[0]="year";
	label[1]="month";
	label[2]="predictor_value";
	filesave=dir+ident+"_"+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}




// function to create time attribute each month
public void addtime_attrib_predictfuture(String filename,String dir,String ident,String filesave,int year,int month) {
	
    // load textfile
    double[][] datain=matlab.dlmread(filename); 

	// get input data dimension
	int row=datain.length;
	int col=datain[0].length;
	System.out.println("row:"+row+" col:"+col);
	
	// generate output table
	double[][] dataout= new double[row][col+2];

	// loop date input
	for (int j=0;j<col;j++) {
	for (int i=0;i<row;i++) {
		
		// add value to array
		if (j==0) {
		dataout[i][0]=year;
		dataout[i][1]=month;
		}
		dataout[i][j+2]=datain[i][j];
		
                // recycle time value if month=12
                if (month==12) {
                    year=year+1;
		    month=1;
                } else {
                    month=month+1;
                }
		
	// terminate loop row col	
	}
	}
  
	// save awg
	String[] label=new String[col+2];
	label[0]="year";
	label[1]="month";
	for (int k=2;k<col+2;k++) {
	label[k]="ensemble_"+Integer.toString(k);
	}
	filesave=dir+ident+"_"+filesave;
	matlab.saveawg(filesave,dataout,label);
	
// terminate function	
}




// function to obtain rain parameter
public void getrain_param(String filein,String dir,String ident,int start_year,int start_month,int end_year,int end_month) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] datain=matlab.readawg(filein);  
        System.out.println("reading:"+filein);
			 
	// create initial loop value
	double[] subset=new double[31];

	// begin while loop to populate data
	int i=0;
        int runflag=0;
	int year=start_year;
	int month=start_month;
	while (runflag==0) {
            
                // check if data read is finished
                if ((year>=end_year)&&(month>end_month)) {
                runflag=1;
                break;
                }
                    
  		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}
	// terminate while loop
	}

	// allocate dataout array
	double[][] dataout=new double[i][5];
	
	// begin while loop to populate data
        int totaldata=i;
	i=0;
        year=start_year;
	month=start_month;
	for (int ndata=0;ndata<totaldata;ndata++) {
  
		// create notification
		System.out.println("processing year:"+year+" month:"+month);
		
		// subset data
		int k=0;
		Arrays.fill(subset,-9999);
		for (int j=0;j<datain.length;j++) {
		// System.out.println(datain.length+" "+j);
		if ((datain[j][0]==year)&&(datain[j][1]==month)&&(datain[j][3]>=0)){
		subset[k]=datain[j][3];
		// System.out.println("month:"+month+" k:"+k+" subset:"+subset[k]);
		k=k+1;
		}
		}
		
		// estimate rain probability
		double prob=matlab.rainprob_estimator(subset);
		
		// estimate gamma distribution
		double[] gamma=matlab.gamma_estimator(subset);
		
		// store result to array
		dataout[i][0]=year;
         	dataout[i][1]=month;
	        dataout[i][2]=prob;
		dataout[i][3]=gamma[0];
		dataout[i][4]=gamma[1];
		
		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}
  
	// terminate while loop
	}

     // define output label 
     String[] label=new String[5];
     label[0]="year";
     label[1]="month";
     label[2]="porbability";
     label[3]="gamma_alpha";
     label[4]="gamma_beta";

     // save to awgfile 
     String fileout=dir+ident+"_rain_param.awg";
     System.out.println("writing rain parameter to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 



// function to obtain temperature parameter
public void gettemp_param(String filein,String dir,String ident,int start_year,int start_month,int end_year,int end_month) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] datain=matlab.readawg(filein);         
			 
	// create initial loop value
	double[] subset=new double[31];

	// begin while loop to populate data
	int i=0;
	int year=start_year;
	int month=start_month;
	while ((year<=end_year)&&(month<=end_month)) {
  		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}
	// terminate while loop
	}

	// allocate dataout array
	double[][] dataout=new double[i][4];
	
	// begin while loop to populate data
	i=0;
        year=start_year;
	month=start_month;
	while ((year<=end_year)&&(month<=end_month)) {
  
		// create notification
		System.out.println("processing year:"+year+" month:"+month);
		
		
		// subset data
		int k=0;
		Arrays.fill(subset,-9999);
		for (int j=0;j<datain.length;j++) {
		System.out.println(datain.length+" "+j);
		if ((datain[j][0]==year)&&(datain[j][1]==month)){
		subset[k]=datain[j][3];
		System.out.println("month:"+month+" k:"+k+" subset:"+subset[k]);
		k=k+1;
		}
		}
		
		// estimate normal distribution
		double[] normdist=matlab.normaldist_estimator(subset,-9999);
		
		// store result to array
		dataout[i][0]=year;
         	dataout[i][1]=month;
	        dataout[i][2]=normdist[0];
		dataout[i][3]=normdist[1];
		
		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}
  
	// terminate while loop
	}

     // define output label 
     String[] label=new String[4];
     label[0]="year";
     label[1]="month";
     label[2]="normdist_mean";
     label[3]="normdist_stdev";

     // save to awgfile 
     String fileout=dir+ident+"_temp_param.awg";
     System.out.println("writing temp parameter to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 



// function to format predictor data
public int predictnow_format(String filein,String dir,String ident,int start_year,int start_month,int end_year,int end_month) {

        // define resultflag
        int resultflag=0;
                
	// load file input
	filein=dir+ident+"_"+filein;
	double[][] datain=matlab.readawg(filein);   
        System.out.println("finished reading file");
        
			 
	// begin while loop to populate data
	int i=0;
	int year=start_year;
	int month=start_month;
        int runflag=0;
	while (runflag==0) {
            
               // check the year and month
               if ((year==end_year)&&(month>end_month)) {
               runflag=1;
               break;
               }
               
  		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}
	// terminate while loop
	}

	// allocate dataout array
	double[][] dataout=new double[i][3];
        //Arrays.fill(dataout,-9999);
        //System.out.println("finished creating new table");
	
	// begin while loop to populate data
        int totaldata=i;
	i=0;
        year=start_year;
	month=start_month;
        runflag=0;
	while (runflag==0) {
  
                // check the year and month
               if ((year==end_year)&&(month>end_month)) {
               runflag=1;
               break;
               }
                    
		// create notification
		System.out.println("processing year:"+year+" month:"+month);
		
		// add data if found
		for (int j=0;j<datain.length;j++) {
		if ((datain[j][0]==year)&&(datain[j][1]==month)){
		
		// store result to array
		dataout[i][0]=year;
         	dataout[i][1]=month;
	        dataout[i][2]=datain[j][2];
		
		// terminate for j and if datain[j]
		} }
                
                // notify if data not found
                if (dataout[i][0]==0) {
                    System.out.println("data not found");  
                    dataout[i][0]=-9999;
         	    dataout[i][1]=-9999;
	            dataout[i][2]=-9999;
                }
  

		// add time increment
		i=i+1;
		
		//  change timerange for next selection
		if (month==12) {
		 	  year=year+1;
			  month=1;
		}  else {
			  month=month+1;
		}

	// terminate while loop
	}
        
     // copy output to new table
     System.out.println("creating output row:"+i);
     double[][] dataout2=new double[i][3];
     for (int j=0;j<dataout2.length;j++) {
     for (int k=0;k<3;k++) {
         dataout2[j][k]=dataout[j][k];
     }
     }

     // define output label 
     String[] label=new String[3];
     label[0]="year";
     label[1]="month";
     label[2]="predictor";

     // save to awgfile 
     String fileout=dir+ident+"_predictnow_param.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
     return resultflag;
                         
// terminate function
} 




// function to build rainmodel
public void build_rainmodel(String filerain,String filepredictnow,String dir,String ident,int modeltype) {

	// load file input
	filerain=dir+ident+"_"+filerain;
	filepredictnow=dir+ident+"_"+filepredictnow;
	double[][] rain=matlab.readawg(filerain); 
	double[][] predict=matlab.readawg(filepredictnow);

	// subset monthly data
	double[] monthval=new double[rain.length];
	for (int i=0;i<rain.length;i++) {
	    monthval[i]=rain[i][1];
	    // System.out.println("monthval:"+monthval[i]);
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	int maxclass;
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	    maxclass=4;
	} else {
            System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	    maxclass=12;
	}

	// generate output table
	double[][] dataout=new double[maxclass][6];
        	
	// loop subset data for each monthclass
	for (int i=0;i<maxclass;i++) {

		// count data subset
		int getcount=matlab.countequal(modelclass,i);
                System.out.println("getcount:"+getcount);                

		// initiate array to store data
		double[] prob=new double[getcount];
                double[] alpha=new double[getcount];
		double[] beta=new double[getcount];
		double[] predict_param=new double[getcount];

		// loop to each data to insert subset each class
		int k=0;
		for (int j=0;j<modelclass.length;j++) {
		
	            // add subset data
		    if (modelclass[j]==i) {
		        prob[k]=rain[j][2];
			alpha[k]=rain[j][3];
			beta[k]=rain[j][4];
			predict_param[k]=predict[j][2];
			System.out.println("prob:"+prob[k]+" alpha:"+alpha[k]+" beta:"+beta[k]+" k:"+k);
			System.out.println("predict_param:"+predict_param[k]);
			k=k+1;
		    }
		}

		// calculate rain probability model
		double[] getprob=matlab.regress(predict_param,prob);

		// calculate alpha in gamma distribution model
		double[] getalpha=matlab.regress(predict_param,alpha);

		// calculate beta in gamma distribution model
		double[] getbeta=matlab.regress(predict_param,beta);

		// store to array
		dataout[i][0]=getprob[0];
		dataout[i][1]=getprob[1];
		dataout[i][2]=getalpha[0];
		dataout[i][3]=getalpha[1];
		dataout[i][4]=getbeta[0];
		dataout[i][5]=getbeta[1];

	// terminate loop i
	}
			 

     // define output label 
     String[] label=new String[6];
     label[0]="probability_a_regress";
     label[1]="probability_b_regress";
     label[2]="gamma_alpha_a_regress";
     label[3]="gamma_alpha_b_regress";
     label[4]="gamma_beta_a_regress";
     label[5]="gamma_beta_b_regress";

     // save to awgfile 
     String fileout=dir+ident+"_rain_regress.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to build temperature model
public void build_tempmodel(String filetemp,String filepredictnow,String dir,String ident,int modeltype) {

	// load file input
	filetemp=dir+ident+"_"+filetemp;
	filepredictnow=dir+ident+"_"+filepredictnow;
	double[][] temp=matlab.readawg(filetemp); 
	double[][] predict=matlab.readawg(filepredictnow);

	// subset monthly data
	double[] monthval=new double[temp.length];
	for (int i=0;i<temp.length;i++) {
	    monthval[i]=temp[i][1];
	    // System.out.println("monthval:"+monthval[i]);
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	int maxclass;
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	    maxclass=4;
	} else {
          System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	    maxclass=12;
	}

	// generate output table
	double[][] dataout=new double[maxclass][4];
        	
	// loop subset data for each monthclass
	for (int i=0;i<maxclass;i++) {

		// count data subset
		int getcount=matlab.countequal(modelclass,i);
              System.out.println("getcount:"+getcount);                

		// initiate array to store data
		double[] mean=new double[getcount];
                double[] stdev=new double[getcount];
		double[] predict_param=new double[getcount];

		// loop to each data
		int k=0;
		for (int j=0;j<modelclass.length;j++) {
		
	            // add subset data
		    if (modelclass[j]==i) {
		        mean[k]=temp[j][2];
			stdev[k]=temp[j][3];
			predict_param[k]=predict[j][2];
			System.out.println("mean:"+mean[k]+" stdev:"+stdev[k]+" k:"+k);
			System.out.println("predict_param:"+predict_param[k]);
			k=k+1;
		    }
		}

		// calculate temperature model
		double[] getmean=matlab.regress(predict_param,mean);

		// calculate deviation model
		double[] getstdev=matlab.regress(predict_param,stdev);

		// store to array
		dataout[i][0]=getmean[0];
		dataout[i][1]=getmean[1];
		dataout[i][2]=getstdev[0];
		dataout[i][3]=getstdev[1];

	// terminate loop i
	}
			 

     // define output label 
     String[] label=new String[4];
     label[0]="mean_a_regress";
     label[1]="mean_b_regress";
     label[2]="stdev_a_regress";
     label[3]="stdev_b_regress";

     // save to awgfile 
     String fileout=dir+ident+"_temp_regress.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to simulate rain model
public void simulate_rain(String filein,String filepredict_future,String dir,String ident,int modeltype) {

	// load file input
	filein=dir+ident+"_"+filein;
	filepredict_future=dir+ident+"_"+filepredict_future;
	double[][] regress=matlab.readawg(filein); 
	double[][] predict=matlab.readawg(filepredict_future);

	// get predictor size
	int row=predict.length;
	int col=predict[0].length;

	// define month length
	int[] monlen_norm={31,28,31,30,31,30,31,31,30,31,30,31};

	// generate main output array
	int getrow=0;
	for (int i=0;i<predict.length;i++) {
	    int getmonlen=0;
            int getyear=(int)predict[i][0];
            int getmonth=(int)predict[i][1];
	    if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	        getmonlen=29; 
	    } else {
	        getmonlen=monlen_norm[getmonth-1];
	    }
            for (int day=0;day<getmonlen;day++) {
            getrow=getrow+1;
	    }
 	}
	double[][] dataout=new double[getrow][col+1];

	// subset monthly data
	double[] monthval=new double[predict.length];
	for (int i=0;i<predict.length;i++) {
	    monthval[i]=predict[i][1];
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	} else {
          System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	}

	// loop to each ensemble
       for (int ens=2;ens<col;ens++) {
	      
       	     // initiate row to write
	     int nwrite=0;
	
	     // loop to each month in prediction data
	     for (int i=0;i<predict.length;i++) {
	
	            // obtain year and month
	            int getyear=(int)predict[i][0];
	            int getmonth=(int)predict[i][1];

	            // obtain rain parameter
	            int pos=(int) modelclass[i];
	            double prob_a=regress[pos][0];
                    double prob_b=regress[pos][1];
	            double alpha_a=regress[pos][2];
	            double alpha_b=regress[pos][3];
	            double beta_a=regress[pos][4];
	            double beta_b=regress[pos][5];
	
		    // identify month length
		    int monlen=0;
	            if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	                 monlen=29; 
	            } else {
	                 monlen=monlen_norm[getmonth-1];
	            }

		    // obtain monthly predictor data
		    double mon_predict=predict[i][ens];

	            // loop to each day
                    for (int day=0;day<monlen;day++) {
		    
    		        // create random seed
    		        double seed=Math.random();
    
    		        // calculate probability model
        		double prob=prob_a+(prob_b*mon_predict);
    
    		        // calculate gamma model
    		        double alpha=alpha_a+(alpha_b*mon_predict);
    		        double beta=beta_a+(beta_b*mon_predict);
    
    		        // initiate rain rate
    		        double rain=matlab.gamrnd(alpha,beta);
    		    
    		        // simulate rain probability
    		        if (seed<(1.0-prob)) {
    		        rain=0.0;
			} else {
			rain=rain+1.0;
    		        }

			// write data
			dataout[nwrite][ens+1]=rain;
    
    		        // write date information
    		        if (ens==2) {
    		        dataout[nwrite][0]=getyear;
    		        dataout[nwrite][1]=getmonth;
    		        dataout[nwrite][2]=day+1;
    		        }

			// add row increment
			nwrite=nwrite+1;

		    // terminate loop day
		    }

	      // terminate loop i	    
	      }

        // terminate loop ens
	}

        // define output label 
     String[] label=new String[col+1];
     label[0]="year";
     label[1]="month";
     label[2]="day";
     for (int nlabel=3;nlabel<col+1;nlabel++){
     label[nlabel]="ensemble"+Integer.toString(nlabel);
     }

     // save to awgfile 
     String fileout=dir+ident+"_rain_sim.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to simulate rain model mean ensemble
public void simulate_rain_meanens(String filein,String filepredict_future,String dir,String ident,int modeltype) {

	// load file input
	filein=dir+ident+"_"+filein;
	filepredict_future=dir+ident+"_"+filepredict_future;
	double[][] regress=matlab.readawg(filein); 
	double[][] predict=matlab.readawg(filepredict_future);

	// get predictor size
	int row=predict.length;
	int col=predict[0].length;

	// define month length
	int[] monlen_norm={31,28,31,30,31,30,31,31,30,31,30,31};

	// generate main output array
	int getrow=0;
	for (int i=0;i<predict.length;i++) {
	    int getmonlen=0;
            int getyear=(int)predict[i][0];
            int getmonth=(int)predict[i][1];
	    if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	        getmonlen=29; 
	    } else {
	        getmonlen=monlen_norm[getmonth-1];
	    }
            for (int day=0;day<getmonlen;day++) {
            getrow=getrow+1;
	    }
 	}
	double[][] dataout=new double[getrow][4];

	// subset monthly data
	double[] monthval=new double[predict.length];
	for (int i=0;i<predict.length;i++) {
	    monthval[i]=predict[i][1];
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	} else {
          System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	}

	      
       	     // initiate row to write
	     int nwrite=0;
	
	     // loop to each month in prediction data
	     for (int i=0;i<predict.length;i++) {
	
	            // obtain year and month
	            int getyear=(int)predict[i][0];
	            int getmonth=(int)predict[i][1];

	            // obtain rain parameter
	            int pos=(int) modelclass[i];
	            double prob_a=regress[pos][0];
                    double prob_b=regress[pos][1];
	            double alpha_a=regress[pos][2];
	            double alpha_b=regress[pos][3];
	            double beta_a=regress[pos][4];
	            double beta_b=regress[pos][5];
	
		    // identify month length
		    int monlen=0;
	            if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	                 monlen=29; 
	            } else {
	                 monlen=monlen_norm[getmonth-1];
	            }

		    // obtain monthly predictor data
		    double mon_predict=0;
		    double total=0.0;
		    for (int ens=2;ens<col;ens++) {
		        mon_predict=mon_predict+predict[i][ens];
			total=total+1.0;
		    }
		    mon_predict=mon_predict/total;

	            // loop to each day
                    for (int day=0;day<monlen;day++) {
		    
    		        // create random seed
    		        double seed=Math.random();
    
    		        // calculate probability model
        		double prob=prob_a+(prob_b*mon_predict);
    
    		        // calculate gamma model
    		        double alpha=alpha_a+(alpha_b*mon_predict);
    		        double beta=beta_a+(beta_b*mon_predict);
    
    		        // initiate rain rate
    		        double rain=matlab.gamrnd(alpha,beta);
    		    
    		        // simulate rain probability
    		        if (seed<(1.0-prob)) {
    		        rain=0.0;
			} else {
			rain=rain+1.0;
    		        }

			// write data
			dataout[nwrite][3]=rain;
    
    		        // write date information
    		        dataout[nwrite][0]=getyear;
    		        dataout[nwrite][1]=getmonth;
    		        dataout[nwrite][2]=day+1;

			// add row increment
			nwrite=nwrite+1;

		    // terminate loop day
		    }

	      // terminate loop i	    
	      }


        // define output label 
     String[] label=new String[4];
     label[0]="year";
     label[1]="month";
     label[2]="day";
     label[3]="mean_ensemble";

     // save to awgfile 
     String fileout=dir+ident+"_rain_sim_meanens.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to simulate temperature model
public void simulate_temp(String filein,String filepredict_future,String dir,String ident,int modeltype) {

	// load file input
	filein=dir+ident+"_"+filein;
	filepredict_future=dir+ident+"_"+filepredict_future;
	double[][] regress=matlab.readawg(filein); 
	double[][] predict=matlab.readawg(filepredict_future);

	// get predictor size
	int row=predict.length;
	int col=predict[0].length;

	// define month length
	int[] monlen_norm={31,28,31,30,31,30,31,31,30,31,30,31};

	// generate main output array
	int getrow=0;
	for (int i=0;i<predict.length;i++) {
	    int getmonlen=0;
            int getyear=(int)predict[i][0];
            int getmonth=(int)predict[i][1];
	    if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	        getmonlen=29; 
	    } else {
	        getmonlen=monlen_norm[getmonth-1];
	    }
            for (int day=0;day<getmonlen;day++) {
            getrow=getrow+1;
	    }
 	}
	double[][] dataout=new double[getrow][col+1];

	// subset monthly data
	double[] monthval=new double[predict.length];
	for (int i=0;i<predict.length;i++) {
	    monthval[i]=predict[i][1];
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	} else {
          System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	}

	// loop to each ensemble
       for (int ens=2;ens<col;ens++) {
	      
       	     // initiate row to write
	     int nwrite=0;
	
	     // loop to each month in prediction data
	     for (int i=0;i<predict.length;i++) {
	
	            // obtain year and month
	            int getyear=(int)predict[i][0];
	            int getmonth=(int)predict[i][1];

	            // obtain temperature parameter
	            int pos=(int) modelclass[i];
	            double mean_a=regress[pos][0];
                    double mean_b=regress[pos][1];
	            double stdev_a=regress[pos][2];
	            double stdev_b=regress[pos][3];
	
		    // identify month length
		    int monlen=0;
	            if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	                 monlen=29; 
	            } else {
	                 monlen=monlen_norm[getmonth-1];
	            }

		    // obtain monthly predictor data
		    double mon_predict=predict[i][ens];

	            // loop to each day
                    for (int day=0;day<monlen;day++) {
		    
    		        // create random seed
    		        double seed=Math.random();
    
    		        // calculate mean value model
        		double mean=mean_a+(mean_b*mon_predict);
    
    		        // calculate stdev value model
    		        double stdev=stdev_a+(stdev_b*mon_predict);
    
    		        // initiate temperature simulation
    		        double temp=(stdev*matlab.randn())+mean;
    		    
			// write data
			dataout[nwrite][ens+1]=temp;
    
    		        // write date information
    		        if (ens==2) {
    		        dataout[nwrite][0]=getyear;
    		        dataout[nwrite][1]=getmonth;
    		        dataout[nwrite][2]=day+1;
    		        }

			// add row increment
			nwrite=nwrite+1;

		    // terminate loop day
		    }

	      // terminate loop i	    
	      }

        // terminate loop ens
	}

     // define output label 
     String[] label=new String[col+1];
     label[0]="year";
     label[1]="month";
     label[2]="day";
     for (int nlabel=3;nlabel<col+1;nlabel++){
     label[nlabel]="ensemble"+Integer.toString(nlabel);
     }

     // save to awgfile 
     String fileout=dir+ident+"_temp_sim.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to simulate temperature model ensemble
public void simulate_temp_meanens(String filein,String filepredict_future,String dir,String ident,int modeltype) {

	// load file input
	filein=dir+ident+"_"+filein;
	filepredict_future=dir+ident+"_"+filepredict_future;
	double[][] regress=matlab.readawg(filein); 
	double[][] predict=matlab.readawg(filepredict_future);

	// get predictor size
	int row=predict.length;
	int col=predict[0].length;

	// define month length
	int[] monlen_norm={31,28,31,30,31,30,31,31,30,31,30,31};

	// generate main output array
	int getrow=0;
	for (int i=0;i<predict.length;i++) {
	    int getmonlen=0;
            int getyear=(int)predict[i][0];
            int getmonth=(int)predict[i][1];
	    if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	        getmonlen=29; 
	    } else {
	        getmonlen=monlen_norm[getmonth-1];
	    }
            for (int day=0;day<getmonlen;day++) {
            getrow=getrow+1;
	    }
 	}
	double[][] dataout=new double[getrow][4];

	// subset monthly data
	double[] monthval=new double[predict.length];
	for (int i=0;i<predict.length;i++) {
	    monthval[i]=predict[i][1];
	}
	
	// classify seasonal and monthly model
	double[] modelclass=new double[monthval.length];
	if (modeltype==1) {
	    modelclass=matlab.getseason(monthval);
	} else {
          System.arraycopy(monthval,0,modelclass,0,modelclass.length);
	}

	// loop to each ensemble
        // for (int ens=2;ens<col;ens++) {
	      
       	     // initiate row to write
	     int nwrite=0;
	
	     // loop to each month in prediction data
	     for (int i=0;i<predict.length;i++) {
	
	            // obtain year and month
	            int getyear=(int)predict[i][0];
	            int getmonth=(int)predict[i][1];

	            // obtain temperature parameter
	            int pos=(int) modelclass[i];
	            double mean_a=regress[pos][0];
                    double mean_b=regress[pos][1];
	            double stdev_a=regress[pos][2];
	            double stdev_b=regress[pos][3];
	
		    // identify month length
		    int monlen=0;
	            if (((getyear % getmonth)==0.0) && getmonth==2.0) {
	                 monlen=29; 
	            } else {
	                 monlen=monlen_norm[getmonth-1];
	            }

		    // obtain monthly predictor data
		    double mon_predict=0;
		    double total=0.0;
		    for (int ens=2;ens<col;ens++) {
		        mon_predict=mon_predict+predict[i][ens];
			total=total+1.0;
		    }
		    mon_predict=mon_predict/total;

	            // loop to each day
                    for (int day=0;day<monlen;day++) {
		    
    		        // create random seed
    		        double seed=Math.random();
    
    		        // calculate mean value model
        		double mean=mean_a+(mean_b*mon_predict);
    
    		        // calculate stdev value model
    		        double stdev=stdev_a+(stdev_b*mon_predict);
    
    		        // initiate temperature simulation
    		        double temp=(stdev*matlab.randn())+mean;
    		    
			// write data
			dataout[nwrite][3]=temp;
    
    		        // write date information
    		        dataout[nwrite][0]=getyear;
    		        dataout[nwrite][1]=getmonth;
    		        dataout[nwrite][2]=day+1;

			// add row increment
			nwrite=nwrite+1;

		    // terminate loop day
		    }

	      // terminate loop i	    
	      }

        // terminate loop ens
	// }

        // define output label 
     String[] label=new String[4];
     label[0]="year";
     label[1]="month";
     label[2]="day";
     label[3]="mean_ensemble";

     // save to awgfile 
     String fileout=dir+ident+"_temp_sim_meanens.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
         
// terminate function
} 




// function to convert daily simulation to monthly
public void getrain_month(String filein,String dir,String ident) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] data=matlab.readawg(filein); 

	// get predictor size
	int row=data.length;
	int col=data[0].length;

	// define start and end time
	double startyear=data[0][0];
	double startmonth=data[0][1];
	double endyear=data[row-1][0];
	double endmonth=data[row-1][1];

	// define output length
	int countrow=1;
	double checkmonth=startmonth;
	for (int i=0;i<row;i++) {
             if (checkmonth!=data[i][1]) {
	     countrow=countrow+1;
	     checkmonth=data[i][1];
	     }
	}
	double[][] dataout= new double[countrow][col-1];
	System.out.println("monthrow:"+countrow);
	
        // loop to each ensemble
	for (int ens=3;ens<col;ens++) {

	        // begin calculate total rain
		int rowpos=0;
		double year=startyear;
		double month=startmonth;
        	
		// loop to each year
		while (year<=endyear&&month<=endmonth) {
	
		// subset data
		double total=0.0;
		for (int i=0;i<row;i++) {
		if (data[i][0]==year&&data[i][1]==month) {
		total=total+data[i][ens];
		}
		}

		// write to array
		dataout[rowpos][ens-1]=total;

		// write time definition to array
		if (ens==3) {
		dataout[rowpos][0]=year;
		dataout[rowpos][1]=month;
		}

		// add row increment
		rowpos=rowpos+1;

		// read next data
		if (month==12) {
		    year=year+1;
		    month=1;
		} else {
		    month=month+1;
		}
		
		// terminate while
		}

	// terminate loop ens	
	}

     // define output label 
     String[] label=new String[col-1];
     label[0]="year";
     label[1]="month";
     for (int nlabel=2;nlabel<col-1;nlabel++){
     label[nlabel]="ensemble"+Integer.toString(nlabel);
     }

     // save to awgfile 
     String fileout=dir+ident+"_rain_monthsim.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
      
// terminate function
}




// function to convert daily simulation to monthly for mean ensemble
public void getrain_monthmean(String filein,String dir,String ident) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] data=matlab.readawg(filein); 

	// get predictor size
	int row=data.length;
	int col=data[0].length;

	// define start and end time
	double startyear=data[0][0];
	double startmonth=data[0][1];
	double endyear=data[row-1][0];
	double endmonth=data[row-1][1];

	// define output length
	int countrow=1;
	double checkmonth=startmonth;
	for (int i=0;i<row;i++) {
             if (checkmonth!=data[i][1]) {
	     countrow=countrow+1;
	     checkmonth=data[i][1];
	     }
	}
	double[][] dataout= new double[countrow][col-1];
	System.out.println("monthrow:"+countrow);
	
        // loop to each ensemble
	// for (int ens=3;ens<col;ens++) {

	        // begin calculate total rain
		int rowpos=0;
		double year=startyear;
		double month=startmonth;
        	
		// loop to each year
		while (year<=endyear&&month<=endmonth) {
	
		// subset data
		double total=0.0;
		for (int i=0;i<row;i++) {
		if (data[i][0]==year&&data[i][1]==month) {
		total=total+data[i][3];
		}
		}

		// write to array
		dataout[rowpos][2]=total;

		// write time definition to array
		dataout[rowpos][0]=year;
		dataout[rowpos][1]=month;

		// add row increment
		rowpos=rowpos+1;

		// read next data
		if (month==12) {
		    year=year+1;
		    month=1;
		} else {
		    month=month+1;
		}
		
		// terminate while
		}

	// terminate loop ens	
	// }

     // define output label 
     String[] label=new String[col-1];
     label[0]="year";
     label[1]="month";
     label[2]="monthly_rain";

     // save to awgfile 
     String fileout=dir+ident+"_rain_monthsim_meanens.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
      
// terminate function
}





// function to convert daily simulation to monthly
public void gettemp_month(String filein,String dir,String ident) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] data=matlab.readawg(filein); 

	// get predictor size
	int row=data.length;
	int col=data[0].length;

	// define start and end time
	double startyear=data[0][0];
	double startmonth=data[0][1];
	double endyear=data[row-1][0];
	double endmonth=data[row-1][1];

	// define output length
	int countrow=1;
	double checkmonth=startmonth;
	for (int i=0;i<row;i++) {
             if (checkmonth!=data[i][1]) {
	     countrow=countrow+1;
	     checkmonth=data[i][1];
	     }
	}
	double[][] dataout= new double[countrow][col-1];
	System.out.println("monthrow:"+countrow);
	
        // loop to each ensemble
	for (int ens=3;ens<col;ens++) {

	        // begin calculate total rain
		int rowpos=0;
		double year=startyear;
		double month=startmonth;
        	
		// loop to each year
		while (year<=endyear&&month<=endmonth) {
	
		// subset data
		double total=0.0;
		double count=0.0;
		for (int i=0;i<row;i++) {
		if (data[i][0]==year&&data[i][1]==month) {
		total=total+data[i][ens];
		count=count+1.0;
		}
		}
		total=total/count;

		// write to array
		dataout[rowpos][ens-1]=total;

		// write time definition to array
		if (ens==3) {
		dataout[rowpos][0]=year;
		dataout[rowpos][1]=month;
		}

		// add row increment
		rowpos=rowpos+1;

		// read next data
		if (month==12) {
		    year=year+1;
		    month=1;
		} else {
		    month=month+1;
		}
		
		// terminate while
		}

	// terminate loop ens	
	}

     // define output label 
     String[] label=new String[col-1];
     label[0]="year";
     label[1]="month";
     for (int nlabel=2;nlabel<col-1;nlabel++){
     label[nlabel]="ensemble"+Integer.toString(nlabel);
     }

     // save to awgfile 
     String fileout=dir+ident+"_temp_monthsim.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
      
// terminate function
}




// function to convert daily simulation to monthly for mean ensemble
public void gettemp_monthmean(String filein,String dir,String ident) {

	// load file input
	filein=dir+ident+"_"+filein;
	double[][] data=matlab.readawg(filein); 

	// get predictor size
	int row=data.length;
	int col=data[0].length;

	// define start and end time
	double startyear=data[0][0];
	double startmonth=data[0][1];
	double endyear=data[row-1][0];
	double endmonth=data[row-1][1];

	// define output length
	int countrow=1;
	double checkmonth=startmonth;
	for (int i=0;i<row;i++) {
             if (checkmonth!=data[i][1]) {
	     countrow=countrow+1;
	     checkmonth=data[i][1];
	     }
	}
	double[][] dataout= new double[countrow][col-1];
	System.out.println("monthrow:"+countrow);
	
        // loop to each ensemble
	// for (int ens=3;ens<col;ens++) {

	        // begin calculate total rain
		int rowpos=0;
		double year=startyear;
		double month=startmonth;
        	
		// loop to each year
		while (year<=endyear&&month<=endmonth) {
	
		// subset data
		double total=0.0;
		double count=0.0;
		for (int i=0;i<row;i++) {
		if (data[i][0]==year&&data[i][1]==month) {
		total=total+data[i][3];
		count=count+1.0;
		}
		}
		total=total/count;

		// write to array
		dataout[rowpos][2]=total;

		// write time definition to array
		dataout[rowpos][0]=year;
		dataout[rowpos][1]=month;

		// add row increment
		rowpos=rowpos+1;

		// read next data
		if (month==12) {
		    year=year+1;
		    month=1;
		} else {
		    month=month+1;
		}
		
		// terminate while
		}

	// terminate loop ens	
	// }

     // define output label 
     String[] label=new String[col-1];
     label[0]="year";
     label[1]="month";
     label[2]="monthly_rain";

     // save to awgfile 
     String fileout=dir+ident+"_temp_monthsim_meanens.awg";
     System.out.println("writing formatted predictor to:"+fileout);
     matlab.saveawg(fileout,dataout,label);
      
// terminate function
}








// terminate main class
}
