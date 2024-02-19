# PBL- sig.Biblioteca
 Sistema de gerenciamento de biblioteca (EXA805 - 2023.2 - UEFS)

### Conhecimentos adquiridos

- Classes e Objetos
- Interface
- Classe Abstrata
- Herança
- Padrão de Projeto DAO (Data Access Object)
- Padrão de Projeto Singleton
- Diagrama de Classes

### Diagrama de Classes

![Diagrama de Classe](Diagramas/Sistema_biblioteca.png)

### Diagrama de casos de uso

![Diagrama de casos de uso](Diagramas/Diagrama_casos.jpg)

### Requisitos do sistema

1. **Registro de Livros:** O sistema deve permitir o registro de novos livros no sistema, incluindo informações como título, autor, editora, ISBN, ano de publicação e categoria.
2. **Pesquisa de Livros:** Os usuários devem ser capazes de pesquisar livros por título, autor, ISBN ou categoria, a fim de encontrar informações sobre disponibilidade, localização e outras informações relevantes.
3. **Empréstimo e Devolução:** O sistema deve permitir o registro de empréstimos de livros para os usuários da biblioteca. Isso inclui a possibilidade de registrar a data de empréstimo, a data de devolução esperada e a identificação do usuário que realizou o empréstimo. Além disso, o sistema deve permitir o registro da devolução dos livros e a atualização da disponibilidade do livro.
4. **Reserva de Livros:** Os usuários devem ter a opção de reservar livros que estejam emprestados por outros usuários. O sistema deve registrar a reserva por ordem de solicitação.
5. **Renovação de Empréstimos:** O sistema deve permitir a renovação dos empréstimos de livros, desde que não haja outras reservas para o mesmo livro e o limite de renovações não tenha sido atingido.
6. **Controle de Usuários:** O sistema deve permitir o cadastro de novos usuários, com informações como nome, endereço, telefone e número de identificação. Além disso, deve ser possível bloquear uma conta, não permitindo que o usuário faça empréstimos e renovação.
7. **Relatórios e Estatísticas:** O sistema deve ser capaz de gerar relatórios e estatísticas, como número de livros emprestados, atrasados e reservados; histórico de empréstimos de um usuário específico; e livros mais populares.
8. **Gerenciamento de Multas:** O sistema deve ser capaz de calcular e registrar multas por atraso na devolução de livros. O usuário deverá ser multado com o dobro de dias em atraso.
9. **Gerenciamento de Acervo:** O sistema deve permitir o gerenciamento do acervo da biblioteca, incluindo adição, remoção e atualização de informações sobre os livros, além do controle de estoque.
10. **Controle de operadores do sistema:** O sistema deve permitir o cadastro de novos operadores, com informações como nome, número de identificação, cargo e senha de acesso. Os cargos podem ser do tipo Administrador ou Bibliotecário. O Bibliotecário só terá acesso às funcionalidades #1, #2 e #3.

### Estrutura de diretórios para desenvolvimento
- [model](src/main/java/com/example/pbl/model): modelos de dados do sistema
- [dao](src/main/java/com/example/pbl/dao): implementação do padrão DAO (Data Access Object)
- [controller](src/main/java/com/example/pbl/controller): Implementação dos controladores para a manipulação das telas.
- [view](src/main/resources): Telas criadas para o sistema.
## Desenvolvimento

### Instalação

Faça o download do repositório

```
git clone https://github.com/fabiomirs/PBL-algoritmos-e-prog-II
```

E abra o projeto no IntelliJ IDEA e aguarde o download das dependências.

## Executando os testes de unidade

Para executar os testes de unidade foi criado um diretório com os testes armazenados, sendo possível executar todos os testes a partir deste. Clicando com o botão direito do mouse em qualquer diretório, já existe a opção "Run all tests".
- [Diretório criado](src/testes)


## Construído com

* [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) - IDE utilizado para codificação
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [JavaFX](https://openjfx.io/) - Framework para o front-end
* [Scene Builder](https://gluonhq.com/products/scene-builder/) - Para construção das telas
