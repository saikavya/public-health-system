package service;

import businesslayer.BusinessLogic;
import dto.CitizenDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HealthServlet
 */
@WebServlet("/HealthServlet")
public class HealthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            PrintWriter out = response.getWriter();
            try {
                CitizenDTO citizenDTO = new CitizenDTO();
                citizenDTO.setSurveyId(Integer.parseInt(request.getParameter("surveyId")));
                citizenDTO.setGender(request.getParameter("gender"));
                citizenDTO.setDateOfBirth(request.getParameter("dob"));
                citizenDTO.setEthnicity(request.getParameter("ethnicity"));
                citizenDTO.setMaritalStatus(request.getParameter("marital"));
                citizenDTO.setWeeklyFastFood(Integer.parseInt(request.getParameter("fastfood")));
                citizenDTO.setWeeklyFruits(Integer.parseInt(request.getParameter("fruits")));
                citizenDTO.setSmoking(Boolean.parseBoolean(request.getParameter("smoking")));
                citizenDTO.setAlcohol(Boolean.parseBoolean(request.getParameter("alcohol")));
                int citizenId = BusinessLogic.getNextCitizenId();
                citizenDTO.setCitizenId(citizenId);
                String[] occupations = request.getParameterValues("occupation");
                String[] diseases = request.getParameterValues("disease");
                
                BusinessLogic.insertCitizenDetails(citizenDTO,occupations,diseases);
                
                out.println("<h3>Citizen data inserted successfully</h3>");
                
            } catch(Exception e){
                
                out.println("<h3>Error while inserting citizen data in database!</h3>" + e);
            }
            

	}

}
