package rakshaa.mothercare1.uistore;

import org.openqa.selenium.By;

public class PDPSection {

    private PDPSection() {}

    // ── Product info ──────────────────────────────────────────────────────────

    public static final By PRODUCT_NAME        = By.cssSelector("div.product--description-pdp h1");
    public static final By PRODUCT_PRICE       = By.cssSelector("div.product--description-pdp h4");
    public static final By PRODUCT_DETAIL_LIS  = By.cssSelector("div.product--description-pdp ul li");

    // ── Quantity selector ─────────────────────────────────────────────────────

    public static final By QUANTITY_DISPLAY    = By.cssSelector("div.product--description-pdp button + span:has(+ button)");
    public static final By DECREASE_BUTTON     = By.cssSelector("div.product--description-pdp button:has(+ span + button)");
    public static final By INCREASE_BUTTON     = By.cssSelector("div.product--description-pdp button + span + button");

    // ── Actions ───────────────────────────────────────────────────────────────

    public static final By ADD_TO_CART_BUTTON     = By.cssSelector("div.product--description-pdp button[class*='primary']");
    public static final By ADD_TO_WISHLIST_BUTTON = By.cssSelector("div.product--description-pdp button[aria-pressed]");

    // ── Delivery / Pincode ────────────────────────────────────────────────────

    public static final By PINCODE_INPUT = By.cssSelector("input[inputmode='numeric'][maxlength='6']");
}
