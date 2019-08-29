package ABoxGen.InstanceGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetRandomName {
    static String firstName,lastName;

    public GetRandomName(){

    }

    public static String getRandomFirstName(){
        try {
            FileInputStream fileInputStream =
                    new FileInputStream("C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\RandomNames.xlsx");
            // new FileInputStream("C:\\Users\\Gunjan\\Desktop\\RandomNames.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet("FirstNames");
            //int lastRowNum= worksheet.getLastRowNum();
            //int randomRow=GetRandomNo.getRandomFromRange(0,lastRowNum);
            //int randomRow=GetRandomNo.getRandomFromRange(0,1000);
            int randomRow=GetRandomNo.getRandomFromRange(0,32951);
            firstName=worksheet.getRow(randomRow).getCell(0).getStringCellValue();
            //System.out.print(worksheet.getRow(randomRow).getCell(0).getStringCellValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstName;
    }

    public static String getRandomLastName(){
        try {
            FileInputStream fileInputStream =
                    new FileInputStream("C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\RandomNames.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet("LastNames");
            //int lastRowNum= worksheet.getLastRowNum();
            //int randomRow=GetRandomNo.getRandomFromRange(0,lastRowNum);
            //int randomRow=GetRandomNo.getRandomFromRange(0,1000);
            int randomRow=GetRandomNo.getRandomFromRange(0,151670);
            lastName= worksheet.getRow(randomRow).getCell(0).getStringCellValue();
            //System.out.print(worksheet.getRow(randomRow).getCell(0).getStringCellValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastName;
    }

    public static String getRandomCityName(){
        try {
            FileInputStream fileInputStream =
                    new FileInputStream("C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\RandomNames.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet("CityNames");
            //int lastRowNum= worksheet.getLastRowNum();
            //int randomRow=GetRandomNo.getRandomFromRange(0,lastRowNum);
            //int randomRow=GetRandomNo.getRandomFromRange(0,1000);
            int randomRow=GetRandomNo.getRandomFromRange(0,37841);
            lastName= worksheet.getRow(randomRow).getCell(0).getStringCellValue();
            //System.out.print(worksheet.getRow(randomRow).getCell(0).getStringCellValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastName;
    }
    }

/*


package ABoxGen.InstanceGenerator;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GetRandomName {

        public static void main(String[] args) {
            try {

                    FileInputStream fileInputStream =
                            new FileInputStream("C:\\Users\\Gunjan\\Desktop\\RandomNames.xlsx");
                    XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                    XSSFSheet worksheet = workbook.getSheet("FirstNames");
                    int lastRowNum= worksheet.getLastRowNum();
                    int randomRow=GetRandomNo.getRandomFromRange(0,lastRowNum);
                    System.out.print(worksheet.getRow(randomRow).getCell(0).getStringCellValue());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





 */


