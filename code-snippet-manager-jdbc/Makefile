###
#
##

.PHONY: *

build:
	./gradlew build
# build

bootRun:
	./gradlew bootRun
# bootRun

request:
	curl -s localhost:8080/snippets
# request

snippet:
	curl -sX POST -d 'title=PHP%3A%20Hello%20World&code=%3C%3Fphp%20echo%20%22Hello%20World%22%3B%20%3F%3E' localhost:8080/snippets
# snippet

# Makefile
