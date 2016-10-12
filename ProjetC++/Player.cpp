#include "Player.h"

Player::Player(Pion* p, bool h ,int n) : pion(p) , human(h) , nbPions(n) {}

Player::~Player(){}

Pion* Player::getPion(){return pion;}

bool Player::isHuman(){return human;}

bool Player::getTourSuivant(){return tourSuivant;}

void Player::setTourSuivant(bool ts){tourSuivant = ts;}

bool Player::isWinner(){
  Pion* tmp = pion;
  bool b = true;
  do {
    if (!tmp->isArrived()) {//Si au moins un des pions n'est pas encore arrivé sur la case finale .. on est pas gagnant
      b = false;
    }
    tmp = tmp->getNext();
  } while (tmp->getNext() != pion->getNext());
  return b;
}
    
int Player::getNbPions(){return nbPions;}

void Player::setPion(Pion* p){pion = p;}
