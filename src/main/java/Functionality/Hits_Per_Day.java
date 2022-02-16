package Functionality;

import com.aventstack.extentreports.model.SystemAttribute;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hits_Per_Day {

    private WebDriver driver;
    public Hits_Per_Day(WebDriver driver)
    {
        this.driver=driver;
    }
    public void Get_HPD_Indicator() throws InterruptedException {
        String arrowDown = "https://distrowatch.com/images/other/adown.png";
        String arrowUp = "https://distrowatch.com/images/other/aup.png";
        String level="https://distrowatch.com/images/other/alevel.png";

        //Wait for page to load
        TimeUnit.SECONDS.sleep(15);

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr"));
        int count = rows.size();

        for(int i=4;i<count;i++)
        {
            WebElement todayValue=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr["+i+"]/td[2]"));

            String yesterdayValue = todayValue.getAttribute("title");
            String imageIndicator =driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr["+i+"]/td[2]/img")).getAttribute("src");

            String distributionItem = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[3]/table[2]/tbody/tr["+i+"]/td[1]")).getText();
            int oldValue = Integer.parseInt(yesterdayValue.substring(11));
            int newValue = Integer.parseInt(todayValue.getText());

            String message=distributionItem+ " : Today's Value Is " + todayValue.getText() + "; Yesterdays Value Was " + oldValue +"; Image Indicator Is "+imageIndicator;

            if(oldValue > newValue)
            {
                Assert.assertEquals(arrowDown,imageIndicator,"Indicator Does Not Match");
                System.out.println(message + " " + distributionItem +" is going down");
            }else
                if(oldValue == newValue)
                {
                    Assert.assertEquals(level,imageIndicator,"Indicator Does Not Match");
                    System.out.println(message + " " + distributionItem +" is going level");
                }else
                    if(oldValue <newValue)
                    {
                        Assert.assertEquals(arrowUp,imageIndicator,"Indicator Does Not Match");
                        System.out.println(message+ " " + distributionItem +" is going up");
                    }
        }
    }
}
