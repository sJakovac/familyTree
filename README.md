## Information
Solution to problem posted in [a relative link](Test-Stjepan.pdf)

## Tests
Testing components and solution itself is done running following command.

```
$ mvn test
```

## Running examples
Running a program with example from exercise:

```
$ mvn exec:java
```
shoud produce this output:https://github.com/sJakovac/familyTree


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
$ mvn exec:java -Dexec.args="{path-to-file}"
```