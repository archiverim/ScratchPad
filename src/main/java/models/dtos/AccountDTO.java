package models.dtos;

public class AccountDTO {
    private final int accountId;
    private final String title;
    private final String username;
    private final String password;
    private final String url;
    private final String note;

    public AccountDTO(int accountId, String title, String username, String password, String url, String note) {
        this.accountId = accountId;
        this.title = title;
        this.username = username;
        this.password = password;
        this.url = url;
        this.note = note;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getNote() {
        return note;
    }
}
