package br.com.rsinet.HUB_Appium.Utility;

import java.util.Random;

public class MassaDados {

	private int linha = 1;
	private static int linhaFalha = 9;

	public void proximaLinha() {
		this.linha += 1;
	}

	public String getNomeUsuario(int quantidadeLetra) {
		Random quantidadedeletras = new Random();
		int index;

		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvxwyz0123456789";
		String palavraRandom = "";

		for (int i = 0; i <= quantidadeLetra - 1; i++) {
			index = quantidadedeletras.nextInt(caracteres.length());
			palavraRandom += caracteres.substring(index, index + 1);
		}
		return palavraRandom;
	}

	public String getSenha() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.Senha);
	}

	public String getEmail() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.Email);
	}

	public String getPrimeiroNome() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.PrimeiroNome);
	}

	public String getUltimoNome() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.UltimoNome);
	}

	public String getNumeroTelefone() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.NumeroTelefone);
	}

	public String getCidade() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.Cidade);
	}

	public String getNomeProduto() throws Exception {
		return ExcelUtils.getCellDataString(linha, Constant.NomeProduto);
	}

	public String getNomeProdutoFalha() throws Exception {
		return ExcelUtils.getCellDataString(linhaFalha, Constant.NomeProdutoFalha);
	}

//	public String getTipoProduto() throws Exception {
//		return ExcelUtils.getCellDataString(linha, Constant.TipoProduto);
//	}
//
//
//
//	public Integer getQuantidadeProduto() throws Exception {
//		return Integer.valueOf(ExcelUtils.getCellDataNumber(linha, Constant.QuantidadeProduto));
//	}

}
