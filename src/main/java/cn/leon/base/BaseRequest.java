package cn.leon.base;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BaseRequest {
    /**
     * 调用postMethod方法，返回response字符串格式，可以直接判断重要字段是否在返回结果中，需要继续用String.indexOf(字符串)是否
     * 返回-1来判断
     * @param url
     * @param key
     * @param value
     * @return response的字符串格式
     * @throws IOException
     */
    public String postMethod(String url,String key,String value) throws IOException {

        //实例post对象
        HttpPost post = new HttpPost(url);
        //将参数放在列表中
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

        //构建BasicNameValuePair参数
        BasicNameValuePair paras1 =  new BasicNameValuePair(key,value);
        //将参数添加到列表中
        list.add(paras1);
        //将参数列表转换为post需要的参数格式
        UrlEncodedFormEntity urlparasformat = new UrlEncodedFormEntity(list);
        post.setEntity(urlparasformat);

        //创建客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //客户端发送请求，得到返回请求
        CloseableHttpResponse response=  client.execute(post);

        //将返回请求转换为HttpEntity实体
        HttpEntity htentity = response.getEntity();
        //将HttpEntity实体转换为String类型
        String sresponse = EntityUtils.toString(htentity);

        //如果想简单判断也可以返还StatusCode
//        int code =  response.getStatusLine().getStatusCode();
//        return code;
        return sresponse;




    }

    /**
     *调用getMethod方法，返回response字符串格式，可以直接判断重要字段是否在返回结果中，需要继续用String.indexOf(字符串)是否
     * 返回-1来判断
     * @param url
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    public  String getMethod(String url,String key,String value) throws IOException {
        //创建参数列表
        List<BasicNameValuePair> list =  new ArrayList<BasicNameValuePair>() ;
        //创建参数
        BasicNameValuePair paras1 =  new BasicNameValuePair(key,value);
        //列表中增加参数
        list.add(paras1);
        //将列表转换为get请求需要的格式
        String getformat = URLEncodedUtils.format(list,"UTF-8");
        //连接url地址
        String getUrl =  url+"?"+getformat;
        //创建get请求
        HttpGet get = new HttpGet(getUrl);
        //创建客户端
        CloseableHttpClient  client =  HttpClients.createDefault();
        //客户端发送get请求
        CloseableHttpResponse response =  client .execute(get);
        //也可以返回状态码
        int StatusCode =  response.getStatusLine().getStatusCode();
        //得到response的实体
        HttpEntity hp_response =  response.getEntity();
        //将response实体转换为字符串类型
        String Str_response =  EntityUtils.toString(hp_response);
        return Str_response;
    }
}
