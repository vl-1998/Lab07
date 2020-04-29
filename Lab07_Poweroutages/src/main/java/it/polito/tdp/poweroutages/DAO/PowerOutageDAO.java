package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List <PowerOutages> elencoBlackout (Nerc nerc) {
		String sql="SELECT id, YEAR(date_event_began) as anno, "
				+ "TIMESTAMP(date_event_began) as dataInizio, "
				+ "TIMESTAMP (date_event_finished) as dataFine, "
				+ "HOUR (TIMEDIFF(date_event_finished, date_event_began)) as differenza, "
				+ "customers_affected FROM poweroutages WHERE nerc_id=?";
		
		List <PowerOutages> elencoBlackout = new ArrayList <> ();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();
			

			while (res.next()) {
				
				
				elencoBlackout.add(new PowerOutages (res.getInt("id"), nerc, res.getInt("anno"), 
						res.getTimestamp("dataInizio").toLocalDateTime(), 
						res.getTimestamp("dataFine").toLocalDateTime(), res.getInt("differenza"), 
						res.getInt("customers_affected")));
			}

			conn.close();
			
			return elencoBlackout;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		
	}
	

}
