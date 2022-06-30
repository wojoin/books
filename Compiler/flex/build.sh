#!/usr/bin/env bash
# $1
lex_name=$1

# split $1 and get first element split by "."
ELFFILE=$(echo $lex_name | cut -d "." -f 1)

flex $lex_name
gcc lex.yy.c -o $ELFFILE
