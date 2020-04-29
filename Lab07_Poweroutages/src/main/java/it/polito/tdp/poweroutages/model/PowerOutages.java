package it.polito.tdp.poweroutages.model;

import java.time.*;

public class PowerOutages {
	private int id;
	
	private Nerc nerc;
	private int annoInizio;
	private LocalDateTime inizioBlackout;
	private LocalDateTime fineBlackout;
	private int differenzaOre;
	
	private int customerAffected;

	/**
	 * @param id
	 * @param nerc
	 * @param annoInizio
	 * @param inizioBlackout
	 * @param fineBlackout
	 * @param differenzaOre
	 * @param customerAffected
	 */
	public PowerOutages(int id, Nerc nerc, int annoInizio, LocalDateTime inizioBlackout, LocalDateTime fineBlackout,
			int differenzaOre, int customerAffected) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.annoInizio = annoInizio;
		this.inizioBlackout = inizioBlackout;
		this.fineBlackout = fineBlackout;
		this.differenzaOre = differenzaOre;
		this.customerAffected = customerAffected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(int annoInizio) {
		this.annoInizio = annoInizio;
	}

	public LocalDateTime getInizioBlackout() {
		return inizioBlackout;
	}

	public void setInizioBlackout(LocalDateTime inizioBlackout) {
		this.inizioBlackout = inizioBlackout;
	}

	public LocalDateTime getFineBlackout() {
		return fineBlackout;
	}

	public void setFineBlackout(LocalDateTime fineBlackout) {
		this.fineBlackout = fineBlackout;
	}

	public int getDifferenzaOre() {
		return differenzaOre;
	}

	public void setDifferenzaOre(int differenzaOre) {
		this.differenzaOre = differenzaOre;
	}

	public int getCustomerAffected() {
		return customerAffected;
	}

	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return annoInizio + " " + inizioBlackout
				+ " "+ fineBlackout + " " + differenzaOre + " " + customerAffected;
	}
	
	
	
	
	
}
