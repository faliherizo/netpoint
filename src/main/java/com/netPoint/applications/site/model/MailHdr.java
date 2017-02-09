/**
 * 
 */
package com.netPoint.applications.site.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Faliherizo
 *
 */
@Entity
@Table(name="mail_hdr")
public class MailHdr implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Integer id;
	@Column(name="CODE")
	private String code;
	@Column(name="mail_support")
	private String mailsupport;
	@Column(name="phone_support")
	private String phonesupport;
	@Column(name="mail_reception")
	private String mailreception;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL, mappedBy="mailhdr")
	@JoinColumn(name="ID_MAIL_HDR")
	private List<MailConfig> maildtl= new ArrayList<MailConfig>();

	public String getMailreception() {
		return mailreception;
	}
	public void setMailreception(String mailreception) {
		this.mailreception = mailreception;
	}
	/**
	 * @return the mailsupport
	 */
	public String getMailsupport() {
		return mailsupport;
	}
	/**
	 * @param mailsupport the mailsupport to set
	 */
	public void setMailsupport(String mailsupport) {
		this.mailsupport = mailsupport;
	}
	/**
	 * @return the phonesupport
	 */
	public String getPhonesupport() {
		return phonesupport;
	}
	/**
	 * @param phonesupport the phonesupport to set
	 */
	public void setPhonesupport(String phonesupport) {
		this.phonesupport = phonesupport;
	}
	/**
	 * @return the maildtl
	 */
	public List<MailConfig> getMaildtl() {
		return maildtl;
	}
	/**
	 * @param maildtl the maildtl to set
	 */
	public void setMaildtl(List<MailConfig> maildtl) {
		this.maildtl = maildtl;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	} 
	
}
