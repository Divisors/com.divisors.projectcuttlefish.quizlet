package com.divisors.projectcuttlefish.quizlet.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Quizlet {
	private final char[] accessToken;
	public Quizlet(char[] apikey) {
		this.accessToken = apikey;
	}
	QuizletClass createClass(String name, String description, int schoolId) {
		return null;
	}
	QuizletClass getClass(int id) {
		return null;
	}
	QuizletUser getUser(int id) {
		return null;
	}
	public Map<String, String> queryAPI(String method, String path) throws IOException {
		HttpsURLConnection conn = (HttpsURLConnection)(new URL("https://api.quizlet.com/2.0/"+path).openConnection());
		conn.setRequestMethod(method!=null?method:"GET");
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", "Bearer " + new String(accessToken));
		System.out.println(""+conn.getResponseCode()+" " + conn.getResponseMessage());
		try (BufferedReader br =new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
		return null;
	}
}
