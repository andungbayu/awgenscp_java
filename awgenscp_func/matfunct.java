package awgenscp_func;

//start import packages
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.text.*;


// define main class
public class matfunct {



// function dlmwrite
    public double[][]  dlmread(String filename) {

        // notify progress
        System.out.println("Reading data... \n");
        
        // define variables
        Scanner scanner;
        int i = 0;
        int j = 0;
        int row = 0;
	    int column = 0;
        int getcolumn;
        double[][] nums = new double[1][1];
        double[][] copy = new double[1][1];        

        // begin try reading
        try {
        
            // define scanner
            scanner = new Scanner(new File(filename));
            scanner.useDelimiter(",");
            
            // define column and row
            while (scanner.hasNext()) {
 
                // reading line
                String line = scanner.nextLine();
            
                // split lines
                String[] fields = line.split(",");

                // check number of array fields
                getcolumn = fields.length;
                
                // check column 
                if (getcolumn>column) {
                column=getcolumn;
                }

                // add increment
                row = row+1;

            // end while loop
            }

            // close scanner
            scanner.close();

            // add row and column        
            nums = new double[row][column];
           
            // notify debug
            System.out.println("row " + nums.length + " col " + nums[0].length);
           
        // end try
        }

        // catch error
        catch (FileNotFoundException ex) {
        Logger.getLogger(matfunct.class.getName()).log(Level.SEVERE, null, ex);
        }


        // ------------------ begin try reading for second attemp-----------------------------
        try {
        
            // define scanner
            scanner = new Scanner(new File(filename));
            scanner.useDelimiter(",");
            
            // reading data
            while (scanner.hasNext()) {
  
                // reading line
                String line = scanner.nextLine();
            
                // split lines
                String[] fields = line.split(",");
				//System.out.println(line);
				

                // check number of array fields
                column = fields.length;
                
                // write data
                for (j=0;j<column;j++) {
                nums[i][j] = Double.parseDouble(fields[j]);
                System.out.println("reading " + " "+ i +" "+ j + " "+nums[i][j]);
                }

                // add increment
                i = i+1;

            // end while loop
            }

            // close scanner
            scanner.close();

        // end try
        }

        // catch error
        catch (FileNotFoundException ex) {
        Logger.getLogger(matfunct.class.getName()).log(Level.SEVERE, null, ex);
        }

    // return result
    return nums;

// end class dlmread
}



// function datenum
    public long datenum(int year,int month, int day) {

    // initiate return value
    long epoch=-9999;

    // define calendar time
    String getyear=Integer.toString(year);
    String getmonth=Integer.toString(month);
    String getday=Integer.toString(day);
    String str = getyear+"-"+getmonth+"-"+getday;
    //System.out.println(str);
    
    // convert date
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
    Date date = df.parse(str);
    epoch = date.getTime();
    //System.out.println(date);
    }
    catch (ParseException e) {
    }
    return epoch;

// end class datenum
}




// function datevec
    public int[] datevec(long newepoch) {

    // initiate return value
    int[] getdate = new int[3];
   
    // convert date
    Date date = new Date(newepoch);
    SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
    String getdatestr = df.format(date);
    String[] datestr = getdatestr.split(",");

    // convert to int
    for (int i=0;i<3;i++) {
    getdate[i]=Integer.parseInt(datestr[i]);
    //System.out.println(date);
    }
    
    return getdate;

// end class datevec
}



