Note: You will be prompted for an input file name. Ensure that the input file is in
same directory as the rest of the files. Input file name must include the extention.
Ex: a1_test_data.txt 

How to compile and run on aviary
Add the following statements:
   	
	make
	java Main
	// enter the input file name


How to compile and run the unit tests
Add the following commands:

	javac -cp .:junit-platform-console-standalone-1.6.0.jar TestList.java
	java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path