let apiUrl = '/items';

// Получение списка продуктов
async function getItems() {
    try {
        //Шлём get-запрос на /items (метод fetch() по умолчанию отправляет GET-запрос)
        // и получаем список продуктов в виде JSON-а из метода getItemList() в controller-е
        let response = await fetch(apiUrl);
        //в ответе получили список JSON-ов, а здесь сделали из них обьекты
        let data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}
//Создание нового продукта
async function addItem() {
    //Получаем имя нового продукта из Input-а, то есть то, что записано в поле
    let newItemName = document.getElementById("new_item_name").value;

    try {

        let response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify({ name: newItemName }) //тело htth запроса (json объект в теле запроса http)
            //JSON.stringify - преобразует обьект в json
        });

        if (!response.ok) {
            throw new Error(`Failed to add item with name=${newItemName}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}
// Удаление продукта
async function deleteItem(id) {
    try {
        //encodeURIComponent(id) - кодирует специальные символы в URL-адресе
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), { method: 'DELETE' });

        if (!response.ok) {
            throw new Error(`Failed to delete item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}
// Изменение продукта
async function editItem(id) {
    try {
        //encodeURIComponent(id) - кодирует специальные символы в URL-адресе
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), { method: 'PUT'});
        if (!response.ok) {
            throw new Error(`Failed to mark item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data); //отображения обновленного списка на странице
        });

    } catch (error) {
        console.log(error);
    }
}
// Отображение списка продуктов
function displayList(products) {
    let list = document.getElementById("list");
    //чистим свойство innerHTML, чтобы удалить все предыдущие элементы из списка.
    list.innerHTML = "";

    for (let i = 0; i < products.length; i++) {
        let product = products[i];

        let li = document.createElement("li");
        li.appendChild(document.createTextNode(product.name));

        let deleteButton = document.createElement("button");
        deleteButton.appendChild(document.createTextNode("Удалить"));
        deleteButton.onclick = function() {
            deleteItem(product.id);
        };
        li.appendChild(deleteButton);

        let checkbox = document.createElement("input");
        checkbox.type = "checkbox";

        checkbox.checked = product.isMarked;
        checkbox.onclick = function() {
            editItem(product.id);
        };
        li.insertBefore(checkbox, li.firstChild);

        list.appendChild(li);
    }
}

// Обновление списка продуктов на странице при загрузке
getItems().then(data => {
    displayList(data);
});