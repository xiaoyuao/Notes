package com.smart.crawler;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjc on 2018/11/9.
 * 中影网站密钥获取
 */
@Component
public class DigifilmCrawler{

    private String baseURL = "http://www.digifilm.com.cn";
    private String loginURL = "http://www.digifilm.com.cn/sb/device/login";
    private String downloadKeyURL = "http://material.digifilm.com.cn/secretkey/index?menuId=141";
    private String downloadEveryKeyURL = "http://material.digifilm.com.cn/secretkey/down-zip?movie_id=";

    public synchronized void crawling() {
        try {
            // String zippath = CrawlerUtil.createZipFolder();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(loginURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //建立一个NameValuePair数组，用于存储欲传送的参数
            //  DownloadkdmInfo downloadKdmInfo = downloadkdmInfoService.selectDownloadKdmInfoByIssuerEn(Constants.DIGIFILM);
            NameValuePair userName = new BasicNameValuePair("username", "*****");
            NameValuePair password = new BasicNameValuePair("password", "*****");
            params.add(userName);
            params.add(password);
            httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            HttpResponse response=httpClient.execute(httppost);
            if(response.getStatusLine().getStatusCode() == 200) {
                String res = EntityUtils.toString(response.getEntity(), "UTF-8");
                String code = JSONObject.fromObject(res).getString("code"); //登陆成功
                if("1".equals(code)){
                    HttpGet getKeyHttp = new HttpGet(downloadKeyURL);
                    HttpResponse getRes = httpClient.execute(getKeyHttp);
                    String html = EntityUtils.toString(getRes.getEntity(),"UTF-8");
                    Document doc = Jsoup.parse(html);
                    Elements trs = doc.select("table").select("tr");
                    for (int i=1; i<trs.size(); i++){
                        Elements tds = trs.get(i).select("td");
                        String filmName = "";
                    //    String validateTime = "";
                        String downloadUrl = "";

                        filmName = tds.get(0).text();
                        File file = new File("E:\\ftp\\kdmzips"+File.separator+filmName+".zip");


                      //  validateTime = tds.get(3).text();
                        downloadUrl = downloadEveryKeyURL+tds.get(2).select(".nourl").attr("noid");
                        HttpGet downloadKeyHttp = new HttpGet(downloadUrl);
                        HttpResponse downZipres=httpClient.execute(downloadKeyHttp);
                        HttpEntity entity = downZipres.getEntity();
                        InputStream is = entity.getContent();
                        FileOutputStream fileout = new FileOutputStream(file);
                        /**
                         * 根据实际运行效果 设置缓冲区大小
                         */
                        byte[] zipbuffer=new byte[1024];
                        int ch = 0;
                        while ((ch = is.read(zipbuffer)) != -1) {
                            fileout.write(zipbuffer,0,ch);
                        }
                        fileout.flush();

                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DigifilmCrawler d = new  DigifilmCrawler();
        d.crawling();
    }
}
