package todo.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RemoveBooks {
	// construct temporary file
	public static void main(String[]args)throws IOException {
	String title;

	 Scanner titlerem= new Scanner (System.in);
	 System.out.println("Enter Title to remove from file");
	 title = titlerem.next ();

	 // construct temporary file
	 File inputFile = new File("books.txt");
	 File tempFile = new File(inputFile + "temp.txt");

	 BufferedReader br = new BufferedReader (new FileReader("books.txt"));
	 PrintWriter Pwr = new PrintWriter(new FileWriter (tempFile));
	 String line = null;

	 //read from original, write to temporary and trim space, while title not found
	 while((line = br.readLine()) !=null) {
	     if(line.trim().startsWith(title)){
	         continue;          }
	     else{
	         Pwr.println(line);
	         Pwr.flush();

	     }
	 }
	 // close readers and writers
	 br.close();
	 Pwr.close();
	 titlerem.close();

	 // delete book file before renaming temp
	 inputFile.delete();

	 // rename temp file back to books.txt
	 if(tempFile.renameTo(inputFile)){
	        System.out.println("Update succesful");
	    }else{
	        System.out.println("Update failed");
	    }
	}
}
