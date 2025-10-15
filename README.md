<img width="1920" height="250" alt="API README-4" src="https://github.com/user-attachments/assets/2affc4f5-3eaf-464d-ba77-bf492b261e41" />


## Documentação da API - Sistema de Biblioteca

Esta é a documentação para a API REST de gerenciamento de livros da biblioteca.

**URL Base:** `http://localhost:8080`

## Recurso: Livros (`/livros`)

-----

### 1\. Listar todos os livros

Retorna uma lista com todos os livros cadastrados no sistema.

  * **Endpoint:** `GET /livros`
  * **Método:** `GET`
  * **Resposta de Sucesso (200 OK):**
      * **Corpo:** Um array de objetos `Livro`.
    <!-- end list -->
    ```json
    [
        {
            "id": 1,
            "titulo": "Dom Casmurro",
            "qtdPaginas": 256,
            "publicacao": {
                "autor": "Machado de Assis",
                "dataPublicacao": "1899-03-10",
                "editora": "Livraria Garnier"
            }
        },
        {
            "id": 2,
            "titulo": "1984",
            "qtdPaginas": 328,
            "publicacao": {
                "autor": "George Orwell",
                "dataPublicacao": "1949-06-08",
                "editora": "Companhia das Letras"
            }
        }
    ]
    ```

-----

### 2\. Buscar livro por ID

Retorna os detalhes de um livro específico.

  * **Endpoint:** `GET /livros/{id}`
  * **Método:** `GET`
  * **Parâmetros de URL:**
      * `id` (Obrigatório): O ID do livro a ser buscado.
  * **Resposta de Sucesso (200 OK):**
      * **Corpo:** O objeto `Livro` correspondente ao ID.
    <!-- end list -->
    ```json
    {
        "id": 1,
        "titulo": "Dom Casmurro",
        "qtdPaginas": 256,
        "publicacao": {
            "autor": "Machado de Assis",
            "dataPublicacao": "1899-03-10",
            "editora": "Livraria Garnier"
        }
    }
    ```
  * **Resposta de Erro (404 Not Found):**
      * **Corpo:** Retornado caso o ID do livro não seja encontrado.
    <!-- end list -->
    ```json
    {
        "status": 404,
        "titulo": "Recurso Não Encontrado",
        "dataHora": "2025-10-15T18:30:00.123456",
        "erros": [
            "Livro não encontrado com o id: 99"
        ]
    }
    ```

-----

### 3\. Criar um novo livro

Cadastra um novo livro no sistema.

  * **Endpoint:** `POST /livros`
  * **Método:** `POST`
  * **Corpo da Requisição (Obrigatório):**
      * **Content-Type:** `application/json`
      * **Exemplo:**
    <!-- end list -->
    ```json
    {
        "titulo": "Psicose",
        "qtdPaginas": 240,
        "publicacao": {
            "autor": "Robert Bloch",
            "dataPublicacao": "1959-04-11",
            "editora": "Darkside Books"
        }
    }
    ```
  * **Resposta de Sucesso (201 Created):**
      * **Corpo:** O objeto `Livro` recém-criado, incluindo o `id` gerado pelo sistema.
  * **Resposta de Erro (400 Bad Request):**
      * **Corpo:** Retornado caso haja erros de validação (ex: campos vazios ou com tamanho inválido).
    <!-- end list -->
    ```json
    {
        "status": 400,
        "titulo": "Erro de Validação",
        "dataHora": "2025-10-15T18:35:10.987654",
        "erros": [
            "publicacao.autor: O nome do autor não pode ser vazio.",
            "titulo: O título não pode ser vazio."
        ]
    }
    ```

-----

### 4\. Atualizar um livro existente

Modifica os dados de um livro já cadastrado.

  * **Endpoint:** `PUT /livros/{id}`
  * **Método:** `PUT`
  * **Parâmetros de URL:**
      * `id` (Obrigatório): O ID do livro a ser atualizado.
  * **Corpo da Requisição (Obrigatório):**
      * **Content-Type:** `application/json`
      * **Exemplo:**
    <!-- end list -->
    ```json
    {
        "titulo": "Dom Casmurro (Edição de Luxo)",
        "qtdPaginas": 300,
        "publicacao": {
            "autor": "Machado de Assis",
            "dataPublicacao": "1899-03-10",
            "editora": "Editora Antofágica"
        }
    }
    ```
  * **Resposta de Sucesso (200 OK):**
      * **Corpo:** O objeto `Livro` com os dados atualizados.
  * **Resposta de Erro:**
      * `400 Bad Request`: Se os dados no corpo da requisição forem inválidos.
      * `404 Not Found`: Se o `id` fornecido na URL não existir.

-----

### 5\. Deletar um livro

Remove um livro do sistema.

  * **Endpoint:** `DELETE /livros/{id}`
  * **Método:** `DELETE`
  * **Parâmetros de URL:**
      * `id` (Obrigatório): O ID do livro a ser deletado.
  * **Resposta de Sucesso (204 No Content):**
      * A resposta não possui corpo.
  * **Resposta de Erro (404 Not Found):**
      * Retornado caso o ID do livro não seja encontrado.
    <!-- end list -->
    ```json
    {
        "status": 404,
        "titulo": "Recurso Não Encontrado",
        "dataHora": "2025-10-15T18:40:00.555555",
        "erros": [
            "Livro não encontrado com o id: 101"
        ]
    }
    ```
-----
  
### Estrutura do Projeto: h2biblioteca

```
📁 h2biblioteca
└── 📁 src
    └── 📁 main
        ├── 📁 java
        │   └── 📁 org
        │       └── 📁 serratec
        │           └── 📁 h2biblioteca
        │               ├── ☕ H2bibliotecaApplication.java
        │               ├── 📁 controller
        │               │   └── ☕ LivroController.java
        │               ├── 📁 converter
        │               │   └── ☕ LivroConverter.java
        │               ├── 📁 domain
        │               │   ├── ☕ Livro.java
        │               │   └── ☕ Publicacao.java
        │               ├── 📁 exception
        │               │   ├── ☕ ControllerExceptionHandler.java
        │               │   ├── ☕ ErroResposta.java
        │               │   └── ☕ RecursoNaoEncontradoException.java
        │               └── 📁 repository
        │                   └── ☕ ILivroRepository.java
        └── 📁 resources
            ├── 📁 static
            ├── 📁 templates
            └── 📄 application.properties
    └── 📁 test
        └── 📁 java
            └── 📁 org
                └── 📁 serratec
                    └── 📁 h2biblioteca
                        └── ☕ H2bibliotecaApplicationTests.java
```
