package top.hunaner.weixin.entity;
/**
 *
 * @date 2016年5月21日 下午2:07:50
 * @author James Yang
 * @version 1.0
 * @since
 */
public class Music {
	private String title;
	private String description;
	private String musicurl;
	private String hqmusicurl;
	private String thumbmediaid;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMusicurl() {
		return musicurl;
	}
	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}
	public String getHqmusicurl() {
		return hqmusicurl;
	}
	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}
	public String getThumbmediaid() {
		return thumbmediaid;
	}
	public void setThumbmediaid(String thumbmediaid) {
		this.thumbmediaid = thumbmediaid;
	}
}
