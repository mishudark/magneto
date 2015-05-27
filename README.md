# Magneto mutant checker
check if dna is a mutant variation

## Running Locally

```sh
$ mvn install
$ foreman start web
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Make request
Send a json with this format  to

https://rocky-hamlet-9109.herokuapp.com/ismutant

```
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
```

###Curl example
```
  curl -X POST -H "Content-Type: text/json" -H "Cache-Control: no-cache" -d \
  '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}' https://rocky-hamlet-9109.herokuapp.com/ismutant
```
