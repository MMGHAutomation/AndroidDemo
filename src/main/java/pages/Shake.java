package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Shake extends BasePage{

    By animation = By.xpath("//android.widget.TextView[@content-desc='Shake']");
    By loginButton = By.xpath("//android.widget.Button[@content-desc='Login']");
    By shakeEditText = By.xpath("//android.widget.EditText[@resource-id='io.appium.android.apis:id/pw']");

    public Shake(AppiumDriver driver) {
        super(driver);
    }
    public void openShake(){
        waitAndTap(animation);
    }
    public void addText_Shake(String text){
      sendKey(shakeEditText, text);
    }
    public void tapOnLogin(){
        waitAndTap(shakeEditText);
    }

}
