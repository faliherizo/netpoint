/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;

/**
 * @author Faliherizo
 *
 */
public interface IServiceMailConfig {
	List<MailConfig> ListMailPersonnalisable();
	MailConfig getMailByCode(String code);
	MailConfig getMailByCode(Integer id);
	MailConfig SaveOrUpdate(MailConfig mail);
	MailHdr getMailHdrById(Integer id);
	MailHdr SaveOrUpdate( MailHdr mailhdr);
	MailHdr Save(MailHdr mailhdr);
	void TestSendMail(MailHdr mailhdr);
}
