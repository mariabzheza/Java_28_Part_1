package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by maria on 02.03.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        //click(By.linkText("Групи"));
        click(By.linkText("groups"));
    }

    public void goToHomePage() {
        //click(By.linkText("Головна"));
        click(By.linkText("home"));
    }
}
