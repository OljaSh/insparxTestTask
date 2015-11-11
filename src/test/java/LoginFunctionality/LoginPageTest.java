package LoginFunctionality;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginPageTest {

    private static final String URL = "http://www.be2.co.uk/";
    private static final String URLLogin = "http://app.be2.co.uk/login/auth";

    private String UserName = "shseven@hotmail.com";
    private String Password = "test1234";

    private String wrongUserName = "shseven111111222@hotmail.com";
    private String wrongPassword = "1234tttttt";

    private WebDriver driver;

    @Before
    public void setup(){
        driver =  new FirefoxDriver();
    }


//---Login from  http://app.be2.co.uk/login/auth  page
    @Test
    public void IncorrectUserNameLoginPage(){

        driver.get(URLLogin);
        driver.findElement(By.id("username")).sendKeys(wrongUserName);
        driver.findElement(By.id("password")).sendKeys(Password);
        driver.findElement(By.id("login_button")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(By.id("errorMsg")).getText().matches("Email address and password do not match"));

        driver.close();
        driver.quit();
    }

    @Test
    public void IncorrectPasswordLoginPage() throws InterruptedException {

        driver.get(URLLogin);
        driver.findElement(By.id("username")).sendKeys(UserName);
        driver.findElement(By.id("password")).sendKeys(wrongPassword);
        driver.findElement(By.id("login_button")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(By.id("errorMsg")).getText().matches("Email address and password do not match"));

        driver.close();
        driver.quit();
    }

    @Test
    public void CorrectLogin() throws InterruptedException {
        driver.get(URLLogin);

        driver.findElement(By.id("username")).sendKeys(UserName);
        driver.findElement(By.id("password")).sendKeys(Password);
        driver.findElement(By.id("login_button")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(driver.findElement(By.id("logged_user")).getText().matches("You are logged in as Seven"));

        driver.close();
        driver.quit();
    }


//----Login from home page ---------------------


    @Test
    public void IncorrectUserName(){
        driver.get(URL);
        driver.findElement(By.className("be2-green-button")).click();

        driver.findElement(By.id("be2_login_username")).sendKeys(wrongUserName);
        driver.findElement(By.id("be2_login_password")).sendKeys(Password);
        driver.findElement(By.id("be2_loginButton")).click();

        Assert.assertTrue(driver.findElement(By.className("be2-dialog-title")).isDisplayed());

        driver.close();
        driver.quit();
    }

    @Test
    public void IncorrectPassword(){
        driver.get(URL);
        driver.findElement(By.className("be2-green-button")).click();

        driver.findElement(By.id("be2_login_username")).sendKeys(UserName);
        driver.findElement(By.id("be2_login_password")).sendKeys(wrongPassword);
        driver.findElement(By.id("be2_loginButton")).click();

        Assert.assertTrue(driver.findElement(By.className("be2-dialog-title")).isDisplayed());

        driver.close();
        driver.quit();
    }



    @Test
    public void CorrectLoginPopup() throws InterruptedException {
        driver.get(URL);
        driver.findElement(By.className("be2-green-button")).click();

        driver.findElement(By.id("be2_login_username")).sendKeys(UserName);
        driver.findElement(By.id("be2_login_password")).sendKeys(Password);
        driver.findElement(By.id("be2_loginButton")).click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(driver.findElement(By.id("logged_user")).getText().matches("You are logged in as Seven"));

        driver.close();
        driver.quit();
    }
}