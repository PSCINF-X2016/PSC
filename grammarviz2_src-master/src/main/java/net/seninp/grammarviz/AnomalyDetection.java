package net.seninp.grammarviz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class AnomalyDetection {
	public static void main(String[]args) throws Exception {
		

		double [][] mult=File2MultiSeries("C:\\Users\\TeTe\\Desktop\\grammarviz2_src-master\\data.txt");
		//System.out.println("hello there!");
		
		double[] tt=AverageCoverage( mult);
		
		//double[] tt1=NonSelfMatchDistance(mult,10);
		tt=Threshold(tt,0.9);
		FromArray2File(tt,"test20","averageCoverage");
		//FromArray2File(tt1,"test21","NonSelfMatchDistance");
		
		/*
		double [][] ts=new double[2][5];
		for (int i=0;i<2;i++) {
			for(int j=0;j<5;j++) {
				ts[i][j]=i+j;
			}
		}
		double []dis=Coverage_AND_distance.sumD(ts);
		for(int i=0;i<dis.length;i++) {
			System.out.print(" "+dis[i]);
		}
		*/
	}
	public static double [] Threshold(double [] AnormalList, double threshold) {
		double [] temp=Coverage_AND_distance.Normalisation(AnormalList);
		double [] anormal=temp;
		for(int i=0;i<AnormalList.length;i++) {
			if(temp[i]>threshold) {
				anormal[i]=1;
			}
			else
				anormal[i]=0;
		}
		return anormal;
	}
	public static double [] AnormalPercentage(double []anormal , double percentage) {
		double[]temp=anormal;
		assert(percentage<1);
		Arrays.sort(anormal);
		int index=(int) (percentage*anormal.length);
		double threshold=anormal[index];
		for(int i=0;i<anormal.length;i++) {
			if(temp[i]>threshold) {
				anormal[i]=1;
			}
			else
				anormal[i]=0;
		}
		return anormal;
	}
	/**
	 * From file to a multi sereies
	 * @param Filename
	 * @return multiseries
	 * @throws Exception
	 */
	public static double[][] File2MultiSeries(String Filename) throws Exception {
		ReadFile fr = new ReadFile();
	    double[][] multiseries = fr.createArray(Filename);
	    return multiseries;
	}
	/**
	 * 
	 * @param args explained at the end of the code
	 * @param multiseries multi-time-double-series
	 * @return AverageCoverage array, type double, the smaller the data is, the more abnormal this point is
	 * @throws Exception
	 */
	public static double[] AverageCoverage(double[][] mult) throws Exception {
		return Coverage_AND_distance.Average_Coverage(mult);
	}
	/**
	 * 
	 * @param args
	 * @param multiseries
	 * @return The greater, the danger it is.
	 * @throws Exception
	 */
	public static double[] NonSelfMatchDistance(double[][] mult,double distance_rate) throws Exception {
		return Coverage_AND_distance.Distance(mult,distance_rate);
	}
	
	private static void FromArray2File(double[] series,String outputPrefix,String anormalType) throws IOException{
		  
		  if (!(outputPrefix.isEmpty())) {

		      // write the coverage array
		      //
		      String currentPath = new File(".").getCanonicalPath();
		      
		      BufferedWriter bw = new BufferedWriter(
		          new FileWriter(new File(currentPath + File.separator + outputPrefix + "_"+anormalType+".txt")));
		      for (double i :series) {
		        bw.write(i+ "\n");
		      }
		      bw.close();
		  }
		  
	  }
}
/**args can be:
 *Usage: <main class> [options] 
  Options:
    --algorithm, -alg
       The algorithm to use
       Default: RRA
       Possible Values: [BRUTEFORCE, HOTSAX, RRA, RRAPRUNED, RRASAMPLED, EXPERIMENT]
    --alphabet_size, -a
       SAX alphabet size
       Default: 4
    --bounds, -b
       RRASAMPLED grid boundaries (Wmin Wmax Wstep Pmin Pmax Pstep Amin Amax
       Astep)
       Default: 10 100 10 10 50 10 2 12 2
    --data, -i
       The input file name
    --discords_num, -n
       The algorithm to use
       Default: 5
    --gi, -g
       GI algorithm to use
       Default: Sequitur
       Possible Values: [Sequitur, Re-Pair]
    --help, -h
       
       Default: false
    --output, -o
       The output file prefix
       Default: <empty string>
    --strategy
       Numerosity reduction strategy
       Default: EXACT
       Possible Values: [NONE, EXACT, MINDIST]
    --subsample
       RRASAMPLED subsampling fraction (0.0 - 1.0) for longer time series
       Default: NaN
    --threshold
       Normalization threshold
       Default: 0.01
    --window_size, -w
       Sliding window size
       Default: 170
    --word_size, -p
       PAA word size
       Default: 4
 * 
 */
