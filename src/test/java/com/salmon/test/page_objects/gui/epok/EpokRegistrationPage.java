package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import org.openqa.selenium.By;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.constants.Context.EMAIL_ID;
import static com.salmon.test.page_objects.gui.constants.Context.PASSWORD;

public class EpokRegistrationPage extends PageObject {

	// ELEMENT MAPPING
	  // Tick boxes
	    public By TsAndCs = By.cssSelector("span.checkmark");

	  // Text
	    public By subHeading = By.cssSelector(".legend");

	  // intractable elements
	    public By firstNameInput = By.cssSelector("#firstname");
	    public By surNameInput = By.cssSelector("#lastname");
	    public By DOBInput = By.cssSelector("input#dob"); // format = 08/05/2019
	    public By gender = By.cssSelector("#gender");
	    public By company = By.cssSelector("#company"); // not mandatory
	    public By phoneNumber = By.cssSelector("#phone");
	    public By streetAddressLine1 = By.cssSelector("#street_1");
	    public By city = By.cssSelector("#city");
	    public By postCode = By.cssSelector("#zip");
	    public By country = By.cssSelector("select#country");
	    public By CheckDataButton=By.cssSelector("button.action.submit.primary");
	    public By CompleteRegBtn=By.cssSelector("button.action.submit.primary");

	    // sign in information
	    public By emailaddress = By.cssSelector("#email_address");
	    public By password = By.cssSelector("input#password");

	private String firstNameData = random(6, ALPHABETS);
	private String lastNameData = random(6, ALPHABETS);
	private String emailAddressData = RandomGenerator.randomEmailAddress(6);
	private String passwordText = "M@nish1710";
	private String companyName = random(20, ALPHABETS);
	private String townOrCityData = "Hamburg";
	private String postCodeData = "21307";

	private ScenarioContext scenarioContext;

	// buttons
	public By confirmageButton = By.cssSelector("button.action.submit.primary");

	public EpokRegistrationPage(TestContext testContext) {
		this.scenarioContext = testContext.getScenarioContext();
	}

	public String getPageHeading(){
	      return waitForExpectedElement(subHeading).getText();
	    }

		public void checkTsCs(){
	      clickByElementByQueryJSExecutor(TsAndCs);
	    }

	  public void enterFirstNameAndLastName(){
		  enterText(firstNameInput, firstNameData);
		  enterText(surNameInput, lastNameData);
	  }

	  public void enterEmailAddressAndPassword() {
		  String emailAddressData = this.emailAddressData;
		  String passwordText = this.passwordText;
		  scenarioContext.setContext(EMAIL_ID, emailAddressData);
		  scenarioContext.setContext(PASSWORD, passwordText);
		  enterText(emailaddress, emailAddressData);
		  enterText(password, passwordText);
		}

	  public void enterRegistrationDetails() throws InterruptedException {
		  enterFirstNameAndLastName();
		  enterEmailAddressAndPassword();
		  clickOnConfirmAgeButton();
		  populateAddressFields();
		  enterDOB("regpageValidDOB.key");
		  clickCheckDataButton();
		  enterPhoneNumber("+40431367");
		  completeRegistration();
	  }

	public void clickOnConfirmAgeButton() {
	  	waitForExpectedElement(confirmageButton);
		clickByElementByQueryJSExecutor(confirmageButton);
	}

	public void populateAddressFields() {
		enterText(company, companyName);
		enterText(streetAddressLine1, "12 The Cloisters");
		enterText(city, townOrCityData);
		enterText(postCode, postCodeData);
	}

	public void enterDOB(String DOBToEnter) {
		enterText(DOBInput, DOBToEnter);
		waitForExpectedElement(DOBInput).submit();
	}


	public void clickCheckDataButton() {
		clickByElementByQueryJSExecutor(CheckDataButton);
	}

	public void enterPhoneNumber(String textToEnter) {
		enterText(phoneNumber, textToEnter);
		waitForExpectedElement(phoneNumber).submit();
	}

	public void completeRegistration() throws InterruptedException {
		waitForExpectedElement(TsAndCs, 10);
		clickByElementByQueryJSExecutor(TsAndCs);
		Thread.sleep(3000);
		clickByElementByQueryJSExecutor(CompleteRegBtn);
	}
}

	
	


