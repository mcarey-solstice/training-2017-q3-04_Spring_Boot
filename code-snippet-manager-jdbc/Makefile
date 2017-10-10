###
#
##

START = 10/1/2027
END = 10/31/2027

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

filtered_request:
	@echo "---- Start only ----"
	curl -s localhost:8080/snippets?start=2027-10-01
	@echo
	@echo "---- End only ----"
	curl -s localhost:8080/snippets?end=2027-10-31
	@echo
	@echo "---- Both ----"
	curl -s localhost:8080/snippets?start=2027-10-01\&end=2027-10-31
# filtered_request

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
