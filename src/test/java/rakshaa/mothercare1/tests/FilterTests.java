package rakshaa.mothercare1.tests;

import rakshaa.mothercare1.pages.NavigationSectionActions;
import rakshaa.mothercare1.pages.PLPSectionActions;
import rakshaa.mothercare1.utils.Base;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.Reporter;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Filter and Sort test cases: TS_0070 – TS_0073.
 */
@Epic("Mothercare Product Listing")
@Feature("Sort and Filter")
public class FilterTests extends Base {

    private NavigationSectionActions navActions;
    private PLPSectionActions plpActions;

    @BeforeMethod
    public void initPages() {
        navActions = new NavigationSectionActions(driver);
        plpActions = new PLPSectionActions(driver);
        LoggerHandler.info("FilterTests setup complete.");
    }

    // ── TS_0070 ──────────────────────────────────────────────────────────────

    @Test(priority = 1, description = "Verify Sort By New Arrivals on Clothing PLP")
    @Story("TS_0070")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0070_verifySortByNewArrivalsOnClothingPLP() {
        LoggerHandler.testStart("TS_0070", "Verify Sort By New Arrivals on Clothing PLP");
        Reporter.startTest("TS_0070", "Verify Sort By New Arrivals on Clothing PLP");

        Reporter.info("Step 1: Navigate to Clothing PLP.");
        navActions.clickClothing();
        Assert.assertTrue(navActions.waitForUrlContains("clothing"), "Should be on clothing PLP.");
        Reporter.pass("Navigated to Clothing PLP.");

        Reporter.info("Step 2: Verify products are displayed before sorting.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be visible before sort.");
        int beforeCount = plpActions.getProductCount();
        Reporter.pass("Products visible before sort. Count: " + beforeCount);

        Reporter.info("Step 3: Click Sort By button.");
        plpActions.clickSortBy();
        Reporter.pass("Clicked Sort By button.");

        Reporter.info("Step 4: Verify sort dialog is visible.");
        Assert.assertTrue(plpActions.isSortDialogVisible(), "Sort dialog should be visible.");
        Reporter.pass("Sort dialog is open.");

        Reporter.info("Step 5: Select 'new arrivals' sort option.");
        plpActions.selectSortOption("new arrivals");
        Reporter.pass("Selected 'new arrivals' sort option.");

        Reporter.info("Step 6: Verify products are still displayed after sorting.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should remain visible after sorting.");
        Reporter.pass("Products are displayed after sorting by new arrivals. Count: " + plpActions.getProductCount());
    }

    // ── TS_0071 ──────────────────────────────────────────────────────────────

    @Test(priority = 2, description = "Verify Sort By Price Low to High on Clothing PLP")
    @Story("TS_0071")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0071_verifySortByPriceLowHighOnClothingPLP() {
        LoggerHandler.testStart("TS_0071", "Verify Sort By Price Low to High on Clothing PLP");
        Reporter.startTest("TS_0071", "Verify Sort By Price Low to High on Clothing PLP");

        Reporter.info("Step 1: Navigate to Clothing PLP.");
        navActions.clickClothing();
        Assert.assertTrue(navActions.waitForUrlContains("clothing"), "Should be on clothing PLP.");
        Reporter.pass("Navigated to Clothing PLP.");

        Reporter.info("Step 2: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be visible.");
        Reporter.pass("Products displayed. Count: " + plpActions.getProductCount());

        Reporter.info("Step 3: Click Sort By button.");
        plpActions.clickSortBy();
        Reporter.pass("Clicked Sort By button.");

        Reporter.info("Step 4: Verify sort dialog is visible.");
        Assert.assertTrue(plpActions.isSortDialogVisible(), "Sort dialog should be visible.");
        Reporter.pass("Sort dialog is open.");

        Reporter.info("Step 5: Select 'price (low > high)' sort option.");
        plpActions.selectSortOption("price (low > high)");
        Reporter.pass("Selected 'price (low > high)' sort option.");

        Reporter.info("Step 6: Verify products are still displayed after sorting.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should remain visible after sorting.");
        Reporter.pass("Products are displayed after sorting by price low to high. Count: " + plpActions.getProductCount());
    }

