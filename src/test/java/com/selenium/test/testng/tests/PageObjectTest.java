package com.selenium.test.testng.tests;

import com.selenium.test.pages.YouTubePage;
import com.selenium.test.pages.YouTubeSearchResultsPage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kuanysh Bekturganov
 * Uses TestNG test framework
 */
@Listeners({ScreenShotOnFailListener.class})
public class PageObjectTest {

    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }


    @Test
    public void testSearch() {
        String toSearch = "Selenium";
        YouTubePage youTubePage = new YouTubePage();
        youTubePage.insertSearchString(toSearch);
        YouTubeSearchResultsPage resultsPage = youTubePage.doSearch();
        assertTrue("No results were found on results page", resultsPage.hasResults());
    }


    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
