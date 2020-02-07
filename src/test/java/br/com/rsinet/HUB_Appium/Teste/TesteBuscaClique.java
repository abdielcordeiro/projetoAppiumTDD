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
import br.com.rsinet.HUB_Appium.ScreenObject.PageCadastro;
import br.com.rsinet.HUB_Appium.Utility.Constant;
import br.com.rsinet.HUB_Appium.Utility.DriverManager;
import br.com.rsinet.HUB_Appium.Utility.ExcelUtils;
import br.com.rsinet.HUB_Appium.Utility.MassaDados;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class TesteBuscaClique {

	@SuppressWarnings("rawtypes")
	private AndroidDriver driver;
	private PageBusca busca;
	private PageCadastro cadastro;
	@SuppressWarnings({ "rawtypes", "unused" })
	private TouchAction scroll;
	private MassaDados dados;
	private ExtentTest test;

	@BeforeTest
	public void report() {
		ExtendReport.setExtent();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@BeforeMethod
	public void iniciaTeste() throws Exception {

		driver = DriverManager.openApp(Constant.URL, Constant.Pacote, Constant.Ativador);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		busca = new PageBusca(driver);
		cadastro = new PageCadastro(driver);
		scroll = new TouchAction(driver);
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
		 * Login
		 */
		cadastro.clicarMenu();
		cadastro.clicarLogin();
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Cadastro");
		busca.inserirLogin(dados.getNomeUsuarioExcel());
		busca.inserirSenha(dados.getSenha());
		busca.botaoLogar();
		busca.clicarAutenticacaoDedo();

		/*
		 * Navegação para o produto
		 */
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Pesquisa");
		busca.selecionaTipo(dados.getTipoProduto());
		busca.selecionaProduto(dados.getNomeProduto().toUpperCase());
		busca.botaoAdicionar();

		for (int i = 0; i < dados.getQuantidadeProduto() - 1; i++) {
			busca.botaoMais();
		}

		busca.aceitarQuantidade();
		busca.botaoAdicionarCarrinho();

		busca.entrarNoCarrinho();

		Assert.assertTrue(busca.validarQuantidadeProduto() != dados.getQuantidadeProduto(),
				"Quantidade diferente da quantidade pedida");

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
