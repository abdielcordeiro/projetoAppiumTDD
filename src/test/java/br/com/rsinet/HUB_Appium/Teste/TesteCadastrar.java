package br.com.rsinet.HUB_Appium.Teste;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	TouchAction scroll;
	MassaDados dados;

	@Before
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

		//while (dados.getSenha() != null) {

			cadastro.clicarMenu();
			cadastro.clicarLogin();
			cadastro.clicarCadastrar();

			cadastro.preencherNomeDeUsuario(dados.getNomeUsuario(6));
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
			cadastro.clicarMenu();

//			dados.proximaLinha();
//		}

	}

	@After
	public void finalizaTeste() {

		// DriverManager.closeApp(driver);

	}

}