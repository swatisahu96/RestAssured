package org.example.tests;

import org.example.utils.UtilExcel;
import org.testng.annotations.Test;


public class DataBaseProvider2 {

   @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
   public void loginTesting(String username, String password){

      System.out.println("Logged in using: "+username+","+password);

   }
}
