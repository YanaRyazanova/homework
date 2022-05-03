package ozon;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CartTest {
    FirefoxDriver driver;

    @Before
    public void setup()
    {
        System.setProperty("webdriver.gecko.driver", "C:/Users/Пользователь/Downloads/geckodriver-v0.31.0-win64/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    @Test
    public void addToCart(){
        driver.get("https://www.ozon.ru/");

        // Ищем корм для кошек и добавляем в корзину
        driver.findElement(By.name("text")).sendKeys("корм для кошек");
        driver.findElement(By.cssSelector(".tx1")).click();
        driver.findElement(new By.ByXPath("//span[contains(.,'В корзину')]")).click();

        // Переходим в корзину
        driver.get("https://www.ozon.ru/cart");

        WebElement element = driver.findElement(new By.ByXPath("//div[2]/a/span/span"));
        // проверяем, что товар высвечивается в корзине
        Assert.assertTrue("Ожидаем отображение товара в корзине", element.isDisplayed());

        // проверяем, что счетчик корзины показывает 1
        WebElement elementCount = driver.findElement(new By.ByXPath("//div[3]/div/div/div/div/div[2]"));
        Assert.assertEquals("Ожидаем в корзине 1 товар", "1", elementCount.getText());

        // переходим в карточку товара
        //driver.get("https://www.ozon.ru/product/suhoy-korm-dlya-koshek-starshe-1-goda-winner-meat-meat-s-sochnym-yagnenkom-0-3-kg-250747996/?sh=pFOa9medPQ");
        driver.get("https://www.ozon.ru/cart");

        // удаляем товар из корзины
        //driver.findElement(By.cssSelector(".s4g:nth-child(4) > .tsCaptionBold")).click();
        driver.findElement(By.cssSelector(".an1:nth-child(2) .ui-g0")).click();
        driver.findElement(By.cssSelector(".a7m .ui-c4")).click();

        // проверка, что в корзине 0 элементов
        WebElement emptyCartElement = driver.findElement(new By.ByXPath("//h1[contains(.,'Корзина пуста')]"));
        Assert.assertTrue("Ожидаем пустую корзину", emptyCartElement.isDisplayed());
        Assert.assertEquals("Ожидаем заголовок пустой корзины", "Корзина пуста", emptyCartElement.getText());
    }


    @After
    public void close()
    {
        //driver.quit();
    }
}
