-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 12 jan. 2019 à 17:47
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_evenements`
--

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

DROP TABLE IF EXISTS `evenements`;
CREATE TABLE IF NOT EXISTS `evenements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_event` varchar(100) NOT NULL,
  `date_event` varchar(50) NOT NULL,
  `nom_organisateur` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `name_event`, `date_event`, `nom_organisateur`) VALUES
(1, 'convention_films', '10/02/2019', 'Léon'),
(2, 'convention_musique', '14/03/2019', 'Marc'),
(4, 'convention_piano_Jazz', '12/05/2019', 'Romuald'),
(5, 'convention_piano_Classic', '12/04/2019', 'Romuald'),
(6, 'convention_pirates', '19/04/2019', 'Eloise'),
(7, 'convention_jolly_jumper', '21/01/2019', 'Hélène'),
(8, 'convention_cheval', '21/02/2019', 'Hélène'),
(9, 'convention_chatons', '13/01/2019', 'Laurène'),
(11, 'convention_fantasy', '30/01/2019', 'Lou'),
(12, 'convention_ouat', '17/01/2019', 'LP'),
(14, 'convention_food', '15/03/2019', 'Maxime'),
(15, 'convention_peluches', '11/03/2019', 'Chelsee'),
(16, 'convention_jurassic', '16/02/2019', 'Laurène'),
(17, 'convention_harry_potter', '08/02/2019', 'Lou');

-- --------------------------------------------------------

--
-- Structure de la table `membres`
--

DROP TABLE IF EXISTS `membres`;
CREATE TABLE IF NOT EXISTS `membres` (
  `Pseudo` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Mdp` varchar(50) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rang` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `membres`
--

INSERT INTO `membres` (`Pseudo`, `Email`, `Nom`, `Prenom`, `Mdp`, `id`, `rang`) VALUES
('Shae', 'lou.bouret@hotmail.fr', 'Bouret', 'Lou', 'SQ25', 1, 2),
('Jojo', 'jojo@gmail.com', 'Johan', 'Johan', '000', 2, 1),
('Jojo', 'ertyghj', 'rtyh', 'rtyu', 'tyu', 3, 1),
('Lucas', 'lucas@gmail.com', 'Léo', 'Lucas', '1234', 6, 1),
('toto', 'toto@gmail.com', 'truc', 'toto', '123', 5, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
