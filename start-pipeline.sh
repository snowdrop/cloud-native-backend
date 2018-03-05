#!/usr/bin/env bash

# Start jenkins pipeline for a namespace
# The only argument passed to the script is the namespace / project into which the pipeline BC will be created
# If no argument is passed, it's assumed that the namespace to be used is the current namespace

# The script assumes that oc login has been performed

namespace=${1:-$(oc project -q)}

oc start-build cloud-native-backend-${namespace}
