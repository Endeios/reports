#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail
if [[ "${TRACE-0}" == "1" ]]; then
    set -o xtrace
fi

if [[ "${1-}" =~ ^-*h(elp)?$ ]]; then
    echo 'Usage: ./do.sh <area> <subcommand>

This is an awesome bash script to make your life better.
One can
   do.sh start db
   do.sh stop db

'
    exit
fi

cd "$(dirname "$0")"

handle_db () {
  echo "Working on db.."
   if [[ "$1" == "start" ]]; then
      echo "Starting db.."
      podman run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
      exit
   fi
   if [[ "$1" == "stop" ]]; then
      echo "Stopping db.."
      podman stop postgres
      podman rm postgres
      exit
   fi
}

handle_message_system() {
  echo "Working on message system.."
   if [[ "$1" == "start" ]]; then
      echo "Starting message system.."
      podman run -d --hostname rabbit --name rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
      exit
   fi
   if [[ "$1" == "stop" ]]; then
      echo "Stopping message system.."
      podman stop rabbit
      podman rm rabbit
      exit
   fi
}

main() {
    # echo "$1 $2"
    if [[ "$1" == "db" ]]; then
      handle_db "$2"
      exit
    fi
    if [[ "$1" == "msg" ]]; then
      handle_message_system "$2"
      exit
    fi
}

main "$@"
