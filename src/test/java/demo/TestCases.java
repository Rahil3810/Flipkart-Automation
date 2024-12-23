package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v123.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.AbstractSequentialIterator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }
    @Test(enabled = true)
    public void testCase01() throws InterruptedException{
       

        driver.get("http://www.flipkart.com/");
        WebElement searchBar = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        searchBar.sendKeys("Washing Machine");
        Actions action = new Actions(driver);
        action.sendKeys(searchBar, Keys.ENTER).perform();
        Thread.sleep(3000);

        
        WebElement popularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
        popularity.click();
        Thread.sleep(5000);

        List <WebElement> Ratings = driver.findElements(By.xpath("//span[@class='Y1HWO0']"));
        int count = 0;
        for(WebElement rate : Ratings){
            String Rating = rate.getText();
            float RatingFloat = Float.parseFloat(Rating);
            if(RatingFloat<=4){
                count ++;
            }
        }System.out.println(count);
    }
    @Test (enabled = true)
    public void testCase02() throws InterruptedException{
        System.out.println("TestCase 02 Start");
        driver.get("http://www.flipkart.com/");
        Thread.sleep(4000);
        WebElement searchBar = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        searchBar.clear();
        Thread.sleep(2000);
        searchBar.sendKeys("iPhone");
        Actions action = new Actions(driver);
        action.sendKeys(searchBar, Keys.ENTER).perform();
        Thread.sleep(10000);
        List<WebElement> ParentList = driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
        

        for(WebElement Item : ParentList){
            try{
            WebElement DiscountEle = Item.findElement(By.xpath(".//div[@class='UkUFwK']"));
            String Discounts = DiscountEle.getText();
            System.out.println(Discounts);
            Thread.sleep(2000);
            
            if(Discounts.contains("% off")){
                String NumberDis = Discounts.replaceAll("[^\\d.]", "");
                int NumberInt = Integer.parseInt(NumberDis);
                System.out.println(NumberInt);
                if(NumberInt>17){
                    WebElement Title = Item.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                    String TitleStr = Title.getText();
                    System.out.println(TitleStr);
                    System.out.println(NumberInt);
                    Thread.sleep(1000);
                    }
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Discount is Not Available for this Item");
            }
           
        }
        System.out.println("TestCase 02 End");
    }

    @Test(enabled = true)
    public void testCase03() throws InterruptedException{
        driver.get("http://www.flipkart.com/");



        Thread.sleep(10000);
        WebElement searchBar = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        searchBar.clear();
        Thread.sleep(2000);
        searchBar.sendKeys("Coffee Mug");
        Actions action = new Actions(driver);
        action.sendKeys(searchBar, Keys.ENTER).perform();
        Thread.sleep(5000);

        WebElement FourStar = driver.findElement(By.xpath("(//label[@class='tJjCVx _3DvUAf'])[1]"));
        FourStar.click();
        Thread.sleep(3000);

        Boolean status = Wrappers.printTitleAndImageOFTopFiveMugs(driver, By.xpath("//div[@class='slAVV4']//span[@class='Wphh3N']"));
        System.out.println("End OF test Caase");







        // WebElement Rating = driver.findElement(By.xpath("//div[contains(@title,'4')]//label[@class='tJjCVx _3DvUAf']"));
        // Rating.click();
        // List<WebElement> items = driver.findElements(By.xpath("//div[@class='slAVV4']"));
        
        
        // for(WebElement Item : items){
        //     WebElement review = Item.findElement(By.xpath("//div[@class='slAVV4']//span[@class='Wphh3N']"));
        //     String reviewTxt = review.getText();
            
        // }       
    }

    



    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}


// WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
//             waits.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='Wphh3N']")));


//             
//             // System.out.println(reviewTxt);
            
            
//             String reviewStr = reviewTxt.replaceAll("\\(", "").replaceAll("\\)", ""); 
//             int reviewInt = Integer.parseInt(reviewStr);
//             System.out.println(reviewInt);
//             Thread.sleep(2000);
