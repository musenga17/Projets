#include "Pion.h"
#include <iostream>

Pion::Pion(string p, int ax, int ay)
  : xPion(-100) , yPion(-100) , argx(ax), argy(ay) ,casepion(0) , picture(p) {
  if (!pionTexture.loadFromFile(picture)) {
    cerr << "Error loading " << picture << endl;
    exit(0);
  }
  pionSprite.setTexture(pionTexture);
}

Pion::~Pion(){}

int Pion::getXPion(){return xPion;}

int Pion::getYPion(){return yPion;}

int Pion::getArgx(){return argx;}

int Pion::getArgy(){return argy;}

int Pion::getCasePion(){return casepion;}

bool Pion::isArrived(){return arrived;}

sf::Sprite* Pion::getPionSprite(){return &pionSprite;}

Pion* Pion::getNext(){return next;}

Pion* Pion::getPred(){return pred;}

void Pion::setXPion(int x){xPion = x;}

void Pion::setYPion(int y){yPion = y;}

void Pion::setCasePion(int c){casepion = c;}

void Pion::setArrived(bool a){arrived = a;}

void Pion::setNext(Pion* n){next = n;}

void Pion::setPred(Pion* p){pred = p;}
