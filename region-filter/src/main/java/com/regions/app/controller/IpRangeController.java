package com.regions.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for retrieving AWS IP ranges for selectable region.
 */
@RestController
public class IpRangeController {

	private static final Logger logger = LoggerFactory.getLogger(IpRangeController.class);

	private static final String AWS_IP_RANGES_URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";
	private static final String REGIONS_ALL = "ALL";
	private static final String REGION2 = "region";
	private static final String IP_PREFIX = "ip_prefix";
	private static final String PREFIXES = "prefixes";

	/**
	 * Retrieves IP ranges for the specified region and returns them as plain text.
	 *
	 * @param region the region(s) to retrieve IP ranges for
	 * @return the IP ranges for the specified region(s) as plain text
	 */
	@GetMapping(value = "/ip-ranges", produces = "text/plain")
	public String getIpRanges(@RequestParam(name = REGION2, defaultValue = "ALL") String region) throws IOException {
		logger.info("In getIpRanges(), endpoint : /ip-ranges");

		// Read the AWS IP range data from the URL
		URL url = new URL(AWS_IP_RANGES_URL);

		InputStream inputStream = url.openStream();

		// parse the JSON data using javax.json
		JsonReader reader = Json.createReader(inputStream);
		JsonObject rootObject = reader.readObject();
		reader.close();
		inputStream.close();

		StringBuilder result = new StringBuilder();

		// filter the IP range based on the selected region
		rootObject.getJsonArray(PREFIXES).forEach(ipRangeObject -> {
			JsonObject ipRange = (JsonObject) ipRangeObject;
			String ipPrefix = ipRange.getString(IP_PREFIX);
			String prefixRegion = ipRange.get(REGION2).toString();
			if (region != null
					&& (region.equals(REGIONS_ALL) || prefixRegion.toLowerCase().contains(region.toLowerCase()))) {
				result.append(ipPrefix).append("\n");
			}
		});

		return result.toString();
	}

}
