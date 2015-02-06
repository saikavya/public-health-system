package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import businesslayer.BusinessLogic;
import dto.LocationDTO;

public class ServiceLogic {
	
	
	public static List<LocationDTO> getLocationDetails(){
		
		List<LocationDTO> locationDTOList = new ArrayList<LocationDTO>();
		Map<Integer, String> locationIdNameMap = BusinessLogic.getLocationDetails();
		for(Integer key: locationIdNameMap.keySet()){
			LocationDTO locationDTO = new LocationDTO();
			locationDTO.setLocationId(key);
			locationDTO.setLocationName(locationIdNameMap.get(key));
			locationDTOList.add(locationDTO);
		}
		return locationDTOList;
	}
        
        public static int getMaxSurveyId(String locationName){
            
            int surveyId = BusinessLogic.getMaxSurveyId(locationName);
            return surveyId;
        }
        
        public static Map<Integer, String> getAggregateInformation(String townIdValue) throws Exception {
            
            Map<Integer, String> getAggregateTownInfo = null;
            try {
                getAggregateTownInfo = BusinessLogic.getAggregateInfoAboutTown(townIdValue);
            } catch(Exception e){
                throw new Exception("Could not fetch aggregated information for town"+ e);
            }
            return getAggregateTownInfo;
        }
        
        public static Map<String, Integer> getQuery1() throws Exception{
            
            Map<String, Integer> getCountTownMap = null;
            try {
                getCountTownMap = BusinessLogic.getQuery1();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 1 details"+ e);
            }
            return getCountTownMap;
        }
        
        public static Map<String, Integer> getQuery2() throws Exception{
            
            Map<String, Integer> accidentTypeNumberMap = null;
            try {
                accidentTypeNumberMap = BusinessLogic.getQuery2();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 2 details"+ e);
            }
            return accidentTypeNumberMap;
        }
        
        public static Double getQuery3() throws Exception {
            
            Double prevalence = null;
            try {
                prevalence = BusinessLogic.getQuery3();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 3 details"+ e);
            }
            return prevalence;
        }
        
        public static Map<String, Double> getQuery4() throws Exception{
            
            Map<String, Double> townMinPrevalence = null;
            try {
                townMinPrevalence = BusinessLogic.getQuery4();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 4 details"+ e);
            }
            return townMinPrevalence;
        }
        
        public static String getQuery5() throws Exception {
            
            String locationName = null;
            try {
                locationName = BusinessLogic.getQuery5();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 5 details" + e);
            }
            return locationName;
        }
        
        public static Double getQuery6() throws Exception {
            
            Double ratio = null;
            try {
                ratio = BusinessLogic.getQuery6();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 6 details"+ e);
            }
            return ratio;
        }
        
        public static Map<String, Double> getQuery7() throws Exception{
            
            Map<String, Double> changeInSmoking = null;
            try {
                changeInSmoking = BusinessLogic.getQuery7();
            } catch(Exception e){
                throw new Exception("Could not fetch Query 7 details"+ e);
            }
            return changeInSmoking;
        }
}
