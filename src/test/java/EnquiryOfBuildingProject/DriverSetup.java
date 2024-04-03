package EnquiryOfBuildingProject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver driver;

	public static WebDriver getChromeWebDriver() {

		driver = new ChromeDriver();
		driver.get("https://ishahomes.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		return driver;

	}

	public static WebDriver getEdgeWebDriver() {

		driver = new EdgeDriver();
		driver.get("https://ishahomes.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		return driver;

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		EnquiryOfBuildingProject test = new EnquiryOfBuildingProject();
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				test.createChromeDriver();

			} else {
				test.createEdgeDriver();
			}
			test.livepop();
			test.completedProjects();
			test.scroll();
			test.count();
			test.first5();
			test.contactusValidation();
			test.scrolltocontact();
			test.printemail();
		}

	}
}