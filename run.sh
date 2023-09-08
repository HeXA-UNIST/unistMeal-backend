#!/bin/bash
docker stop hexa-meal
docker rm hexa-meal

id=$(docker run -it -p 8080:7778 -d hexa-meal)
docker rename $id hexa-meal
