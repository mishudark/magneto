# Magneto mutant checker
check if dna is a mutant variation

## Running Locally

```sh
$ mvn install
$ foreman start web
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Make request
Send a json with this format 

```
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]};
```
