package unit;

import application.DependencyInitializer;
import business.AccountSortingModeRegistry;
import business.AccountSortingService;
import models.Account;
import models.enums.SortingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static constants.TestConstants.AccessTimeSortingTests.OLD_ACCESS;
import static constants.TestConstants.AccessTimeSortingTests.RECENT_ACCESS;
import static constants.TestConstants.AccountPinningSortingTests.*;
import static constants.TestConstants.AccountPinningSortingTests.FAV_ACC_2;
import static constants.TestConstants.AssertionMessages.CHRONOLOGICALLY;
import static constants.TestConstants.AssertionMessages.DESCENDING_ORDER;
import static constants.TestConstants.CreationTimeSortingTests.*;
import static constants.TestConstants.CreationTimeSortingTests.ACCOUNT_OLDEST;
import static constants.TestConstants.MostUsedSortingTests.HIGH_USE;
import static constants.TestConstants.MostUsedSortingTests.LOW_USE;
import static constants.TestConstants.TitleSortingTests.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountSortingServiceTest {
    private AccountSortingService sortingService;
    private AccountSortingModeRegistry registry;

    @BeforeEach
    public void setup() {
        DependencyInitializer dependencyInitializer = DependencyInitializer.getInstance();
        registry = dependencyInitializer.createAccountSortingModeRegistry();
    }

    @Nested
    class getMostUsedAccountsTests {
        private List<Account> dummyData;

        @BeforeEach
        public void setup() {
            dummyData = Arrays.asList(LOW_USE, HIGH_USE);
            sortingService = new AccountSortingService(registry);
        }

        @Test
        public void descendingOrderUsageSortingTest() {
            sortingService.setSortingMode(SortingMode.MOST_USED_MODE);
            List<Account> result = sortingService.sort(dummyData);
            assertEquals(dummyData.size(), result.size());
            assertEquals(HIGH_USE.getId(), result.getFirst().getId(), DESCENDING_ORDER);
            assertEquals(LOW_USE.getId(), result.get(1).getId(), DESCENDING_ORDER);
        }
    }

    @Nested
    class GetRecentlyAccessedAccountsTests {
        private AccountSortingService sortingService;
        private List<Account> dummyData;

        @BeforeEach
        public void setup() {
            dummyData = Arrays.asList(OLD_ACCESS, RECENT_ACCESS);
            sortingService = new AccountSortingService(registry);
        }

        @Test
        public void sortsNewestFirstTest() {
            sortingService.setSortingMode(SortingMode.RECENT_USED_MODE);
            List<Account> result = sortingService.sort(dummyData);
            assertEquals(dummyData.size(), result.size());
            assertEquals(RECENT_ACCESS.getId(), result.getFirst().getId(), CHRONOLOGICALLY);
            assertEquals(OLD_ACCESS.getId(), result.get(1).getId(), CHRONOLOGICALLY);
        }
    }

    @Nested
    class GetPinnedAccountsTests {
        private AccountSortingService sortingService;

        @BeforeEach
        public void setup() {
            sortingService = new AccountSortingService(registry);
        }

        @Test
        public void sortsPinnedAccountsFirstTest() {
            // Arrange
            // Create three accounts where their pinned value is in a mixed up order
            List<Account> accounts = Arrays.asList(FAV_ACC_1, NOT_FAV_ACC_1, FAV_ACC_2);
            sortingService.setSortingMode(SortingMode.FAVOURITES_MODE);
            // Act
            List<Account> result = sortingService.sort(accounts);
            // Assert the pinned accounts are at the top
            assertEquals(accounts.size(), result.size());
            assertTrue(result.get(0).isPinned());
            assertTrue(result.get(1).isPinned());
            assertFalse(result.get(2).isPinned());
        }

        @Test
        void returnsAllAccountsWhenNoPinnedAccountsTest() {
            List<Account> accounts = Arrays.asList(NOT_FAV_ACC_1, NOT_FAV_ACC_2);
            sortingService.setSortingMode(SortingMode.FAVOURITES_MODE);
            List<Account> result = sortingService.sort(accounts);
            assertEquals(accounts.size(), result.size());
        }

        @Test
        void returnsAllAccountsWhenAllPinnedAccountsTest() {
            List<Account> accounts = Arrays.asList(FAV_ACC_1, FAV_ACC_2);
            sortingService.setSortingMode(SortingMode.FAVOURITES_MODE);
            List<Account> result = sortingService.sort(accounts);
            assertEquals(accounts.size(), result.size());
        }
    }

    @Nested
    class GetNewestAccountsTests {
        private AccountSortingService sortingService;
        private List<Account> dummyData;

        @BeforeEach
        public void setup() {
            // Create accounts with different createdAt times
            dummyData = Arrays.asList(ACCOUNT_OLDEST, ACCOUNT_NEWEST, ACCOUNT_MIDDLE);
            // Create a stub repository to return the accounts
            sortingService = new AccountSortingService(registry);
        }

        @Test
        public void newestAccountsFirstTest() {
            // Get the newest accounts
            sortingService.setSortingMode(SortingMode.NEWEST_MODE);
            List<Account> newestAccounts = sortingService.sort(dummyData);
            // Check that there are 3 accounts, and they are in the correct order (ACCOUNT_NEWEST, ACCOUNT_MIDDLE,
            // ACCOUNT_OLDEST)
            assertEquals(dummyData.size(), newestAccounts.size());
            assertEquals(ACCOUNT_NEWEST.getId(), newestAccounts.getFirst().getId());
            assertEquals(ACCOUNT_MIDDLE.getId(), newestAccounts.get(1).getId());
            assertEquals(ACCOUNT_OLDEST.getId(), newestAccounts.get(2).getId());
        }
    }

    @Nested
    class GetAccountsAlphabeticalTests {
        private AccountSortingService sortingService;
        private List<Account> dummyData;

        @BeforeEach
        public void setup() {
            this.sortingService = new AccountSortingService(registry);
            dummyData = List.of(ACC_APPLE, ACC_AARDVARK, ACC_APPLE_JUICE, ACC_BANANA);
        }

        @Test
        public void alphabeticalAscendingTest() {
            // Arrange
            sortingService.setSortingMode(SortingMode.ALPHABETICAL_TITLE_ASC_MODE);
            // Act
            List<Account> result = sortingService.sort(dummyData);
            // Assert
            assertEquals(4, result.size());
            assertEquals(ACC_AARDVARK.getId(), result.getFirst().getId());
            assertEquals(ACC_APPLE.getId(), result.get(1).getId());
            assertEquals(ACC_APPLE_JUICE.getId(), result.get(2).getId());
            assertEquals(ACC_BANANA.getId(), result.get(3).getId());
        }

        @Test
        public void alphabeticalDescendingTest() {
            // Arrange
            sortingService.setSortingMode(SortingMode.ALPHABETICAL_TITLE_DESC_MODE);
            // Act
            List<Account> result = sortingService.sort(dummyData);
            // Assert
            assertEquals(4, result.size());
            assertEquals(ACC_BANANA.getId(), result.getFirst().getId());
            assertEquals(ACC_APPLE_JUICE.getId(), result.get(1).getId());
            assertEquals(ACC_APPLE.getId(), result.get(2).getId());
            assertEquals(ACC_AARDVARK.getId(), result.get(3).getId());
        }
    }
}
