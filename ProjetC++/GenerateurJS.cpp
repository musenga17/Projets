#include "GenerateurJS.h"

GenerateurJS::GenerateurJS(int xd, int yd, int lon, int lar)
  : xdim(xd) , ydim(yd) , longueur(lon) , largeur(lar) {}

GenerateurJS::~GenerateurJS(){}

bool GenerateurJS::deplacement_droite(int i,int h,int l){
  bool b = false;
  for (int k=0; k<h; k += 2) 
    b = b || (i >= k*l && i <= k*l + l - 2);
  return b;
}

bool GenerateurJS::etage_suivant(int i,int h,int l){
  bool b = false;
  for (int k=0; k<h*l; k += l)
    b = b || (i == k+(l-1));
  return b;
}

CaseJeuSerpent** GenerateurJS::generer(){
  int longueurCase = ydim/longueur;
  int largeurCase = xdim/largeur;
  int x = 0;
  int y = ydim - longueurCase;
  CaseJeuSerpent** tableau = new CaseJeuSerpent*[longueur*largeur];
  for (int i = 0; i < longueur*largeur; i++) {
    tableau[i] = new CaseJeuSerpent(x,y,i+1,-1);
    if (deplacement_droite(i,longueur,largeur)) x += largeurCase;
    else if (etage_suivant(i,longueur,largeur)) y -= longueurCase;
    else x -= largeurCase;
  }
  return tableau;
}
