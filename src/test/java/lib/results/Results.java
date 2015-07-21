package lib.results;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class Results extends ArrayList<Row>{
	
	public static ArrayList<String> orderColumns = null;
	
	public Results(ResultSet res) throws SQLException {
		while (res.next()) {
			this.add(new Row(res));
		}
	}

	public static Comparator<Row> RowsASC = new Comparator<Row>() {
	
		public int compare(Row r1, Row r2) {
			
			for (String column:orderColumns){
				if (r1.get(column).equals(r2.get(column))){
					continue;}
				return r1.get(column).compareTo(r2.get(column));
			}
			return 0;
		}
	};	
	
	public static Comparator<Row> RowsDESC = new Comparator<Row>() {
		
		public int compare(Row r1, Row r2) {
			
			for (String column:orderColumns){
				if (r1.get(column).equals(r2.get(column))){
					continue;}
				return r2.get(column).compareTo(r1.get(column));
			}
			return 0;
		}
	};
	
	@Override
	public String toString(){
		return "Results with " + this.size() + " rows";
	}
	
}

