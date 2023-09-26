package com.salmon.test.step_definitions.gui.Demo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.salmon.test.services.posts.CommentsApi;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HTTPURLConnections {
    // Run either test - one using HTTPURLConnections, the other uses Rest Assured
    // QUESTIONS :
    // 1 - DO WE NEED THE LIVE LINKS THAT GO TO GOVYPE?
    // 2 - DO WE NEED 3RD PARTY SITE VALIDATION - FACEBOOK / TWITTER ETC, NOT OUR RESPONSIBILITY IF DOWN?
    // ASKED ABOVE AS THE LESS LINKS TO CHECK THE FASTER THE METHODS EXECUTE
    // 3 - THIS WAS RUNNING AT 25 SECONDS, NOT SURE WHY IT'S NOW TAKING 2 MINS, PERHAPS SITE RESPONSE TIMES, OR SLOW CONNECTION SPEEDS?
    private static final Logger LOG = LoggerFactory.getLogger(HTTPURLConnections.class);
    String URL = "https://www-eu-uat-global-vype.non-prod.marketing.bat.net/ie/en";
    WebDriver driver;
    List<WebElement> linkList;
    ArrayList<String> activeLinks;
    HttpURLConnection connection;

    @BeforeTest
    public void setup(){
        setupDriverAndOpenURL();
        linkExtractionAndSanitation();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void runLinkCheckHttpConn( ) throws Exception {
        for (String link : activeLinks) {
            connection = (HttpURLConnection) new URL(link).openConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                LOG.info("\n ***** BROKEN LINK : ******");
                LOG.info(" ***** URL : " + link);
                LOG.info(" ***** STATUS CODE  : " + connection.getResponseCode());
            }
        }
        connection.disconnect();
    }

    @Test
    public void runLinkCheckRestAssured( ) {
        for (String link : activeLinks) {
            int statusCode = RestAssured.get(link).statusCode();

            if (statusCode != 200){
                LOG.info("\n ***** BROKEN LINK : ******");
                LOG.info(" ***** URL : " + link);
                LOG.info(" ***** STATUS CODE  : " + statusCode);
            }
        }
    }

    private void extractValidLinks() {
        activeLinks  = new ArrayList<>();
        for (WebElement link : linkList) {
            boolean validLink = link.getAttribute("href") != null && !link.getAttribute("href").contains("javascript") && !link.getAttribute("href").contains("tel") && link.getAttribute("href").contains("vype");
            if (validLink) {
                activeLinks.add(link.getAttribute("href"));
            }
        }
    }

    private void setupDriverAndOpenURL() {
        System.setProperty("webdriver.chrome.driver", "tools/chromedriver/win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.get(URL);
    }

    private void linkExtractionAndSanitation() {
        linkList = driver.findElements(By.tagName("a"));
        extractValidLinks();
    }

}
