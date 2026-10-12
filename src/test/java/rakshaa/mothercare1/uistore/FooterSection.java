package rakshaa.mothercare1.uistore;

import org.openqa.selenium.By;

public class FooterSection {

    private FooterSection() {}

    public static final By FOOTER                = By.cssSelector("footer");
    public static final By PRIVACY_POLICY_LINK   = By.cssSelector("footer a[href='/page/privacy-policy']");
    public static final By COOKIES_POLICY_LINK   = By.cssSelector("footer a[href='/page/cookies-policy']");
    public static final By CUSTOMER_CARE_LINK    = By.cssSelector("footer a[href='/contact-us']");
    public static final By TERMS_LINK            = By.cssSelector("footer a[href='/page/terms-and-conditions']");
    public static final By ORDER_TRACKING_LINK   = By.cssSelector("footer a[href='/order-tracking']");
    public static final By FOOTER_SECTION_BTNS   = By.cssSelector("footer button");
}
