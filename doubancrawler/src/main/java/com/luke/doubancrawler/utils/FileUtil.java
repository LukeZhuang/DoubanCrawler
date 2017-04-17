package com.luke.doubancrawler.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> getFileByLine(String fileName){
		List<String> result = new ArrayList<>();
		try {
			FileReader reader=new FileReader(fileName);
			BufferedReader br=new BufferedReader(reader);
			String s="";
			while((s=br.readLine())!=null){
				result.add(s);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static List<String> getResourceFileByLine(String fileName) {
		List<String> result = new ArrayList<String>();
		try {
			InputStreamReader in = new InputStreamReader(FileUtil.class
					.getClassLoader().getResourceAsStream(fileName));
			BufferedReader br = new BufferedReader(in);
			String s;
			while ((s = br.readLine()) != null) {
				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static void appendFileByLine(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content+"\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String[]> readProxyList(){
		List<String[]> proxyList = new ArrayList<>();
		List<String> proxys=FileUtil.getResourceFileByLine("proxy.list");
		for(String proxy:proxys){
			String[] dummy=proxy.split(":");
			String[] pro=new String[4];
			pro[0]=pro[1]="";
			pro[2]=dummy[0];
			pro[3]=dummy[1];
			proxyList.add(pro);
		}
		return proxyList;
	}

	public static void main(String[] args){
		List<String> file=FileUtil.getFileByLine("E:\\git\\fudata\\frame\\data_performance\\data\\datax.csv");
		for(String line:file){
			System.out.println(line);
		}
	}

}
