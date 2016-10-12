#ifndef PLAYER_H
#define PLAYER_H

#include "Pion.h"

class Player {
 private :
  Pion *pion;
  bool human;
  bool tourSuivant = true;
  bool winner = false;
  int nbPions;
 public :
  Player(Pion*,bool,int);
  virtual ~Player();
  Pion* getPion();
  bool isHuman();
  bool getTourSuivant();
  void setTourSuivant(bool);
  bool isWinner();
  int getNbPions();
  void setPion(Pion*);
};

#endif
