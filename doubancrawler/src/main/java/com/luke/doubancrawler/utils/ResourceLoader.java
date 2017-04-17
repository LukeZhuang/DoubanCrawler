package com.luke.doubancrawler.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ResourceLoader {  
  
    private static ResourceLoader loader = new ResourceLoader();  
    private static Map<String, Properties> loaderMap = new HashMap<String, Properties>();  
  
    private ResourceLoader() {  
    }  
  
    public static ResourceLoader getInstance() {  
        return loader;  
    }  
      
    public Properties getPropFromProperties(String fileName) throws Exception {  
          
        Properties prop = loaderMap.get(fileName);  
        if (prop != null) {  
            return prop;  
        }  
        
        prop = new Properties();  
        
        InputStream inputStream = ResourceLoader.class.getResourceAsStream("/"+fileName);
        prop.load(inputStream);  
        inputStream.close();
        
//        String filePath = null;  
//        String configPath = System.getProperty("configurePath");  
//        System.out.println(configPath);
//        if (configPath == null) {  
//            filePath = this.getClass().getClassLoader().getResource(fileName).getPath();  
//        } else {  
//            filePath = configPath + "/" + fileName;  
//        }  
//        System.out.println(filePath);
//        
//        prop.load(new FileInputStream(new File(filePath)));  
  
        loaderMap.put(fileName, prop);  
        return prop;  
    }  
}  
