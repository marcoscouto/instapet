# Instapet - Zup Innovation

"A compaixão para com os animais é das mais nobres virtudes da natureza humana." - Charles Darwin

E quem é que não gosta de Pets, não é verdade?

### Fatos

Os animais domésticos, famosos pets, estão cada vez mais presentes na vida das pessoas, a compaixão e conciência 
que temos com eles está evidente nos cuidados do dia a dia e na mudança de visão do ser humano que cada vez menos
trata o animal como um bixo de estimação o tornando um membro da família.

Além desse fato, vamos para o fator econômico, o Brasil tornou-se em 2020 o segundo maior mercado de produtos pet
no mundo. Com cerca de 139,3 milhões de pets, o faturamento total do segmento, que incluí indústria e varejo, 
foi de R$ 35,4 bilhões até o terceiro trimestre de 2019 e está aquecido e em plena expansão.

[Brasil torna-se o segundo maior mercado de produtos pet (Forbes)](https://forbes.com.br/negocios/2020/08/brasil-torna-se-o-segundo-maior-mercado-de-produtos-pet/)

### Produto

Com a visão mudando em referência aos Pets a ideia é que exista uma Rede Social exclusiva para eles, a missão da rede
é dar espaço para registrar ações do dia a dia como recordações e a interação dos perfis. 

O perfil do Pet será independente de seu tutor, onde serão registradas as informações do mesmo, 
bem como dará espaço a registros do dia a dia através de posts e a interação entre perfis.
Essa interação será através de ações como seguir um perfil de outro Pet, poder visualizar, comentar e interagir com
likes/dislikes em posts.

Desta forma, com o consentimento dos usuários desta rede, é possível fazer o mapeamento de informações trazendo mais
dados para os investidores do setor, que podem não só investir em propagandas para a rede, mas podem também realizar um
estudo de quais produtos podem ser promissores e para quais pet em particular.

### Desafio Zup

O desafio consiste em criar uma rede social do zero com funcionalidades.

A rede escolhida então foi um esboço de Instagram exclusivo para o perfil de Pets visando a aproximação de 
tutores de pets, investidores deste mercado e de certa forma os próprios pets.

### Inicializando a aplicação

Clone este repositório e na pasta raiz do projeto digite o seguinte código:

>mvn spring-boot:run

O projeto está configurado para rodar na porta 8080, sendo assim o acesso dele
será através do link:

> http://localhost:8080

Para gerar o Token de acesso utiliza o endpoint **/login** com as seguintes
credenciais:

>{
>	"email": "rex@pet.com.br",
>   "password": "123456"
>}

Com isto a aplicação retornará no cabeçalho de resposta o Token de autorização. 

É necessário em todas as outras requisições, exceto a de criação de um novo Pet, a inserção no cabeçalho da requisição uma propriedade de chave **Authorization** e como valor o Token com o sufixo **Bearer**.

Este projeto contém uma classe nomeada DatabasePopulate que popula a base de dados com algumas informações para teste.

### Funcionalidades

A rede conta com as seguintes funcionalidades:

* Ações vinculadas a Conta (CRUD)

* Opção de Seguir e Deixar de Seguir outros perfis

* Ações vinculadas a Posts (CRUD vinculado ao perfil) 

* Curtir e Descurtir um Post (Não vinculado ao perfil)

* Ações de comentários de Post (CRUD vinculado ao Post e ao perfil)

### Endpoints

Todos os endpoints estão disponíveis através do Swagger, ao executar a aplicação acesse a documentação através do link:

>http://localhost:8080/swagger-ui.html

Existe um arquivo chamado **Instapet-Postman.json** no projeto pré-configurado para testes que pode ser importado
no programa Postman.

### Tecnologias Utilizadas

* Java 11

* Spring Boot (Spring Data (JPA), Spring Security, Spring Web)

* Banco de dados H2 para testes

* Docker - Imagem do Postgres

* Jwt

* Swagger

* Postman

* Intellij IDE
