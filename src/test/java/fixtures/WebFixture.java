package fixtures;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterMethod;
import reporting.Report;

public abstract class WebFixture {

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        Report.attachSessionInfo(sessionId);
        Selenide.closeWebDriver();
    }
}