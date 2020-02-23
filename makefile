compile: 
	find . -name *.java > sources.txt
	javac -d classes -cp jars/sqlite-jdbc-3.27.2.1.jar:jars/gson-2.6.2.jar @sources.txt
