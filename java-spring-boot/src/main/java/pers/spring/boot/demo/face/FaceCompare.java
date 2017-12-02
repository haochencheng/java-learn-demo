package pers.spring.boot.demo.face;

import java.io.InputStream;

import com.google.common.base.Strings;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import pers.spring.boot.demo.util.Base64Util;
import pers.spring.boot.demo.util.PropertiesUtil;

/**
 * Created by cc on 2017/11/16
 */
public class FaceCompare {

	private static final String APIKEY = PropertiesUtil.getConfig("api.key");
	private static final String APISECERT = PropertiesUtil.getConfig("api.key");
	private static final String IMAGE_1 = "/static/image/z1.png";
	private static final String IMAGE_2 = "/static/image/z2.png";
	private static final String URL_API = PropertiesUtil.getConfig("api.compate");

	public static void main(String[] args) throws Exception {
		FaceCompare faceCompare = new FaceCompare();
		faceCompare.compare();
	}

	public boolean compare() throws Exception {
		InputStream image1 = FaceCompare.class.getResourceAsStream(IMAGE_1);
		InputStream image2 = FaceCompare.class.getResourceAsStream(IMAGE_2);
		String imageStr1 = Base64Util.encode(image1);
		String imageStr2 = Base64Util.encode(image2);
		if (!Strings.isNullOrEmpty(imageStr1)) {
			HttpResponse<JsonNode> jsonResponse = Unirest.post(URL_API).field("api_key", APIKEY)
					.field("api_secret", APISECERT).field("image_base64_1", imageStr1)
					.field("image_base64_2", imageStr2).asJson();
			if (jsonResponse.getStatus() == 200) {
				System.out.println(jsonResponse.getBody().toString());
			}
			System.out.println(jsonResponse.getStatus());
		}
		return true;
	}

}
