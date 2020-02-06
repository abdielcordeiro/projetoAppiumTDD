package br.com.rsinet.HUB_Appium.Teste;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.HUB_Appium.ExtendReport.ExtendReport;
import br.com.rsinet.HUB_Appium.ScreenObject.PageBusca;
import br.com.rsinet.HUB_Appium.Utility.Constant;
import br.com.rsinet.HUB_Appium.Utility.DriverManager;
import br.com.rsinet.HUB_Appium.Utility.ExcelUtils;
import br.com.rsinet.HUB_Appium.Utility.MassaDados;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class TesteBuscaLupa {

	private AndroidDriver<MobileElement> driver;
	private PageBusca busca;
	private TouchAction scroll;
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
		scroll = new TouchAction(driver);
		dados = new MassaDados();
	}

	@Test
	public void testeBuscaLupaSucesso() throws Exception {

		test = ExtendReport.createTest("BuscaLupaSucesso");

		busca.clicarLupa();
		busca.inserirBusca(dados.getNomeProduto());
		busca.clicarLupa();
		busca.selecionarProduto();

		Assert.assertTrue(busca.retornaNomeProduto(dados.getNomeProduto().toUpperCase()).equals(dados.getNomeProduto().toUpperCase()),
				"Produto encontrado com sucesso");

	}

	@Test
	public void testeBuscaLupaFalha() throws Exception {
		test = ExtendReport.createTest("BuscaLupaFalha");

		busca.clicarLupa();
		busca.inserirBusca(dados.getNomeProdutoFalha());
		busca.clicarLupa();

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
