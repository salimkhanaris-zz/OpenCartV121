package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement fName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailAddress;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;
    @FindBy(xpath = "//input[@name='agree']") WebElement agreeBox;
    @FindBy(xpath = "//input[@value='Continue']") WebElement continueBtn;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmMessage;

    public void setFirstName(String firstName)
    {
        fName.sendKeys(firstName);
    }
    public void setLastName(String lastName)
    {
        lName.sendKeys(lastName);
    }
    public void setEmail(String email)
    {
        emailAddress.sendKeys(email);
    }
    public void setPhone(String tel)
    {
        telephone.sendKeys(tel);
    }
    public void setPassword(String pass)
    {
        password.sendKeys(pass);
    }
    public void setConfirmPassword(String pass)
    {
        confirmPassword.sendKeys(pass);
    }
    public void setAgreeBox()
    {
        agreeBox.click();
    }
    public void clickContinue()
    {
        continueBtn.click();
    }
    public String getConfirmMessage()
    {
        try {
            return (confirmMessage.getText());
        }
        catch (Exception e){
            return (e.getMessage());
        }
    }



}
