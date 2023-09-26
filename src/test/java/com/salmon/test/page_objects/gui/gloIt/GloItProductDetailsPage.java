package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

public class GloItProductDetailsPage extends PageObject {
	
	public By pdpProductTitle= By.cssSelector(".page-title-wrapper");
	public By pdpProductTitleGloIT = By.xpath("(//div[@class='widget block block-static-block']//div[@class='pagebuilder-column'])[1]//span");
	public final static By M_PDP_PRODUCT_TITLE_GLOIT = By.cssSelector("div[data-visible-mobile='1'] > div > div.pagebuilder-column-group > div.pagebuilder-column > div > h2 > span");
	private final static By PDP_PRODUCT_TITLE2=By.cssSelector("h1.page-title");
	private final static By PDP_HYPER_PLUS_DELIVERY = By.cssSelector("main#maincontent div.hyper-plus-pdp__logistics-item.hyper-plus-pdp__logistics-item--delivery");
	private final static By PDP_HYPER_PLUS_SHIPPING = By.cssSelector("main#maincontent div.hyper-plus-pdp__logistics-item.hyper-plus-pdp__logistics-item--shipping");
	private final static By PDP_HYPER_PLUS_SPECIFICATIONS = By.cssSelector("div.hyper-plus-specs__body");
	private final static By PDP_HYPER_PLUS_WHATS_IN_THE_BOX = By.cssSelector("div.hyper-plus-pdp__box-item");
	private final static By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE = By.cssSelector("div.hyper-plus-pdp__products-row");
	private final static By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM = By.cssSelector("li.product-item");
	private final static By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_PHOTO = By.cssSelector("img.product-image-photo");
	private final static By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_LINK = By.cssSelector("a.product-item-link");
	private static final By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_PRICE = By.cssSelector("div.price-box.price-final_price");
	private static final By PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_ACTIONS = By.cssSelector("div.product-item-actions");
	private static final By PDP_HYPER_PLUS_MAIN_IMAGE_LOADED = By.cssSelector("div.m360-loader[data-progress='100%'");
	public static final By PDP_HYPER_PLUS_PRODUCT_PRICE = By.cssSelector("div.product-info-price span.price");
	private static final By PDP_HYPER_PLUS_MAIN_BUTTON = By.cssSelector("div.configurator-button.main");
	public static final By PDP_HYPER_PLUS_MAIN_BODY_SWATCH_TITLE = By.cssSelector("div.sliding-panel-sections.desktop-only > div > div.configurator-section.main > h2 > span:nth-child(3)");
	public static final By PDP_HYPER_PLUS_MAIN_BODY_SWATCH = By.cssSelector("div.sliding-panel-sections.desktop-only > div > div.configurator-section.main > div > div.colour-swatch.main");
	private static final By PDP_HYPER_PLUS_SIDE_BUTTON = By.cssSelector("div.configurator-button.side");
	public static final By PDP_HYPER_PLUS_SIDE_SWATCH_TITLE = By.cssSelector("div.sliding-panel-sections.desktop-only > div > div.configurator-section.side > h2 > span:nth-child(3)");
	public static final By PDP_HYPER_PLUS_SIDE_SWATCH = By.cssSelector("div.sliding-panel-sections.desktop-only div.colour-swatch.side");
	public static final By PDP_HYPER_PLUS_GET_INSPIRED_TITLE = By.cssSelector("div.sliding-panel-sections.desktop-only > div > div.configurator-section.side > h2 > span:nth-child(3)");
	public static final By PDP_HYPER_PLUS_GET_INSPIRED_SWATCH = By.cssSelector("div.colours-swatches.inspiration.desktop-only > div:nth-child(2) > div > figure");
	private static final By ADDITIONAL_DETAILS_SECTION=By.cssSelector("div.additional-attributes-wrapper.table-wrapper");
	private static final By ADDITIONAL_DETAILS_SECTION_TITLE = By.cssSelector("h2.product-section-title");
	public static final By QTY_EXCEEDED_ERROR_MESSAGE=By.cssSelector("div#qty-error");
	public static final By QTY_TEXT_PDP=By.cssSelector("div.field.qty>label>span");

	public void userNavigatesToPdpPage() throws MalformedURLException{
		UrlBuilder.navigateToPdpPage();
	}
	public void userNavigatesToCheckoutPage() throws MalformedURLException{
		UrlBuilder.navigateToCheckoutPage();
	}
	public void getPDPTitle() {
		switch (UrlBuilder.getLocale())  {
			case "kz":
				assertTrue(waitForExpectedElement(PDP_PRODUCT_TITLE2).isDisplayed());
				break;
			case "it":
				if(UrlBuilder.isMobile()){
					scrollElementIntoView(M_PDP_PRODUCT_TITLE_GLOIT);
					assertTrue(waitForExpectedElement(M_PDP_PRODUCT_TITLE_GLOIT).isDisplayed());
				}
				else {
					assertTrue(waitForExpectedElement(pdpProductTitleGloIT).isDisplayed());
				}
				break;
			default:
				assertTrue(waitForExpectedElement(pdpProductTitle).isDisplayed());
		}
}

	public String getHyperPlusDeliveryDetails() {
	  	waitForExpectedElement(PDP_HYPER_PLUS_DELIVERY);
	  	return getWebDriver().findElement(PDP_HYPER_PLUS_DELIVERY).getText();
	  }

