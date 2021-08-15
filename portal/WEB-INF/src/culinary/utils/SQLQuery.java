package culinary.utils;

import java.sql.*;

public class SQLQuery {

	private Statement stmt;

	public SQLQuery(Object conn) throws SQLException {
		this.stmt = ((Connection) conn).createStatement();

		}

	public ResultSet select(String query) throws SQLException {
		return stmt.executeQuery(query);
	}

	public int insert(String query) throws SQLException {
		return stmt.executeUpdate(query);
	}

	public long insert(String query, boolean b) throws SQLException {
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		long autoIncKeyFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncKeyFromApi = rs.getInt(1);
		} else {

			// throw an exception from here
		}

		rs.close();
		return autoIncKeyFromApi;
	}

	public int update(String query) throws SQLException {
		return stmt.executeUpdate(query);
	}

	public int delete(String query) throws SQLException {
		return stmt.executeUpdate(query);
	}

	public static int getResultSetSize(ResultSet rs) throws SQLException {
		int size = 0;
		if (rs.last()) {
			size = rs.getRow();
			rs.beforeFirst();
		}
		return size;
	}

}
