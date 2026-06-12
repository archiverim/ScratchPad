# A01-G12-PasswordManagerApp
## Vision Statement – ScratchPad
ScratchPad aims to provide a clean, easy to use interface in which users can intuitively manage and secure passwords, sensitive information and other sensitive documents without needing to stumble through menus and pages.

Designed from a user-first perspective, ScratchPad eliminates the hassle of trying to create and remember secure passwords by providing features such strong randomized password generation, duplicate password checking and integration with the user clipboard to encourage smooth access to any accounts saved within it. The goal is to provide users with a secure, reliable, and convenient way to manage their credentials while building confidence that their sensitive data remains protected and easily accessible.

ScratchPad puts privacy and security first by rejecting SaaS and cloud-based ideologies and instead providing a local-only, open-source solution for users who do not want to risk storing sensitive credentials through remote hosting services. Designed to strengthen account security and protect information from unauthorized access and potential attackers, the application allows users to securely store passwords and other sensitive data in a centralized location without sacrificing convenience or reliability.

Through features such as password encryption, weak and reused password detection, and password expiration reminders, ScratchPad encourages stronger security practices while helping users better manage their credentials. By combining accessibility, encryption, and security-focused functionality, the application aims to create a trusted environment that users can understand immediately and continue returning to because of its reliability, convenience, and focus on protecting sensitive information.

The project will be considered successful if users are able to securely store, access, and manage their sensitive information through a stable and responsive application experience. Success means that all passwords and stored data are protected using AES-256 encryption, authentication is consistently required before access is granted, and encryption and decryption processes operate reliably without exposing user information. The application should also detect and alert users about repeated passwords to encourage stronger security practices. In addition, all sensitive data must successfully pass vulnerability and security testing to ensure the system remains resistant to common threats. The project will further be considered successful if users can quickly store and retrieve their information without noticeable login delays or loading times, and if the frontend remains stable, intuitive, and free from performance issues that could interrupt the user experience.

## Team Working Agreement
Here is our [Team Working Agreement](/docs/TWA.md)

## Environment Requirements
```bash
- Gradle 9.x (wrapper gradlew provided)
- Java 21
- All dependencies are managed through gradle
```

## Building & Running
```bash
git clone https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp.git
./gradlew run
```

## Developer Recommended Workflow
```bash
./gradlew clean
./gradlew test
./gradlew test jacocoTestReport
```
Then:
- Open coverage report
- Inspect logic layer coverage

## Testing
Our tests are focuses on covering:
- Sorting logic
- Filtering logic
- Validation
- Edge cases
- Password constraints

## Running All Tests
```bash
./gradlew test
```

## Running a Specific Test Types
```bash
./gradlew test --tests "unit.*" # unit tests (business logic)
./gradlew test --tests "integration.*" # integration tests (layer interaction)
./gradlew test --tests "e2e.*" # end-to-end tests (full flow)
```

>Note: When running end-to-end tests, we have had issues because sometimes the window will lose focus on the first one and cause the test to fail. From our research we've found this may be a bug in JavaFX. To counteract it we've found that minimizing all windows after initiating the test works. Or try:
>```
>./gradlew clean
>```
> Then run the above test command(s). However, the first suggestion works the best.

## Generating Coverage Reports
>Note: Ensure that all tests pass before you generate coverage.

We use **JaCoCo** via Gradle for consistent, reliable, reproducable coverage.
```bash
./gradlew test jacocoTestReport
```
Then open build/reports/jacoco/test/html/index.html in your browser.

## Running Demo Website

The website lives in the `website/` directory and is a static site built with HTML, CSS, and Vanilla JavaScript.

To run it locally, open `website/index.html` in your browser, or use one of the following methods from the project root:

**Using Python:**
```bash
cd website  
python -m http.server 8000
```
Then visit `http://localhost:8000` in your browser.

**Using Node.js:**
```bash
npx serve website
```

**Using a file path (macOS):**
```bash
open ./website/index.html
```

**Using a file path (Windows):**
```bash
start ./website/index.html
```

## Project Structure
- Project structure explanation (presentation, business, persistence, application packages and their responsibilities)

### Presentation
The presentation package is the outermost layer of the application. It acts as the direct interface for the user and handles how data is displayed and how user inputs are initially captured.
- `components` - Contains smaller components of our UI and manages actual visual elements of the application.
- `utils`- Provide utilities such as date formatting or providing access to commonly used `TextFormatters`.
- `controllers` - Control larger UI components such as the main `DashboardView`.
- `views` - Hold views of larger UI components.
- Other classes such as `WindowManager` exist to assist in managing the main current scene, and `ClipboardService` to provide clipboard functionality.
- UI Components: Manages the actual visual elements of the application (e.g., windows, buttons, text fields, layouts).
- Exception Handling: Catches and handles presentation-specific errors to ensure the application doesn't crash abruptly from user mistakes.
- Event Listeners: Monitors and responds to user interactions (like clicks, keystrokes, or window scaling) and forwards those actions down to the logic layer.

### Business
The business package sits in the middle of the architecture. It acts as the brain of the application, decoupling the user interface from the raw data access.
- `utils` - Provides utility classes such as a `StringUtility` and `ExceptionCollector`.
- `wrappers` - Hold the wrappers for `Account` to implement `Comparable` so we could use a registry and strategy pattern for sorting.
- Services such as `AccountService`, `AccountCatalogService`, `AccountSortingService`, `AccountSearchingService` provide a middle layer to perform CRUD operations with the persistence layer and to return a filtered list of `Accounts`.
- Validator (UI Field Validation): Ensures that any input entering the system from the presentation layer meets specific format and business rules before it is processed further.
- Exceptions (Custom Exceptions): Defines business-logic-specific errors that help pinpoint algorithmic or process failures.

### Models
The models package acts as a shared foundation, containing data structures and configurations used across the different layers.
- `Account` is our main model for what is stored in the database.
- `dtos` - Holds data transfer objects, notably `AccountDTO`.
- `enums` - Contains any enums, currently our `SortingMode` enum.

### Persistence
The persistence package is responsible for data storage, retrieval, and management. It isolates the rest of the application from the specific database technology being used.

## Dependency Management
- JavaFX
  - Used for building the graphical user interface.
- JUnit 5
  - Used for unit, integration, and end-to-end testing.
- JaCoCo
  - Used for generating code coverage reports.
- Gradle
  - Used for dependency management and build automation.
- Ikonli / FontAwesomeFX
  - Used for icons within the user interface.
- TestFX
  - For E2E testing.
- SQLCipher
  - Our SQLite database implementation of choice.

## Milestone Documents
### Iteration 0
- [Team Working Agreement](/docs/TWA.md)
- [Iteration 0 Retrospective](/docs/retrospectives/Iteration0Retro.md)
### Iteration 1
- [Architecture Diagram](/docs/ArchitectureDiagramI1.png)
- [Iteration 1 Retrospective](/docs/retrospectives/Iteration1Retro.md)
### Iteration 2
- [Iteration 2 Architecture Diagram](/docs/ArchitectureDiagramI2.png)
- [Iteration 2 Retrospective](/docs/retrospectives/Iteration2Retro.md)
### Iteration 3
- [Iteration 3 Architecture Diagram](/docs/ArchitectureDiagramI3.png)
- [Iteration 3 Retrospective](/docs/retrospectives/Iteration3Retro.md)

## AI Usage Statement
- Debugging assistance
- Code refactoring suggestions
- Explanations of frameworks and libraries
- UI design guidance for JavaFX and CSS styling
- Generating example snippets and documentation formatting
