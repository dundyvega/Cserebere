package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

final public class FileOperator {
	
	
	
	private static int honap;
	private static int napok;
	private static int kezdo;
	private static String expertM;
	private static String expertCs;
	private static String t1t2M;
	private static String t1t2Cs;
	private static String fix;
	private static String spec;
	private static String HVHT;
	private static String HTHV;
	private static String lineSzabadHK;
	private static String lineDelutanok;
	private static String lineDelelottok;
	private static String maradjon;
	private static String megj;
	private static String multHonap;
	private static int napokPrew;
	
	

	public static User getUserInfo(String expertCsiri, String [] speckok) throws IOException {
		
		FileInputStream excelFile = new FileInputStream(expertCsiri);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet datatypeSheet = workbook.getSheetAt(0);
		
		Iterator<Row> iterator = datatypeSheet.iterator();
		Row currentRow = iterator.next();
		
		boolean megtalalt = false;
		
		User user = null;
		
		while (iterator.hasNext() && !megtalalt) {
			
			 currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			
			Cell userNameCell = cellIterator.next();
			String userName = userNameCell.getStringCellValue();
			
			
			// megkeressük a felhasználót
			if (userName.equals(System.getProperty("user.name"))) {
				
				megtalalt = true;
				
				Cell nameC = cellIterator.next();
				String name = nameC.getStringCellValue();
				
				//létrehozzuk az usert
				
				user = new User(name);
				
				
				int beok = 0;
				
				while (cellIterator.hasNext()) {
					//Cell currentCell = cellIterator.next();
	                //getCellTypeEnum shown as deprecated for version 3.15
	                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					
					//System.out.println("kacsa");
					nameC = cellIterator.next();
					name = nameC.getStringCellValue();
					
					Beo beo = new Beo(name);
					user.addToList(beo);
					beok++;
					
					//ide még jön a beo tip beállítás
					/**
					 * 
					 * 
					 * 
					 * 
					 * 
					 */
					
					
					/*
					 * mivel még nem tudjuk, hogy hol vannak a hétvégék/hétköznapok, a szabad napokat bejelöljük,
					 */
					if (!name.equals("") && !name.equals(null))
					if (name.charAt(0) == '0' || (name.charAt(0) == '1' && (name.charAt(1) == '0' || name.charAt(1) == '1'))) {
						
						beo.setUserke(dolgozik.dolgVde);
					} else {
						
						if (name.charAt(0) == '1') {
							beo.setUserke(dolgozik.dolgVdu);
							
							
						} else  if (name.charAt(0) == '2') {
							
							beo.setUserke(dolgozik.dolgE);
							
						}
						
						else {
							beo.setUserke(dolgozik.szabadV);
							
							//System.out.println(name + "kurv");
						}
					}
					
				
					
					for (String s: speckok) {
						
						if (name.toUpperCase().contains(s.toUpperCase())) {
							
							//ha speciális
							
							beo.setUserke(dolgozik.spec);
							
							
							
							
						}
					
					
					
			
				}
				
				
				
				//System.out.println("\n");
			}
				
				
			}
		}
		
		workbook.close();
		
		return user;
	}
	
	public static ArrayList<User> getPrewMonthsUser() throws IOException {
		
		configBetoltes();
		
		String[] speckok = spec.split(",");
		
		
		ArrayList<User>  emberek = getInfoFromFile(multHonap, speckok);
		
		return emberek;
	}
	
	
	public static ArrayList<NaponDolgozik> teljesPrevMonth() throws IOException {
		
		configBetoltes();
		String[] speckok = spec.split(",");
		
		
		ArrayList<User>  emberek = getInfoFromFile(multHonap, speckok);
		
		ArrayList<NaponDolgozik> napokK = new ArrayList<NaponDolgozik>();
		
		for (int i = napokPrew - 8; i < napokPrew; ++i) {
			
			NaponDolgozik npSz = new NaponDolgozik(i + 1);
			
			for (int j = 1; j < emberek.size(); ++j) {
				
					
					npSz.addUser(emberek.get(j));
			}
			
			napokK.add(npSz);
			
		}
		
		return napokK;
	}
	
	
	public static ArrayList<User> getInfoFromFile(int mode) throws IOException {
		
		configBetoltes();
		
		//System.out.println("spec: " + spec);
		
		String[] speckok = spec.split(",");
		
		if (mode == 1) {
			
			return getInfoFromFile(expertCs, speckok);
		} else {
			
			return getInfoFromFile(t1t2Cs, speckok);
			
		}
	}
	
	
	

