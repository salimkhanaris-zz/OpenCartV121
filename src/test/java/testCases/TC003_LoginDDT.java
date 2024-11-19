package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(groups = {"Sanity","Master"}, dataProvider = "LoginData",dataProviderClass = DataProviders.class) //Getting Data Provider from different class
    public void verify_loginDDT (String uname, String pwd, String exp) {
        logger.info("*******Starting TC003_LoginDDT*******");

        //HomePage
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //Login

            LoginPage lp= new LoginPage(driver);
            logger.info("Providing Email Details");
            lp.setEmailAdd(uname);
            logger.info("Providing Password");
            lp.setPassword(pwd);
            lp.clickLogin();
            logger.info("User is logged in");

            //MyAccount
            MyAccountPage map= new MyAccountPage(driver);
            boolean targetPage = map.validateHeader();
            Assert.assertEquals(targetPage,true);

            if(exp.equalsIgnoreCase("Valid"))
            {
                if(targetPage==true)
                {
                    Assert.assertTrue(true);
                    map.logout();
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp.equalsIgnoreCase("Invalid"))
            {
                if(targetPage==true)
                {
                    Assert.assertTrue(false);
                    map.logout();
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("*******TC Finished*******");

    }


}
