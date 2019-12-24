help:
	@echo "This is help"

clean:
	mvn clean

build:
	mvn install

run: build
	nohup java -jar \
		-Dspring.profiles.active=production \
		-Xmx2048m \
		rest-api/target/rest-api-0.1.jar \
		&

