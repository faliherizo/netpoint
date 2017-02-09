/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netPoint.applications.site.dao.IDaoProduct;
import com.netPoint.applications.site.dao.IDaoProductRevendeur;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
@Service
public class ProductServiceImpl implements IProductService {
	public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private IDaoProduct daoProduct;
	/**
	 * @param daoProduct the daoProduct to set
	 */
	public void setDaoProduct(IDaoProduct daoProduct) {
		this.daoProduct = daoProduct;
	}
	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#getListProduct()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Produit> getListProduct() {
		// TODO Auto-generated method stub
		return daoProduct.getListProduct();
	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#addOrUpdateProduct(com.netPoint.applications.site.model.Produit)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addOrUpdateProduct(Produit produit) {
		daoProduct.addOrUpdateProduct(produit);

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#deleteProduct(com.netPoint.applications.site.model.Produit)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteProduct(Produit produit) {
		daoProduct.deleteProduct(produit);

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#deleteProduct(int)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteProduct(int pIdProduct) {
		daoProduct.deleteProduct(pIdProduct);

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#findProduitById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Produit findProduitById(Integer id) {
		return daoProduct.findProduitById(id);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Produit> getListProductByFrns() {
		
		return daoProduct.getListProductByFrns();
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Produit> getListProductByFrns(Integer fournisseur) {
		return daoProduct.getProduitByFrns(fournisseur);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Produit> getListProductByFrns(Integer fournisseur, Revendeur revendeur) {
		
		return daoProduct.getProduitByFrns(getFns(fournisseur),revendeur);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Fournisseur getFns(Integer idfrns) {
		
		return daoProduct.getFns(idfrns);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Produit> getListProductByRev(Integer revendeur) {
		
		return daoProduct.getListProductByRev(revendeur);
	}
	@Override
	public List<Revendeur> getListRevByIdprod(Integer prod) {
		
		return daoProduct.getListRevByIdprod(prod);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Fournisseur findFrnsByUser(User userconnect) {
		
		return daoProduct.findFrnsByUser(userconnect);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Produit getProductById(Integer id) {
		
		return daoProduct.getProductById(id);
	}

	

}
