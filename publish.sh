#!/bin/bash

git add .
git commit -m "$1"
git tag -a $1 -m "$1"
git push --follow-tags

./gradlew clean
./gradlew build
./gradlew uploadArchives --no-daemon --no-parallel
./gradlew closeAndReleaseRepository
#./gradlew bintrayUpload -PbintrayUser="$2" -PbintrayKey="$3"
#./gradlew :lib:publishAllPublicationsToGithubRepository