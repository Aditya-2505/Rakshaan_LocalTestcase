package rakshaa.mothercare1.uistore;

import org.openqa.selenium.By;

public class PLPSection {

    private PLPSection() {}

    // ── Page structure ────────────────────────────────────────────────────────

    public static final By PAGE_HEADING   = By.cssSelector("main h1");
    public static final By PRODUCT_LINKS  = By.cssSelector("main a[href^='/product/']");
    public static final By BREADCRUMB     = By.cssSelector("nav[aria-label='Breadcrumb']");

    // ── Sort ──────────────────────────────────────────────────────────────────

    // Sort/filter buttons — identified by JS text-matching in PLPSectionActions (see clickSortBy/clickFilter)
    public static final By SORT_BY_BUTTON      = By.cssSelector("button:has(i.fa-sort-icon)");
    public static final By SORT_DIALOG         = By.cssSelector(".DqWmk");
    public static final By SORT_CLOSE_BUTTON   = By.cssSelector("button[aria-label='Close sort options']");
    public static final By SORT_OPTION_BUTTONS = By.cssSelector(".DqWmk div.R5reE button");

    // ── Filter ────────────────────────────────────────────────────────────────

    public static final By FILTER_BUTTON          = By.cssSelector("button:has(i.fa-filter-icon)");
    public static final By FILTER_DIALOG          = By.cssSelector("[role='dialog'][aria-modal='true']");
    public static final By FILTER_PANEL           = By.cssSelector(".PPDJC");
    public static final By FILTER_CLOSE_BUTTON    = By.cssSelector("button[aria-label='close']");
    public static final By FILTER_APPLY_BUTTON    = By.cssSelector("button.F5d3R");
    public static final By FILTER_SECTION_HEADERS = By.cssSelector(".PPDJC .uBH2z");
    public static final By FILTER_OPTION_LINKS    = By.cssSelector(".PPDJC a");
    public static final By FILTER_OPTION_LABELS   = By.cssSelector(".PPDJC a span.vugQU");
}
