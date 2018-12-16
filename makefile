all:
	mkdir -p classes
	javac \
		-sourcepath src \
		-classpath lib/json-simple-1.1.1.jar:. \
		Main.java MyServerSocket.java MyClientSocket.java Doctor.java Paramedico.java Enfermero.java Pair.java Requerimientos.java Pacientes.java MyThread.java Election.java Process.java\
		-d classes

test: all
	java \
		-cp classes:lib/json-simple-1.1.1.jar \
		Main

clean:
	rm -rf classes
