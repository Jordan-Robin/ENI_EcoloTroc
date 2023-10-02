# ENI_EcoloTroc

Application réalisée dans le cadre de la formation Développeur Web & Web Mobile à l'ENI - Ecole Informatique.
Application réalisée avec Nicolas CHARRIERE-DUTHOIT, Mathieu GOMILA et Aleksei PAVLYSHIN-GADAIS en septembre 2023 (10 jours).

Outils utilisés : Eclipse (Apache Tomcat), GitHub, console.clever-cloud (base de données), Trello.

Languages utilisés : JavaEE, html, css (Boostrap), SQL.

=> Une base de données avec des enregistrements prédéfinis pour tester l'application est donnée dans le fichier "sauvegardeRequetesSQL" (src > main > webapp > Documentation).


Présentation de l'application :

Cette application web a pour but la vente de tout type d'objets entre particuliers (type LeBonCoin), mais sur le principe d'une vente aux enchères. Ainsi, lorsqu'un utilisateur du site souhaite vendre un article, il choisit son prix de vente et une période d'enchères. Lorsque la période d'enchères débute, les autres utilisateurs du site peuvent enchérir avec un système de crédits dont ils disposent (possibilité d'acheter des crédits). A l'issue de la période d'enchères, le meilleur enchérisseur remporte la vente. Pour pouvoir vendre ou enchérir sur une vente, l'utilisateur doit au préalable avoir créé un compte et s'être connecté.

Certains utilisateurs ont le profil d'administrateurs. A ce titre, ils peuvent :

-> activer/désactiver un utilisateur, un article en vente ou une enchère

-> envoyer un mail de réinitialisation de mot de passe aux utilisateurs l'ayant perdu

-> modifier la liste des catégories d'articles (exemples : mobilier, automobile, électroménager, etc.)
