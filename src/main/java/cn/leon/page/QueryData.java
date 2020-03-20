package cn.leon.page;

import cn.leon.base.ContralDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class QueryData {
    public void queryMethod() throws InterruptedException {
        ContralDriver selfdriver =  new ContralDriver();
        RemoteWebDriver driver = selfdriver.driver;
        driver.navigate().to("http://bjjs.zjw.beijing.gov.cn/eportal/ui?pageId=314443");

        driver.manage().window().maximize();
        Thread.sleep(3000);

        driver.findElementById("filter_LIKE_姓名").sendKeys("李逵");
        driver.findElementByXPath("//table/tbody/tr/td/table[2]/tbody/tr[1]/td[5]/input").click();
    }
}
