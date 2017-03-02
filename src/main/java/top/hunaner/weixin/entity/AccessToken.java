package top.hunaner.weixin.entity;

public class AccessToken {
	private String accessToken;
	private long expiresin;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpiresin() {
		return expiresin;
	}
	public void setExpiresin(long expiresin) {
		this.expiresin = expiresin;
	}
	
}
