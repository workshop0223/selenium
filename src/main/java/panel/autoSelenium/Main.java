package panel.autoSelenium;

import java.io.*;
import java.util.Arrays;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert.*;

import static org.junit.Assert.fail;

public class Main {

	private static final String CHROMEDRIVER = "D://webDrivers//resources//chromedriver108//chromedriver.exe";
	private static final String PATH = "https://www.saucedemo.com/";

	public static void main( String[] args ){
		System.setProperty("logfilepath", "target\\output\\");

		List<Test> tests = readJson();

		for (Test test : tests) {
			executeTest(test);
		}


	}

	@org.junit.Test
	private static void executeTest(Test test){
		System.setProperty("logfilename", test.getTestid());

		WebDriver driver = getDriver();

		driver.get(PATH);
		try {
			LoginPage login = new LoginPage(driver, test);
			login.execute();
		} catch (Exception e) {
			fail (e.toString());
		}
		finally {
			driver.quit();
		}
	}


	private static List<Test> readJson() {
		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("D:\\workshop\\selenium\\autoSelenium\\src\\main\\resources\\input.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Test[] tests = gson.fromJson(reader, Test[].class);
		return Arrays.asList(tests);
	}

	private static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER);

		ChromeOptions options = new ChromeOptions();

		WebDriver chromeDriver = new ChromeDriver(options);
		chromeDriver.manage().window().maximize();

		return chromeDriver;
	}
}

