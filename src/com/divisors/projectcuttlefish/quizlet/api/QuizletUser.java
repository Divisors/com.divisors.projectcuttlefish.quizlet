package com.divisors.projectcuttlefish.quizlet.api;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizletUser {
	protected final Quizlet parent;
	protected String username;
	protected String accountType;
	protected String profileImage;
	protected int id;
	protected long lastModified;
	protected QuizletUser(Quizlet parent, JSONObject data) {
		this.parent = parent;
	}
	public void update() throws JSONException {
		parent.queryAPI("GET", "users/" + username, new JSONObject("{last_modified: "+lastModified + '}'))
		.then(this::update);
	}
	protected void update(JSONObject data) {
		lastModified = System.currentTimeMillis() / 1000L;
		if (data == null)
			return;
		try {
			this.username = data.getString("username");
			this.accountType = data.getString("account_type");
			this.profileImage = data.getString("profile_image");
			this.id = data.getInt("id");
			
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
	}
}
