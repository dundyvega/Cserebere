package objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

final public class FileOperator {
	
	
	
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
		
		
		return user;
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
		
		
		
		return users;
	}

}
