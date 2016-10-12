#include "Case.h"

Case::Case(int xx, int yy, int n) : x(xx) , y(yy) , numero(n) {}

Case::~Case(){}

int Case::getNumero(){return numero;}

int Case::getX(){return x;}

int Case::getY(){return y;}
