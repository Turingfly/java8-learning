package jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.sun.corba.se.pept.transport.Connection;

/**
 * 
 * @author chengfeili 
 * Jun 14, 2017 5:30:14 PM
 *
 *         Driver: Knows how to get a connection to the database 
 *         
 *         Connection: Knows how to communicate with the database 
 *         
 *         Statement: Knows how to run the SQL 
 *         
 *         ResultSet: Knows what was returned by a SELECT query
 * 
 *         In real applications, you should use a DataSource rather than
 *         DriverManager to get a Connection. For one thing, there’s no reason
 *         why you should have to know the database password. It’s far better if
 *         the database team or another team can set up a data source that you
 *         can reference. Another reason is that a DataSource maintains a
 *         connection pool so that you can keep reusing the same connection
 *         rather than needing to get a new one each time.
 */
public class ConnectingADataBase {
	public void connect() throws SQLException {
		Connection conn = (Connection) DriverManager.getConnection("jdbc:derby:zoo");
		System.out.println(conn);

		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/ocp-book",
				"username", "password");
		System.out.println(conn1);
	}

	// Statement: Knows how to run the SQL
	public void obtainingAStatement() throws SQLException {
		Connection conn = (Connection) DriverManager.getConnection("jdbc:derby:zoo");

		/**
		 * Parameters: resultSetType a result set type; one of
		 * ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, or
		 * ResultSet.TYPE_SCROLL_SENSITIVE resultSetConcurrency a concurrency
		 * type; one of ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
		 */
		Statement stmt = ((java.sql.Connection) conn).createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);

	}

	public void executingAStatement() throws SQLException {
		Connection conn = (Connection) DriverManager.getConnection("jdbc:derby:zoo");
		Statement stmt = ((java.sql.Connection) conn).createStatement();
		int result = stmt.executeUpdate("insert into species values(10, 'Deer', 3)");
		System.out.println(result); // 1
		result = stmt.executeUpdate("update species set name = '' where name = 'None'");
		System.out.println(result); // 0
		result = stmt.executeUpdate("delete from species where id =10");
		System.out.println(result); // 1

		/**
		 * If sql is a SELECT , the boolean is true and we can get the ResultSet
		 * . If it is not a SELECT , we can get the number of rows updated.
		 */
		boolean isResultSet = stmt.execute("sql");
		if (isResultSet) {
			ResultSet rs = stmt.getResultSet();
			System.out.println("ran a query");
		} else {
			int result1 = stmt.getUpdateCount();
			System.out.println("ran an update");
		}
	}

	public void readingAResultSet() throws SQLException {
		Map<Integer, String> idToNameMap = new HashMap<>();
		Connection conn = (Connection) DriverManager.getConnection("jdbc:derby:zoo");
		Statement stmt = ((java.sql.Connection) conn).createStatement();
		ResultSet rs = stmt.executeQuery("select id, name from species");
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			// int id = rs.getInt(1); // first column
			// String name = rs.getString(2);
			idToNameMap.put(id, name);
		}
		// getObject()
		while (rs.next()) {
			Object idField = rs.getObject("id");
			Object nameField = rs.getObject("name");
			if (idField instanceof Integer) {
				int id = (Integer) idField;
				System.out.println(id);
			}
			if (nameField instanceof String) {
				String name = (String) nameField;
				System.out.println(name);
			}
			System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}
		}
		rs.afterLast();
		System.out.println(rs.previous()); // last row
		System.out.println(rs.first());
		System.out.println(rs.last());
		System.out.println(rs.absolute(1)); // first row
		System.out.println(rs.absolute(-1)); // last row
	}

	// closing a Database
	/**
	 * 
	public void closingDataBase() throws SQLException {
		String url = " jdbc:derby:zoo";
		try (Connection conn = (Connection) DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select name from animal")) {
			while (rs.next())
				System.out.println(rs.getString(1));
		}
	}
	 */

	// closing a Database prior to Java 7
	public void closingDataBase1() throws SQLException {
		String url = "jdbc:derby:zoo";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = (Connection) DriverManager.getConnection(url);
			stmt = ((java.sql.Connection) conn).createStatement();
			rs = stmt.executeQuery("select name from animal");
			while (rs.next())
				System.out.println(rs.getString(1));
		} finally

		{
			closeResultSet(rs);
			closeStatement(stmt);
			// closeConnection(conn);
		}
	}

	private void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
	}

	private void closeStatement(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
		}
	}

	/**
	private void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}
	*/

	/**
	public void dealingWithExceptions() {
		String url = " jdbc:derby:zoo";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select not_a_column from animal")) {
			while (rs.next())
				System.out.println(rs.getString(1));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getErrorCode());
		}
	}
	*/
}
