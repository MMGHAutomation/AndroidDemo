package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class DragAndDrop extends BasePage{

    By dragAndDrop = By.xpath("//android.widget.TextView[@content-desc='Drag and Drop']");
    By firstElement = By.id("io.appium.android.apis:id/drag_dot_1");
    By secondElement = By.id("io.appium.android.apis:id/drag_dot_2");
    public DragAndDrop(AppiumDriver driver) {
        super(driver);
    }
    public void openDragAndDrop(){
        waitAndTap(dragAndDrop);
    }
    public void dragFirstCircleToTheSecond(){
        dragAndDrop(firstElement, secondElement);
    }
}
