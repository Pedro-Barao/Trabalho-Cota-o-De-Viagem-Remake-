window.onload = function() {

    const cliente_lista = document.getElementById("cliente_lista");

    cliente_lista.innerHTML = "";

}

function buscarCliente() {
    
    fetch(`http://localhost:8080/api/clientes`)
    .then(response => response.json())
    .then(clientes => {

        clientes.forEach(cliente => {

            const linha = document.createElement("tr");

            const coluna_id = document.createElement("td");
            const coluna_nome = document.createElement("td");
            const coluna_email = document.createElement("td");
            const coluna_telefone = document.createElement("td");

            coluna_id.textContent = cliente.id;
            coluna_nome.textContent = cliente.nome;
            coluna_email.textContent = cliente.email;
            coluna_telefone.textContent = cliente.telefone;

            linha.appendChild(coluna_id);
            linha.appendChild(coluna_nome);
            linha.appendChild(coluna_email);
            linha.appendChild(coluna_telefone);

            const cliente_lista = document.getElementById("cliente_lista");

            cliente_lista.appendChild(linha);

        })

    })

    .catch(error => {

        alert(error.message);

    });

}