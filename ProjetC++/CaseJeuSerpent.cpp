#include "Case.h"
#include "CaseJeuSerpent.h"
using namespace std;

CaseJeuSerpent::CaseJeuSerpent(int xx, int yy, int n1, int n2) : Case(xx,yy,n1) , ncs(n2) {}

CaseJeuSerpent::~CaseJeuSerpent(){}

int CaseJeuSerpent::getNcs(){return ncs;}
bool CaseJeuSerpent::getBonus(){return bonus;}
bool CaseJeuSerpent::getMalus(){return malus;}
void CaseJeuSerpent::setBonus(){bonus = true;}
void CaseJeuSerpent::setMalus(){malus = true;}
void CaseJeuSerpent::setNcs(int n){ncs = n;}
