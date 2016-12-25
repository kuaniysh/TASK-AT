package com.selenium.test.testng.tests;

import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Uses TestNG test framework
 * Test demonstrates simple webdriver functions : how to start browser, open url, insert some text and check that this text was inserted
 */
@Listeners({ScreenShotOnFailListener.class})
public class SimpleTest {

    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void testGoogleTitle()
    {
        try {
            System.out.println("Google1 Test Started! " + Thread.currentThread().getId());
            WebDriverFactory.getDriver().navigate().to("http://www.google.com");
            System.out.println("Google1 Test's Page title is: " + WebDriverFactory.getDriver().getTitle() + " " + Thread.currentThread().getId());
            Assert.assertEquals(WebDriverFactory.getDriver().getTitle(), "Google");
            System.out.println("Google1 Test Ended! " + Thread.currentThread().getId());
//		} catch ( NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {  //this is for Java7
        } catch (Exception e) {                                                                   //this is for Java6
            e.printStackTrace();
        }
    }

    @Test
    public void searchGoogle(){
        WebDriverFactory.getDriver().get("http://www.google.com");
        WebDriverFactory.getDriver().findElement(By.cssSelector("#lst-ib")).sendKeys("Türkiye");
        WebDriverFactory.getDriver().findElement(By.name("btnG")).click();
        String result = WebDriverFactory.getDriver().findElement(By.cssSelector(".r>a")).getText();
        System.out.println(result);
        Assert.assertTrue(result != null);
    }

    @Test
    public void testNavigation(){
        WebDriverFactory.getDriver().get("http://google.com");
        WebDriverFactory.getDriver().findElement(By.name("q")).sendKeys("Maven Repository");
        System.out.println("Updating my project");

    }
    
    @Test
    public void testYoutube() {
        String toSearch = "Selenium";
        WebDriverFactory.getDriver().get("http://www.youtube.com");
        WebElement searchString = WebDriverFactory.getDriver().findElement(By.cssSelector("#masthead-search-term"));
        searchString.sendKeys(toSearch);
        String searchStringText = searchString.getAttribute("value");
        assertTrue("Text from page(" + searchStringText + ") not equals to text from test(" + toSearch + ")",
                searchStringText.equals(toSearch));
    }

    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }

}
