window.onload = function() {

    document.getElementById("cliente_Nome").value = "";
    document.getElementById("cliente_Email").value = "";
    document.getElementById("cliente_Telefone").value = "";

}

async function criarCliente() {

    const nome = document.getElementById("cliente_Nome").value;
    const email = document.getElementById("cliente_Email").value;
    const telefone = document.getElementById("cliente_Telefone").value;

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

            const salvar = await fetch(`http://localhost:8080/api/clientes`, {

                method: "POST",
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
                    
                if(salvar.status == 200 || salvar.status == 201)
                {

                    alert("Cliente criado com sucesso");


                    document.getElementById("cliente_Nome").value = "";
                    document.getElementById("cliente_Email").value = "";
                    document.getElementById("cliente_Telefone").value = "";

                }

                else
                {
                    
                    const tipoError = await salvar.text();

                    throw new Error("Erro ao criar cliente: " + salvar.status + " " + tipoError)

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

            document.getElementById("cliente_Nome").value = "";
            document.getElementById("cliente_Email").value = "";
            document.getElementById("cliente_Telefone").value = "";

        };

    }

}