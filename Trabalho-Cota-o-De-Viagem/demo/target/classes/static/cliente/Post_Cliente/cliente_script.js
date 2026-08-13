window.onload = function() {

    document.getElementById("cliente_Nome").value = "";
    document.getElementById("cliente_Email").value = "";
    document.getElementById("cliente_Telefone").value = "";

}

function criarCliente() {


    
    const nome = document.getElementById("cliente_Nome").value = cliente.nome;
    const email = document.getElementById("cliente_Email").value = cliente.email;
    const telefone = document.getElementById("cliente_Telefone").value = cliente.telefone;

    if(!nome || !email || !telefone)
    {

        alert("Por favor, preencha todos os dados");
        return;

    }

    const botao_criar = document.getElementById("botao_criar");

    if(botao_criar)
    {

        botao_criar.disabled = true;

        

        try 
        {

            const salvar = fetch(`http://localhost:8080/api/clientes`, {

                method: "POST",
                headers:
                {

                    "Content-Type": "application/json"

                },
                body: 
                {

                    nome: nome,
                    email: email,
                    telefone: telefone

                }

            });

            if(resp.status == 200 || resp.status == 201)
            {

                alert("Cliente criado com sucesso");


                document.getElementById("cliente_Nome").value = "";
                document.getElementById("cliente_Email").value = "";
                document.getElementById("cliente_Telefone").value = "";

            }

            else
            {

                let tipoError;

                try 
                { 
                    
                    tipoError = await resp.text(); 
                
                } 
                
                catch(error) 
                { 
                    
                    tipoError = resp.statusText; 
                
                }

                throw new Error("Erro ao criar cliente: " + resp.status + " " + tipoError);

            }

        }

        catch(error) 
        {

            alert(error.message);

            document.getElementById("cliente_Nome").value = "";
            document.getElementById("cliente_Email").value = "";
            document.getElementById("cliente_Telefone").value = "";

        };

    }

    if (botao_criar) 
    {
            
        botao_criar.disabled = false;

    }

}