# Calculadora de Emissões de CO2

---

Este projeto é uma aplicação backend desenvolvida em Java para calcular as emissões de CO2 dos usuários e permitir o registro de plantio de árvores para compensar essas emissões. A aplicação fornece endpoints RESTful para gerenciar usuários, emissões de CO2 e plantio de árvores.

---

### Funcionalidades:

* Cadastro de usuários;
* Registro de emissão de CO2 para o usuário;
* Cálculo do total da emissão mensal/anual cadastrada e previsão de emissão anual;
* Informativo sobre a emissão;
* Registro de árvores plantadas para o usuário;
* Cálculo do total de árvores plantadas e quantidade de CO2 "evitada".

---

### Tecnologias Utilizadas:

* Java
* Spring Boot
* Maven

---
## Endpoints

### Usuário

**Criar Novo Usuário:**

`POST /api/users`

Cria um novo usuário com um nome de usuário especificado.

**Exemplo de solicitação:**
```json
{
  "username": "João"
}
```
**Exibir Usuário:**

`GET /api/users/{id}`

Exibe os detalhes de um usuário específico pelo seu ID.

**Exemplo de resposta:**
```json
{
  "id": 1,
  "username": "João",
  "emissions": []
}
```
**Deletar Usuário:**

`DELETE /api/users/{id}`

Remove um usuário específico pelo seu ID.

----

### Emissão

**Criar/cadastrar Emissão para o Usuário:**

`POST /api/users/{userId}/emissions`

Registra uma nova emissão de CO2 para um usuário específico.

**Exemplo de solicitação:**
```json
{
"activity": "Carro",
"co2": 30,
"month": 12,
"year": 2024
}
```
**Exibir todas as Emissões do Usuário:**

`GET /api/users/{userId}/emissions`

Exibe todas as emissões de CO2 registradas para um usuário específico.

**Exemplo de resposta:**
```json
  {
    "id": 1,
    "activity": "Carro",
    "co2": 30.0,
    "month": 12,
    "year": 2024
  }
```
**Exibir Resumo das Emissões do Usuário:**

`GET /api/users/{userId}/emissions/summary`

Exibe um resumo das emissões de CO2 do usuário, incluindo o total mensal e anual, e uma previsão anual.

**Exemplo de resposta:**
```json
{
"year": 2024,
"monthlyTotals": {
"janeiro": 0.0,
"fevereiro": 0.0,
"março": 0.0,
"abril": 0.0,
"maio": 0.0,
"junho": 0.0,
"julho": 0.0,
"agosto": 0.0,
"setembro": 0.0,
"outubro": 0.0,
"novembro": 0.0,
"dezembro": 30.0
},
"totalAnnualCO2": 30.0,
"totalAnnualCO2Prevision": 360.0
}
```
**Exibir Impacto das Emissões do Usuário:**

`GET /api/users/{userId}/emissions/impact`

Calcula e exibe o impacto das emissões de CO2 do usuário, incluindo a previsão de 10 anos e o número de árvores necessárias para compensar essas emissões.

**Exemplo de resposta:**
```json
{
	"totalCO2": 30.0,
	"tenYearPrevision": 3600.0,
	"treesRequired": 25.2,
	"tenYearPrevisionDescription": "Em 10 anos, a quantidade de CO2 emitida será de 3,60 toneladas. Para compensar essa emissão, será necessário plantar aproximadamente 25,20 árvores.",
	"informativo": "Para cada tonelada de CO2 emitida, são necessárias cerca de 7 árvores para absorver essa quantidade de CO2 ao longo de um ano. Por exemplo, um carro médio emite cerca de 0,12 kg de CO2 por quilômetro rodado. Portanto, para cada 8.333 km dirigidos, uma árvore deve ser plantada para compensar as emissões de gases de efeito estufa."
}
```

**Deletar Emissão do Usuário:**

`DELETE /api/users/{userId}/emissions/{id}`

Remove uma emissão específica de CO2 registrada para um usuário pelo ID da emissão.

----

### Plantio árvores

**Criar/registrar Árvores Plantadas para o Usuário:**

`POST /api/users/{userId}/tree-plantings`

Registra o plantio de árvores para um usuário específico.

**Exemplo de solicitação:**
```json
{
  "numberOfTrees": 1,
  "plantingDate": "2024-12-20"
}
```
**Exibir todas as Árvores Plantadas do Usuário:**

`GET /api/users/{userId}/tree-plantings`

Exibe todos os registros de árvores plantadas por um usuário específico.

**Exemplo de resposta:**
```json
[
	{
		"numberOfTrees": 1,
		"plantingDate": "2024-11-20",
		"userId": 1
	},
	{
		"numberOfTrees": 1,
		"plantingDate": "2024-12-20",
		"userId": 1
	}
]
```
**Exibir Impacto/Benefício das Árvores Plantadas do Usuário:**

`GET /api/users/{userId}/tree-plantings/totals`

Calcula e exibe o impacto/benefício das árvores plantadas pelo usuário, incluindo o total de CO2 evitado.

**Exemplo de resposta:**
```json
{
	"totalTreesPlanted": 2.0,
	"totalCO2Avoided": 30.0,
	"explanation": "Com essa quantidade de árvores plantadas, está sendo absorvido aproximadamente 0,03 toneladas de CO2 por ano."
}
```
**Deletar Registro de Árvore Plantada do Usuário:**

`DELETE /api/users/{userId}/tree-plantings/{id}`

Remove um registro específico de plantio de árvores para um usuário pelo ID do registro.
