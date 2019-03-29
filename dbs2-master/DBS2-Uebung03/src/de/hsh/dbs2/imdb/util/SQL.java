package de.hsh.dbs2.imdb.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL {
		
		static Connection conn = null ;
		static Statement stmt = null ;
		
		public static void init() {
			
			try {
				conn = DBConnection.getConnection();
				stmt = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
		

		public static void sqlEingabe(String sql) throws SQLException {
			init();
			stmt.execute(sql);
			stmt.close();
			DBConnection.getConnection().commit();
		}
		

		public static ResultSet sqlAbfrage(String sql) throws SQLException {	
			init();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
			
		}
		
		public static void close() throws SQLException {
			if(!conn.isClosed()) conn.close();
		}
}