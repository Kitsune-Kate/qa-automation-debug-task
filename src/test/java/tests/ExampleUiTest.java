package tests;

import fixtures.WebFixture;
import org.testng.annotations.Test;
import pages.WebFormPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ExampleUiTest extends WebFixture {
    @Test
    public void submitWebForm() {
        String testText = "QA Automation";
        String successMsg = "Received!";

        WebFormPage page = new WebFormPage();
        open("https://www.selenium.dev/selenium/web/web-form.html");
        page.verifyPageOpened();
        page.fillForm(testText);
        page.verifyForm(testText);
        page.submit();
        page.verifySuccessMessage(successMsg);
    }
}
