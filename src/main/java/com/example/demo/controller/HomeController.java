package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LocationStates;
import com.example.demo.repository.LocationStatesRepository;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class HomeController 
{
	@Autowired
	private CoronaVirusDataServices crnService;
	
	@Autowired
	private LocationStatesRepository locationStatesRepository;


	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStates> allstates=crnService.getAllstates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		locationStatesRepository.saveAll(allstates);
		return "home";
	}
	
	@GetMapping(value ="/viewchart")
	@ResponseBody
	public ModelAndView viewChart() {
		return new ModelAndView("viewchart");
	}
	
	@GetMapping(value ="/collectChartData", produces="application/json")
	@CrossOrigin("*")
	@ResponseBody
	public List<LocationStates> getChartData() {
		return locationStatesRepository.findAll();
	}

}
