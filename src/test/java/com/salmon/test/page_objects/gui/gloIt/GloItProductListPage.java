package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.salmon.test.framework.helpers.UrlBuilder.getMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class GloItProductListPage extends PageObject {

	private OrderHistoryPage orderHistoryPage;

	private By epokLogo = By.cssSelector("a.logo");
	public  By PLPaddToCartButton=By.cssSelector("");
    public By addToCartSuccessMsg=By.cssSelector("div.message-success.success.message>div");
    public final By firstBuyableProduct=By.xpath("//button[@class='action tocart primary']/ancestor::div[@class='product details product-item-details']//a[@class='product-item-link']");
    private static final By CUSTOMISABLE_ICON = By.cssSelector("a.product.hyper-plus-customisable");
	private static final By PLUS_ICON = By.cssSelector("span.icon-plus");
	private static final By PRODUCT_IMAGE = By.cssSelector("span.product-image-wrapper");
	private static final By PRODUCT_ITEM = By.cssSelector("div.products.wrapper.grid.products-grid ol:nth-child(2) li.item.product.product-item");
	private static final By PRODUCT_ITEM_LINK = By.cssSelector("a.product-item-link");
	private static final By PRODUCT_ITEM_ADD_LINK = By.cssSelector("#slick-slide10 > div > li > div > div > div > div > div > form > div > button");
	public static final  By PRODUCT_TITLE = By.cssSelector(".base");
	private static final By SIMPLE_PRODUCT_BUTTON = By.cssSelector("button.action.tocart.primary");
	private static final By CONFIGURABLE_PRODUCT_BUTTON = By.cssSelector("a.action.tocart.primary");
    private List<WebElement> products;
	private static final By ADDED_SUCCESSFULLY_MSG = By.cssSelector("div.bat-productlistings-card-message.success");
	private static final By BUNDLE_ADDED_SUCCESSFULLY_MSG = By.cssSelector("div.cart-notification.success");
	private static final By PLP_ADD_TO_CART_BUTTON = By.cssSelector(" div.bat-productlistings-card-btn-container > div > button");
	private static final By PDP_BUNDLE_ADD_TO_CART_BUTTON = By.cssSelector("div.bat-producthero-configurator-buttons>div:nth-child(2)>button");
	private static final By PLP_CUSTOMIZE_BUNDLE = By.cssSelector("div.bat-productlistings-card-buttons>a");
	private static final By PLP_PRODUCT_PRICE = By.cssSelector("div.bat-productlistings-card-prices>span:last-child");
	private static final By PDP_BUNDLE_PRICE = By.cssSelector("div.bat-producthero-configurator-price.formatPrice.discountPrice");
	private static final By PDP_BUNDLE_OPTIONS = By.cssSelector("button.dropdown-toggle.js-dropdown");
	private static final By PDP_BUNDLE_DROPDOWN_OPTION = By.cssSelector("div.dropdown-menu.js-dropdown-menu>a.dropdown-item");
	private static final By PDP_BUNDLE_DROPDOWN_OPTION2 = By.cssSelector("div.bat-producthero-bundle-options:nth-child(2)>div.select-wrapper>div>div.dropdown-menu.js-dropdown-menu>a");
	private static final By PDP_BUNDLE_NAME = By.cssSelector("div.bat-producthero-configurator>div>h3");
	private static final By PLP_PRODUCT_NAME = By.cssSelector(".bat-productlistings-card-name");
	private static final By PLP_PRODUCT_LIST = By.cssSelector("div.bat-productlistings-cards>div.bat-productlistings-card");
	private String main_body_colour, side_body_colour, checkout_main_body_colour, checkout_side_body_colour;
	public static final By PLP_GLO_DROPDOWN = By.cssSelector("div.bat-image.bat-logo-odd>i:last-child");
	public static final By PLP_VELO_DROPDOWN = By.cssSelector("div.bat-image.bat-logo-even>i:nth-child(2)");
	public static final By PLP_HEADER_MENU = By.cssSelector("div.bat-header-menu>div>nav.bat-navigation");
	public static final By GLO_VELO_LOGO = By.cssSelector("div.responsivegrid.bat-header-logo-section>div");
	public static final List<Object> productsAdded = new ArrayList<>();
	public static float plp_sticks_amt, plp_velo_amt, plp_acc_amt, plp_devices_amt, plp_bundle_amt, expectedProductsTotal;
	public By loader = By.cssSelector("div.loading-mask");
	public static final By SUCCESS_MESSAGE = By.cssSelector("div.message.message-success.success");
	private static final By VELO_PRODUCT_ITEM_LINK = By.xpath("//h3[@class='bat-productlistings-card-name']//a[contains(@href,'/velo/velo-ruby-berry')]");
	private static final By VELO_PDP_QTY_PLUS_BUTTON= By.cssSelector("button.bat-quantity-button.bat-quantity-button--plus.button-plus:nth-child(4) > i.far.fa-plus-circle");
	private static final By ADD_TO_CART_BUTTON= By.cssSelector("button.bat-cta-style.button-dark.center span:nth-child(1)");
	private static final By LOYALTY_PRODUCTS_LABEL = By.cssSelector("div[data-loyalty-product='1']");
	private List<WebElement> BOTTOM_COLOR_BAR;
	private static final By COLOR_OPTIONS = By.cssSelector("div.hyper-plus.sliding-panel-sections.desktop-only > div > div.configurator-section.main > div > div > div >div>div>div");
	private static final By OUT_OF_STOCK_MSG = By.cssSelector("#hyper-out-of-stock-message");
	private static final By ADD_TO_CART = By.cssSelector("#product-addtocart-button");
	private static final By GLOIT_SELECT_PRODUCT_COLOUR = By.cssSelector("div[role='listbox'] > div:nth-of-type(1)");
	private static final By GLO_CHECK_CART = By.cssSelector(".link.viewcart > span");


	public void ClickonAddToCartButton() {
		waitForExpectedElement(PLPaddToCartButton, 10);
		LOG.info("stuck here ");
		clickByElementByQueryJSExecutor(PLPaddToCartButton);
	}

	public void selectFirstResult() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
			if(UrlBuilder.isDesktop()){
				waitForExpectedElement(PRODUCT_ITEM_ADD_LINK, 20);
				clickFirstElementByQueryJSExecutor(PRODUCT_ITEM_ADD_LINK);}
			else{
				waitForExpectedElement(PRODUCT_ITEM_LINK, 20);
				clickFirstElementByQueryJSExecutor(PRODUCT_ITEM_LINK);}
			break;
			case "glojp":
				clickByElementByQueryJSExecutor(HeaderPage.HEADER_REDIRECT_LINKS);
				clickByElementByQueryJSExecutor(PLP_ADD_TO_CART_BUTTON);
				isElementPresent(ADDED_SUCCESSFULLY_MSG,20);
				break;
			case "kz":
				waitForExpectedElement(PRODUCT_ITEM_LINK, 10);
				waitForExpectedElements(PRODUCT_ITEM_LINK).get(0).click();
				break;
			default:
				waitForExpectedElement(PRODUCT_ITEM_LINK, 20);
				clickFirstElementByQueryJSExecutor(PRODUCT_ITEM_LINK);
		}
	}

	public void selectProductByName() {
		clickUsingJS(webDriver.findElement(By.xpath("//a[contains(text(), '"+UrlBuilder.getMessage("relatedUpSellProducts.key")+"')]")));
	}

	public void selectFirstBuyableProduct() {
		//workaround for glo it,as the first product is not available on pdp
		List<WebElement> productList = visibilityOfAllElementsLocatedBy(firstBuyableProduct);
		switch (UrlBuilder.getLocale()) {
			case "it":
				clickUsingJS(productList.get(productList.size()-3));
				break;
			default:
				clickUsingJS(productList.get(4));
		}
	}

	public void selectFirstBuyableProductForLoyalty() {
		List<WebElement> productList = visibilityOfAllElementsLocatedBy(firstBuyableProduct);
				clickUsingJS(productList.get(productList.size()-1));
	}

	public boolean hyperPlusProductIsDisplayed(String productType) {
		products = getWebDriver().findElements(PRODUCT_ITEM);
		for (WebElement product : products) {
			if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier"))) {
				if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("addToCartButtonText")) && productType.equals("simple")
						|| product.getText().toLowerCase().contains(UrlBuilder.getMessage("configureButtonText")) && productType.equals("configurable")) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hyperPlusProductsHaveIcon() {
		products = getWebDriver().findElements(PRODUCT_ITEM);
		for (WebElement product: products) {
			if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier")) && !product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier") + " bundle") ) {
				if (!product.findElement(CUSTOMISABLE_ICON).isDisplayed()) {
					return false;
				}
			}
		}
		return true;
	}

	public void addHyperPlusPlpProductToCart() {
		selectFirstHyperPlusResult();
		clickByElementByQueryJSExecutor(GLOIT_SELECT_PRODUCT_COLOUR);
		scrollElementIntoView(ADD_TO_CART);
		clickByElementByQueryJSExecutor(ADD_TO_CART);
		clickByElementByQueryJSExecutor(GLO_CHECK_CART);
	}

	public void clickOnHyperPlusPlpProductComponent(String productType, String component) {
		By selectedComponent = null;
		if (component.toLowerCase().equals("productimage")) {
			selectedComponent = PRODUCT_IMAGE;
		} else if ( component.toLowerCase().equals("productitemlink")) {
			selectedComponent = PRODUCT_ITEM_LINK;
		} else if (component.toLowerCase().equals("productbutton")) {
			if (productType.equals("simple")) {
			selectedComponent = SIMPLE_PRODUCT_BUTTON; } else {
				selectedComponent = CONFIGURABLE_PRODUCT_BUTTON;
			}
		} else {
			assertThat(true).as("ERROR: invalid PLP component "+component+" supplied").isFalse();
		}
		waitForExpectedElement(PRODUCT_ITEM);
		products = getWebDriver().findElements(PRODUCT_ITEM);
		for (WebElement product: products) {
			LOG.info(product.getText());
			if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier"))) {
				if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("addToCartButtonText")) && productType.equals("simple")) {
						waitForExpectedElement(selectedComponent);
						product.findElement(selectedComponent).click();
						break;
				} else if (product.getText().toLowerCase().contains(UrlBuilder.getMessage("configureButtonText")) && productType.equals("configurable")) {
					waitForExpectedElement(selectedComponent,20);
					product.findElement(selectedComponent).click();
					break;
				}
			}
		}

	}

	public boolean assertLoyaltyOnlyConfigProduct(){
		return isElementDisplayedBySeconds(LOYALTY_PRODUCTS_LABEL,10);
	}

	public void selectColorInLoyaltyOnlyConfigProduct(){
		isElementDisplayedBySeconds(COLOR_OPTIONS,10);
		BOTTOM_COLOR_BAR = getWebDriver().findElements(COLOR_OPTIONS);
			for(WebElement element : BOTTOM_COLOR_BAR){
				if(element.getAttribute("class").contains("active")){
					continue;
				}
				if(!isElementClickable(ADD_TO_CART) && isElementDisplayedBySeconds(OUT_OF_STOCK_MSG,5)){
					element.click();
					break;
				}

			}
		 waitAndClickByElementByJSExecutor(ADD_TO_CART,10);
	}

	public boolean isConfiguratorPageDisplayed() {
		waitForAjaxElementNotToBePresent(getWebDriver(), 10);
		return getWebDriver().findElement(PLUS_ICON).isDisplayed();
	}

	public void selectFirstNonHyperPlusResult() {
		products = getWebDriver().findElements(PRODUCT_ITEM);
		for (WebElement product : products) {
			if (!(product.getText().length() >0) && !product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier"))) {
				product.findElement(PRODUCT_IMAGE).click();
				break;
			}
		}
	}

	public void selectFirstHyperPlusResult() {
		products = getWebDriver().findElements(PRODUCT_ITEM);
		for (WebElement product : products) {
			if ((product.getText().length() >0) && product.getText().toLowerCase().contains(UrlBuilder.getMessage("hyperPlusNameIdentifier"))) {
				product.findElement(PRODUCT_IMAGE).click();
				break;
			}
		}
	}
	public void clickOnGloDropdown(){
		waitForExpectedElement(PLP_GLO_DROPDOWN,30);
		clickByElementByQueryJSExecutor(PLP_GLO_DROPDOWN);
		waitForExpectedElement(PLP_HEADER_MENU);
	}
	public void clickOnVeloDropdown(){
		waitForExpectedElement(PLP_VELO_DROPDOWN,30);
		clickByElementByQueryJSExecutor(PLP_VELO_DROPDOWN);
		waitForExpectedElement(PLP_HEADER_MENU);
	}

	public void addProductsToBasket(DataTable table) {
		productsAdded.clear();
		List<List<String>> lists = table.asLists(String.class);
		for (List<String> list : lists) {
			switch (list.get(0)) {
				case "accessories.key":
					clickOnGloDropdown();
					waitForItemToBeClickableAndClick(getWebDriver(),10,By.linkText(getMessage(list.get(0))));
					addAccessoriesProduct();
					break;
				case "bundle.key":
					clickOnGloDropdown();
					waitForItemToBeClickableAndClick(getWebDriver(), 20, By.linkText(getMessage(list.get(0))));
					addBundleProduct();
					break;
				case "velo.key":
					clickOnVeloDropdown();
					waitForItemToBeClickableAndClick(getWebDriver(), 20, By.linkText(getMessage(list.get(0))));
					addVeloProduct();
					break;
				case "devices.key":
					clickOnGloDropdown();
					waitForItemToBeClickableAndClick(getWebDriver(), 20, By.linkText(getMessage(list.get(0))));
					addDevicesProduct();
					break;
				case "sticks.key":
					clickOnGloDropdown();
					waitForItemToBeClickableAndClick(getWebDriver(), 20, By.linkText(getMessage(list.get(0))));
					addSticksProduct();
					break;
				default:
					break;
			}
		}
		expectedProductsTotal();
	}

	public void expectedProductsTotal() {
		expectedProductsTotal = plp_acc_amt + plp_devices_amt + plp_sticks_amt + plp_velo_amt + plp_bundle_amt;
	}

	public void addAccessoriesProduct() {
		try {
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,5);
		} catch (Exception e) {
			webDriver.navigate().refresh();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		if (!isElementDisplayedBySeconds(PLP_PRODUCT_LIST, 5)) {
			refreshBrowser();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		waitForExpectedElement(PLP_PRODUCT_LIST,20).isDisplayed();
		updateDriverWaitTimeTo(20);
		plp_acc_amt = 0;
		List<WebElement> productLists = waitForExpectedElements(PLP_PRODUCT_LIST);
		for (WebElement ele : productLists) {
			try {
				if (ele.findElement(PLP_ADD_TO_CART_BUTTON).isDisplayed()) {
					String plp_price1 = ele.findElement(PLP_PRODUCT_PRICE).getText();
					String[] plp_price = ele.findElement(PLP_PRODUCT_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
					plp_acc_amt = Float.parseFloat(plp_price[0].replaceAll(",", ""));
					productsAdded.add(Arrays.asList(ele.findElement(PLP_PRODUCT_NAME).getText(), plp_price1, "1"));
					ele.findElement(PLP_ADD_TO_CART_BUTTON).click();
					isElementDisplayedBySeconds(ADDED_SUCCESSFULLY_MSG,20);
					break;
				}
			} catch (NoSuchElementException e) {
				LOG.info("Product is out of stock so checking for the next product");
			}
		}
	}

	public void addBundleProduct() {
		waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,20);
		if (!isElementDisplayedBySeconds(PLP_PRODUCT_LIST, 5)) {
			getWebDriver().navigate().refresh();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		updateDriverWaitTimeTo(20);
		waitForExpectedElement(PLP_PRODUCT_LIST,20).isDisplayed();
		plp_bundle_amt = 0;
		List<WebElement> productLists = waitForExpectedElements(PLP_PRODUCT_LIST);
		for (WebElement ele : productLists) {
			try {
				if (ele.findElement(PLP_CUSTOMIZE_BUNDLE).isDisplayed()) {
					waitForExpectedElement(PLP_CUSTOMIZE_BUNDLE);
					ele.findElement(PLP_CUSTOMIZE_BUNDLE).click();
					waitForExpectedElement(PDP_BUNDLE_PRICE);
					break;
				}
			} catch (NoSuchElementException e) {
				LOG.info("Products are out of stock");
			}
		}
		String bundle_name = waitForExpectedElement(PDP_BUNDLE_NAME).getText();
		String plp_price1 = waitForExpectedElement(PDP_BUNDLE_PRICE).getText();
		String[] plp_price = waitForExpectedElement(PDP_BUNDLE_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
		plp_bundle_amt = Float.parseFloat(plp_price[0].replaceAll(",", ""));
		selectBundleOptions();
		waitForExpectedElement(PDP_BUNDLE_ADD_TO_CART_BUTTON).click();
		productsAdded.add(Arrays.asList(bundle_name, plp_price1, "1"));
		isElementPresent(BUNDLE_ADDED_SUCCESSFULLY_MSG,20);
	}

	public void selectBundleOptions() {
		int index = 0;
		clickByElementByQueryJSExecutor(PDP_BUNDLE_OPTIONS);
		List<WebElement> productLists = presenceOfAllElementsLocatedBy(PDP_BUNDLE_DROPDOWN_OPTION);
		for (WebElement product : productLists) {
			if (!product.getAttribute("class").contains("disabled")) {
				clickIndexElementByQueryJSExecutor(PDP_BUNDLE_DROPDOWN_OPTION, index);
				break;
			}
			index++;
		}
		clickIndexElementByQueryJSExecutor(PDP_BUNDLE_OPTIONS, 1);
		clickIndexElementByQueryJSExecutor(PDP_BUNDLE_DROPDOWN_OPTION2, 1);
	}

	public void addDevicesProduct() {
		waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,20);
		if (!isElementDisplayedBySeconds(PLP_PRODUCT_LIST, 5)) {
			getWebDriver().navigate().refresh();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		updateDriverWaitTimeTo(20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PLP_PRODUCT_LIST));
		waitForExpectedElement(PLP_PRODUCT_LIST).isDisplayed();
		List<WebElement> productLists = waitForExpectedElements(PLP_PRODUCT_LIST);
		plp_devices_amt = 0;
		for (WebElement ele : productLists) {
			try {
				if (ele.findElement(PLP_ADD_TO_CART_BUTTON).isDisplayed()) {
					String plp_price1 = ele.findElement(PLP_PRODUCT_PRICE).getText();
					String[] plp_price = ele.findElement(PLP_PRODUCT_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
					plp_devices_amt = Float.parseFloat(plp_price[0].replaceAll(",", ""));
					productsAdded.add(Arrays.asList(ele.findElement(PLP_PRODUCT_NAME).getText(), plp_price1, "1"));
					ele.findElement(PLP_ADD_TO_CART_BUTTON).click();
					isElementPresent(ADDED_SUCCESSFULLY_MSG,20);
					break;
				}
			} catch (NoSuchElementException e) {
				LOG.info("Product is out of stock so checking for the next product");
			}
		}
	}

	public void addSticksProduct() {
		waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,20);
		if (!isElementDisplayedBySeconds(PLP_PRODUCT_LIST, 5)) {
			getWebDriver().navigate().refresh();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		updateDriverWaitTimeTo(20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PLP_PRODUCT_LIST));
		waitForExpectedElement(PLP_PRODUCT_LIST).isDisplayed();
		List<WebElement> productLists = waitForExpectedElements(PLP_PRODUCT_LIST);
		plp_sticks_amt = 0;
		for (WebElement ele : productLists) {
			try {
				if (ele.findElement(PLP_ADD_TO_CART_BUTTON).isDisplayed()) {
					String plp_price1 = ele.findElement(PLP_PRODUCT_PRICE).getText();
					String[] plp_price = ele.findElement(PLP_PRODUCT_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
					plp_sticks_amt = Float.parseFloat(plp_price[0].replaceAll(",", ""));
					productsAdded.add(Arrays.asList(ele.findElement(PLP_PRODUCT_NAME).getText(), plp_price1, "1"));
					ele.findElement(PLP_ADD_TO_CART_BUTTON).click();
					isElementPresent(ADDED_SUCCESSFULLY_MSG,20);
					break;
				}
			} catch (NoSuchElementException e) {
				LOG.info("Product is out of stock so checking for the next product");
			}
		}
	}

	public void addVeloProduct() {
		waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,20);
		if (!isElementDisplayedBySeconds(PLP_PRODUCT_LIST, 5)) {
			getWebDriver().navigate().refresh();
			waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,30);
		}
		updateDriverWaitTimeTo(20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PLP_PRODUCT_LIST));
		waitForExpectedElement(PLP_PRODUCT_LIST).isDisplayed();
		List<WebElement> productLists = waitForExpectedElements(PLP_PRODUCT_LIST);
		plp_velo_amt = 0;
		for (WebElement ele : productLists) {
			try {
				if (ele.findElement(PLP_ADD_TO_CART_BUTTON).isDisplayed()) {
					String plp_price1 = ele.findElement(PLP_PRODUCT_PRICE).getText();
					String[] plp_price = ele.findElement(PLP_PRODUCT_PRICE).getText().split(getMessage("JapaneseCurrencySymbol.key"));
					plp_velo_amt = Float.parseFloat(plp_price[0].replaceAll(",", ""));
					productsAdded.add(Arrays.asList(ele.findElement(PLP_PRODUCT_NAME).getText(), plp_price1, "1"));
					ele.findElement(PLP_ADD_TO_CART_BUTTON).click();
					isElementPresent(ADDED_SUCCESSFULLY_MSG,20);
					break;
				}
			} catch (NoSuchElementException e) {
				LOG.info("Product is out of stock so checking for the next product");
			}
		}
	}
	public void waitForLoaderToDisappear() {
		try {
			waitForElementToDisappear(loader, 30);
		} catch (Exception e) {
			try {
				refreshBrowser();
				waitForElementToDisappear(loader,30);
			} catch (Exception exception) {
				refreshBrowser();
				waitForElementToDisappear(loader,30);
			}
		}
		waitForAjaxElementNotToBePresent(getWebDriver(),5);
	}
	public void clickOnAddToCartButton() {
		waitForItemToBeClickableAndClick(PLP_ADD_TO_CART_BUTTON);
		waitForExpectedElement(SUCCESS_MESSAGE);
	}

	public void clickOnProductTileNavigateToPDPAndAddQuantityToCart(String strProductCategory,String strQuantity) {
		switch (strProductCategory){
			case "velo.key":
				clickOnVeloDropdown();
				waitForItemToBeClickableAndClick(getWebDriver(), 20, By.linkText(getMessage(strProductCategory)));
				waitForItemToBeClickableAndClick(VELO_PRODUCT_ITEM_LINK);
				addVeloProductWithSpecifiedQuantity(strQuantity);
				break;
			default:
		}
	}

	public void addVeloProductWithSpecifiedQuantity(String strQuantity) {
		waitForElementToDisappear(OrderHistoryPage.BASKET_LOADER,20);
		for (int i=0;i<Integer.parseInt(strQuantity);i++) {
			waitForExpectedElement(VELO_PDP_QTY_PLUS_BUTTON,2);
			clickUsingJS(VELO_PDP_QTY_PLUS_BUTTON);
		}
		clickUsingJS(ADD_TO_CART_BUTTON);
		waitForAjaxElementNotToBePresent(getWebDriver(),5);
	}
}
