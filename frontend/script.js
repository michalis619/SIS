document.addEventListener('DOMContentLoaded', () => {
    const apiBaseUrl = 'http://localhost:8080/api/products';

    const productTable = document.getElementById('productTable').getElementsByTagName('tbody')[0];
    const productForm = document.getElementById('productForm');
    const categoryFilter = document.getElementById('categoryFilter');

    function fetchProducts() {
        fetch(apiBaseUrl)
            .then(response => response.json())
            .then(products => {
                productTable.innerHTML = ''; // Clear existing rows
                products.forEach(product => {
                    const row = productTable.insertRow();
                    row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.description}</td>
                        <td>${product.category}</td>
                        <td>${product.price}</td>
                        <td>${product.special ? 'Yes' : 'No'}</td>
                        <td>
                            <button onclick="deleteProduct('${product.id}')">Delete</button>
                        </td>
                    `;
                    if (product.special) row.classList.add('special');
                });
            });
    }

    // Create a new product
    productForm.addEventListener('submit', event => {
        event.preventDefault();

        const productId = document.getElementById('productId').value;
        const product = {
            description: document.getElementById('description').value,
            category: document.getElementById('category').value,
            price: parseFloat(document.getElementById('price').value),
            special: document.getElementById('special').checked
        };

        const method =  'POST';
        const url = productId ? `${apiBaseUrl}/${productId}` : apiBaseUrl;

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product)
        })
        .then(response => {
            if (response.ok) {
                fetchProducts();
                productForm.reset();
                document.getElementById('productId').value = ''; // Clear the ID
            } else {
                console.error('Failed to save product:', response.statusText);
            }
        });
    });

    // Delete product
    window.deleteProduct = function(id) {
        fetch(`${apiBaseUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                fetchProducts();
            } else {
                console.error('Failed to delete product:', response.statusText);
            }
        });
    }

    // Filter by category
    categoryFilter.addEventListener('change', () => {
        const category = categoryFilter.value;
        const rows = productTable.getElementsByTagName('tr');
        Array.from(rows).forEach(row => {
            const cell = row.getElementsByTagName('td')[2]; // Category cell
            if (cell) {
                const matches = !category || cell.textContent === category;
                row.style.display = matches ? '' : 'none';
            }
        });
    });

    // Initial fetch
    fetchProducts();
});
