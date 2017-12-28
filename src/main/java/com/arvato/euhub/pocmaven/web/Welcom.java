package com.arvato.euhub.pocmaven.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Welcom {
	private static final Logger LOGGER = LoggerFactory.getLogger(Welcom.class);

	@RequestMapping("/")
	public String showWelcome(@RequestParam(value="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("today",Calendar.getInstance());
		return "showWelco";
	}
	
	@RequestMapping(value="/api/welco", method=RequestMethod.POST)
	public @ResponseBody POCResponseJSON apiPost(@ModelAttribute POCResponseJSON resJSON) {
		LOGGER.info("API called");
		return resJSON;
	}
	
	@RequestMapping(value="/api/welco/json", method=RequestMethod.POST)
	public @ResponseBody POCResponseJSON apiJSONPost(HttpServletRequest request) {
		LOGGER.info("JSON API called");
		POCResponseJSON resJSON = new POCResponseJSON();
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				Charset charset = Charset.forName("UTF-8");
				String strBuf = IOUtils.toString(inputStream, charset);
				JSONObject jsonObj = new JSONObject(strBuf);
				resJSON.setHeader(jsonObj.getString("header"));
				resJSON.setBody(jsonObj.getString("body"));
			}
			
		} catch (Exception ex) {
			
		}
		return resJSON;
	}

	@RequestMapping(value="/api/welco/json-model", method=RequestMethod.POST)
	public @ResponseBody POCResponseJSON apiJSONModelPost(@RequestBody POCResponseJSON resJSON) {
		LOGGER.info("JSON model API called");
		return resJSON;
	}
}
