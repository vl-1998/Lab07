package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List <PowerOutages> soluzione;
	private List <PowerOutages> disponibili;
	private int bestCoinvolti;
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	public List<PowerOutages> trovaSequenza (Nerc nerc, int X, int Y) {
		soluzione = new ArrayList<>();
		disponibili= podao.elencoBlackout(nerc);
		bestCoinvolti=0;
		
		List <PowerOutages> parziale = new ArrayList <>();
		cerca(parziale, 0, X, Y, disponibili);
		
		return soluzione;
			
	}
	
	private List <PowerOutages> cerca(List<PowerOutages> parziale, int livello, int X, 
			int Y, List <PowerOutages> disponibili){
		
		//caso terminale
		if (disponibili.size()==0 && this.numeroPersone(parziale)>bestCoinvolti) {
			this.soluzione= new ArrayList <>(parziale);
			bestCoinvolti =this.numeroPersone(parziale);
		}
		
		//caso intermedio 
		else {
			for (PowerOutages p: disponibili) {
				boolean flag=true;
				if (p.getDifferenzaOre()>=Y) {
					flag=false;
				}
				if (controllo(parziale, X, Y) && flag==true) {						
						parziale.add(p);
						List <PowerOutages> restanti = new ArrayList <> (disponibili);
						restanti.remove(p);
						cerca(parziale, livello+1, X, Y, restanti);
					}
				}
			}
		//}

		return soluzione;
	
	}

	public int numeroPersone(List<PowerOutages> parziale) {
		int coinvolti=0;
		
		for (PowerOutages p: parziale) {
			coinvolti += p.getCustomerAffected();
		}
		
		return coinvolti;
	}
	
	public int oreDisservizio (List <PowerOutages> parziale) {
		int ore=0;
		
		for (PowerOutages p: parziale) {
			ore += p.getDifferenzaOre();
		}
		 return ore;
	}
	
	public int controlloAnno (List <PowerOutages> parziale) {
		int minore=0;
		int maggiore=0;
		
		maggiore= parziale.get(0).getAnnoInizio();
		minore = parziale.get(0).getAnnoInizio();
		for (int i=1; i<parziale.size(); i++) {
			if (parziale.get(i).getAnnoInizio()> maggiore) {
				maggiore = parziale.get(i).getAnnoInizio();
			}
			
			if (parziale.get(i).getAnnoInizio()<minore) {
				minore=parziale.get(i).getAnnoInizio();
			}
		}
		
		return maggiore-minore;
	}
	
	public boolean controllo (List <PowerOutages> parziale, int X, int Y) {
		if (parziale.size()==0)  
			return true;
		
		if (this.oreDisservizio(parziale)< Y && this.controlloAnno(parziale)<X) 
			return true;
	
		
		return false;
		
	}

}
