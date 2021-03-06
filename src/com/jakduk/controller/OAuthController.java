package com.jakduk.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

import com.jakduk.service.CommonService;
import com.jakduk.service.UserService;

/**
 * @author <a href="mailto:phjang1983@daum.net">Jang,Pyohwan</a>
 * @company  : http://jakduk.com
 * @date     : 2014. 7. 17.
 * @desc     :
 */

@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Resource
	LocaleResolver localeResolver;

	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/daum/callback2", method = RequestMethod.GET)
	public String daumCallback(Model model,
			@RequestParam(value = "code") String authCode) {
		
		String token_url = "https://apis.daum.net/oauth2/token";
		String client_id = "1234567890";
		String client_secret = "75fba1";
		
		URI url = null;
		try {
			url = new URI(token_url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("code", authCode);
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);
		params.put("redirect_uri", "http://localhost:8080/jakduk/oauth/daum/callback03");
		
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		Map<String, Object> accessToken = (Map<String, Object>) restTemplate.postForObject(url, params, Map.class);
		
		logger.debug("accessToken=" + accessToken);
		
		String atk = (String) accessToken.get("access_token");
		Integer exp = (Integer) accessToken.get("expires_in");
		
		model.addAttribute("accessToken", atk);
		model.addAttribute("expiresIn", exp);
		
		logger.debug("accessToken=" + accessToken);
		logger.debug("model=" + model);
		
		return "home/oauth02";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String lang,
			@RequestParam(required = false) Integer status,
			Model model) {
		
		Locale locale = localeResolver.resolveLocale(request);
		String language = commonService.getLanguageCode(locale, lang);
		
		userService.getOAuthProfile(model, language, status);
		
		return "oauth/profile";
	}
	
}
