package com.arvato.euhub.pocmaven.web;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Welcom {

	@RequestMapping("/")
	public String showWelcome(@RequestParam(value="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("today",Calendar.getInstance());
		return "showWelco";
	}
}
