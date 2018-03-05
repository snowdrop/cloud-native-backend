#!/usr/bin/env bash

# Creates jenkins pipeline for a namespace
# The only argument passed to the script is the namespace / project into which the pipeline BC will be created
# If no argument is passed, it's assumed that the namespace to be used is the current namespace

# The script assumes that oc login has been performed

namespace=${1:-$(oc project -q)}

substituted_var_filename=jenkins-bc-temp.yml

# change the namespace placeholder
sed -e "s/changeme/${namespace}/g" openshift/jenkins-bc.yaml > ${substituted_var_filename}

# actually create the JenkinsPipeline BuildConfig
oc apply -f ${substituted_var_filename}

# remove temporary file
rm ${substituted_var_filename}

