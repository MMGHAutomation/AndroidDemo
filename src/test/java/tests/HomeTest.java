package tests;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utilities.StaticThreadLocal;


public class HomeTest extends TestBase {

	Home home;
	Animation animation;
	Shake shake;
	ExpandableList expandableList;
	DragAndDrop dragAndDrop;
	@BeforeClass(alwaysRun = true)
	public void initialize(){
		log = LogManager.getLogger(this.getClass());
		System.out.println("inside before class");
		home = new Home(StaticThreadLocal.getAppiumDriver());
		animation = new Animation(StaticThreadLocal.getAppiumDriver());
		shake = new Shake(StaticThreadLocal.getAppiumDriver());
		expandableList = new ExpandableList(StaticThreadLocal.getAppiumDriver());
		dragAndDrop = new DragAndDrop(StaticThreadLocal.getAppiumDriver());
	}

	@Test(priority = 1, testName = "Home : Check fashion",
			suiteName = "Home", groups = {"Home"},
			description="description test",enabled = false)
	public void checkFashionTitle(){
		StaticThreadLocal.setExtentTestInfo("Open views");
		home.clickOnView();
		animation.openAnimation();
		shake.openShake();
		shake.addText_Shake("ABCD");
		shake.tapOnLogin();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Assert.assertTrue(true);
	}

	@Test(priority = 2, testName = "Scroll : Scrolling to group 19",
			suiteName = "Scrolling", groups = {"Scrolling"},
			description="Scrolling to group 19", enabled = false)
	public void scroll(){
		StaticThreadLocal.setExtentTestInfo("Open views");
		home.clickOnView();
		StaticThreadLocal.setExtentTestInfo("Open expandable list view");
		animation.openExpandableViews();
		StaticThreadLocal.setExtentTestInfo("Open simple adapter");
		expandableList.openSimpleAdapter();
		StaticThreadLocal.setExtentTestInfo("Scroll to group 19");
		//expandableList.jsscrollToGroup19();
		expandableList.scrollToGroup19();
		StaticThreadLocal.setExtentTestInfo("swipe up back to group 1");
		expandableList.getBackToToGroup1();
		StaticThreadLocal.setExtentTestInfo("swipe down to group 19");
		expandableList.scrollToGroup19();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Assert.assertTrue(true);
	}
	@Test(priority = 3, testName = "Scroll : long press",
			suiteName = "Scrolling", groups = {"Scrolling"},
			description="long press", enabled = false)
	public void longPress(){
		StaticThreadLocal.setExtentTestInfo("Open views");
		home.clickOnView();
		StaticThreadLocal.setExtentTestInfo("Open expandable list view");
		animation.openExpandableViews();
		StaticThreadLocal.setExtentTestInfo("Open custom adapter");
		expandableList.openCustomAdapter();
		StaticThreadLocal.setExtentTestInfo("long Press Names");
		expandableList.longPressPeopleNames();

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Assert.assertTrue(true);
	}
	@Test(priority = 4, testName = "Scroll : drag and drop",
			suiteName = "dragAndDrop", groups = {"drag and drop"},
			description="drag and drop", enabled = true)
	public void dragAndDrop(){
		StaticThreadLocal.setExtentTestInfo("Open views");
		home.clickOnView();
		StaticThreadLocal.setExtentTestInfo("Open Drag and drop");
		dragAndDrop.openDragAndDrop();
		StaticThreadLocal.setExtentTestInfo("drag first circle and drop it on the second");
		dragAndDrop.dragFirstCircleToTheSecond();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Assert.assertTrue(true);
	}
}
