#include "FenetreJS.h"
#include <iostream>

FenetreJS::FenetreJS(int xd, int yd, int lg, int lr, int n, Controler* c, string pa, Player** p)
  : Fenetre(xd,yd,lg,lr,n,c,pa) , players(p) {}

FenetreJS::~FenetreJS(){delete[] players;}

void FenetreJS::run(){
  cout << "Appuyer sur espace pour démarrer le jeu" << endl;
  while (!sf::Keyboard::isKeyPressed(sf::Keyboard::Space));//boucle tant que la touche espace n'est pas appuyée
  while (window.isOpen()) {
    cout << "Joueur " << indicePlayer + 1 << " , à vous !" << endl;
    processEvents();
    render();//mise a jour des pions (repaint)
    if (players[indicePlayer]->getTourSuivant()) {//droit de jouer sur le moment
      if (players[indicePlayer]->isHuman())
	while (!sf::Keyboard::isKeyPressed(sf::Keyboard::Space));
      control->notifyObservable(indicePlayer);
      //Que le joueur soit humain ou robot, Le fonctionnement reste le meme. meme si le robot execute directement cette méthode (puisqu'il ne peut pas appuyer sur une touche du clavier) on doit quand meme respecter l'architecture et utiliser le controleur
    } else {//Quand le joueur était tombé sur une case malus, il rentre dans ce bloc. On passe au joueur suivant en incrémentant indicePlayer
      players[indicePlayer]->setTourSuivant(true);//au prochain tour il aura le droit de jouer à nouveau
      if (indicePlayer < nbPlayers - 1) indicePlayer++;
      else indicePlayer = 0;
    }
  }
}

void FenetreJS::update(list<Case*> l,Player* player,int indice) {
  Pion *pion;
  int coeffx;
  int coeffy;
  for (list<Case*>::iterator it=l.begin(); it!=l.end(); it++) { 
    pion = player->getPion();
    coeffx = pion->getPionSprite()->getGlobalBounds().width;
    coeffy = pion->getPionSprite()->getGlobalBounds().height;
    //mise a jour des coordonnes du pion
    pion->setXPion((*it)->getX() + coeffx*cos(pion->getArgx()) + (xdim/lrg)/2 - coeffx/2); 
    pion->setYPion((*it)->getY() + coeffy*sin(pion->getArgy()) + (ydim/lgr)/2 - coeffy/2);
    render();//repaint
    sf::sleep(sf::milliseconds(200));
  }
  if (player->isWinner()) {
    cout << "Joueur " << indicePlayer + 1 << ", Vous avez gagné !!!" << endl;
    exit(1);
  }
  indicePlayer = indice;
}


void FenetreJS::render(){
  window.clear(sf::Color::White);
  setBackgroundSprite(&backgroundSprite,0,0);
  Pion* p;
  for (int i = 0; i < nbPlayers; i++ ) {
    p = players[i]->getPion();
    setPionSprite(p->getPionSprite(),p->getXPion(),p->getYPion());
  }
  window.display();
}
