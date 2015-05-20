package com.web.pages;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.generic.actions.IWebAction;
import com.generic.actions.SyncWebWait;
import com.generic.actions.WebActions;
import com.generic.exceptions.WaitException;
import com.generic.page.base.WebPageBase;
import com.web.locators.HomePageLocators;

public class HomePage extends WebPageBase implements HomePageLocators {

    public HomePage(WebDriver driver) throws WaitException {
        super(driver);
        org.junit.Assert.assertTrue(webActions.getVisibiltyOfElementLocatedBy(BYLOGIN));
    }

    public HomePage enterLastName(String lastNameVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, LASTNAME, lastNameVal);
        return this;
    }

    public HomePage enterFirstName(String firstNameVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, FIRSTNAME, firstNameVal);
        return this;
    }

    public HomePage enterAddress1(String address1Val) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, ADDRESS1, address1Val);
        return this;
    }

    public HomePage enterAddress2(String address2Val) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, ADDRESS2, address2Val);
        return this;
    }

    public HomePage enterCity(String cityVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, CITY, cityVal);
        return this;
    }

    public HomePage enterState(String stateVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, STATE, stateVal);
        return this;
    }

    public HomePage enterZip(String zipVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, ZIP, zipVal);
        return this;
    }

    public HomePage selectUnderGradProgOfInterest(String underGradProgOfInterestVal)
            throws TimeoutException, WaitException {
        webActions.selectFromDropDown(VISIBILITY, UNDERGRADPROGOFINTEREST,
                underGradProgOfInterestVal);
        return this;
    }

    public HomePage selectUnderGradCertOfInterest(String underGradCertOfInterestVal)
            throws TimeoutException, WaitException {
        webActions.selectFromDropDown(VISIBILITY, UNDERGRADCERTOFINTEREST,
                underGradCertOfInterestVal);
        return this;
    }

    public HomePage selectGradProgOfInterest(String gradProgOfInterestVal) throws TimeoutException,
            WaitException {
        webActions.selectFromDropDown(VISIBILITY, GRADPROGOFINTEREST, gradProgOfInterestVal);
        return this;
    }

    public HomePage selectGradCertOfInterest(String gradCertOfInterestVal) throws TimeoutException,
            WaitException {
        webActions.selectFromDropDown(VISIBILITY, GRADCERTOFINTEREST, gradCertOfInterestVal);
        return this;
    }

    public HomePage enterPhoneAreaCode(String phAreadCodeVal) throws TimeoutException,
            WaitException {
        webActions.enterText(VISIBILITY, PHAREADCODE, phAreadCodeVal);
        return this;
    }

    public HomePage enterPhoneFirstThreeDigits(String phFirstThreeVal) throws TimeoutException,
            WaitException {
        webActions.enterText(VISIBILITY, PHFIRSTTHREE, phFirstThreeVal);
        return this;
    }

    public HomePage enterPhoneLastFourDigits(String phLastFourVal) throws TimeoutException,
            WaitException {
        webActions.enterText(VISIBILITY, PHLASTFOUR, phLastFourVal);
        return this;
    }

    public HomePage enterEmail(String emailVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, EMAIL, emailVal);
        return this;
    }

    public HomePage enterVerifyEmail(String verifyEmailVal) throws TimeoutException, WaitException {
        webActions.enterText(VISIBILITY, VERIFYEMAIL, verifyEmailVal);
        return this;
    }
  
    public void login(String username, String password) throws TimeoutException, WaitException,
            InterruptedException {
        webActions.click(VISIBILITY, BYLOGIN);
        webActions.enterText(VISIBILITY, BYUSERNAME, username);
        webActions.enterText(PRESENCE, BYPASSWORD, password);
        Thread.sleep(5000);
        webActions.click(VISIBILITY, BYLOGINBUTTON);
    }

    public void addItemToCart() throws TimeoutException, WaitException {
        webActions.click(VISIBILITY, FIRSTDAILYOFFERITEM);
    }

    public void clickOnBuyNow() throws TimeoutException, WaitException {
    	/*WebActions webAction = new SyncWebWait(webActions);
        webAction.click(VISIBILITY, BUYNOWBUTTON);*/
    }
}
