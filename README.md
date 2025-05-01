# Springboot backend template

# Install
- Requires Java 21:
	- on Mac:
	- `brew install openjdk@21`
	- in your .zshrc or .bash_profile put:
		export JAVA_HOME=`/usr/libexec/java_home -v 21`

- Requires MySQL

- run `mvn clean install`
- and then `mvn spring-boot:run`

# Test API
- By default it will run on http://localhost:8080 with user: admin and pass: admin123 (see application.properties)
- can run the APIs with curl or on postman. For example:
	- `curl -u admin:admin123 http://localhost:8080/api/items`
	- `curl -u admin:admin123 -X POST http://localhost:8080/api/items      -H "Content-Type: application/json"      -d '{"name": "iPad", "quantity": 15, "price": 499.99}'`
