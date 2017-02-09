package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;

public interface IDaoMail {
	List<MailConfig> ListMailPersonnalisable();
	MailConfig getMailByCode(String code);
	MailConfig getMailByCode(Integer id);
	MailConfig SaveOrUpdate(MailConfig mail);
	MailHdr getMailHdrById(Integer id);
	MailHdr SaveOrUpdate(MailHdr mailHdr);
}
