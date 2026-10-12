package rakshaa.mothercare1.pages;

import rakshaa.mothercare1.uistore.PDPSection;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PDPSectionActions {

    private final WebDriver driver;
    private final WebDriverHelper helper;

    public PDPSectionActions(WebDriver driver) {
        this.driver = driver;
        this.helper = new WebDriverHelper(driver);
    }

    // ── Product info ──────────────────────────────────────────────────────────

    public boolean isProductNameVisible() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15)).until(
                d -> !d.findElements(PDPSection.PRODUCT_NAME).isEmpty() &&
                     d.findElements(PDPSection.PRODUCT_NAME).get(0).isDisplayed()
            );
            LoggerHandler.info("Product name visible.");
            return true;
        } catch (Exception e) {
            LoggerHandler.error("Product name not visible: " + e.getMessage());
            return false;
        }
    }

    public String getProductName() {
        return helper.getText(PDPSection.PRODUCT_NAME);
    }

    public boolean isProductPriceVisible() {
        boolean v = helper.isDisplayed(PDPSection.PRODUCT_PRICE);
        LoggerHandler.info("Product price visible: " + v);
        return v;
    }

    public String getItemCode() {
        List<WebElement> lis = driver.findElements(PDPSection.PRODUCT_DETAIL_LIS);
        return lis.stream()
            .filter(li -> li.getText().trim().startsWith("Item code"))
            .findFirst()
            .map(li -> li.getText().trim())
            .orElse("Item code not found");
    }

    // ── Quantity ──────────────────────────────────────────────────────────────

    public int getCurrentQuantity() {
        String qty = helper.waitForVisible(PDPSection.QUANTITY_DISPLAY).getText().trim();
        LoggerHandler.info("Current quantity: " + qty);
        try {
            return Integer.parseInt(qty);
        } catch (NumberFormatException e) {
            LoggerHandler.error("Quantity is not a number: '" + qty + "'");
            return -1;
        }
    }

    public void clickIncrease() {
        helper.jsClick(PDPSection.INCREASE_BUTTON);
        LoggerHandler.info("Clicked Increase (+) button.");
    }

    public void clickDecrease() {
        helper.jsClick(PDPSection.DECREASE_BUTTON);
        LoggerHandler.info("Clicked Decrease (-) button.");
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    public boolean isAddToCartVisible() {
        boolean v = helper.isDisplayed(PDPSection.ADD_TO_CART_BUTTON);
        LoggerHandler.info("Add to Cart button visible: " + v);
        return v;
    }

    public boolean isAddToWishlistVisible() {
        boolean v = helper.isDisplayed(PDPSection.ADD_TO_WISHLIST_BUTTON);
        LoggerHandler.info("Add to Wishlist button visible: " + v);
        return v;
    }

    public void clickAddToCart() {
        helper.jsClick(PDPSection.ADD_TO_CART_BUTTON);
        LoggerHandler.info("Clicked Add to Cart button.");
    }
}
