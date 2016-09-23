package lol.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the lolheros database table.
 * 
 */
//@Entity
//@Table(name="heros")
//@NamedQuery(name="Lolhero.findAll", query="SELECT l FROM Lolhero l")
public class LolheroForm implements Serializable {
//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

//	@Lob
//	private byte[] headpic;

//	@Column(name="headpic_url")
//	private String headpicUrl;

//	@Column(name="NAMECN")
//	@NotNull
	@Size(min=2, max=30,message="字符长度必须在2到30之间")
	private String nameCn;

//	@Column(name="NAMEEN")
//	@NotNull
	@Size(min=2, max=30,message="字符长度必须在2到30之间")
	private String nameEn;
	@NotNull
	@Size(min=2, max=30,message="字符长度必须在2到30之间")
	private String nickname;

//	@Lob
//	private byte[] sounds;

//	@Column(name="sounds_url")
//	@NotNull
//	private String soundsUrl;
	@Size(min=2,max=300)
	private String story;
	
	private String type;

	public LolheroForm() {
	}

	public LolheroForm(long long1, String string, String string2) {
		this.id=long1;
		this.nameCn=string;
		this.nameEn=string2;
	}

	@Override
	public String toString() {
		return "LolheroForm [id=" + id + ", nameCn=" + nameCn + ", nameEn=" + nameEn + "]";
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
//
//	public byte[] getHeadpic() {
//		return this.headpic;
//	}
//
//	public void setHeadpic(byte[] headpic) {
//		this.headpic = headpic;
//	}
//
//	public String getHeadpicUrl() {
//		return this.headpicUrl;
//	}
//
//	public void setHeadpicUrl(String headpicUrl) {
//		this.headpicUrl = headpicUrl;
//	}
//
	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

//	public byte[] getSounds() {
//		return this.sounds;
//	}
//
//	public void setSounds(byte[] sounds) {
//		this.sounds = sounds;
//	}
//
//	public String getSoundsUrl() {
//		return this.soundsUrl;
//	}
//
//	public void setSoundsUrl(String soundsUrl) {
//		this.soundsUrl = soundsUrl;
//	}
//
	public String getStory() {
		return this.story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}