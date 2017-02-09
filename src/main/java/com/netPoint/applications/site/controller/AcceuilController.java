package com.netPoint.applications.site.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;

import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.netPoint.applications.site.birt.BirtEngine;
import com.netPoint.applications.site.dto.ProduitRevendeurDto;
import com.netPoint.applications.site.dto.RevendeurDto;
import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.model.forms.RenouvellementDuMoisEchu;
import com.netPoint.applications.site.model.forms.RenouvellementMois;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.RenouvellementMoteurSA;
import com.netPoint.applications.site.util.Parameters;

@Controller
public class AcceuilController extends AbstractController{
	@Autowired
	private RenouvellementMoteurSA moteurSA;
	@Autowired
    protected InterfaceService interfaceService;
	@Autowired
	protected IServiceCommande iServiceCommande;
	@Autowired
	private IServiceUser iServiceUser;
	/**
	 * @param iServiceUser the iServiceUser to set
	 */
	public void setiServiceUser(IServiceUser iServiceUser) {
		this.iServiceUser = iServiceUser;
	}
	/**
	 * @param moteurSA the moteurSA to set
	 */
	public void setMoteurSA(RenouvellementMoteurSA moteurSA) {
		this.moteurSA = moteurSA;
	}

	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}


	
	private static String TT_REVENDEUR = "0";
	private static String TT_PRODUIT = "0";
	
	@PostConstruct
	public void init(){
		
	}
	
	@RequestMapping(value = "/renouvellementDuMois", method = RequestMethod.GET)
    public String renouvellementDuMois(@ModelAttribute("formRecherher")RenouvellementMois renouvellementMois,ModelMap modelMap) {
	      try{
	    	  int id = moteurSA.findCurrentUser().getId();
	    	  List<Commande> listcommande =new  ArrayList<Commande>();
	          List<Revendeur> revendeur=interfaceService.findAllRevendeur();//moteurSA.getPerimetreRevendeur(id);
	          List<Produit> listproduit = interfaceService.findAllProduit();
			  modelMap.addAttribute("listproduit",listproduit);
		      modelMap.addAttribute("listRevendeur",revendeur);
		     /*  if (renouvellementMois.getProduit()!=null) {
				  if (renouvellementMois.getRevendeur()!=null) */
					  listcommande = moteurSA.findAllCommandeByProduitAndRevend(renouvellementMois.getRevendeur(), renouvellementMois.getProduit(), id, true);
				/*  else
				  listcommande = moteurSA.findAllCommandeByProduitAndRevendeur(renouvellementMois.getProduit(), id, true);
			}
		       else
		    	   if(renouvellementMois.getRevendeur()!=null)
		    		   listcommande = moteurSA.findAllCommandeByProduitAndRevend(renouvellementMois.getRevendeur(), id, true);
		    	   else
		    	   listcommande = moteurSA.findAllCommandeByProduitAndRevendeur(id, true);*/
		       modelMap.addAttribute("listcommand", listcommande);
		    	   
		  }
	      catch (Exception e) {
	    	  modelMap.addAttribute("error","Une erreur est survenu lors du chargement de la page");
		  }
      return "accueil/renouvellementDuMois";
    }
	
	/*@RequestMapping(value="/rechercheRenouvellementDuMois")
	public String getRechercheRenouvellement(@ModelAttribute("formRecherher")RenouvellementMois renouvellementMois,ModelMap modelMap){
		/*try{
			List<RevendeurDto> revendeurDtos=moteurSA.getAllByIdUser();
	        List<ProduitRevendeurDto> produitRevendeurDtos=moteurSA.getAllById();
			modelMap.addAttribute("listProduitRevendeur",produitRevendeurDtos);
		    modelMap.addAttribute("listRevendeur",revendeurDtos);
		  //  List<ViewRenouvellementDuMois> viewRenouvellementDuMois=moteurSA.getRenouvellementDuMois(renouvellementMois.getProduit().trim(),renouvellementMois.getRevendeur().trim());
		    modelMap.addAttribute("viewRenouvellementDuMois",viewRenouvellementDuMois);
		}catch(Exception exception){
			modelMap.addAttribute("error","Une erreur est survenu lors du chargement de la page");	
		}
		
		return "accueil/renouvellementDuMois";
	}
	*/
   @RequestMapping(value = "/renouvellementEchus", method = RequestMethod.GET)
   public String renouvellementEchus(@ModelAttribute("formRecherher") RenouvellementDuMoisEchu renouvellementMois,ModelMap modelMap) {
		      try{
		    	  int id = moteurSA.findCurrentUser().getId();
		    	  List<Commande> listcommande =new  ArrayList<Commande>();
		          List<Revendeur> revendeur= interfaceService.findAllRevendeur();//moteurSA.getPerimetreRevendeur(id);
		          List<Produit> listproduit = interfaceService.findAllProduit();
				  modelMap.addAttribute("listproduit",listproduit);
			      modelMap.addAttribute("listRevendeur",revendeur);
			      /* if (renouvellementMois.getProduit()!=null) {
					  if (renouvellementMois.getRevendeur()!=null) */
						  listcommande = moteurSA.findAllCommandeByProduitAndRevend(renouvellementMois.getRevendeur(), renouvellementMois.getProduit(), id, false);
					 /* else
					  listcommande = moteurSA.findAllCommandeByProduitAndRevendeur(renouvellementMois.getProduit(), id, false);
				}
			       else
			    	   if(renouvellementMois.getRevendeur()!=null)
			    		   listcommande = moteurSA.findAllCommandeByProduitAndRevend(renouvellementMois.getRevendeur(), id, false);
			    	   else
			    	   listcommande = moteurSA.findAllCommandeByProduitAndRevendeur(id, false);*/
			       modelMap.addAttribute("listcommand", listcommande);
			    	   
			  }
		      catch (Exception e) {
		    	  modelMap.addAttribute("error","Une erreur est survenu lors du chargement de la page");
			  }
       return "accueil/renouvellementEchus";
   }
   
   /*@RequestMapping(value="/rechercheRenouvellementEchu")
	public String getRechercheRenouvellementEchu(@ModelAttribute("formRecherher")RenouvellementMois renouvellementMois,ModelMap modelMap){
	/*	try{
			List<RevendeurDto> revendeurDtos=moteurSA.getAllByIdUser();
	        List<ProduitRevendeurDto> produitRevendeurDtos=moteurSA.getAllById();
			modelMap.addAttribute("listProduitRevendeur",produitRevendeurDtos);
		    modelMap.addAttribute("listRevendeur",revendeurDtos);
		    List<ViewRenouvellementDuMois> viewRenouvellementDuMois=moteurSA.getRenouvellementEchu(renouvellementMois.getProduit().trim(),renouvellementMois.getRevendeur().trim());
		    modelMap.addAttribute("viewRenouvellementEchu",viewRenouvellementDuMois);
		}catch(Exception exception){
			modelMap.addAttribute("error","Une erreur est survenu lors du chargement de la page");	
		}
		return "accueil/renouvellementEchus";*/
	  /* return null;
	}*/
   //Reports vente par mois
   /*protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   // String format = RequestUtils.getStringParameter(request, "format", "html");
		 
		    IReportRunnable runnable = engine.openReportDesign(new ClassPathResource("vente.rptdesign").getInputStream());
		    IRunAndRenderTask runAndRenderTask = engine.createRunAndRenderTask(runnable);
		 
		    // Although it's called HtmlRenderOption, it is used for all formats
		    HTMLRenderOption htmlRenderOption = new HTMLRenderOption();
		    //htmlRenderOption.setOutputFormat(format);
		    htmlRenderOption.setOutputStream(response.getOutputStream());
		 
		   // runAndRenderTask.setAppContext(renderOptions);
		    runAndRenderTask.setRenderOption(htmlRenderOption);
		    runAndRenderTask.run();
		 
		    return null;
	  }*/

		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
				HttpServletResponse arg1) throws Exception {
			
			return null;
		}
		 @ModelAttribute("get_frns_list")
		 public List<Fournisseur> get_List_Frns() {
				return iServiceUser.getListFournisseur();
		 }
		@ModelAttribute("listfournisseur")
		public List<Fournisseur> getListFrns(){
			List<Fournisseur> fournisseurs =null; 
			if(Parameters.group!=null)
				fournisseurs= interfaceService.getListFrnst(Parameters.group);
			return fournisseurs;
				
		}
		 @ModelAttribute("revendeurList")
		 public List<Revendeur> populateRevendeurList() {
		      return interfaceService.findAllRevendeur();
		    }
  /* protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

   String reportName = request.getParameter("ReportName");
   ServletContext sc = request.getSession().getServletContext();
   reportEngine = BirtEngine.getBirtEngine(sc);
   IReportRunnable runnable = null;
   runnable = reportEngine.openReportDesign( sc.getRealPath("/Reports")+"/"+reportName );
   IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(runnable);

   HTMLRenderOption options = new HTMLRenderOption();
   options.setOutputFormat("html");
   options.setOutputStream(response.getOutputStream());
   options.setImageHandler(new HTMLServerImageHandler());
   options.setBaseImageURL(request.getContextPath()+"/images");
   options.setImageDirectory(sc.getRealPath("/images"));
   runAndRenderTask.setRenderOption(options);
   runAndRenderTask.run();

   return null;
   }
*/
		 @ModelAttribute("access_admin")
			public boolean get_access_admin() {
				Boolean access= false;
			    if(Parameters.Role_User.equals("SUP") || Parameters.Role_User.equals("ADM") || Parameters.Role_User.equals("GRS") || Parameters.Role_User.equals("REV"))
			    	access=true;
			   return access;
			}
		 @ModelAttribute("access_frns")
			public boolean get_access_frns() {
				Boolean access= false;
			    if( Parameters.Role_User.equals("GRS"))
			    	access=true;
			   return access;
			}
		 
		  @RequestMapping(value = "/commandesMoisEnCours", method = RequestMethod.GET)
		    public ModelAndView commandesMoisEnCours(ModelMap model) {
		    	//Instance des configurations sur le parameters
		    	Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		    	User user = interfaceService.findUserByMail(auth.getName());
		    	//Parameters.userconnect =user;
		    	Parameters.userconnect=user; 
		    	
		    	if(user.getGroup() !=null)
		    		Parameters.setGroup(user.getGroup());
		    	else
		    		Parameters.setGroup(null);
		    	if(user.getRevendeur()!=null && user.getRevendeur().getSociete()!=null)
		    		Parameters.setSociete(user.getRevendeur().getSociete());
		    	if(user.getProfil().getId()==3){
		    			List<Revendeur> revendeurs=interfaceService.findAllRevendeurByMail(user.getLogin());
		        		Parameters.setSociete(revendeurs.get(0).getSociete());
		        		Parameters.revendeurUse=revendeurs.get(0);
		    	}
		    	//List<Produit> produits= iProductService.getListProductByFrns();
		    	List<Produit> produits = interfaceService.findAllProduit();
		    	model.addAttribute("produits", produits);
		        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
		        etatCommandeList = interfaceService.findAllEtatCommande();
		       /* if(Parameters.group!=null)
		        	model.addAttribute("get_frns_list", interfaceService.getListFrnst(Parameters.group));
		        else*/
		        	model.addAttribute("get_frns_list", interfaceService.getListFrnst());
		        return new ModelAndView("accueil/commandesMoisEnCours", new ModelMap(etatCommandeList));
		    }
		    	 
}
