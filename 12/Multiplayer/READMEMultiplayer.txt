23.12.2018 V prvom ročníku som primitívnu logickú hru z Pythonu (ročníkový projekt) riešil asi 2 týždne. 
Teraz som schopný zbúchať hru na podobnej úrovni, ktorá je ešte aj multiplayerová, na mobil a má celkom
slušné menu za 2 dni. I keď dva dni usilovnej roboty. Na úvod som tým len chcel povedať, že sa z toho
veľmi teším.

Nechcel som robiť len otrepané piškvorky, alebo niečo čo som už robil. Na internete som našiel celkom
zaujímavú hru (https://en.m.wikipedia.org/wiki/Dots_and_Boxes). Sanozrejme som to bral tak, že zadanie
nie je o tom mať ultra nadupanú hru, ale práve všetky tie multiplayerové veci okolo. Celkovo by sa
s aplikáciou dalo ešte veľa hrať a možno sa k nej niekedy v budúcnosti vrátim. 

Na hranie je potrebné sa najskôr v úvodnom menu prihlásiť. Stačí dať ľubovoľný email a heslo, ak taký
účet ešte neexistuje, automaticky sa vytvorí. Všetko samozrejme poháňané mojou FB autorizáciou...

New Game umožnuje vytvoriť novú hru s vlastným menom. Vytvorí sa "GameState", ktorý sa uloží do firebase
databázy a ako ID prvého hráča sa nastaví userID aktuálneho používateľa. Následne vyskočí AlertDialog,
ktorý umožnuje prejsť rovno do hry, prípadne zrušiť a vrátiť sa do nej neskôr...

Appka totiž umožňuje hráčovi byť súčasťou viacerých hier zároveň. Po kliknutí na Join Game sa ukáže zoznam
všetkých prebiehajúcich hier. Tu hráč vidí hry ktoré sám vytvoril, ale aj hry, ktoré vytvoril niekto iný.
Ak sa pripojí do hry iného hráča, tak sa v nej ako ID druhého hráča nastaví jeho ID. Vtedy je táto hra
plná a nemôže sa pripojiť nikto ďalší. Hrá sa dokým niekto nevyhrá, prípadne jeden z hráčov stlačí tlačidlo
Leave Game. Pri zavretí appky alebo návrate do menu ale hráč hru neopúšťa, pokojne sa k nej môže neskôr 
vrátiť a vykonať svoj ťah (ak je na rade). To mi príde dosť cool.

Samozrejme čo sa týka UI to nie je žiadna topka. Hráč si musí hry ktorých je súčasťou pamätať podľa mena
a je jasné, že keby to začalo teraz hrať veľa ludí, tak by tá ponuka v Join Game bola nekonečne dlhá...
Možný upgrade by teda bol v Join Game ukazovať iba hry, ktorých je daný hráč súčasťou, alebo do ktorých
sa ešte dá pripojiť. Nechávam to ale pre teraz takto, je to jednoduchšie...

Pre ilustráciu prikladám aj krátke video z hrania.