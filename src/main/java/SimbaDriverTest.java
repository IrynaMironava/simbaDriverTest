import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import lib.QueryRunner;
import lib.results.Results;
import lib.utils.Parser;

public class SimbaDriverTest {
	
	static String JDBCDriver = "com.simba.couchbase.jdbc41.Driver";
	static String SQLDriver = "com.mysql.jdbc.Driver";
	static String ConnectionURL = null;
	static String ConnectionSQLURL = "jdbc:mysql://localhost/rqg";
	static Parser Config = new Parser();
	static Properties CommonProp = null;
	static Connection con = null;
	static Connection sqlCon = null;
	static QueryRunner runner = null;
	static QueryRunner sqlRunner = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CommonProp = Config.getCommonProperties(); 
		ConnectionURL = String.format("jdbc:couchbase://%s:%s;queryEnabled=1;",
				CommonProp.getProperty("n1qlHost"),
				CommonProp.getProperty("n1qlPort"));
		Class.forName(JDBCDriver);
		Class.forName(SQLDriver);
		sqlCon = DriverManager.getConnection(ConnectionSQLURL, CommonProp.getProperty("sqlUser"),
						CommonProp.getProperty("sqlPassword"));
		con = DriverManager.getConnection(ConnectionURL, CommonProp.getProperty("restUser"),
				CommonProp.getProperty("password"));
		runner = new QueryRunner(con);
		sqlRunner = new QueryRunner(sqlCon);
	}

	@Test
	public void test_query() throws SQLException, IOException {
		Map<String,String> queries = Config.getTestProperties("queryN1qlSql");
		Iterator it = queries.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey().toString());
	        Results res = runner.runQuery(pair.getKey().toString());
	        Results sqlResults = sqlRunner.runQuery(pair.getValue().toString());
	        assertEquals(sqlResults, res);
	        it.remove();
	    }
	}

}
