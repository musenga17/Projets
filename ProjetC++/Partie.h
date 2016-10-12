#ifndef PARTIE_H
#define PARTIE_H

#include "Plateau.h"
#include "Controler.h"
#include "Fenetre.h"

class Partie {
 protected :
  Plateau *plateau;
  Controler *controler;
  Fenetre *fenetre;
  
 public :
  Partie(Plateau*,Controler*,Fenetre*);
  virtual ~Partie();
  virtual void launch();
};

#endif
