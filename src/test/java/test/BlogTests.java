package test;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.ArticleBlock;
import pages.ArticlePage;
import pages.MainPage;
import resources.Constants;
import resources.DriverFactory;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;
import java.net.MalformedURLException;
import java.util.List;
import static org.testng.Assert.assertTrue;

/**
 * Тест-кейс проверки отображения блоков постов при полной загрузке страницы
 * и проверки корректной работы счетчика количества просмотров
 */
public class BlogTests {

    private WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "autUrl", "driverPath"})
    public void setEnvironment(String browserName, String autUrl,
                               @Optional("") String driverPath) throws MalformedURLException {
        this.driver = DriverFactory.createDriver(browserName, driverPath);
        Dimension dimension = new Dimension(Constants.WIDTH, Constants.LENGTH);
        driver.manage().window().setSize(dimension);
    }

    @BeforeMethod
    @Parameters({"autUrl"})
    public void getToStartPage(String autUrl) {
        driver.get(autUrl);
    }

    @Severity(SeverityLevel.NORMAL)
    @Title("Тест проверки отображения блоков постов при полной загрузке страницы")
    @Parameters({"autUrl"})
    @Test
    public void blogPageDisplayTest(String autUrl) {
        MainPage mainPage = new MainPage(driver, autUrl);
        ArticleBlock articleBlock;
        List<WebElement> articles = mainPage.getBlocks();
        for (WebElement article: articles) {
            articleBlock = new ArticleBlock(driver, article);
            try {
                articleBlock.checkBlock ();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                assertTrue(false, e.getMessage());
            }
        }
        assertTrue(true);
    }

    @Severity(SeverityLevel.NORMAL)
    @Title("Тест проверки корректной работы счетчика количества просмотров при открытии страницы")
    @Parameters({"autUrl"})
    @Test
    public void articleDisplayTest(String autUrl) {
        SoftAssert sa= new SoftAssert();
        MainPage mainPage = new MainPage(driver, autUrl);
        int articles = mainPage.getBlocks().size();
        for (int i=0; i<articles; i++) {
            mainPage = new MainPage(driver, autUrl);
            ArticleBlock articleBlock = new ArticleBlock(driver, mainPage.getBlockByIndex(i));
            if (!articleBlock.isVisible()) {
                continue;
            }
            String titleMain = articleBlock.getArticleTitle();
            int viewCounterSelectorMain = articleBlock.getViewCounterSelector();
            articleBlock.clickTitle();
            ArticlePage articlePage = new ArticlePage(driver);
            String titleLocal = articlePage.getTitle();
            int viewCounterSelectorLocal = articlePage.getViewCounter();
            sa.assertEquals(titleMain, titleLocal, String.format(Constants.TITLE_ERROR_MESSAGE, titleMain,
                    viewCounterSelectorLocal));
            sa.assertTrue(viewCounterSelectorMain < viewCounterSelectorLocal,
                    String.format(Constants.VIEW_COUNTER_ERROR_MESSAGE, titleMain, viewCounterSelectorMain,
                            viewCounterSelectorLocal));
            articlePage.clickBlog();
        }
        sa.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
