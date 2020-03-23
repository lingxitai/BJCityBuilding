import cn.leon.base.BaseRequest;
import cn.leon.base.BaseUrl;
import cn.leon.base.ReadExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 通过excel表驱动测试案例，将表中数据读到dataProvider中
 */
public class BJGvoAPITest {
    ReadExcel excel;
    /**
     * 通过datasProvider提供的key和value,放到get请求中，得到String类型的response,然后判读关键字段是否在返回的response中
     * @param key
     * @param value
     */
    @Test(dataProvider = "datasProvider")
    public void test1(String key,String value) {
        String url = BaseUrl.baseUrlGJGov;
        BaseRequest request =  new BaseRequest();
        String response = null;
        try {
            response = request.getMethod(url,key,value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expectResult = excel.getEasyValue("BJgvoTestcase",1,7);
        //判断表格中的预期结果”北京市住房和城乡建设委员会门户网站“是否在返回字段中，如果有，会返回相应位置数字，没有就返回-1，
        //所以判断是否不为-1，就是在里面
        Assert.assertTrue(response.indexOf(expectResult)!=-1);
    }

    @DataProvider
    public Object[][] datasProvider() throws IOException, InvalidFormatException {
        return excel.getBatchValues("BJgvoTestcase",2,2,5,6);
    }

    @BeforeClass
    public void beforeMetod() throws IOException, InvalidFormatException {
        excel = new ReadExcel("Testcase-query.xlsx");
    }
}
