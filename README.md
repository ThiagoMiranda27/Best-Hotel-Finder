# Best-Hotel-Finder

Reserva de hotéis - melhor hotel para o período - Rodrigo e colegas

 
 
Uma rede de hotéis em Porto Alegre gostaria de oferecer um serviço de reservas pela internet, chamado Best Hotel Finder®. A rede é composta por três hotéis: Plaza, Hilton e Continental. Cada hotel tem tarifas diferentes para dia de semana ou no final de semana, incluindo tarifas específicas para participantes de  programas de fidelidade. Ainda, cada hotel tem uma classificação, indicando quão bom o serviço deles é.

 

O Hotel Plaza possui uma classificação 3 e suas taxas de dia de semana são R$110 para clientes normais e R$80 para participantes do programa de fidelidade. As taxas de final de semana são respectivamente R$90 e R$80 para clientes normais e participantes do programa de fidelidade.

 

Já o Hotel Hilton possui uma classificação 4 e suas taxas de dia de semana são R$160 para clientes normais e R$110 para participantes do programa de fidelidade. As taxas de final de semana são respectivamente R$60 e R$50 para clientes normais e participantes do programa de fidelidade.

 

Por fim, o Hotel Continental possui uma classificação 5 e suas taxas de dia de semana são R$220 para clientes normais e R$100 para participantes do programa de fidelidade. As taxas de final de semana são respectivamente R$150 e R$40 para clientes normais e participantes do programa de fidelidade.



Hotel |Classificação |Dia de semana |Dia de semana reward |Fim de semana |Fim de semana reward |
:---: | :---: | :---: | :---: |
Plaza |3 | R$110 | R$80 | R$90 | R$80 |
:---: | :---: |:---: | :---: |
Hilton | 4 | R$160 | R$110 | R$60 | R$50 |
:---: | :---: | :---: | :---: |
Continenta | 5 | R$220 | R$100 | R$150 | R$40 |
:---: | :---: | :---: | :---: |



Dadas essas informações, desenvolva um sistema para buscar o hotel mais em conta. A entrada é uma sequência de datas desejadas por um cliente participante ou não do programa de fidelidade. Utilize "Regular" para denominar um cliente normal e "Reward" para um cliente participante do programa de fidelidade. A saída deverá ser o hotel disponível mais barato e em caso de empate, o hotel com a maior classificação deverá ser retornado.




Padrão da entrada:

<tipo_do_cliente>: < data1 >, < data2 >, < data3 >, …

 
Padrão da saída:

<nome_do_hotel_mais_barato>

**Exemplos:**



Entrada 1:

Regular: 16Mar2009(seg), 17Mar2009(ter), 18Mar2009(qua)

Saída 1:

Plaza


Entrada 2:

Regular: 20Mar2009(sex), 21Mar2009(sab), 22Mar2009(dom)
 
Saída 2:

Hilton



Entrada 3:

Rewards: 26Mar2009(qui), 27Mar2009(sex), 28Mar2009(sab)

Saída 3:

Continental
