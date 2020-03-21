package cn.leon.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



/**
 * create driver
 */

public class ContralDriver {

    private static Logger loggeer = Logger.getLogger(ContralDriver.class);
    public RemoteWebDriver driver;
    public ContralDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\灵犀台\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        driver =  new ChromeDriver();
        loggeer.info("driver has been biult");
    }

}
