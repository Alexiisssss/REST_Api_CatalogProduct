async function loadCategories() {
    const response = await fetch('/api/categories');
    const categories = await response.json();
    const tableBody = document.getElementById('categoriesTableBody');
    tableBody.innerHTML = '';
    categories.forEach(category => {
        const row = `<tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td>
                        <button onclick="editCategory(${category.id})">Редактировать</button>
                        <button onclick="deleteCategory(${category.id})">Удалить</button>
                    </td>
                </tr>`;
        tableBody.insertAdjacentHTML('beforeend', row);
    });
}

async function addCategory(event) {
    event.preventDefault();
    const name = document.getElementById('categoryName').value;
    const description = document.getElementById('categoryDescription').value;
    await fetch('/api/categories', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({name, description})
    });
    document.getElementById('categoryForm').reset();
    loadCategories();
}

async function deleteCategory(id) {
    await fetch(`/api/categories/${id}`, {method: 'DELETE'});
    loadCategories();
}

async function editCategory(id) {
    const name = prompt('Введите новое название категории:');
    const description = prompt('Введите новое описание категории:');
    if (name && description) {
        await fetch(`/api/categories/${id}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name, description})
        });
        await loadCategories();
    }
}

document.addEventListener('DOMContentLoaded', loadCategories);
