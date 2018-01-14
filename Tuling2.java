package com.hc.io;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import net.sf.json.JSONObject;

import java.io.*;
public class Tuling2 {
	public enum RequestMethod {

	    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE

	}
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String url = "http://www.tuling123.com/openapi/api";	
		String end = "";
		System.out.println("你好我是图灵机器人");
		while (true) {
			System.out.println();
			end = new Scanner(System.in).nextLine();
		    if (end != null) {
			String text = InputData(end, url);
			System.out.println(text);
		    }
		}
	}
	
public static String InputData(String info,String url) {
	JSONObject jsonParam;
	JSONObject jsonObject;
	//创建json并加入键和值
	jsonParam = new JSONObject();
	jsonParam.put("key", "18764c2743cb4280a1e7194c57c8ce60");		
	jsonParam.put("info", info);
	String data =  GetJsonData(jsonParam, url);
	jsonObject = JSONObject.fromObject(data);
	//指定获取text的值
	String text = jsonObject.getString("text"); 
	return text;
	
}
public static String GetJsonData (JSONObject jsonParam,String urls){
	StringBuffer sb = new StringBuffer(); 
      try {
    	    //创建URL
	        URL url = new  URL(urls);
	        //建立连接
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);    //设置允许输出
	        conn.setDoInput(true);    //设置允许输入
	        conn.setRequestMethod("POST");   // 设置传递方式
	        conn.setRequestProperty("Content-Type", "json"); //设置发送文件类型
	        //建立字符输出转换流，向指向的URL传入参数，并且转换UTF-8文本格式 ，解决中文乱码问题
	        OutputStreamWriter ot = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
	        // 写入请求的字符串
	        ot.write(jsonParam.toString());
	        ot.flush(); //刷新缓冲
	        ot.close();  //关闭
	        //建立输入流  接收URL输入流
            InputStream ins = conn.getInputStream();  
            try {  
                  int readLine;  
                  //建立字符转换流 把字节转换成字符
                  InputStreamReader isr =new InputStreamReader(ins,"UTF-8"); 
                  //循环写入字节 并转换成字符
                  while ((readLine = isr.read()) != -1){ 
                    sb.append((char)readLine); 
                  }  
                  isr.close();  
                  
            } catch (Exception e1) {  
                e1.printStackTrace();  
            }  
	} catch (Exception e) {
		// TODO: handle exception
	}
  return sb.toString();
 }	      
}
