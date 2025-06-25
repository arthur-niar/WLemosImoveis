
# 🏠 WLemos Imóveis

Sistema web para busca e gerenciamento de imóveis utilizando **Spring Boot** no backend e **Bootstrap + HTML/CSS** no frontend. Idealizado para facilitar a consulta de imóveis com uma interface simples, limpa e funcional. Feito para a empresa WLemos Negócios.

---

## Funcionalidades

- **Busca dinâmica de imóveis** por diferentes atributos (endereço, tipo, valor, etc)
- **Listagem interativa** com cards visuais de cada imóvel
- **Template com imagem destacada** para cada imóvel
- Integração entre **frontend e backend**
- Estrutura pronta para deploy futuro

---

## Tecnologias Utilizadas

**Backend:**
- Java 17
- Spring Boot
- JPA (Hibernate)
- H2 Database (para testes locais)

**Frontend:**
- HTML5
- CSS3
- Bootstrap 5

**Ferramentas:**
- Maven
- Git/GitHub
- Insomnia (para testar API)

---

## Como rodar o projeto localmente

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Git

---

### 1. Clone o projeto

```bash
git clone https://github.com/arthur-niar/WLemosImoveis.git
cd WLemosImoveis
```

---

### 2. Backend (Spring Boot)

#### Rodar com Maven

```bash
./mvnw spring-boot:run
```

#### Ou via IDE

- Abra o projeto na sua IDE (IntelliJ ou Eclipse)
- Rode a classe principal: `WlemosImoveisApplication.java`

#### Acesso

- A API estará disponível em: [http://localhost:8081](http://localhost:8081)

---

### 3. Frontend

- Acesse o caminho `src/main/resources/static`
- Abra o `index.html` com um servidor local (VS Code com Live Server, ou apache/Nginx)
- Ou simplesmente abra com o navegador (limitado para requisições GET)

---

### Testes da API

Use o Insomnia ou outro cliente REST para testar os endpoints:

| Método | Rota            | Descrição             |
|--------|------------------|------------------------|
| GET    | `/imoveis`      | Lista todos os imóveis |
| POST   | `/imoveis`      | Cria um novo imóvel    |
| PUT    | `/imoveis/{id}` | Atualiza um imóvel     |
| DELETE | `/imoveis/{id}` | Remove um imóvel       |

OBS: Criar o body em JSON
---

## Interface

Os imóveis são exibidos em **cards** com:

- 📍 Endereço
- 💰 Valor
- 🏢 Tipo
- 📸 Imagem (mock)

---

## Estrutura do Projeto

```plaintext
WLemosImoveis/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/wlemosimoveis/
│   │   │   │   ├── controller/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   └── WlemosImoveisApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── data.sql
├── frontend/
│   └── index.html
├── README.md
└── pom.xml
```

---

## Contribuições

Contribuições são bem-vindas! Sinta-se livre para abrir issues ou pull requests.

---

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Desenvolvido com ❤️ (e ☕) por Arthur Lemos.
