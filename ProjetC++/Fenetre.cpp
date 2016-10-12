#include <iostream>
#include "Fenetre.h"

using namespace std;

Fenetre::Fenetre(int xd, int yd, int lg, int lr, int n, Controler* c,string pa)
  : xdim(xd) , ydim(yd) , lgr(lg) , lrg(lr) , nbPlayers(n) , indicePlayer(0) , window(sf::VideoMode(xd,yd), "Fenetre") , control(c) , path(pa) {
  if (!backgroundTexture.loadFromFile(path)) {
    cerr << "Error loading " << path << endl;
    exit(0);
  }
  backgroundSprite.setTexture(backgroundTexture);
}

Fenetre::~Fenetre(){}

void Fenetre::processEvents() {
  sf::Event event;
  while (window.pollEvent(event)) {
    if (event.type == sf::Event::Closed) {
      window.close();
    }
  }
}

void Fenetre::setBackgroundSprite(sf::Sprite *sprite, int dx, int dy) {
  sf::FloatRect rect = sprite->getGlobalBounds();
  float xpos = xdim - rect.width - dx;
  float ypos = ydim - rect.height - dy;
  sprite->setPosition(xpos,ypos);
  window.draw(*sprite);
}

void Fenetre::setPionSprite(sf::Sprite *sprite, int dx, int dy){
  float xpos = dx;
  float ypos = dy;
  sprite->setPosition(xpos,ypos);
  window.draw(*sprite);
}