	public static ArrayList<User> getInfoFromFile(String expertCsiri, String[] speckok) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList<User> users = new ArrayList<User>();
		FileInputStream excelFile = new FileInputStream(expertCsiri);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet datatypeSheet = workbook.getSheetAt(0);
		
		Iterator<Row> iterator = datatypeSheet.iterator();
		Row currentRow = iterator.next();
		
		User user = new User("<< Választás >>");
		users.add(user);
		
		while (iterator.hasNext()) {
			 currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			
			Cell nameC = cellIterator.next();
			String name = nameC.getStringCellValue();
			
			user = new User(name);
			users.add(user);
			
			int beok = 0;
			
			while (cellIterator.hasNext()) {
				//Cell currentCell = cellIterator.next();
                //getCellTypeEnum shown as deprecated for version 3.15
                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
				
				//System.out.println("kacsa");
				nameC = cellIterator.next();
				name = nameC.getStringCellValue();
				
				Beo beo = new Beo(name);
				user.addToList(beo);
				beok++;
				
				//ide még jön a beo tip beállítás
				/**
				 * 
				 * 
				 * 
				 * 
				 * 
				 */
				
				
				/*
				 * mivel még nem tudjuk, hogy hol vannak a hétvégék/hétköznapok, a szabad napokat bejelöljük,
				 */
				
				if (!name.equals("") && !name.equals(null))
				if (name.charAt(0) == '0' || (name.charAt(0) == '1' && (name.charAt(1) == '0' || name.charAt(1) == '1'))) {
					
					beo.setUserke(dolgozik.dolgVde);
				} else {
					
					if (name.charAt(0) == '1') {
						beo.setUserke(dolgozik.dolgVdu);
						
						
					} else  if (name.charAt(0) == '2') {
						
						beo.setUserke(dolgozik.dolgE);
						
					}
					
					else {
						beo.setUserke(dolgozik.szabadV);
						
						//System.out.println(name + "kurv");
					}
				}
				
			
				
				for (String s: speckok) {
					
					if (name.toUpperCase().contains(s.toUpperCase())) {
						
						//ha speciális
						
						beo.setUserke(dolgozik.spec);
						
						
						
						
					}
				
				
				
		
			}
			
			
			
			//System.out.println("\n");
		}
		}
		
		workbook.close();
		
