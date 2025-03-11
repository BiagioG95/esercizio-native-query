Ecco la traccia per un esercizio CRUD in Spring Boot con controller, service, repository e entity, includendo query personalizzate e native:

Entity (Prodotto)

Creare una classe Prodotto con campi: id, nome, descrizione, prezzo, categoria, quantitàDisponibile, dataCreazione
Annotare con @Entity, @Id, @GeneratedValue, @Column ecc.

Repository (ProdottoRepository)

Interfaccia che estende JpaRepository
Aggiungere metodi con @Query per query personalizzate
Implementare @Query(nativeQuery = true) per query SQL native
Esempi: trovare prodotti per categoria, trovare prodotti con prezzo tra due valori, ecc.


Service (ProdottoService)

Implementare metodi CRUD standard (findAll, findById, save, delete)
Aggiungere metodi di business logic che utilizzano le query personalizzate del repository
Aggiungere validazione e gestione degli errori

Controller (ProdottoController)

REST controller con endpoint per operazioni CRUD
Implementare GET, POST, PUT, DELETE
Gestire parametri di query, path variables e request body
Implementare paginazione e ordinamento

Configurazioni aggiuntive

Configurare application.yaml per il database
Implementare gestione delle eccezioni
Aggiungere test unitari e di integrazione



L'esercizio dovrebbe includere query personalizzate che filtrino prodotti per vari criteri e almeno una query nativa SQL che esegua operazioni più complesse non facilmente esprimibili con JPA.

Punti per Query personalizzate

Filtraggio per categoria
Crea un metodo nel repository che trovi tutti i prodotti di una categoria specifica

Ricerca per nome
Implementa un metodo che cerca prodotti il cui nome contiene una parola chiave

Filtraggio per prezzo
Crea un metodo per trovare prodotti con prezzo minore di un valore dato

Ordinamento semplice
Implementa un metodo che restituisce prodotti ordinati per prezzo (crescente o decrescente)

Filtraggio combinato base
Crea un metodo che trova prodotti di una categoria con prezzo sotto una soglia



Punti per Query native (SQL) - Livello Junior:

Conteggio prodotti per categoria
Implementa una query nativa che conta quanti prodotti ci sono per categoria

Prodotti con maggiore disponibilità
Crea una query nativa che trova i 5 prodotti con la maggiore quantità disponibile

Prodotti recenti
Implementa una query nativa che trova i prodotti aggiunti nell'ultima settimana

Ricerca semplice
Crea una query nativa che cerca prodotti il cui nome o descrizione contiene una parola chiave

Prezzo medio per categoria
Implementa una query nativa che calcola il prezzo medio dei prodotti per ogni categoria