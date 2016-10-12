ARGS = -Wall -std=c++11

CaseJeuSerpent.o : CaseJeuSerpent.h CaseJeuSerpent.cpp Case.h
	g++ $(ARGS) -c CaseJeuSerpent.cpp

Case.o : Case.h Case.cpp
	g++ $(ARGS) -c Case.cpp

Fenetre.o : Fenetre.h Fenetre.cpp Case.h Controler.h Observer.h Player.h
	g++ $(ARGS) -c Fenetre.cpp

Plateau.o : Pion.h Observer.h Player.h Plateau.h Plateau.cpp Case.h
	g++ $(ARGS) -c Plateau.cpp

PlateauJeuSerpent.o : Pion.h Plateau.h Case.h Player.h PlateauJeuSerpent.h PlateauJeuSerpent.cpp
	g++ $(ARGS) -c PlateauJeuSerpent.cpp

Controler.o : Pion.h Plateau.h Controler.h Controler.cpp
	g++ $(ARGS) -c Controler.cpp

FenetreJS.o : Observer.h Case.h Player.h Fenetre.h FenetreJS.h FenetreJS.cpp
	g++ $(ARGS) -c FenetreJS.cpp

FenetreJSV1.o : Fenetre.h FenetreJSV1.h FenetreJSV1.cpp
	g++ $(ARGS) -c FenetreJSV1.cpp

FenetreJSV2.o : Observer.h Fenetre.h FenetreJSV2.h FenetreJSV2.cpp
	g++ $(ARGS) -c FenetreJSV2.cpp

Partie.o : Plateau.h Controler.h Partie.h Partie.cpp
	g++ $(ARGS) -c Partie.cpp

Pion.o : Pion.h Pion.cpp
	g++ $(ARGS) -c Pion.cpp

Player.o : Player.h Player.cpp
	g++ $(ARGS) -c Player.cpp

GenerateurJS.o : GenerateurJS.h GenerateurJS.cpp
	g++ $(ARGS) -c GenerateurJS.cpp

serpentechelleV2.o : serpentechelleV2.cpp
	g++ $(ARGS) -c serpentechelleV2.cpp

jeuJSV2 : Case.o CaseJeuSerpent.o Partie.o Plateau.o PlateauJeuSerpent.o Fenetre.o FenetreJS.o FenetreJSV1.o FenetreJSV2.o Controler.o serpentechelleV2.o Pion.o Player.o GenerateurJS.o
	g++ $(ARGS) Case.o CaseJeuSerpent.o Plateau.o PlateauJeuSerpent.o Partie.o Fenetre.o FenetreJS.o FenetreJSV1.o FenetreJSV2.o Controler.o serpentechelleV2.o Pion.o Player.o GenerateurJS.o -o jeuJSV2 -lsfml-graphics -lsfml-window -lsfml-system

clean :
	rm -f *.o
	rm -f *~
