#ifndef FENETREJS_H
#define FENETREJS_H

#include "Fenetre.h"
#include "Case.h"
#include "Player.h"
#include "Observer.h"

class FenetreJS : public Observer , public Fenetre {
 protected :
  Player** players;
 public :
  FenetreJS(int,int,int,int,int,Controler*,string,Player**);
  virtual ~FenetreJS();
  virtual void run();//De fenetre
  void render();//De fenetre
  void update(list<Case*> l, Player *player, int indice);//De observer
};

#endif
