#!/usr/bin/env bash

function build_docker(){
    echo "Building docker image mssql_supermercados:1.0"
    docker build --no-cache -t mssql_supermercados:1.0 .
}
build_docker