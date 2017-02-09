/**
 * 
 */
package com.netPoint.applications.site.util;

import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public class Parameters {
	
	public static Revendeur revendeurUse=null;
	public static MailHdr mailhdr=null;
	public static Integer group=null;
	public static User userconnect=null;
	public static Integer nouveaugroup;
	public static Societe societe=null;
	public static String Role_User=null;
	/**
	 * @param role_User the role_User to set
	 */
	public static void setRole_User(String role_User) {
		Role_User = role_User;
	}

	/**
	 * @return the societe
	 */
	public static Societe getSociete() {
		return societe;
	}

	/**
	 * @param societe the societe to set
	 */
	public static void setSociete(Societe societe) {
		Parameters.societe = societe;
	}

	/**
	 * @return the nouveaugroup
	 */
	public static int getNouveaugroup() {
		return nouveaugroup;
	}

	/**
	 * @param nouveaugroup the nouveaugroup to set
	 */
	public static void setNouveaugroup(int nouveaugroup) {
		Parameters.nouveaugroup = nouveaugroup;
	}

	/**
	 * @return the userconnect
	 */
	public static User getUserconnect() {
		return userconnect;
	}

	/**
	 * @param userconnect the userconnect to set
	 */
	public static void setUserconnect(User userconnect) {
		Parameters.userconnect = userconnect;
	}

	/**
	 * @return the group
	 */
	public static int getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public static void setGroup(Integer group) {
		Parameters.group = group;
	}

	/**
	 * @return the mailhdr
	 */
	public static MailHdr getMailhdr() {
		return mailhdr;
	}

	/**
	 * @param mailhdr the mailhdr to set
	 */
	public static void setMailhdr(MailHdr mailhdr) {
		Parameters.mailhdr = mailhdr;
	}

	/**
	 * @return the revendeur
	 */
	public static Revendeur getRevendeur() {
		return revendeurUse;
	}

	/**
	 * @param revendeur the revendeur to set
	 */
	public static void setRevendeur(Revendeur revendeur) {
		Parameters.revendeurUse = revendeur;
	}

	public static void initialise() {
		group=null;
		userconnect=null;
		revendeurUse=null;
		mailhdr=null;
		societe=null;
		nouveaugroup= null;
	}
	
}
