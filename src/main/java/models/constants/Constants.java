package models.constants;

import models.enums.SortingMode;

public final class Constants {
    public static final class AccountConstants {
        public static final int DEFAULT_ACCOUNT_ID = -1;
        public static final String DEFAULT_STRING = "";
        public static final int DEFAULT_INTERACTION_COUNT = 0;
    }

    public static final class SortingModes {
        public static final SortingMode DEFAULT_SORTING_MODE = models.enums.SortingMode.NEWEST_MODE;
        public static final String ALPHABETICAL_TITLE_ASC_MODE_MESSAGE = "Alphabetical - Title (asc.)";
        public static final String ALPHABETICAL_TITLE_DESC_MODE_MESSAGE = "Alphabetical - Title (desc.)";
        public static final String MOST_USED_MODE_MESSAGE = "Most Used";
        public static final String RECENT_USED_MODE_MESSAGE = "Recently Used";
        public static final String NEWEST_MODE_MESSAGE = "Newest";
        public static final String FAVOURITES_MODE_MESSAGE = "Favourites";
    }

}
