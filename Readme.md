## Desafio API Oh My Dog :dog:

## Configuração :rocket:
O projeto foi criado com:
- [IDE Eclipse] versão 2022-03 (4.23.0).
- Iniciado com [spring initializr], com as configurações e dependências:
    - Projeto: [Maven] Project
    - Linguagem: Java
    - [Spring Boot] 2.7.1
    - [Java 17]
    - Dependencies:
        - Spring Data JPA
        - Validation
        - Spring Web
        - Spring Boot DevTools
        - MySQL Driver
        - Lombok
        - h2Database
        - swagger
        - JWT
        - Spring Security
        - Gson
        - WebFlux
        
### API DOG - Desafio API Starter #4 :rocket:
Nste projeto foram realizados as seguintes tasks:

:white_check_mark: CRUD completo para clientes; <br />
:white_check_mark: CRUD completo para veterinários; <br />
:white_check_mark: CRUD completo para cachorros; <br />
:white_check_mark: Registrar dados de atendimento, utilizando informações das Entidades acima citadas; <br />
:white_check_mark: Autenticação de usuários e autorização por token com Spring Security, personalizando os acessos aos endpoints; <br />
:white_check_mark: Ao executar o programa, o banco de dados é populado. <br />
:white_check_mark: Foi configurado Swagger juntamente com docs para controllers e segurança para permitir autorização por token para que se possa fazer requisições por ele; <br />
:white_check_mark: O cadastro de Endereço e de Novo Cliente consome uma API externa de CEP. 

## Instalação :rocket:
Para abrir o projeto basta clonar o repositório e:

- Importar o projeto, preferencialmente na IDE Eclipse;
- Fazer atualizações das dependências do Maven (Alt+F5 no Eclipse);
- Possuir acesso à internet para atualização de dependências;
- Necessário possuir o banco de dados MySQL Server;
- No arquivo src/main/resources/application.properties é possível configurar as informações do Banco de Dados

## Endpoints: :rocket:

- Autenticação: 
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | POST   | http://localhost:8080/v1/auth              | PÚBLICO                 |

- Atendimentos:
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | GET    | http://localhost:8080/v1/atendimentos      | ADMIN                   |
    | POST   | http://localhost:8080/v1/atendimentos      | ADMIN                   |
    | GET    | http://localhost:8080/v1/atendimentos/{id} | ADMIN                   |
    | PUT    | http://localhost:8080/v1/atendimentos/{id} | ADMIN                   |

- Cachorros:
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | POST   | http://localhost:8080/v1/cachorros         | ADMIN                   |
    | GET    | http://localhost:8080/v1/cachorros/{id}    | ADMIN                   |
    | PUT    | http://localhost:8080/v1/cachorros/{id}    | ADMIN                   |
    | DELETE | http://localhost:8080/v1/cachorros/{id}    | ADMIN                   |
    | GET    | http://localhost:8080/v1/cachorros/page    | USUARIO, ADMIN          |

- Clientes:
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | POST   | http://localhost:8080/v1/clientes/         | ADMIN                   |
    | GET    | http://localhost:8080/v1/clientes/{id}     | ADMIN                   |
    | PUT    | http://localhost:8080/v1/clientes/{id}     | ADMIN                   |
    | DELETE | http://localhost:8080/v1/clientes/{id}     | ADMIN                   |
    | GET    | http://localhost:8080/v1/clientes/page     | ADMIN                   |

- Endereços:
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | POST   | http://localhost:8080/v1/enderecos         | ADMIN                   |
    | PUT    | http://localhost:8080/v1/enderecos/{id}    | ADMIN                   |
    | DELETE | http://localhost:8080/v1/enderecos/{id}    | ADMIN                   |

- Veterinários:
    | Método | URL                                        | Perfil(s) Autorizado(s) | 
    | ------ | ---                                        | ----------------------- |
    | POST   | http://localhost:8080/v1/vets              | ADMIN                   |
    | GET    | http://localhost:8080/v1/vets/{id}         | ADMIN                   |
    | PUT    | http://localhost:8080/v1/vets/{id}         | ADMIN                   |
    | DELETE | http://localhost:8080/v1/vets/{id}         | ADMIN                   |
    | GET    | http://localhost:8080/v1/vets/page         | USUARIO, ADMIN          |

A documentação completa dos Endpoints estará disponível através do Swagger acessível pelo link: http://localhost:8080/swagger-ui.html  
  
É possível utilizar a Coleção do Postman, basta importar o arquivo [api-ohMyDog.postman_collection.json] que está na pasta docs.  
  
**IMPORTANTE:** Como é uma aplicação que possui checagem de permissão de acesso, é necessário que se utilize token para as requisições através do Endpoint descrito na tabela de Autenticação.  

### Autenticando e utilizando o token no Swagger :rocket:
Para fazer a autenticação e utilizar o token no Swagger siga os passos:  
1- Na página do Swagger ( http://localhost:8080/swagger-ui.html ) procure por 'autenticacao-controller', depois clique em 'v1/auth' e 'Try it out';

Utilize o email: admin@gft.com
e senha: Gft@1234

Clique em executar, copie o token que será disponibilizado abaixo e vá até o botão Authorize. No campo "Value" coloque a palavra Bearer e o token copiado.

### Cadastrando endereço: :rocket:
A API Oh My Dog possui conexão com uma API externa (https://viacep.com.br/). Para cadastrar um endereço, basta informar um CEP válido e o número do local.

### Cadastrando cliente ou veterinário: :rocket:

A API possui validação de CPF e CNPJ. Para cadastrar o cliente é necessário utilizar um CPF válido, você pode gerar um através do site https://www.4devs.com.br/gerador_de_cpf <br />
O cadastro do veterinário possui a opção da escolha do tipo de documento, para cadastrar um CPF você pode gerar um através do link acima, e no caso do CNPJ, pode ser gerado
no site https://www.4devs.com.br/gerador_de_cnpj

:kissing_heart: :dog:

Desenvolvido com :blue_heart: por Marina Dondé