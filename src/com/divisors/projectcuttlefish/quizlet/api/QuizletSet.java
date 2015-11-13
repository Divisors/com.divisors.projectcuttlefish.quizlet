package com.divisors.projectcuttlefish.quizlet.api;

import java.lang.ref.WeakReference;
import java.util.Map;

import org.json.JSONObject;

public class QuizletSet {
	protected final WeakReference<Quizlet> parent;
	protected long lastModified;
	protected int id;
	public QuizletSet(Quizlet parent, JSONObject data) throws IllegalArgumentException {
		this.parent = new WeakReference<>(parent);
		this.update(data);
	}
	public void update() {
		
	}
	
	protected boolean update(JSONObject data) {
		lastModified = System.currentTimeMillis() / 1000L;
		if (data == null)
			return false;
		
		return true;
	}
	
	public class QuizletTerm implements Map.Entry<String, String> {
		
		protected String key;
		protected String value;
		protected int id;
		
		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getValue() {
			return value;
		}
		
		public int getId() {
			return id;
		}
		
		@Override
		public String setValue(String value) {
			throw new UnsupportedOperationException();
		}
		
	}
}
