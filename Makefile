postgres:
	docker run --name postgres13 -p 5432:5432 -e POSTGRES_USER=root  -e POSTGRES_PASSWORD=aaa -d postgres:13

createdb:
	docker exec -it postgres13 createdb --username=root --owner=root hr

dropdb:
	docker exec -it postgres13 dropdb hr

start-dev:
	mvn spring-boot:run -Dspring-boot.run.profiles=dev

helm-test:
	helm upgrade --install --dry-run --debug -o yaml api helm/hr -n hr

helm-deploy:
	helm upgrade --install api helm/hr -n hr
helm-remove:
	helm uninstall api -n hr

.PHONY: postgres createdb dropdb start-dev helm-test helm-deploy helm-remove
