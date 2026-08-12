window.onload = function() {

    document.getElementById("procura_id").value = "";
    document.getElementById("cliente_Id").value = "";
    document.getElementById("cliente_Nome").value = "";
    document.getElementById("cliente_Email").value = "";
    document.getElementById("cliente_Telefone").value = "";

}

function buscarClientePorId() {

    const id = document.getElementById("procura_id").value;

    if (!id || id.trim() === "") 
    {

        alert("Por favor, informe o id do cliente");
        return;

    }

    fetch(`http://localhost:8080/api/clientes/${id}`)
        .then(response => {

            if (response.status === 404)
            {

                throw new Error("Id do cliente não encontrado");

            }

            return response.json();

        })
        .then(cliente => {

            document.getElementById("cliente_Id").value = cliente.id;
            document.getElementById("cliente_Nome").value = cliente.nome;
            document.getElementById("cliente_Email").value = cliente.email;
            document.getElementById("cliente_Telefone").value = cliente.telefone;

        })

        .catch(error => {

            alert(error.message);

            document.getElementById("cliente_Id").value = "";
            document.getElementById("cliente_Nome").value = "";
            document.getElementById("cliente_Email").value = "";
            document.getElementById("cliente_Telefone").value = "";

        });

}