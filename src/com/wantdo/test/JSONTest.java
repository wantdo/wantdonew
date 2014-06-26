package com.wantdo.test;

import static org.junit.Assert.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.wantdo.utils.JSONUtils;

public class JSONTest {

	@Test
	public void testGetCurrentState() throws Exception{
		HttpClient httpClient=new DefaultHttpClient();
		String uri="http://api.ickd.cn/?id=103340&secret=2cd1a18a0d8687bd7e791298faca6f30&com=shentong&nu=668746038407&type=json&encode=utf8&ord=desc";
		HttpGet httpGet=new HttpGet(uri);
		HttpResponse response=httpClient.execute(httpGet);
		String str=EntityUtils.toString(response.getEntity());
		System.out.println(str);
		System.out.println(JSONUtils.getCurrentState(str));
	}

}
