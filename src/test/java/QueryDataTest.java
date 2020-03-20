import cn.leon.page.QueryData;

public class QueryDataTest {
    public static void main(String[] args){
        QueryData query  = new QueryData();
        try {
            query.queryMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
