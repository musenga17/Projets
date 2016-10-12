#include "Controler.h"

Controler::Controler(Plateau* p) : plateau(p) {}

Controler::~Controler(){}

void Controler::notifyObservable(int indicePlayer){
  plateau->notifyObserver(indicePlayer);
}
