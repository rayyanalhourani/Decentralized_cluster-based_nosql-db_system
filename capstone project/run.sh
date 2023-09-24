#!/bin/sh

echo "Enter number of nodes that you want"
read nodes

docker network create -d bridge net

docker run -d --name bootstrapping -p 8080:8080 --network net --env numOfNodes="${nodes}" bootstrapping

for (( i = 1; i < (nodes+1); i++ )); do
    docker run -d --name node"$i" -p 808"$i":8080 --network net --env numOfNodes="${nodes}" --env nodeNumber="$i" node
done

