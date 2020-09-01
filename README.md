# loanSpring

API criada para solicitação e acompanhamento de emprestimos de uma empresa ficticia.

## Tecnologias Utilizadas

  - Java
  - Spring Boot
  - Firestore ( Firebase)
  
  ## Rotas User
 
 ### POST /addUser
 Body da requisição precisa de um json com os seguintes atributos:
  - name
  - email
  - password
  - age
  - cpf
  
  Retorna a data que o usuário foi adicionado
  
  ### GET /getUser
  Passar como parâmetro o CPF do usuario
  
  Retorna o json com os dados do usuário
  
  ### GET /getAll
  Retorna todos os usuários
  
  ### PUT /updateUser
 Body da requisição precisa de um json com os seguintes atributos:
  - name
  - email
  - password
  - age
  - cpf
  
  Retorna a data que o usuário foi alterado
  
  ### DELETE /deleteUser
  Passar como parâmetro o CPF do usuario
  
  Retorna uma String
  
  ### POST /loginUser
 Body da requisição precisa de um json com os seguintes atributos:
  - email
  - password
  Retorna o json com os dados do usuário

  ## Rotas Loan
  
 ### POST /addLoan
 Body da requisição precisa de um json com os seguintes atributos:
  - cpfUser
  - status
  - value
  - hashID
  
  Retorna a data que o emprestimo foi adicionado
  
  ### GET /getLoan
  Passar como parâmetro o hashID do emprestimo
  
  Retorna o json com os dados do emprestimo
  
  ### GET /getAllLoans
  Passar como parâmetro o cpfUser do usuário
  
  Retorna todos os emprestimos do usuário
  
  ### PUT /updateLoan
 Body da requisição precisa de um json com os seguintes atributos:
  - cpfUser
  - status
  - value
  - hashID
  
  Retorna a data que o emprestimo foi alterado
  
  ### DELETE /deleteLoan
  Passar como parâmetro o hashID do emprestimo
  
  Retorna uma String
