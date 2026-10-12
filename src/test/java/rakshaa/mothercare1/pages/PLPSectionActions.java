package rakshaa.mothercare1.pages;

import rakshaa.mothercare1.uistore.PLPSection;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PLPSectionActions {

    private final WebDriver driver;
    private final WebDriverHelper helper;

    public PLPSectionActions(WebDriver driver) {
        this.driver = driver;
        this.helper = new WebDriverHelper(driver);
    }

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

    // ── Page state ────────────────────────────────────────────────────────────

    public boolean isPageHeadingVisible() {
        boolean v = helper.isDisplayed(PLPSection.PAGE_HEADING);
        LoggerHandler.info("PLP heading visible: " + v);
        return v;
    }

    public String getPageHeadingText() {
        return helper.getText(PLPSection.PAGE_HEADING);
    }

    public boolean areProductsDisplayed() {
        dismissDYOverlay();
        try {
            // Wait for at least ONE visible product (not all) to avoid false negatives during load
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
                List<WebElement> els = d.findElements(PLPSection.PRODUCT_LINKS);
                return els.stream().anyMatch(WebElement::isDisplayed);
            });
            int count = driver.findElements(PLPSection.PRODUCT_LINKS).size();
            LoggerHandler.info("Products displayed. Count: " + count);
            return true;
        } catch (Exception e) {
            LoggerHandler.error("Products not found: " + e.getMessage());
            return false;
        }
    }

    public int getProductCount() {
        return driver.findElements(PLPSection.PRODUCT_LINKS).size();
    }

    // ── Click first product ───────────────────────────────────────────────────

    public void clickFirstProduct() {
        List<WebElement> products = helper.waitForAllVisible(PLPSection.PRODUCT_LINKS);
        if (products.isEmpty()) throw new RuntimeException("No products found on PLP");
        WebElement first = products.get(0);
        helper.scrollToElement(first);
        helper.jsClick(first);
        LoggerHandler.info("Clicked first product on PLP.");
    }

    // ── Sort ──────────────────────────────────────────────────────────────────

    public void clickSortBy() {
        dismissDYOverlay();
        // Sort/filter buttons are mobile-only (display:none on desktop) — resize to mobile viewport first
        driver.manage().window().setSize(new Dimension(390, 844));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 350);");
        Boolean clicked = (Boolean) ((JavascriptExecutor) driver).executeScript(
            "var els = document.querySelectorAll('button:has(i.fa-sort-icon)');" +
            "for (var el of Array.from(els)) {" +
            "  var r = el.getBoundingClientRect();" +
            "  if (r.width > 0 && r.height > 0) { el.click(); return true; }" +
            "}" +
            "return false;");
        if (!Boolean.TRUE.equals(clicked)) {
            throw new RuntimeException("Sort By button (fa-sort-icon) not found with non-zero dimensions");
        }
        LoggerHandler.info("Clicked Sort By button.");
    }

    public boolean isSortDialogVisible() {
        boolean v = helper.isDisplayed(PLPSection.SORT_DIALOG);
        LoggerHandler.info("Sort dialog visible: " + v);
        return v;
    }

    public void selectSortOption(String optionText) {
        List<WebElement> btns = driver.findElements(PLPSection.SORT_OPTION_BUTTONS);
        btns.stream()
            .filter(b -> b.getText().trim().equalsIgnoreCase(optionText))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Sort option not found: " + optionText))
            .click();
        LoggerHandler.info("Selected sort option: " + optionText);
    }

    public void closeSortDialog() {
        if (helper.isDisplayed(PLPSection.SORT_CLOSE_BUTTON)) {
            helper.click(PLPSection.SORT_CLOSE_BUTTON);
            LoggerHandler.info("Closed sort dialog.");
        }
    }

    // ── Filter ────────────────────────────────────────────────────────────────

    public void clickFilter() {
        dismissDYOverlay();
        // Sort/filter buttons are mobile-only (display:none on desktop) — resize to mobile viewport first
        driver.manage().window().setSize(new Dimension(390, 844));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 350);");
        Boolean clicked = (Boolean) ((JavascriptExecutor) driver).executeScript(
            "var els = document.querySelectorAll('button:has(i.fa-filter-icon)');" +
            "for (var el of Array.from(els)) {" +
            "  var r = el.getBoundingClientRect();" +
            "  if (r.width > 0 && r.height > 0) { el.click(); return true; }" +
            "}" +
            "return false;");
        if (!Boolean.TRUE.equals(clicked)) {
            throw new RuntimeException("Filter button (fa-filter-icon) not found with non-zero dimensions");
        }
        LoggerHandler.info("Clicked Filter button.");
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }

    public boolean isFilterPanelVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(d ->
                !d.findElements(PLPSection.FILTER_PANEL).isEmpty() &&
                d.findElements(PLPSection.FILTER_PANEL).get(0).isDisplayed()
            );
            LoggerHandler.info("Filter panel visible.");
            return true;
        } catch (Exception e) {
            // Also accept the generic modal dialog as the filter panel
            try {
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(d ->
                    !d.findElements(PLPSection.FILTER_DIALOG).isEmpty() &&
                    d.findElements(PLPSection.FILTER_DIALOG).get(0).isDisplayed()
                );
                LoggerHandler.info("Filter panel visible (dialog fallback).");
                return true;
            } catch (Exception e2) {
                LoggerHandler.error("Filter panel not visible: " + e.getMessage());
                return false;
            }
        }
    }

    public void closeFilterPanel() {
        List<WebElement> btns = driver.findElements(PLPSection.FILTER_CLOSE_BUTTON);
        if (!btns.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btns.get(0));
            LoggerHandler.info("Closed filter panel.");
        }
    }

    public void expandFilterSection(String sectionName) {
        List<WebElement> headers = driver.findElements(PLPSection.FILTER_SECTION_HEADERS);
        headers.stream()
            .filter(h -> h.getText().trim().equalsIgnoreCase(sectionName))
            .findFirst()
            .ifPresent(h -> {
                helper.jsClick(h);
                LoggerHandler.info("Expanded filter section: " + sectionName);
            });
        // Wait for options to appear after expanding
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }

    public void selectFilterOption(String optionText) {
        // Wait for options and use contains match (handles currency symbols, extra text)
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(d ->
                !d.findElements(PLPSection.FILTER_OPTION_LINKS).isEmpty());
        } catch (Exception ignored) {}
        List<WebElement> options = driver.findElements(PLPSection.FILTER_OPTION_LINKS);
        String lower = optionText.toLowerCase();
        options.stream()
            .filter(a -> a.getText().trim().toLowerCase().contains(lower))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Filter option not found: " + optionText +
                ". Available: " + options.stream().map(a -> "\"" + a.getText().trim() + "\"")
                    .collect(java.util.stream.Collectors.joining(", "))))
            .click();
        LoggerHandler.info("Selected filter option: " + optionText);
    }

    public void clickApplyFilter() {
        helper.click(PLPSection.FILTER_APPLY_BUTTON);
        LoggerHandler.info("Clicked Apply filter button.");
    }
}
