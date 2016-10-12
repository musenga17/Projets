#ifndef CASEJEUSERPENT_H
#define CASEJEUSERPENT_H
#include "Case.h"

class CaseJeuSerpent : public Case {
 private :
  int ncs; //numero Case Saut : correspond à la case de l'extremité haute de l'echelle ou l'extremité basse d'un serpent
  bool bonus;
  bool malus;
  
 public :
  CaseJeuSerpent(int,int,int,int);
  virtual ~CaseJeuSerpent();
  int getNcs();
  bool getBonus();
  bool getMalus();
  void setBonus();
  void setMalus();
  void setNcs(int);
};

#endif
