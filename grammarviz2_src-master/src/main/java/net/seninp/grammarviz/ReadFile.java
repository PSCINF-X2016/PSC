package net.seninp.grammarviz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    /**
     * ��ȡ�ļ�
     * @param filePath
     * @return
     */
    public static  List  readTxtFile(String filePath) {
  
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.startsWith("#"))
                        list.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("�Ҳ����ļ�");
            }
        } catch (Exception e) {
            System.out.println("������");
            e.printStackTrace();
        }
        return list;
  
    }
      
    /**
     * ������ά����
     * @param list
     * @return
     * @throws IOException 
     */
    public static double[][] createArray(String filePath) throws IOException{
    	//String currentPath = new File(".").getCanonicalPath();
        List<String> list = readTxtFile(filePath);
        double array[][] = new double[list.size()][];
        for(int i=0;i<list.size();i++){
            String linetxt=list.get(i);
            String[] myArray = linetxt.replaceAll(",", "@").split("@");
            array[i] = new double[myArray.length];
            for(int j=0;j<myArray.length;j++){
            	array[i][j]=Double.valueOf(myArray[j]);
            }
        }
        double[][] transpose = new double[array[0].length][array.length];
        for(int i=0;i<array.length;i++){
        	for(int j = 0 ; j< array[0].length;j++){
        		transpose[j][i] = array[i][j];
        	}
        }
        return transpose;
    }
      
    /**
     * ��ӡ����
     * @param array
     */
    public static void printArray(String array[][]){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(j!=array[i].length-1){
                    System.out.print("array["+i+"]["+j+"]="+array[i][j]+",");
                }
                else{
                    System.out.print("array["+i+"]["+j+"]="+array[i][j]);
                }
                  
            }
            System.out.println();
        }
    }
  
      
      
}
