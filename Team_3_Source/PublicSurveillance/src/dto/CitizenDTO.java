package dto;

public class CitizenDTO {
    
    private int surveyId;
    private int citizenId;
    private String gender;
    private String dateOfBirth;
    private String ethnicity;
    private String maritalStatus;
    private int weeklyFastFood;
    private int weeklyFruits;
    private boolean isAlcohol;
    private boolean isSmoking;

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }
        
        
        
        
    public int getSurveyId() {
            return surveyId;
    }
    public void setSurveyId(int surveyId) {
            this.surveyId = surveyId;
    }
    public String getGender() {
            return gender;
    }
    public void setGender(String gender) {
            this.gender = gender;
    }
    public String getDateOfBirth() {
            return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
    }
    public String getEthnicity() {
            return ethnicity;
    }
    public void setEthnicity(String ethnicity) {
            this.ethnicity = ethnicity;
    }
    public String getMaritalStatus() {
            return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
    }
    public int getWeeklyFastFood() {
            return weeklyFastFood;
    }
    public void setWeeklyFastFood(int weeklyFastFood) {
            this.weeklyFastFood = weeklyFastFood;
    }
    public int getWeeklyFruits() {
            return weeklyFruits;
    }
    public void setWeeklyFruits(int weeklyFruits) {
            this.weeklyFruits = weeklyFruits;
    }
    public boolean isAlcohol() {
            return isAlcohol;
    }
    public void setAlcohol(boolean isAlcohol) {
            this.isAlcohol = isAlcohol;
    }
    public boolean isSmoking() {
            return isSmoking;
    }
    public void setSmoking(boolean isSmoking) {
            this.isSmoking = isSmoking;
    }
}
