package businesslayer;

import java.util.Map;

import java.util.List;

public interface ISurveyDAO {

    public abstract Map<Integer, String> getLocationDetails();
        
    public abstract int getMaxSurveyId(String locationName);

    public abstract int getNextCitizenId();

    public void insertIntoCitizenTable(int citizenId, int surveyId, String gender, String dateOfBirth, String ethnicity, String maritalStatus, int weeklyFastFood, int weeklyFruits, boolean alcohol, boolean smoking) throws Exception;
    
    public void insertIntoOccCitizenMappingTable(List<Integer> occupationIds, int citizenId) throws Exception;
    
    public void insertIntoDiseaseCitizenMapTables(List<Integer> diseaseIds, int citizenId) throws Exception;
    
    public List<Integer> getDiseaseIds(List<String> diseases) throws Exception;
    
    public List<Integer> getOccupationIds(List<String> occupations) throws Exception;

    public Map<Integer, String> getAggregateInfo(String townIdValue) throws Exception;

    public Map<String, Integer> executeQuery1() throws Exception;

    public Map<String, Integer> executeQuery2() throws Exception;

    public Double executeQuery3() throws Exception;
    
    public Map<String, Double> executeQuery4() throws Exception;

    public String executeQuery5() throws Exception;
    
    public Double executeQuery6() throws Exception;
    
    public Map<String, Double> getQuery7() throws Exception;
}
