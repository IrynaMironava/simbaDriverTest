package lib.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Parser {
	private String commonConf = "src/main/java/conf/common.properties";
	
	public Properties getCommonProperties() throws IOException{
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(commonConf);
		// load a properties file
		prop.load(input);
		return prop;
	}

	public Map<String, String> getTestProperties(String name) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/conf/testProperties/" + name)));
        String line = null;
        Map<String, String> map = new HashMap<String, String>();
        while ((line = reader.readLine()) != null) {
            if (line.contains(":::")) {
                String[] strings = line.split(":::");
                map.put(strings[0], strings[1]);
            }
        }
		return map;
	}
}
