#ifndef FENETREJSV2_H
#define FENETREJSV2_H

#include "Fenetre.h"
#include "Case.h"
#include "Player.h"
#include "Observer.h"

class FenetreJSV2 : public Observer , public Fenetre {
 protected :
  Player** players;
  sf::Texture blancTexture;
  sf::Sprite blancSprite;//Curseur blanc pour selectionner un pion
  
 public :
  FenetreJSV2(int,int,int,int,int,Controler*,string,Player**);
  virtual ~FenetreJSV2();
  virtual void run();
  void render();
  void update(list<Case*> l,Player*,int);
};

#endif
