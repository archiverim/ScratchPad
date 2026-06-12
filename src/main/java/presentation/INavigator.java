package presentation;

// Implementors will be able to navigate to different main scenes in the app.
// This interface exists so it can be extended in the future when more pages
// are added to the app.
public interface INavigator {
    void navigateToDashboard();
}
