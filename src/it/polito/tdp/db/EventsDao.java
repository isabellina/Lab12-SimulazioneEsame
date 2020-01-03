package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<Year> getYear(){
		List<Year> listaAnni = new LinkedList<Year>();
		String sql= "select distinct Year(reported_date) as anno from events order by reported_date asc ";
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				listaAnni.add(Year.of(res.getInt("anno"))) ;
			}
			conn.close();
			return listaAnni;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<District> getDistricts(Year y){
		List<District> ltemp = new LinkedList<District>(); 
			String sql ="select district_id, avg(geo_lon) as a_lon, avg(geo_lat) as a_lat from events  where year(reported_date) = ? group by district_id; " ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, y.getValue());
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				ltemp.add(new District(res.getInt("district_id"), res.getDouble("a_lon") , res.getDouble("a_lat")) ) ;
			}
			conn.close();
			return ltemp;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
