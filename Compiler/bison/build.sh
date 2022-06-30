#!/usr/bin/env bash

# ./build.sh calc1.y calc1.l
# calc1
bison_name=$1
flex_name=$2

# split $1 and get first element split by "."
ELFFILE=$(echo $bison_name | cut -d "." -f 1)


# generate the header file y.tab.h (-d) and source file y.tab.c (-y)
# bison #bison_name will generate #bison_name.tab.c source file
bison -y -d $bison_name
# generate lex.yy.c
flex $flex_name

# flex file: lex.tab.c, bison file: y.tab.c
gcc -c y.tab.c lex.yy.c
gcc y.tab.o lex.yy.o -o $ELFFILE
