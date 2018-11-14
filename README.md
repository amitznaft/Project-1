# Project-1

This project represent:
1. simple “Monom” of the shape : aX^b , where ‘a’ is a real number and ‘b’ is an
integer (none negative). Init of simple “Monom”:
• Constructor that get a double coefficient and int power.
• Constructor that get a String of the type “Monom”, proper input : a \ ax \ ax^b
where ‘a’ is a real number and ‘b’ is an integer (none negative).
This class support simple operations as : value at x , derivative , add , multiply , equals and tostring.
2. “Polynom” of the shape : a_1X^b_1 + ... + a_nX^b_n , where each organ in the polynomial is a simple “Monom”.
Init of Polynom:
• Constructor that init() the zero Polygon
• Constructor that get a String of the type “Polynom” ( proper input like the simple “Monom”).
This class implements the interface Polynom_able and support operations as: value at x , derivative , add(Monom), add (Polynom), multiply ,substruct , equals , Iszero , root , area and tostring.

** in our project we use the code from: https://github.com/eseifert/gral
** link for download the gral.jar: http://trac.erichseifert.de/gral/wiki/Download  --> ​gral-core-0.11.jar (273 KiB)
Author’s : Amit Znaft and Yuval Cohen
