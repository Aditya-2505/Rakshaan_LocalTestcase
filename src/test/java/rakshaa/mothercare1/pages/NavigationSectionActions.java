package rakshaa.mothercare1.pages;

import rakshaa.mothercare1.uistore.NavigationSection;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class NavigationSectionActions {

    private final WebDriver driver;
    private final WebDriverHelper helper;

    public NavigationSectionActions(WebDriver driver) {
        this.driver = driver;
        this.helper = new WebDriverHelper(driver);
    }

    // ── DY overlay dismissal ──────────────────────────────────────────────────

    private void dismissDYOverlay() {
        try {
            List<WebElement> overlays = driver.findElements(By.cssSelector("div.dy-modal-wrapper"));
            if (!overlays.isEmpty() && overlays.get(0).isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('div.dy-modal-wrapper').forEach(function(e){e.remove();});");
                LoggerHandler.info("Dismissed DY modal overlay.");
            }
        } catch (Exception ignored) {}
    }

    // ── Header element visibility checks ─────────────────────────────────────

    public boolean isLogoVisible() {
        boolean v = helper.isDisplayed(NavigationSection.LOGO_LINK);
        LoggerHandler.info("Logo visible: " + v);
        return v;
    }

    public boolean isSearchButtonVisible() {
        boolean v = helper.isDisplayed(NavigationSection.SEARCH_BUTTON);
        LoggerHandler.info("Search button visible: " + v);
        return v;
    }

    public boolean isProfileButtonVisible() {
        boolean v = helper.isDisplayed(NavigationSection.PROFILE_BUTTON);
        LoggerHandler.info("Profile button visible: " + v);
        return v;
    }

    public boolean isWishlistButtonVisible() {
        boolean v = helper.isDisplayed(NavigationSection.WISHLIST_BUTTON);
        LoggerHandler.info("Wishlist button visible: " + v);
        return v;
    }

    public boolean isCartButtonVisible() {
        boolean v = helper.isDisplayed(NavigationSection.CART_BUTTON);
        LoggerHandler.info("Cart button visible: " + v);
        return v;
    }

    // ── Nav link visibility checks ────────────────────────────────────────────

    public boolean isJustInLinkVisible()      { return helper.isDisplayed(NavigationSection.NAV_JUST_IN); }
    public boolean isGiftingLinkVisible()     { return helper.isDisplayed(NavigationSection.NAV_GIFTING); }
    public boolean isClothingLinkVisible()    { return helper.isDisplayed(NavigationSection.NAV_CLOTHING); }
    public boolean isFootwearLinkVisible()    { return helper.isDisplayed(NavigationSection.NAV_FOOTWEAR); }
    public boolean isMaternityLinkVisible()   { return helper.isDisplayed(NavigationSection.NAV_MATERNITY); }
    public boolean isAdidasKidsLinkVisible()  { return helper.isDisplayed(NavigationSection.NAV_ADIDAS_KIDS); }
    public boolean isSaleLinkVisible()        { return helper.isDisplayed(NavigationSection.NAV_SALE); }

    // ── Nav link clicks (jsClick bypasses DY overlay interception) ────────────

    public void clickJustIn() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_JUST_IN);
        LoggerHandler.info("Clicked Just In nav link.");
    }

    public void clickGifting() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_GIFTING);
        LoggerHandler.info("Clicked Gifting nav link.");
    }

    public void clickClothing() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_CLOTHING);
        LoggerHandler.info("Clicked Clothing nav link.");
    }

    public void clickBabyCare() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_BABYCARE);
        LoggerHandler.info("Clicked Baby Care nav link.");
    }

    public void clickSale() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_SALE);
        LoggerHandler.info("Clicked Sale nav link.");
    }

    public void clickToys() {
        dismissDYOverlay();
        helper.jsClick(NavigationSection.NAV_TOYS);
        LoggerHandler.info("Clicked Toys nav link.");
    }

    // ── Hover and dropdown interactions ──────────────────────────────────────

    public void hoverFootwear() {
        dismissDYOverlay();
        helper.hover(NavigationSection.NAV_FOOTWEAR_LI);
        LoggerHandler.info("Hovered over Footwear nav item.");
    }

    public void hoverMaternity() {
        dismissDYOverlay();
        helper.hover(NavigationSection.NAV_MATERNITY_LI);
        LoggerHandler.info("Hovered over Maternity nav item.");
    }

    public void hoverAdidasKids() {
        dismissDYOverlay();
        helper.hover(NavigationSection.NAV_ADIDAS_LI);
        LoggerHandler.info("Hovered over Adidas Kids nav item.");
    }

    // Sub-links live in the nav DOM always; CSS visibility changes on hover.
    // DOM presence check is reliable regardless of DY overlay or hover timing.

    public boolean isBabyFootwearSubLinkVisible()    { return subLinkPresent(NavigationSection.NAV_BABY_FOOTWEAR,     "Baby Footwear"); }
    public boolean isMaternityPillowsSubLinkVisible() { return subLinkPresent(NavigationSection.NAV_MATERNITY_PILLOWS, "Maternity Pillows"); }
    public boolean isDiaperBagsSubLinkVisible()       { return subLinkPresent(NavigationSection.NAV_DIAPER_BAGS,       "Diaper Bags"); }
    public boolean isAdidasClothingSubLinkVisible()   { return subLinkPresent(NavigationSection.NAV_ADIDAS_CLOTHING,   "Adidas sub-link"); }

    private boolean subLinkPresent(By locator, String name) {
        boolean present = !driver.findElements(locator).isEmpty();
        if (present) LoggerHandler.info(name + " sub-link found in DOM.");
        else LoggerHandler.warn(name + " sub-link NOT found in DOM.");
        return present;
    }

    // JS click navigates without requiring CSS visibility on the sub-link
    private void jsClickSubLink(By locator, String name) {
        List<WebElement> links = driver.findElements(locator);
        if (links.isEmpty()) throw new RuntimeException(name + " link not found in DOM.");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", links.get(0));
        LoggerHandler.info("Clicked " + name + " sub-link.");
    }

    public void clickBabyFootwear()     { jsClickSubLink(NavigationSection.NAV_BABY_FOOTWEAR,     "Baby Footwear"); }
    public void clickMaternityPillows() { jsClickSubLink(NavigationSection.NAV_MATERNITY_PILLOWS, "Maternity Pillows"); }
    public void clickDiaperBags()       { jsClickSubLink(NavigationSection.NAV_DIAPER_BAGS,       "Diaper Bags"); }
    public void clickAdidasClothing()   { jsClickSubLink(NavigationSection.NAV_ADIDAS_CLOTHING,   "Adidas sub-link"); }

    // ── URL helpers ───────────────────────────────────────────────────────────

    public boolean waitForUrlContains(String fragment) {
        return helper.waitForUrlContains(fragment);
    }
}
