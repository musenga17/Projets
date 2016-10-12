#include "FenetreJSV2.h"
#include <iostream>

//Variante c) avec plusieurs pions
FenetreJSV2::FenetreJSV2(int xd, int yd, int lg, int lr, int n, Controler* c, string pa, Player** p)
    : Fenetre(xd,yd,lg,lr,n,c,pa) , players(p) {
    if (!blancTexture.loadFromFile("Images/blanc.gif")) {
        cerr << "Error loading blanc.gif" << endl;
        exit(0);
    }
    blancSprite.setTexture(blancTexture);
}

FenetreJSV2::~FenetreJSV2() {
    delete[] players;
}

void FenetreJSV2::run() {
    sf::Event event;
    int alea;
    Pion* pion;
    Pion* tmp;
    srand(time(0));
    cout << "Appuyer sur espace pour démarrer le jeu" << endl;
    while (!sf::Keyboard::isKeyPressed(sf::Keyboard::Space));
    while (window.isOpen()) {
        Player *player = players[indicePlayer];
        while(player->getPion()->isArrived()) {//Tant que notre pion actuel est arrivé , on va regarder si le suivant ne l'est pas car on veut uniquement avoir le choix de selectionner des pions qui ne sont pas encore arrivé à destination
            player->setPion(player->getPion()->getNext());//on regarde le pion suivant(qui peut lui aussi etre arrivé, ce pourquoi on boucle)
        }
        processEvents();
        render();
        if (player->getTourSuivant()) {
            cout << "Joueur " << indicePlayer + 1 << " , à vous !" << endl;
            if (player->isHuman()) {
                while (!sf::Keyboard::isKeyPressed(sf::Keyboard::Space)) {
                    while (window.pollEvent(event)) {
                        if (event.type == sf::Event::KeyPressed) {
                            if (event.key.code == sf::Keyboard::Up) {
                                do {
                                    player->setPion(player->getPion()->getPred());//on regarde le pion précédent
                                } while(player->getPion()->isArrived());//tant qu'on tombe sur pion qui est arrivé on regarde sur le précédent
                            }
                            if (event.key.code == sf::Keyboard::Down) {//pareil avec touche down
                                do {
                                    player->setPion(player->getPion()->getNext());
                                } while(player->getPion()->isArrived());
                            }
                        }
                        render();
                    }
                }
            } 
            else {//On selectionne un pion au hasard pour le robot
                pion = player->getPion();
                do {
                    tmp = pion;
                    alea = rand()%player->getNbPions();
                    for (int k = 0; k < alea; k++) {
                        tmp = tmp->getNext();
                    }
                } while (tmp->isArrived());
                player->setPion(tmp);
                render();
            }
            control->notifyObservable(indicePlayer);
        } else {
            players[indicePlayer]->setTourSuivant(true);
            if (indicePlayer < nbPlayers - 1) indicePlayer++;
            else indicePlayer = 0;
        }
    }
}

void FenetreJSV2::update(list<Case*> l, Player* player, int indice) {
    Pion *pion;
    int pion_width;
    int pion_height;
    for (list<Case*>::iterator it=l.begin(); it!=l.end(); it++) {
        pion = player->getPion();
        pion_width = pion->getPionSprite()->getGlobalBounds().width;
        pion_height = pion->getPionSprite()->getGlobalBounds().height;
        pion->setXPion((*it)->getX() + 2 + ((((xdim/lrg)-pion_width)/nbPlayers)*pion->getArgx()));
        pion->setYPion((*it)->getY() + 2 + (((ydim/lgr)-pion_height)/(player->getNbPions()))*pion->getArgy());
        sf::sleep(sf::milliseconds(200));
        render();
    }
    if (player->isWinner()) {
        cout << "Joueur " << indicePlayer + 1 << ", Vous avez gagné !!!" << endl;
        exit(1);
    }
    indicePlayer = indice;
}

void FenetreJSV2::render() {
    Player* player;
    Pion* pion;
    Pion *tmp;
    window.clear(sf::Color::White);
    setBackgroundSprite(&backgroundSprite,0,0);
    for (int i = 0; i < nbPlayers; i++) {
        player = players[i];
        pion = player->getPion();
        tmp = pion;
        do {
            setPionSprite(tmp->getPionSprite(),tmp->getXPion(),tmp->getYPion());
            tmp = tmp->getNext();
        } while (tmp->getNext() != pion->getNext());
    }
    player = players[indicePlayer];//joueur actuel
    pion = player->getPion();//son pion
    setPionSprite(&blancSprite,pion->getXPion()-3,pion->getYPion()-3);//j'affiche le petit cercle blanc de selection
    window.display();
}

