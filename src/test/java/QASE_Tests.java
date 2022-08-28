import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class QASE_Tests {
    protected final static String EMAIL = "amigo-10291@mail.ru";
    protected final static String PASSWORD = "8467189178";
    protected final static String TEST_CASE_NAME_LOCATOR = "//div[@data-suite-body-id='0'and@data-handler-id]/div[text()]";
    protected final static String DEFECT_NAME_LOCATOR = "//a[@class='defect-title']";
    String EXPECTED_ERROR_MESSAGE_TEXT="These credentials do not match our records.";
    String TEST_CASE_TITLE = "Login button";
    String DEFECT_NAME = "Login button isn't clickable";


    @BeforeMethod
    public void navigate() {
        open("https://app.qase.io");
    }

    @AfterMethod
    public void close() {
        getWebDriver().quit();
    }

    @Test
    public void positiveLoginTest() {
        $("input[type=email]").sendKeys(EMAIL);
        $("input[type=password]").sendKeys(PASSWORD);
        $("#btnLogin").click();
        Assert.assertTrue($(By.xpath("//h1[text()='Projects']")).isDisplayed(),
                "Home page is not displayed");
    }
    @Test
    public void negativeLoginTest() {
        $("input[type=email]").sendKeys(EMAIL);
        $("input[type=password]").sendKeys("555");
        $("#btnLogin").click();
        Assert.assertEquals($(".form-control-feedback").getText(),EXPECTED_ERROR_MESSAGE_TEXT);
    }

    @Test
    public void createTestCaseTest() {
        $("input[type=email]").sendKeys(EMAIL);
        $("input[type=password]").sendKeys(PASSWORD);
        $("#btnLogin").click();
        $("a[href=\"/project/QA\"]").click();
        $("#create-case-button").click();
        $("#title").sendKeys(TEST_CASE_TITLE);
        $("#save-case").click();
        $("span.OL6rtE").shouldHave(Condition.exactText("Test case was created successfully!"));
        Assert.assertEquals($(By.xpath(String.format(TEST_CASE_NAME_LOCATOR))).getText(), TEST_CASE_TITLE,
                "Test case names don't match");
    }

    @Test
    public void createDefectTest() {
        $("input[type=email]").sendKeys(EMAIL);
        $("input[type=password]").sendKeys(PASSWORD);
        $("#btnLogin").click();
        $("a[href=\"/project/QA\"]").click();
        $(By.xpath("//span[text()='Defects']")).click();
        $("a.btn.btn-primary").click();
        $("#title").sendKeys(DEFECT_NAME);
        $("div.ProseMirror.toastui-editor-contents").sendKeys("Doesn't work");
        $("button.btn.btn-primary.me-3.save-button").click();
        $("span.OL6rtE").shouldHave(Condition.exactText("Defect was created successfully!"));
        Assert.assertEquals($(By.xpath(String.format(DEFECT_NAME_LOCATOR))).getText(), DEFECT_NAME,
                "Defects don't match");

    }
}
