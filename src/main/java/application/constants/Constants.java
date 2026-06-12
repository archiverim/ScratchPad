package application.constants;

public class Constants {
    public static final String DATABASE_PATH = "data.db";

    // Our intention initially was to create a login system where the user
    // would enter their password, but this never came to be. Something to
    // keep in mind is that even hardcoding this password is an improvement
    // over just storing everything in a SQLite db, as there is more work
    // involved in reading this file. App Login has been moved to the future
    // milestone.
    public static final String DATABASE_KEY = "password";
    
}
