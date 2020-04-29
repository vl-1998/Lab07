package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class TestModel {


	public static void main(String[] args) {
	
		
		Model model = new Model();
		System.out.println(model.getNercList());
		PowerOutageDAO podao = new PowerOutageDAO();
		
		
		System.out.println (model.controlloAnno(podao.elencoBlackout(new Nerc (3, "MAAC"))));
		
		System.out.println (model.trovaSequenza(new Nerc (3, "MAAC"), 4, 200));

	}
	
	
	
	
	
	
	
	

}
