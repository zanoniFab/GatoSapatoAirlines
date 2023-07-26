# M3P2-BackEnd-Squad2

# Gato & Sapato Airlines 

## Introdução

A empresa Gato & Sapato Airlines está expandindo sua atuação e precisa estruturar melhor o sistema para atender a demanda! 
Projeto final do DEVinHouse, curso de qualificação profissional de desenvolvimento fullStack.

## Tecnologias utilizadas

- Java com Spring Boot
- Banco de Dados SQL (embarcado - H2)

### Dependências e versões

- Java 17
- JDK 11+
- Spring boot 3.1.1
- Maven 4.0.0
- Lombok
- Model mapper 3.0.0
- Banco de dados h2

## Funcionalidades

A aplicação conta com as seguintes funcionalidades:

### Consulta de Passageiros: 

Permite consultar todos os passageiros registrados na aplicação.

```
GET: http://localhost:8080/api/passageiros
```
**Resultado:**
```
[
  {
    "cpf":10101010101,
    "nome":"Leandro de Medeiros",
    "dataNascimento":"01/02/1992",
    "classificacao":"OURO",
    "milhas":180,
    "eticket":"24034c2a-04ad-4698-8e9b-7b559d3e4317",
    "assento":"3C"
  },...
]
```

**Desenvolvido por:**

- Fabiane Zanoni

### Consulta de Passageiros Pendentes de confirmação: 

Permite consultar todos os passageiros que ainda não realizaram o check-in.

```
GET: http://localhost:8080/api/passageiros/pendentes
```
**Resultado:**
```
[
  {
     "nome":"James Tiberius Kirk",
     "cpf":11111111111
  },...
]
```

**Desenvolvido por:**

- Fabiane Zanoni

### Consulta de assentos:

Permite consultar todos os assentos e sua disponibilidade.

```
GET: http://localhost:8080/api/assentos
```
**Resultado:**
```
[
  {
    "assento":"1A",
    "ocupado":true
  },
  {
    "assento":"1B",
    "ocupado":false
  },...
]
```

**Desenvolvido por:**

- Fabiane Zanoni

### Realização de Confirmação (check-in): 

Permite que os passageiros confirmem o check-in seguindo determinadas regras.

```
POST: http://localhost:8080/api/passageiros/confirmacao
```
**Resultado:**
```
{
  "malasDespachadas": true,
  "assento": "2D",
  "cpf": 12121212121
}
```

**Desenvolvido por:**

- Fabiane Zanoni

### Consulta de Confirmação (por e-ticket): 

Permite consultar a confirmação de check-in por e-ticket.

```
GET: http://localhost:8080/api/passageiros/confirmacao/{eticket}
```
**Resultado:**
```
{
  "eticket": "31b51505-3cf4-48ee-ad26-f4eccd60947e",
  "cpf": 12121212121,
  "nome": "Fabiane Zanoni",
  "assento": "2D",
  "malasDespachadas": true,
  "dataHoraConfirmacao": "2023-07-08T17:10:00.778201",
  "classificacao": "PRATA"
}
```

### Tratamento de erros:

Realização de tratamento de erros.

**Desenvolvido por:**

- Leandro de Medeiros Zepechouka
- Fabiane Zanoni

### Carga inicial:

Realização da carga inicial de dados dos passegeiros.

**Desenvolvido por:**

- Leandro de Medeiros Zepechouka
- Fabiane Zanoni

### Criação de modelos:

Criação dos modelos de confirmação, passageiro e enum de classificação.

**Desenvolvido por:**

- Leandro de Medeiros Zepechouka
- Fabiane Zanoni

### Testes unitários:

Realização de testes unitários no controller e service com Junit.

**Desenvolvido por:**

- Leandro de Medeiros Zepechouka
- Fabiane Zanoni

### Code review:

Realização de code review.

**Realizado por:**

- Silvana Penkal Santos
- Fabiane Zanoni

### Readme:

Documentação do projeto.

- Silvana Penkal Santos
- Leandro de Medeiros Zepechouka
- Fabiane Zanoni
- Luciano De Carli Rocha

## Equipe de projeto

Link para o github dos integrantes da equipe.

- [Arthur Fraga Krelling](https://github.com/ArthurKrelling)
- [Fabiane Rodrigues Zanoni](https://github.com/zanoniFab)
- [Leandro de Medeiros Zepechouka](https://github.com/lzpck)
- [Luciano De Carli Rocha](https://github.com/LucianoCarliRocha)
- [Silvana Penkal Santos](https://github.com/silvanapenkal)





