package com.netPoint.applications.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.netPoint.applications.site.dao.IEchuRenouvelement;
import com.netPoint.applications.site.dao.IRecuRenouvellementDuM;
import com.netPoint.applications.site.dao.InterfaceDaoProduitRevendeur;
import com.netPoint.applications.site.dao.InterfaceRevendeurDao;
import com.netPoint.applications.site.dao.InterfaceUserDao;
import com.netPoint.applications.site.dto.ProduitRevendeurDto;
import com.netPoint.applications.site.dto.RevendeurDto;
import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;


@Service
public class RenouvellementMoteurSAImpl implements RenouvellementMoteurSA {
   
	@Autowired
	private InterfaceRevendeurDao revendeurDaoInterfaceRevendeurDao; 
	
	@Autowired
	private InterfaceUserDao userDao;
	
	/**
	 * @param revendeurDao the revendeurDao to set
	 */
	public void setRevendeurDao(InterfaceRevendeurDao revendeurDao) {
		this.revendeurDaoInterfaceRevendeurDao = revendeurDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(InterfaceUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param iEchuRenouvelement the iEchuRenouvelement to set
	 */
	public void setiEchuRenouvelement(IEchuRenouvelement iEchuRenouvelement) {
		this.iEchuRenouvelement = iEchuRenouvelement;
	}

	/**
	 * @param interfaceDaoProduitRevendeur the interfaceDaoProduitRevendeur to set
	 */
	public void setInterfaceDaoProduitRevendeur(
			InterfaceDaoProduitRevendeur interfaceDaoProduitRevendeur) {
		this.interfaceDaoProduitRevendeur = interfaceDaoProduitRevendeur;
	}
	@Autowired
	private IRecuRenouvellementDuM iRecuRenouvellementDuM;
	
	@Autowired
	private IEchuRenouvelement iEchuRenouvelement;
	
	@Autowired
	private InterfaceDaoProduitRevendeur interfaceDaoProduitRevendeur;
	
	@Override
	public List<RevendeurDto> getAllByIdUser() {
		/*List<Revendeur> revendeurs = revendeurDao.getAllByIdUser(findCurrentUser().getId());
		List<RevendeurDto> revendeurDtos = null;
		if(revendeurs!=null){
			List<Integer> idUser = new ArrayList<Integer>();
			revendeurDtos = new ArrayList<RevendeurDto>();
			for(Revendeur revendeur: revendeurs){
				idUser.add(revendeur.getUser().getId());
			}
			List<User> list = userDao.getListUserById(idUser);
			for(User user: list){
				RevendeurDto revendeurDto = new RevendeurDto(user.getId(), user.getNom(), user.getPrenom());
				revendeurDtos.add(revendeurDto);
			}
		}*/
		return null;
	}
	
	/**
	 * Recupere l'utilisateur en cours (null si aucun connecte)
	 * 
	 * @return Utilisateur
	 */
	@Override
	public User findCurrentUser() {
		User currentUser = null;
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			org.springframework.security.core.userdetails.User customUserCmv = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
			if (customUserCmv != null) {
				currentUser = userDao.findByLogin(customUserCmv.getUsername());
			}
		}
		return currentUser;
	}
    /**
     * 
     */
	@Override
	public List<ProduitRevendeurDto> getAllById() {
		List<ProduitRevendeur> revendeurs = interfaceDaoProduitRevendeur.getAllById(findCurrentUser().getId());
		List<ProduitRevendeurDto> revendeurDtosRevendeur = null;
		if(revendeurs!=null){
			revendeurDtosRevendeur = new ArrayList<ProduitRevendeurDto>();
			for(ProduitRevendeur revendeurP: revendeurs){
				ProduitRevendeurDto revendeurDto = new ProduitRevendeurDto(revendeurP);
				revendeurDtosRevendeur.add(revendeurDto);
			}
		}
		return revendeurDtosRevendeur;
	}
	
	@Override
	public List<ViewRenouvellementDuMois> getRenouvellementDuMois(final String critereProduit,final String critereRevedeur){
		return iRecuRenouvellementDuM.ListgetRenouvellementByCritere(critereProduit,critereRevedeur, findCurrentUser().getId());
	}
	
	@Override
	public List<ViewRenouvellementDuMois> getRenouvellementEchu(final String critereProduit,final String critereRevedeur){
		return iEchuRenouvelement.ListgetRenouvellementByCritere(critereProduit,critereRevedeur, findCurrentUser().getId());
	}

	@Override
	public List<Commande> findAllCommandeByProduitAndRevendeur(Integer IdUser, boolean moisCourant) {
		// TODO Auto-generated method stub
		return revendeurDaoInterfaceRevendeurDao.findAllCommandeByProduitAndRevendeur(IdUser,moisCourant);
	}

	@Override
	public List<Commande> findAllCommandeByProduitAndRevendeur(
			Integer idProduit, Integer IdUser, boolean moisCourant) {
		// TODO Auto-generated method stub
		return revendeurDaoInterfaceRevendeurDao.findAllCommandeByProduitAndRevendeur(idProduit,IdUser, moisCourant);

	}

	@Override
	public List<Commande> findAllCommandeByProduitAndRevend(
			Integer idRevendeur, Integer IdUser, boolean moisCourant) {
		return revendeurDaoInterfaceRevendeurDao.findAllCommandeByProduitAndRevend(idRevendeur, IdUser, moisCourant);
	}

	@Override
	public List<Commande> findAllCommandeByProduitAndRevend(
			Integer idRevendeur, Integer idProduit, Integer IdUser, boolean moisCourant) {
		return revendeurDaoInterfaceRevendeurDao.findAllCommandeByProduitAndRevend(idRevendeur, idProduit, IdUser, moisCourant);
	}
	@Override
	public List<Revendeur> getPerimetreRevendeur(int IdCurrent){
		Revendeur revendeur =revendeurDaoInterfaceRevendeurDao.getByIdUser(IdCurrent);
		List<Revendeur> list = revendeurDaoInterfaceRevendeurDao.getAllRevendeurPerimetre(revendeur.getId());
		list.add(revendeur);
		return list;
	}
}
