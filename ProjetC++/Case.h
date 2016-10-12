#ifndef CASE_H
#define CASE_H

class Case {
private :
  int x; 
  int y;
  // x et y sont les coordonnées en pixels pour l'interface graphique
  int numero;
  //numero de la case
public :
  Case(int,int,int);
  virtual ~Case();
  int getX();
  int getY();
  int getNumero();
};

#endif
