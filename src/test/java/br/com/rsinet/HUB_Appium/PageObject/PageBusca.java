package br.com.rsinet.HUB_Appium.PageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PageBusca {

	private AndroidDriver driver;

	public PageBusca(AndroidDriver driver) {
		this.driver = driver;
	}

	private MobileElement botaoLupa() {
		MobileElement bntLupa = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/imageViewSearch");
		return bntLupa;
	}

	public void clicarLupa() {
		botaoLupa().click();
	}

	private MobileElement escreverBusca() {
		MobileElement escLupa = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/editTextSearch");
		return escLupa;
	}

	public void inserirBusca(String busca) {
		escreverBusca().sendKeys(busca);
	}

	private MobileElement clicarProduto() {
		MobileElement apertarProduto = (MobileElement) driver
				.findElementById("com.Advantage.aShopping:id/imageViewProduct");
		return apertarProduto;
	}

	public void selecionarProduto() {
		clicarProduto().click();
	}

	private MobileElement verificarNomeProduto(String nome) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElementById("com.Advantage.aShopping:id/textViewProductName"), nome));

		MobileElement el2 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/textViewProductName");
		return el2;
	}

	public String retornaNomeProduto(String nome) {
		return verificarNomeProduto(nome).getText();
	}

	private MobileElement verificarNomeProdutoFalha(){
		MobileElement el1 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/textViewNoProductsToShow");
		return el1;
	}

	public String mensagemDeErro() {
		return verificarNomeProdutoFalha().getText();
	}


}
