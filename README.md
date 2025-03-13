# LookBook - Gestione di un Negozio di Abbigliamento

![LookBook Logo](/LookBook.webp)

LookBook è un'applicazione Java che simula una parte di gestione di un negozio di abbigliamento di seconda mano.

## 📌 Funzionalità

- Visualizzazione dell'inventario dei capi di abbigliamento
- Acquisto e restituzione di capi
- Aggiunta di nuovi utenti
- Esportazione della lista dei capi disponibili in formato CSV
- Caricamento dati da file CSV

---

## 🛠️ Configurazione e Installazione

### 1️⃣ Requisiti

- **Java 17+**
- **Maven** (per la gestione delle dipendenze e la compilazione del progetto)

### 2️⃣ Clonazione del Repository

```sh
# Clona il repository GitHub
git clone https://github.com/tuo-username/lookbook.git
cd lookbook
```

### 3️⃣ Compilazione del Progetto

Per compilare il progetto ed eseguire i test:

```sh
mvn clean install
```

Se vuoi generare il file JAR eseguibile:

```sh
mvn package
```

Il file verrà creato nella cartella `target/` con il nome:

```sh
target/lookbook-1.0-SNAPSHOT.jar
```

### 4️⃣ Esecuzione del Programma

Dopo la compilazione, puoi eseguire il programma con il comando:

```sh
java -jar target/lookbook-1.0-SNAPSHOT.jar
```
---
🛠️ Comandi Principali

Ecco i principali comandi disponibili nell'applicazione:

| Comando |Descrizione            |
|---------|-----------------------|
| 1       |Visualizza tutti i capi|                        
| 2       |Comprare un capo       |
| 3       |Restituire un capo     |
| 4       |Aggiungere un utente   |
| 5       |Esportare i capi(.csv) |
| 0       |uscire dall' App       |
---

## 📂 Struttura del Progetto

```
lookbook/
├── src/
│   ├── main/
│      ├── java/com/lookbook/
│      │   ├── App.java       # Classe principale
│      │   ├── dao/           # Gestione dati
│      │   ├── models/        # Modelli di dati
│      │   ├── utils/         # Utility
│      ├── resources/
│          ├── capi.csv       # Dati dei capi di abbigliamento
|
├── pom.xml                   # Configurazione Maven
├── README.md                 # Documentazione
```

---

## 🛠️ Contributi

Se vuoi contribuire al progetto:
1. Fai un **fork** del repository
2. Crea un nuovo branch con la tua feature (`git checkout -b feature/nome-feature`)
3. Effettua le modifiche e committale (`git commit -m 'Aggiunta nuova feature'`)
4. Esegui il push sul tuo repository (`git push origin feature/nome-feature`)
5. Apri una **Pull Request**!

---

## 🐛 Segnalazione Bug

Se trovi un bug o vuoi suggerire una funzionalità, apri un **Issue** su GitHub descrivendo il problema in dettaglio.

---

## 📬 Contatti

- **Email:** franck.devw@gmail.com

---

🚀 **Grazie per aver utilizzato LookBook!**

