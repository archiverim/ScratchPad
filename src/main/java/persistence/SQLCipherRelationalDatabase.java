package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import persistence.constants.DatabaseQueries.SQLCipher.Queries;
import persistence.exceptions.DatabaseInitializationException;

public class SQLCipherRelationalDatabase implements IPasswordProtectedRelationalDatabase {
    private Connection connection;
    private String dbFilePath;
    private String schema;

    /*** Constructor ***/
    public SQLCipherRelationalDatabase(String dbFilePath, String schema) {
        this.dbFilePath = dbFilePath;
        this.schema = schema;
    }

    /*** Public Methods ***/

    // Execute any queries that return values (SELECT)
    @Override
    public java.sql.ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    // Execute any queries with arguments that return values (SELECT)
    @Override
    public java.sql.ResultSet executeQuery(String sql, Object... args) throws SQLException {
        PreparedStatement statement = prepareStatement(sql, java.util.Arrays.asList(args));
        return statement.executeQuery();
    }

    // Execute any queries that make updates to the database and return no values (INSERT, UPDATE, DELETE)
    @Override
    public int executeUpdate(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    // Execute any queries with arguments that make updates to the database and return no values (INSERT, UPDATE,
    // DELETE)
    @Override
    public int executeUpdate(String sql, Object... args) throws SQLException {
        PreparedStatement statement = prepareStatement(sql, java.util.Arrays.asList(args));
        return statement.executeUpdate();
    }

    // Safely closes the database connection
    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Connects to the database, setEncryptionKey() must be called first otherwise InvalidPasswordException will be
    // thrown
    @Override
    public void connect(String password) {
        try {
            this.connection = this.getDBConnection(this.dbFilePath, password);
            this.initializeSchema(this.schema);
            this.testDatabaseConnection();
        } catch (SQLException e) {
            throw new DatabaseInitializationException();
        }
    }

    /*** Private Methods ***/
    // Helper that makes a prepared statement
    private PreparedStatement prepareStatement(String sql, List<Object> params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i)); // params are 1 indexed (skull emoji)
        }
        return statement;
    }

    // Establishes a connection to the SQLCipher database, creating it if it doesn't exist (and initializing the schema)
    private Connection getDBConnection(String databasePath, String dbPassword) throws SQLException {
        // This won't ever change for this implementation, so I'm not making them constants
        String url = "jdbc:sqlite:" + databasePath;
        Properties props = new Properties();
        props.setProperty("cipher", "sqlcipher");
        props.setProperty("key", dbPassword);

        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    // Init the schema
    private void initializeSchema(String schema) throws SQLException {
        executeUpdate(schema);
    }

    // Runs a simple test to check if the database connection is valid (ex: password is incorrect), throws an exception
    // if not
    private void testDatabaseConnection() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(Queries.TEST_CONNECTION);
        }
    }
}
