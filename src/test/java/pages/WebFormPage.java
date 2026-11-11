package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class WebFormPage {

    private final SelenideElement pageTitle = $("h1");
    private final SelenideElement textInput = $(byName("my-text"));
    private final SelenideElement checkbox = $(byName("my-check"));
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement successMessage = $("#message");

    public WebFormPage verifyPageOpened() {
        pageTitle
                .shouldBe(visible)
                .shouldHave(text("Web form"));
        return this;
    }

    public WebFormPage fillForm(String text) {
        textInput.setValue(text);
        checkbox.setSelected(true);
        return this;
    }

    public WebFormPage verifyForm(String expectedText) {
        textInput.shouldHave(value(expectedText));
        checkbox.shouldBe(checked);
        return this;
    }

    public WebFormPage submit() {
        submitButton.click();
        return this;
    }

    public WebFormPage verifySuccessMessage(String expectedText) {
        successMessage
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }
}