package lib.results;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Row extends HashMap<String, String>{
	
	public Row(ResultSet res) throws SQLException{
		super();
		int columnCount = res.getMetaData().getColumnCount();
		for (int i = 1; i < columnCount; i++){
			this.put(res.getMetaData().getColumnName(i), res.getString(i));
			}
	}
	
    public ArrayList<String> getRowByColumns(ArrayList<String> columns){
		ArrayList<String> row = new ArrayList<String>();
		for (String column:columns){
			row.add(this.get(column));
		}
		return row;
	}
}
