package com.regions.app.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * JUnit Test to test the retrieval of AWS IP ranges for selectable region 
 */
public class IpRangeControllerTest {

	private static final String IP_RANGES_URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";

	private final HttpClient httpClient = mock(HttpClient.class);
	private final StatusLine statusLine = mock(StatusLine.class);
	private final HttpResponse httpResponse = mock(HttpResponse.class);
	private final IpRangeController ipRangeController = new IpRangeController();
	private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ipRangeController).build();

	@Test
	public void testGetIpRanges() throws Exception {
		when(httpClient.execute(new HttpGet(IP_RANGES_URL))).thenReturn(httpResponse);
		when(httpResponse.getStatusLine()).thenReturn(statusLine);
		when(statusLine.getStatusCode()).thenReturn(200);

		mockMvc.perform(MockMvcRequestBuilders.get("/ip-ranges").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetIpRangesWithRegion() throws Exception {
		when(httpClient.execute(new HttpGet(IP_RANGES_URL))).thenReturn(httpResponse);
		when(httpResponse.getStatusLine()).thenReturn(statusLine);
		when(statusLine.getStatusCode()).thenReturn(200);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/ip-ranges").param("region", "us-west-1").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
