###
#
##

.PHONY: *

build: db
	./gradlew build
# build

bootRun: db
	./gradlew bootRun
# bootRun

request:
	curl -s localhost:8080/snippets
# request

snippet:
	curl -sX POST -H 'Content-Type: application/json' -d '{"title": "PHP: Hello World", "code": "<?php echo \"Hello World\"; ?>"}' localhost:8080/snippets
# snippet

db:
	[ -s db.tmp ] || docker run -d -p 3306:3306 -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_USER=springboot -e MYSQL_PASSWORD=workshop -e MYSQL_DATABASE=testdb mysql:latest > db.tmp
	[ `docker inspect -f {{.State.Running}} $$(cat db.tmp)` = "false" ] || docker start $$(cat db.tmp)
# db

stop:
	[ -s db.tmp ] && docker stop $$(cat db.tmp)
	rm db.tmp
# stop

# Makefile
