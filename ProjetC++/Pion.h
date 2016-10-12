#ifndef PION_H
#define PION_H

#include <SFML/Graphics.hpp>

using namespace std;

class Pion {
 private :
  //Coordonnées du pion dans l'IG
  int xPion;
  int yPion;
  //des coefficiants de placement
  int argx;
  int argy;
  int casepion;
  //L'image à charger
  string picture;
  bool arrived = false;
  sf::Texture pionTexture;
  sf::Sprite pionSprite;
  Pion *next = nullptr;
  Pion *pred = nullptr;

 public :
  Pion(string,int,int);
  virtual ~Pion();
  int getXPion();
  int getYPion();
  int getArgx();
  int getArgy();
  int getCasePion();
  bool isArrived();
  sf::Sprite* getPionSprite();
  Pion* getNext();
  Pion* getPred();
  void setXPion(int);
  void setYPion(int);
  void setCasePion(int);
  void setArrived(bool);
  void setNext(Pion*);
  void setPred(Pion*);
};

#endif
