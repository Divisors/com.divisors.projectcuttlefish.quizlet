package com.divisors.projectcuttlefish.quizlet.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.jdeferred.Deferred;
import org.jdeferred.DoneFilter;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONObject;

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
	Promise<QuizletUser, IllegalArgumentException, Void> getUser(String name) {
		return queryAPI("GET", "users/"+name, null)
				.<QuizletUser, IllegalArgumentException, Void>then((DoneFilter<JSONObject, QuizletUser>)((result)->(new QuizletUser(this, result))));
	}
	public Deferred<JSONObject, RuntimeException, Void> queryAPI(String method, String path, JSONObject data) {
		Deferred<JSONObject, RuntimeException, Void> result = new DeferredObject<>();
		try {
			HttpsURLConnection conn = (HttpsURLConnection)(new URL("https://api.quizlet.com/2.0/"+path).openConnection());
			conn.setRequestMethod(method!=null?method:"GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + new String(accessToken));
			if (data != null) {
				conn.setDoInput(true);
				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
					data.write(writer);
				}
			}
			System.out.println(""+conn.getResponseCode()+" " + conn.getResponseMessage());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			
		}
		return result;
	}
}
