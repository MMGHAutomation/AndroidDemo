package pages;

import io.appium.java_client.AppiumDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    AndroidDriver anddriver;
    public static final long duration = 15;
    public BasePage(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
    }

    protected void waitAndTap(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }


    protected void hideKeyboard() {
        driver.navigate().back();
    }

    protected List<WebElement> waitAndFindElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    protected void sendKey(By by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }
    protected void clear(By by) {
        waitAndFindElement(by).clear();
    }
    protected String getAttribute(By by, String attribute) {
        return waitAndFindElement(by).getAttribute(attribute);
    }

    protected void jsScrolling(String direction){
        JavascriptExecutor js = driver;
        HashMap<String, String> param = new HashMap<>();
        param.put("direction", direction);
        js.executeScript("mobile: scroll", param);
    }
    protected void tap(By by) {
        WebElement element = waitAndFindElement(by);
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger,1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction( new Pause(finger, Duration.ofMillis(50)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }
    protected void doubleTap(By by) {
        WebElement element = waitAndFindElement(by);
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger,1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction( new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction( new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }
    protected void longPress(By by) {
        WebElement element = waitAndFindElement(by);
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger,1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction( new Pause(finger, Duration.ofSeconds(2)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }
    protected void scrolling(By startPoint, By endPoint){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
       // Start element
        WebElement startElement = waitAndFindElement(startPoint);
        Dimension startSize = startElement.getSize();
        Point startLocation = startElement.getLocation();
        Point centerOfElement = getCenterOfElement(startLocation, startSize);
       //End Element
        WebElement endElement = waitAndFindElement(endPoint);
        Dimension endSize = endElement.getSize();
        Point endLocation = endElement.getLocation();
        Point centerOfEndElement = getCenterOfElement(endLocation, endSize);
        // Prepare Sequence
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerOfElement));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), centerOfEndElement));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // Perform Scroll action
        driver.perform(Arrays.asList(swipe));

    }
    protected void dragAndDrop(By startPoint, By endPoint){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Start element
        WebElement startElement = waitAndFindElement(startPoint);
        Dimension startSize = startElement.getSize();
        Point startLocation = startElement.getLocation();
        Point centerOfElement = getCenterOfElement(startLocation, startSize);
        //End Element
        WebElement endElement = waitAndFindElement(endPoint);
        Dimension endSize = endElement.getSize();
        Point endLocation = endElement.getLocation();
        Point centerOfEndElement = getCenterOfElement(endLocation, endSize);
        // Prepare Sequence
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerOfElement))
        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        .addAction( new Pause(finger, Duration.ofMillis(500)))
        .addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), centerOfEndElement))
        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // Perform Scroll action
        driver.perform(Arrays.asList(swipe));

    }
    private Point getCenterOfElement(Point location, Dimension size){
        int x = location.getX()+size.getWidth()/2;
        int y = location.getY()+size.getHeight()/2;
        return new Point(x,y);
    }

}
