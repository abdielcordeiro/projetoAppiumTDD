package br.com.rsinet.hub.appium.Teste;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.hub.appium.ExtendReport.ExtendReport;
import br.com.rsinet.hub.appium.ScreenObject.PageBusca;
import br.com.rsinet.hub.appium.ScreenObject.PageHome;
import br.com.rsinet.hub.appium.Utility.Constant;
import br.com.rsinet.hub.appium.Utility.DriverManager;
import br.com.rsinet.hub.appium.Utility.ExcelUtils;
import br.com.rsinet.hub.appium.Utility.MassaDados;
import io.appium.java_client.android.AndroidDriver;

public class TesteBuscaLupa {

	private AndroidDriver<WebElement> driver;
	private PageBusca busca;
	private PageHome home;
	private MassaDados dados;
	private ExtentTest test;

	@BeforeTest
	public void report() {
		ExtendReport.setExtent();
	}

	@BeforeMethod
	public void iniciaTeste() throws Exception {

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Pesquisa");
		driver = DriverManager.openApp(Constant.URL, Constant.Pacote, Constant.Ativador);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		busca = new PageBusca(driver);
		dados = new MassaDados();
	}

	@Test
	public void testeBuscaLupaSucesso() throws Exception {

		test = ExtendReport.createTest("BuscaLupaSucesso");

		home.clicarLupa();
		home.inserirBusca(dados.getNomeProduto());
		home.clicarLupa();
		busca.selecionarProduto();

		Assert.assertTrue(busca.retornaNomeProduto(dados.getNomeProduto().toUpperCase()).equals(dados.getNomeProduto().toUpperCase()),
				"Produto encontrado com sucesso");

	}

	@Test
	public void testeBuscaLupaFalha() throws Exception {
		test = ExtendReport.createTest("BuscaLupaFalha");

		home.clicarLupa();
		home.inserirBusca(dados.getNomeProdutoFalha());
		home.clicarLupa();

		Assert.assertTrue(
				busca.mensagemDeErro().equals("- No results for " + "\"" + dados.getNomeProdutoFalha() + "\" -"),
				"Produto: " + dados.getNomeProdutoFalha() + "  não encontrado");

	}

	@AfterMethod
	public void finalizaTeste(ITestResult result) throws Exception {
		ExtendReport.tearDown(result, test, driver);
		DriverManager.closeApp(driver);
	}

	@AfterTest
	public void finalizaReport(){
		ExtendReport.endReport();
	}

}
