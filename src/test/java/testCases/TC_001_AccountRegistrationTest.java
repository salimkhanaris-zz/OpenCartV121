package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verify_account_registration() {
        logger.info("*******Starting TC_001_AccountRegistrationTest*******");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");
            hp.clickRegister();
            logger.info("Clicked on Register");

            AccountRegistrationPage arp = new AccountRegistrationPage(driver);
            logger.info("Providing Registration Details");
            arp.setFirstName("Salim");
            arp.setLastName("Khan");
            arp.setEmail(randomString() + "@gmail.com"); //random email
            arp.setPhone(randomNumber());
            arp.setPassword("salim1234");
            arp.setConfirmPassword("salim1234");
            arp.setAgreeBox();
            arp.clickContinue();
            arp.getConfirmMessage();
            logger.info("Validating expected message");
            String confmsg = arp.getConfirmMessage();
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        } catch (Exception e) {
            logger.error("Test Failed...");
            logger.debug("Debug Logs...");
            Assert.fail();
        }
        logger.info("*****TC Finished*****");
    }


}
