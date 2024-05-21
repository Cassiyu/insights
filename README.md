

<div align="center">
    <h2 >DaVinci Insights</h2>
</div>

API do Projeto DaVinci Insights - Processamento de dados para análise de feedbacks de clientes

## Integrantes
 
- RM550341 - Allef Santos (2TDSPV) 
- RM551491 - Cassio Yuji Hirassike Sakai 
- RM97836  - Debora Damasso Lopes 
- RM550323 - Paulo Barbosa Neto 
- RM552314 - Yasmin Araujo Santos Lopes

## Competência 

- Allef - Java Advanced

- Cassio Y.H. Sakai - Mobile Application Development

- Debora D. Lopes - Advanced Business Development With .NET e Disruptive Architectures: IOT, IOB & GENERATIVE IA 

- Paulo B. Neto - Mastering Relational AND Non-Relational DataBase

- Yasmin A. S. Lopes- Compliance, Quality Assurance & Tests e DevOps Tools & Cloud Computing


## Objetivo do Projeto

O Projeto tem como objetivo desenvolver um sistema avançado para análise de feedbacks de clientes. A proposta central é permitir que os clientes expressem suas opiniões sobre produtos específicos após a compra e, em seguida, integrar esses feedbacks com outros já existentes. Dessa forma, o sistema visa extrair insights valiosos para ajudar as empresas a compreenderem as razões subjacentes às avaliações, sejam elas positivas, negativas, neutras ou ausentes. Essa abordagem proporcionará uma compreensão mais profunda do que os clientes pensam e sentem sobre os produtos, possibilitando que as empresas ajam de forma mais informada e eficaz para atender às necessidades e expectativas do mercado.

O Público-alvo do Projeto DaVinci Insights visa atender empresas que buscam aprimorar seu relacionamento com os clientes, compreendendo suas percepções sobre produtos específicos. Ao entender essas opiniões, as empresas podem identificar e resolver os problemas que enfrentam no mercado, permitindo uma abordagem mais focada e eficaz para atender às necessidades do cliente.

## Pitch Projeto
<p align="center"> <a href="https://www.canva.com/design/DAGCM51NRec/lrD6jDrLBVs0VjEa9xFJGw/view?utm_content=DAGCM51NRec&utm_campaign=designshare&utm_medium=link&utm_source=recording_view">Vídeo PITCH de apresentação do projeto</a></p>

## Repositório:

```bash
git clone https://github.com/Cassiyu/insights
```

## Atualizações

- Controllers Refeitos.

- Relacionamentos Adicionados.

- Ligação com o Banco de Dados.


## Requisitos

- [ ] CRUD Cliente
- [ ] CRUD Opnião
- [ ] CRUD Produto 


## Documentação da API

### Endpoints

- [Listar Clientes](#listar-clientes)
- [Listar Opniões](#listar-opniões)
- [Listar Produtos](#listar-produtos)
---
- [Cadastrar Cliente](#cadastrar-cliente)
- [Cadastrar Opnião](#cadastrar-opnião)
- [Cadastrar Produto](#cadastrar-produto)
---
- [Detalhar Cliente](#detalhar-cliente)
- [Detalhar Opnião](#detalhar-opnião)
- [Detalhar Produto](#detalhar-produto)

### Listar Clientes

`GET` /cliente : Retorna os dados do cliente

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "cidade": "Manaus",
        "idade": 30,
        "sexo": "M"
    }
]
```
#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Cliente não encontrado

----------------------------------------

### Listar Opniões

`GET`/opiniao : Retorna o comentario do cliente sobre o Produto

#### Exemplo de Resposta

```js
[
    {
        "id": 53,
        "comentario": "Excelente tecido!"
    }
]
```
#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Opnião não encontrado

----------------------------------------

### Listar Produtos

`GET`/Produto : Informações do produto 

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Camisa",
        "valor": 29.90
    }
]

```
#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Produto não encontrado

----------------------------------------

### Cadastrar Cliente

`POST` /cliente

Cadastre um cliente com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|cidade|String|✅| Cidade do Cliente
|idade|Integer|✅| Idade do Cliente
|sexo|String|❌| Sexo do Cliente

---

#### Exemplo de Requisição
```js
// Post /cliente
    {
        "cidade": "Manaus",
        "idade": 30,
        "sexo": "M"
    }
```

#### Exemplo de Resposta
```js
{
    "id": 1,
    "cidade": "Manaus",
    "idade": 30,
    "sexo": "M"
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Cliente cadastrado com sucesso
|400| Validação falhou. Verifique o corpo da requisição

----------------------------------------

### Cadastrar Opnião

`POST` /opiniao

Cadastre uma opinião com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|comentario|String|✅| Opinião do Cliente

---

#### Exemplo de Requisição
```js
// Post /opiniao
    {
        "comentario": "Excelente tecido!"
    }
```

#### Exemplo de Resposta
```js
{
    "id": 1,
    "comentario": "Excelente tecido!"
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Opnião cadastrada com sucesso
|400| Validação falhou. Verifique o corpo da requisição

----------------------------------------

### Cadastrar Produto

`POST` /produto

Cadastre um produto com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|String|✅| Nome do produto
|valor|BigDecimal|✅| Valor do produto

---

#### Exemplo de Requisição
```js
// Post /produto
    {
        "nome": "Camisa",
        "valor": 29.90
    }
```

#### Exemplo de Resposta
```js
{
    "id": 1,
    "nome": "Camisa",
    "valor": 29.90
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Produto cadastrado com sucesso
|400| Validação falhou. Verifique o corpo da requisição

----------------------------------------

### Detalhar Cliente

`GET` /cliente/`{id}`

Retorna os detalhes do cliente com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /cliente/1
{
    "id": 1,
    "cidade": "Manaus",
    "idade": 30,
    "sexo": "M"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Cliente retornado com sucesso
|404| Não existe cliente com o `id` informado

---

### Detalhar Opnião

`GET` /opiniao/`{id}`

Retorna os detalhes da opinião com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /opiniao/53
{
    "id": 53,
    "comentario": "Excelente tecido!"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Opnião retornada com sucesso
|404| Não existe opnião com o `id` informado

---

### Detalhar Produto

`GET` /produto/`{id}`

Retorna os detalhes do produto com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /produto/1
{
    "id": 1,
    "nome": "Camisa",
    "valor": 29.90
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Produto retornado com sucesso
|404| Não existe produto com o `id` informado

---