 
#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Refreshing ${SCHEMA}"
    mvn -f ../../../.. clean install -Denv=$SCHEMA
}

echo "Refreshing Dbs"

dbstart "rest_indec"