package EnquiryOfBuildingProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnquiryOfBuildingProject {
	public static WebDriver driver;

	public void createChromeDriver() {
		driver = DriverSetup.getChromeWebDriver();
	}

	public void createEdgeDriver() {
		driver = DriverSetup.getEdgeWebDriver();
	}

	public void completedProjects() {
		driver.findElement(By.xpath(
				"//li[@id='menu-item-25810']//a[contains(@class,'nav-link')][normalize-space()='Completed Projects']"))
				.click();
	}

	public void livepop() {
		try {
			driver.switchTo().frame("livservMiddleFrame");
			driver.findElement(By.xpath("//*[@id=\'td2\']/div")).click();
		} catch (Exception e) {
		}
		try {
			driver.findElement(By.id("livchat_close")).click();
		} catch (Exception e) {
		}
		driver.switchTo().defaultContent();
		try {
			driver.findElement(By.xpath("//*[@id=\"elementor-popup-modal-33100\"]/div/a")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
		}
	}

	public void count() throws IOException {
		List<WebElement> projects = driver
				.findElements(By.xpath("//div[@id='boosted-tab-0']//div[@id='module_properties']/div"));
		System.out.println("Number of projects " + projects.size());

		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\Excel\\Output.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Output");

		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("Number of projects " + projects.size());

		workbook.write(file);
		workbook.close();
		file.close();
	}

	public void first5() throws IOException {
		List<WebElement> projectnames = driver
				.findElements(By.xpath("//div[@id='boosted-tab-0']//div[@id='module_properties']//div//h2/a"));
		int i = 0;
		FileOutputStream file1 = new FileOutputStream(System.getProperty("user.dir") + "\\Excel\\Data.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook1.createSheet("Data");

		for (WebElement ele : projectnames) {

			if (i == 5) {
				break;
			}
			System.out.println(i + " " + ele.getText());
			XSSFRow row1 = sheet1.createRow(i);
			XSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue(i + " " + ele.getText());

			i++;
		}
		workbook1.write(file1);
		workbook1.close();
		file1.close();
	}

	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5000)");
	}

	public void contactusValidation() {
		boolean contactus = driver.findElement(By.xpath("//span[normalize-space()='Contact Us']")).isDisplayed();
		if (contactus) {
			System.out.println("Contact Us Element is present");
		} else {
			System.out.println("Contact Us Element is not present");
		}
		driver.findElement(By.xpath("//*[span ='Contact Us']")).click();
	}

	public void scrolltocontact() throws IOException {
		JavascriptExecutor details = (JavascriptExecutor) driver;
		details.executeScript("window.scrollBy(0,500)");
	}

	public void printemail() throws IOException, InterruptedException {
		WebElement eid = driver.findElement(By.xpath("//a[text()='marketing@ishahomes.com']"));
		System.out.println(eid.getText());

		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\Excel\\Email.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Email");

		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(eid.getText());

		workbook.write(file);
		workbook.close();
		file.close();

		// Taking screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\2318337\\eclipse-workspace\\EnquiryofBuildingProject\\Screenshot\\Final.png");
		FileUtils.copyFile(src, trg);
		driver.quit();
	}

}
