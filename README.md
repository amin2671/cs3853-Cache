# cs3853project

How to use this program
******************************
In command prompt, perform the following line:

java -jar group19milestone1.exe -f FILENAME -s CACHESIZE -b BLOCKSIZE -a ASSOCIATIVITY -r POLICIES

Replace only the words that are capitalized.

Example using a file in the repository:
java -jar group19milestone1.exe -f TinyTrace -s 4096 -b 32 -a 2 -r RR



FILENAME: 	File to be analyzed

CACHESIZE:	Size of the theoretical cache	- Can only be a power of 2 from 1 KB (1024) to 8 MB

BLOCKSIZE:	Size of blocks			- Can only be a power of 2 from 4 to 64 bytes

ASSOCIATIVTY:	Determines cache associativity	- Can only be 1, 2, 4, 8, or 16

POLICIES:	Determines cache policies	- Can only be RR, RND, or LRU
