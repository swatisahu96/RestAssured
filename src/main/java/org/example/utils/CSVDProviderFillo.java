package org.example.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.Test;

public class CSVDProviderFillo {

    String File_Path= System.getProperty("user.dir")+
            "\\src\\main\\java\\org\\example\\resource\\TestData.xlsx";

    String value;
    public String fetchDataFromXLSX(String SheetName, int id, String fieldName) throws FilloException {

        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(File_Path);
        Recordset recordset = connection.executeQuery("Select * from "+SheetName+" where ID= "+id);
        while(recordset.next()){
            value = recordset.getField(fieldName);
        }

        recordset.close();
        connection.close();
        return value;
    }

    @Test
    public void test1() throws FilloException {

       String data= fetchDataFromXLSX("Sheet1", 2, "First_Name");
        System.out.println("Data fetched: "+ data);
    }

    }



