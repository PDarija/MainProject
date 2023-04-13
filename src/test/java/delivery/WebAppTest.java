package delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebAppTest {


    @Test

    public void incorrectLogin(){

//       Configuration.browser="firefox" ;
        Configuration.holdBrowserOpen = true;
       open("http://51.250.6.164:3000/signin");
        SelenideElement usernameInput=$(By.xpath("//input[@data-name ='username-input']"));
        SelenideElement passwordInput=$(By.xpath("//input[@data-name ='password-input'] "));

        usernameInput.setValue("Hello");
        passwordInput.setValue("123456789");

        $(By.xpath("//button[@data-name ='signIn-button']")).click();
        $(By.xpath("//div[@data-name = 'authorizationError-popup'] ")).shouldBe(Condition.exist);
        $(By.xpath("//div[@data-name = 'authorizationError-popup'] ")).shouldBe(Condition.visible);
        $(By.xpath("//button[@data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.exist);
        $(By.xpath("//button[@data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.visible);
    }

    @Test

    public void correctLogin(){
        Configuration.holdBrowserOpen = true;
//        Configuration.headless = true; чтобы тесты шли в фоновом режиме и окно не всплывало
        open("http://51.250.6.164:3000/signin");
        SelenideElement usernameInput=$(By.xpath("//input[@data-name ='username-input']"));
        SelenideElement passwordInput=$(By.xpath("//input[@data-name ='password-input'] "));

        usernameInput.setValue("darijapl");
        passwordInput.setValue("hellouser123");
        $(By.xpath("//button[@data-name ='signIn-button']")).click();
        $(By.xpath("//input[@data-name='username-input'] ")).shouldBe(Condition.exist);
        $(By.xpath("//input[@data-name='username-input']")).shouldBe(Condition.visible);
        $(By.xpath("//input[@data-name='phone-input']")).shouldBe(Condition.exist);
        $(By.xpath("//input[@data-name='phone-input']")).shouldBe(Condition.visible);
        $(By.xpath("//input[@data-name='comment-input'] ")).shouldBe(Condition.exist);
        $(By.xpath("//input[@data-name='comment-input']")).shouldBe(Condition.visible);
        $(By.xpath("//button[@data-name='createOrder-button']")).shouldBe(Condition.visible);
    }

}
