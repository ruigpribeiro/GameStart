# GameStart - Video Game Sales Management System 🎮

This project is an application developed for the video game store **"GameStart"**, aimed at analyzing, researching, and managing information related to their sales, clients, and games. The system is implemented in **Java** and offers different functionalities for administrators and clients.

## Features

### ADMIN Menu 🛠️
1. **File Query:** View the contents of sales, client, and category files.
2. **Total Sales:** Display the total number of sales and their aggregate value.
3. **Total Profit:** Calculate and display the total profit based on margin percentages.
4. **Client Search:** Retrieve detailed information about a client by ID.
5. **Most Expensive Game:** Identify the most expensive game and the clients who bought it.
6. **Top Clients:** Show the clients who spent the most money and the games they purchased.
7. **Best Category:** Discover which category generated the most profit.
8. **Sales Search:** Search for all clients who bought a specific game.
9. **Top 5 Games:** List the top 5 games that generated the most profit.
10. **Bottom 5 Games:** List the bottom 5 games that generated the least profit.

### CLIENT Menu 👤
1. **New Registration:** Register a new client.
2. **Find Parking:** Check available parking spots, which are multiples of 5 and triangular numbers up to 121.
3. **Print Catalog:** List all game titles without duplicates.
4. **Print Graphic Catalogs:** Print the list of games with specific graphic art.
5. **Print Publisher Catalog:** List all categories and games of a specific publisher.
6. **Print Category Catalog:** List all publishers and games for a specific category.
7. **Print Most Recent Game:** Show the game that appeared for the first time with the fewest sales.

## Data Structure 📂

The system uses four CSV files to store information:

1. **Sales (GameStart_Vendas.csv):**
    - `idVenda`: Unique identifier for the sale
    - `idCliente`: Client identifier
    - `editora`: Publisher of the game
    - `categoria`: Category of the game
    - `jogo`: Title of the game
    - `valor`: Value of the game

2. **Clients (GameStart_Clientes.csv):**
    - `idCliente`: Unique identifier for the client
    - `nome`: Name of the client
    - `telemovel`: Phone contact
    - `email`: Email address

3. **Commissions (GameStart_Categorias.csv):**
    - `nomeCategoria`: Name of the game category
    - `percentagemMargem`: Profit percentage

4. **Admin Credentials (GameStart_Admins.csv):**
    - Username
    - Password

## Installation 🚀

Clone the repository:

```bash
git clone https://github.com/ruigpribeiro/GameStart.git
cd GameStart
```

Thank you for visiting the GameStart project repository! 🚀
