**ABOUT THE PROBLEM**
The project was to write a Java program Graph.java , that takes three command line arguments: two csv files(samples given below), and a function name. Based on the function name, program output changes.
**FUNCTIONS IMPLEMENTED**
1.average
2.rank using merge sort
3.independent_storylines_dfs using DFS (DEPTH FIRST SEARCH) 
**COMPILATION**
  Compile .java files using
    javac Graph.java
**EXECUTION**
  Run .java files using  
    $java filename nodes.csv edges.csv function_name
**Example**
  $java Graph nodes.csv edges.csv average
  $java Graph nodes.csv edges.csv rank
  $java Graph nodes.csv edges.csv independent_storylines_dfs
**Sample Test Cases**
  nodes.csv 327 nodes: Each node represents a character.
  edges.csv 9891 edges (with weights): Edges between nodes/characters represents that the
  characters/nodes have appeared together. Edge-weights are proportional to the number of co-occurrences.
**Sample Output** 
