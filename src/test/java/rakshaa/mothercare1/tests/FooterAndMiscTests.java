package rakshaa.mothercare1.tests;

import rakshaa.mothercare1.pages.FooterSectionActions;
import rakshaa.mothercare1.pages.NavigationSectionActions;
import rakshaa.mothercare1.pages.PDPSectionActions;
import rakshaa.mothercare1.pages.PLPSectionActions;
import rakshaa.mothercare1.utils.Base;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.Reporter;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Footer and miscellaneous test cases: TS_0076, TS_0078, TS_0079.
 */
@Epic("Mothercare Footer and Product Detail")
@Feature("Footer Links and PDP Interactions")
public class FooterAndMiscTests extends Base {

    private NavigationSectionActions navActions;
    private PLPSectionActions plpActions;
    private PDPSectionActions pdpActions;
    private FooterSectionActions footerActions;

    @BeforeMethod
    public void initPages() {
        navActions = new NavigationSectionActions(driver);
        plpActions = new PLPSectionActions(driver);
        pdpActions = new PDPSectionActions(driver);
        footerActions = new FooterSectionActions(driver);
        LoggerHandler.info("FooterAndMiscTests setup complete.");
    }

    // ── TS_0076 ──────────────────────────────────────────────────────────────

    @Test(priority = 1, description = "Verify Footer Links Are Present on Homepage")
    @Story("TS_0076")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0076_verifyFooterLinksVisibility() {
        LoggerHandler.testStart("TS_0076", "Verify Footer Links Are Present on Homepage");
        Reporter.startTest("TS_0076", "Verify Footer Links Are Present on Homepage");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Scroll to footer.");
        footerActions.scrollToFooter();
        Reporter.pass("Scrolled to footer section.");

        Reporter.info("Step 3: Verify footer element is visible.");
        Assert.assertTrue(footerActions.isFooterVisible(), "Footer should be visible after scrolling.");
        Reporter.pass("Footer is visible.");

        Reporter.info("Step 4: Verify Privacy Policy link is present in footer DOM.");
        Assert.assertTrue(footerActions.isPrivacyPolicyLinkPresent(),
                "Privacy Policy link should be present in footer.");
        Reporter.pass("Privacy Policy link is present.");

        Reporter.info("Step 5: Verify Cookies Policy link is present in footer DOM.");
        Assert.assertTrue(footerActions.isCookiesPolicyLinkPresent(),
                "Cookies Policy link should be present in footer.");
        Reporter.pass("Cookies Policy link is present.");

        Reporter.info("Step 6: Verify Customer Care link is present in footer DOM.");
        Assert.assertTrue(footerActions.isCustomerCareLinkPresent(),
                "Customer Care link should be present in footer.");
        Reporter.pass("Customer Care link is present.");

        Reporter.info("Step 7: Verify Terms & Conditions link is present in footer DOM.");
        Assert.assertTrue(footerActions.isTermsLinkPresent(),
                "Terms and Conditions link should be present in footer.");
        Reporter.pass("Terms and Conditions link is present.");

        Reporter.info("Step 8: Click Privacy Policy link and verify navigation.");
        footerActions.clickPrivacyPolicy();
        Assert.assertTrue(navActions.waitForUrlContains("privacy-policy"),
                "URL should contain 'privacy-policy' after clicking the link.");
        Reporter.pass("Privacy Policy page opened successfully.");
    }

    // ── TS_0078 ──────────────────────────────────────────────────────────────

