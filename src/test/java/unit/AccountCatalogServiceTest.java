package unit;

import application.DependencyInitializer;
import business.AccountCatalogService;
import business.AccountSearchingService;
import business.AccountSortingModeRegistry;
import business.AccountSortingService;
import business.wrappers.*;
import models.Account;
import models.enums.SortingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.IAccountRepository;

import java.util.Arrays;
import java.util.List;

import static constants.TestConstants.CatalogServiceTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

// This class tests the combination of sorting and searching, what we call a catalog
// Mostly to cover any exceptional cases in AccountCatalogService
@ExtendWith(MockitoExtension.class)
public class AccountCatalogServiceTest {
    @Mock private IAccountRepository accountRepository;
    private AccountSortingService sortingService;
    private AccountSearchingService searchingService;
    private AccountCatalogService catalogService;
    private List<Account> mockDB;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockDB = Arrays.asList(
                ACCOUNT_ALPHA_POPULAR, ACCOUNT_ALPHA_NEWEST, ACCOUNT_PINNED_PARTIAL, ACCOUNT_ZETA_CONTROL);
        DependencyInitializer dependencyInitializer = DependencyInitializer.getInstance();
        AccountSortingModeRegistry registry = dependencyInitializer.createAccountSortingModeRegistry();
        sortingService = new AccountSortingService(registry);
        searchingService = new AccountSearchingService();
        catalogService = new AccountCatalogService(accountRepository, sortingService, searchingService);
        lenient().when(accountRepository.getAllAccounts()).thenReturn(mockDB);
    }

    @Test
    public void limitCatalogToNegativeTest() {
        // Test catalog requesting a negative limit
        int limit = -1;
        List<Account> results = catalogService.getAccounts(limit);
        assertEquals(0, results.size());
    }

    @Test
    public void limitCatalogOverMaximumTest() {
        // Test catalog requesting a limit past the actual size
        int limit = mockDB.size() + 1;
        List<Account> results = catalogService.getAccounts(limit);
        assertEquals(mockDB.size(), results.size());
    }

    @Test
    public void sharedTitleAlphaSortTest() {
        // Arrange, set the search text and sorting
        searchingService.setSearchText(TITLE_SEARCH_TEXT);
        sortingService.setSortingMode(SortingMode.ALPHABETICAL_TITLE_ASC_MODE);
        // Act
        List<Account> results = catalogService.getAccounts();
        // Assert they are in alphabetical order
        assertEquals(3, results.size());
        assertEquals(ACCOUNT_ALPHA_POPULAR.getId(), results.get(0).getId());
        assertEquals(ACCOUNT_ALPHA_NEWEST.getId(), results.get(1).getId());
        assertEquals(ACCOUNT_PINNED_PARTIAL.getId(), results.get(2).getId());
    }

    @Test
    public void sharedTitleCreatedTimeTest() {
        // Arrange, set the search text and sorting
        searchingService.setSearchText(TITLE_SEARCH_TEXT);
        sortingService.setSortingMode(SortingMode.NEWEST_MODE);
        // Act
        List<Account> results = catalogService.getAccounts();
        // Assert they are newest first
        assertEquals(3, results.size());
        assertEquals(ACCOUNT_ALPHA_NEWEST.getId(), results.get(0).getId());
        assertEquals(ACCOUNT_ALPHA_POPULAR.getId(), results.get(1).getId());
        assertEquals(ACCOUNT_PINNED_PARTIAL.getId(), results.get(2).getId());
    }

    @Test
    public void sharedTitlePinnedTest() {
        // Arrange, set the search text and sorting
        searchingService.setSearchText(TITLE_SEARCH_TEXT);
        sortingService.setSortingMode(SortingMode.FAVOURITES_MODE);
        // Act
        List<Account> results = catalogService.getAccounts();
        // Assert the first one is pinned
        assertEquals(3, results.size());
        assertEquals(ACCOUNT_PINNED_PARTIAL.getId(), results.getFirst().getId());
    }
}
