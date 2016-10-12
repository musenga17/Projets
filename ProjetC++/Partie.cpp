#include "Partie.h"

Partie::Partie(Plateau* p, Controler* c, Fenetre *f)
  : plateau(p) , controler(c) , fenetre(f) {
}

Partie::~Partie(){}

void Partie::launch(){
  fenetre->run();
}
