package businesslayer;

import java.util.Map;

import datalayer.SurveyDetailsDAO;
import dto.CitizenDTO;
import java.util.Arrays;
import java.util.List;

public class BusinessLogic {
	
	
    public static Map<Integer, String> getLocationDetails(){

        ISurveyDAO surveyDAO = new SurveyDetailsDAO();
        return surveyDAO.getLocationDetails();
    }

    public static int getMaxSurveyId(String locationName){

        ISurveyDAO surveyDAO = new SurveyDetailsDAO();
        return surveyDAO.getMaxSurveyId(locationName);

    }

    public static int getNextCitizenId() {
        
        ISurveyDAO surveyDAO = new SurveyDetailsDAO();
        return surveyDAO.getNextCitizenId();
    }
    
    public static void insertCitizenDetails(CitizenDTO citizenDTO, String[] occupations, String[] diseases) throws Exception {
        
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            surveyDAO.insertIntoCitizenTable(citizenDTO.getCitizenId(), citizenDTO.getSurveyId(), citizenDTO.getGender(), citizenDTO.getDateOfBirth(), 
                    citizenDTO.getEthnicity(), citizenDTO.getMaritalStatus(), citizenDTO.getWeeklyFastFood(), citizenDTO.getWeeklyFruits(), citizenDTO.isAlcohol(), citizenDTO.isSmoking());
           
            List<Integer> occupationIds = surveyDAO.getOccupationIds(Arrays.asList(occupations));
            surveyDAO.insertIntoOccCitizenMappingTable(occupationIds, citizenDTO.getCitizenId());
            
            if(null != diseases && diseases.length !=0){
                List<Integer> diseaseIds = surveyDAO.getDiseaseIds(Arrays.asList(diseases));
                surveyDAO.insertIntoDiseaseCitizenMapTables(diseaseIds, citizenDTO.getCitizenId());
            }
            
        } catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
        
        
    }

    public static Map<Integer, String> getAggregateInfoAboutTown(String townIdValue) throws Exception {
        
        Map<Integer,String> aggregateTownInfo = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            aggregateTownInfo = surveyDAO.getAggregateInfo(townIdValue);
            
        } catch(Exception e){
            throw new Exception("Failed to fetch aggregate town info" + e);
        }
        return aggregateTownInfo;
    }

    public static Map<String, Integer> getQuery1() throws Exception {
        
        Map<String, Integer> countPeopleTownMap = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            countPeopleTownMap = surveyDAO.executeQuery1();
            
        } catch(Exception e){
            throw new Exception("Failed to fetch aggregate town info" + e);
        }
        return countPeopleTownMap;
        
    }

    public static Map<String, Integer> getQuery2() throws Exception {
        Map<String, Integer> accidentTypeNumberMap = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            accidentTypeNumberMap = surveyDAO.executeQuery2();
            
        } catch(Exception e){
            throw new Exception("Failed to fetch query 2 details" + e);
        }
        return accidentTypeNumberMap;
    }

    public static Double getQuery3() throws Exception {
        
        Double prevalence = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            prevalence = surveyDAO.executeQuery3();

        } catch(Exception e){
            throw new Exception("Failed to fetch query 3 details" + e);
        }
        return prevalence;
    }

    public static Map<String, Double> getQuery4() throws Exception{
        
        Map<String, Double> townPrevalenceMap = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            townPrevalenceMap = surveyDAO.executeQuery4();
            
        } catch(Exception e){
            throw new Exception("Failed to fetch query 4 details" + e);
        }
        return townPrevalenceMap;
    }

    public static String getQuery5() throws Exception {
        String locationName = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            locationName = surveyDAO.executeQuery5();
        } catch(Exception e){
            throw new Exception("Failed to fetch query 5 details" + e);
        }
        return locationName;
    }

    public static Double getQuery6() throws Exception{
        
        Double ratio = null;
        try {
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            ratio = surveyDAO.executeQuery6();
        } catch(Exception e){
            throw new Exception("Failed to fetch query 6 details" + e);
        }
        return ratio;
    }
    
    public static Map<String, Double> getQuery7() throws Exception {
        
        Map<String, Double> changeInSmokingHabit = null;
        try {
            
            ISurveyDAO surveyDAO = new SurveyDetailsDAO();
            changeInSmokingHabit = surveyDAO.getQuery7();
            
        } catch(Exception e){
            throw new Exception("Failed to fetch query 7 details" + e);
        }
        return changeInSmokingHabit;
        
        
    }

}
