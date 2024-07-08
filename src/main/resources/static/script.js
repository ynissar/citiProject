getMarketName();
getStockTable();
document.getElementById('buy-stock').addEventListener('submit', addStock);
document.getElementById('edit-stock').addEventListener('submit', editStock);
document.getElementById('stock-ticker-search').addEventListener('submit', searchStockByTicker);
document.getElementById('sell-stock').addEventListener('submit', deleteStock);

async function getMarketName() {
    try {
        const response = await fetch('http://localhost:8080/stock/market');
        const market  = await response.text();
        document.getElementById('market-name').textContent = "Market: " + market;
    } catch (error) {
        throw new Error("Response from network not okay")
    }

}

async function getStockTable() {
    try {
        const response = await fetch('http://localhost:8080/stock');
        const stocks  = await response.json();
        // console.log(stocks);
        const tableBody = document.getElementById('stock-table').getElementsByTagName('tbody')[0];
        // console.log(tableBody);
        tableBody.innerHTML = '';
        stocks.forEach(stock => {
            const row = tableBody.insertRow();
            row.insertCell(0).textContent = stock.tickerSymbol;
            row.insertCell(1).textContent = stock.companyName;
            row.insertCell(2).textContent = stock.closingPrice;
            row.insertCell(3).textContent = stock.openingPrice;
            row.insertCell(4).textContent = stock.date;
            row.insertCell(5).textContent = stock.industryGroup;
            const actionsCell = row.insertCell(6);

            const deleteButton = document.createElement('button');
            deleteButton.classList.add("secondary");
            deleteButton.textContent = 'Sell';
            deleteButton.addEventListener('click', () => deleteStock(stock.tickerSymbol));
            actionsCell.appendChild(deleteButton);
        });
        document.getElementById('stock-table-body').innerHTML = rows;
    } catch (error) {
        throw new Error("Response from network not okay")
    }
}

async function addStock(event) {
    event.preventDefault(); // Prevents page from reloading on form submission
    console.log("add stock");
    const tickerSymbol = document.getElementById('add-ticker').value;
    const companyName = document.getElementById('add-name').value;
    const closingPrice = document.getElementById('add-closing-price').value;
    const openingPrice = document.getElementById('add-opening-price').value;
    const d = new Date();
    const date = d.toString();
    const industryGroup = document.getElementById('add-industry').value;
    try {
        const response = await fetch(`http://localhost:8080/stock`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({tickerSymbol, companyName, closingPrice, openingPrice, date, industryGroup})
        });
        if (!response.ok) {
            throw new Error('Response from network not ok');
        }
        getStockTable();
        event.target.reset();
    } catch (error) {
        console.error('There was a problem fetching:', error);
    }
}

async function editStock(event) {
    event.preventDefault(); // Prevents page from reloading on form submission
    console.log("edit stock");
    const tickerSymbol = document.getElementById('edit-ticker').value;
    const companyName = document.getElementById('edit-name').value;
    const closingPrice = document.getElementById('edit-closing-price').value;
    const openingPrice = document.getElementById('edit-opening-price').value;
    const d = new Date();
    const date = d.toString();
    const industryGroup = document.getElementById('edit-industry').value;
    
    try {
        const response = await fetch(`/stock/index/${tickerSymbol}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ tickerSymbol, companyName, closingPrice, openingPrice, date, industryGroup})
        });
        if (!response.ok) {
            throw new Error('Response from network not ok');
        }
        getStockTable();
        event.target.reset();
    } catch (error) {
        console.error('There was a problem fetching:', error);
    }
}

async function searchStockByTicker(event) {
    event.preventDefault(); // Prevents page from reloading on form submission
    console.log("search stock");

    try {
        const searchTerm = document.getElementById('ticker-input').value.toUpperCase();
        console.log(searchTerm);
        
        const response = await fetch(`http://localhost:8080/stock/index/${searchTerm}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        
        const stock  = await response.json();
        console.log(stock);
        const searchStockTableBody = document.getElementById('stock-search-table').getElementsByTagName('tbody')[0];
        searchStockTableBody.innerHTML = '';
        
        // Check if stock found and then display
        if (stock) {
            const row = searchStockTableBody.insertRow();
            row.insertCell(0).textContent = stock.tickerSymbol;
            row.insertCell(1).textContent = stock.companyName;
            row.insertCell(2).textContent = stock.closingPrice;
            row.insertCell(3).textContent = stock.openingPrice;
            row.insertCell(4).textContent = stock.date;
            row.insertCell(5).textContent = stock.industryGroup;
        } 
        
    } catch (error) {
        console.error('There was a problem fetching:', error);

        const searchStockTableBody = document.getElementById('stock-search-table').getElementsByTagName('tbody')[0];
        searchStockTableBody.innerHTML = '';

        const row = searchStockTableBody.insertRow();
        const cell = row.insertCell(0);
        cell.colSpan = 7; // Msg cell spans all cols
        cell.textContent = 'No stock found';
        
    }
}

async function deleteStock(tickerSymbol) {
    console.log("delete stock");
    
    try {    
        console.log("ticker Symbol: " + tickerSymbol)
        const response = await fetch(`/stock/index/${tickerSymbol}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        getStockTable();        
    } catch (error) {
        console.error('There was a problem deleting:', error);
    }
}