package todo.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ToDoList {
	private static final String PESAN_WELCOME = "====Selamat datang di ToDoList! %1$s siap digunakan===";
	private static final String PESAN_KOSONG = "file %1$s kosong";

	// private static final String PESAN_ADD =
	// "Ditambahkan pada %1$s: \"%2$s\"";

	// Tipe command dalam program
	enum COMMAND_TYPE {
		ADD, DISPLAY, SORT, DELETE, CLEAR, EXIT
	};

	// Untuk menandakan nomor tugas tidak ada
	// private static final int NOT_FOUND = -1;

	static Scanner scanner = new Scanner(System.in);
	static int hitung = 1;

	public static void main(String[] args) throws IOException {
		showToUser(PESAN_WELCOME);

		int back = 0;

		while (back != 1) {
			String userCommand = readUserCommand();
			String block[] = userCommand.split(" ", 2);

			if (block[0].compareTo("add") == 0) {
				String tampung = block[1];
				//int hitung = 1;
				saveStringToFile(
						"D:\\tugas2.txt",
						tampung,hitung);
				hitung ++;
			} else if (block[0].compareTo("display") == 0) {
				displayFileContent("D:\\tugas2.txt");
			} else if (block[0].compareTo("sort") == 0) {
				sortStrings("D:\\tugas2.txt");
			} else if (block[0].compareTo("delete") == 0) {
				String tampungan = block[1];
				deleteLines("D:\\tugas2.txt",
						tampungan);
			} else if (block[0].compareTo("clear") == 0) {
				clearFileContent("D:\\tugas2.txt");
				showToUser(PESAN_KOSONG);
			} else if (block[0].compareTo("exit") == 0) {
				back = 1;
			} else {
				System.out.println("command salah");
			}
		}
	}

	public static void showToUser(String feedback) {
		System.out.println(feedback);
	}

	private static String readUserCommand() throws IOException {

		System.out.printf("Command : ", (System.in));

		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(System.in));

		// Menginisialisasi String
		String string = " ";

		// Mengambil String dari keyboard
		try {
			string = bfr.readLine();

		} catch (IOException ex) {
			System.out.println(ex);
		}
		// Mengambil string hasil pembacaan dari keyboard
		return string;
	}

	public static boolean saveStringToFile(String fileName, String saveString,int hitung)
			throws IOException {
		boolean saved = false;
		//int hitung = 1;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,
					true));
			bw.write(hitung + ". " + saveString);

			bw.newLine();
			hitung ++;
			
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	private static void deleteLines(String fileNames, String linesToRemove)
			throws IOException {
		File tempFile = new File(
				"D:\\temp.txt");

		BufferedReader br = new BufferedReader(new FileReader(fileNames));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;
		// Read from the original file and write to the new
		// unless content matches data to be removed.
		while ((line = br.readLine()) != null) {

			if (!line.trim().equals(linesToRemove)) {

				pw.println(line);
				pw.flush();
			}
		}
		pw.close();
		br.close();

		BufferedReader reader = new BufferedReader(new FileReader(tempFile));
		PrintWriter writer = new PrintWriter(new FileWriter(fileNames));
		String newLine = null;

		// Read from the original file and write to the new
		// unless content matches data to be removed.
		while ((newLine = reader.readLine()) != null) {
			writer.println(newLine);
			writer.flush();

		}

		writer.close();
		reader.close();
		tempFile.delete();
	}

	private static String displayFileContent(String fileName)
			throws IOException {

		//int temp = 0;

		FileReader displayFile = new FileReader(fileName);
		BufferedReader display = new BufferedReader(displayFile);

		StringBuilder tampDisplay = new StringBuilder();
		String line = display.readLine();
		if (line != null) {
			while (line != null) {

				//tampDisplay.append((temp + 1) + ".");
				tampDisplay.append(line);
				tampDisplay.append("\n");
				line = display.readLine();

				//temp++;
			}

			String tampil = tampDisplay.toString();
			System.out.println(tampil);
			display.close();

		} else {
			System.out.println("data kosong");
		}
		display.close();
		return line;
	}

	private static void sortStrings(String fileName) throws IOException {
		ArrayList<String> rows = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		String s;
		String tampung[];
		while ((s = reader.readLine()) != null){
			tampung = s.split(" ",2);
			rows.add(tampung[1]);
		}
			

		Collections.sort(rows);

		FileWriter writer = new FileWriter(fileName);
		for (String cur : rows)
			writer.write(cur + "\n");

		reader.close();
		writer.close();
	}

	private static void clearFileContent(String fileName) throws IOException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();

	}
}
