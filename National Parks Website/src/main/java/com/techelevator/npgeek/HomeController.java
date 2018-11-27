package com.techelevator.npgeek;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDAO;
import com.techelevator.npgeek.model.SurveyResults;
import com.techelevator.npgeek.model.WeatherDAO;

@SessionAttributes("scale")
@Controller
public class HomeController {

	@Autowired
	private ParkDAO parkDAO;

	@Autowired
	private SurveyDAO surveyDAO;

	@Autowired
	private WeatherDAO weatherDAO;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		List<Park> parks = parkDAO.getAllParks();
		map.addAttribute("parks", parks);
		return "homePage";
	}

	@RequestMapping("/parkDetails")
	public String displayParkDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String parkCodeParam = request.getParameter("parkImageName").toUpperCase();
			request.setAttribute("park", parkDAO.getParkByParkCode(parkCodeParam));
			request.setAttribute("weather", weatherDAO.getWeatherByParkCode(parkCodeParam));
			return "parkDetail";
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(path = "/parkDetails", method = RequestMethod.POST)
	public String displayTempConversion(HttpServletRequest request, ModelMap map) {
		String tempScaleParam = request.getParameter("scale");
		// String parkCodeParam = request.getParameter("parkImageName").toUpperCase();

		String parkCodeParam = request.getParameter("parkCode");
		request.setAttribute("park", parkDAO.getParkByParkCode(parkCodeParam));
		request.setAttribute("weather", weatherDAO.getWeatherByParkCode(parkCodeParam));
		map.addAttribute("scale", tempScaleParam);

		return "parkDetail";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurvey(Model model) {
		if (!model.containsAttribute("survey")) {
			model.addAttribute("survey", new Survey());
		}
		return "survey";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String doSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "survey";
		}
		surveyDAO.saveSurvey(survey);
		return "redirect:/surveyResults";
	}

	@RequestMapping(path = "/surveyResults", method = RequestMethod.GET)
	public String getSurveyResults(ModelMap map) {
		List<SurveyResults> results = surveyDAO.getSurveyResults();
		map.addAttribute("surveyResults", results);
		return "surveyResults";
	}
}
