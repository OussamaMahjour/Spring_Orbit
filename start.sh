#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Loop through all subdirectories
for dir in */; do
  # Check if the directory contains a pom.xml
  if [ -f "$dir/pom.xml" ]; then
    echo "Building Maven project in directory: $dir"
    # Navigate into the directory
    cd "$dir"
    # Run Maven package
    ./mvnw package
    # Navigate back to the parent directory
    cd ..
  else
    echo "Skipping directory: $dir (no pom.xml found)"
  fi
done

echo "All Maven projects have been built."

echo "Starting the containers"
docker compose up --build
