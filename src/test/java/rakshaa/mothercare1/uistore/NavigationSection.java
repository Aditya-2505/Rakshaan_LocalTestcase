package rakshaa.mothercare1.uistore;

import org.openqa.selenium.By;

public class NavigationSection {

    private NavigationSection() {}

    // ── Header global elements ────────────────────────────────────────────────

    public static final By LOGO_LINK        = By.cssSelector("header a[href='/']");
    public static final By SEARCH_INPUT     = By.id("desktop-search-input");
    public static final By SEARCH_BUTTON    = By.cssSelector("header button[aria-label='search']");
    public static final By PROFILE_BUTTON   = By.cssSelector("header button[aria-label='Profile']");
    public static final By WISHLIST_BUTTON  = By.cssSelector("header button[aria-label='wishlist']");
    public static final By CART_BUTTON      = By.cssSelector("header button[aria-label*='item in cart']");

    // ── Primary nav links ─────────────────────────────────────────────────────

    public static final By NAV_HOME              = By.cssSelector("header nav a[aria-label='Home']");
    public static final By NAV_JUST_IN           = By.cssSelector("header nav a[href='/collection/new-arrival']");
    public static final By NAV_GIFTING           = By.cssSelector("header nav a[href='/collection/gifting-edit']");
    public static final By NAV_OCCASION_WEAR     = By.cssSelector("header nav a[href='/collection/occasion-wear']");
    public static final By NAV_CLOTHING          = By.cssSelector("header nav a[href='/collection/clothing']");
    public static final By NAV_BABYCARE          = By.cssSelector("header nav a[href='/collection/babycare']");
    public static final By NAV_BABY_GEAR         = By.cssSelector("header nav a[href='/collection/baby-gear']");
    public static final By NAV_FOOTWEAR          = By.cssSelector("header nav a[href='/collection/footwear']");
    public static final By NAV_FURNITURE_BEDDING = By.cssSelector("header nav a[href='/collection/furniture--bedding']");
    public static final By NAV_MATERNITY         = By.cssSelector("header nav a[href='/collection/moms--maternity']");
    public static final By NAV_TOYS              = By.cssSelector("header nav a[href='/collection/toys']");
    public static final By NAV_ADIDAS_KIDS       = By.cssSelector("header nav a[href='/collection/adidas-kids']");
    public static final By NAV_SALE              = By.cssSelector("header nav a[href='/collection/sale']");

    // ── Nav item parent LI elements (for hover trigger) ──────────────────────

    public static final By NAV_FOOTWEAR_LI   = By.cssSelector("header nav li:has(a[href='/collection/footwear'])");
    public static final By NAV_MATERNITY_LI  = By.cssSelector("header nav li:has(a[href='/collection/moms--maternity'])");
    public static final By NAV_ADIDAS_LI     = By.cssSelector("header nav li:has(a[href='/collection/adidas-kids'])");
    public static final By NAV_GIFTING_LI    = By.cssSelector("header nav li:has(a[href='/collection/gifting-edit'])");

    // ── Dropdown sub-links ────────────────────────────────────────────────────

    // Footwear sub-links
    public static final By NAV_BABY_FOOTWEAR   = By.cssSelector("header nav a[href='/collection/baby-footwear']");
    public static final By NAV_KIDS_FOOTWEAR   = By.cssSelector("header nav a[href='/collection/kids-footwear']");
    public static final By NAV_BABY_BOOTIES    = By.cssSelector("header nav a[href='/collection/baby-booties']");

    // Gifting sub-links
    public static final By NAV_BABY_SHOWER_GIFTS = By.cssSelector("header nav a[href='/collection/baby-shower-gifts']");

    // Maternity sub-links
    public static final By NAV_DIAPER_BAGS       = By.cssSelector("header nav a[href='/collection/diaper-bags']");
    public static final By NAV_MATERNITY_PILLOWS = By.cssSelector("header nav a[href='/collection/maternity-pillows']");

    // Adidas sub-links — use href*= contains to match any adidas-kids-* sub-collection
    public static final By NAV_ADIDAS_CLOTHING = By.cssSelector("header nav a[href*='adidas-kids-']");
    public static final By NAV_ADIDAS_SHOES    = By.cssSelector("header nav a[href='/collection/adidas-kids-shoes']");
}
