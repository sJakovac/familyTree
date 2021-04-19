## Information
Solution to problem posted in [problem pdf-file](Test-Stjepan.pdf)

## Tests
Testing components and solution itself is done running following command.

```
$ mvn test
```

## Running examples
Running a program with example from exercise:

```
$ mvn compile
$ mvn exec:java
```
shoud produce this output:


```
Ivan
	Adam
		Stjepan
			Marko
			Robert
	Fran
Luka
	Leopold
```
Runnig with custom example:

```
$ mvn compile
$ mvn exec:java -Dexec.args="{path-to-file}"
```
