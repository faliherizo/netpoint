package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;

public interface IEchuRenouvelement {

	List<ViewRenouvellementDuMois> ListgetRenouvellementByCritere(
			String critereProduit, String critereRevedeur, Integer idUser);

}
