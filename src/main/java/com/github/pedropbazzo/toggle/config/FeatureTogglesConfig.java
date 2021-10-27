package com.github.pedropbazzo.toggle.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("feature")
public class FeatureTogglesConfig {

	private Map<String, Boolean> toggles = new HashMap<>();
	
	public Map<String, Boolean> getToggles() {
		return toggles;
	}
	
	public boolean isEnabled(String key) {
		return toggles.get(key);
	}
	
	public void enableToggle(String key, Boolean value) throws Exception {
		
//		if(!featureToggle.isEmpty()) {
//			for(Map.Entry<String, Boolean> entry : toggles.entrySet()) {
//				toggles.replace(entry.getKey(), entry.getValue());
//			}
//		}
		
		
		if(toggles.containsKey(key)) {
			toggles.replace(key, value);
		} else {			
			throw new Exception("Não foi possível alterar os valores do feature toggle");
		}
		
	}
	
}
