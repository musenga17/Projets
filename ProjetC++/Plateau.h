#ifndef PLATEAU_H
#define PLATEAU_H

#include "Case.h"
#include "Observer.h"
#include "Player.h"

//L'observable
class Plateau {
 private :
  Case** tableau;
  Observer* observer;
  Player** players;
  bool over = false;
  int nbPlayers;
  int nbCases;
  
 public :
  Plateau(Case**,Player**,int,int);
  virtual ~Plateau();
  Case** getTableau();
  Observer* getObserver();
  Player** getPlayers();
  int getNbPlayers();
  int getNbCases();
  bool getOver();
  void setObserver(Observer& obs);
  void setOver(bool);
  virtual void notifyObserver(int) = 0;
};

#endif
