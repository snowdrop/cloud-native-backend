# Spring Boot, JPA, Hibernate Rest API Tutorial

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, Database (H2, MySQL), JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/snowdrop/cloud-native-backend.git
```
**2. Build and run the app using maven**

You can run the app using the `spring-boot maven plugin`

```bash
mvn clean spring-boot:run -Dspring.profiles.active=local -Ph2
```

The app will start running at <http://localhost:8080>.

**3. Explore Rest APIs**

The app defines following CRUD APIs.

    GET /api/notes
    
    POST /api/notes
    
    GET /api/notes/{noteId}
    
    PUT /api/notes/{noteId}
    
    DELETE /api/notes/{noteId}

You can test them using postman or any other rest client such as `curl`.

```bash
curl -k http://localhost:8080/api/notes 
curl -k -H "Content-Type: application/json" -X POST -d '{"title":"My first note","content":"Spring Boot is awesome!"}' http://localhost:8080/api/notes 
curl -k http://localhost:8080/api/notes/1
```

**4. Access the H2 Web Console**

Open your web browser at the following address `http://localhost:8080/h2-console` and log on using as username `sa`, database `jdbc:h2:mem:DEMO`

## Deploy it on OpenShift

1. Check if the Service Catalog contains APB services

The process to download the repository of the catalog and deploy it locally could take time !

```bash
oc get clusterserviceclasses --all-namespaces -o custom-columns=NAME:.metadata.name,DISPLAYNAME:spec.externalMetadata.displayName | grep APB
0e991006d21029e47abe71acc255e807       Pyzip Demo (APB)
135bd0df0401e2fdd52fd136935014fb       NGINX (APB)
1dda1477cace09730bd8ed7a6505607e       PostgreSQL (APB)
67042296c7c95e84142f21f58da2ebfe       MariaDB (APB)
693cb128e68365830c913631300deac0       Pyzip Demo Database (APB)
6df7afbd132c094704b4a8bfd44378c0       ManageIQ (APB)
9f7da06f179b895a8ee5f9a3ce4af7ef       Hello World (APB)
a946a139a9308a59bf642ac52b4ba317       Wordpress-HA (APB)
ab24ffd54da0aefdea5277e0edce8425       Hastebin (APB)
b43a4272a6efcaaa3e0b9616324f1099       Hello World Database (APB)
...
```

2. Create a new namespace to host the project

```bash
oc new-project demo-cloud-native
```

3. Deploy the MySQL Service within the namespace created

The serviceInstance represents a provisioned instance of the MySQL ClusterServiceClass. When a new ServiceInstance resource is created, the Service Catalog controller
will connect to the appropriate Service Broker and instruct it to provision the service instance.

```bash
oc create -f openshift/mysql_serviceinstance.yml
```

4. Create a new app on the cloud platform

```bash
oc new-app -f openshift/cloud-native-demo_backend_template.yml
```

5. Start the build using project's source

```bash
oc start-build spring-boot-db-notes-s2i --from-dir=. --follow
```

6. Bind the credentials of the ServiceInstances to a Secret

The following file will allow to access the credentials of the MySQL ServiceInstance. Upon creation, the Service Catalog controller will create a Kubernetes Secret containing connection details
and credentials for the Service Instance, which can be mounted into Pods.

```bash
oc create -f openshift/mysql-secret_servicebinding.yml
```

7. Mount the secret within the Deploymentconfig

```bash
oc env --from=secret/spring-boot-notes-mysql-binding dc/spring-boot-db-notes
```

8. Wait till the pod is recreated and then test the service

```bash
export HOST=$(oc get route/spring-boot-db-notes -o jsonpath='{.spec.host}')
curl -k $HOST/api/notes 
curl -k -H "Content-Type: application/json" -X POST -d '{"title":"My first note","content":"Spring Boot is awesome!"}' $HOST/api/notes 
curl -k $HOST/api/notes/1
```
