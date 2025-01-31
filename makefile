build:
	javac --enable-preview --source 21 -d out/make --source-path \
	SC2002Project/src SC2002Project/src/CAMS/*.java \
	SC2002Project/src/CAMS/Data/*.java SC2002Project/src/CAMS/Data/Utils/*.java \
	SC2002Project/src/CAMS/Operator/*.java

run: out/make
	java --enable-preview -cp out/make CAMS.App $(arg)

runarg: out/make
	java --enable-preview -cp out/make CAMS.App \
	--student datasets/student.csv --staff datasets/staff.csv \
	-o datasets/data.cams

clean:
	rm -r out/make
