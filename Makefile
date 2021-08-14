run:
	javac Graph.java
	java Graph nodes.csv edges.csv
avg:
	javac Graph.java
	java Graph nodes.csv edges.csv average
rank:
	javac Graph.java
	java Graph nodes.csv edges.csv rank
dfs:
	javac Graph.java
	java Graph nodes.csv edges.csv independent_storylines_dfs
clean:
	rm *.class
