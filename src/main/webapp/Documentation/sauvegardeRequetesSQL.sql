-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : bprpidhs8s6evs0vvjkb-mysql.services.clever-cloud.com:21027
-- Généré le : ven. 29 sep. 2023 à 08:45
-- Version du serveur : 8.0.33-25
-- Version de PHP : 8.2.7

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- START TRANSACTION;
-- SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `EcoloTroc`
--

--
-- Structure de la table `Utilisateur`
--
CREATE TABLE Utilisateur (
  noUtilisateur INT NOT NULL CONSTRAINT PK_Utilisateur PRIMARY KEY IDENTITY(1,1),
  pseudo VARCHAR(30) NOT NULL CONSTRAINT UN_Utilisateur_pseudo UNIQUE,
  nom VARCHAR(30) NOT NULL,
  prenom VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(15) NOT NULL,
  rue VARCHAR(50) NOT NULL,
  codePostal INT NOT NULL,
  ville VARCHAR(30) NOT NULL,
  motDePasse VARCHAR(30) NOT NULL,
  credit INT NULL DEFAULT '0',
  administrateur BIT NOT NULL DEFAULT '0', -- 0 : non administrateur ; 1 : administrateur
  activate BIT NOT NULL DEFAULT '1' -- 1 : activé ; 0 : désactivé
);
--
-- Déchargement des données de la table `Utilisateur`
--
INSERT INTO Utilisateur (noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse,
credit, administrateur, activate) VALUES
(1, 'admin', 'Mortimer', 'Philip', 'p.morti@mail.com', '0923803409', '34 rue de Londres', 57000, 'Metz', 'MortimerP57', NULL, 1, 1),
(2, 'user1', 'Mallerand ', 'Raymond', 'r.m@gmail.com', '0101010101', "8, rue de l'aube", 75001, 'Paris', 'motdepasse1', NULL, 0, 1),
(3, 'user2', 'Smith', 'John', 'john.smith@hotmail.com', '0202020202', '2, rue du Bois', 56200, 'Cournon', 'motdepasse2', NULL, 0, 1),
(4, 'user3', 'Ferrand', 'Juliette', 'j.f@gmail.com', '0303030303', '18 les rues Garel', 45000, 'Montoir', 'motdepasse3', NULL, 0, 1);


--
-- Structure de la table `Categorie`
--
CREATE TABLE Categorie (
  noCategorie INT NOT NULL CONSTRAINT PK_Categorie PRIMARY KEY IDENTITY(1,1),
  libelle VARCHAR(30) NOT NULL CONSTRAINT UN_Categorie_libelle UNIQUE
);
--
-- Déchargement des données de la table `Categorie`
--
INSERT INTO Categorie (libelle) VALUES
('Électronique'),
('Vêtements'),
('Alimentation'),
('Mobilier'),
('Sport et loisirs'),
('Informatique'),
('Livres'),
('Musique'),
('Jouets'),
('Électroménagers'),
('Bijoux'),
('Chaussures'),
('Accessoires de mode'),
('Santé'),
('Automobile'),
('Décoration intérieure');


--
-- Structure de la table `ArticleVendu`
--
CREATE TABLE ArticleVendu (
  noArticle		INT NOT NULL CONSTRAINT PK_ArticleVendu PRIMARY KEY IDENTITY(1,1),
  nom			VARCHAR(50) NOT NULL,
  description	VARCHAR(800) NOT NULL,
  etatVente		VARCHAR(12) NOT NULL CONSTRAINT CK_ArticleVendu_etatVente CHECK ( etatVente IN('EN_COURS','NON_DEBUTEE','TERMINEE') ),
  dateDebutEncheres DATE NOT NULL,
  dateFinEncheres	DATE NOT NULL,
  miseAPrix		INT NOT NULL,
  prixVente		INT NOT NULL,
  noCategorie	INT NOT NULL CONSTRAINT FK_ArticleVendu_Categorie REFERENCES Categorie(noCategorie),
  noUtilisateurVendeur INT NOT NULL,
  activate		BIT NOT NULL DEFAULT '0',
  noRetrait		INT DEFAULT NULL,
  noUtilisateurAcheteur	INT DEFAULT NULL,
  CONSTRAINT CK_ArticleVendu_CheckDates CHECK ( dateFinEncheres > dateDebutEncheres )
);
SELECT * From ArticleVendu
--
-- Déchargement des données de la table `ArticleVendu`
--
INSERT INTO ArticleVendu ( noArticle, nom, description, etatVente, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente,
		noCategorie, noUtilisateurVendeur, activate, noRetrait, noUtilisateurAcheteur ) VALUES
