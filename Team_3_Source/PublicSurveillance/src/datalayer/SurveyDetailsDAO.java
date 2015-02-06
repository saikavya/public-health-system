package datalayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DBConnection;
import businesslayer.ISurveyDAO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SurveyDetailsDAO implements ISurveyDAO {
	
    @Override
    public Map<Integer, String> getLocationDetails() {
        ResultSet rs = null;
        String query = "SELECT location_id,location_name FROM location";
        // TODO Auto-generated method stub
        Map<Integer, String> locationIDNameMap = new HashMap<>();
        Connection connection = DBConnection.getConnection();
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    locationIDNameMap.put(rs.getInt("location_id"), rs.getString("location_name"));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                    rs.close();
                    if(connection != null)
                        connection.close();
                    } catch (SQLException e) {
                    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
            return locationIDNameMap;
    }

    @Override
    public int getMaxSurveyId(String locationId) {
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int surveyId = 0;
        ResultSet rs = null;
        String query = "SELECT MAX(sur_id) AS sur_id FROM survey WHERE year = " + year + " AND loc_id = " + locationId;
        Connection connection = DBConnection.getConnection();
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    surveyId = rs.getInt("sur_id");
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                    rs.close();
                        if(connection != null)
                            connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }	
        }
        return surveyId;
    }

    @Override
    public int getNextCitizenId() {
        
        ResultSet rs = null;
        int surveyId = 0;
        String query = "SELECT MAX(cit_id) AS cit_id FROM citizen";
        Connection connection = DBConnection.getConnection();
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    surveyId = rs.getInt("cit_id");
                }
            }
            surveyId++;
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                    rs.close();
                        if(connection != null)
                            connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return surveyId;
    }

    @Override
    public void insertIntoCitizenTable(int citizenId, int surveyId, String gender, String dateOfBirth, String ethnicity, 
            String maritalStatus, int weeklyFastFood, int weeklyFruits, boolean alcohol, boolean smoking) throws Exception {
        
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String insertQuery = "INSERT INTO citizen (cit_id, sur_id, gender, dob, ethnicity, marital_stat, fast_food_weekly, fruits_weekly,"
                + "alcohol, smoke) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, citizenId);
            ps.setInt(2, surveyId);
            ps.setString(3, gender);
            ps.setString(4, dateOfBirth);
            ps.setString(5, ethnicity);
            ps.setString(6, maritalStatus);
            ps.setInt(7, weeklyFastFood);
            ps.setInt(8, weeklyFruits);
            ps.setBoolean(9, alcohol);
            ps.setBoolean(10, smoking);
            ps.executeUpdate();
            connection.commit();
        } catch(Exception e){
            connection.rollback();
            throw new Exception("insertIntoCitizenTable :: Cannot insert data in citizen table" + e);
        } finally {
            if(ps!= null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void insertIntoOccCitizenMappingTable(List<Integer> occupationIds, int citizenId) throws Exception {
        
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO occ_cit (occ_id, cit_id) VALUES (?," + citizenId + ")";
        
        try {
            ps = connection.prepareStatement(query);
            for(Integer occId: occupationIds){
                ps.setInt(1, occId);
                ps.executeUpdate();
            }
            connection.commit();
        } catch(Exception e){
            connection.rollback();
            throw new Exception("insertIntoOccCitizenMappingTable :: Failed to insert citizen data" + e);
        } finally {
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        
    }

    @Override
    public void insertIntoDiseaseCitizenMapTables(List<Integer> diseaseIds, int citizenId) throws Exception {
        
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO dis_cit (dis_id, cit_id) VALUES (?, " + citizenId + ")";
        
        try {
            ps = connection.prepareStatement(query);
            for(Integer disId: diseaseIds){
                ps.setInt(1, disId);
                ps.executeUpdate();
            }
            connection.commit();
        } catch(Exception e){
            connection.rollback();
            throw new Exception("insertIntoDiseaseCitizenMapTables :: Failed to insert citizen data" + e);
        } finally {
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Integer> getDiseaseIds(List<String> diseases) throws Exception {
     
        StringBuilder sb = new StringBuilder(200);
        String s = "";
        ResultSet rs = null;
        List<Integer> disIds = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        
        sb.append("SELECT DISTINCT disease_id FROM disease WHERE disease_description IN (");
        for(String dis: diseases){
            if(!s.equals("")){
                s += ",";
            }
            s += "'" + dis.trim() + "'";
        }
        sb.append(s).append(")");
        
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(sb.toString());
            if(rs != null){
                while(rs.next()){
                    disIds.add(rs.getInt("disease_id"));
                }
            }
        } catch(Exception e){
            System.out.println("Could not fetch values from occupation table");
            throw new Exception("getDiseaseIds :: Could not fetch values from occupation table" + e);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SurveyDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return disIds;
    }

    @Override
    public List<Integer> getOccupationIds(List<String> occupations) throws Exception {
        
        StringBuilder sb = new StringBuilder(200);
        String s = "";
        ResultSet rs = null;
        List<Integer> occIds = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        
        sb.append("SELECT DISTINCT occ_id FROM occupation WHERE occup_description IN (");
        for(String occ: occupations){
            if(!s.equals("")){
                s += ",";
            }
            s += "'"+occ.trim()+"'";
        }
        sb.append(s).append(")");
        
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(sb.toString());
            if(rs != null){
                while(rs.next()){
                    occIds.add(rs.getInt("occ_id"));
                }
            }
            
        } catch(Exception e){
            System.out.println("Could not fetch values from occupation table");
            throw new Exception(" getOccupationIds :: Could not fetch values from occupation table" + e);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SurveyDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return occIds;
    }

    @Override
    public Map<Integer, String> getAggregateInfo(String townIdValue) throws Exception{
        
        Connection connection = DBConnection.getConnection();
        Map<Integer, String> aggregateTownInfo = new HashMap<Integer, String>();
        ResultSet rs = null;

        String query = "SELECT l.location_name,s.year,avg(population_m) as average_population FROM location l, survey s WHERE s.loc_id = l.location_id AND l.location_id = " + townIdValue + " GROUP BY s.year,l.location_name ORDER BY l.location_name";
        
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    aggregateTownInfo.put(rs.getInt("year"), rs.getString("average_population"));
                }
            }
            
        } catch(Exception e){
            throw new Exception("getAggregateInfo :: Could not fetch aggregate information" + e);
        }
        return aggregateTownInfo;
    }

    @Override
    public Map<String, Integer> executeQuery1() throws Exception {
        
        Connection connection = DBConnection.getConnection();
        Map<String, Integer> aggregateTownInfo = new HashMap<String, Integer>();
        ResultSet rs = null;

        String query = "SELECT l.location_name, COUNT(c.cit_id) as no_of_pple_surveyed " +
                       "FROM citizen c INNER JOIN survey s ON s.sur_id=c.sur_id " +
                       "INNER JOIN location l ON l.location_id=s.loc_id " +
                       "WHERE s.year=2012 AND c.gender='f' GROUP BY l.location_id;";
        
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    aggregateTownInfo.put(rs.getString("location_name").trim(), rs.getInt("no_of_pple_surveyed"));
                }
            }
            
        } catch(Exception e){
            throw new Exception("getAggregateInfo :: Could not fetch aggregate information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return aggregateTownInfo;
    }

    @Override
    public Map<String, Integer> executeQuery2() throws Exception {
        
        Connection connection = DBConnection.getConnection();
        Map<String, Integer> accidentTypeNumberMap = new HashMap<String, Integer>();
        ResultSet rs = null;

        String createView = "CREATE TEMPORARY TABLE qu2 AS "
                + "SELECT COUNT(a.acc_type) AS num, a.acc_type "
                + "FROM loc_accident AS a INNER JOIN location l ON l.location_id=a.loc_id "
                + "WHERE l.location_name='town A' GROUP BY a.acc_type";
        
        String query = "SELECT acc_type, MAX(num) as no_of_accidents FROM qu2";
        
       try {
            Statement st = connection.createStatement();
            st.executeUpdate(createView);
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    accidentTypeNumberMap.put(rs.getString("acc_type").trim(), rs.getInt("no_of_accidents"));
                }
            }            
        } catch(Exception e){
            throw new Exception("getAggregateInfo :: Could not fetch aggregate information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return accidentTypeNumberMap;
    }
    
    @Override
    public Double executeQuery3() throws Exception{
        
        ResultSet rs = null;
        Double prevalence = null;
        Connection connection = DBConnection.getConnection();
        
        String tempTable1 = "CREATE TEMPORARY TABLE survey_pop AS SELECT COUNT(c.cit_id) AS citizens, l.location_name, s.year "
         + "FROM citizen AS c INNER JOIN survey AS s ON s.sur_id=c.sur_id "
         + "INNER JOIN location l ON l.location_id=s.loc_id WHERE l.location_name='town B' and s.year=2014";
        
        String tempTable2 = "CREATE TEMPORARY TABLE diab AS SELECT count(c.cit_id) AS patients, l.location_name, s.year FROM "
                            + "citizen AS c "
                            + "INNER JOIN survey AS s ON s.sur_id=c.sur_id "
                            + "INNER JOIN location l ON l.location_id=s.loc_id "
                            + "INNER JOIN dis_cit dc ON dc.cit_id=c.cit_id "
                            + "INNER JOIN disease d ON dc.dis_id=d.disease_id "
                            + "WHERE l.location_name='town B' and s.year=2014 and d.disease_description='diabetes'";
        
        String query = "SELECT d.patients/sp.citizens as diabetes_prevalence FROM survey_pop sp,diab d";
        
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(tempTable1);
            st.executeUpdate(tempTable2);
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    prevalence = rs.getDouble("diabetes_prevalence");
                }
            }            
        } catch(Exception e){
            throw new Exception("executeQuery3 :: Could not fetch information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return prevalence;
    }

    @Override
    public Map<String, Double> executeQuery4() throws Exception {
        
        ResultSet rs = null;
        Map<String, Double> townMinPrevalence = new HashMap<String,Double>();
        Connection connection = DBConnection.getConnection();
        
        String tempTable1 = "CREATE TEMPORARY TABLE peop AS SELECT COUNT(c.cit_id) AS citizens, l.location_name,s.year "
                             + "FROM citizen AS c "
                             + "INNER JOIN survey AS s ON s.sur_id=c.sur_id "
                             + "INNER JOIN location l ON l.location_id=s.loc_id "
                             + "WHERE s.year=2012 group by l.location_name";

        String tempTable2 = "CREATE TEMPORARY TABLE peop1 AS SELECT COUNT(c.cit_id) AS patients,l.location_name,s.year "
                            + "FROM citizen AS c "
                            + "INNER JOIN survey AS s ON s.sur_id=c.sur_id "
                            + "INNER JOIN location l ON l.location_id=s.loc_id "
                            + "INNER JOIN dis_cit dc ON dc.cit_id=c.cit_id "
                            + "INNER JOIN disease d ON dc.dis_id=d.disease_id "
                            + "WHERE s.year=2012 AND d.disease_description='diabetes' GROUP BY l.location_name";

        String tempTable3 = "CREATE TEMPORARY TABLE diab_prev AS SELECT p1.patients/p.citizens AS prevalence, p1.location_name "
                            + "FROM peop p INNER JOIN peop1 p1 ON p1.location_name=p.location_name GROUP BY p1.location_name";

        String query = "SELECT location_name, min(prevalence) as min_prevalence from diab_prev";
        
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(tempTable1);
            st.executeUpdate(tempTable2);
            st.executeUpdate(tempTable3);
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    townMinPrevalence.put(rs.getString("location_name"), rs.getDouble("min_prevalence"));
                }
            }            
        } catch(Exception e){
            throw new Exception("executeQuery3 :: Could not fetch information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return townMinPrevalence;
    }

    @Override
    public String executeQuery5() throws Exception {
        
        ResultSet rs = null;
        String locationName = null;
        Connection connection = DBConnection.getConnection();
        
        String query = "Select l.location_name from location l where l.location_id not in (select a.loc_id from loc_accident a)";
        
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    locationName = rs.getString("location_name");
                }
            }            
        } catch(Exception e){
            throw new Exception("executeQuery3 :: Could not fetch information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return locationName;
    }
    
    @Override
    public Double executeQuery6() throws Exception {
        
        ResultSet rs = null;
        Double ratio = null;
        Connection connection = DBConnection.getConnection();
        
        String tempTable1 = "CREATE TEMPORARY TABLE hc_townC AS SELECT COUNT(h.hc_type) AS healthcenters, l.location_name FROM healthcare h "
                            + "INNER JOIN location l ON h.loc_id=l.location_id "
                            + "WHERE l.location_name = 'town C' GROUP BY l.location_name"; 

        String tempTable2 = "CREATE TEMPORARY TABLE citi AS SELECT SUM(s.population_m) AS citizens,l.location_name FROM survey s "
                            +"INNER JOIN location l ON s.loc_id=l.location_id "
                            +"WHERE l.location_name='town C' group by s.loc_id";

        String query = "SELECT citi.citizens/hc_townC.healthcenters AS ratio_health_resources FROM citi,hc_townC";
        
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(tempTable1);
            st.executeUpdate(tempTable2);
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    ratio = rs.getDouble("ratio_health_resources");
                }
            }            
        } catch(Exception e){
            throw new Exception("executeQuery3 :: Could not fetch information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return ratio;
    }
    
    @Override
    public Map<String, Double> getQuery7() throws Exception {
        
        ResultSet rs = null;
        Map<String, Double> changeInSmokingHabit = new HashMap<String,Double>();
        Connection connection = DBConnection.getConnection();
        
        String tempTable1 = "CREATE TEMPORARY TABLE v2014y AS SELECT COUNT(c.cit_id) AS c2014, s.loc_id AS loc2014 FROM citizen c "
                            +"INNER JOIN survey s ON c.sur_id=s.sur_id WHERE s.year=2014 AND c.smoke=1 GROUP BY s.loc_id";

        String tempTable2 = "CREATE TEMPORARY TABLE v2012y AS SELECT COUNT(c.cit_id) AS c2012, s.loc_id AS loc2012 FROM citizen c "
                            +"INNER JOIN survey s ON c.sur_id=s.sur_id WHERE s.year=2012 AND c.smoke=1 GROUP BY s.loc_id";

        String query = "SELECT location.location_name AS location, ((v2014y.c2014-v2012y.c2012)*100)/v2012y.c2012 AS change_in_per "
                        +"FROM v2012y,v2014y,location "
                        +"WHERE v2014y.loc2014=v2012y.loc2012 AND v2012y.loc2012=location.location_id";
        
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(tempTable1);
            st.executeUpdate(tempTable2);
            rs = st.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    changeInSmokingHabit.put(rs.getString("location"),rs.getDouble("change_in_per"));
                }
            }            
        } catch(Exception e){
            throw new Exception("executeQuery3 :: Could not fetch information" + e);
        } finally {
            if(rs != null){
                rs.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return changeInSmokingHabit;
        
    }
}