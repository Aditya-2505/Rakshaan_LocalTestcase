package rakshaa.mothercare1.pages;

import rakshaa.mothercare1.uistore.FooterSection;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FooterSectionActions {

    private final WebDriver driver;
    private final WebDriverHelper helper;

    public FooterSectionActions(WebDriver driver) {
        this.driver = driver;
        this.helper = new WebDriverHelper(driver);
    }

    public boolean isFooterVisible() {
        helper.scrollToElement(FooterSection.FOOTER);
        boolean v = helper.isDisplayed(FooterSection.FOOTER);
        LoggerHandler.info("Footer visible: " + v);
        return v;
    }

    public void scrollToFooter() {
        helper.scrollToElement(FooterSection.FOOTER);
        LoggerHandler.info("Scrolled to footer.");
    }

    // Footer links may be inside collapsed accordions.
    // Use DOM presence (findElements) rather than visibility.

    public boolean isPrivacyPolicyLinkPresent() {
        boolean present = linkPresent(FooterSection.PRIVACY_POLICY_LINK);
        LoggerHandler.info("Privacy Policy link present in DOM: " + present);
        return present;
    }

    public boolean isCookiesPolicyLinkPresent() {
        boolean present = linkPresent(FooterSection.COOKIES_POLICY_LINK);
        LoggerHandler.info("Cookies Policy link present in DOM: " + present);
        return present;
    }

    public boolean isCustomerCareLinkPresent() {
        boolean present = linkPresent(FooterSection.CUSTOMER_CARE_LINK);
        LoggerHandler.info("Customer Care link present in DOM: " + present);
        return present;
    }

    public boolean isTermsLinkPresent() {
        boolean present = linkPresent(FooterSection.TERMS_LINK);
        LoggerHandler.info("Terms & Conditions link present in DOM: " + present);
        return present;
    }

    private boolean linkPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void clickPrivacyPolicy() {
        // Use JS click to bypass any overlay
        List<WebElement> links = driver.findElements(FooterSection.PRIVACY_POLICY_LINK);
        if (links.isEmpty()) throw new RuntimeException("Privacy Policy link not found in DOM.");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", links.get(0));
        LoggerHandler.info("Clicked Privacy Policy link.");
    }

    public void clickCustomerCare() {
        List<WebElement> links = driver.findElements(FooterSection.CUSTOMER_CARE_LINK);
        if (links.isEmpty()) throw new RuntimeException("Customer Care link not found in DOM.");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", links.get(0));
        LoggerHandler.info("Clicked Customer Care link.");
    }
}
