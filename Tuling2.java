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
		System.out.println("�������ͼ�������");
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
	//����json���������ֵ
	jsonParam = new JSONObject();
	jsonParam.put("key", "18764c2743cb4280a1e7194c57c8ce60");		
	jsonParam.put("info", info);
	String data =  GetJsonData(jsonParam, url);
	jsonObject = JSONObject.fromObject(data);
	//ָ����ȡtext��ֵ
	String text = jsonObject.getString("text"); 
	return text;
	
}
public static String GetJsonData (JSONObject jsonParam,String urls){
	StringBuffer sb = new StringBuffer(); 
      try {
    	    //����URL
	        URL url = new  URL(urls);
	        //��������
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);    //�����������
	        conn.setDoInput(true);    //������������
	        conn.setRequestMethod("POST");   // ���ô��ݷ�ʽ
	        conn.setRequestProperty("Content-Type", "json"); //���÷����ļ�����
	        //�����ַ����ת��������ָ���URL�������������ת��UTF-8�ı���ʽ �����������������
	        OutputStreamWriter ot = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
	        // д��������ַ���
	        ot.write(jsonParam.toString());
	        ot.flush(); //ˢ�»���
	        ot.close();  //�ر�
	        //����������  ����URL������
            InputStream ins = conn.getInputStream();  
            try {  
                  int readLine;  
                  //�����ַ�ת���� ���ֽ�ת�����ַ�
                  InputStreamReader isr =new InputStreamReader(ins,"UTF-8"); 
                  //ѭ��д���ֽ� ��ת�����ַ�
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
