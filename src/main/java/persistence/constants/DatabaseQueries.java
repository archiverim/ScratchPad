package persistence.constants;

public class DatabaseQueries {
    public static class SQLCipher {
        public static class Schema {
            public static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE IF NOT EXISTS Accounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title TEXT NOT NULL,"
                    + "username TEXT NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "url TEXT NOT NULL,"
                    + "note TEXT NOT NULL,"
                    + "createdAt DATETIME  NOT NULL DEFAULT (datetime('now')),"
                    + "lastInteract DATETIME  NOT NULL DEFAULT (datetime('now')),"
                    + "interactionCount INTEGER  NOT NULL DEFAULT 0,"
                    + "pinned INTEGER DEFAULT 0"
                    + ");";
        }

        public static class Queries {
            public static final String TEST_CONNECTION = "SELECT 1;";
            public static final String INSERT_ACCOUNT =
                    "INSERT INTO Accounts (title, username, password, url, note, pinned) VALUES (?, ?, ?, ?, ?, ?);";
            public static final String SELECT_ALL_ACCOUNTS = "SELECT * FROM Accounts;";
            public static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM Accounts WHERE id = ?;";
            public static final String UPDATE_ACCOUNT_BY_ID =
                    "UPDATE Accounts SET title = ?, username = ?, password = ?, url = ?, note = ?, createdAt = ?, "
                    + "lastInteract = ?, interactionCount = ?, pinned = ? WHERE id = ?;";
            public static final String DELETE_ACCOUNT_BY_ID = "DELETE FROM Accounts WHERE id = ?;";
        }
    }
}
