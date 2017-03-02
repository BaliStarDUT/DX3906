package top.hunaner.weixin.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import top.hunaner.weixin.controller.AccessTokenServlet;
import top.hunaner.weixin.entity.AccessTokenInfo;

@Component
public class ScheduledTasks {
	final String appId = "wx438a019f03a2c758";
	final String appSecret = "34acd375ac856acd30d3671925aad8ee";
	@Scheduled(fixedRate = 7000*1000)
	public void init(){
		JSONObject object = new JSONObject();
		AccessTokenServlet accessTokenServlet = new AccessTokenServlet();
		AccessTokenInfo.accessToken  = accessTokenServlet.getAccessToken(appId, appSecret);
	}
}
