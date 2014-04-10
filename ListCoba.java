package todo.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ListCoba {
private static int count = 0;

	public static void main(String[] args) throws IOException {
		String[] tamp = new String[100];
		Scanner scanner = new Scanner(System.in);
		String inputUser;
		int ulang = 0;
		
		
		
		while (ulang != 1) {
			System.out.print("command: ");
			inputUser = scanner.nextLine();
			
			String pisah[] = inputUser.split(" ", 2);
			
			if (pisah[0].compareTo("add") == 0) {
					
					tamp[count] = pisah[1];
					count++;
					FileWriter tulisFile = new FileWriter("d:\\tugasku.txt");
					BufferedWriter tulis = new BufferedWriter(tulisFile);
					
					for(int i = 0; i<count; i++){
						tulis.write((i+1) + ". " + tamp[i]);
						tulis.newLine();
					}
					
					tulis.close();
					System.out.println("done");
					
					
			} 
			else if (pisah[0].compareTo("display") == 0) {
					FileReader displayFile = new FileReader("d:\\tugasku.txt");
					BufferedReader display = new BufferedReader(displayFile);
					
					StringBuilder tampDisplay = new StringBuilder();
					String line = display.readLine();
					if(line != null){
						while(line != null){
							tampDisplay.append(line);
							tampDisplay.append('\n');
							line = display.readLine();
						}
						String tampil = tampDisplay.toString();
						System.out.println(tampil);
						display.close();
					}
					else{
						System.out.println("tidak ada data");
					}
			}
			else if(pisah[0].compareTo("sort") == 0){
				Arrays.sort(tamp, 0, count);
				
				FileWriter tulisFile = new FileWriter("d:\\tugasku.txt");
				BufferedWriter tulis = new BufferedWriter(tulisFile);
				
				for(int i = 0; i<count; i++){
					tulis.write((i+1) + ". " + tamp[i]);
					tulis.newLine();
				}
				
				tulis.close();
				System.out.println("done");
			}
			else if(pisah[0].compareTo("clean") == 0){
				for(int i=0;i<count;i++){
					tamp[i]="";
				}
				count = 0;
				
				FileWriter tulisFile = new FileWriter("d:\\tugasku.txt");
				BufferedWriter tulis = new BufferedWriter(tulisFile);
				
					tulis.write("");
				
				
				tulis.close();
				System.out.println("done");
			
			}
			else if(pisah[0].compareTo("delete")==0){
				
				
				
				int delete = Integer.valueOf(pisah[1]);
				
				if(delete <= count){
					for(int i = delete-1;i<count;i++){
						tamp[i]=tamp[i+1];
					}
					count -=1;
				
					FileWriter tulisFile = new FileWriter("d:\\tugasku.txt");
					BufferedWriter tulis = new BufferedWriter(tulisFile);
				
					for(int i = 0; i<count; i++){
						tulis.write((i+1) + ". " + tamp[i]);
						tulis.newLine();
					}
				
					tulis.close();
						System.out.println("done");
				}
				else
				{
					System.out.println("pilihan tidak diketahui");
				}
				
			}
			else if(pisah[0].compareTo("exit") == 0){
				ulang = 1;
			}
			else{
				System.out.println("command salah");
			}
		}
		
		
		scanner.close();
		
	}

}
