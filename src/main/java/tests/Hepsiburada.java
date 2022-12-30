package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Hepsiburada {

    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.hepsiburada.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void test() throws InterruptedException {
        //Çerezleri Kabul et uygulanır.
        WebElement coockieBtn = driver.findElement(By.xpath("//div[@id='onetrust-button-group']//button[@id='onetrust-accept-btn-handler']"));
        coockieBtn.click();
        //İphone ürünü aranır.
        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Ürün, kategori veya marka ara']"));
//        search.click();
        search.sendKeys("iPhone");
        //arama butonuna basılır.
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='SearchBoxOld-cHxjyU99nxdIaAbGyX7F']"));
        searchButton.click();
        Thread.sleep(3000);
        //24 . cü ürünü seçtik
        WebElement item = driver.findElement(By.xpath("(//a[contains(@class,'moria-ProductCard')])[1]"));
        item.click();
        switchLastTab(driver); //açılan sekmeye geçiş yapıldı.
        // Değerlendirme yerine gidicek
        WebElement evaluation = driver.findElement(By.xpath("//div[@id='comments-container']//a"));
        evaluation.click();
        //Begenme tuşuna basılır.
        WebElement yesButton = driver.findElement(By.xpath("(//div[contains(@class,'thumbsUp')])[1]"));
        yesButton.click();
        // son ürünün olduğu yere scroll ettirdik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", yesButton);
        //Teşekkür ederiz yazısını gördük
        String mesaj = driver.findElement(By.xpath("(//div/span[contains(text(),'Teşekkür Ederiz.')])[1]")).getText();
        System.out.println(mesaj);
    }

    @After
    public void teardown() {

        driver.close();
    }


    public static void switchLastTab(WebDriver driver) {
        String originTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!originTab.equals(tab)) {
                driver.switchTo().window(tab);
            }
        }
    }

}
