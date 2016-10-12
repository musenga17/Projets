#include "Plateau.h"
#include "PlateauJeuSerpent.h"
#include "Pion.h"
#include "CaseJeuSerpent.h"
#include <list>
#include <iostream>

PlateauJeuSerpent::PlateauJeuSerpent(Case** tab,Player** p,int n,int nb)
  : Plateau(tab,p,n,nb) {}

PlateauJeuSerpent::~PlateauJeuSerpent(){}

void PlateauJeuSerpent::notifyObserver(int indice){
  list<Case*> l;
  srand(time(0));
  Player *player = getPlayers()[indice];
  Pion *pion = player->getPion();
  int tmp = pion->getCasePion();
  int valeurDe = (rand()%6)+1;
  if (pion->getCasePion() + valeurDe == getNbCases()) {
    pion->setArrived(true);
  }
  cout << "Valeur de = " << valeurDe << endl; 
  for (int i = 1; i <= valeurDe; i++) { 
    if (tmp + i <= getNbCases()) {//Tant que la prochaine case à atteindre est dans le plateau
      l.push_back(getTableau()[pion->getCasePion()]);
      pion->setCasePion(pion->getCasePion() + 1);
    }
    else { //Si la prochaine destination est hors du plateau on recule du nombre de case restante
      pion->setCasePion(pion->getCasePion() - 1);
      l.push_back(getTableau()[pion->getCasePion() - 1]); 
    }
  }
  CaseJeuSerpent *c = (CaseJeuSerpent*)getTableau()[pion->getCasePion() - 1];//La dernière case sur laquelle on arrive
  if (!c->getBonus()) {//Si ce n'est pas une case bonus
    if (c->getNcs() != -1) {//Si On est en bas d'une echelle ou haut d'un serpent
      l.push_back(getTableau()[c->getNcs()-1]);
      pion->setCasePion(c->getNcs());
    } else if (c->getMalus()) {
      player->setTourSuivant(false);
    }
    if (indice < getNbPlayers() - 1) indice++;
    else indice = 0;
  }
  //Sinon l'indicePlayer n'est pas incrémenté donc le joueur aura le droit à un deuxième tour
  getObserver()->update(l,player,indice);//l'indice du joueur ne s'incrémente pas car il aura droit a un 2e tour
}
