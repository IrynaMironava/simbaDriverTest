package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import lib.results.Results;


public class QueryRunner {


	Connection con = null;
	Statement stmt = null;
	PreparedStatement prep = null;
	
	public QueryRunner(Connection con) {
	    this.con = con;
	}
	
	public Results runQuery(String query) throws SQLException{
		try{
			stmt = con.createStatement();
			return new Results(stmt.executeQuery(query));
		}finally{
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Results runQuery(String query, int parameter) throws SQLException{
		try{
			prep = con.prepareStatement(query);
			// Bind the query parameter with a value
			prep.setInt(1, parameter);
			return new Results(prep.executeQuery());
		}finally{
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
