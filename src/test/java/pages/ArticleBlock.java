package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Constants;
import java.util.concurrent.TimeUnit;

/**
 * Блок статьи/поста
 */
public class ArticleBlock {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement rootElement;
    private static final String postImageSelector = ".photo-link img";
    private static final String postTypeSelector = ".type";
    private static final String postCreationDateSelector = ".date-display-single";
    private static final String postViewCounterSelector = ".views";
    private static final String postView = ".stat-block > span:nth-child(2)";
    private static final String postCommentCounterSelector = ".comment";
    private static final String postTagsSelector = ".field-content.tags>a";
    private static final String postTitleSelector = ".field-content.title>a";
    private static final String postAnnounceSelector = ".announce>p";

    public ArticleBlock(WebDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.rootElement = rootElement;
        wait = (WebDriverWait) new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT).withMessage(Constants.ELEMENT_ERROR_MESSAGE);
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    private void existsOnPage(WebElement webElement, String errorMessage) throws IllegalStateException {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException e) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public boolean isVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(rootElement.findElement(By.cssSelector(postImageSelector))));
        } catch (TimeoutException t) {
            return false;
        }
        return true;
    }

    public String getArticleTitle() {
        WebElement title = rootElement.findElement(By.cssSelector(postTitleSelector));
        return title.getText();
    }

    public int getViewCounterSelector() {
        WebElement view = rootElement.findElement(By.cssSelector(postView));
        String viewCounterSelector = view.getText();
        return Integer.valueOf(viewCounterSelector);
    }

    public void clickTitle() {
        Actions action = new Actions(driver);
        action.moveToElement(rootElement.findElement(By.cssSelector(postTitleSelector)), 0, 200).perform();
        rootElement.findElement(By.cssSelector(postTitleSelector)).click();
    }

    public void checkBlock() throws IllegalStateException {
        if(isVisible()) {
            existsOnPage(rootElement.findElement(By.cssSelector(postTitleSelector)), Constants.CAN_NOT_FIND_TITLE_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postAnnounceSelector)), Constants.CAN_NOT_FIND_ANNOUNCE_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postTypeSelector)), Constants.CAN_NOT_FIND_TYPE_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postCreationDateSelector)), Constants.CAN_NOT_FIND_DATE_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postTagsSelector)), Constants.CAN_NOT_FIND_TAGS_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postImageSelector)), Constants.CAN_NOT_FIND_IMAGE_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postViewCounterSelector)), Constants.CAN_NOT_FIND_VIEW_COUNTER_ERROR_MESSAGE);
            existsOnPage(rootElement.findElement(By.cssSelector(postCommentCounterSelector)), Constants.CAN_NOT_FIND_COMMENT_COUNTER_ERROR_MESSAGE);
        }
    }
}
