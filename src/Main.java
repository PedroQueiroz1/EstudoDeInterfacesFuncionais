import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        /*
            Supplier
            Fornecedor de valores
            Não aceita nenhum argumento
            Retorna valor do tipo T
         */
        Supplier<LocalDate> supplierLocalDate = LocalDate::now;
        Supplier<LocalDate> supplierLocalDate2 = () -> LocalDate.now();
        System.out.println(supplierLocalDate.get());
        System.out.println(supplierLocalDate2.get());

        /*
            Consumer
            Realiza alguma ação ou operação sem precisar de resultado de retorno
         */
        List<String> nomes = new ArrayList<String>();
        nomes.add("Nome 1");
        nomes.add("Nome 2");
        nomes.add("Nome 3");

        Consumer<String> imprimirNomes = System.out::println;
        Consumer<String> imprimirNomes2 =  nome -> System.out.println(nome);

        nomes.forEach(imprimirNomes);
        System.out.println("***");
        nomes.forEach(imprimirNomes2);

        /*
            BiConsumer
            Não retorna nenhum valor.
         */
        List<String> sobrenomes = new ArrayList<>();
        sobrenomes.add("Sobrenome1");
        sobrenomes.add("Sobrenome2");
        sobrenomes.add("Sobrenome3");

        BiConsumer<String, String> juntarNomeSobrenome = (nome, sobrenome)
                -> System.out.println(nome + " " + sobrenome);

        if (nomes.size() == sobrenomes.size()) {
            for(int i = 0; i < nomes.size(); i++) {
                String nome = nomes.get(i);
                String sobrenome = sobrenomes.get(i);
                juntarNomeSobrenome.accept(nome, sobrenome);
            }
        }

        /*
            Predicate
            retorna true ou false
         */
        Collection<String> nomesColl = nomes.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
        Predicate<String> contemNome = nome -> nomesColl.stream().anyMatch(item -> item.equals(nome.toLowerCase()));
        System.out.println(contemNome.test("Nome 2"));

        /*
            Function
         */
        Integer numero = 1;
        Function<Integer, String> transformaEmString = x -> x.toString();
        System.out.println(transformaEmString.apply(numero).equals(1));

        /*
            BiFunction
         */
        Integer numero2 = 2;
        BiFunction<Integer, Integer, String> soma = (x,y) -> "Soma: " + (x + y) + "\n";
        System.out.println(soma.apply(numero, numero2));

        /*
            UnaryOperator
         */
        UnaryOperator<Integer> numero4 = x -> x + 4;
        Integer numero5 = numero4.apply(numero2);
        System.out.println(numero5);

        /*
            BinaryOperator
        */
        BinaryOperator<Integer> numeroSoma = (x,y) -> x + y; // Integer::sum
        System.out.println(numeroSoma.apply(numero5, numero2));

        /*
            Peek
            usado principalmente para depuração,
            inspeção ou observação dos elementos
            à medida que fluem através do fluxo,
            sem modificar os elementos em si.
        */
        Stream<String> sobrenomesMaiusculos = sobrenomes.stream()
                .peek(n -> System.out.println("Sobrenome: " + n)).map(n -> "Sobrenome maiusculo: " + n.toUpperCase());
                sobrenomesMaiusculos.forEach(System.out::println);

        /*
            Reduce
         */
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        int somaNumeros = numeros.stream()
                .reduce(10, (a, b) -> a + b);
        System.out.println(somaNumeros);
    }
}