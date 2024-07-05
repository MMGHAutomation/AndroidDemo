package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class ExpandableList extends BasePage{

    By people_Names = By.xpath(" //android.widget.TextView[@text='People Names']");
    By customerAdapter = By.xpath(" //android.widget.TextView[@content-desc='1. Custom Adapter']");
    By simpleAdapter = By.xpath("//android.widget.TextView[@content-desc='3. Simple Adapter']");
    By group19 = By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Group 19']");
    //android.widget.TextView[@resource-id="android:id/text1" and @text="Group 11"]
    By scrollStartElement = By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Group 11']");
    By scrollEndElement = By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Group 4']");
    By group10 = By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Group 10']");
    public ExpandableList(AppiumDriver driver) {
        super(driver);
    }
    public void openSimpleAdapter(){
        waitAndTap(simpleAdapter);
    }
    public void openCustomAdapter(){
        waitAndTap(customerAdapter);
    }
    public void scrollToGroup19(){
        scrolling(scrollStartElement, scrollEndElement);
    }
    public void getBackToToGroup1(){
        scrolling(group10, group19);
    }
    public void longPressPeopleNames(){
       longPress(people_Names);
    }
    public void tapPeopleNames(){
        tap(people_Names);
    }
}
