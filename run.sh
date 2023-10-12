#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o xtrace

java \
  -Djava.security.properties=file:config/java.security.additional \
  -Dquarkus.http.host=0.0.0.0 \
  -Dquarkus.config.locations=config/application.properties \
  -Djava.util.logging.manager=org.jboss.logmanager.LogManager \
  -Dsemeru.fips=${FIPS_ENABLED:-"true"} \
  '--class-path=target/quarkus-app/lib/boot/*:target/quarkus-app/lib/main/*' \
  -jar target/quarkus-app/quarkus-run.jar
