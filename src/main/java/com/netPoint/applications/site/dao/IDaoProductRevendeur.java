package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;

public interface IDaoProductRevendeur 
{
	List<ProduitRevendeur> getListProduct();
	void addOrUpdateProduct(ProduitRevendeur produit);
	void deleteProduct(ProduitRevendeur produitRevendeur);
	void deleteProduct(int pIdProduct);
	ProduitRevendeur findProduitById(Integer id);
	
}
