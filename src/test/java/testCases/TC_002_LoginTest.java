package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_account_login() {
        logger.info("*******Starting TC_002_LoginTest*******");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");
            hp.clickLogin();
            logger.info("Clicked on Login");

            LoginPage lp= new LoginPage(driver);
            logger.info("Providing Email Details");
            lp.setEmailAdd(p.getProperty("email"));
            logger.info("Providing Password");
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();
            logger.info("User is logged in");
            MyAccountPage map= new MyAccountPage(driver);
            boolean targetPage = map.validateHeader();
            Assert.assertEquals(targetPage,true);
            map.logout();
            logger.info("User logged out");
        } catch (Exception e) {
            logger.error("Test Failed...");
            logger.debug("Debug Logs...");
            Assert.fail();
        }
        logger.info("*****TC Finished*****");
    }


}
