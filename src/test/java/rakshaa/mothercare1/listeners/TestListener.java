package rakshaa.mothercare1.listeners;

import rakshaa.mothercare1.utils.Base;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.ProgressBar;
import rakshaa.mothercare1.utils.ScreenRecording;
import rakshaa.mothercare1.utils.Reporter;
import rakshaa.mothercare1.utils.Screenshot;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;

public class TestListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    // ── ISuiteListener ────────────────────────────────────────────────────────

    @Override
    public void onStart(ISuite suite) {
        LoggerHandler.info("====== MOTHERCARE1 AUTOMATION SUITE STARTED ======");
        LoggerHandler.info("Browser : " + Base.getConfig("browser", "chrome"));
        LoggerHandler.info("URL     : " + Base.getConfig("base.url"));

        clearDirectory(Base.getConfig("recording.dir", "recordings"));

        Reporter.init();
        ProgressBar.init();
    }

    @Override
    public void onFinish(ISuite suite) {
        LoggerHandler.info("====== MOTHERCARE1 AUTOMATION SUITE FINISHED ======");
        Reporter.flush();
        ProgressBar.close();
    }

    // ── IInvokedMethodListener ────────────────────────────────────────────────

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod()) return;
        ScreenRecording.start(result.getMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod()) return;
        ScreenRecording.stop();
    }

    // ── ITestListener ─────────────────────────────────────────────────────────

    @Override
    public void onTestSuccess(ITestResult result) {
        ProgressBar.recordPass();
        LoggerHandler.testEnd(result.getMethod().getMethodName(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String message  = result.getThrowable() != null
                ? result.getThrowable().getMessage() : "No message";

        ProgressBar.recordFail();

        WebDriver driver = Base.getDriverOrNull();
        if (driver != null) {
            Reporter.failWithScreenshot(driver, testName, message);
            byte[] screenshotBytes = Screenshot.captureBytes(driver);
            if (screenshotBytes.length > 0) {
                Allure.addAttachment("Failure Screenshot - " + testName,
                        "image/png", new ByteArrayInputStream(screenshotBytes), "png");
            }
        } else {
            Reporter.fail(message);
        }

        LoggerHandler.testEnd(testName, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ProgressBar.recordSkip();
        Reporter.skip("Test skipped.");
        LoggerHandler.testEnd(result.getMethod().getMethodName(), "SKIP");
    }

    // ── Utility ───────────────────────────────────────────────────────────────

    private void clearDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) { dir.mkdirs(); return; }
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) deleteRecursive(f);
        LoggerHandler.info("Cleared recordings directory: " + dirPath);
    }

    private void deleteRecursive(File f) {
        if (f.isDirectory()) {
            File[] children = f.listFiles();
            if (children != null) for (File c : children) deleteRecursive(c);
        }
        f.delete();
    }
}