		return users;
	}
	
	
	public static  void configBetoltes() {
			// TODO Auto-generated method stub
			
			//configBetoltes();
			try {
			
				BufferedReader br = new BufferedReader(new FileReader("series.conf"));
				
				honap = Integer.parseInt(br.readLine().split("=")[1]);
				
				napok = Integer.parseInt(br.readLine().split("=")[1]);
				
				kezdo = Integer.parseInt(br.readLine().split("=")[1]);
				
				expertM = br.readLine().split("=")[1];
				
				expertCs = br.readLine().split("=")[1];
		
				t1t2M = br.readLine().split("=")[1];
				
				 t1t2Cs = br.readLine().split("=")[1];
				
				 fix = br.readLine().split("=")[1];
				
				 spec = br.readLine().split("=")[1];
				
				 HVHT = br.readLine().split("=")[1];
				 HTHV = br.readLine().split("=")[1];
				 multHonap = br.readLine().split("=")[1];
				 
				 napokPrew = Integer.parseInt(br.readLine().split("=")[1]);
				 
				 br.close();
			} catch (Exception ex) {}
			
				
			}

	public static void createFolder() {
		// TODO Auto-generated method stub
		
		configBetoltes();
		
		//System.out.println(expertM);
		
		File theDir = new File(expertM);
		File theDir2 = new File(t1t2M);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
		if (!theDir2.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir2.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
		
		
	}

	public static void fileRess(String fileName) {
		// TODO Auto-generated method stub
		
		configBetoltes();
		
		String [] speckok = {""};
		try {
			ArrayList<User> users = FileOperator.getInfoFromFile(fileName, speckok);
			
			ArrayList<User> expertList = new ArrayList<User>();
			ArrayList<User> t1t2List = new ArrayList<User>();
			
			//ketté bontjuk a fájlt
			
			for (int i = 0; i < users.size(); ++i) {
				
				boolean expert = false;
				
				for (int j = 0; j < users.get(i).getList().size() && !expert; ++j) {
					
					if (users.get(i).get(j).getLeiras().contains("EXP")) {
						expert = true;
					}
					
				}
				
				if (expert) {
					expertList.add(users.get(i));
				} else {
					t1t2List.add(users.get(i));
				}		
			}
			
			FileOperator.toExcelFile(expertList, expertCs);
			
			t1t2List.remove(0);
			FileOperator.toExcelFile(t1t2List, t1t2Cs);
			
			
			
			//System.out.println(t1t2List.get(0).getName());
			
			 // System.out.println(t1t2Cs);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	//fájlba írja adatokat
	private static void toExcelFile(ArrayList<User> users, String fileName)  {
		// TODO Auto-generated method stub
		
		
		System.out.println("Emberek " + users.size());
		try {
		
		  XSSFWorkbook workbook = new XSSFWorkbook();
		  XSSFSheet sheet = workbook.createSheet("beo");
		  
		  CellStyle yellow = workbook.createCellStyle();



		
		  
		  int rowIndex = 0;
		  
		  Row row = sheet.createRow(rowIndex++);
		  
		  int cellIndex = 0;
		  
		  Cell cell = row.createCell(cellIndex++);
		  
		  cell.setCellStyle(yellow);
		  
		  cell.setCellValue("kezelő");
		  
		 
		  
		  //napok kiírása
		  
		  for (int i = 1; i <= napok; i++) {
			  
			  cell = row.createCell(cellIndex++);
			  cell.setCellValue(honap + "." + i);  
		  }
		  
		  
		  for (int i = 0; i < users.size(); ++i) {
			  
			 // System.out.println(users.get(i).getName());
			  row = sheet.createRow(rowIndex++);
			  cellIndex = 0;
			  
			  cell = row.createCell(cellIndex++);
			  
			  cell.setCellValue(users.get(i).getName());
			 
			  
			  for (int j = 0; j < napok; ++j) {
				  
				  cell = row.createCell(cellIndex++);
				  cell.setCellStyle(yellow);
				  
				  cell.setCellValue(users.get(i).get(j).getLeiras());
				  
			  }
			  
		  }
		  
		  
		 
		  FileOutputStream excelFile = new FileOutputStream(fileName);
		  
		  
		
		  workbook.write(excelFile);
		  
		  excelFile.close();
		  workbook.close();
		  
		} catch (Exception ex) {
			
			System.out.println(ex);
		}
		  
		  
		  
		
	}
	
	private static void createTxt(ArrayList<User> users, String fileName) throws IOException {
		for (int i = 0; i < users.size(); ++i) {
			String fullPathName = "./" + fileName + "/" + users.get(i).getName() + ".txt";
			Path path = Paths.get(fullPathName);
			
			Files.createFile(path);
		}
	}

	public static void generateEmptyTxt(int mode) {
		// TODO Auto-generated method stub
		
		try {
			
			configBetoltes();
			
			String[] speckok = {};
			
			switch (mode) {
			
			case 0: {
				
				ArrayList<User> users = FileOperator.getInfoFromFile(expertCs, speckok);
				users.remove(0);
				createTxt(users, expertM);
				
				break;
				
			}
			
			case 1: {
				ArrayList<User> users = FileOperator.getInfoFromFile(t1t2Cs, speckok);
				users.remove(0);
				
				createTxt(users, t1t2M);
				
				break;
			}
			
			case 2: {
				ArrayList<User> users = FileOperator.getInfoFromFile(expertCs, speckok);
				users.remove(0);
				
				
				createTxt(users, expertM);
				
				users = FileOperator.getInfoFromFile(t1t2Cs, speckok);
				users.remove(0);
				
				createTxt(users, t1t2M);
				
			}
			}
			
			
			
		} catch (Exception ex) {}
		
	}

	public static void WriteAssigmentsToTxtFile(String tipus, String fileName, String igeny) {
		// TODO Auto-generated method stub
		
		configBetoltes();
		
		try {
			String fullPathName = "./";
			fullPathName += tipus.equals("Expert")?expertM:t1t2M;
			fullPathName = fullPathName + "/" + fileName;
			
			File file = new File(fullPathName);
			BufferedWriter br = new BufferedWriter(new FileWriter(fullPathName));
			br.write(igeny);
			br.close();
			
		
		} catch (Exception ex) {}
		
	}

	public static String getAssigment(String tipus, String fileName) {
		// TODO Auto-generated method stub
		
		configBetoltes();
		
		try {
			String fullPathName = "./";
			fullPathName += tipus.equals("Expert")?expertM:t1t2M;
			fullPathName = fullPathName + "/" + fileName;
			
			File file = new File(fullPathName);
			BufferedReader br = new BufferedReader(new FileReader(fullPathName));
			
			String elso = br.readLine();
			
			if (elso == null || elso.equals("")) {
				throw new Exception("Üres file");
			}
			
			
			String st1 = br.readLine();
			String st2 = br.readLine();
			String st3 = br.readLine();
			String st4 = br.readLine();
			String st5 = br.readLine();
			br.close();
			
			return elso + "\n" + st1 + "\n" + st2 +
					"\n" + st3+ "\n"  + st4 + "\n" +  st5;
			
			
		} catch (Exception ex) {
			return "";
		}
		
	}
	
	
	
	/*
	 * kinek: 0 - t1t2, 1 - expert; 2 mindkettőnek
	 */
	public static void igenyExcelElkeszites(int kinek) throws IOException {
		
		configBetoltes();
		
		
		String nameFile = "";
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		switch (kinek) {
		
		case 0: generaldIgenyeket(t1t2M, workbook); nameFile = t1t2M; break;
		
		case 1: generaldIgenyeket(expertM, workbook); nameFile = expertM; break;
			
		case 2: generaldIgenyeket(expertM, workbook); generaldIgenyeket(t1t2M, workbook); nameFile = ""; break;
		}
		
		
		
		String fPath = "./" + nameFile + "Igenyek.xlsx";
		
		FileOutputStream excelFile = new FileOutputStream(fPath);
		  
		
		
		  workbook.write(excelFile);
		  workbook.close();
		  
		  excelFile.close();
		
		
		
	}

	private static void generaldMindenkinek() {
		// TODO Auto-generated method stub
		
	}
	
	
	private static void generaldIgenyeket2(String mappa, XSSFWorkbook workbook) throws IOException {
		
		String fullPathName = "./" + mappa + "/";
		
		File folder = new File(fullPathName);
		
		File[] listOfFiles = folder.listFiles();
		
		String hetvegeIgeny = "";
		String hetvegeCsere = "";
		
		String hetkoznapIgeny = "";
		String hetkoznapCsere = "";
		
		String deIgeny = "";
		String deCsere = "";
		
		String duIgeny = "";
		String duCsere = "";
		
		String megjegyzes = "";
		
		String marad = "";
		
		
		XSSFSheet sheet = workbook.createSheet(mappa.split("\\.")[0]);
		
		
		int rowIndex = 0;
		int cellIndex = 0;
		/**
		Row foRow = sheet.createRow(rowIndex++);
		foRow.createCell(0).setCellValue("Kezelő");
		foRow.createCell(1).setCellValue("Módosítást kérek");
		foRow.createCell(2).setCellValue("Helyette szívesen bejövök");
		foRow.createCell(3).setCellValue("Egyéb megjegyzés");
		**/
		
		Row foRow = sheet.createRow(rowIndex++);
		
		foRow.createCell(0).setCellValue("Kezelő");
		foRow.createCell(1).setCellValue("Szabad hétvége");
		foRow.createCell(3).setCellValue("Szabad hétköznap");
		foRow.createCell(5).setCellValue("Délelőtt");
		foRow.createCell(7).setCellValue("Délután");
		foRow.createCell(9).setCellValue("Maradjon");
		foRow.createCell(10).setCellValue("Megjegyzés");
		
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 1,2));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 3,4));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 5,6));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 7,8));
		
		foRow = sheet.createRow(rowIndex++);
		
		foRow.createCell(1).setCellValue("Szeretné");
		foRow.createCell(2).setCellValue("Adná");
		
		foRow.createCell(3).setCellValue("Szeretné");
		foRow.createCell(4).setCellValue("Adná");
		
		foRow.createCell(5).setCellValue("Szeretné");
		foRow.createCell(6).setCellValue("Adná");
		
		foRow.createCell(7).setCellValue("Szeretné");
		foRow.createCell(8).setCellValue("Adná");
		
		String lineSzabadHV = "";
		String lineSzabadHK = "";
		String lineDelutanok = "";
		String lineDelelottok = "";
		String maradjon = "";
		String megj = "";
		
		
		for (File file: listOfFiles) {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				
			  lineSzabadHV = br.readLine();
			  lineSzabadHK = br.readLine();
			  lineDelutanok = br.readLine();
			  lineDelelottok = br.readLine();
			  maradjon = br.readLine();
			  megj = br.readLine();
			  br.close();
			 
			} catch (Exception ex) {
				
				
				 lineSzabadHV = "0=0";
				 lineSzabadHK = "0=0";
				 lineDelutanok = "0=0";
				 lineDelelottok = "0=0";
				 maradjon = "0";
				 megj = "";
			}
			
			
		/*
		 * String hetvegeIgeny = "";
		String hetvegeCsere = "";
		
		String hetkoznapIgeny = "";
		String hetkoznapCsere = "";
		
		String deIgeny = "";
		String deCsere = "";
		
		String duIgeny = "";
		String duCsere = "";
		
		String megjegyzes = "";
		
		String marad = "";
			 */
			
			
		try {	
		 hetvegeIgeny = lineSzabadHV.split("=")[0];
		 hetvegeCsere = lineSzabadHV.split("=")[1];
		} catch (Exception ex) {}
		 try {
		 hetkoznapIgeny = lineSzabadHK.split("=")[0];
		 hetkoznapCsere = lineSzabadHK.split("=")[0];
		 } catch (Exception ex) {}
		 
		 try {
		 deIgeny = lineDelelottok.split("=")[0];
		 deCsere = lineDelelottok.split("=")[0];
		 } catch (Exception ex) {}
		 
		 try {
		 duIgeny = lineDelutanok.split("=")[0];
		 duCsere = lineDelutanok.split("=")[0];	
		 } catch (Exception ex) {}
		 
		 try {
		 marad = maradjon.contains("0")?maradjon.split("0,")[1]:"";
		 } catch (Exception ex) {}
		 
		 try {
		 megjegyzes = megj == null || megj.equals("")?"":megj;
		 } catch (Exception ex) {}
		 
		 Row row = sheet.createRow(rowIndex++);
		 
		 row.getCell(0).setCellValue(file.getName().split("\\.")[0]);
		// row.getCell(1).setCellValue(hetvegeIgeny);
		 //row.getCell(1).setCellValue(hetvegeCsere);
		 //row.getCell(1).setCellValue(hetkoznapIgeny);
		 //row.getCell(1).setCellValue(hetkoznapCsere);
		 //row.getCell(1).setCellValue(deIgeny);
		 //row.getCell(1).setCellValue(deCsere);
		 //row.getCell(1).setCellValue(duIgeny);
		 //row.getCell(1).setCellValue(duCsere);
		 //row.getCell(1).setCellValue(marad);
		 //row.getCell(1).setCellValue(megjegyzes);
		 
		 
		}
		
		
		
	}

	/**excelbe írja a mappa neveként kapott mappába 
	 * @throws IOException */
	private static void generaldIgenyeket(String mappa, XSSFWorkbook workbook) throws IOException {
		// TODO Auto-generated method stub
		String fullPathName = "./" + mappa + "/";
		
		File folder = new File(fullPathName);
		
		File[] listOfFiles = folder.listFiles();
		
		/**
		 * az összes fájlt kinézzük
		 */
		
		String lineSzabadHV = "";
		String lineSzabadHK = "";
		String lineDelutanok = "";
		String lineDelelottok = "";
		String maradjon = "";
		String megj = "";
		
		String hetvegeIgeny = "";
		String hetvegeCsere = "";
		
		String hetkoznapIgeny = "";
		String hetkoznapCsere = "";
		
		String deIgeny = "";
		String deCsere = "";
		
		String duIgeny = "";
		String duCsere = "";
		
		String st1 = "";
		String st2 = "";
		String megjegyzesS = "";
		
	
		XSSFSheet sheet = workbook.createSheet(mappa.split("\\.")[0]);
		
		int rowIndex = 0;
		int cellIndex = 0;
		
		/*Row foRow = sheet.createRow(rowIndex++);
		foRow.createCell(0).setCellValue("Kezelő");
		foRow.createCell(1).setCellValue("Módosítást kérek");
		foRow.createCell(2).setCellValue("Helyette szívesen bejövök");
		foRow.createCell(3).setCellValue("Egyéb megjegyzés");*/
		
		
		Row foRow = sheet.createRow(rowIndex++);
		
		foRow.createCell(0).setCellValue("Kezelő");
		foRow.createCell(1).setCellValue("Szabad hétvége");
		foRow.createCell(3).setCellValue("Szabad hétköznap");
		foRow.createCell(5).setCellValue("Délelőtt");
		foRow.createCell(7).setCellValue("Délután");
		foRow.createCell(9).setCellValue("Maradjon");
		foRow.createCell(10).setCellValue("Megjegyzés");
		
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 1,2));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 3,4));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 5,6));
		sheet.addMergedRegionUnsafe(new CellRangeAddress(rowIndex-1, rowIndex-1, 7,8));
		
		foRow = sheet.createRow(rowIndex++);
		
		foRow.createCell(1).setCellValue("Szeretné");
		foRow.createCell(2).setCellValue("Adná");
		
		foRow.createCell(3).setCellValue("Szeretné");
		foRow.createCell(4).setCellValue("Adná");
		
		foRow.createCell(5).setCellValue("Szeretné");
		foRow.createCell(6).setCellValue("Adná");
		
		foRow.createCell(7).setCellValue("Szeretné");
		foRow.createCell(8).setCellValue("Adná");
		
		
		
		
		
		for (File file: listOfFiles) {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				  lineSzabadHV = br.readLine();
				 lineSzabadHK = br.readLine();
				 lineDelutanok = br.readLine();
				 lineDelelottok = br.readLine();
				 maradjon = br.readLine();
				 megj = br.readLine();
				 br.close();
				 
				 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				 lineSzabadHV = "0=0";
				 lineSzabadHK = "0=0";
				 lineDelutanok = "0=0";
				 lineDelelottok = "0=0";
				 maradjon = "0";
				 megj = "";
			}
			
			
			Row row = sheet.createRow(rowIndex++);
			cellIndex = 0;
			
			Cell cell = row.createCell(cellIndex++);
			
			cell.setCellValue(file.getName().split("\\.")[0]);
			
			Cell cell1 = row.createCell(cellIndex++);
			Cell cell2 = row.createCell(cellIndex++);
			Cell cell3 = row.createCell(cellIndex++);
			Cell cell4 = row.createCell(cellIndex++);
			Cell cell5 = row.createCell(cellIndex++);
			Cell cell6 = row.createCell(cellIndex++);
			Cell cell7 = row.createCell(cellIndex++);
			Cell cell8 = row.createCell(cellIndex++);
			Cell cell9 = row.createCell(cellIndex++);
			Cell cell10 = row.createCell(cellIndex++);
			
			//igények
			
			try {
			String[] cser1 = lineSzabadHV.split("=");
			String[] cser2 = lineSzabadHK.split("=");
			String[] cser3 = lineDelutanok.split("=");
			String[] cser4 = lineDelelottok.split("=");
			
			if(!cser1[0].equals("0")) {
				
				hetvegeIgeny = cser1[0].substring(0, cser1[0].length() - 2);
				
			}
			
			if (!cser1[1].equals("0")) {
				hetvegeCsere =  cser1[1].substring(2, cser1[1].length());
			}
			
			
			if(!cser2[0].equals("0")) {
				
				hetkoznapIgeny =  cser2[0].substring(0, cser2[0].length() - 2);
				
			}
			
			if (!cser2[1].equals("0")) {
				hetkoznapCsere =  cser2[1].substring(2, cser2[1].length());
			}
			
			
			if(!cser3[0].equals("0")) {
				
				deIgeny =   cser3[0].substring(0, cser3[0].length() - 2);
				
			}
			
			if (!cser3[1].equals("0")) {
				deCsere = cser3[1].substring(2, cser3[1].length());
			}
			
			if(!cser4[0].equals("0")) {
				
				duIgeny =   cser4[0].substring(0, cser4[0].length() - 2);
				
			}
			
			if (!cser4[1].equals("0")) {
				duCsere = cser4[1].substring(2, cser4[1].length());
			}
			
			
			if (maradjon.equals("0")) {
				maradjon = "";
			} else {
				maradjon =   maradjon.substring(0, maradjon.length() - 2);
			}
			
			
			
			
			
			} catch (Exception ex) {
			
				hetvegeIgeny = "";
				 hetvegeCsere = "";
				
				 hetkoznapIgeny = "";
				 hetkoznapCsere = "";
				
				 deIgeny = "";
				 deCsere = "";
				
				 duIgeny = "";
				 duCsere = "";
				 megj = "";
				 maradjon = "";
			}
			
			cell1.setCellValue(hetvegeIgeny);
			cell2.setCellValue(hetvegeCsere);
	
			cell3.setCellValue(hetkoznapIgeny);
			cell4.setCellValue(hetkoznapCsere);
			
			cell5.setCellValue(deIgeny);
			cell6.setCellValue(deCsere);
			
			cell7.setCellValue(duIgeny);
			cell8.setCellValue(duCsere);
			
			cell9.setCellValue(maradjon);
			cell10.setCellValue(megj);
		}
		
		
		
	}

	public static ArrayList<Igeny> getIgenyek(int mode) throws IOException {
		// TODO Auto-generated method stub
		
		configBetoltes();
		switch (mode) {
		
		case 0: return getIgenyek(t1t2M);
		case 1: return getIgenyek(expertM);
		
		}
		return null;
	}

	private static ArrayList<Igeny> getIgenyek(String mappa) throws IOException {
		// TODO Auto-generated method stub
		
	
		
		String fullPathName = "./" + mappa + "/";
		
		File folder = new File(fullPathName);
		
		File[] listOfFiles = folder.listFiles();
		
		ArrayList<Igeny> leadottIgenyek = new ArrayList<Igeny>();
		
		System.out.println(fullPathName);
		
	for (File file: listOfFiles) {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String lineSzabadHV;
			try {
				  lineSzabadHV = br.readLine();
				 lineSzabadHK = br.readLine();
				 lineDelutanok = br.readLine();
				 lineDelelottok = br.readLine();
				 maradjon = br.readLine();
				 megj = br.readLine();
				 
				
				 
				 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				 lineSzabadHV = "0=0";
				 lineSzabadHK = "0=0";
				 lineDelutanok = "0=0";
				 lineDelelottok = "0=0";
				 maradjon = "0";
				 megj = "";
			}
			
			br.close();
			
			
			
			String fname = file.getName().split("\\.txt")[0];
			if (lineSzabadHV != null)  {
				igenyBeallitas(lineSzabadHV, leadottIgenyek, IgenyTipus.SzabadHetvege, fname);
				igenyBeallitas(lineSzabadHK, leadottIgenyek, IgenyTipus.SzabadHetkoznap, fname);
				igenyBeallitas(lineDelutanok, leadottIgenyek, IgenyTipus.Delutan, fname);
				igenyBeallitas(lineDelelottok, leadottIgenyek, IgenyTipus.Delelott, fname);
				igenyBeallitas(maradjon, leadottIgenyek, IgenyTipus.Maradjon, fname);
			}
			
			

			
	}
	
	return leadottIgenyek;
			
	}

	private static void igenyBeallitas(String szoveg, ArrayList<Igeny> igenyek, IgenyTipus tip, String fileName) {
		// TODO Auto-generated method stub
		
		//System.out.println(szoveg + " " + fileName);
		
		String[] szovegEgyenlo = szoveg.split("=");
		
		if (!szovegEgyenlo[0].equals("0")) {
			
			String[] kellNapok = szovegEgyenlo[0].split(",");
			
			String[] adomNapok = {"0"};
			
			if (tip != IgenyTipus.Maradjon) {
			 adomNapok = szovegEgyenlo[1].split(",");
			}
			
			for (int i = 0; i < kellNapok.length - 1; ++i) {
				//a nullás a végéről nem kell
				
				Igeny ig = new Igeny();
				
				ig.setTipus(tip);
				
				ig.setName(fileName);
				ig.setNap(Integer.parseInt(kellNapok[i]));
				
				for (int j = 1; j < adomNapok.length; ++j) {
					ig.addToAdnaErte(Integer.parseInt(adomNapok[j]));
				}
				
				igenyek.add(ig);
				
				//System.out.println(ig.toString());
				
		}
			
			
			
		}
		
		
	}
	

}
