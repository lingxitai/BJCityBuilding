package cn.leon.page;

import cn.leon.base.ContralDriver;

import cn.leon.base.ReadExcel;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class QueryData {

    static Logger logger= Logger.getLogger(QueryData.class);
    @Test(dataProvider = "dataProvider")
    public void queryMethod(String accountNum) throws InterruptedException {
        logger.info("开始测试queryMethod");
        ContralDriver selfdriver =  new ContralDriver();
        RemoteWebDriver driver = selfdriver.driver;
        driver.navigate().to("http://bjjs.zjw.beijing.gov.cn/eportal/ui?pageId=314443");
        logger.info("打开网站");

        driver.manage().window().maximize();
        logger.info("最大化窗口");
        Thread.sleep(3000);
        logger.info("等待3秒结束");

        driver.findElementById("filter_LIKE_注册号").sendKeys(accountNum);
        logger.info("输入注册账号，账号从excel案例中读取");
        driver.findElementByXPath("//table/tbody/tr/td/table[2]/tbody/tr[1]/td[5]/input").click();
        logger.info("点击查询");

        Assert.assertEquals(driver.findElementByXPath("//form/table/tbody/tr/td/table[3]/tbody/tr[2]/td[1]").getText(),"李逵");
        logger.info("查看结果第一栏是否是李逵的信息");
        driver.close();

    }
    @DataProvider
    public Object[][] dataProvider() throws IOException, InvalidFormatException {
        ReadExcel excel = new ReadExcel("Testcase-query.xlsx");
        logger.info("从Testcase-query.xlsx读取数据作为dataprovide测试");
        Object[][] aa = excel.getBatchValues("sheet1",2,2,6,6);
        return aa;
    }
}
