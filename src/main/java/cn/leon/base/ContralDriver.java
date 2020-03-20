package cn.leon.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



/**
 * 创建driver
 */

public class ContralDriver {

    static Logger logger = Logger.getLogger(ContralDriver.class);
    public RemoteWebDriver driver;
    public ContralDriver(){
        System.setProperty("webdriver.chrome.driver","D:\\plugin\\chromev74\\chromedriver.exe");
        driver =  new ChromeDriver();
        logger.info("driver has been biult");
    }

}
