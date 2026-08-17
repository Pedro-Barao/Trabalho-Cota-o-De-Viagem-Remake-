window.onload = function() {

    document.getElementById("procura_id").value = "";

    document.getElementById("cliente_Id").value = "";
    document.getElementById("cliente_Nome_Antigo").value = "";
    document.getElementById("cliente_Email_Antigo").value = "";
    document.getElementById("cliente_Telefone_Antigo").value = "";

    document.getElementById("cliente_Nome_Novo").value = "";
    document.getElementById("cliente_Email_Novo").value = "";
    document.getElementById("cliente_Telefone_Novo").value = "";

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
        document.getElementById("cliente_Nome_Antigo").value = cliente.nome;
        document.getElementById("cliente_Email_Antigo").value = cliente.email;
        document.getElementById("cliente_Telefone_Antigo").value = cliente.telefone;

    })

    .catch(error => {

        alert(error.message);

        document.getElementById("cliente_Id").value = "";
        document.getElementById("cliente_Nome_Antigo").value = "";
        document.getElementById("cliente_Email_Antigo").value = "";
        document.getElementById("cliente_Telefone_Antigo").value = "";

    });

}

async function alterarCliente() {

    const id = document.getElementById("cliente_Id").value;
    const nome = document.getElementById("cliente_Nome_Novo").value;
    const email = document.getElementById("cliente_Email_Novo").value;
    const telefone = document.getElementById("cliente_Telefone_Novo").value;

    if(!nome || !email || !telefone)
    {

        alert("Por favor, preencha todos os dados");
        return;

    }

    const botao_alterar = document.getElementById("botao_alterar");

    if(botao_alterar)
    {

        try 
        {

            const alterar = await fetch(`http://localhost:8080/api/clientes/${id}`, {

                method: "PUT",
                headers:
                {

                    "Content-Type": "application/json"

                },
                body: JSON.stringify({

                    nome: nome,
                    email: email,
                    telefone: telefone

                })

            });

            try 
            { 
                    
                if(alterar.status == 200 || alterar.status == 204)
                {

                    alert("Cliente alterado com sucesso");

                    document.getElementById("cliente_Id").value = "";
                    document.getElementById("cliente_Nome_Antigo").value = "";
                    document.getElementById("cliente_Email_Antigo").value = "";
                    document.getElementById("cliente_Telefone_Antigo").value = "";

                    document.getElementById("cliente_Nome_Novo").value = "";
                    document.getElementById("cliente_Email_Novo").value = "";
                    document.getElementById("cliente_Telefone_Novo").value = "";

                }

                else
                {
                    
                    const tipoError = await alterar.text();

                    throw new Error("Erro ao criar cliente: " + alterar.status + " " + tipoError)

                }
                
            } 
                
            catch(error) 
            { 
                    
                alert(error.message);
                
            }

        }

        catch(error) 
        {

            alert(error.message);

            document.getElementById("cliente_Id").value = "";
            document.getElementById("cliente_Nome_Antigo").value = "";
            document.getElementById("cliente_Email_Antigo").value = "";
            document.getElementById("cliente_Telefone_Antigo").value = "";

            document.getElementById("cliente_Nome_Novo").value = "";
            document.getElementById("cliente_Email_Novo").value = "";
            document.getElementById("cliente_Telefone_Novo").value = "";

        };

    }

}