    @Test(priority = 2, description = "Verify Product Detail Page Quantity and Add to Cart via Diaper Bags")
    @Story("TS_0078")
    @Severity(SeverityLevel.CRITICAL)
    public void TS_0078_verifyProductDetailQuantityAndAddToCart() {
        LoggerHandler.testStart("TS_0078", "Verify Product Detail Page Quantity and Add to Cart via Diaper Bags");
        Reporter.startTest("TS_0078", "Verify Product Detail Page Quantity and Add to Cart via Diaper Bags");

        Reporter.info("Step 1: Mothercare homepage is open.");

        Reporter.info("Step 2: Hover over Maternity nav item.");
        navActions.hoverMaternity();
        Reporter.pass("Hovered over Maternity nav item.");

        Reporter.info("Step 3: Verify Diaper Bags sub-link is present in nav DOM.");
        Assert.assertTrue(navActions.isDiaperBagsSubLinkVisible(),
                "Diaper Bags sub-link should be present in nav.");
        Reporter.pass("Diaper Bags sub-link is present.");

        Reporter.info("Step 4: Click Diaper Bags sub-link.");
        navActions.clickDiaperBags();

        Reporter.info("Step 5: Verify URL updated to diaper-bags collection.");
        Assert.assertTrue(navActions.waitForUrlContains("diaper-bags"),
                "URL should contain 'diaper-bags'.");
        Reporter.pass("Navigated to Diaper Bags PLP.");

        Reporter.info("Step 6: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be displayed on Diaper Bags PLP.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());

        Reporter.info("Step 7: Click first product to open PDP.");
        plpActions.clickFirstProduct();

        Reporter.info("Step 8: Verify PDP is loaded.");
        Assert.assertTrue(navActions.waitForUrlContains("/product/"), "URL should contain '/product/'.");
        Assert.assertTrue(pdpActions.isProductNameVisible(), "Product name should be visible on PDP.");
        String productName = pdpActions.getProductName();
        Reporter.pass("PDP loaded. Product: " + productName);

        Reporter.info("Step 9: Retrieve and log item code.");
        String itemCode = pdpActions.getItemCode();
        Assert.assertFalse(itemCode.equals("Item code not found"), "Item code should be present on PDP.");
        Reporter.pass("Item code retrieved: " + itemCode);

        Reporter.info("Step 10: Verify initial quantity is 1.");
        int initialQty = pdpActions.getCurrentQuantity();
        Assert.assertEquals(initialQty, 1, "Initial quantity should be 1.");
        Reporter.pass("Initial quantity is 1.");

        Reporter.info("Step 11: Click Increase (+) to increase quantity.");
        pdpActions.clickIncrease();
        Reporter.pass("Clicked Increase button.");

        Reporter.info("Step 12: Verify quantity is now 2.");
        int updatedQty = pdpActions.getCurrentQuantity();
        Assert.assertEquals(updatedQty, 2, "Quantity should be 2 after clicking Increase.");
        Reporter.pass("Quantity updated to 2 successfully.");

        Reporter.info("Step 13: Verify Add to Cart button is visible.");
        Assert.assertTrue(pdpActions.isAddToCartVisible(), "Add to Cart button should be visible on PDP.");
        Reporter.pass("Add to Cart button is visible.");
    }

    // ── TS_0079 ──────────────────────────────────────────────────────────────

    @Test(priority = 3, description = "Verify Sort by Popularity and Gender Filter on Just In PLP")
    @Story("TS_0079")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0079_verifySortAndGenderFilterOnJustInPLP() {
        LoggerHandler.testStart("TS_0079", "Verify Sort by Popularity and Gender Filter on Just In PLP");
        Reporter.startTest("TS_0079", "Verify Sort by Popularity and Gender Filter on Just In PLP");

        Reporter.info("Step 1: Navigate to Just In PLP.");
        navActions.clickJustIn();
        Assert.assertTrue(navActions.waitForUrlContains("new-arrival"), "Should be on Just In PLP.");
        Reporter.pass("Navigated to Just In PLP.");

        Reporter.info("Step 2: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be visible.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());

        Reporter.info("Step 3: Click Sort By button.");
        plpActions.clickSortBy();
        Reporter.pass("Clicked Sort By button.");

        Reporter.info("Step 4: Verify sort dialog is visible.");
        Assert.assertTrue(plpActions.isSortDialogVisible(), "Sort dialog should be visible.");
        Reporter.pass("Sort dialog is open.");

        Reporter.info("Step 5: Select 'popularity' sort option.");
        plpActions.selectSortOption("popularity");
        Reporter.pass("Selected 'popularity' sort option.");

        Reporter.info("Step 6: Verify products still displayed after sorting.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should remain visible after sort.");
        Reporter.pass("Products displayed after sorting by popularity.");

        Reporter.info("Step 7: Click Filter button.");
        plpActions.clickFilter();
        Reporter.pass("Clicked Filter button.");

        Reporter.info("Step 8: Verify filter panel is visible.");
        Assert.assertTrue(plpActions.isFilterPanelVisible(), "Filter panel should be open.");
        Reporter.pass("Filter panel opened.");

        Reporter.info("Step 9: Expand Gender filter section.");
        plpActions.expandFilterSection("Gender");
        Reporter.pass("Expanded Gender filter section.");

        Reporter.info("Step 10: Select 'Boys' gender filter option.");
        plpActions.selectFilterOption("Boys");
        Reporter.pass("Selected 'Boys' gender filter.");

        Reporter.info("Step 11: Click Apply to apply the filter.");
        plpActions.clickApplyFilter();
        Reporter.pass("Applied gender filter.");

        Reporter.info("Step 12: Verify at least one product is displayed after filter.");
        Assert.assertTrue(plpActions.areProductsDisplayed(),
                "At least one product should be displayed after Boys gender filter.");
        Reporter.pass("Products displayed after Boys gender filter. Count: " + plpActions.getProductCount());
    }
}
