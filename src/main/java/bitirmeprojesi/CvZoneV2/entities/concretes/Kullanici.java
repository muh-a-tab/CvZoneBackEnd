package bitirmeprojesi.CvZoneV2.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="kullanici")
@Entity
public class Kullanici {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kullanici_id")
	private int kullaniciId;
	
	@Column(name="kullanici_tip")
	private int kullaniciTip;
	
	@Column(name= "ad_soyad")
	private String adSoyad;
	
	@Column(name= "e_mail")
	private String eMail;
	
	@Column(name= "sehir")
	private String sehir;
	
	@Column(name= "sifre")
	private String sifre;
	
	@Column(name= "resim",columnDefinition = "text")
	private String resim;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date(System.currentTimeMillis());
	
}
