package com.gft.ohMyDog.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.gft.ohMyDog.dto.EnderecoDTO;
import com.gft.ohMyDog.dto.cliente.ClienteNewDTO;
import com.google.gson.Gson;

public class ApiCepService {
	
	static String webService = "http://viacep.com.br/ws/";
	
	public EnderecoDTO findAdressByCep(EnderecoDTO end) throws IOException {

		URL url = new URL(webService + end.getCep() + "/json/");
		URLConnection conn = url.openConnection();
		InputStream inputStream = conn.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		
		while((cep = bufferedReader.readLine()) != null) {
			jsonCep.append(cep);
		}	
		
		EnderecoDTO endAux = new Gson().fromJson(jsonCep.toString(), EnderecoDTO.class);
		
		end.setCep(endAux.getCep());
		end.setLogradouro(endAux.getLogradouro());
		end.setBairro(endAux.getBairro());
		end.setLocalidade(endAux.getLocalidade());
		end.setUf(endAux.getUf());
		
		return end;
	}	
	
	public ClienteNewDTO findAdressByCep(ClienteNewDTO end) throws IOException {

		URL url = new URL(webService + end.getCep() + "/json/");
		URLConnection conn = url.openConnection();
		InputStream inputStream = conn.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		
		while((cep = bufferedReader.readLine()) != null) {
			jsonCep.append(cep);
		}	
		
		ClienteNewDTO endAux = new Gson().fromJson(jsonCep.toString(), ClienteNewDTO.class);
		
		end.setCep(endAux.getCep());
		end.setLogradouro(endAux.getLogradouro());
		end.setBairro(endAux.getBairro());
		end.setLocalidade(endAux.getLocalidade());
		end.setUf(endAux.getUf());
		
		return end;
	}	
}
