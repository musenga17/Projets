#ifndef CONTROLER_H
#define CONTROLER_H

#include "Plateau.h"

class Controler {
 private :
  Plateau *plateau;
 public :
  Controler(Plateau*);
  virtual ~Controler();
  void notifyObservable(int);
};

#endif
