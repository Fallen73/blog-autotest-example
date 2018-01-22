package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Constants;
import java.util.List;

/**
 * Страница статьи
 */
public class ArticlePage{

    private WebDriverWait wait;

    @FindBy(css = "h2.field-content.title")
    private WebElement title;

    @FindBy(css = ".main-news .field-item.even>img")
    private WebElement image;

    @FindBy(css = ".main-news .announce>p")
    private WebElement announce;

    @FindBy(css = ".main-news .date-display-single")
    private WebElement creationDate;

    @FindBy(css = ".main-news .field-content.tags>a")
    private List<WebElement> tags;

    @FindBy(css = ".main-news .type>a")
    private WebElement type;

    @FindBy(css = ".empty-second>a")
    private WebElement blog;

    @FindBy(css = ".stat-block > span:nth-child(2)")
    private WebElement viewCounter;

    @FindBy(css = ".main-news .field-content .comment")
    private WebElement commentCounter;

    public ArticlePage(WebDriver driver) {
        wait = (WebDriverWait) new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT).withMessage(Constants.ELEMENT_ERROR_MESSAGE);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public int getViewCounter() {
        return Integer.valueOf(viewCounter.getText());
    }

    public void clickBlog() {
        blog.click();
    }
}
