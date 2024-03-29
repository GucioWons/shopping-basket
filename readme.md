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

## API Reference

### Products

#### Get all products

```http
  GET /products
```

### Baskets

#### Create basket

```http
  POST /baskets
```

#### Add products to basket

```http
  PUT /baskets/{basketId}/{productId}/{quantity}
```

| Parameter | Type     |Required|Description                  |
| :-------- | :------- | :----- |:--------------------------- |
| `basketId`| `string` | `YES`  |Id of basket |
| `productId`| `string` | `YES`  |Id of product to add |
| `quantity`| `int` | `YES`  |Quantity of products to add |

#### Remove products from basket

```http
  DELETE /baskets/{basketId}/{productId}/{quantity}
```

| Parameter | Type     |Required|Description                  |
| :-------- | :------- | :----- |:--------------------------- |
| `basketId`| `string` | `YES`  |Id of basket |
| `productId`| `string` | `YES`  |Id of product to remove |
| `quantity`| `int` | `YES`  |Quantity of products to remove |

#### Get basket by id

```http
  GET /baskets/{basketId}
```

| Parameter | Type     |Required|Description                  |
| :-------- | :------- | :----- |:--------------------------- |
| `basketId`| `string` | `YES`  |Id of basket to get |
