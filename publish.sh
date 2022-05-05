#!/bin/bash

VERSION_NAME="$1"
VERSION_CODE="${VERSION_NAME//./}"

sed -e "14s/.*/VERSION_NAME=$VERSION_NAME/" -i '' gradle.properties
sed -e "15s/.*/VERSION_CODE=$VERSION_CODE/" -i '' gradle.properties

git add .
git commit -m "$1"
git tag -a $1 -m "$1"
git push --follow-tags

./gradlew clean
./gradlew build
./gradlew uploadArchives --no-daemon --no-parallel