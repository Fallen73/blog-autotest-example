package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Constants;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Главная страница блога
 */
public class MainPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private static final String blockLocator = ".blog-block";

    public MainPage(WebDriver driver, String pageUrl) {
        this.driver = driver;
        wait = (WebDriverWait) new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT).withMessage(Constants.ELEMENT_ERROR_MESSAGE);
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.urlToBe(pageUrl));
        } catch (TimeoutException e) {
            throw new IllegalStateException(Constants.CAN_NOT_GET_TO_PAGE_ERROR_MESSAGE);
        }
    }

    public List<WebElement> getBlocks() {
        return driver.findElements(new By.ByCssSelector(blockLocator));
    }

    public WebElement getBlockByIndex(int index) {
        return getBlocks().get(index);
    }
}
