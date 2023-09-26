package com.salmon.test.framework;

import com.salmon.test.framework.helpers.utils.WriteToFile;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.utils.WriteToFile.closeFile;
import static com.salmon.test.framework.helpers.utils.WriteToFile.writeToFile;

/**
 * @author msmith
 * Class to find broken links
 * differnt methods available for API link checking
 */
public class BrokenLinkFinder {

  String URL;
  String pageTitle;
  WebDriver driver;
  List<WebElement> linkList;
  ArrayList<String> activeLinks;
  ArrayList<String> brokenLinks;
  HttpURLConnection connection;
  WriteToFile writeToFile;
  private static final Logger LOG = LoggerFactory.getLogger(BrokenLinkFinder.class);

  public BrokenLinkFinder(WebDriver driver) {
    this.driver = driver;
    URL = driver.getCurrentUrl();
    pageTitle = driver.getTitle();
    }

  public BrokenLinkFinder(WebDriver driver, String URLToNavigateTo) {
    this.driver = driver;
    driver.get(URLToNavigateTo);
    URL = driver.getCurrentUrl();
    pageTitle = driver.getTitle();
  }

  public void runLinkChecker() throws Exception {
    linkExtractionAndSanitation();
    extractValidLinks();
    runLinkCheckHttpConn();
    createRunReport();
  }

  private void createRunReport() throws FileNotFoundException, UnsupportedEncodingException {
    LOG.info("Saving information to report");
    String pageTitleFormatted = pageTitle.trim().toLowerCase().replace("|","");
    writeToFile = new WriteToFile();
    writeToFile.createNewFile("target/brokenLinkReports",pageTitleFormatted );
    writeToFile("Page Title : " + pageTitle);
    writeToFile("URL : " + URL);
    writeToFile("Number of broken links found : " + brokenLinks.size());

    for (String entry : brokenLinks){
      writeToFile(entry);
    }
    closeFile();
  }

  public void runLinkCheckHttpConn() throws Exception {
    brokenLinks = new ArrayList<>();
    for (String link : activeLinks) {
      connection = (HttpURLConnection) new URL(link).openConnection();
      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
        LOG.info("\n ***** BROKEN LINK : ******");
        LOG.info(" ***** URL : " + link);
        LOG.info(" ***** STATUS CODE  : " + connection.getResponseCode());
        brokenLinks.add("Broken LInk : " + link + " RESPONSE CODE : " + connection.getResponseCode());
      }
    }
    connection.disconnect();
  }

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
      boolean validLink = link.getAttribute("href") != null && !link.getAttribute("href").contains("javascript") && !link.getAttribute("href").contains("tel") && link.getAttribute("href").contains("vype") && !link.getAttribute("href").contains("mailto");
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