(1, 'Billard Premier', 'Très bon état', 'EN_COURS', '2023-10-01', '2023-10-30', 130, 185, 5, 2, 1, 1, 3),
(2, 'Tapis de course DOMYOS', 'État parfait', 'NON_DEBUTEE', '2023-10-18', '2023-11-05', 35, 35, 5, 2, 1, 2, NULL),
(3, 'Citroën C3', '150000km', 'NON_DEBUTEE', '2023-10-09', '2023-10-30', 500, 500, 15, 3, 1, 3, NULL),
(3, 'chaise de jardin', 'en bois, bon état', 'EN_COURS', '2023-10-01', '2023-10-30', 15, 15, 4, 3, 1, 4, NULL),
(3, 'Ordinateur portable Dell Inspiron 15', 'Qq rayures', 'EN_COURS', '2023-10-01', '2023-10-30', 180, 190, 6, 2, 1, 5, 4);

--
-- Structure de la table `Retrait`
--
CREATE TABLE Retrait (
  noRetrait INT NOT NULL CONSTRAINT PK_Retrait PRIMARY KEY IDENTITY(1,1),
  rue VARCHAR(50) NOT NULL,
  ville VARCHAR(30) NOT NULL,
  codePostal INT NOT NULL,
  noArticle INT NOT NULL CONSTRAINT FK_Retrait_ArticleVendu REFERENCES ArticleVendu(noArticle)
);
--
-- Déchargement des données de la table `Retrait`
--
INSERT INTO Retrait ( noRetrait, rue, ville, codePostal, noArticle ) VALUES
(1, '2, rue du Bois', 'Damgan', 56850, 1),
(2, '10, rue du Vilan', 'Paris', 75001, 2),
(3, '10, rue du Vilan', 'Cournon', 56200, 3),
(4, '2, rue du Bois', 'Cournon', 56200, 4),
(5, '10, rue du Vilan', 'Paris', 75001, 5);

--
-- Structure de la table `Enchere`
--
CREATE TABLE Enchere (
  noEnchere INT NOT NULL CONSTRAINT PK_Enchere PRIMARY KEY IDENTITY(1,1),
  noUtilisateur INT NOT NULL CONSTRAINT FK_Enchere_Utilisateur REFERENCES Utilisateur(noUtilisateur),
  noArticle INT NOT NULL CONSTRAINT FK_Enchere_ArticleVendu REFERENCES ArticleVendu(noArticle),
  dateEnchere DATE NOT NULL,
  montantEnchere INT NOT NULL,
  activate BIT NOT NULL DEFAULT '0',
  etatEnchere VARCHAR(8) NOT NULL CONSTRAINT CK_Enchere_etatEnchere CHECK ( etatEnchere IN ('MEILLEUR','PERDU','GAGNE') )
);
--
-- Déchargement des données de la table `Enchere`
--
INSERT INTO Enchere ( noEnchere , noUtilisateur, noArticle, dateEnchere, montantEnchere, activate, etatEnchere ) VALUES
(1, 3, 1, '2023-10-01', 150, 1, 'PERDU'),
(2, 4, 1, '2023-10-01', 170, 1, 'PERDU'),
(3, 4, 5, '2023-10-01', 190, 1, 'MEILLEUR'),
(4, 3, 1, '2023-10-01', 185, 1, 'MEILLEUR');