package pages;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class Home extends BasePage{
    //android.widget.TextView[@content-desc="Animation"]
    By views = By.xpath("//android.widget.TextView[@content-desc='Views']");
    By preference = By.xpath("//android.widget.TextView[@content-desc='Preference']");


    By fragment = By.xpath("//android.widget.TextView[@content-desc='7. Fragment']");
    By editTextPreference = By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit text preference']");
    By editFavoriteAnimal = By.xpath("  //android.widget.EditText[@resource-id='android:id/edit']");

    public Home(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnView(){
        waitAndTap(views);
    }
    public void clickOnPreference(){
        waitAndTap(preference);
    }

    public void clickOneditTextPreference(){
        waitAndTap(editTextPreference);
    }
    public void enterFavoriteAnimal(String text){
        sendKey(editFavoriteAnimal,text);
    }
}
