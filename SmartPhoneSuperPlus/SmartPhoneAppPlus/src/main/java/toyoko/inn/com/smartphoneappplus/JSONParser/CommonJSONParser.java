package toyoko.inn.com.smartphoneappplus.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class CommonJSONParser{

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private ArrayList<NameValuePair> arrayList = null;

	public ArrayList<NameValuePair> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<NameValuePair> arrayList) {
		this.arrayList = arrayList;
	}

	public JSONObject getJSONData(String url) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			if(getArrayList()!=null){
				UrlEncodedFormEntity encodePost = new UrlEncodedFormEntity(getArrayList(),"UTF-8");
				encodePost.setContentEncoding(HTTP.UTF_8);
				post.setEntity(encodePost);
			}
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			//Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			//Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return jObj;
	}
}