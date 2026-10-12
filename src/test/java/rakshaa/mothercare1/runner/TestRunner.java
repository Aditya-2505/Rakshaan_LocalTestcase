package rakshaa.mothercare1.runner;

import rakshaa.mothercare1.utils.Base;
import rakshaa.mothercare1.utils.EmailUtil;
import rakshaa.mothercare1.utils.LoggerHandler;
import rakshaa.mothercare1.utils.ProgressBar;
import rakshaa.mothercare1.utils.Reporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.ITestListener;

import java.io.File;

public class TestRunner implements ISuiteListener, ITestListener {

    private static int totalPassed  = 0;
    private static int totalFailed  = 0;
    private static int totalSkipped = 0;

    @Override
    public void onStart(ISuite suite) {
        LoggerHandler.info("====== MOTHERCARE1 AUTOMATION SUITE STARTED ======");
        LoggerHandler.info("Developer: Rakshaa");
        LoggerHandler.info("Browser  : " + Base.getConfig("browser", "chrome"));
        LoggerHandler.info("URL      : " + Base.getConfig("base.url"));

        clearDirectory(Base.getConfig("recording.dir", "recordings"));

        Reporter.init();
        ProgressBar.init();
    }

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

    @Override
    public void onFinish(ISuite suite) {
        LoggerHandler.info("====== MOTHERCARE1 AUTOMATION SUITE FINISHED ======");
        LoggerHandler.info("Results  : Passed=" + totalPassed
                + "  Failed=" + totalFailed + "  Skipped=" + totalSkipped);

        Reporter.flush();
        ProgressBar.close();
        // Email disabled — set email.enabled=true in config.properties to enable
    }

    @Override
    public void onTestSuccess(ITestResult result) { totalPassed++; }

    @Override
    public void onTestFailure(ITestResult result) { totalFailed++; }

    @Override
    public void onTestSkipped(ITestResult result) { totalSkipped++; }
}
