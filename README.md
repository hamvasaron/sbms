# sbms
Just a sample spring-boot microservice

# Minikube on Windows with Docker Environment 
https://www.shellhacks.com/install-minikube-on-windows/

1. Enable Hyper-V
2. Download Minikube: https://github.com/kubernetes/minikube/releases
3. Download kubectl.exe: https://dl.k8s.io/release/v1.23.3/bin/windows/amd64/kubectl.exe
   1. Copy to Minikube installation directory
4. `minikube start --vm=true --container-runtime=docker --hyperv-virtual-switch=minikube`
   1. An external virtual switch named minikube needs to be created so that minikube node gets IP address.
5. To use Minikube docker-env in docker-cli in CMD:
   1. `@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i`
6. In Git Bash:
   1. `eval $(minikube -p minikube docker-env)`
7. Minikube dashboard:
   1. Install: `minikube dashboard`
   2. Get URL: `minikube dashboard --url`

# Containerize spring-boot application
https://hackmd.io/@ryanjbaxter/spring-on-k8s-workshop

1. To start outside container:
   1. `./mvnw -DskipTests spring-boot:run`
   2. query spring-boot app: `curl http://localhost:8080/actuator/health`
2. Docker image:
   1. build docker image: ` ./mvnw -DskipTests -Djdk.tls.client.protocols=TLSv1.2 -Dspring-boot.build-image.imageName=docker.io/hamvasaron/sbms spring-boot:build-image`
   2. start docker image: `docker run --name sbms -p 8080:8080 hamvasaron/sbms:0.0.1-SNAPSHOT`
   3. get minikube cluster ip: `minikube ip`
   4. query spring-boot app: `curl http://<minikube_ip>:8080/actuator/health`
   5. push to docker.io: `docker push hamvasaron/sbms`
3. K8s deployment:
   1. Create deployment manifest:
      1. `kubectl create deployment k8s-demo-app --image hamvasaron/sbms -o yaml --dry-run=client > k8s/deployment.yaml`
   2. Create service manifest:
      1. `kubectl create service clusterip k8s-demo-app --tcp 80:8080 -o yaml --dry-run=client > k8s/service.yaml`
   3. Deploy:
      1. `kubectl apply -f ./k8s`
      2. In another terminal: `watch -n 1 kubectl get all` to see when deployment is ready
   4. Port-forward to access app:
      1. `kubectl port-forward service/k8s-demo-app 8080:80`
   5. Verify: `curl http://localhost:8080/actuator/health`
