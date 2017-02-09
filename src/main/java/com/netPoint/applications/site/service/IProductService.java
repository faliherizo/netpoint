/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public interface IProductService {
	List<Revendeur> getListRevByIdprod(Integer prod);
	List<Produit> getListProduct();
	void addOrUpdateProduct(Produit produit);
	void deleteProduct(Produit produit);
	void deleteProduct(int pIdProduct);
	Produit findProduitById(Integer id);
	List<Produit> getListProductByFrns();
	List<Produit> getListProductByFrns(Integer fournisseur);
	Fournisseur getFns(Integer idfrns);
	List<Produit> getListProductByRev(Integer revendeur);
	List<Produit> getListProductByFrns(Integer fournisseur,  Revendeur revendeur);
	Fournisseur findFrnsByUser(User userconnect);
	Produit getProductById(Integer id);
}
