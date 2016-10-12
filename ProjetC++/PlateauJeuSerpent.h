#ifndef PLATEAUJEUSERPENT_H
#define PLATEAUJEUSERPENT_H

#include "Plateau.h"
#include "Case.h"
#include "Player.h"

class PlateauJeuSerpent : public Plateau {
 public : 
  PlateauJeuSerpent(Case**,Player**,int,int);
  virtual ~PlateauJeuSerpent();
  void notifyObserver(int);
};

#endif
