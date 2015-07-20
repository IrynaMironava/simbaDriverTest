package lib.utils;

import java.io.IOException;

public class SQLUtils {
	
	String SQLDumpFilePath = "";

	public void runSqlDump() throws IOException{
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mysql -p -h ServerName DbName < dump.sql");
	}
	
}
