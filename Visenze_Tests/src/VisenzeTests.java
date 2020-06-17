import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.NoSuchElementException;

public class VisenzeTests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://visenze-my.iprice.mx/compare/apple-iphone-11/");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement keySpecs = driver.findElementByCssSelector("a[data-vars-cgt*='product_characteristic']");
        keySpecs.click();
        driver.navigate().back();

        WebElement offers = driver.findElementById("default-nav-1");
        offers.click();

/*        WebElement moreOffers = driver.findElementById("state_lazadaShowMore");
        moreOffers.click();*/

        WebElement productSpecs = driver.findElementById("default-nav-2");
        productSpecs.click();

        WebElement similarProducts = driver.findElementById("default-nav-3");
        similarProducts.click();

        WebElement relatedProducts = driver.findElementByCssSelector("a[data-vars-cgt*='related_product']");
        relatedProducts.click();

        WebElement recommendedOffer = driver.findElementByCssSelector("a[data-vars-action='pc-recommended']");
        String merchant = recommendedOffer.getAttribute("data-vars-merchant");
        int separator = merchant.indexOf('|');
        String trimmedMerchant = (merchant.substring(0, separator)).toLowerCase();

        recommendedOffer.click();

        String parentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()){
            if(!parentHandle.equals(handle)){
                System.out.println(handle);
                driver.switchTo().window(handle);
            }
        }

        boolean waitAfterRedirect = wait.until(ExpectedConditions.urlContains(trimmedMerchant));
        driver.close();
    }
}
