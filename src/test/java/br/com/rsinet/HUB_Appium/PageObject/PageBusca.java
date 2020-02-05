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
		wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElementById("com.Advantage.aShopping:id/textViewProductName"), nome));

		MobileElement el2 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/textViewProductName");
		return el2;
	}

	public String retornaNomeProduto(String nome) {
		return verificarNomeProduto(nome).getText();
	}

	private MobileElement verificarNomeProdutoFalha() {
		MobileElement el1 = (MobileElement) driver
				.findElementById("com.Advantage.aShopping:id/textViewNoProductsToShow");
		return el1;
	}

	public String mensagemDeErro() {
		return verificarNomeProdutoFalha().getText();
	}

	private MobileElement escolherTipo(String tipo) {
		MobileElement el2 = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[starts-with(@text, '" + tipo + "')]");
		return el2;
	}

	public void selecionaTipo(String tipo) {
		escolherTipo(tipo).click();
	}

	private MobileElement escolherProduto(String produto) {
		MobileElement el2 = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[starts-with(@text, '" + produto + "')]");
		return el2;
	}

	public void selecionaProduto(String produto) {
		escolherProduto(produto).click();
	}

	private MobileElement bntAdicionar() {
		MobileElement el1 = (MobileElement) driver
				.findElementById("com.Advantage.aShopping:id/linearLayoutProductQuantity");
		return el1;
	}

	public void botaoAdicionar() {
		bntAdicionar().click();
	}

	private MobileElement bntMais() {
		MobileElement el2 = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.ImageView[2]");
		return el2;
	}

	public void botaoMais() {
		bntMais().click();
	}

	private MobileElement bntApply() {
		MobileElement el1 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/textViewApply");
		return el1;
	}

	public void aceitarQuantidade() {
		bntApply().click();
	}

	private MobileElement bntAddCarinho() {
		MobileElement el2 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/buttonProductAddToCart");
		return el2;
	}

	public void botaoAdicionarCarrinho() {
		bntAddCarinho().click();
	}

	private MobileElement inputLogin() {
		MobileElement el3 = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[3]/android.widget.EditText");
		return el3;
	}

	public void inserirLogin(String nome) {
		inputLogin().click();
		inputLogin().sendKeys(nome);
	}

	private MobileElement inputSenha() {
		MobileElement el1 = (MobileElement) driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[4]/android.widget.EditText");
		return el1;
	}

	public void inserirSenha(String senha) {
		inputSenha().click();
		inputSenha().sendKeys(senha);
	}

	private MobileElement bntLogar() {
		MobileElement el2 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/buttonLogin");
		return el2;
	}

	public void botaoLogar() {
		bntLogar().click();
	}

	private MobileElement bntDedo() {
		MobileElement el2 = (MobileElement) driver.findElementById("android:id/button2");
		return el2;
	}

	public void clicarAutenticacaoDedo() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(bntDedo()));
		bntDedo().click();
	}

	private MobileElement bntEntrarCarinho() {
		MobileElement el1 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/imageViewCart");
		return el1;
	}

	public void entrarNoCarrinho() {
		bntEntrarCarinho().click();
	}

	private MobileElement validaQnt(){
		MobileElement el2 = (MobileElement) driver.findElementById("com.Advantage.aShopping:id/textViewCartQuantity");
		return el2;
	}

	public int validarQuantidadeProduto() {
		return Integer.parseInt(validaQnt().getText());
	}

}