// funtion saveawg
    public void saveawg(String filename,double data[][],String[] label) {

    // identify matrix size
    int row = data.length;
    int col = data[0].length;

    // write to file
    try{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    // write data size
   writer.write(Integer.toString(row)+"\n");
   writer.write(Integer.toString(col)+"\n");

    // write label
    for (int i=0;i<label.length;i++) {
    if (i<(label.length)-1) {
    writer.write(label[i]+",");
    } else {
    writer.write(label[i]+"\n");
    }
    }

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
       // Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }
// end funtion saveawg
}



public double[][] readawg(String thisfile) {
        System.out.println("Tryin to read data at "+thisfile+" \n");
        Scanner scanner;
        int columns;
        int row;
        String [] getlabel = new String [1];
        double [][] nums = new double [1][1];
        
        // begin try reading
        try {
            // define scanner
            scanner = new Scanner(new File(thisfile));
            scanner.useDelimiter(",");
            
            // define column and row
            row = Integer.parseInt(scanner.nextLine());
            columns = Integer.parseInt(scanner.nextLine());

            // read label line
            String linelabel = scanner.nextLine();
            String[] labelfields = linelabel.split(",");
            
            //  store label
            getlabel = new String[columns];
            for (int i = 0; i < columns; i++) {
                getlabel[i] = labelfields[i];
            }
            
            // create table array to store data
            nums = new double[row][columns];
            
            // reading data
            for (int i = 0; i < row; i++) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            for (int j = 0; j < columns; j++) {
            nums[i][j] = Double.parseDouble(fields[j]);
            }
            }  
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nums;
// end function readawg
}



// fuction rainprob estimator
public double rainprob_estimator(double[] datain) {

    // define default threshold
    double threshold=1;

    // count data
    int count=0;
	int total=0;
	for (int i=0;i<datain.length;i++) {
		
		// count rain events
		if (datain[i]>=threshold) {
			count=count+1;
		}
		
		// count totaldata
		if (datain[i]>=0.0) {
			total=total+1;
		}		
		
	//terminate loop
	}

    // get probability
    double prob=(double) count/total;
	System.out.println("count:"+count+" total:"+total+" prob:"+prob);
        if (Double.isNaN(prob)) {
        prob=-9999;
        }
        if (Double.isInfinite(prob)) {
        prob=-9999;
        }
	return prob;
  
// terminate function
}



// fuction gamma estimator
public double[] gamma_estimator(double[] datain) {

    // define default threshold
    double threshold=1;
	double[] gamma=new double[2];
	Arrays.fill(gamma,0);

    // count data
    int count=0;
	for (int i=0;i<datain.length;i++) {
		
		// count rain events
		if (datain[i]>=threshold) {
			count=count+1;
		}
		
	//terminate loop
	}
	
	// calculate gammma if data exit
	if (count>1) {
		
		// calculate mean
		double meanval=mean(datain,-9999);
		
		// calculate stdev
		double stdev=std(datain,-9999);
		
		// calculate gamma function
		double shape=Math.pow((meanval/stdev),2);
                double scale=meanval/shape;
                
                // check valid value
                if (Double.isNaN(shape)) {
                shape=-9999;
                }
                if (Double.isInfinite(shape)) {
                shape=-9999;
                }
                if (Double.isNaN(scale)) {
                shape=-9999;
                }
                if (Double.isInfinite(scale)) {
                shape=-9999;
                }
		
		// add result
		gamma[0]=shape;
		gamma[1]=scale;
	}

    // get result
	System.out.println("gamma:"+gamma[0]+" "+gamma[1]);
	return gamma;
  
// terminate function
}


// funtion to calculate mean
public double mean(double[] data,double filter) {
	
	// initiate data
	double sum=0;
	double count=0;
	int row=data.length;
	
	// loop to get mean
	for(int i=0;i<row;i++) {
		if (data[i]!=filter){
			sum=sum+data[i];
			count=count+1;
		}
	}
	
	// get mean
	double meanval=sum/count;
	System.out.println("mean:"+meanval);
	return meanval;
}



// function to calculate stdev
public double std(double[] data,double filter) {
	
	// initiate data
	double sum=0;
	double count=0;
	int row=data.length;
	
	// calculate mean
	double meanval=mean(data,-9999);
	
	// loop to get mean
	for(int i=0;i<row;i++) {
		if (data[i]!=filter){
			double diff=data[i]-meanval;
			diff=diff*diff;
			sum=sum+diff;
			count=count+1;
		}
	}
	
	// get stdev
	double stdev=sum/count;
	stdev=Math.sqrt(stdev);
	System.out.println("stdev:" + stdev) ;
	return stdev;
}



// function normal distribution estimator
public double[] normaldist_estimator(double[] datain,double filter) {

    // calculate mean
    double meanval=mean(datain,filter);

    // calculate stdev	
    double stdev=std(datain,filter);

    // copy result to main array
    double[] normdist=new double[2];
   normdist[0]=meanval;
   normdist[1]=stdev; 

   // return result
   return normdist;
}



// regression function 
public double[] regress(double[] x,double[] y) {

    // get data length
    int row=x.length;

    // generate regression matrix
    double[][] matrix=new double[row][5];

    // loop to matrix
    for (int i=0;i<row;i++) {
        matrix[i][0]=x[i];
        matrix[i][1]=y[i];
	matrix[i][2]=x[i]*y[i];
	matrix[i][3]=x[i]*x[i];
	matrix[i][4]=y[i]*y[i];
      }

    // calculate matrix sum
    double[] matrixsum=new double[5];
    for (int ncol=0;ncol<5;ncol++) {
    for (int nrow=0;nrow<row;nrow++) {
        matrixsum[ncol] = matrixsum[ncol]+ matrix[nrow][ncol];
    }}

    // calculate param_alpha
    double param_a=((matrixsum[1]*matrixsum[3])-(matrixsum[0]*matrixsum[2]))/((row*matrixsum[3])-(matrixsum[0]*matrixsum[0]));

    // calculate param_beta
    double param_b=((row*matrixsum[2])-(matrixsum[0]*matrixsum[1]))/((row*matrixsum[3])-(matrixsum[0]*matrixsum[0]));

    // copy to output
    double[] output=new double[2];
    output[0]=param_a;
    output[1]=param_b;
    return output;
}	



// data count equal function
public int countequal(double[] datain,double equalvalue) {

	// get length
	int row=datain.length;

	// loop to each data
	int count=0;
	for (int i=0;i<row;i++) {
	if (datain[i]==equalvalue) {
	    count=count+1;
	}
	}

	// return result
	return count;
}




// function to convert monthly data to season
public double[] getseason(double[] month) {

	// create output array
	int row=month.length;
	double[] dataout=new double[row];

	// loop to index season
	for (int i=0;i<row;i++) {
	
	      // classify month
	      if ((month[i]==3)||(month[i]==4)||(month[i]==5)) {
	            dataout[i]=0;
	      } else if ((month[i]==6)||(month[i]==7)||(month[i]==8)) {
	            dataout[i]=1;      
	      } else if ((month[i]==9)||(month[i]==10)||(month[i]==11)) {
                    dataout[i]=2;
	      } else if ((month[i]==12)||(month[i]==1)||(month[i]==2)) {
                    dataout[i]=3;
	      } 
	}

	// return result
	return dataout;
} 



// function to generate gamma random value
public double gamrnd(double k,double theta) {

	// initiate variables
    	boolean accept = false;
	Random rng = new Random();
        //Calendar.getInstance().getTimeInMillis() +
        //Thread.currentThread().getId());

	// check parameter
        if (k < 1) {

                // Weibull algorithm
 		double c = (1 / k);
 		double d = ((1 - k) * Math.pow(k, (k / (1 - k))));
 		double u, v, z, e, x;
 		do {
  		u = rng.nextDouble();
  		v = rng.nextDouble();
  		z = -Math.log(u);
  		e = -Math.log(v);
  		x = Math.pow(z, c);
                
		// check condition
		if ((z + e) >= (d + x)) {
                accept = true;
                }

                // alternate condtion to wihle
                } while (!accept);
                return (x * theta);
        } else {

                // Cheng's algorithm
                double b = (k - Math.log(4));
                double c = (k + Math.sqrt(2 * k - 1));
                double lam = Math.sqrt(2 * k - 1);
                double cheng = (1 + Math.log(4.5));
                double u, v, x, y, z, r;
 
		// loop condition
		do {
                u = rng.nextDouble();
                v = rng.nextDouble();
                y = ((1 / lam) * Math.log(v / (1 - v)));
                x = (k * Math.exp(y));
                z = (u * v * v);
                r = (b + (c * y) - x);

		// check condition
	      	if ((r >= ((4.5 * z) - cheng)) ||
                (r >= Math.log(z))) {
                accept = true;
                }

		// alternate condition
                } while (!accept);
                return (x * theta);
         }

// terminate function
}




// function to generate gamma random value (failed)
public double gamrnd1(double alpha,double lambda) {

       // initiate variables
       double v=-9999;
	
       // check if alpha less than one
       double oldalpha;
       if (alpha<=1) {
             oldalpha=alpha;
             alpha=alpha+1;
       } else {
             alpha=alpha;
             oldalpha=alpha;
       }

       // initiate value
       double d=alpha-(1/3);
       double c=1/Math.sqrt(9.0*d);
       int flag = 1;

       // loop while condition
       while (flag==1) {
       
	       // get a random normal value
	       double z=randn();

	       // calculate v
	       if (z>-1/c) {
	       	    v=Math.pow((1+(c*z)),3);
	            double u=Math.log(Math.random());
	            double k=(0.5*Math.pow(z,2))+d-(d*v)+(d*Math.log(v));

	           // check iteration
	           if (u>k) {
	           flag=0;
	           }
	      }	   
        }

	// return value       
	double x=d*v/lambda;

	// recalculate value if alpha < 1
	if (oldalpha<=1){
	x=x*Math.pow(Math.random(),(1.0/oldalpha));
	}

	// add to return value
	return x;

// terminate function
}





// function to generate random normal distribution
public double randn() {

	// initiate random object
	Random val=new Random();

        // copy value to output
	double getrand=val.nextGaussian();

       // return value
       return getrand;	

// terminate function
}




// end class matfunc
} 
