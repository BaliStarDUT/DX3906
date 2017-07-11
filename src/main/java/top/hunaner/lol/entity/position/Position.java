package top.hunaner.lol.entity.position;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the position database table.
 * 
 */
@Entity
@Table(name = "position")
//@NamedQuery(name="Position.findAll", query="SELECT p FROM position p")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sid;

	private double accuracy;

	private double altitude;

	@Column(name = "altitudeAccuracy" )
	private double altitudeAccuracy;

	private double heading;

	private double latitude;

	private double longitude;

	private double speed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public Position() {
	}

	public Position(String jack, String bauer) {
		System.out.println(jack+"---"+bauer);
		this.setAccuracy(0);
		this.setAltitude(1);
		this.setAltitudeAccuracy(3);
		this.setHeading(4);
		this.setLatitude(5);
		this.setLongitude(6);
		this.setSid(3);
		this.setSpeed(8);
		this.setTimestamp(new Date());
		System.out.println(this.toString());
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public double getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getAltitudeAccuracy() {
		return this.altitudeAccuracy;
	}

	public void setAltitudeAccuracy(double altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
	}

	public double getHeading() {
		return this.heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("Position{时间：%s，纬度：%f，经度：%f，精度：%f，海拔：%f，海拔精度：%f，方向：%f，速度:%f}",
				this.timestamp.toString(),this.latitude,this.longitude,this.accuracy,
				this.altitude,this.altitudeAccuracy,this.heading,this.speed);
	}
	
}