    // ── TS_0072 ──────────────────────────────────────────────────────────────

    @Test(priority = 3, description = "Verify Filter Panel Opens and Closes on Clothing PLP")
    @Story("TS_0072")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0072_verifyFilterPanelOpenCloseOnClothingPLP() {
        LoggerHandler.testStart("TS_0072", "Verify Filter Panel Opens and Closes on Clothing PLP");
        Reporter.startTest("TS_0072", "Verify Filter Panel Opens and Closes on Clothing PLP");

        Reporter.info("Step 1: Navigate to Clothing PLP.");
        navActions.clickClothing();
        Assert.assertTrue(navActions.waitForUrlContains("clothing"), "Should be on clothing PLP.");
        Reporter.pass("Navigated to Clothing PLP.");

        Reporter.info("Step 2: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be visible before filtering.");
        Reporter.pass("Products displayed before filter. Count: " + plpActions.getProductCount());

        Reporter.info("Step 3: Click Filter button.");
        plpActions.clickFilter();
        Reporter.pass("Clicked Filter button.");

        Reporter.info("Step 4: Verify filter panel is visible.");
        Assert.assertTrue(plpActions.isFilterPanelVisible(), "Filter panel should be open.");
        Reporter.pass("Filter panel opened successfully.");

        Reporter.info("Step 5: Close the filter panel.");
        plpActions.closeFilterPanel();
        Reporter.pass("Filter panel closed.");

        Reporter.info("Step 6: Verify products are still displayed after closing filter.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should still be visible after closing filter.");
        Reporter.pass("Products remain displayed after closing filter panel.");
    }

    // ── TS_0073 ──────────────────────────────────────────────────────────────

    @Test(priority = 4, description = "Verify Price Range Filter on Toys PLP")
    @Story("TS_0073")
    @Severity(SeverityLevel.NORMAL)
    public void TS_0073_verifyPriceRangeFilterOnToysPLP() {
        LoggerHandler.testStart("TS_0073", "Verify Price Range Filter on Toys PLP");
        Reporter.startTest("TS_0073", "Verify Price Range Filter on Toys PLP");

        Reporter.info("Step 1: Navigate to Toys PLP.");
        navActions.clickToys();
        Assert.assertTrue(navActions.waitForUrlContains("toys"), "Should be on toys PLP.");
        Reporter.pass("Navigated to Toys PLP.");

        Reporter.info("Step 2: Verify products are displayed.");
        Assert.assertTrue(plpActions.areProductsDisplayed(), "Products should be visible before filtering.");
        Reporter.pass("Products displayed before filter. Count: " + plpActions.getProductCount());

        Reporter.info("Step 3: Click Filter button.");
        plpActions.clickFilter();
        Reporter.pass("Clicked Filter button.");

        Reporter.info("Step 4: Verify filter panel is visible.");
        Assert.assertTrue(plpActions.isFilterPanelVisible(), "Filter panel should be open.");
        Reporter.pass("Filter panel opened.");

        Reporter.info("Step 5: Expand Price filter section.");
        plpActions.expandFilterSection("Price");
        Reporter.pass("Expanded Price filter section.");

        Reporter.info("Step 6: Select price range '2000 - 3000' (closest to test value 2500).");
        plpActions.selectFilterOption("2000 - 3000");
        Reporter.pass("Selected price range '2000 - 3000'.");

        Reporter.info("Step 7: Click Apply to apply the filter.");
        plpActions.clickApplyFilter();
        Reporter.pass("Clicked Apply filter.");

        Reporter.info("Step 8: Verify products are displayed after applying price filter.");
        Assert.assertTrue(plpActions.areProductsDisplayed(),
                "Products should be displayed after applying price range filter.");
        Reporter.pass("Products are displayed after applying price filter. Count: " + plpActions.getProductCount());
    }
}
