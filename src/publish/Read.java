package publish;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.ibm.bidiTools.bdlayout.Mlog;



public class Read {


	public static void main(String[] args) {

		//System.out.println("Pointer is going to main function of read");
		DbConnect conn;
		Connection c = null;

		try{
			//System.out.println("This Pointer is going to Try block");

			conn = new DbConnect(); 
			c = DbConnect.connect();
			if(conn!=null){
				try{
					// Path to the source excel file
					File path = new File("/home/ec2-user/files/AWS_SecurityCheck_output.xls");
					String filename = path.getName().substring(0, 24);
					System.out.println("The workbook name is "+ filename);
					FileInputStream readfile = new FileInputStream(path);
					System.out.println(path);
					HSSFWorkbook workbook = new HSSFWorkbook(readfile);	
					String comparefile = "AWS_SecurityCheck_output";

					int sheetnumbers = workbook.getNumberOfSheets() -1;
					//	System.out.println(sheetnumbers);

					if(filename.matches(comparefile)){

						for(int j=0; j<= sheetnumbers;j++){

							HSSFSheet sheet = workbook.getSheetAt(j);
							String sheetname = workbook.getSheetName(j);
							System.out.println(sheetname);

							String comparesheet1 = "MFA_Enabled";
							String comparesheet2 = "iam_key_rotate";

							if(comparesheet1.equalsIgnoreCase(sheetname)){


								Row row;
								for(int i=1; i<=sheet.getLastRowNum();i++)

								{
									row = sheet.getRow(i);
									String Name = row.getCell(0).getStringCellValue();
									String Status= row.getCell(1).getStringCellValue();
									System.out.println(Name+" " + Status);
									DbConnect.insertsheet1(conn, Name, Status); // calls the Database insert function which is in DbConnect
									//	System.out.println("Import rows "+i);
								}
								c.close();
								//System.out.println("Success Import Excel Data to Database Table");

								readfile.close();
								workbook.close();

							}

							else if(comparesheet2.equalsIgnoreCase(sheetname)){


								Row row;
								for(int i=1; i<=sheet.getLastRowNum();i++)

								{
									row = sheet.getRow(i);
									String Name = row.getCell(0).getStringCellValue();
									String Status= row.getCell(3).getStringCellValue();
									System.out.println(Name+" " + Status);
									DbConnect.insertsheet2(conn, Name, Status); // calls the Database insert function which is in DbConnect
									//	System.out.println("Import rows "+i);
								}
								c.close();
								//System.out.println("Success Import Excel Data to Database Table");

								readfile.close();
								workbook.close();
/*
								boolean fileDelete = path.delete();
								if(fileDelete){
									System.out.println("File was deleted successfully!");
								}
								else{
									System.out.println("File was not deleted");
								}

*/
							}
							else{
								System.out.println("Sheet Not Required!");
							}
						}
					}
					else{
						System.out.println("Error");
					}


				}

				catch(FileNotFoundException e) {
					System.out.println("Incorrect File Input!");
				}
			}



		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{

			System.out.println("Success!");
		}

	} 


}