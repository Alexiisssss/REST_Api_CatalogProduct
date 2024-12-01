function filterProducts() {
    const name = document.getElementById('searchName').value.toLowerCase();
    const category = document.getElementById('searchCategory').value;
    const minPrice = parseFloat(document.getElementById('minPrice').value) || 0;
    const maxPrice = parseFloat(document.getElementById('maxPrice').value) || Infinity;

    const rows = document.querySelectorAll('#productTable tr');

    rows.forEach(row => {
        const productName = row.children[0].textContent.toLowerCase();
        const productCategory = row.children[3].textContent;
        const productPrice = parseFloat(row.children[2].textContent);

        const matchesName = name === '' || productName.includes(name);
        const matchesCategory = category === '' || productCategory.includes(category);
        const matchesPrice = productPrice >= minPrice && productPrice <= maxPrice;

        row.style.display = matchesName && matchesCategory && matchesPrice ? '' : 'none';
    });
}
