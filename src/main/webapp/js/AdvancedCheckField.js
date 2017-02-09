/* Fonctions de contrôle de champs de formulaires coté client */
//Diverses expressions régulières utiles

// Test sur la longueur d'une chaîne
var regExpEmpty=/^$/g;										// Accepte une chaine vide
var regExp8Chars=/^[0-9a-zA-Z]{8,}$/g;		// Accepte une chaine d'au moins 8 carctères alphanumeriques (pour un mot de passe par exemple).
var regExpAlphabeticMinuscule=/[a-z]+/g; 	// Accepte une chaine alphabétique en minuscules
var regExpAlphabeticMajuscule=/[A-Z]+/g; 	// Accepte une chaine alphabétique en majuscules
var regExpNumeric=/[0-9]+/g; 							// Accepte une chaine numérique

// Test le type de caractère dans une chaîne
var regExpAlphanumeric=/^[a-zA-Z0-9_]+$/;									// Accepte une chaine alphanumérique
var regExpAlphanumericWithWhitespace=/[0-9a-zA-Z ]+/g;	// Accepte une chaine alphanumérique + ' '
var regExpAlphabetic=/[a-zA-Z]+/g;											// Accepte une chaine alphabétique
var regExpNumeric=/[0-9]+/g;														// Accepte une chaine numérique

// Test le type d'une valeur (int, double etc...)
var regExpInt=/^[0-9]+$/g;																			// Accepte une chaine de type 'int'
var regExpDouble=/^[-+]?[0-9]+(\.[0-9]+)?$/g;										// Accepte une chaine de type 'double'
var regExpFloat=/^[-+]?[0-9]+(\.[0-9]+)?([eE][-+]?[0-9]+)?$/g;	// Accepte une chaine de type 'float'
var regExpTime=/^([01][0-9]|2[0123])\:([012345][0-9])(\:([012345][0-9])(.([0-9]{3})+)?)?$/g;		 // Accepte une chaine de type 'time'. Ex : 12:51 ou 21:45:35.654
var regExpFrenchDate=/^(0[1-9]|[12][0-9]|3[01])[\- \/\.](0[1-9]|1[012])[\- \/\.]\d\d$/g;  // date au format jj/mm/aaaa ou jj-mm-aaaa ou jj mm aaaa ou jj.mm.aaaa avec aaaa compris entre 1900 et 2099.
var regExpEnglishDate=/^\d\d[\- \/\.](0[1-9]|1[012])[\- \/\.](0[1-9]|[12][0-9]|3[01])$/g; // idem ci-dessus mais format anglais (Ex : aaaa/mm/jj)
var regExpBoolean=/^true|false$/g;															// Accepte une chaine de type 'boolean'

