#ifndef OBSERVER_H
#define OBSERVER_H

#include <list>
#include "Case.h"
#include "Player.h"

class Observer {
 public :
  virtual void update(list<Case*>,Player*,int) = 0;
};

#endif
