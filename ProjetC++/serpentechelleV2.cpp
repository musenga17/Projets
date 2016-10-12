#include "CaseJeuSerpent.h"
#include "Partie.h"
#include "Plateau.h"
#include "PlateauJeuSerpent.h"
#include "Player.h"
#include "Pion.h"
#include "GenerateurJS.h"
#include "FenetreJS.h"
#include "FenetreJSV1.h"
#include "FenetreJSV2.h"
#include <iostream>

using namespace std;

int main() {
  int n1;
  int n2;
  char c;
  bool b;
  int xdim = 600;
  int ydim = 600;
  int longueur = 10;
  int largeur = 10;
  Pion **pions = new Pion*[6];
  Pion ***tabtabpions = new Pion**[6];
  GenerateurJS *gen = new GenerateurJS(xdim,ydim,longueur,largeur);
  CaseJeuSerpent** tableau = gen->generer();
  string background = "Images/serpentEchelle.jpg";
  pions[0] = new Pion("Images/blue.gif",0,0);
  pions[1] = new Pion("Images/red.gif",-45,-45);
  pions[2] = new Pion("Images/green.gif",-90,-90);
  pions[3] = new Pion("Images/yellow.gif",-135,-135);
  pions[4] = new Pion("Images/purp.gif",-180,-180);
  pions[5] = new Pion("Images/bluesky.gif",-225,-225);
    tableau[0]->setNcs(38);
    tableau[3]->setNcs(14);
    tableau[8]->setNcs(31);
    tableau[16]->setNcs(7);
    tableau[20]->setNcs(42);
    tableau[27]->setNcs(84);
    tableau[50]->setNcs(67);
    tableau[52]->setNcs(34);
    tableau[62]->setNcs(19);
    tableau[63]->setNcs(60);
    tableau[71]->setNcs(91);
    tableau[79]->setNcs(99);
    tableau[86]->setNcs(36);
    tableau[91]->setNcs(73);
    tableau[94]->setNcs(75);
    tableau[97]->setNcs(79);
  cout << "Combien de joueurs ? " << endl;
  do {
    cin >> n1;
    b = n1 < 1 || n1 > 6;
    if (b) cout << "un nombre entre 1 et 6 !" << endl;
  } while (b);
  Player** players = new Player*[n1];
  Plateau *plateau = new PlateauJeuSerpent((Case**)tableau,(Player**)players,n1,longueur*largeur);
  Controler controler(plateau);
  cout << "Combien de pions par joueur ? " << endl;
  do {
    cin >> n2;
    b = n2 < 2 || n2 > 6;
    if (b) cout << "un nombre entre 1 et 6 !" << endl;
  } while (b);
  string path;
  tabtabpions = new Pion**[n1];
  for (int i = 0; i < n1; i++) {
    tabtabpions[i] = new Pion*[n2];
    if(i==0) path = "Images/blue.gif";
    if(i==1) path = "Images/red.gif";
    if(i==2) path = "Images/green.gif";
    if(i==3) path = "Images/yellow.gif";
    if(i==4) path = "Images/purp.gif";
    if(i==5) path = "Images/bluesky.gif";
    for (int j = 0; j < n2; j++) {
      tabtabpions[i][j] = new Pion(path,i,j);
    }
  }
  for (int i = 0; i < n1; i++) {
    tabtabpions[i][0]->setPred(tabtabpions[i][n2-1]);
    tabtabpions[i][0]->setNext(tabtabpions[i][1]);
    tabtabpions[i][n2-1]->setNext(tabtabpions[i][0]);
    tabtabpions[i][n2-1]->setPred(tabtabpions[i][n2-2]);
    for (int j = 1; j < n2 - 1; j++) {
      tabtabpions[i][j]->setPred(tabtabpions[i][j-1]);
      tabtabpions[i][j]->setNext(tabtabpions[i][j+1]);
    }
  }
  for (int i = 0; i < n1; i++) {
    cout << "Humain ou Robot ? [y/*]" << endl;
    cin >> c;
    if (c=='y') players[i] = new Player(tabtabpions[i][0],true,n2);
    else players[i] = new Player(tabtabpions[i][0],false,n2);
  }
  FenetreJSV2 *fenetre = new FenetreJSV2(xdim,ydim,longueur,largeur,n1,&controler,background,players);
  plateau->setObserver(*fenetre);
  Partie partie(plateau,&controler,fenetre);
  partie.launch();
}
  
