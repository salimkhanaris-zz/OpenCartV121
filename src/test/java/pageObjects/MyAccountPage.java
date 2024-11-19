package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement header;
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logoutLink;
    public boolean validateHeader()
    {

        return (header.isDisplayed());
    }
    public void logout()
    {
        logoutLink.click();
    }

}
