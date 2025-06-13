package io.qameta.allure;

import org.junit.jupiter.api.*;


@Layer("web")
@Owner("vladi_allure_example")
@Feature("Pull Requests")
public class PullRequestsWebTest {

    private static final String OWNER = "vladi_allure_example";
    private static final String REPO = "allure_example";

    private static final String BRANCH = "new-feature";

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("AE-T6")
    @Microservice("Billing")
    @Story("Create new pull request")
    @Tags({@Tag("web"), @Tag("regress"), @Tag("smoke")})
    @JiraIssues({@JiraIssue("AE-1"), @JiraIssue("AE-2")})
    @DisplayName("Creating new issue for authorized user")
    public void shouldCreatePullRequest() {
        steps.openPullRequestsPage(OWNER, REPO);
        steps.createPullRequestFromBranch(BRANCH);
        steps.shouldSeePullRequestForBranch(BRANCH);
    }

    @Test
    @TM4J("AE-T7")
    @JiraIssue("AE-2")
    @Microservice("Repository")
    @Story("Close existing pull request")
    @Tags({@Tag("web"), @Tag("regress")})
    @DisplayName("Deleting existing issue for authorized user")
    public void shouldClosePullRequest() {
        steps.openPullRequestsPage(OWNER, REPO);
        steps.createPullRequestFromBranch(BRANCH);
        steps.closePullRequestForBranch(BRANCH);
        steps.shouldNotSeePullRequestForBranch(BRANCH);
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }

}