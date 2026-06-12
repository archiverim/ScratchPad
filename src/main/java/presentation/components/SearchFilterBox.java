package presentation.components;

import java.util.function.Consumer;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import presentation.constants.Constants;
import models.enums.SortingMode;
import java.util.List;

public class SearchFilterBox extends HBox {
    private final Consumer<SortingMode> onSortChange;
    private final ComboBox<SortingMode> filterDropdown;
    private final Runnable onNewClick;
    private final Consumer<String> onSearchClick;
    private final Button newAccountButton;
    private final TextField searchBar;

    public SearchFilterBox(Consumer<SortingMode> onSortChange, Runnable onNewClick, Consumer<String> onSearchClick,
            List<SortingMode> sortingModes) {
        super(Constants.Layout.COMPONENT_SPACING);
        this.onSortChange = onSortChange;
        this.onNewClick = onNewClick;
        this.onSearchClick = onSearchClick;

        // Set up the dropdown
        filterDropdown = new ComboBox<>();
        initDropdown(sortingModes);

        this.searchBar = new TextField();
        StackPane searchBarWithIcon = initSearchBar();

        this.newAccountButton = new Button();
        initButton(newAccountButton, Constants.Text.ADD_ACCOUNT_BUTTON_TOOLTIP, Constants.IconNames.ADD,
                e -> onNewClick.run(), "add-account-button");

        Label allItemsLabel = new Label(Constants.Text.ALL_ITEMS);
        allItemsLabel.getStyleClass().add("all-items-label");

        HBox welcomeBox = buildWelcomeBox();

        this.getChildren().addAll(allItemsLabel, newAccountButton, searchBarWithIcon, filterDropdown, welcomeBox);
        this.getStyleClass().add("search-box");

        setTestIds();
        this.setAlignment(Pos.CENTER_LEFT);
        setStyling(searchBarWithIcon);
    }

    private void initDropdown(List<SortingMode> sortingModes) {
        filterDropdown.getItems().addAll(sortingModes);

        filterDropdown.setValue(models.constants.Constants.SortingModes.DEFAULT_SORTING_MODE);

        // Make it run the method when the mode changes
        filterDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (this.onSortChange != null) {
                this.onSortChange.accept(newValue);
            }
        });
        filterDropdown.getStyleClass().add("search-filter-dropdown");
    }

    private StackPane initSearchBar() {
        searchBar.setPromptText(Constants.Text.SEARCH_BUTTON_TOOLTIP);
        searchBar.getStyleClass().add("search-bar-field");
        searchBar.setOnKeyPressed(this::handleSearchKeyDown);

        FontIcon searchIcon = new FontIcon(Constants.IconNames.SEARCH);
        searchIcon.getStyleClass().add("search-bar-icon");
        searchIcon.setOnMouseClicked(e -> onSearchClick.accept(searchBar.getText().trim()));

        StackPane wrapper = new StackPane();
        wrapper.getStyleClass().add("search-bar-wrapper");
        StackPane.setAlignment(searchBar, Pos.CENTER_LEFT);
        StackPane.setAlignment(searchIcon, Pos.CENTER_LEFT);
        StackPane.setMargin(searchIcon,
                new Insets(
                        Constants.INSETS.ZERO, Constants.INSETS.ZERO, Constants.INSETS.ZERO, Constants.INSETS.TEN));
        wrapper.getChildren().addAll(searchBar, searchIcon);
        HBox.setHgrow(wrapper, Priority.ALWAYS);
        return wrapper;
    }

    private void initButton(
            Button button, String tooltip, String iconName, EventHandler<ActionEvent> actionEvent, String styleClass) {
        button.setTooltip(new Tooltip(tooltip));
        button.setGraphic(new FontIcon(iconName));
        button.setOnAction(actionEvent);
        button.getStyleClass().add(styleClass);
    }

    private HBox buildWelcomeBox() {
        Label welcomeLabel = new Label(Constants.Text.WELCOME);
        welcomeLabel.getStyleClass().add("welcome-label");
        welcomeLabel.setWrapText(true);

        ImageView catImage = new ImageView();
        try {
            Image cat = new Image(getClass().getResourceAsStream(Constants.Resources.INITIAL_LOGO_PATH));
            catImage.setImage(cat);
            catImage.setFitHeight(Constants.Layout.IMG_HEIGHT);
            catImage.setPreserveRatio(true);
        } catch (Exception ignored) {
        }

        HBox box = new HBox(Constants.Layout.COMPONENT_SPACING, welcomeLabel, catImage);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setMinWidth(Constants.Layout.MIN_WIDTH);
        box.getStyleClass().add("welcome-box");
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    private void handleSearchKeyDown(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onSearchClick.accept(searchBar.getText().trim());
            event.consume();
        }
    }

    private void setStyling(StackPane searchBarWithIcon) {
        this.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(this, Priority.ALWAYS);

        filterDropdown.prefWidthProperty().bind(this.widthProperty().multiply(Constants.Layout.FILTER_DROPDOWN_RATIO));
        filterDropdown.prefHeightProperty().bind(this.heightProperty().multiply(Constants.Layout.FILTER_SEARCH_HEIGHT));

        searchBarWithIcon.prefWidthProperty().bind(this.widthProperty().multiply(Constants.Layout.SEARCH_BAR_RATIO));
        searchBar.prefHeightProperty().bind(this.heightProperty().multiply(Constants.Layout.FILTER_SEARCH_HEIGHT));

        newAccountButton.prefWidthProperty().bind(this.widthProperty().multiply(Constants.Layout.ADD_BUTTON_RATIO));
        newAccountButton.prefHeightProperty().bind(
                this.heightProperty().subtract(Constants.Layout.FILTER_SEARCH_HEIGHT));
    }

    private void setTestIds() {
        this.newAccountButton.setId("add-account-btn");
    }
}
