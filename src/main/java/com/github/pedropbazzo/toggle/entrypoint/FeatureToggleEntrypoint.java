package com.github.pedropbazzo.toggle.entrypoint;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pedropbazzo.toggle.config.FeatureTogglesConfig;

@Component
@Endpoint(id = "feature-toggles")
public class FeatureToggleEntrypoint {
	
	private final FeatureTogglesConfig featureToggles;
	
	@Autowired
	public FeatureToggleEntrypoint(FeatureTogglesConfig featureToggles) {
		this.featureToggles = featureToggles;
	}
	
	@ReadOperation
	public Map<String, Boolean> getAllFeatureToggles() {
		return featureToggles.getToggles();
	}
	
	@WriteOperation
	public ResponseEntity<?> setFeatureToogle(
			@RequestParam(required = true) String key, 
			@RequestParam(required = true) Boolean value) throws Exception {
		
		if(!key.isEmpty() && value != null) {
			featureToggles.enableToggle(key, value);
			
			return new ResponseEntity<String>("Feature Toggle: " + key + " changed.",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
}
