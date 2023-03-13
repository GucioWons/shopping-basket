# Shopping basket

Shopping basket is a spring application for adding and deleting products from basket.

## Installation

Go to the project folder.

Execute mvn install:

```bash
mvn install -DskipTests
```

Build docker image with docker:

```bash
docker build -t shopping-basket:{version of app} .
```

Load docker image into minikube:

```bash
minikube image load shopping-basket:{version of image}
```

Go to the kubernetes folder in the project folder.

Build helm dependencies:

```bash
helm dependency build ./shopping-basket
```

Execute helm install

```bash
helm install shopping-basket ./shopping-basket
```

