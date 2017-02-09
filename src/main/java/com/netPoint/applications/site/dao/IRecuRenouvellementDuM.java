package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;

public interface IRecuRenouvellementDuM{

	List<ViewRenouvellementDuMois> ListgetRenouvellementByCritere(
			final String critereProduit,final String critereRevedeur, Integer idUser);

}
