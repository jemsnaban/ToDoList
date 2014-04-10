package todo.List;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Test {
	public static void main(String[] args) throws IOException {
		String str;
	    String path = "D:\\go.txt"; // you can input also..i created this way :P

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pw = new PrintWriter(new FileWriter(path, true));
	    
		
		       while(true)
		        {
		            System.out.println("Enter the text : ");
		            str = br.readLine();
		            if(str.equalsIgnoreCase("exit"))
		                break;
		            else
		                pw.write(str);
		            pw.close();
		            
		        }
		    
	}
	
}
