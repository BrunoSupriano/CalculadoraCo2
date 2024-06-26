
# Encurtador de links e gerador de QR Code

---

Este projeto consiste em um serviço simples de encurtamento de URLs desenvolvido como parte de um projeto de backend para um trabalho de faculdade. Ele permite aos usuários encurtarem URLs longas e criar QR Codes para facilitar o compartilhamento.

---

### Funcionalidades:

* Encurtamento de URLs longas em URLs curtas.
* Geração de QR Codes para as URLs encurtadas.
* Personalização de URLs curtas.

---

### Tecnologias Utilizadas:

* Java
* Spring Boot
* Maven
---

### APIs Utilizadas:

* https://ulvis.net/developer.html
* https://goqr.me/api/

---
## Endpoints da API

### Gerar URL e QR Code 

`POST /encurtar`

Este endpoint recebe como entrada uma URL longa e um nome para a customização da url (opcional). Retorna uma URL encurtada e personalizada e um link para a imagem do QR Code.

**Exemplo de solicitação:**
```json
{
  "urlOriginal": "https://www.example.com/lorem/ipsum",
  "custom": "nomecustomizado"
}
```
        
**Exemplo de resposta:**
```json
{
  "urlOriginal": {
    "id": "nomecustomizado",
    "url": "https://ulvis.net/nomecustomizado",
    "full": "https://www.example.com/lorem/ipsum"
  },
  "qrCodeUrl": "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=https://ulvis.net/nomecustomizado"
}
```
`GET /encurtar`

Este endpoint lista todas as respostas das requisições realizadas na forma de um JSON.

---

