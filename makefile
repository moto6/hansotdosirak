DB_URL=mysql://root:root@localhost:23306/?sslmode=disable

start:
	make create-db && make serve

serve:
	./gradlew build -x test && ./gradlew bootRun

create-db:
	docker-compose -f docker-compose-infra.yml up -d


.PHONY: create-db serve start
