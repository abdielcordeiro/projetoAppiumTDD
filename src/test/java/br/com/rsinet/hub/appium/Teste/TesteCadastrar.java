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
import br.com.rsinet.hub.appium.ScreenObject.PageCadastro;
import br.com.rsinet.hub.appium.Utility.Constant;
import br.com.rsinet.hub.appium.Utility.DriverManager;
import br.com.rsinet.hub.appium.Utility.ExcelUtils;
import br.com.rsinet.hub.appium.Utility.MassaDados;
import io.appium.java_client.android.AndroidDriver;

public class TesteCadastrar {

	private AndroidDriver<WebElement> driver;
	private PageCadastro cadastro;
	private MassaDados dados;
	private ExtentTest test;
	private String nomeUsuario;

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
		dados = new MassaDados();
	}

	@Test
	public void testeCadastroSucesso() throws Exception {

		test = ExtendReport.createTest("CadastroSucesso");

		cadastro.clicarMenu();
		cadastro.clicarLogin();
		cadastro.clicarCadastrar();

		nomeUsuario = dados.getNomeUsuario(6);
		cadastro.preencherNomeDeUsuario(nomeUsuario);
		cadastro.preencherEmail(dados.getEmail());
		cadastro.preencherSenha(dados.getSenha());

		driver.hideKeyboard();
		cadastro.preencherConfirmacaoSenha(dados.getSenha());

		driver.hideKeyboard();
		cadastro.preencherPrimeiroNome(dados.getPrimeiroNome());
		cadastro.preencherUltimoNome(dados.getUltimoNome());

		driver.hideKeyboard();
		cadastro.scroll(0.9, 0.0);

		//driver.hideKeyboard();

		cadastro.preencherNumeroTelefone(dados.getNumeroTelefone());

		driver.hideKeyboard();
		cadastro.scroll(0.9, 0.0);

		cadastro.clicarLocalizacao();
		cadastro.clicarConfirmaLocalizacao();
		cadastro.preencherCidade(dados.getCidade());

		cadastro.clicarPais();
		cadastro.scrollAndClick("Brazil");

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

		nomeUsuario = dados.getNomeUsuario(16);
		cadastro.preencherNomeDeUsuario(nomeUsuario);
		cadastro.preencherEmail(dados.getEmail());
		cadastro.preencherSenha(dados.getSenha());

		driver.hideKeyboard();
		cadastro.preencherConfirmacaoSenha(dados.getSenha());

		driver.hideKeyboard();

		cadastro.preencherPrimeiroNome(dados.getPrimeiroNome());
		cadastro.preencherUltimoNome(dados.getUltimoNome());

		driver.hideKeyboard();

		cadastro.scroll(0.9, 0.0);

		driver.hideKeyboard();

		cadastro.preencherNumeroTelefone(dados.getNumeroTelefone());

		driver.hideKeyboard();
		cadastro.scroll(0.9, 0.0);

		cadastro.clicarLocalizacao();
		cadastro.clicarConfirmaLocalizacao();
		cadastro.preencherCidade(dados.getCidade());

		cadastro.clicarPais();
		cadastro.scrollAndClick("Brazil");

		cadastro.scrollVisible("ACCOUNT DETAILS");

		Assert.assertTrue(cadastro.validaUsuarioErrado().equals("Use up to 15 characters"), "Falha no nome de usuário");
	}

	@AfterMethod
	public void finalizaTeste(ITestResult result) throws Exception{
		ExtendReport.tearDown(result, test, driver);
		DriverManager.closeApp(driver);
	}

	@AfterTest
	public void finalizaReporte(){
		ExtendReport.endReport();
	}




}