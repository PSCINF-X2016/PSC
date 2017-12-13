package net.seninp.grammarviz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateData {
	public static void main(String[] args) throws IOException{
		String currentPath = new File(".").getCanonicalPath();
		BufferedWriter bw = new BufferedWriter(
		          new FileWriter(new File(currentPath + File.separator + "data.txt")));
		for (int i = 0; i <= 500; i++) {
			for(int j = 0;j< 5;j++){
				double d = Math.random();
				bw.write(d+" ");
			}
			bw.write("\n");
		     
		}
		bw.close();
	}
}
