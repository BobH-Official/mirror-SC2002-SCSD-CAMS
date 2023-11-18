build:
	javac --enable-preview --source 21 -d out/make --source-path SC2002Project/src SC2002Project/src/CAMS/*.java SC2002Project/src/CAMS/Data/*.java SC2002Project/src/CAMS/Data/Utils/*.java SC2002Project/src/CAMS/Operator/*.java

run:
	java --enable-preview -cp out/make CAMS.App

clean:
	rm -r out/make
