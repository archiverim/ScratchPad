package unit;

import business.AccountSearchingService;
import models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static constants.TestConstants.SearchServiceTests.*;
import static constants.TestConstants.SearchServiceTests.ACCOUNT_SECRET;
import static constants.TestConstants.SearchServiceTests.DIFFERENT_CASE;
import static constants.TestConstants.SearchServiceTests.NOTE_SECRET;
import static constants.TestConstants.SearchServiceTests.SAME_CASE;
import static constants.TestConstants.SearchServiceTests.URL_PARTIAL;
import static constants.TestConstants.SearchServiceTests.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Isolated tests for the AccountSearchingService using fakes
public class AccountSearchingServiceTest {
    @Nested
    class SearchAccountTests {
        private AccountSearchingService searchingService;
        private List<Account> dummy;

        @BeforeEach
        public void setup() {
            dummy = Arrays.asList(ACCOUNT_HOOGLE_1, ACCOUNT_HOOGLE_2, ACCOUNT_CHASEBOOK, ACCOUNT_SECRET);
            this.searchingService = new AccountSearchingService();
        }

        @Test
        public void emptySearchText() {
            // Act
            searchingService.setSearchText("");
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(4, result.size());
        }

        @Test
        public void searchTitleSameCase() {
            // Act
            searchingService.setSearchText(SAME_CASE);
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(a -> a.getTitle().equalsIgnoreCase(SAME_CASE)));
        }

        @Test
        public void searchTitleDifferentCase() {
            // Act
            searchingService.setSearchText(DIFFERENT_CASE);
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(a -> a.getTitle().equalsIgnoreCase(DIFFERENT_CASE)));
        }

        @Test
        public void searchUsername() {
            // Act
            searchingService.setSearchText(USERNAME);
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(1, result.size());
            assertTrue(result.stream().allMatch(a -> a.getUsername().equalsIgnoreCase(USERNAME)));
        }

        @Test
        public void searchURL() {
            // Act
            searchingService.setSearchText(URL_PARTIAL);
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(1, result.size());
            assertTrue(result.stream().allMatch(a -> a.getUrl().toLowerCase().contains(URL_PARTIAL.toLowerCase())));
        }

        @Test
        public void searchNotes() {
            // Act
            searchingService.setSearchText(NOTE_SECRET);
            List<Account> result = searchingService.search(dummy);
            // Assert
            assertEquals(1, result.size());
            assertTrue(result.stream().allMatch(a -> a.getNote().equalsIgnoreCase(NOTE_SECRET)));
        }
    }
}
