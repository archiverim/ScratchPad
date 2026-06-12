package presentation.constants;

public class Constants {
    public static final class Layout {
        public static final double ACCOUNT_CARD_INBETWEEN_SPACING = 10;
        public static final double APP_STARTING_RATIO = 0.65;
        public static final double HBOX_SPACING = 6;
        public static final double COMPONENT_SPACING = 8;
        public static final int STAR_SIZE = 18;
        public static final double MIN_WIDTH = 160;
        public static final double IMG_HEIGHT = 48;
        public static final int DASHBOARD_SIDE_COL_WIDTH = 38;
        public static final int DASHBOARD_MAIN_COL_WIDTH = 100 - DASHBOARD_SIDE_COL_WIDTH;
        public static final int DASHBOARD_TOP_ROW_HEIGHT = 10;
        public static final int DASHBOARD_BOTTOM_ROW_HEIGHT = 100 - DASHBOARD_TOP_ROW_HEIGHT;
        public static final double LABELLED_INPUT_SPACING = 2;
        public static final double FILTER_DROPDOWN_RATIO = 0.15;
        public static final double SEARCH_BAR_RATIO = 0.15;
        public static final double ADD_BUTTON_RATIO = 0.06;
        public static final double FILTER_SEARCH_HEIGHT = 0.3;
    }

    public static final class Text {
        public static final String ADD_ACCOUNT_BUTTON_TOOLTIP = "Add an account";
        public static final String ALL_ITEMS = "All Items";
        public static final String WELCOME = "Welcome to\nScratchPad!";
        public static final String CANCEL = "Cancel";
        public static final String COPY_SUCCESS_TITLE = "Copied to clipboard!";
        public static final String COPY_TO_CLIPBOARD = "Copy password to clipboard";
        public static final String COPY_SUCCESS_MESSAGE = "%s has been copied to clipboard for %d seconds.";
        public static final String DYNAMIC_BOX_PLACEHOLDER = "Select an account to edit or add a new account";
        public static final String GENERATE_PASSWORD = "Generate Password";
        public static final String NOTES = "Notes";
        public static final String NO_ACCOUNTS_TO_DISPLAY = "No accounts to display";
        public static final String PASSWORD = "Password";
        public static final String SAVE = "Save";
        public static final String SEARCH_BUTTON_TOOLTIP = "Search for an account";
        public static final String SHOW_HIDE_PASSWORD = "Show/Hide Password";
        public static final String TITLE = "Title";
        public static final String URL = "URL";
        public static final String DELETE = "DELETE";
        public static final String USERNAME = "Username";
        public static final String WINDOW_TITLE_LOGIN = "ScratchPad";
        public static final String DATE_TIME_ZONE = "UTC";
        public static final String DATE_CREATED_LABEL = "Created:";
        public static final String INTERACTION_COUNT_LABEL = "Interact Count:";
        public static final String CREATE = "Create";
        public static final String EDIT_HEADER = "Edit Account";
        public static final String ADD_HEADER = "New Account";
        public static final String DELETE_CONFIRMATION_TITLE = "Delete Confirmation";
        public static final String DELETE_CONFIRMATION_SUBTITLE = "Delete Account";
        public static final String DELETE_CONFIRMATION_PROMPT =
                "Are you sure you want to delete this account? This cannot be undone.";
    }

    public static final class Numbers {
        public static final int COPY_TIMEOUT = 30; // 30s copy timeout
        public static final int MAX_USERNAME_LEN = 254; // RFC standard for emails
        public static final int MAX_URL_LEN = 500;
        public static final int MAX_NOTE_INPUT_LEN = 5000;
        public static final int MAX_TITLE_LEN = 50;
        public static final int MAX_PASSWORD_LEN = 100;
        public static final int GENERATED_PASSWORD_LENGTH = 12;
    }

    public static final class Resources {
        public static final String INITIAL_LOGO_PATH = "/images/logincat.png";
        public static final String STYLE_SHEET_PATH = "/styles/style.css";
    }

    public static final class ExceptionMessages {
        public static final String UNEXPECTED = "An unexpected error occurred.";
    }

    public static final class IconNames {
        public static final String ADD = "fas-plus";
        public static final String CANCEL = "fas-ban";
        public static final String COPY = "far-copy";
        public static final String EYE_CLOSED = "fas-eye-slash";
        public static final String EYE_OPEN = "fas-eye";
        public static final String GENERATE = "fas-random";
        public static final String SAVE = "fas-save";
        public static final String SEARCH = "fas-search";
    }

    public static final class INSETS {
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int EIGHT = 8;
        public static final int TEN = 10;
        public static final int TWENTY4 = 24;
    }
}
