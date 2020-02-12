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
import br.com.rsinet.hub.appium.Utility.Constant;
import br.com.rsinet.hub.appium.Utility.DriverManager;
import br.com.rsinet.hub.appium.Utility.ExcelUtils;
import br.com.rsinet.hub.appium.Utility.MassaDados;
import io.appium.java_client.android.AndroidDriver;

public class TesteBuscaClique {

	private AndroidDriver<WebElement> driver;
	private PageBusca busca;
	private MassaDados dados;
	private ExtentTest test;

	@BeforeTest
	public void report() {
		ExtendReport.setExtent();
	}

	@BeforeMethod
	public void iniciaTeste() throws Exception {

		driver = DriverManager.openApp(Constant.URL, Constant.Pacote, Constant.Ativador);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		busca = new PageBusca(driver);
		dados = new MassaDados();
	}

	@Test
	public void testeBuscaCliqueSucesso() throws Exception {

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Pesquisa");

		test = ExtendReport.createTest("BuscaCliqueSucesso");

		busca.selecionaTipo(dados.getTipoProduto());
		busca.selecionaProduto(dados.getNomeProduto().toUpperCase());

		Assert.assertTrue(busca.retornaNomeProduto(dados.getNomeProduto().toUpperCase())
				.equals(dados.getNomeProduto().toUpperCase()), "Produto encontrado com sucesso");
	}

	@Test
	public void testeBuscaCliqueFalha() throws Exception {

		test = ExtendReport.createTest("BuscaCliqueFalha");

		/*
		 * Navegação para o produto
		 */
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Pesquisa");
		busca.selecionaTipo(dados.getTipoProduto());

		busca.clicarBotaoFiltro();
		busca.botaoFuncionalidade();
		busca.botaoResolucao();
		busca.botaoMemoria();
		busca.botaoAplicar();

		Assert.assertTrue(busca.mensagemProdutoClique().equals("- No results -"), "Produto não encontrado");

	}

	@AfterMethod
	public void finalizaTeste(ITestResult result) throws Exception {
		ExtendReport.tearDown(result, test, driver);
		DriverManager.closeApp(driver);
	}

	@AfterTest
	public void encerraReport() {
		ExtendReport.endReport();
	}

}
