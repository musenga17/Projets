#ifndef GENERATEURJS_H
#define GENERATEURJS_H

#include "CaseJeuSerpent.h"

class GenerateurJS {
 private :
  int xdim;
  int ydim;
  int longueur;
  int largeur;
  bool deplacement_droite(int,int,int);
  bool etage_suivant(int,int,int);

 public :
  GenerateurJS(int,int,int,int);
  virtual ~GenerateurJS();
  CaseJeuSerpent** generer();
};

#endif
