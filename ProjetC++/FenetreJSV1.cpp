#include "FenetreJSV1.h"
#include <iostream>

FenetreJSV1::FenetreJSV1(int xd, int yd, int lg, int lr, int n, Controler* c, string pa, Player** p)
    : FenetreJS(xd,yd,lg,lr,n,c,pa,p) {}

FenetreJSV1::~FenetreJSV1() {}

void FenetreJSV1::run() {
    int n1;
    int n2;
    int n;
    srand(time(0));
    cout << "Appuyer sur espace pour démarrer le jeu" << endl;
    while (!sf::Keyboard::isKeyPressed(sf::Keyboard::Space));
    while (window.isOpen()) {
        n1 = (rand()%10)+1;
        n2 = (rand()%10)+1;
        processEvents();
        render();
        if(players[indicePlayer]->getTourSuivant()) {
            cout << "Joueur " << indicePlayer + 1 << " , à vous !" << endl;
            if (players[indicePlayer]->isHuman()) {
                cout << n1 << " x " << n2 << " = ?" << endl;
                cin >> n;
                if (n==n1*n2) {
                    cout << "Bonne réponse" << endl;
                    control->notifyObservable(indicePlayer);
                } else {
                    cout << "Mauvaise réponse" << endl;
                    if (indicePlayer < nbPlayers - 1) indicePlayer++;
                    else indicePlayer = 0;
                }
            } 
            else {
                control->notifyObservable(indicePlayer);
            }
        } 
        else {
            players[indicePlayer]->setTourSuivant(true);
            if (indicePlayer < nbPlayers - 1) indicePlayer++;
            else indicePlayer = 0;
        }
    }
}

