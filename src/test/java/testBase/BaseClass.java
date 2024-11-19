package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.io.File;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity","Master","Regression"})
    @Parameters({"OS","Browser"})
    public void setup(String os, String br) throws IOException {
        //Loading config.properties file
        FileInputStream file= new FileInputStream(".//src/test//resources//config.properties");
        p=new Properties();
        p.load(file);


        Configurator.initialize("log4j2.xml", "src/test/resources/log4j2.xml");
        logger= LogManager.getLogger(this.getClass());  //log4j2 code

        //For Remote execution

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            String hubURL= "http://localhost:4444/wd/hub";
            DesiredCapabilities dc= new DesiredCapabilities();

            //os
            if (os.equalsIgnoreCase("windows"))
            {
                dc.setPlatform(Platform.WIN11);
            }

            //browser

            switch (br.toLowerCase())
            {
                case "chrome": dc.setBrowserName("chrome"); break;
                case "firefox": dc.setBrowserName("firefox"); break;
                case "edge": dc.setBrowserName("MicrosoftEdge"); break;
                default:
                    System.out.println("Invalid Browser"); return;
            }
            driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
        }

        //For Local Execution
        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase())
            {
                case "chrome": driver= new ChromeDriver(); break;
                case "firefox": driver= new FirefoxDriver(); break;
                default:
                    System.out.println("Invalid Browser"); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL")); //Reading URL from Properties file
    }
    @AfterClass(groups = {"Sanity","Master","Regression"})
    public void tearDown()
    {
        driver.quit();
    }
    public String randomString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }

    public String randomNumber()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }
    public String captureScreen(String tname)
    {
        String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts= (TakesScreenshot) driver;
        File sourceFile= ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png"; //Add Screenshot with timestamp
        File targetFile= new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

}
