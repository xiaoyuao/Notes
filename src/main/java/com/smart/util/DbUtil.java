package com.smart.util;

import java.io.*;

/**
 * Created by admin on 2019/7/16.
 */
public class DbUtil {
    /**
     * 备份数据库db
     * @param root
     * @param pwd
     * @param dbName
     * @param backPath
     * @param backName
     */
    public  void dbBackUp(String root,String pwd,String dbName,String backPath,String backName) throws Exception {
        String pathSql = backPath+backName;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        StringBuffer sb = new StringBuffer();
    //    sb.append("mysqldump");
        sb.append(" -hlocalhost");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        System.out.println("cmd命令为2："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份："+dbName);
        Process process = runtime.exec("cmd /c D:/mysqldump.exe"+sb.toString());
        System.out.println("备份成功!");


    /*    public void backupDB() throws IOException {
            String jdbcUser="root";
            String jdbcPassword="root";
            String dbHost="localhost";
            String dbName="myDatabase";
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "/usr/bin/mysqldump -u" + jdbcUser + " -p" + jdbcPassword + " -h" + dbHost + " " + dbName + "> ~/Workspace/DatabaseBackup/"+dbName+"-`date '+%Y-%m-%d %H:%M:%S'`.sql"});
        }*/


        StreamHandler errorStreamHandler = new StreamHandler(process.getErrorStream(), "ERROR");
        errorStreamHandler.start();
        StreamHandler outputStreamHandler = new StreamHandler(process.getInputStream(), "STDOUT");
        outputStreamHandler.start();
    }

    /**
     * 恢复数据库
     * @param root
     * @param pwd
     * @param dbName
     * @param filePath
     * mysql -hlocalhost -uroot -p123456 db < /home/back.sql
     */
    public static void dbRestore(String root,String pwd,String dbName,String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("mysql");
        sb.append(" -h60.115.212.196");
        sb.append(" -u" + root);
        sb.append(" -p" + pwd);
        sb.append(" " + dbName + " <");
        sb.append(filePath);
        System.out.println("cmd命令为：" + sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始还原数据");
        try {
            Process process = runtime.exec("cmd /c" + sb.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "utf8"));
            String line = null;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("还原成功！");
    }

    public static void main(String[] args) {
        //dbBackUp(String root,String pwd,String dbName,String backPath,String backName) throws Exception {
        try {
            DbUtil d = new DbUtil();
            d.dbBackUp("root","PythA90ra5","tms_db_s3","E://","2019.sql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class StreamHandler extends Thread{
        InputStream m_inputStream;
        String m_type;

        public StreamHandler(InputStream is, String type){
           this.m_inputStream = is;
           this.m_type = type;
        }

        @Override
          public void run(){
            InputStreamReader isr = null;
            BufferedReader br = null;

            try{
              //设置编码方式，否则输出中文时容易乱码
              isr = new InputStreamReader(m_inputStream, "GBK");
              br = new BufferedReader(isr);
              String line=null;
              while ( (line = br.readLine()) != null){
                    System.out.println("PRINT > " + m_type + " : " + line);
              }
              System.out.println("GG");
            }catch (IOException ioe) {
              ioe.printStackTrace();
             }finally{
                  try{
                      br.close();
                      isr.close();
                  }catch (IOException ex){
                    ex.printStackTrace();
                  }
             }
        }
    }



}
