Reversi

Hra pre dvoch - Firebaseovská

Jazyk: Kotlin

Link na zip projektu a apk: https://drive.google.com/open?id=1A7dIy8SflgO2N7dUePIPokihdCoDA0a8

Ovládanie:

- Firebase Email autorizácia cez tlačidla 'Sign Up', 'Sign In'
- Odhlásenie po autorizácii cez tlačidlo 'Sign Out'
- Tlačidlo 'Play' pre začiatok hry, dostupné len po autorizacii
- Hráč 1 označí, že iný hráč sa vie k nemu pripojiť pomocou tlačidla 'Connection pending'
- Hráč sa pripojí k inému hráčovi tak, že napíše jeho login. Ak k hráčovi sa da pripojiť, potom hracom sa zobrazí hracia plocha
- Hráči vidia rovnakú hraciu plochu, striedavo ťahajú, pričom ťah protihráča sa im rozumne zobrazuje na ich zariadení
- Keď je hráč na ťahu, môže vykonať zmenu na hracej ploche
- Keď je súper na ťahu, tak sa len pozerá, nemôže ťahať
- Koniec hry:
	- Ked jeden hrac vyhra, obidva sa o tom dozvedia a hra skonci
    - Ked jeden hrac stlaci 'Give Up', druhy hrac sa dozvie o tom, ze vyhral
    - Ked jeden hrac sa odhlasi, druhy hrac sa dozvie o tom, ze vyhral