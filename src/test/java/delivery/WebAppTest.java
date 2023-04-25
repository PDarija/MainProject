package delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WebAppTest {

    @BeforeEach
    public void setUp() {
        open("http://51.250.6.164:3000/signin");
    }

    @AfterEach
    public void tearDown(){
        closeWebDriver();
    }


    @Test

    public void incorrectLogin() {

//       Configuration.browser="firefox" ;
        Configuration.holdBrowserOpen = true;
//        open("http://51.250.6.164:3000/signin");
//        SelenideElement usernameInput = $(By.xpath("//input[@data-name ='username-input']"));
//        SelenideElement passwordInput = $(By.xpath("//input[@data-name ='password-input'] "));
//        usernameInput.setValue("Hello");
//        passwordInput.setValue("123456789");
//        $(By.xpath("//button[@data-name ='signIn-button']")).click();
//        $(By.xpath("//div[@data-name = 'authorizationError-popup'] ")).shouldBe(Condition.exist);
//        $(By.xpath("//div[@data-name = 'authorizationError-popup'] ")).shouldBe(Condition.visible);
//        $(By.xpath("//button[@data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.exist);
//        $(By.xpath("//button[@data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.visible);
        LoginPage loginPage = new LoginPage();
        loginPage.insertLogin("1616");
        loginPage.insertPassword("464622222");
        loginPage.clickSignInButton();
        loginPage.checkAuthorizationErrorPopup();
        loginPage.checkAuthorizationErrorPopupCloseButton();
        sleep(1500);
    }

    @Test

    public void correctLogin() {
        Configuration.holdBrowserOpen = true;
//        Configuration.headless = true; чтобы тесты шли в фоновом режиме и окно не всплывало
//        open("http://51.250.6.164:3000/signin");
//        SelenideElement usernameInput = $(By.xpath("//input[@data-name ='username-input']"));
//        SelenideElement passwordInput = $(By.xpath("//input[@data-name ='password-input'] "));
//
//        usernameInput.setValue("darijapl");
//        passwordInput.setValue("hellouser123");
//        $(By.xpath("//button[@data-name ='signIn-button']")).click();
////        $(By.xpath("//input[@data-name='username-input'] ")).shouldBe(Condition.exist);
////        $(By.xpath("//input[@data-name='username-input']")).shouldBe(Condition.visible);
//        $(By.xpath("//input[@data-name='phone-input']")).shouldBe(Condition.exist);
//        $(By.xpath("//input[@data-name='phone-input']")).shouldBe(Condition.visible);
//        $(By.xpath("//input[@data-name='comment-input'] ")).shouldBe(Condition.exist);
//        $(By.xpath("//input[@data-name='comment-input']")).shouldBe(Condition.visible);
//        $(By.xpath("//button[@data-name='createOrder-button']")).shouldBe(Condition.visible);

        LoginPage loginPage = new LoginPage();
        loginPage.insertLogin("darijapl");
        loginPage.insertPassword("hellouser123");
        loginPage.clickSignInButton();
        loginPage.checkNameField();
        loginPage.checkSurnameField();
        loginPage.checkCommentField();
        loginPage.checkOrderButton();
        sleep(1500);
    }

    @Test
    public void incorrectLoginAndCheckPopupPageObject(){
        LoginPage loginPage = new LoginPage();
        loginPage.insertLogin("1616");
        loginPage.checkSignInDisabled();
    }

}

