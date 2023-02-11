package panel.autoSelenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private final WebDriver driver;
	private final TestData test;
	static Logger log = Logger.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver, TestData test) {

		this.driver = driver;
		this.test = test;
	}
	
	public void execute() throws Exception {
		log.info("Introducimos datos de acceso");
		setUsername();
		setPassword();

		log.info("datos de acceso introducidos");

		clickLogin();

		if(accessOk()){
			if("loginOK".equals(test.getOperation())){
				log.info("accedemos a la página");
			}
			else{
				log.info("El login debería haber fallado");
				throw new Exception("El login se ha producido exitosamente con credenciales erróneas");
			}
		}
		else{
			setError();
		}
	}

	private void setError() throws Exception {
		String message = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

		if("error".equals(test.getOperation())){
			log.info("Aparece el error " + message);
		}
		else{
			throw new Exception(message);
		}
	}

	private boolean accessOk() {
		try{
			driver.findElement(By.id("inventory_container"));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}


	private void clickLogin() {
		driver.findElement(By.id("login-button")).click();
	}

	private void setPassword() {
		driver.findElement(By.id("password")).sendKeys(test.getPassword());
	}

	private void setUsername() {
		driver.findElement(By.id("user-name")).sendKeys(test.getUsername());
	}

}
