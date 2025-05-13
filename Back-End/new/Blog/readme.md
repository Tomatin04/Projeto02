# Como Implementar Blacklist de Tokens JWT com Redis no Spring Boot

## 1. Por que usar Redis para Blacklist de Tokens?

O Redis é um banco de dados em memória, muito rápido, ideal para armazenar dados temporários como tokens revogados (blacklist). Ele permite que você armazene tokens com tempo de expiração, removendo-os automaticamente quando expirarem.

**Vantagens:**
- Muito rápido (tudo em memória)
- Fácil de usar
- Remove tokens expirados automaticamente
- Funciona bem em aplicações distribuídas

---

## 2. Instalando o Redis no Windows

### Opção 1: Usando Docker (Recomendado)

1. **Instale o Docker Desktop:**  
   Baixe em [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)  
   Instale e abra o Docker Desktop.

2. **Rode o Redis com Docker:**
   ```
   docker run --name redis-blog -p 6379:6379 -d redis
   ```

3. **Verifique se está rodando:**
   ```
   docker ps
   ```

### Opção 2: Redis nativo para Windows

- Baixe em: https://github.com/tporadowski/redis/releases
- Extraia e execute `redis-server.exe`.

---

## 3. Testando o Redis

1. **Abra o terminal do Redis:**
   ```
   redis-cli
   ```

2. **Teste comandos:**
   ```
   set minha-chave valor
   get minha-chave
   ```

---

## 4. Integrando Redis ao Spring Boot

### a) Adicione a dependência no `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### b) Configure o Redis no `application.properties`:

```properties
spring.redis.host=localhost
spring.redis.port=6379
```

### c) Crie o serviço de blacklist:

```java
// filepath: src/main/java/com/Blog/infra/securety/TokenBlacklistService.java
package com.Blog.infra.securety;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PREFIX = "blacklist:";

    public void blacklistToken(String token, long secondsToExpire) {
        redisTemplate.opsForValue().set(PREFIX + token, "blacklisted", Duration.ofSeconds(secondsToExpire));
    }

    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(PREFIX + token);
    }
}
```

### d) Adapte seu `TokenService`:

```java
// filepath: src/main/java/com/Blog/infra/securety/TokenService.java
// ...existing code...
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;

public class TokenService {

    @Value("${api.securety.token.secret}")
    private String secret;

    @Autowired
    private TokenBlacklistService blacklistService;

    // ...existing code...

    public void blacklistToken(String tokenJWT) {
        var alg = Algorithm.HMAC256(secret);
        var decodedJWT = JWT.require(alg).build().verify(tokenJWT);
        long secondsToExpire = decodedJWT.getExpiresAt().toInstant().getEpochSecond() - Instant.now().getEpochSecond();
        blacklistService.blacklistToken(tokenJWT, secondsToExpire);
    }

    public boolean isTokenBlacklisted(String tokenJWT) {
        return blacklistService.isTokenBlacklisted(tokenJWT);
    }

    // ...existing code...
}
```

### e) Verifique a blacklist ao autenticar:

No filtro de autenticação, adicione:

```java
if (tokenService.isTokenBlacklisted(tokenJWT)) {
    throw new RuntimeException("Token revogado");
}
```

---

## 5. Comandos Úteis do Docker

- **Parar o container Redis:**
  ```
  docker stop redis-blog
  ```
- **Remover o container Redis:**
  ```
  docker rm redis-blog
  ```

---

## 6. Resumo

- Use Redis para armazenar tokens revogados.
- Adicione tokens à blacklist com tempo de expiração igual ao tempo restante do token.
- Sempre verifique a blacklist ao autenticar.
- Redis pode ser rodado facilmente via Docker no Windows.

---

## 7. Dúvidas Frequentes

**O que é Redis?**  
É um banco de dados em memória, rápido, usado para cache, filas, sessões e dados temporários.

**Por que não usar ArrayList ou banco relacional?**  
ArrayList não é persistente e não escala. Banco relacional é mais lento para operações rápidas e temporárias.

**Preciso instalar algo além do Docker?**  
Não, apenas o Docker Desktop e o comando para rodar o Redis.

---

Pronto! Basta salvar este arquivo e converter para PDF quando quiser.
