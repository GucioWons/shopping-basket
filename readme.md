# Shopping basket

Shopping basket is a spring application for adding and deleting products from basket.

## Installation

Execute mvn install.

Go to the project folder.

Build docker image with docker.

```bash
docker build -t shopping-basket .
```

Run container at port 9090.

```bash
docker run -p 9090:8080 shopping-basket
```