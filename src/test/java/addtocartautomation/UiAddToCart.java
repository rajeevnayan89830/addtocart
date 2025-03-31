package addtocartautomation;



	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import java.time.Duration;

	public class UiAddToCart {
	    WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.flipkart.com");
	    }

	    @Test(priority = 1)
	    public void searchProduct() {
	        driver.findElement(By.name("q")).sendKeys("iPhone 14");
	        driver.findElement(By.cssSelector("button[type='submit']")).click();

	        WebElement firstProduct = driver.findElement(By.xpath("//div[contains(@class,'_4rR01T')]"));
	        Assert.assertTrue(firstProduct.isDisplayed(), "Product search failed!");
	        firstProduct.click();
	    }

	    @Test(priority = 2)
	    public void addToCart() {
	        // Switch to new tab where product is opened
	        for (String tab : driver.getWindowHandles()) {
	            driver.switchTo().window(tab);
	        }

	        driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();

	        WebElement cartMsg = driver.findElement(By.xpath("//span[contains(text(),'Place Order')]"));
	        Assert.assertTrue(cartMsg.isDisplayed(), "Product not added to cart!");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}


