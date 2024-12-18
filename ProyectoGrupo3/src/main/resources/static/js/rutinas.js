/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result).height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function addCard(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}

function exportarExcel(fragmentId) {
    const fragmento = document.querySelector(`#${fragmentId}`);
    const tabla = fragmento.querySelector("table"); // Encuentra la tabla dentro del fragmento

    if (!tabla) {
        console.error("No se encontró la tabla en el fragmento.");
        return;
    }

    // Extrae los datos de la tabla
    const productos = Array.from(tabla.querySelectorAll("tbody tr")).map(row => {
        const columnas = row.querySelectorAll("td");
        return {
            Numero: columnas[0]?.innerText.trim() || "",
            Descripcion: columnas[2]?.innerText.trim() || "",
            Precio: columnas[3]?.innerText.trim() || "",
            Existencias: columnas[4]?.innerText.trim() || "N/A",
            Activo: columnas[5]?.innerText.trim() || "N/A",
            Categoria: columnas[6]?.innerText.trim() || "",
        };
    });

    // Genera el archivo Excel
    const ws = XLSX.utils.json_to_sheet(productos);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "Productos");

    // Descarga el archivo Excel
    XLSX.writeFile(wb, "listado_productos.xlsx");
}






