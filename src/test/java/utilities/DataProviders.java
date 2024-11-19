package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public  String[][] getData() throws IOException {
        String path= ".\\testData\\Data.xlsx"; //Taking XL file from testData
        XlUtility xlutil= new XlUtility(path); //Creating an object for XLUtility

        int totalrows=xlutil.getRowCount("Sheet1");
        int totalcols= xlutil.getCellCount("Sheet1",1);

        String loginData[][]= new String[totalrows][totalcols]; //Created 2d array which can store the data

        for (int i=1;i<=totalrows;i++)
        {
            for (int j=0;j<totalcols;j++)
            {
                loginData[i-1][j] = xlutil.getCellData("Sheet1",i,j);
            }
        }
        return loginData; //returning 2d array

    }
}
