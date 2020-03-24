import cn.leon.base.BaseRequest;
import cn.leon.base.BaseUrl;
import cn.leon.base.ReadExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TBphoneTest {
    ReadExcel excel= null;
    @Test(dataProvider = "dataPhoneProvider")
    public void testTbphone (String key,String value,String url,String expectResult) throws IOException {
        BaseRequest request =  new BaseRequest();
        String str_response=request.postMethod(url,key,value);
        System.out.println("实际结果：" +str_response);
        System.out.println("预期结果：" +expectResult);
        Assert.assertTrue(str_response.contains(expectResult));

    }
    @DataProvider
    public Object[][] dataPhoneProvider() throws IOException, InvalidFormatException {
        return excel.getBatchValues("TBphone",2,6,5,8);
    }
    @BeforeClass
    public void beforeClass() throws IOException, InvalidFormatException {
        excel =  new ReadExcel("Testcase-query.xlsx");
    }
}
