package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPasswordProtectedRelationalDatabase {
    // For executing queries that return results (SELECT)
    ResultSet executeQuery(String sql) throws SQLException;
    ResultSet executeQuery(String sql, Object... args) throws SQLException;

    // For executing updates (INSERT, UPDATE, DELETE)
    int executeUpdate(String sql) throws SQLException;
    int executeUpdate(String sql, Object... args) throws SQLException;

    // Close database connection
    void close() throws SQLException;

    // Initialize connection to the database
    void connect(String password);
}