// Test de valeurs administratives française
var regExpCodePostal=/^([A-Z]+[A-Z]?\-)?[0-9]{1,2} ?[0-9]{3}$/g; // Accepte une chaine de type 'code postal'. Ex : F-33370 ou 33 370 ou 33370 ou F-1 370
var regExpTelephoneFixe=/^(01|02|03|04|05)[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}$/g; // Accepte un numero de téléphone de type 'fixe'. Ex : 01.34.12.52.30 ou 0134125230
var regExpTelephonePortable=/^(06)[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}$/g; // Accepte un numero de téléphone de type 'portable'.
var regExpTelephoneNational=/^(0[1234568])[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}$/g; // Accepte un numero de téléphone de type 'national' y compris numéros en '08'.
//var regExpTelephoneInternational=/^(\+[0-9]{2})[ \.\-]?[0-9][ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}$/g;	// Accepte un numero de téléphone de type 'international'. Ex : (+33) 1 34 12 52 30 (parenthèses non prises en compte)
var regExpTelephoneInternational=/^(\(\+[0-9]{2}\))[ \.\-]?[0-9][ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{2}$/g; // Accepte un numero de téléphone de type 'international'. Ex : (+33) 1 34 12 52 30 (parenthèses prises en compte)
var regExpNumeroSecuriteSociale=/^[12][ \.\-]?[0-9]{2}[ \.\-]?(0[1-9]|[1][0-2])[ \.\-]?([0-9]{2}|2A|2B)[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{2}$/g; // Accepte un numero de sécurité sociale français. Ex : 1 85 34 33 354 450 45
var regExpTVAIntracommunautaire=/^[A-Z]{2}[ \.\-]?[0-9]{2}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{3}$/g;		// Accepte un numero de TVA Intra-communautaire. Ex : FR 02 254 254 254
var regExpNumeroSiren=/^[0-9]{3}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{3}$/g; // Accepte un numero SIREN. Ex : 254 254 254
var regExpNumeroSiret=/^[0-9]{3}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{3}[ \.\-]?[0-9]{5}$/g; // Accepte un numero SIRET. Ex : 254 254 254 12345
var regExpCodeApe=/^[0-9]{2}[ \.\-]?[0-9]{1} ?[a-zA-Z]$/g;           // Accepte un code APE. Ex : 25.4Z

// Test de chaînes liées à internet
var regExpEmailAdress=/^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/g;										// Accepte une adresse email. Ex : toto@toto.com
var regExpIpAdress=/\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/g;	// Accepte une adresse ip. Ex : 192.168.0.1
var regExpDomainName=/^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$/g;													// Accepte un nom de domaine. Ex : toto.com
var regExpUrl=/^(((ht|f)tp(s?))\:\/\/)?(([a-zA-Z0-9]+([@\-\.]?[a-zA-Z0-9]+)*)(\:[a-zA-Z0-9\-\.]+)?@)?(www.|ftp.|[a-zA-Z]+.)?[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,})(\:[0-9]+)?\/?/g;			// Accepte une url ftp, http ou https, avec ou sans login/mot de passe, avec ou sans numero de port. Ex : http://www.toto.com, ftp://toto:toto@ftp.toto.com:21/

var regExpHexColor=/^#[0-9A-Fa-f]{6}$/g; // Accepte une couleur hexadécimale

// Les deux fonction suivantes servent à identifier si une chaine de caractère est compatible ou non 
// avec une expression régulière passée en paramètre
function matchRegExp(chaine, RegExpr)
{
	var resultat = chaine.match(RegExpr);
	if(resultat!=null && resultat.length==1) return true;
	else return false;
}

function doesntMatchRegExp(chaine, regExpr)
{
	if(matchRegExp(chaine, regExpr)) return false;
	else return true;
}

/*
 * les fonctions de contrôle suivantes prennent toutes comme argument 
 * la valeur de l'attribut "value" d'un champ de formulaire de type "text" ou "password"
 */
function isEmpty(chaine)
{
	return matchRegExp(chaine, regExpEmpty);
}

function isNotEmpty(chaine)
{
	return doesntMatchRegExp(chaine, regExpEmpty);
}

function isNot8CharsLength(chaine)
{
	return doesntMatchRegExp(chaine, regExp8Chars);
}

function isNotAlphanumeric(chaine)
{
	return doesntMatchRegExp(chaine, regExpAlphanumeric);
}

function isNotAlphanumericWithWhitespace(chaine)
{
	return doesntMatchRegExp(chaine, regExpAlphanumericWithWhitespace);
}

function isNotAlphabetic(chaine)
{
	return doesntMatchRegExp(chaine, regExpAlphabetic);
}

function isNotNumeric(chaine)
{
	return doesntMatchRegExp(chaine, regExpNumeric);
}

function isNotInt(chaine)
{
	return doesntMatchRegExp(chaine, regExpInt);
}

function isNotDouble(chaine)
{
	return doesntMatchRegExp(chaine, regExpDouble);
}

function isNotFloat(chaine)
{
	return doesntMatchRegExp(chaine, regExpInt);
}

function isNotBoolean(chaine)
{
	return doesntMatchRegExp(chaine, regExpBoolean);
}

function isNotTime(chaine)
{
	return doesntMatchRegExp(chaine, regExpTime);
}

/*
 * Test la validité d'une date
 *   fr : date au format français (voir regExpFrenchDate)
 *   en : date au format anglais  (voir regExpEnglishDate)
 */
function isNotDate(chaine, mode)
{
	switch (mode)
	{
		case "fr" : 
			return doesntMatchRegExp(chaine, regExpFrenchDate);
			break;
		case "en" :
			return doesntMatchRegExp(chaine, regExpEnglishDate);
			break;
		default : 
			return doesntMatchRegExp(chaine, regExpFrenchDate);
			break;
	}
}

function isNotCodePostal(chaine)
{
	return doesntMatchRegExp(chaine, regExpCodePostal);
}

/*
 * Les modes possibles sont :
 *	fixe: numéro de téléphone fixe
 *	port: numéro de téléphone portable
 *	nati: numéro de téléphone nationale
 *	inte: numéro de téléphone internationale
 */
function isNotTelephone(chaine, mode)
{
	switch (mode)
	{
		case "fixe" : 
			return doesntMatchRegExp(chaine, regExpTelephoneFixe);
			break;
		case "port" :
			return doesntMatchRegExp(chaine, regExpTelephonePortable);
			break;
		case "nati" :
			return doesntMatchRegExp(chaine, regExpTelephoneNational);
			break;
		case "inte" :
			return doesntMatchRegExp(chaine, regExpTelephoneInternational);
			break;
		default : 
			return doesntMatchRegExp(chaine, regExpTelephoneNational);
			break;
	}
}

function isNotNumeroSecuriteSociale(chaine)
{
	return doesntMatchRegExp(chaine, regExpNumeroSecuriteSociale);
}

function isNotTVAIntracommunautaire(chaine)
{
	return doesntMatchRegExp(chaine, regExpTVAIntracommunautaire);
}

function isNotNumeroSiren(chaine)
{
	return doesntMatchRegExp(chaine, regExpNumeroSiren);
}

function isNotNumeroSiret(chaine)
{
	return doesntMatchRegExp(chaine, regExpNumeroSiret);
}

function isNotCodeApe(chaine)
{
	return doesntMatchRegExp(chaine, regExpCodeApe);
}

function isNotEmailAdress(chaine)
{
	return doesntMatchRegExp(chaine, regExpEmailAdress);
}

function isNotIpAdress(chaine)
{
	return doesntMatchRegExp(chaine, regExpIpAdress);
}

function isNotDomainName(chaine)
{
	return doesntMatchRegExp(chaine, regExpDomainName);
}

function isNotUrl(chaine)
{
	return doesntMatchRegExp(chaine, regExpUrl);
}

function isNotHexColor(chaine)
{
	return doesntMatchRegExp(chaine, regExpHexColor);
}

// compte le nombre de chiffres dans la chaîne
function countNumeric(chaine)
{
	var nb= 0;
	var resultat = chaine.match(regExpNumeric);
	if(resultat)
		for(i=0;i<resultat.length; ++i)
			nb+=((resultat[i]).length);
	return(nb);
}

// compte le nombre de charactères minuscules alphabetiques dans la chaîne
function countLowercaseAlphaChar(chaine)
{
	var nb= 0;
	var resultat = chaine.match(regExpAlphabeticMinuscule);
	if(resultat)
		for(i=0;i<resultat.length;++i)
			nb+=((resultat[i]).length);
	return(nb);
}

// compte le nombre de charactères majuscules alphabetiques dans la chaîne 
function countUpperAlphaChar(chaine)
{
	var nb= 0;
	var resultat = chaine.match(regExpAlphabeticMajuscule);
		if(resultat)
			for(i=0;i<resultat.length;++i)
				nb+=((resultat[i]).length);
	return(nb);
}
