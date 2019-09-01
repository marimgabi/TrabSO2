import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {

        ArrayList<Trace> listaInicial = new ArrayList<Trace>();

        String path = new String();
        path = "src/traces/bigone.trace";
//        path = "src/traces/bzip.trace";
//        path = "src/traces/gcc.trace";
//        path = "src/traces/sixpack.trace";
//        path = "src/traces/swim.trace";
//        path = "src/traces/file.txt";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o Tamanho da Página: ");
        int tamanhoPagina = scanner.nextInt();
        System.out.print("Digite o tamanho do conjunto: ");
        int tamW = scanner.nextInt();

        File arquivo = new File(path);
        Scanner leitor = new Scanner(arquivo);
        String linha = new String();

        while(leitor.hasNext()){
            linha = leitor.nextLine();
            String[] values = linha.split(" ");
            Trace trace = new Trace(values[0],values[1],tamanhoPagina);
            listaInicial.add(trace);
        }

        int cont=0, it=0;
        ArrayList<Trace> [] conjuntos = new ArrayList[(listaInicial.size()/tamW)+1];
        ArrayList<Integer> [] pagAcessadas = new ArrayList[(listaInicial.size()/tamW)+1];

        for(int i=0; i<conjuntos.length;i++){
            conjuntos[i]= new ArrayList<>();
            pagAcessadas[i]= new ArrayList<>();
        }

        for(Trace a:listaInicial){
            conjuntos[it].add(a);
            cont++;
            if(cont%tamW==0){
                it++;
            }
        }

        //Separa quais foram as diferentes págs acessadas em cada conjunto
        for(int i=0;i<pagAcessadas.length;i++){
            for(Trace a:conjuntos[i]){
                int temp=Integer.parseInt(a.getPage(),2);
                if(!pagAcessadas[i].contains(temp)){
                    pagAcessadas[i].add(temp);
                }
            }
        }

        /*
            Grava num arquivo a quantia de diferentes págs acessadas por conjunto
            Cada linha corresponde a um conjunto de 0..n
         */

        FileWriter arq1 = new FileWriter("src/tracesResut/bigone/graficos/swim1quantidades.txt");
        PrintWriter gravarArq1 = new PrintWriter(arq1);

        for(int i=0;i<pagAcessadas.length;i++){
            gravarArq1.println(pagAcessadas[i].size());
        }

        arq1.close();

        //------------------------------

        //Calcula a média de diferentes páginas acessadas por conjunto
        double mediaPagAcessadas=0;

        for(int i=0;i<pagAcessadas.length;i++){
            mediaPagAcessadas+=pagAcessadas[i].size();
        }

        if(conjuntos[conjuntos.length-1].size()==0){
            mediaPagAcessadas/=(pagAcessadas.length-1);
        }else{
            mediaPagAcessadas/=(pagAcessadas.length);
        }

        /*
            Grava num arquivo as seguintes informações:
            1 - Tamanho de página adotado em bytes
            2 - Tamanho dos conjuntos (delta t)
            3 - Quantos grupos foram gerados
            4 - Media da quantidade de páginas por conjunto
            5 - As diferentes páginas acessadas por cada conjunto
         */

//        FileWriter arq = new FileWriter("src/tracesResut/bigone/bigone5.txt");
//        PrintWriter gravarArq = new PrintWriter(arq);
//
//        gravarArq.print(tamanhoPagina+"\n");
//        gravarArq.print(tamW+"\n");
//        gravarArq.print("Quant grupos: "+conjuntos.length+"\n");
//        gravarArq.print("Media quant grupos: "+mediaPagAcessadas+"\n");
//
//        for(int i=0;i<pagAcessadas.length;i++){
//            //System.out.println("--- Conjunto "+i+" ---\n");
//            gravarArq.print("--- Conjunto "+i+" ---\n");
//            for(int a:pagAcessadas[i]){
//                //System.out.println(a+"\n");
//                gravarArq.print(a+"\n");
//            }
//            //System.out.println("\n");
//            gravarArq.print("\n");
//        }
//
//        arq.close();

        //------------------------------

        //calcula o total de páginas requisitadas ao longo do processo
        ArrayList<Integer> pagsRequisitadas = new ArrayList<Integer>();

        for(Trace a: listaInicial){
            int temp=Integer.parseInt(a.getPage(),2);
            if(!(pagsRequisitadas.contains(temp))){
                pagsRequisitadas.add(temp);
            }
        }

        System.out.println("Total de paginas: "+pagsRequisitadas.size());

        // O índice do vetor corresponde ao index da lista pagsRequisitadas
        ArrayList<Integer> [] acessos = new ArrayList[pagsRequisitadas.size()];

        for(int i=0;i<acessos.length;i++){
            acessos[i] = new ArrayList<>();
        }

        for(int i=0;i<pagAcessadas.length;i++){
            for(Integer a: pagAcessadas[i]){
                int index;
                index=pagsRequisitadas.indexOf(a);
                acessos[index].add(i);
            }
        }

//        FileWriter arq3 = new FileWriter("src/tracesResut/bigone/bigoneInt5.txt");
//        PrintWriter gravarArq3 = new PrintWriter(arq3);
//
//        gravarArq3.print("Tam de pag: "+tamanhoPagina+"\n");
//        gravarArq3.print("Tam conjunto: "+tamW+"\n");
//        gravarArq3.print("Total pags: "+pagsRequisitadas.size()+"\n");
//
//        for(int i=0;i<acessos.length;i++){
//            gravarArq3.print("-----Pag "+pagsRequisitadas.get(i)+"-----\n");
//            for(Integer a: acessos[i]){
//                gravarArq3.print(a+"\n");
//            }
//            gravarArq3.print("\n");
//        }
//
//        arq3.close();
    }
}
