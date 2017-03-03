ReadMe for Assignment 7 Project

2/23/17
JJ Garzella and Mark Van der Merwe
Assginment 7 - Binary Search Trees


Summary:

This project includes a Binary Search Tree class, the SortedSet interface
which BST implements, and a spell checker class. It also includes a Test 
case for the BST, timing code for the BST, and a Demo of the Spell Checker

Notes to the TA:

1. We chose to implement some functionality in the BinarySearchTree class
instead of in the node; for example, the first() and last() methods.
2. Our timing code writes itself to .csv files, which are created 
automatically by the program. If the timing code is run again, these
files will be overwritten, so be careful.
3. In writeDot(), we choose not to share a (static?) variable or
pass in a counter As a paramater, we choose to make a unique string 
identifier for null termination. The identifier is "null" plus the 
number of the node. Also, to support negative numbers, we replace
negative signs with "n", otherwise Graphviz wouldn't do the graph
right.
4. We think we have the best set of tests for the remove() method. As this
might have been our most complicated, and the one we struggled with, we
wrote a bunch of tests. They are marked off with comments in the test case.
5. In many places, we explicitly throw a NullPointerException by design,
for clarity purposes. We could just wait for the pointer to be dereferenced,
but this is clearere. In a language like C, we would have to check.

