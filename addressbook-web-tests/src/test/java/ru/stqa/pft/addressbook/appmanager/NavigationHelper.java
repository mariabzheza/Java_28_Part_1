package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by maria on 02.03.2016.
 */
public class NavigationHelper {

    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("Групи")).click();
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("Головна")).click();
    }
}
