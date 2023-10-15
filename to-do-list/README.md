# To Do List - Aplicação Back End

Este é o repositório do projeto To Do List, uma aplicação back end que permite a gestão de tarefas de usuários, com autenticação segura e geração de IDs usando UUID. As senhas dos usuários são armazenadas de forma segura, utilizando hash. Você pode encontrar a documentação completa da API no [Postman](https://documenter.getpostman.com/view/26000888/2s9YR6ZtE7).

## Bibliotecas Utilizadas

O projeto To Do List foi desenvolvido em Java usando o framework Spring Boot e as seguintes bibliotecas:

- **spring-boot-devtools**: Uma ferramenta de desenvolvimento para melhorar a produtividade durante o desenvolvimento.
  
- **spring-boot-starter-actuator**: Fornece recursos de monitoramento e gerenciamento da aplicação.
  
- **spring-boot-starter-web**: Um starter que inclui as dependências necessárias para criar aplicativos web com Spring Boot.
  
- **spring-boot-starter-test**: Biblioteca para testes unitários e de integração em projetos Spring Boot.
  
- **spring-boot-starter-data-jpa**: Starter para integração com o JPA (Java Persistence API) para acesso a dados.
  
- **h2**: Banco de dados H2 em memória, utilizado no ambiente de desenvolvimento.
  
- **bcrypt**: Uma biblioteca que fornece funções para realizar o hash de senhas de maneira segura.

- **lombok**: Uma biblioteca que ajuda a reduzir a verbosidade do código Java, gerando automaticamente getters, setters, construtores, e muito mais.

Essas bibliotecas foram adicionadas ao projeto usando o sistema de gerenciamento de dependências Maven, conforme especificado no arquivo `pom.xml`. Isso permite que o projeto tenha acesso às funcionalidades oferecidas por essas bibliotecas e frameworks para desenvolver o To Do List de forma mais eficiente.

Lembre-se de verificar o arquivo `pom.xml` no repositório do projeto para obter a versão exata de cada biblioteca utilizada.

Para mais detalhes sobre como utilizar o projeto e sua API, consulte a [documentação no Postman](https://documenter.getpostman.com/view/26000888/2s9YR6ZtE7).