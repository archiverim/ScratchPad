package models.enums;

import models.constants.Constants;

public enum SortingMode {
    MOST_USED_MODE(Constants.SortingModes.MOST_USED_MODE_MESSAGE),
    RECENT_USED_MODE(Constants.SortingModes.RECENT_USED_MODE_MESSAGE),
    NEWEST_MODE(Constants.SortingModes.NEWEST_MODE_MESSAGE),
    FAVOURITES_MODE(Constants.SortingModes.FAVOURITES_MODE_MESSAGE),
    ALPHABETICAL_TITLE_ASC_MODE(Constants.SortingModes.ALPHABETICAL_TITLE_ASC_MODE_MESSAGE),
    ALPHABETICAL_TITLE_DESC_MODE(Constants.SortingModes.ALPHABETICAL_TITLE_DESC_MODE_MESSAGE);

    private final String displayValue;

    SortingMode(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
