package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver)
    {
//        this.driver=driver;
//        PageFactory.initElements(driver,this);
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;
    @FindBy(xpath = "//a[normalize-space()='Register']") WebElement registerLink;
    @FindBy(xpath = "//a[normalize-space()='Login']") WebElement loginLink;

    public void clickMyAccount()
    {
        myAccount.click();
    }
    public void clickRegister()
    {
        registerLink.click();
    }
    public void clickLogin()
    {
        loginLink.click();
    }

}
