help:
	@echo "This is help"

clean:
	mvn clean

test:
	mvn test

build:
	mvn install -DskipTests

run: build
	nohup java -jar \
		-Dspring.profiles.active=production \
		-Xmx2048m \
		rest-api/target/rest-api-0.1.jar \
		&

logs:
	@tail -f nohup.out -n 20

# Find PID of application for kill
pid:
	@ps aux \
		| grep rest-api \
		| grep jar \
		| grep java \
		| grep -v grep \
		|| echo "Application is not running"

