package models;
import java.time.LocalDateTime;
import models.constants.Constants.AccountConstants;

// Represents an account with its data that is stored in the database
public class Account {
    // Data
    private final Integer id; // Database Id
    private final String title; // The name of the account ex: "Gmail", "Instagram", etc
    private final String username; // The username
    private final String password; // The password for the account
    private final String url; // The URL of the account ex: "https://www.google.com"
    private final String note; // A note about the account ex: "This is my work email"
    private final LocalDateTime createdAt; // The date and time the account was created
    private final LocalDateTime lastInteract; // The date and time the account was last interacted with (Any CRUD)
    private final Integer interactionCount; // The number of times this account has been interacted with
    private final Boolean pinned; // Pinned accounts should be shown at the top of the list of accounts

    private Account(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.username = builder.username;
        this.password = builder.password;
        this.url = builder.url;
        this.note = builder.note;
        this.createdAt = builder.createdAt;
        this.lastInteract = builder.lastInteract;
        this.interactionCount = builder.interactionCount;
        this.pinned = builder.isPinned;
    }

    // Accessors
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastInteract() {
        return lastInteract;
    }

    public int getInteractionCount() {
        return interactionCount;
    }

    public boolean isPinned() {
        return pinned;
    }

    // Builder Pattern Class
    public static class Builder {
        // Fields
        private String title;
        private String username;
        private String password;
        private int id = AccountConstants.DEFAULT_ACCOUNT_ID;
        private String url = AccountConstants.DEFAULT_STRING;
        private String note = AccountConstants.DEFAULT_STRING;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime lastInteract = LocalDateTime.now();
        private int interactionCount = AccountConstants.DEFAULT_INTERACTION_COUNT;
        private boolean isPinned = false;

        // Field Setters
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastInteract(LocalDateTime lastInteract) {
            this.lastInteract = lastInteract;
            return this;
        }

        public Builder interactionCount(int interactionCount) {
            this.interactionCount = interactionCount;
            return this;
        }

        public Builder isPinned(boolean isPinned) {
            this.isPinned = isPinned;
            return this;
        }

        // The final build step
        public Account build() {
            return new Account(this);
        }
    }
}
