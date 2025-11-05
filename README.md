# 🔥 HC Auxilia - API (Driven Soft)

Bem-vindo(a) à solução back-end do projeto **HC Auxilia**, desenvolvido pela equipe **Driven Soft**!
Esta API foi criada para apoiar o sistema de inclusão digital na saúde, ajudando pacientes e cuidadores a se cadastrarem, enviarem feedback e receberem notificações — contribuindo para a **redução da taxa de absenteísmo em teleconsultas**.

---

## 🎯 Desafio

Criar uma solução capaz de melhorar a experiência do paciente em plataforma de saúde digital, **sem alterar** o sistema oficial do hospital.

✅ Resultados esperados:

- Facilitar comunicação e suporte
- Permitir envio de feedback
- Enviar lembretes e notificações
- **Diminuir a taxa de absenteísmo em consultas remotas**

---

## 📌 Objetivo

A **HC Auxilia API**, desenvolvida com **Quarkus + Java + Maven + Oracle JDBC**, fornece uma camada intermediária simples e eficiente para registrar exames, coletar feedback e gerenciar inscritos para notificações.

Tudo validado e armazenado no banco Oracle, garantindo dados consistentes para futuramente gerar acompanhamento e relatórios.

---

## 🗂 Estrutura Principal

```

src/main/java/br/com/fiap/
├── resource/       # Endpoints REST
│   ├── ExameResource.java
│   ├── FeedbackResource.java
│   └── InscritoNotificacaoResource.java
│
├── business/       # Regras de negócio e validações
├── repository/     # Persistência Oracle (JDBC + SQL)
└── model/          # Entidades e DTOs

```

---

## 🌐 Recursos da API

Todos os endpoints utilizam **RESTEasy (JAX-RS)** com respostas em **JSON (Jackson)**.
Cada controller chama sua camada de validação (`business`) antes de salvar no banco.

### ✅ `ExameResource.java`

> Gerencia o registro e consulta de exames.

📌 Funções principais:
- `POST /exame` → cadastra um novo exame  
- `GET /exame` → lista todos os exames  
- `GET /exame/{id}` → consulta por ID  

✔ Validações aplicadas:
- Nome do exame é obrigatório
- ID/CPF do paciente deve ser válido
- Data não pode ser nula
- Impede envio de campos vazios

Se os dados estiverem corretos → grava no Oracle via JDBC  
Se estiver incorreto → retorna `400` com mensagem JSON

---

### ✅ `FeedbackResource.java`

> Responsável por registrar e listar feedbacks dos usuários.

📌 Funções:
- `POST /feedback` → grava feedback
- `GET /feedback` → lista todos

✔ Validações:
- Nome deve ter tamanho mínimo
- Sugestão não pode ser vazia
- Nível de satisfação deve ser numérico e dentro de faixa válida

Exemplo de retorno de sucesso:
```json
{ 
  "mensagem": "Feedback registrado com sucesso" 
}
```

---

### ✅ `InscritoNotificacaoResource.java`

> Cadastra usuários para receber notificações e lembretes.

📌 Funções:

* `POST /inscrito` → inscreve usuário
* `GET /inscrito` → lista usuários cadastrados

✔ Validações:

* Nome obrigatório
* Telefone ou email precisa ser válido
* Impede cadastros incompletos

Exemplo de POST de sucesso:
```json
{
  "nome": "Maria Andrade",
  "telefone": "11999998888",
  "recebeSms": "S",
  "recebeWhatsapp": "N"
}
```

---

## 🧠 Camada Business

Antes de qualquer gravação no banco:

✔ valida campos obrigatórios
✔ evita dados inconsistentes
✔ retorna erro claro para o cliente
✔ só chama o `repository` se estiver válido

Fluxo simplificado:

1. Resource recebe JSON
2. Business valida
3. Repository executa SQL (JDBC)
4. Resposta JSON é retornada

---

## 🚀 Como rodar localmente

```bash
git clone https://github.com/Driven-Soft/HC-Auxilia-API
cd HC-Auxilia-API
mvn quarkus:dev
```

A API sobe em:

```
http://localhost:8080
```

Configure seu `application.properties`:

```
quarkus.datasource.username=
quarkus.datasource.password=
quarkus.datasource.jdbc.url=
```

---

## 🛠 Tecnologias Utilizadas

| Tecnologia             | Função                                |
| ---------------------- | ------------------------------------- |
| **Quarkus**            | Framework rápido para APIs REST       |
| **Java 17**            | Linguagem principal                   |
| **Maven**              | Gerenciamento de dependências         |
| **RESTEasy + Jackson** | JSON + controle de requests/responses |
| **Oracle JDBC**        | Persistência no banco Oracle          |

---

## 👥 Equipe

* 🧑‍🎨 **Henrique Cunha Torres – RM 565119**
* 👨‍💻 **Felipe Bezerra Beatriz – RM 564723**
* 👨‍🔬 **Max Hayashi Batista – RM 563717**

---

## 🌐 Repositório da API

🔗 [HC-Auxilia-API](https://github.com/Driven-Soft/HC-Auxilia-API)

## 🌐 Repositório do Front-End

🔗 [HC-Auxilia-React](https://github.com/Driven-Soft/HC-Auxilia-React)

---

✨ Obrigado por conhecer nossa solução!
