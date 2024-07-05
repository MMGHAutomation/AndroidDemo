package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Animation extends BasePage{

    By animation = By.xpath("//android.widget.TextView[@content-desc='Animation']");

    By expandableList = By.xpath("   //android.widget.TextView[@content-desc='Expandable Lists']");
    By spinner = By.xpath("//android.widget.TextView[@content-desc='Spinner']");
    By splitTouchesAcrossView = By.xpath("//android.widget.TextView[@content-desc='Splitting Touches across Views']");
    public Animation(AppiumDriver driver) {
        super(driver);
    }
    public void openAnimation(){
        waitAndTap(animation);
    }

    public void openExpandableViews(){
        waitAndTap(expandableList);
    }
    public void openSpinner(){
        waitAndTap(expandableList);
    }
    public void opensplitTouchesAcrossView(){
        waitAndTap(splitTouchesAcrossView);
    }
}
