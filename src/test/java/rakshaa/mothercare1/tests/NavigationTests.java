package rakshaa.mothercare1.tests;

import rakshaa.mothercare1.pages.NavigationSectionActions;
import rakshaa.mothercare1.pages.PLPSectionActions;
import rakshaa.mothercare1.pages.PDPSectionActions;
import rakshaa.mothercare1.utils.Base;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.Reporter;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Navigation test cases: TS_0061 – TS_0069.
 */
@Epic("Mothercare Navigation")
@Feature("Header and Navigation Menu")
public class NavigationTests extends Base {

    private NavigationSectionActions navActions;
    private PLPSectionActions plpActions;
    private PDPSectionActions pdpActions;

    @BeforeMethod
    public void initPages() {
        navActions = new NavigationSectionActions(driver);
        plpActions = new PLPSectionActions(driver);
        pdpActions = new PDPSectionActions(driver);
        LoggerHandler.info("NavigationTests setup complete.");
    }

    // ── TS_0061 ──────────────────────────────────────────────────────────────

    @Test(priority = 1, description = "Verify Header Elements Are Visible on Homepage")
    @Story("TS_0061")
    @Severity(SeverityLevel.CRITICAL)
    public void TS_0061_verifyHeaderElementsVisibility() {
        LoggerHandler.testStart("TS_0061", "Verify Header Elements Are Visible on Homepage");
        Reporter.startTest("TS_0061", "Verify Header Elements Are Visible on Homepage");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Verify logo is visible in header.");
        Assert.assertTrue(navActions.isLogoVisible(), "Logo should be visible in header.");
        Reporter.pass("Logo is visible.");

        Reporter.info("Step 3: Verify Search button is visible.");
        Assert.assertTrue(navActions.isSearchButtonVisible(), "Search button should be visible in header.");
        Reporter.pass("Search button is visible.");

        Reporter.info("Step 4: Verify Profile button is visible.");
        Assert.assertTrue(navActions.isProfileButtonVisible(), "Profile button should be visible in header.");
        Reporter.pass("Profile button is visible.");

        Reporter.info("Step 5: Verify Wishlist button is visible.");
        Assert.assertTrue(navActions.isWishlistButtonVisible(), "Wishlist button should be visible in header.");
        Reporter.pass("Wishlist button is visible.");

        Reporter.info("Step 6: Verify Cart button is visible.");
        Assert.assertTrue(navActions.isCartButtonVisible(), "Cart button should be visible in header.");
        Reporter.pass("Cart button is visible.");

        Reporter.info("Step 7: Verify primary navigation links are visible.");
        Assert.assertTrue(navActions.isJustInLinkVisible(), "Just In link should be visible in nav.");
        Assert.assertTrue(navActions.isClothingLinkVisible(), "Clothing link should be visible in nav.");
        Assert.assertTrue(navActions.isFootwearLinkVisible(), "Footwear link should be visible in nav.");
        Assert.assertTrue(navActions.isSaleLinkVisible(), "Sale link should be visible in nav.");
        Reporter.pass("All primary navigation links are visible.");
    }

    // ── TS_0062 ──────────────────────────────────────────────────────────────

    @Test(priority = 2, description = "Verify Just In Navigation Link and PLP Loading")
    @Story("TS_0062")
    @Severity(SeverityLevel.CRITICAL)
    public void TS_0062_verifyJustInNavigationAndPLP() {
        LoggerHandler.testStart("TS_0062", "Verify Just In Navigation Link and PLP Loading");
        Reporter.startTest("TS_0062", "Verify Just In Navigation Link and PLP Loading");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Click Just In nav link.");
        navActions.clickJustIn();

        Reporter.info("Step 3: Verify URL updated to new-arrival collection.");
        Assert.assertTrue(navActions.waitForUrlContains("new-arrival"),
                "URL should contain 'new-arrival' after clicking Just In.");
        Reporter.pass("URL updated correctly to new-arrival collection.");

        Reporter.info("Step 4: Verify products are displayed on Just In PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Just In PLP.");
        Reporter.pass("Products are displayed on Just In PLP. Count: " + plpActions.getProductCount());
    }

    // ── TS_0063 ──────────────────────────────────────────────────────────────

