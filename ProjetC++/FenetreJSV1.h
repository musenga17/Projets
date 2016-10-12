#ifndef FENETREJSV1_H
#define FENETREJSV1_H

#include "FenetreJS.h"

class FenetreJSV1 : public FenetreJS {
 public :
  FenetreJSV1(int,int,int,int,int,Controler*,string,Player**);
  virtual ~FenetreJSV1();
  void run();
};

#endif
