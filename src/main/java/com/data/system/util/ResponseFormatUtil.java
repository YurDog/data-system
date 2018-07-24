package com.data.system.util;

import com.alibaba.fastjson.JSONObject;

public class ResponseFormatUtil {
	public enum ResponseCode {
		SUCCESS("0000", "成功"), PARAMS_ERROR("1001", "参数错误"), SYSTEM_ERROR("9999", "系统繁忙");
		private String code;
		private String message;

		private ResponseCode(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	public static JSONObject success(Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ResponseCode.SUCCESS.code);
		jsonObject.put("message", ResponseCode.SUCCESS.message);
		jsonObject.put("data", data);
		return jsonObject;
	}

	public static JSONObject error() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ResponseCode.SYSTEM_ERROR.code);
		jsonObject.put("message", ResponseCode.SYSTEM_ERROR.message);
		return jsonObject;
	}

	public static JSONObject paramsError() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ResponseCode.PARAMS_ERROR.code);
		jsonObject.put("message", ResponseCode.PARAMS_ERROR.message);
		return jsonObject;
	}

	public static JSONObject error(ResponseCode responseCode) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", responseCode.code);
		jsonObject.put("message", responseCode.message);
		return jsonObject;
	}

}