    @Test(priority = 3, description = "Verify Footwear Hover Dropdown Sub-menu and Navigation")
    @Story("TS_0063")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0063_verifyFootwearHoverDropdownNavigation() {
        LoggerHandler.testStart("TS_0063", "Verify Footwear Hover Dropdown Sub-menu and Navigation");
        Reporter.startTest("TS_0063", "Verify Footwear Hover Dropdown Sub-menu and Navigation");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Hover over Footwear nav menu item.");
        navActions.hoverFootwear();
        Reporter.pass("Mouse hover performed on Footwear nav item.");

        Reporter.info("Step 3: Verify Baby Footwear sub-link appears in dropdown.");
        Assert.assertTrue(navActions.isBabyFootwearSubLinkVisible(),
                "Baby Footwear sub-link should appear after hovering Footwear.");
        Reporter.pass("Baby Footwear sub-link is visible in dropdown.");

        Reporter.info("Step 4: Click Baby Footwear sub-link.");
        navActions.clickBabyFootwear();

        Reporter.info("Step 5: Verify URL updated to baby-footwear collection.");
        Assert.assertTrue(navActions.waitForUrlContains("baby-footwear"),
                "URL should contain 'baby-footwear'.");
        Reporter.pass("Navigated to Baby Footwear PLP.");

        Reporter.info("Step 6: Verify products are displayed on Baby Footwear PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Baby Footwear PLP.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());

        Reporter.info("Step 7: Click first product to open PDP.");
        plpActions.clickFirstProduct();

        Reporter.info("Step 8: Verify PDP is loaded.");
        Assert.assertTrue(navActions.waitForUrlContains("/product/"), "URL should contain '/product/'.");
        Assert.assertTrue(pdpActions.isProductNameVisible(), "Product name should be visible on PDP.");
        Reporter.pass("PDP loaded. Product: " + pdpActions.getProductName());
    }

    // ── TS_0064 ──────────────────────────────────────────────────────────────

    @Test(priority = 4, description = "Verify Gifting Navigation Link and PLP Loading")
    @Story("TS_0064")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0064_verifyGiftingNavigationAndPLP() {
        LoggerHandler.testStart("TS_0064", "Verify Gifting Navigation Link and PLP Loading");
        Reporter.startTest("TS_0064", "Verify Gifting Navigation Link and PLP Loading");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Click Gifting nav link.");
        navActions.clickGifting();

        Reporter.info("Step 3: Verify URL updated to gifting collection.");
        Assert.assertTrue(navActions.waitForUrlContains("gifting"),
                "URL should contain 'gifting' after clicking Gifting.");
        Reporter.pass("URL updated to gifting collection.");

        Reporter.info("Step 4: Verify products are displayed on Gifting PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Gifting PLP.");
        Reporter.pass("Products are displayed on Gifting PLP. Count: " + plpActions.getProductCount());
    }

    // ── TS_0065 ──────────────────────────────────────────────────────────────

    @Test(priority = 5, description = "Verify Maternity Hover Dropdown and Maternity Pillows Navigation")
    @Story("TS_0065")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0065_verifyMaternityDropdownAndMaternityPillows() {
        LoggerHandler.testStart("TS_0065", "Verify Maternity Hover Dropdown and Maternity Pillows Navigation");
        Reporter.startTest("TS_0065", "Verify Maternity Hover Dropdown and Maternity Pillows Navigation");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Hover over Maternity nav menu item.");
        navActions.hoverMaternity();
        Reporter.pass("Mouse hover performed on Maternity nav item.");

        Reporter.info("Step 3: Verify Maternity Pillows sub-link is visible.");
        Assert.assertTrue(navActions.isMaternityPillowsSubLinkVisible(),
                "Maternity Pillows sub-link should appear after hovering Maternity.");
        Reporter.pass("Maternity Pillows sub-link is visible in dropdown.");

        Reporter.info("Step 4: Click Maternity Pillows sub-link.");
        navActions.clickMaternityPillows();

        Reporter.info("Step 5: Verify URL updated to maternity-pillows collection.");
        Assert.assertTrue(navActions.waitForUrlContains("maternity-pillows"),
                "URL should contain 'maternity-pillows'.");
        Reporter.pass("Navigated to Maternity Pillows PLP.");

        Reporter.info("Step 6: Verify products are displayed on Maternity Pillows PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Maternity Pillows PLP.");
        Reporter.pass("Products displayed on Maternity Pillows PLP. Count: " + plpActions.getProductCount());
    }

    // ── TS_0066 ──────────────────────────────────────────────────────────────

    @Test(priority = 6, description = "Verify Adidas Kids Hover Dropdown and Clothing Navigation")
    @Story("TS_0066")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0066_verifyAdidasKidsDropdownNavigation() {
        LoggerHandler.testStart("TS_0066", "Verify Adidas Kids Hover Dropdown and Clothing Navigation");
        Reporter.startTest("TS_0066", "Verify Adidas Kids Hover Dropdown and Clothing Navigation");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Hover over Adidas Kids nav menu item.");
        navActions.hoverAdidasKids();
        Reporter.pass("Mouse hover performed on Adidas Kids nav item.");

        Reporter.info("Step 3: Verify Adidas Kids Clothing sub-link is visible.");
        Assert.assertTrue(navActions.isAdidasClothingSubLinkVisible(),
                "Adidas Kids Clothing sub-link should appear after hovering Adidas Kids.");
        Reporter.pass("Adidas Kids Clothing sub-link is visible in dropdown.");

        Reporter.info("Step 4: Click Adidas Kids Clothing sub-link.");
        navActions.clickAdidasClothing();

        Reporter.info("Step 5: Verify URL updated to adidas-kids collection.");
        Assert.assertTrue(navActions.waitForUrlContains("adidas-kids"),
                "URL should contain 'adidas-kids'.");
        Reporter.pass("Navigated to Adidas Kids Clothing PLP.");

        Reporter.info("Step 6: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());
    }

    // ── TS_0067 ──────────────────────────────────────────────────────────────

    @Test(priority = 7, description = "Verify Clothing Navigation Link and PLP Loading")
    @Story("TS_0067")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0067_verifyClothingNavigationAndPLP() {
        LoggerHandler.testStart("TS_0067", "Verify Clothing Navigation Link and PLP Loading");
        Reporter.startTest("TS_0067", "Verify Clothing Navigation Link and PLP Loading");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Click Clothing nav link.");
        navActions.clickClothing();

        Reporter.info("Step 3: Verify URL updated to clothing collection.");
        Assert.assertTrue(navActions.waitForUrlContains("clothing"),
                "URL should contain 'clothing'.");
        Reporter.pass("URL updated to clothing collection.");

        Reporter.info("Step 4: Verify products are displayed on Clothing PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Clothing PLP.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());
    }

    // ── TS_0068 ──────────────────────────────────────────────────────────────

    @Test(priority = 8, description = "Verify Baby Care Navigation Link and PLP Loading")
    @Story("TS_0068")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0068_verifyBabyCareNavigationAndPLP() {
        LoggerHandler.testStart("TS_0068", "Verify Baby Care Navigation Link and PLP Loading");
        Reporter.startTest("TS_0068", "Verify Baby Care Navigation Link and PLP Loading");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Click Baby Care nav link.");
        navActions.clickBabyCare();

        Reporter.info("Step 3: Verify URL updated to babycare collection.");
        Assert.assertTrue(navActions.waitForUrlContains("babycare"),
                "URL should contain 'babycare'.");
        Reporter.pass("URL updated to babycare collection.");

        Reporter.info("Step 4: Verify products are displayed on Baby Care PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Baby Care PLP.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());
    }

    // ── TS_0069 ──────────────────────────────────────────────────────────────

    @Test(priority = 9, description = "Verify Sale Navigation Link and PLP Loading")
    @Story("TS_0069")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0069_verifySaleNavigationAndPLP() {
        LoggerHandler.testStart("TS_0069", "Verify Sale Navigation Link and PLP Loading");
        Reporter.startTest("TS_0069", "Verify Sale Navigation Link and PLP Loading");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Click Sale nav link.");
        navActions.clickSale();

        Reporter.info("Step 3: Verify URL updated to sale collection.");
        Assert.assertTrue(navActions.waitForUrlContains("sale"),
                "URL should contain 'sale'.");
        Reporter.pass("URL updated to sale collection.");

        Reporter.info("Step 4: Verify products are displayed on Sale PLP.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Sale PLP.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());
    }
}
