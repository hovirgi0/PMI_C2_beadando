# Fitness App

A program egy Fitness App melyben a felhasználó rögzíteni tudja jelenlegi fittségét és a kiindulási súlya, egyebek alapján az alkalmazás tippeket ad. 

Main osztályban van négy metódus (A main metóduson kívül, ahol a felhasználóval a konzol segítségével kommunikálunk): Az egyik elemek listájának lekérdezése, kiíratása a konzolra (getFormattedXML()); új elemek rögzítésére szolgál (newUserInfo()); writetoFormattedXML() előre megadott szempontok.

-> UserInfo.xml: a felhasználó által a konzolon bevitt adatokat ebben a fájlban tárolja majd, newUserInfo() metódus hozza létre

-> formattedXML.xml: az app előre megadott menüpontjait ebben a fájlban összegzi, writetoFormattedXML() metódus hozza létre


ModifyElement osztály modifyUserInfo() metódussal a felhasználó módosíthatja a korábban megadott információit

-> myTempUserInfom.xml: egy elem módosítása után ebben tárolja a megváltozott felhasználói adatokat, modifyUserInfo() metódus hozza létre


RemoveElement osztály removeUserInfo() metódussal a felhasználó törölheti a korábban megadott információit

-> myTempUserInfor.xml: egy elem eltávolítása után ebben tárolja a módosult felhasználói adatokat, removeUserInfo() metódus hozza létre
