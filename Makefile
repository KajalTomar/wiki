# define compiler and compiler flag variables
JC = javac

# clear any default targets for building .class files from .java files
.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

# macro for each java source file
CLASSES = \
        Document.java \
        Entity.java \
        FileReader.java \
        Line.java \
		List.java \
		Main.java \
		Node.java \
		User.java \
		Version.java \
		Wiki.java

# the defaul make target entry
default: classes

# target entry
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
