# Utilize Jenkins pipeline build

## Prerequisites

- The Openshift Sync Jenkins plugin needs to be configured to watch the namespace where the application will be deployed to

- The Jenkins SA needs to able to watch various resources in the namespace where the application will be deployed to

The easiest way to give Jenkins SA such permissions is to execute

```bash
    $ oc adm policy add-cluster-role-to-user edit system:serviceaccount:infra:jenkins -n $(oc project -q)
```

- The user has logged in to Openshift and is using the namespace where the application is going to be deployed 

## Create pipeline

```bash
    $ ./create-pipeline.sh
```

The script works by utilizing the `openshift/jenkins-bc` "template" and substituting the namespace in the provided placeholder
The BuildConfig that is created contains an environment variable whose value is the namespace where the application will be deployed.
Finally the `podTemplate` of the pipeline utilizes the aforementioned environment variable to switch namespaces using `oc project`.

## Start pipeline

```bash
    $ ./start-pipeline.sh
```

  
 
