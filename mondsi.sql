-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Jeu 03 Mai 2012 à 15:39
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `mondsi`
--

-- --------------------------------------------------------

--
-- Structure de la table `civilite`
--

CREATE TABLE IF NOT EXISTS `civilite` (
  `ID_CIVILITE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_CIVILITE` varchar(4) NOT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_CIVILITE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `civilite`
--

INSERT INTO `civilite` (`ID_CIVILITE`, `CODE_CIVILITE`, `LIBELLE`) VALUES
(1, 'Mr', 'Monsieur');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `ID_CLIENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_SOCIETE` bigint(20) NOT NULL,
  `ID_COMPTE_BANCAIRE` bigint(20) NOT NULL,
  `CODE_CLIENT` varchar(60) DEFAULT NULL,
  `RCS` varchar(32) DEFAULT NULL,
  `SIRET` varchar(32) DEFAULT NULL,
  `CUSTOMER_KEY` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENT`),
  KEY `FK_ASSOCIATION_2` (`ID_SOCIETE`),
  KEY `FK_ASSOCIATION_22` (`ID_COMPTE_BANCAIRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `client_revendeur`
--

CREATE TABLE IF NOT EXISTS `client_revendeur` (
  `ID_CLIENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_REVENDEUR` bigint(20) NOT NULL,
  `NUMERO_CLIENT_REVENDEUR` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENT`,`ID_REVENDEUR`),
  KEY `FK_CLIENT_REVENDEUR2` (`ID_REVENDEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `ID_COMMANDE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_CLIENT` bigint(20) NOT NULL,
  `ID_PRODUIT` bigint(20) NOT NULL,
  `ID_REVENDEUR` bigint(20) NOT NULL,
  `ID_ETAT_COMMANDE` bigint(20) NOT NULL,
  `ID_DUREE_COMMANDE` bigint(20) NOT NULL,
  `NUMERO_COMMANDE` varchar(32) DEFAULT NULL,
  `CREE_LE` datetime NOT NULL,
  `CREE_PAR` varchar(256) NOT NULL,
  `MODIFIEE_LE` datetime DEFAULT NULL,
  `MODIFIEE_PAR` varchar(256) DEFAULT NULL,
  `QUANTITE` int(11) DEFAULT NULL,
  `MONTANT` decimal(8,0) DEFAULT NULL,
  `DATE_DEBUT` datetime DEFAULT NULL,
  `DATE_FIN` datetime DEFAULT NULL,
  `DATE_COMMANDE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_COMMANDE`),
  KEY `FK_ASSOCIATION_13` (`ID_ETAT_COMMANDE`),
  KEY `FK_ASSOCIATION_14` (`ID_DUREE_COMMANDE`),
  KEY `FK_ASSOCIATION_15` (`ID_REVENDEUR`),
  KEY `FK_ASSOCIATION_16` (`ID_PRODUIT`),
  KEY `FK_ASSOCIATION_17` (`ID_CLIENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compte_bancaire`
--

CREATE TABLE IF NOT EXISTS `compte_bancaire` (
  `ID_COMPTE_BANCAIRE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_COMPTE_BANQUE` varchar(50) DEFAULT NULL,
  `BANQUE` varchar(50) DEFAULT NULL,
  `CODE_BANQUE` varchar(5) DEFAULT NULL,
  `CODE_GUICHET` varchar(5) DEFAULT NULL,
  `NUMERO_COMPTE` varchar(20) DEFAULT NULL,
  `CLE_RIB` varchar(2) DEFAULT NULL,
  `ID_REGLEMENT` bigint(20) NOT NULL,
  `ID_DEVISE` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_COMPTE_BANCAIRE`),
  KEY `ID_REGLEMENT` (`ID_REGLEMENT`),
  KEY `ID_DEVISE` (`ID_DEVISE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `compte_bancaire`
--

INSERT INTO `compte_bancaire` (`ID_COMPTE_BANCAIRE`, `CODE_COMPTE_BANQUE`, `BANQUE`, `CODE_BANQUE`, `CODE_GUICHET`, `NUMERO_COMPTE`, `CLE_RIB`, `ID_REGLEMENT`, `ID_DEVISE`) VALUES
(1, 'BOA', 'BOA', 'BOA', 'BOA', '115454', '12', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE IF NOT EXISTS `devise` (
  `ID_DEVISE` bigint(20) NOT NULL AUTO_INCREMENT,
  `LIBELLE` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_DEVISE`),
  KEY `LIBELLE` (`LIBELLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `devise`
--

INSERT INTO `devise` (`ID_DEVISE`, `LIBELLE`) VALUES
(1, 'Ariary'),
(2, 'Euro');

-- --------------------------------------------------------

--
-- Structure de la table `duree_commande`
--

CREATE TABLE IF NOT EXISTS `duree_commande` (
  `ID_DUREE_COMMANDE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DUREE` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_DUREE_COMMANDE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `etat_commande`
--

CREATE TABLE IF NOT EXISTS `etat_commande` (
  `ID_ETAT_COMMANDE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_ETAT_COMMANDE` varchar(3) NOT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_ETAT_COMMANDE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `etat_compte`
--

CREATE TABLE IF NOT EXISTS `etat_compte` (
  `ID_ETAT_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_ETAT_COMPTE` varchar(3) DEFAULT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_ETAT_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `etat_compte`
--

INSERT INTO `etat_compte` (`ID_ETAT_COMPTE`, `CODE_ETAT_COMPTE`, `LIBELLE`) VALUES
(1, 'O', 'Active');

-- --------------------------------------------------------

--
-- Structure de la table `langue`
--

CREATE TABLE IF NOT EXISTS `langue` (
  `ID_LANGUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_LANGUE` varchar(2) DEFAULT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_LANGUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `langue`
--

INSERT INTO `langue` (`ID_LANGUE`, `CODE_LANGUE`, `LIBELLE`) VALUES
(1, 'MG', 'malgache');

-- --------------------------------------------------------

--
-- Structure de la table `mode_reglement`
--

CREATE TABLE IF NOT EXISTS `mode_reglement` (
  `ID_REGLEMENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `LIBELLE` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_REGLEMENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `mode_reglement`
--

INSERT INTO `mode_reglement` (`ID_REGLEMENT`, `LIBELLE`) VALUES
(1, 'Cheque'),
(2, 'Espece');

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `ID_PAYS` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_PAYS` varchar(2) NOT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  `TVA` decimal(8,0) NOT NULL,
  PRIMARY KEY (`ID_PAYS`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`ID_PAYS`, `CODE_PAYS`, `LIBELLE`, `TVA`) VALUES
(1, 'MG', 'Madagascar', '15');

-- --------------------------------------------------------

--
-- Structure de la table `prix`
--

CREATE TABLE IF NOT EXISTS `prix` (
  `ID_PRIX` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_PRODUIT` bigint(20) NOT NULL,
  `ID_DUREE_COMMANDE` bigint(20) NOT NULL,
  `DATE_DEBUT` datetime DEFAULT NULL,
  `DATE_FIN` datetime DEFAULT NULL,
  `QTE_INF` bigint(20) DEFAULT NULL,
  `QTE_SUP` bigint(20) DEFAULT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `TARIF` decimal(10,2) DEFAULT NULL,
  `CODE_PRODUIT_SAGE` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID_PRIX`),
  KEY `FK_ASSOCIATION_19` (`ID_PRODUIT`),
  KEY `FK_ASSOCIATION_20` (`ID_DUREE_COMMANDE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `ID_PRODUIT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ETAT_PRODUIT` varchar(1) DEFAULT NULL,
  `NOM_PRODUIT` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUIT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit_revendeur`
--

CREATE TABLE IF NOT EXISTS `produit_revendeur` (
  `ID_REVENDEUR` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_PRODUIT` bigint(20) NOT NULL,
  `NOM_PRODUIT_REVENDEUR` varchar(255) DEFAULT NULL,
  `TAUX_REMISE` bigint(20) DEFAULT NULL,
  `QUANTITE_REMISE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_REVENDEUR`,`ID_PRODUIT`),
  KEY `FK_PRODUIT_REVENDEUR2` (`ID_PRODUIT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE IF NOT EXISTS `profil` (
  `ID_PROFIL` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_PROFIL` varchar(3) NOT NULL,
  `LIBELLE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID_PROFIL`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `profil`
--

INSERT INTO `profil` (`ID_PROFIL`, `CODE_PROFIL`, `LIBELLE`) VALUES
(1, 'adm', 'Administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `revendeur`
--

CREATE TABLE IF NOT EXISTS `revendeur` (
  `ID_REVENDEUR` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_SOCIETE` bigint(20) NOT NULL,
  `REV_ID_REVENDEUR` bigint(20) DEFAULT NULL,
  `ID_COMPTE_BANCAIRE` bigint(20) NOT NULL,
  `ID_UTILISATEUR` int(11) NOT NULL,
  `CODE_REVENDEUR` varchar(32) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_FIN` datetime DEFAULT NULL,
  `DROIT_CREATION_REVENDEUR` varchar(1) DEFAULT NULL,
  `EMAIL_AUTOMATIQUE` varchar(1) DEFAULT NULL,
  `NIVEAU` bigint(20) DEFAULT NULL,
  `PARTNER_CODE` varchar(60) DEFAULT NULL,
  `PARTNER_PWD` varchar(60) DEFAULT NULL,
  `ETAT` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ID_REVENDEUR`),
  KEY `FK_ASSOCIATION_21` (`ID_SOCIETE`),
  KEY `FK_ASSOCIATION_4` (`ID_COMPTE_BANCAIRE`),
  KEY `FK_PARENT` (`REV_ID_REVENDEUR`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `revendeur`
--

INSERT INTO `revendeur` (`ID_REVENDEUR`, `ID_SOCIETE`, `REV_ID_REVENDEUR`, `ID_COMPTE_BANCAIRE`, `ID_UTILISATEUR`, `CODE_REVENDEUR`, `DATE_CREATION`, `DATE_FIN`, `DROIT_CREATION_REVENDEUR`, `EMAIL_AUTOMATIQUE`, `NIVEAU`, `PARTNER_CODE`, `PARTNER_PWD`, `ETAT`) VALUES
(1, 1, NULL, 1, 2, 'Faly', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 1, 1, 1, 1, 'TEST', '2012-04-26 00:00:00', NULL, NULL, NULL, 1, NULL, NULL, 'O');

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

CREATE TABLE IF NOT EXISTS `societe` (
  `ID_SOCIETE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_PAYS` bigint(20) NOT NULL,
  `ID_LANGUE` bigint(20) NOT NULL,
  `ID_COMPTE_BANCAIRE` bigint(20) NOT NULL,
  `CODE_SOCIETE` varchar(20) DEFAULT NULL,
  `NOM` varchar(50) DEFAULT NULL,
  `SIRET` varchar(200) NOT NULL,
  `ADRESSE1` varchar(50) DEFAULT NULL,
  `ADRESSE2` varchar(50) DEFAULT NULL,
  `CODE_POSTAL` varchar(5) DEFAULT NULL,
  `VILLE` varchar(50) DEFAULT NULL,
  `TVA_INTRA_COMMUNAUTAIRE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID_SOCIETE`),
  KEY `FK_ASSOCIATION_11` (`ID_PAYS`),
  KEY `FK_ASSOCIATION_12` (`ID_LANGUE`),
  KEY `FK_ASSOCIATION_8` (`ID_COMPTE_BANCAIRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `societe`
--

INSERT INTO `societe` (`ID_SOCIETE`, `ID_PAYS`, `ID_LANGUE`, `ID_COMPTE_BANCAIRE`, `CODE_SOCIETE`, `NOM`, `SIRET`, `ADRESSE1`, `ADRESSE2`, `CODE_POSTAL`, `VILLE`, `TVA_INTRA_COMMUNAUTAIRE`) VALUES
(1, 1, 1, 1, 'Omega Web Mada', 'Faly', '', 'Tana', 'Tana', '101', 'Tana', '15');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID_UTILISATEUR` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_PROFIL` bigint(20) NOT NULL,
  `ID_LANGUE` bigint(20) NOT NULL,
  `ID_CIVILITE` bigint(20) NOT NULL,
  `ID_REVENDEUR` bigint(20) NOT NULL,
  `ID_ETAT_COMPTE` bigint(20) NOT NULL,
  `LOGIN` varchar(60) DEFAULT NULL,
  `NOM` varchar(50) DEFAULT NULL,
  `PRENOM` varchar(50) DEFAULT NULL,
  `TELEPHONE` varchar(32) DEFAULT NULL,
  `PORTABLE` varchar(32) DEFAULT NULL,
  `FONCTION` varchar(255) DEFAULT NULL,
  `FAX` varchar(32) DEFAULT NULL,
  `PASSWORD` varchar(60) DEFAULT NULL,
  `CREE_LE` datetime DEFAULT NULL,
  `CREE_PAR` varchar(256) DEFAULT NULL,
  `MODIFIEE_LE` datetime DEFAULT NULL,
  `MODIFIEE_PAR` varchar(256) DEFAULT NULL,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_FIN` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_UTILISATEUR`),
  KEY `FK_ASSOCIATION_10` (`ID_CIVILITE`),
  KEY `FK_ASSOCIATION_5` (`ID_REVENDEUR`),
  KEY `FK_ASSOCIATION_6` (`ID_ETAT_COMPTE`),
  KEY `FK_ASSOCIATION_7` (`ID_PROFIL`),
  KEY `FK_ASSOCIATION_9` (`ID_LANGUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID_UTILISATEUR`, `ID_PROFIL`, `ID_LANGUE`, `ID_CIVILITE`, `ID_REVENDEUR`, `ID_ETAT_COMPTE`, `LOGIN`, `NOM`, `PRENOM`, `TELEPHONE`, `PORTABLE`, `FONCTION`, `FAX`, `PASSWORD`, `CREE_LE`, `CREE_PAR`, `MODIFIEE_LE`, `MODIFIEE_PAR`, `DATE_CREATION`, `DATE_FIN`) VALUES
(1, 1, 1, 1, 1, 1, 'faliherizo@gmail.com', 'Faly', 'Faly', '0331193145', '0331193145', 'Développeur', '101', '69fab538effdc823257a391071980ad9', '2012-04-24 00:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 1, 1, 1, 2, 1, 'falyflhz@yahoo.fr', 'faly', 'faly', '123', '123', 'Dev', '101', 'faly', NULL, NULL, NULL, NULL, '2012-04-26 12:00:25', '2012-12-12 00:00:00'),
(3, 1, 1, 1, 1, 1, 'falyflhz@yahoo.frf', 'qsdsq', 'dsqds', '242', '242', 'dsqd', '424', 'dqsd', NULL, NULL, NULL, NULL, '2012-04-27 10:36:04', '2012-12-12 00:00:00'),
(4, 1, 1, 1, 1, 1, 'falyflhz@yahoo.frt', 'q', 'w', '242', '242', '', '424', '', NULL, NULL, NULL, NULL, '2012-04-27 10:36:31', '2012-12-12 00:00:00'),
(5, 1, 1, 1, 1, 1, 'falyflhz@yahoo.frt', 'q', 'w', '242', '242', '', '424', '', NULL, NULL, NULL, NULL, '2012-04-27 10:36:40', '2012-12-12 00:00:00');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_ASSOCIATION_2` FOREIGN KEY (`ID_SOCIETE`) REFERENCES `societe` (`ID_SOCIETE`),
  ADD CONSTRAINT `FK_ASSOCIATION_22` FOREIGN KEY (`ID_COMPTE_BANCAIRE`) REFERENCES `compte_bancaire` (`ID_COMPTE_BANCAIRE`);

--
-- Contraintes pour la table `client_revendeur`
--
ALTER TABLE `client_revendeur`
  ADD CONSTRAINT `FK_CLIENT_REVENDEUR` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID_CLIENT`),
  ADD CONSTRAINT `FK_CLIENT_REVENDEUR2` FOREIGN KEY (`ID_REVENDEUR`) REFERENCES `revendeur` (`ID_REVENDEUR`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_ASSOCIATION_13` FOREIGN KEY (`ID_ETAT_COMMANDE`) REFERENCES `etat_commande` (`ID_ETAT_COMMANDE`),
  ADD CONSTRAINT `FK_ASSOCIATION_14` FOREIGN KEY (`ID_DUREE_COMMANDE`) REFERENCES `duree_commande` (`ID_DUREE_COMMANDE`),
  ADD CONSTRAINT `FK_ASSOCIATION_15` FOREIGN KEY (`ID_REVENDEUR`) REFERENCES `revendeur` (`ID_REVENDEUR`),
  ADD CONSTRAINT `FK_ASSOCIATION_16` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`ID_PRODUIT`),
  ADD CONSTRAINT `FK_ASSOCIATION_17` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID_CLIENT`);

--
-- Contraintes pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  ADD CONSTRAINT `compte_bancaire_ibfk_2` FOREIGN KEY (`ID_DEVISE`) REFERENCES `devise` (`ID_DEVISE`),
  ADD CONSTRAINT `compte_bancaire_ibfk_1` FOREIGN KEY (`ID_REGLEMENT`) REFERENCES `mode_reglement` (`ID_REGLEMENT`);

--
-- Contraintes pour la table `prix`
--
ALTER TABLE `prix`
  ADD CONSTRAINT `FK_ASSOCIATION_19` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`ID_PRODUIT`),
  ADD CONSTRAINT `FK_ASSOCIATION_20` FOREIGN KEY (`ID_DUREE_COMMANDE`) REFERENCES `duree_commande` (`ID_DUREE_COMMANDE`);

--
-- Contraintes pour la table `produit_revendeur`
--
ALTER TABLE `produit_revendeur`
  ADD CONSTRAINT `FK_PRODUIT_REVENDEUR` FOREIGN KEY (`ID_REVENDEUR`) REFERENCES `revendeur` (`ID_REVENDEUR`),
  ADD CONSTRAINT `FK_PRODUIT_REVENDEUR2` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`ID_PRODUIT`);

--
-- Contraintes pour la table `revendeur`
--
ALTER TABLE `revendeur`
  ADD CONSTRAINT `FK_ASSOCIATION_21` FOREIGN KEY (`ID_SOCIETE`) REFERENCES `societe` (`ID_SOCIETE`),
  ADD CONSTRAINT `FK_ASSOCIATION_4` FOREIGN KEY (`ID_COMPTE_BANCAIRE`) REFERENCES `compte_bancaire` (`ID_COMPTE_BANCAIRE`),
  ADD CONSTRAINT `FK_PARENT` FOREIGN KEY (`REV_ID_REVENDEUR`) REFERENCES `revendeur` (`ID_REVENDEUR`);

--
-- Contraintes pour la table `societe`
--
ALTER TABLE `societe`
  ADD CONSTRAINT `FK_ASSOCIATION_11` FOREIGN KEY (`ID_PAYS`) REFERENCES `pays` (`ID_PAYS`),
  ADD CONSTRAINT `FK_ASSOCIATION_12` FOREIGN KEY (`ID_LANGUE`) REFERENCES `langue` (`ID_LANGUE`),
  ADD CONSTRAINT `FK_ASSOCIATION_8` FOREIGN KEY (`ID_COMPTE_BANCAIRE`) REFERENCES `compte_bancaire` (`ID_COMPTE_BANCAIRE`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK_ASSOCIATION_10` FOREIGN KEY (`ID_CIVILITE`) REFERENCES `civilite` (`ID_CIVILITE`),
  ADD CONSTRAINT `FK_ASSOCIATION_5` FOREIGN KEY (`ID_REVENDEUR`) REFERENCES `revendeur` (`ID_REVENDEUR`),
  ADD CONSTRAINT `FK_ASSOCIATION_6` FOREIGN KEY (`ID_ETAT_COMPTE`) REFERENCES `etat_compte` (`ID_ETAT_COMPTE`),
  ADD CONSTRAINT `FK_ASSOCIATION_7` FOREIGN KEY (`ID_PROFIL`) REFERENCES `profil` (`ID_PROFIL`),
  ADD CONSTRAINT `FK_ASSOCIATION_9` FOREIGN KEY (`ID_LANGUE`) REFERENCES `langue` (`ID_LANGUE`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
