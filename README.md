# Programação Reativa Com Quarkus
A Programacdo Reativa é um modelo de desenvolvimento estruturado em torno de fluxos de dados assincronos.

# Como assim?
Usando a forma tradicional de desenvolvimento para preparar uma boa cerveja chamamos o método prepararAgua(temperatura) e 
prepararMalte(), assim que tivermos isso pronto acionamos o método moer(malte) na sequencia executamos o
metodo misturarTudo() e por fim adicionarLupulo(). Perfeito, a cerveja estará deliciosa.

Mas, imagine se não tivermos malte? Bom, o sistema falhará assim que o método moer(malte) for invocado.
Na programação reativa as coisas aconrecem de forma bem diferente. Os eventos ocorrem de forma paralela sem precisar seguir nenhuma ordem cronológica e linear. O sistema não irá travar pois através de bibliotecas específicad será possível continuar o preparo da cerveja sem a etapa que irá moer o malte, isso porque as outras etapas de preparo serão executadas e quando o malte tiver preparado o método moer(malte) será executado.

# Posso confiar
Sim. As execuções são manipuladas a partir de eventos que notificam os interessados que estão a espera de mensagens para que cada um possa realizar seu trabalho.

# O que mais precisao saber?
Ao usar a programação restiva, os fluxos de dados serão a espinha dorsal do seu aplicativo. Eventos, mensagens, chamadas e até mesmo falhas serão transmitidas por um fluxo de dados, possibilitando observar esses fluxos e reagir quando um valor é emitido. Pode-se dizer que a programação reativa é uma versão extendida e mais poderosa que do padrão [Observer](https://pt.wikipedia.org/wiki/Observer)

Os frameworks reativos oferecem uma espetacular caixa de ferramentas para manipulação de stream; podemos filtrá-los, transforma-los, combiná-los entre outras dezenas de operações. 

# Em quais situações posso utilizar?

* __Abstração sobre processamento assícrono:__ Frameworks reativos fornecem uma função simples para processamento assícrono, desafogando sua aplicação dos detalhes mais complexos envolvendo MULTITHREADING e permitindo que seu código possa concentra-se na lógica dde manipulação de eventos. 

* __Chamada para serviços externos(REST, RPC,etc)__ Operações realizadas sobre HTTP são bloqueantes por natureza; Ao fornecer uma simplificação para código assícrono a programação reativa pode ajuda-lo a desbloquear o código de cliente HTTP. Mas isso é a parte mais simples. Em arquitetura de microserviço é comum um código de back-and construir uma composição entre várias chamadas e Frameworks reativos podem ajudar a orquestrar chamadas com a vantagem de NÃO bloquear o código no cliente.
