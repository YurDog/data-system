package com.data.system.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 钉钉自定义机器人
 * 
 * @author 小二狗
 *
 */
public class ChatbotSend {

	private static Logger logger = LoggerFactory.getLogger(ChatbotSend.class);

	public static final String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=99628edc4eec5b1068f9a8d62fc137fac7506b30ab657406700224432fd9b1d2";

	HttpClient httpclient = HttpClients.createDefault();

	public String send(String message, List<String> phones, boolean isAtAll) {
		TextMessage textMessage = new TextMessage(message);
		textMessage.setAtMobiles(phones);
		textMessage.setIsAtAll(true);
		String result = null;
		try {
			result = httpPost(textMessage);
		} catch (IOException e) {
			logger.error("ChatbotSend ERROR" + e.getMessage(), e);
		}
		return result;
	}

	public String httpPost(TextMessage textMessage) throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		StringEntity se = new StringEntity(textMessage.toJsonString(), "utf-8");
		httppost.setEntity(se);

		SendResult sendResult = new SendResult();
		HttpResponse response = this.httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			JSONObject obj = JSONObject.parseObject(result);

			Integer errcode = obj.getInteger("errcode");
			sendResult.setErrorCode(errcode);
			sendResult.setErrorMsg(obj.getString("errmsg"));
			sendResult.setIsSuccess(errcode.equals(Integer.valueOf(0)));
		}
		return sendResult.toString();
	}

	public static void main(String args[]) throws Exception {

		HttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");

		String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"我就是我, 是不一样的烟火\"}}";
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}

	public class TextMessage {
		private String text;
		private List<String> atMobiles;
		private boolean isAtAll;

		public TextMessage(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public List<String> getAtMobiles() {
			return this.atMobiles;
		}

		public void setAtMobiles(List<String> atMobiles) {
			this.atMobiles = atMobiles;
		}

		public boolean isAtAll() {
			return this.isAtAll;
		}

		public void setIsAtAll(boolean isAtAll) {
			this.isAtAll = isAtAll;
		}

		public String toJsonString() {
			Map<String, Object> items = new HashMap<>();
			items.put("msgtype", "text");

			Map<String, String> textContent = new HashMap<>();
			if (StringUtils.isEmpty(this.text)) {
				throw new IllegalArgumentException("text should not be blank");
			}
			textContent.put("content", this.text);
			items.put("text", textContent);

			Map<String, Object> atItems = new HashMap<>();
			if ((this.atMobiles != null) && (!this.atMobiles.isEmpty())) {
				atItems.put("atMobiles", this.atMobiles);
			}
			if (this.isAtAll) {
				atItems.put("isAtAll", Boolean.valueOf(this.isAtAll));
			}
			items.put("at", atItems);

			return JSONObject.toJSONString(items);
		}
	}

	public class SendResult {
		private boolean isSuccess;
		private Integer errorCode;
		private String errorMsg;

		public boolean isSuccess() {
			return this.isSuccess;
		}

		public void setIsSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public Integer getErrorCode() {
			return this.errorCode;
		}

		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMsg() {
			return this.errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		public String toString() {
			Map<String, Object> items = new HashMap();
			items.put("errorCode", this.errorCode);
			items.put("errorMsg", this.errorMsg);
			items.put("isSuccess", Boolean.valueOf(this.isSuccess));
			return JSONObject.toJSONString(items);
		}
	}
}
