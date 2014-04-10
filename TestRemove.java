package todo.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TestRemove {
	private static final String PESAN_WELCOME = "====Selamat datang di ToDoList! %1$s siap digunakan===";
	private static final String PESAN_KOSONG = "file %1$s kosong";

	static Scanner scanner = new Scanner(System.in);
	static int hitung = 0;
	static String[] Array = new String[100];

	public static void main(String[] args) throws IOException {
		showToUser(PESAN_WELCOME,args[0]);

		int back = 0;
		while (back != 1) {
			String userCommand;
			System.out.printf("Command : ");
			userCommand = scanner.nextLine();
			String block[] = userCommand.split(" ", 2);

			if (block[0].compareTo("add") == 0) {
				Array[hitung] = block[1];
				hitung++;
				saveStringToFile("d:\\tugasku.txt",Array);
			} else if (block[0].compareTo("display") == 0) {
				displayFileContent("d:\\tugasku.txt");
			} else if (block[0].compareTo("sort") == 0) {
				sortStrings("d:\\tugasku.txt");
			} else if (block[0].compareTo("delete") == 0) {
				String tampungan = block[1];
				deleteLines("d:\\tugasku.txt",
						tampungan);
			} else if (block[0].compareTo("clear") == 0) {
				clearFileContent("d:\\tugasku.txt");
				showToUser(PESAN_KOSONG,args[0]);
			} else if (block[0].compareTo("exit") == 0) {
				back = 1;
			} else {
				System.out.println("command salah");
			}
		}
	}

	public static void showToUser(String feedback,String FileName) {
		System.out.printf(feedback,FileName);
	}

	public static void saveStringToFile(String fileName, String[] saveString)
			throws IOException {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			for(int i = 0; i<hitung; i++){
				bw.write((i+1) + ". " + saveString[i]);
				bw.newLine();
			}
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void deleteLines(String fileNames, String linesToRemove)
			throws IOException {
		File tempFile = new File("D:\\temp.txt");
		int delete = Integer.valueOf(linesToRemove);
		
		if(delete > 0 && delete <= hitung){
			for(int i = delete-1;i<hitung;i++){
				Array[i]=Array[i+1];
			}
			hitung -=1;
			BufferedReader br = new BufferedReader(new FileReader(fileNames));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;
		// Read from the original file and write to the new
		// unless content matches data to be removed.
		while ((line = br.readLine()) != null) {

			if (!line.trim().startsWith(linesToRemove)) {

				pw.println(line);
				pw.flush();
			}
		}
		pw.close();
		br.close();

		BufferedReader reader = new BufferedReader(new FileReader(tempFile));
		PrintWriter writer = new PrintWriter(new FileWriter(fileNames));
		String newLine = null;
		int i=0;

		// Read from the original file and write to the new
		// unless content matches data to be removed.
		while ((newLine = reader.readLine()) != null) {
			String split[] = newLine.split(" ",2);
			writer.println((i+1) + ". " + split[1]);
			i++;
			writer.flush();
		}

		writer.close();
		reader.close();
		tempFile.delete();
		}

		
	}

	private static String displayFileContent(String fileName) throws IOException {
		FileReader displayFile = new FileReader(fileName);
		BufferedReader display = new BufferedReader(displayFile);

		StringBuilder tampDisplay = new StringBuilder();
		String line = display.readLine();
		if (line != null) {
			while (line != null) {
				tampDisplay.append(line);
				tampDisplay.append("\n");
				line = display.readLine();
			}

			String show = tampDisplay.toString();
			System.out.println(show);
		} else {
			System.out.println("data kosong");
		}
		display.close();
		return line;
	}

	private static void sortStrings(String fileName) throws IOException {
		Arrays.sort(Array, 0, hitung);
		try{
			FileWriter writeFile = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(writeFile);

			for(int i = 0; i<hitung; i++){
				writer.write((i+1) + ". " + Array[i]);
				writer.newLine();
			}
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void clearFileContent(String fileName) throws IOException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();

	}
}
