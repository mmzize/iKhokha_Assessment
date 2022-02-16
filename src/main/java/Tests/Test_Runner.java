package Tests;

import Functionality.Hits_Per_Day;
import Functionality.Home_Page;
import Helpers.JavaScriptExecutor;
import Helpers.Take_Screenshot;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Test_Runner {

    private WebDriver driver =Setup.Setup_Class.startBrowserOfChoice("http://www.distrowatch.com", "chrome");

    Home_Page home_page = PageFactory.initElements(driver,Home_Page.class);
    Hits_Per_Day hits=PageFactory.initElements(driver,Hits_Per_Day.class);
    Take_Screenshot screenshot=new Take_Screenshot();
    JavaScriptExecutor scriptExecutor = new JavaScriptExecutor();


    @Test(description = "Verify Home Page Element")
    public void Home_Page_Items_Tests() throws InterruptedException {
        home_page.Verify_Home_Page_Items();
        screenshot.takeSnapShot(driver,"Home_Page_Items_Tests");
    }
    @Test(priority =1,description = "Select A Period To Filter By")
    public void Select_Option_To_Filter_By_Tests() throws InterruptedException {

        scriptExecutor.Scroll(driver);
        home_page.Select_Year("Year 2005");
        screenshot.takeSnapShot(driver,"Select_Option_To_Filter_By_Tests");
    }
    @Test(priority = 2,description = "Apply Filter By Clicking Go Button And Verify Top 5")
    public void Verify_Top_Five_Tests() throws InterruptedException {
        home_page.Click_Go();
        scriptExecutor.Scroll(driver);
        home_page.Check_Top_Five("Ubuntu","Mandriva","SUSE","Fedora","MEPIS");
        screenshot.takeSnapShot(driver,"Verify_Top_Five_Tests");
    }
    @Test(priority = 3,description = "Loop Through The Table For Last 6 Months And Check Indicator")
    public void Get_HPD_Indicator_Tests() throws InterruptedException {
        home_page.Select_Year("Last 6 months");
        home_page.Click_Go();
        hits.Get_HPD_Indicator();
    }
}
