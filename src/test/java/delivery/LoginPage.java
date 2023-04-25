package delivery;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static void insertLogin(String query){
        $(By.xpath("//input[@data-name='username-input'] ")).setValue(query);
    }

    public static void insertPassword(String query){
        $(By.xpath("//input[@data-name ='password-input'] ")).setValue(query);
    }

    public void checkSignInDisabled(){
        $(By.xpath("//*[@data-name=\"signIn-button\"] ")).shouldBe(Condition.disabled);
    }
    public static void clickSignInButton(){

    $(By.xpath("//button[@data-name ='signIn-button']")).click();
    }
    public void checkAuthorizationErrorPopup() {

        $(By.xpath("//div[@data-name = 'authorizationError-popup'] ")).shouldBe(Condition.exist);
    }
    public void checkAuthorizationErrorPopupCloseButton() {
        $(By.xpath("//button[@data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.exist);
    }

    public static void checkNameField(){
        $(By.xpath("//input[@data-name='username-input']")).shouldBe(Condition.exist);
    }
    public static void checkSurnameField(){
        $(By.xpath("//input[@data-name='phone-input']")).shouldBe(Condition.exist);
    }

    public static void checkCommentField(){
        $(By.xpath("//input[@data-name='comment-input']")).shouldBe(Condition.exist);
    }
    public static void checkOrderButton(){
        $(By.xpath("//button[@data-name='createOrder-button']")).shouldBe(Condition.exist);
    }




}
