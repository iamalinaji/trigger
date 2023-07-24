# trigger
This project is based on Spring Boot and incorporates Spring JPA along with Liquibase for database management.

## How to Use Liquibase with spring boot project:
1. Set the appropriate versions in the properties section of your pom file.
```
	<properties>
		<liquibase-maven-plugin.version>4.19.0</liquibase-maven-plugin.version>
		<liquibase-hibernate.version>4.19.0</liquibase-hibernate.version>
	</properties>
```
2. Add necessary dependencies.
```
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-maven-plugin</artifactId>
			<version>${liquibase-maven-plugin.version}</version>
		</dependency>
```
3. Add the "liquibase.properties" file to the resources directory of your project. Here is an example of how would be the contents:
```
changeLogFile: src/main/resources/db/master.xml
driver: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/<nameofyourdb>
username: <username>
password: <password>
referenceUrl: hibernate:spring:com.example.model?dialect=org.hibernate.dialect.MySQL8Dialect
```
**Note**: Don't forget to change the `com.example` to the appropriate one in your project.

4. Add this plugin:
```
			<plugin>
				<groupId>org.liquibase</groupId>
				<artifactId>liquibase-maven-plugin</artifactId>
				<version>${liquibase-maven-plugin.version}</version>
				<configuration>
					<changeLogFile>src/main/resources/db/changelog/master.xml</changeLogFile>
					<outputChangeLogFile>src/main/resources/db/changelog/migration/1.0_init.mysql.sql</outputChangeLogFile>
					<!--suppress UnresolvedMavenProperty -->
					<diffChangeLogFile>src/main/resources/db/changelog/migration/_${diff.version}_migrate.mysql.sql</diffChangeLogFile>
					<propertyFile>src/main/resources/liquibase.properties</propertyFile>
				</configuration>

				<dependencies>
					<dependency>
						<groupId>org.liquibase.ext</groupId>
						<artifactId>liquibase-hibernate6</artifactId>
						<version>${liquibase-hibernate.version}</version>
					</dependency>
					<dependency>
						<groupId>org.springframework.data</groupId>
						<artifactId>spring-data-jpa</artifactId>
						<version>3.0.2</version>
					</dependency>
					<dependency>
						<groupId>io.hypersistence</groupId>
						<artifactId>hypersistence-utils-hibernate-60</artifactId>
						<version>3.2.0</version>
					</dependency>
					<dependency>
						<groupId>com.fasterxml.jackson.core</groupId>
						<artifactId>jackson-databind</artifactId>
						<version>2.14.2</version>
					</dependency>
				</dependencies>
			</plugin>
```
You're all set! Now run `mvn liquibase:diff -Ddiff.version=1.0` to generate the migration file in .sql format
