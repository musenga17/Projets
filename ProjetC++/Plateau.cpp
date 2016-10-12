#include <cstdlib>
#include <ctime>
#include <list>
#include "Plateau.h"
#include <iostream>

Plateau::Plateau(Case** tab,Player** p,int n,int nb)
  : tableau(tab) , players(p) , nbPlayers(n) , nbCases(nb) {}

Plateau::~Plateau(){delete[] tableau; delete[] players;}

Case** Plateau::getTableau(){return tableau;}

Observer* Plateau::getObserver(){return observer;}

Player** Plateau::getPlayers(){return players;}

int Plateau::getNbPlayers(){return nbPlayers;}

int Plateau::getNbCases(){return nbCases;}

bool Plateau::getOver(){return over;}

void Plateau::setObserver(Observer& obs){observer = &obs;}

void Plateau::setOver(bool o){over = o;}
