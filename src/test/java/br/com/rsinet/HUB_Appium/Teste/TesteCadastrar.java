package br.com.rsinet.HUB_Appium.Teste;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.HUB_Appium.ExtendReport.ExtendReport;
import br.com.rsinet.HUB_Appium.PageObject.PageCadastro;
import br.com.rsinet.HUB_Appium.Utility.Constant;
import br.com.rsinet.HUB_Appium.Utility.DriverManager;
import br.com.rsinet.HUB_Appium.Utility.ExcelUtils;
import br.com.rsinet.HUB_Appium.Utility.MassaDados;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class TesteCadastrar {

	private AndroidDriver driver;
	private PageCadastro cadastro;
	private TouchAction scroll;
	private MassaDados dados;
	private ExtentTest test;

	@BeforeTest
	public void report() {
		ExtendReport.setExtent();
	}

	@BeforeMethod
	public void iniciaTeste() throws Exception {

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Cadastro");
		driver = DriverManager.openApp(Constant.URL, Constant.Pacote, Constant.Ativador);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		cadastro = new PageCadastro(driver);
		scroll = new TouchAction(driver);
		dados = new MassaDados();
	}

	@Test
	public void testeCadastroSucesso() throws Exception {

		test = ExtendReport.createTest("CadastroSucesso");
		cadastro.clicarMenu();
		cadastro.clicarLogin();
		cadastro.clicarCadastrar();

		String nomeUsuario = dados.getNomeUsuario(6);
		cadastro.preencherNomeDeUsuario(nomeUsuario);
		cadastro.preencherEmail(dados.getEmail());
		cadastro.preencherSenha(dados.getSenha());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		cadastro.preencherConfirmacaoSenha(dados.getSenha());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		cadastro.preencherPrimeiroNome(dados.getPrimeiroNome());
		cadastro.preencherUltimoNome(dados.getUltimoNome());
		cadastro.preencherNumeroTelefone(dados.getNumeroTelefone());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		driver.hideKeyboard();

		cadastro.clicarLocalizacao();
		cadastro.clicarConfirmaLocalizacao();
		cadastro.preencherCidade(dados.getCidade());

		cadastro.clicarPais();

		scroll.press(PointOption.point(922, 1612)).moveTo(PointOption.point(967, 274)).perform();
		scroll.press(PointOption.point(922, 1612)).moveTo(PointOption.point(943, 274)).perform();
		scroll.press(PointOption.point(922, 1612)).moveTo(PointOption.point(943, 274)).perform();

		cadastro.clicarConfirmaPais();

		cadastro.clicarEmCadastrar();
		cadastro.clicarMenu();

		Assert.assertTrue(cadastro.validaCadastro().equals(nomeUsuario), "Usuário cadastrado com sucesso");
	}

	@Test
	public void testeCadastroFalha() throws Exception {
		test = ExtendReport.createTest("CadastroFalha");

		cadastro.clicarMenu();
		cadastro.clicarLogin();
		cadastro.clicarCadastrar();

		cadastro.preencherNomeDeUsuario(dados.getNomeUsuario(16));
		cadastro.preencherEmail(dados.getEmail());
		cadastro.preencherSenha(dados.getSenha());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		cadastro.preencherConfirmacaoSenha(dados.getSenha());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		cadastro.preencherPrimeiroNome(dados.getPrimeiroNome());
		cadastro.preencherUltimoNome(dados.getUltimoNome());
		cadastro.preencherNumeroTelefone(dados.getNumeroTelefone());

		scroll.press(PointOption.point(1040, 1143)).moveTo(PointOption.point(1038, 419)).perform();

		driver.hideKeyboard();

		cadastro.clicarLocalizacao();
		cadastro.clicarConfirmaLocalizacao();
		cadastro.preencherCidade(dados.getCidade());

		cadastro.clicarPais();

		scroll.press(PointOption.point(922, 1612)).moveTo(PointOption.point(967, 274)).perform();
		scroll.press(PointOption.point(922, 1612)).moveTo(PointOption.point(943, 274)).perform();

		cadastro.clicarConfirmaPais();

		scroll.press(PointOption.point(1038, 266)).moveTo(PointOption.point(1019, 1690)).perform();

		Assert.assertTrue(cadastro.validaUsuarioErrado().equals("Use up to 15 characters"), "Falha no nome de usuário");
	}

	@AfterMethod
	public void finalizaTeste(ITestResult result) throws Exception {
		ExtendReport.tearDown(result, test, driver);
		ExtendReport.endReport();
		DriverManager.closeApp(driver);

	}

}