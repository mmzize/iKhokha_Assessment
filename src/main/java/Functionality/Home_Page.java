package Functionality;

import Helpers.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Home_Page {

    private WebDriver driver;

    public Home_Page(WebDriver driver)
    {
        this.driver=driver;
    }

    @FindBy(xpath = "//th[@colspan='3'][contains(.,'Page Hit Ranking')]")
    WebElement Home_Page_Linux_Distro;
    @FindBy(xpath = "//select[contains(@name,'dataspan')]")
    WebElement Data_Span_Dropdown;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[2]/td/form/input")
    WebElement Go_Button;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[4]/td[1]")
    WebElement Rank_One;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[5]/td[1]")
    WebElement Rank_Two;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[6]/td[1]")
    WebElement Rank_Three;
    @FindBy (xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[7]/td[1]")
    WebElement Rank_Four;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr[8]/td[1]")
    WebElement Rank_Five;

    public void Verify_Home_Page_Items() throws InterruptedException {

        WebDriverWait home=new WebDriverWait(driver,20);
        home.until(ExpectedConditions.visibilityOf(Home_Page_Linux_Distro));

        JavaScriptExecutor executor =new JavaScriptExecutor();
        executor.Scroll(driver);

        Assert.assertTrue(Home_Page_Linux_Distro.isDisplayed());
    }
    public void Select_Year(String _year) throws InterruptedException {
        WebDriverWait drpOptions=new WebDriverWait(driver,20);
        drpOptions.until(ExpectedConditions.visibilityOf(Data_Span_Dropdown));

        Select year=new Select(Data_Span_Dropdown);
        year.selectByVisibleText(_year);
    }
    public void Click_Go() throws InterruptedException {
        Go_Button.click();
        TimeUnit.SECONDS.sleep(5);
    }
    public void Check_Top_Five(String _first,String _second,String _third,String _fourth,String _fifth)
    {
        WebDriverWait top_five=new WebDriverWait(driver,20);
        top_five.until(ExpectedConditions.visibilityOf(Data_Span_Dropdown));

        Assert.assertEquals(Rank_One.getText(),_first ,"Error : "+ _first+" not ranked correctly");
        Assert.assertEquals(Rank_Two.getText(),_second,"Error : "+_second+" not ranked correctly");
        Assert.assertEquals(Rank_Three.getText(),_third,"Error : "+ _third+" not ranked correctly");
        Assert.assertEquals(Rank_Four.getText(),_fourth,"Error : "+_fourth+" not tanked correctly");
        Assert.assertEquals(Rank_Five.getText(),_fifth,"Error : "+_fifth+" not ranked correctly");

    }
}
