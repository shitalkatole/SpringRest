package com.companyportal.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.companyportal.app.entity.Employee;
import com.companyportal.app.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayRegistrationForm(Model model) {
		Employee employee = new Employee();

		model.addAttribute("employee", employee);
		return "employeeform";

		// return new ModelAndView("employeeform", "employee", employee);

	}

//	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Employee saveEmployeeData(@RequestBody Employee employee) {
		if (employee.getEmployeeId() != null && employee.getEmployeeId() > 0) {
			employeeService.updateEmployee(employee);
		} else {
			employeeService.saveEmployeeData(employee);
		}

		return employee;
	}

//	@PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public Employee saveEmployee(@RequestBody Employee employee) {
//		return employee;
//	}

	@GetMapping(value = "/employees")
	@ResponseBody
	public List<Employee> getEmployeesData() {
		return employeeService.getEmployeesData();
	}

	@DeleteMapping(value = "/employee/{employeeId}")
	@ResponseBody
	public void deleteEmployee(@PathVariable int employeeId) {
		employeeService.deleteEmployee(employeeId);
		System.out.println("employee deleted successfully :" +employeeId);
//		return "redirect:/employeelist";
	}

	@RequestMapping(value = "/employeelist", method = RequestMethod.GET)
	public String getEmployeesData(Model model) {
		List<Employee> employeeList = employeeService.getEmployeesData();

		model.addAttribute("employeeList", employeeList);
		return "employeelist";
	}

//	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.GET)

	@RequestMapping(value = "/updateEmployee{employeeId}", method = RequestMethod.GET)
	public ModelAndView updateEmployeeData(@RequestParam Integer employeeId) {
		System.out.println("updateEmployee employeeId : " + employeeId);
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		ModelAndView model = new ModelAndView();
		model.addObject("employee", employee);
		return new ModelAndView("employeeform", "employee", employee);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getEmployeesByName(@RequestParam("name") String name, Model model) {
		List<Employee> employeeList = employeeService.getEmployeesByName(name);
		model.addAttribute("employeeList", employeeList);
		return "employeelist";
	}

}