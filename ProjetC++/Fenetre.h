#ifndef FENETRE_H
#define FENETRE_H

#include <SFML/Graphics.hpp>
#include <SFML/System.hpp>
#include "Case.h"
#include "Controler.h"
#include <list>
#include <iterator>

class Fenetre {
 public :
  Fenetre(int,int,int,int,int,Controler*,string);
  virtual ~Fenetre();
  virtual void run() = 0;//Gère les interactions au clavier et communique avec le controleur
  virtual void render() = 0;//dessine dans l'interface graphique en appelant setBackgroundSprite et setPionSprite

 protected :
  void processEvents();//fermer la fenetre
  void setBackgroundSprite(sf::Sprite*,int,int);
  void setPionSprite(sf::Sprite*,int,int);
  const int xdim;
  const int ydim;
  int lgr;
  int lrg;
  int nbPlayers;
  int indicePlayer;
  sf::RenderWindow window;
  sf::Text text;
  Controler *control;
  string path;
  sf::Texture backgroundTexture; //la texture sert à charger l'image
  sf::Sprite backgroundSprite; //charge la texture, c'est grace a lui qu'on va manipuler l'image dans l'interface graphique
};

#endif