	public String getHyperPlusShippingDetails() {
	  	waitForExpectedElement(PDP_HYPER_PLUS_SHIPPING);
	  	return getWebDriver().findElement(PDP_HYPER_PLUS_SHIPPING).getText();
	  }

	  public String getHyperPlusSpecsDetails() {
	  	waitForExpectedElement(PDP_HYPER_PLUS_SPECIFICATIONS);
		  return getWebDriver().findElement(PDP_HYPER_PLUS_SPECIFICATIONS).getText();
	  }

	 public boolean validWhatsInTheBox() {
		waitForExpectedElement(PDP_HYPER_PLUS_WHATS_IN_THE_BOX);
		  List<WebElement> boxItems =  getWebDriver().findElements(PDP_HYPER_PLUS_WHATS_IN_THE_BOX);
		  if (boxItems.size() == 0) {
		  	return false;
		  }
		  for (WebElement item: boxItems) {
		  	if (item.getText() == null || item.getText().length() == 0) {
		  		return false;
			}
		  }
		  return true;
	  }

	  public boolean validYouMightAlsoLike() {
	  	waitForExpectedElement(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE, 5);
	  	List<WebElement> products = getWebDriver().findElements(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM);
	  	List<WebElement> itemPhotos = getWebDriver().findElements(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_PHOTO);
	  	List<WebElement> itemLinks = getWebDriver().findElements(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_LINK);
	  	List<WebElement> itemPrices = getWebDriver().findElements(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_PRICE);
	  	List<WebElement> itemActions = getWebDriver().findElements(PDP_HYPER_PLUS_YOU_MIGHT_ALSO_LIKE_ITEM_ACTIONS);
		return (products.size()>0 && itemPhotos.size()>0 && itemLinks.size()>0 && itemPrices.size()>0 && itemActions.size()>0);
	  }

	public boolean waitForMainImageToLoad() {
		waitForExpectedElement(PDP_HYPER_PLUS_MAIN_IMAGE_LOADED, 20);
		return true;
	}

	public boolean priceIsDisplayed() {
	  	String priceString = webDriver.findElement(PDP_HYPER_PLUS_PRODUCT_PRICE).getText();
	  	// check that at least 1 non zero digit is in the price
	  	return Pattern.compile("[1-9]").matcher(priceString).find();
	}

	public boolean mainBubbleIsDisplayed() {
		webDriver.findElement(PDP_HYPER_PLUS_MAIN_BUTTON).isDisplayed();
	  	webDriver.findElement(PDP_HYPER_PLUS_MAIN_BUTTON).isEnabled();
	  	return true;
	}

	public void clickOnMainButton() {
		webDriver.findElement(PDP_HYPER_PLUS_MAIN_BUTTON).click();
	}

	public boolean validMainBodySwatches() {
	  	wait.until(ExpectedConditions.visibilityOfElementLocated(PDP_HYPER_PLUS_MAIN_BODY_SWATCH_TITLE));
	  	String title = webDriver.findElement(PDP_HYPER_PLUS_MAIN_BODY_SWATCH_TITLE).getText();
		List<WebElement> swatches = webDriver.findElements(PDP_HYPER_PLUS_MAIN_BODY_SWATCH);
		return title.length()>0 && swatches.size()>0;
	}

	public boolean sideBubbleIsDisplayed() {
		webDriver.findElement(PDP_HYPER_PLUS_SIDE_BUTTON).isDisplayed();
		webDriver.findElement(PDP_HYPER_PLUS_SIDE_BUTTON).isEnabled();
		return true;
	}

	public void clickOnSideButton() {
		webDriver.findElement(PDP_HYPER_PLUS_SIDE_BUTTON).click();
	}

	public boolean validSideSwatches() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PDP_HYPER_PLUS_SIDE_SWATCH_TITLE));
		String title = webDriver.findElement(PDP_HYPER_PLUS_SIDE_SWATCH_TITLE).getText();
		List<WebElement> swatches = webDriver.findElements(PDP_HYPER_PLUS_SIDE_SWATCH);
		return title.length()>0 && swatches.size()>0;
	}

	public boolean validGetInspiredSwatches() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PDP_HYPER_PLUS_GET_INSPIRED_TITLE));
		String title = webDriver.findElement(PDP_HYPER_PLUS_GET_INSPIRED_TITLE).getText();
		List<WebElement> swatches = webDriver.findElements(PDP_HYPER_PLUS_GET_INSPIRED_SWATCH);
		return title.length()>0 && swatches.size()>0;
	}

	public void clickOnGetInspired() {
		webDriver.findElement(PDP_HYPER_PLUS_GET_INSPIRED_TITLE).click();
	}

	public void userNavigatesToGLOAccessoriesPage(String strURL) {
		getWebDriver().navigate().to(getWebDriver().getCurrentUrl()+UrlBuilder.getMessage(strURL));
	}

	public void GloUserClicksOnAnyLinkByText(String linkText) {
		waitForExpectedElement(By.linkText(linkText),10);
		clickByElementByQueryJSExecutor(By.linkText(linkText));
	}

	public void assertWeitereInformationBlockNotDisplayed() {
		waitForExpectedElement(ADDITIONAL_DETAILS_SECTION_TITLE,3);
		assertTrue(invisibilityOfElementWithText(ADDITIONAL_DETAILS_SECTION_TITLE,"Weitere Information"));
		assertTrue(invisibilityOfElementLocated(ADDITIONAL_DETAILS_SECTION));
	}

	public String getQuantityTextOnPDPPage() {
		return waitForExpectedElement(QTY_TEXT_PDP,3).getText();
	}
}
	

