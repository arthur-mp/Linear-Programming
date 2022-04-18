## Programação Linear Força Bruta

Implementado em java o algoritmo força bruta para resolução de problemas em programação linear

Disciplina: Otimização

# Passo a passo de como executar:

    1. Informar se a função objetiva é de máximo ou mínimo
        . máximo: 1
        . mínimo: -1

    2. Informar os coeficientes da função objetiva
        . por padrão é definido apenas duas variáveis, portanto informar 2 coeficientes apenas

    3. Informar a quantidade de restrições
        . Será solicitado:
            . 2 coeficientes
            . Operação (podendo ser <= ou >=)
            . Valor restricional

# Lógica do algoritmo por força bruta

Com as restrições informadas pelo usuário, é realizado a função de força bruta (functionBruteForce), onde a mesma irá testar valores (inteiros apenas) nas restrições, os valores que satisfazerem ambas restrições serão armazenados para serem usados na função objetiva.
Detalhe que o range de valores para serem validados nas restrições é definido no laço de repetição "for" na função "functionBruteForce".

Com esses valores calculados, ambos são calculados na função objetiva e os valores são armazenados em um array que será ordenado de forma crescente.
Uma vez tendo em mãos um array ordenado de valores, analisa os critérios de máximo ou mínimo.

Temos os seguintes casos:
. Sem solução: array de valores vazio;
. Solução:
. máx: último valor do array de valores;
. mín: primeiro valor do array de valores;
. Possui mais de uma solução:
. máx: analisa os dois ultimos elementos do array;
. mín: analisa os dois primeiros valores;
