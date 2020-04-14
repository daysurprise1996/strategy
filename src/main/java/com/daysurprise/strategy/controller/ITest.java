package com.daysurprise.strategy.controller;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wangjie
 * @version V1.0
 * @date 2020/4/13 19:10
 * @desc:
 * @className com.daysurprise.strategy.controller.ITest
 */
public class ITest {

    public static void main(String[] args) throws InterruptedException {
        loginCsdn();
    }

    public static void loginBaidu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\workspace2020\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        WebElement chooseLoginMethod = driver.findElement(By.linkText("登录"));
        chooseLoginMethod.click();
        Thread.sleep(1000);
        WebElement login = driver.findElement(By.id("TANGRAM__PSP_10__footerULoginBtn"));
        login.click();
        Thread.sleep(1000);
        WebElement loginName = driver.findElement(By.name("userName"));
        loginName.clear();
        loginName.sendKeys("wangjie19961314@163.com");
        Thread.sleep(1000);
        WebElement pwd = driver.findElement(By.name("password"));
        pwd.clear();
        pwd.sendKeys("wj1996521000");
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.id("TANGRAM__PSP_10__submit"));
        loginButton.submit();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl:" + currentUrl);
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        WebElement moveButton = driver.findElement(By.cssSelector(".vcode-slide-button"));
        actions.moveToElement(moveButton).clickAndHold(moveButton);
        actions.dragAndDropBy(moveButton, 210, 0).perform();
        // actions.release();
    }

    /**
     * 模拟登录CSDN
     */
    public static void loginCsdn() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\workspace2020\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://passport.csdn.net/login?code=public");
        WebElement chooseLoginMethod = driver.findElement(By.linkText("账号密码登录"));
        chooseLoginMethod.click();
        Thread.sleep(1000);
        WebElement loginName = driver.findElement(By.id("all"));
        loginName.clear();
        loginName.sendKeys("17601067267");
        Thread.sleep(1000);
        WebElement pwd = driver.findElement(By.name("pwd"));
        pwd.clear();
        pwd.sendKeys("wj1996521000");
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.cssSelector(".form-group > .form-submit > .btn"));
        loginButton.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl:" + currentUrl);
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        WebElement moveButton = driver.findElement(By.id("nc_1_n1z"));
        actions.moveToElement(moveButton).clickAndHold(moveButton);
        actions.dragAndDropBy(moveButton, 300, 0).perform();
        actions.release();
        // driver.close();
    }

    public static void loginCsdn2() {
        System.setProperty("webdriver.chrome.driver", "E:\\workspace2020\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Cookie cookie = new Cookie("UserName", "DaySurprise");
        Cookie cookie2 = new Cookie("UserInfo", "624196d5c2bf41588e147ed5ece4031f");
        Cookie cookie3 = new Cookie("UserToken", "624196d5c2bf41588e147ed5ece4031f");
        Cookie cookie4 = new Cookie("UserNick", "DaySurprise");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(cookie2);
        driver.manage().addCookie(cookie3);
        driver.manage().addCookie(cookie4);
        driver.get("https://passport.csdn.net/login?code=public");
        String title = driver.getTitle();
        System.out.println("title: " + title);
    }
}
